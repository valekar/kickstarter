package com.cloud.ViewModel;

public class ProjectVM {

    private String subCategory;
    private String category;
    private String country;


    private String state;

    public ProjectVM(String subCategory, String category, String country){
        this.category = category;
        this.subCategory = subCategory;
        this.country = country;
        this.state = state;
    }

    public ProjectVM(){}

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
