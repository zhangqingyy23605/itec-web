package edu.hust.itec.propertyeditor;

import edu.hust.itec.model.Category;
import edu.hust.itec.model.News;
import edu.hust.itec.service.NewsService;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * Created by xsh on 2015/5/20.
 */
public class NewsPropertyEditor extends PropertyEditorSupport {
    private NewsService newsService;
    public NewsPropertyEditor(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public String getAsText() {
        //Handle null value, value of incorrect type, etc here
        return String.valueOf(((News)getValue()).getCategory().getName());
    }

    @Override
    public void setAsText(String categoryName) throws IllegalArgumentException {
        //handle empty string, number format exception, etc

        Category category = newsService.getCateogyrByName(categoryName);
        setValue(category);
    }
}
