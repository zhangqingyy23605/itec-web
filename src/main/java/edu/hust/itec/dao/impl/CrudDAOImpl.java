package edu.hust.itec.dao.impl;

import edu.hust.itec.dao.CrudDAO;
import edu.hust.itec.model.Category;
import edu.hust.itec.util.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public abstract class CrudDAOImpl<T, ID extends Serializable> implements CrudDAO<T, ID> {

    private Class<T> clazz;
    @SuppressWarnings("unchecked")
    public CrudDAOImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    //item
    public boolean saveOrUpdate(T t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(t);
        return true;
    }
    public boolean deleteById(ID id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete" + clazz.getName() + "t where t.id = :id");
        query.setParameter("id", id);
        if (query.executeUpdate() == 0 ) {
            return false;
        } else {
            return true;
        }
    }
    @SuppressWarnings("unchecked")
    public T getById(ID id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (T)session.get(clazz, id);
    }

    //list
    @SuppressWarnings("unchecked")
    public Collection<T> getByCategory(Page page) {
        return this.getByCategory(page, "", "");
    }

    public Collection<T> getByCategory(Page page,
            String fields) {
        return this.getByCategory(page, fields, "");
    }

    @SuppressWarnings("unchecked")
    public Collection<T> getByCategory(Page page,
            String fields, String order) {

        String categoryName = page.getCategoryName();
        Category category = categoryMap.get(categoryName);
        if (category == null) {//如果分类名称错误，就用默认值
            category = categoryMap.get(this.rootCategoryName);
        }
        Collection<Integer> categoryIds = category.getLeavesId();
        int numberOfRecords = this.getTotalNumberByCategory(categoryIds);
        page.setNumberOfRecordsThenAutoSetOthers(numberOfRecords);
        //需要先知道有多少页后，才能知道当前访问的页码是否有问题，并且从数据库第几条开始查询
        int firstResult = page.getFirstResult();
        int pageSize = page.getPageSize();

        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "select " + fields + " from " + clazz.getName() +
                        " t where t.category.id in :categoryIds " + order);
        query.setParameterList("categoryIds", categoryIds);
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);
        return (Collection<T>)query.list();
    }
    public int getTotalNumberByCategory(Collection<Integer> categoryIds) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from " + clazz.getName() + " t where t.category.id in :categoryIds");
        query.setParameterList("categoryIds", categoryIds);
        return ((Long)query.uniqueResult()).intValue();
    }

    //category========================================================
    private String rootCategoryName;
    private Map<String, Category> categoryMap;
    private Collection<Category> categoryLeaves;

    //Begin initCategoryTree
    public void initCategoryTree(String rootCategoryName) {
        this.rootCategoryName = rootCategoryName;

        Map<String, Category> categoryMap = new HashMap<>();
        List<Category> categoryLeaves = new ArrayList<>();

        System.out.println("开始加载\"" + rootCategoryName + "\"分类信息");
        Category rootCategory = this.getCategoryByName(rootCategoryName);
        if (rootCategory == null) {
            System.out.println("数据库中不存在\"" + rootCategoryName + "\"的分类信息！");
        } else {
            generateCategoryMap(rootCategory, categoryMap);
            extractCategoryLeaves(categoryMap, categoryLeaves);
            System.out.println("成功加载\"" + rootCategoryName + "\"分类信息");
        }

        this.categoryMap = categoryMap;
        this.categoryLeaves = categoryLeaves;
    }
    public Category getCategoryByName(String categoryName) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from Category c where name=:name");
        query.setString("name", categoryName);
        return (Category)query.uniqueResult();
    }
    private void generateCategoryMap(Category rootCategory, Map<String, Category> categoryMap) {
        Queue<Category> queue = new LinkedList<>();
        queue.add(rootCategory);
        Category currentCategory;
        while (!queue.isEmpty()) {
            currentCategory = queue.remove();
            categoryMap.put(currentCategory.getName(), currentCategory);

            List<Category> children = currentCategory.getChildren();
            if (children.isEmpty()) {
                //如果某个节点没有孩子，就用parent向上找所有的祖先，放入他们的leaves中。
                int leafId = currentCategory.getId();

                Category upperCategory = currentCategory;
                upperCategory.addLeafId(leafId);

                while(upperCategory.getParent() != null) {
                    upperCategory = upperCategory.getParent();
                    upperCategory.addLeafId(leafId);
                }
            } else {
                for (Category subRootCategory : children) {
                    queue.add(subRootCategory);
                }
            }
        }
    }
    private void extractCategoryLeaves(Map<String, Category> categoryMap, List<Category> categoryLeaves) {
        for (Category category : categoryMap.values()) {
            if (category.getChildren().isEmpty()) {
                categoryLeaves.add(category);
            }
        }
    }
    //End initCategoryTree

    public Category getRootCategory() {
        return this.categoryMap.get(this.rootCategoryName);
    }

    public Collection<Category> getCategoryLeaves() {
        return this.categoryLeaves;
    }
    //category========================================================

    @Autowired
    protected SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
