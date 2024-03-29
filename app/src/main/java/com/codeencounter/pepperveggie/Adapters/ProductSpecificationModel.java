package com.codeencounter.pepperveggie.Adapters;

public class ProductSpecificationModel {

    private String featureName,featureValue;
    public static final int SPECIFICATION_TITILE=0;
    public static final int SPECIFICATION_BODY=1;
    private int type;
    private String title;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ProductSpecificationModel(int type, String title) {

        this.type=type;
        this.title=title;
       }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public ProductSpecificationModel(String featureName, String featureValue, int type) {
        this.featureName = featureName;
        this.featureValue = featureValue;
        this.type = type;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }
}
