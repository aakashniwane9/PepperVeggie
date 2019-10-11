package com.codeencounter.pepperveggie.Adapters;

public class CategoryModel {

    private String catergoryIconLink;
    private String categoryName;

    public CategoryModel(String catergoryIconLink, String categoryName) {
        this.catergoryIconLink = catergoryIconLink;
        this.categoryName = categoryName;
    }

    public String getCatergoryIconLink() {
        return catergoryIconLink;
    }

    public void setCatrgoryIconLink(String catergoryIconLink) {
        this.catergoryIconLink = catergoryIconLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
