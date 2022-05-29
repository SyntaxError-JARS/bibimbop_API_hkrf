package com.revature.bibimbop.order;

import com.revature.bibimbop.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao {

    public OrderModel createCustomOrder(int id, String menuItem, String comment, int isFavorite, String orderDate, String customerUsername) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection();){
            String sql = "insert into orders values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,id);
            ps.setString(2, menuItem);
            ps.setString(3, comment);
            ps.setInt(4, isFavorite);
            ps.setString(5, orderDate);
            ps.setString(6, customerUsername);

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0){
                throw new RuntimeException();
            }

            followUpCreateCustomOrder(id, menuItem,comment,isFavorite, orderDate, customerUsername);

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public OrderModel followUpCreateCustomOrder(int id, String menuItem, String comment, int isFavorite, String orderDate, String customerUsername) {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        try {
            String sql2 = "select * from orders where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            OrderModel newCustomOrder = new OrderModel();

            newCustomOrder.setId(rs.getInt("id"));
            newCustomOrder.setMenuItem(rs.getString("menu_item"));
            newCustomOrder.setComment(rs.getString("comment"));
            newCustomOrder.setIsFavorite(rs.getInt("is_favorite"));
            newCustomOrder.setOrderDate(rs.getString("order_date"));
            newCustomOrder.setCustomerUsername(rs.getString("customer_username"));

            return newCustomOrder;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    // MVP - View past orders by date
    public OrderModel[] viewAllByDate(String theDate){
        Connection conn = ConnectionFactory.getInstance().getConnection();

        OrderModel[] orders = new OrderModel[20];

        int index = 0;

        try{
            String sql = "select * from orders where order_date = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, theDate);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                OrderModel modelOrder = new OrderModel();

                modelOrder.setId(rs.getInt("id"));
                modelOrder.setMenuItem(rs.getString("menu_item"));
                modelOrder.setComment(rs.getString("comment"));
                modelOrder.setIsFavorite(rs.getInt("is_favorite"));
                modelOrder.setOrderDate(rs.getString("order_date"));
                modelOrder.setCustomerUsername(rs.getString("customer_username"));

                orders[index] = modelOrder;
                index++;

            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return orders;
    }







    public OrderModel[] viewAllOrders(){
        Connection conn = ConnectionFactory.getInstance().getConnection();

        OrderModel[] orders = new OrderModel[20];

        int index = 0;

        try{
            String sql = "select * from orders";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                OrderModel modelOrder = new OrderModel();

                modelOrder.setId(rs.getInt("id"));
                modelOrder.setMenuItem(rs.getString("menu_item"));
                modelOrder.setComment(rs.getString("comment"));
                modelOrder.setIsFavorite(rs.getInt("is_favorite"));
                modelOrder.setOrderDate(rs.getString("order_date"));
                modelOrder.setCustomerUsername(rs.getString("customer_username"));

                orders[index] = modelOrder;
                index++;

            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return orders;
    }


}
