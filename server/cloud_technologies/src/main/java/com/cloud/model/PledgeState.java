package com.cloud.model;

public class PledgeState {

    private String category;
    private String state;
    private long count;



    public PledgeState(String category, String state, long count) {
        this.category = category;
        this.state = state;
        this.count = count;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


}
