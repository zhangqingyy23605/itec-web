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
    private int numberOfPages;// TODO 如果有20页，下面页码导航会过于长
    private int firstResult;

    public void autoSetPageNumberAndFirstResult() {
        if (this.pageAction != null) {
            switch (this.pageAction) {
                case "first":
                    this.pageNumber = 1;
                    break;
                case "last":
                    this.pageNumber = this.numberOfPages;
                    break;
                case "previous":
                    if (this.pageNumber > 1) {
                        this.pageNumber--;
                    }
                    break;
                case "next":
                    if (this.pageNumber < this.numberOfPages) {
                        this.pageNumber++;
                    }
                    break;
            }
            this.pageAction = null;
        }
        this.firstResult = (this.pageNumber - 1) * this.pageSize;
    }

    public void setNumberOfRecordsAndPages(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
        this.numberOfPages = (int)Math.ceil(this.numberOfRecords / (double)this.pageSize);
        this.autoSetPageNumberAndFirstResult();
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

    public void setPageAction(String pageAction) {
        this.pageAction = pageAction;
    }
}
