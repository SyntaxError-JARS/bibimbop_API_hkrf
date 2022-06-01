package com.revature.bibimbop.menu;



import com.revature.bibimbop.util.ConnectionFactory;

import java.sql.*;
import java.io.IOException;

import java.util.List;

public class MenuDao {

    //    // MVP - Add items to the menu
    public MenuModel createMenu(String menuItem, double cost, String protein, Integer isSubstitutable) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection();){
            String sql = "insert into menu values (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,menuItem);
            ps.setDouble(2, cost);
            ps.setString(3, protein);
            ps.setInt(4, isSubstitutable);

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0){
                throw new RuntimeException();
            }

            followUpCreateMenu(menuItem);

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public MenuModel followUpCreateMenu(String menuItem) {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        try {
            String sql2 = "select * from menu where menu_item = ?";
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setString(1, menuItem);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            MenuModel newMenuItem = new MenuModel();

            newMenuItem.setMenuItem(rs.getString("menu_item"));
            newMenuItem.setCost(rs.getDouble("cost"));
            newMenuItem.setProtein(rs.getString("protein"));
            newMenuItem.setIsSubstitutable(rs.getInt("is_substitutable"));

            return newMenuItem;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }



    //    // MVP - View all items on the menu without needing to Register or Login
    public MenuModel[] findAllMenuItems() throws IOException {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        MenuModel[] menuItems = new MenuModel[20];

        int index = 0;

        try {
            String sql = "select * from menu";
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {

                MenuModel fillInMenu = new MenuModel();

                fillInMenu.setMenuItem(rs.getString("menu_item"));
                fillInMenu.setCost(rs.getDouble("cost"));
                fillInMenu.setProtein(rs.getString("protein"));
                fillInMenu.setIsSubstitutable(rs.getInt("is_substitutable"));

                menuItems[index] = fillInMenu;
                index++;

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return menuItems;

    }

    //     MVP - Delete items to the menu
    public boolean deleteByMenuItem(String menuItem) {
        Connection conn = ConnectionFactory.getInstance().getConnection();{
            String sql = "delete from menu where menu_item = ?";

            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, menuItem);

                int checkInsert = ps.executeUpdate();

                if (checkInsert == 0){
                    throw new RuntimeException();
                }

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    //    // MVP - Update items to the menu
    public MenuModel updateMenu(double cost, String protein, Integer isSubstitutable, String menuItem) {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        String sql = "update menu set cost = ?, protein = ?, is_substitutable = ? where menu_item = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, cost);
            ps.setString(2, protein);
            ps.setInt(3, isSubstitutable);
            ps.setString(4, menuItem);

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException();
            }

            followUPUpdateMenu(menuItem);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public MenuModel followUPUpdateMenu(String menuItem){
        Connection conn = ConnectionFactory.getInstance().getConnection();

        try {
            String sql = "select * from menu where menu_item = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, menuItem);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            MenuModel updateMenuItem = new MenuModel();

            updateMenuItem.setCost(rs.getDouble("cost"));
            updateMenuItem.setProtein(rs.getString("protein"));
            updateMenuItem.setIsSubstitutable(rs.getInt("is_substitutable"));
            updateMenuItem.setMenuItem(rs.getString("menu_item"));

            return updateMenuItem;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}