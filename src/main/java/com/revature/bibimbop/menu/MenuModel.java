package com.revature.bibimbop.menu;

public class MenuModel {

    private String menuItem;
    private double cost;
    private String protein;
    private Integer isSubstitutable;

    public MenuModel(String menuItem, double cost, String protein, Integer isSubstitutable) {
        this.menuItem = menuItem;
        this.cost = cost;
        this.protein = protein;
        this.isSubstitutable = isSubstitutable;

    }

    public MenuModel() {

    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public Integer getIsSubstitutable() {
        return isSubstitutable;
    }

    public void setIsSubstitutable(Integer isSubstitutable) {
        this.isSubstitutable = isSubstitutable;
    }


    @Override
    public String toString() {
        return "MenuModel{" +
                "menuItem='" + menuItem + '\'' +
                ", cost=" + cost +
                ", protein='" + protein + '\'' +
                ", isSubstitutable=" + isSubstitutable +
                '}';
    }
}




