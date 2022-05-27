package com.revature.bibimbop.menu;

import com.revature.bibimbop.menu.Menu;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "menu_Selection")
    private int menuSelection;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "selection_type", referencedColumnName = "id")
    private MenuType menuType;

    public Menu(String menuName, int menuSelection, MenuType menuType) {
        this.menuName = menuName;
        this.menuSelection = menuSelection;
        this.menuType = menuType;
    }

    public Menu() {
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuSelection() {
        return menuSelection;
    }

    public void setMenuSelection(int menuSelection) {
        this.menuSelection = menuSelection;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return getMenuSelection() == menu.getMenuSelection() && Objects.equals(getMenuName(), menu.getMenuName()) && Objects.equals(getElementType(), ability.getElementType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenuName(), getMenuSelection(), getMenuType());
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuName='" + menuName + '\'' +
                ", menuSelection=" + menuSelection +
                ", menuType=" + menuType +
                '}';
    }
}
