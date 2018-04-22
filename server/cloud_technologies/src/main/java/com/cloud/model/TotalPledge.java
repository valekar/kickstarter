package com.cloud.model;

public class TotalPledge {

    private String category;
    private double pledged;
    private long backers;


    public TotalPledge(String category,double pledged,long backers){
        this.category = category;
        this.pledged = pledged;
        this.backers = backers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPledged() {
        return pledged;
    }

    public void setPledged(double pledged) {
        this.pledged = pledged;
    }

    public double getBackers() {
        return backers;
    }

    public void setBackers(long backers) {
        this.backers = backers;
    }
}
