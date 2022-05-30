package com.revature.bibimbop.customer;

import com.revature.bibimbop.util.ConnectionFactory;

import java.io.IOException;
import java.sql.*;

public class CustomerDao {

    //    // MVP - Add items to the menu
    public CustomerModel createCustomer(String customerUsername, String fName, String lName, String password, double balance, Integer isAdmin) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "insert into customer values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, customerUsername);
            ps.setString(2, fName);
            ps.setString(3, lName);
            ps.setString(4, password);
            ps.setDouble(5, balance);
            ps.setInt(6, isAdmin);

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException();
            }

            followUpCreateCustomer(customerUsername);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public CustomerModel followUpCreateCustomer(String customerUsername) {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        try {
            String sql2 = "select * from customer where customer_username = ?";
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setString(1, customerUsername);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            CustomerModel newCustomerUsername = new CustomerModel();

            newCustomerUsername.setCustomerUsername(rs.getString("customer_username"));
            newCustomerUsername.setfName(rs.getString("fname"));
            newCustomerUsername.setlName(rs.getString("lname"));
            newCustomerUsername.setPassword(rs.getString("password"));
            newCustomerUsername.setBalance(rs.getDouble("balance"));
            newCustomerUsername.setIsAdmin(rs.getInt("is_admin"));

            return newCustomerUsername;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    //    // MVP - View all items on the menu without needing to Register or Login
    public CustomerModel[] findAllCustomers() throws IOException {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        CustomerModel[] customerUsernames = new CustomerModel[20];

        int index = 0;

        try {
            String sql = "select * from customer";
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {

                CustomerModel fillInCustomer = new CustomerModel();

                fillInCustomer.setCustomerUsername(rs.getString("customer_username"));
                fillInCustomer.setfName(rs.getString("fname"));
                fillInCustomer.setlName(rs.getString("lname"));
                fillInCustomer.setPassword(rs.getString("password"));
                fillInCustomer.setBalance(rs.getDouble("balance"));
                fillInCustomer.setIsAdmin(rs.getInt("is_admin"));

                customerUsernames[index] = fillInCustomer;
                index++;

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return customerUsernames;

    }

    //     MVP - Delete customer
    public boolean deleteByCustomer(String customerUsername) {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        {
            String sql = "delete from customer where customer_username = ?";

            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, customerUsername);

                int checkInsert = ps.executeUpdate();

                if (checkInsert == 0) {
                    throw new RuntimeException();
                }

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }

    }

    //    // MVP - Update customer
    public CustomerModel updateCustomer(String customerUsername, String fName, String lName, String password, double balance, Integer isAdmin) {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        String sql = "update customer set fname = ?, lname = ?, password = ? balance = ? is_admin = ? where customer = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, password);
            ps.setDouble(4, balance);
            ps.setInt(5, isAdmin);
            ps.setString(6, customerUsername);

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException();
            }

            followUPUpdateCustomer(customerUsername);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public CustomerModel followUPUpdateCustomer(String customerUsername) {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        try {
            String sql = "select * from customer where customer_username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customerUsername);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            CustomerModel updatedCustomer = new CustomerModel();

            updatedCustomer.setCustomerUsername(rs.getString("customer_username"));
            updatedCustomer.setfName(rs.getString("fname"));
            updatedCustomer.setlName(rs.getString("lname"));
            updatedCustomer.setPassword(rs.getString("password"));
            updatedCustomer.setBalance(rs.getDouble("balance"));
            updatedCustomer.setIsAdmin(rs.getInt("is_admin"));


            return updatedCustomer;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

