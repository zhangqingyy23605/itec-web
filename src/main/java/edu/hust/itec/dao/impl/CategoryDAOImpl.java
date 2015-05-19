package edu.hust.itec.dao.impl;

import edu.hust.itec.model.Category;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Scope("prototype")
@Transactional
public class CategoryDAOImpl {
    //category========================================================
    private String rootCategoryName;
    private Map<String, Category> categoryMap;
    private Collection<Category> categoryLeaves;

    //Begin initCategoryTree
    public void initCategoryTree(String rootCategoryName) { //TODO Code Review 寻找优化点
        this.rootCategoryName = rootCategoryName;

        Map<String, Category> categoryMap = new HashMap<>();
        List<Category> categoryLeaves = new ArrayList<>();

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
