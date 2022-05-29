package com.revature.bibimbop.menu;


public class MenuDTO {


    private String menuItem;
    private double cost;
    private String protein;
    private Integer isSubstitutable;

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
}