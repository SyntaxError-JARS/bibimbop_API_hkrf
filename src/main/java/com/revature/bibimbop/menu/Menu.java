package com.revature.bibimbop.menu;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "item_name")

    private String menuNumber;
    private int cost;
    private String protein;
    @Column(name = "is_substitutable")
    private boolean isSubstitutable;


    public Menu(String menuNumber, int cost, String protein, boolean isSubstitutable) {
        super();
        this.menuNumber = menuNumber;
        this.cost = cost;
        this.protein = protein;
        this.isSubstitutable = isSubstitutable;
    }

    public String getMenuNumber() {
        return menuNumber;
    }

    public void setMenuNumber(String menuNumber) {
        this.menuNumber = menuNumber;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public boolean getIsSubstitutable() {
        return isSubstitutable;
    }

    public void setIsSubstitutable(boolean isSubstitutable) {
        this.isSubstitutable = isSubstitutable;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuNumber='" + menuNumber + '\'' +
                ", cost=" + cost +
                ", protein=" + protein +
                ", isSubstitutable=" + isSubstitutable +
                '}';
    }
}
