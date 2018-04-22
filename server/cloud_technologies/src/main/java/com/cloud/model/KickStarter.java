package com.cloud.model;


import java.io.Serializable;

/**
 * <p>Class representing the csv file columns</p>
 */
public class KickStarter  {

    private int ID;
    private String name;
    private String category;
    private String main_category;
    private String currency;
    private String deadline;
    private double goal;
    private String launched;
    private double pledged;
    private String state;
    private int backers;
    private String country;
    private double usd_pledged;
    private double usd_pledged_real;
    private double usd_goal_real;


    public KickStarter(int ID,String name, String category, String main_category, String currency,
                       String deadline, double goal, String launched, double pledged, String state,
                       int backers, String country, double usd_pledged, double usd_pledged_real, double usd_goal_real){
        this.ID=ID;
        this.name=name;
        this.category=category;
        this.main_category=main_category;
        this.currency=currency;
        this.deadline=deadline;
        this.goal=goal;
        this.launched=launched;
        this.pledged=pledged;
        this.state=state;
        this.backers=backers;
        this.country=country;
        this.usd_pledged=usd_pledged;
        this.usd_pledged_real=usd_pledged_real;
        this.usd_goal_real=usd_goal_real;
    }

        /*public KickStarter(int ID,String name,String category, String main_category,
                           String currency,String deadline,double goal,String launched){
        this.ID=ID;
        this.name=name;
        this.category=category;
        this.main_category=main_category;
        this.currency=currency;
        this.deadline=deadline;
        this.goal=goal;
        this.launched=launched;
        /*this.pledged=pledged;
        this.state=state;
        this.backers=backers;
        this.country=country;*/
        /*this.usd_pledged=usd_pledged;
        this.usd_pledged_real=usd_pledged_real;
        this.usd_goal_real=usd_goal_real;
    }*/


    public KickStarter(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMain_category() {
        return main_category;
    }

    public void setMain_category(String main_category) {
        this.main_category = main_category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public String getLaunched() {
        return launched;
    }

    public void setLaunched(String launched) {
        this.launched = launched;
    }

    public double getPledged() {
        return pledged;
    }

    public void setPledged(double pledged) {
        this.pledged = pledged;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getBackers() {
        return backers;
    }

    public void setBackers(int backers) {
        this.backers = backers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getUsd_pledged() {
        return usd_pledged;
    }

    public void setUsd_pledged(double usd_pledged) {
        this.usd_pledged = usd_pledged;
    }

    public double getUsd_pledged_real() {
        return usd_pledged_real;
    }

    public void setUsd_pledged_real(double usd_pledged_real) {
        this.usd_pledged_real = usd_pledged_real;
    }

    public double getUsd_goal_real() {
        return usd_goal_real;
    }

    public void setUsd_goal_real(double usd_goal_real) {
        this.usd_goal_real = usd_goal_real;
    }
}
