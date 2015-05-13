package edu.hust.itec.util;

/**
 * Created by xsh on 2015/4/26.
 */
public class Page {
    private int pageSize = 10;
    private int pageNumber = 1;
    private String columnName;
    private String categoryName;
    private String pageAction;

    private int numberOfRecords;
    private int numberOfPages;// TODO 如果有20页，下面页码导航会过于长：所有工作完成后用jQuery改进
    private int firstResult;//offset

    public void setNumberOfRecordsThenAutoSetOthers(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;

        //set the number of page automatically
        this.numberOfPages = (int)Math.ceil(this.numberOfRecords / (double)this.pageSize);

        //set the page number automatically
        if (this.pageAction != null) {
            switch (this.pageAction) {
                case "first":
                    this.pageNumber = 1;
                    break;
                case "last":
                    this.pageNumber = this.numberOfPages;
                    break;
                case "previous":
                    this.pageNumber--;
                    break;
                case "next":
                    this.pageNumber++;
                    break;
                default:
                    try {
                        this.pageNumber = Integer.parseInt(this.pageAction);
                    } catch(NumberFormatException e) {}
                }
            this.pageAction = null;
        }
        if(this.pageNumber > this.numberOfPages) {
            this.pageNumber = this.numberOfPages;
        }
        if(this.pageNumber < 1) {
            this.pageNumber = 1;
        }

        //set the first result automatically
        this.firstResult = (this.pageNumber - 1) * this.pageSize;
    }

    public void setPageAction(String pageAction) {
        this.pageAction = pageAction;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFirstResult() {
        return firstResult;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
