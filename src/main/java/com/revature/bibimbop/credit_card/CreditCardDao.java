package com.revature.bibimbop.credit_card;

import com.revature.bibimbop.credit_card.CreditCardModel;
import com.revature.bibimbop.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardDao {

    //    // MVP - add credit card
    public CreditCardModel addCC(String ccNumber, String ccName, Integer cvv, String expDate, Integer zip, double limits, String customerUsername) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection();){
            String sql = "insert into credit_card values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,ccNumber);
            ps.setString(2, ccName);
            ps.setInt(3, cvv);
            ps.setString(4, expDate);
            ps.setInt(5, zip);
            ps.setDouble(6, limits);
            ps.setString(7, customerUsername);

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0){
                throw new RuntimeException();
            }

            followUpAddCC(ccNumber);

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public CreditCardModel followUpAddCC(String ccNumber) {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        try {
            String sql2 = "select * from credit_card where cc_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setString(1, ccNumber);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            CreditCardModel newCreditCard = new CreditCardModel();

            newCreditCard.setCcNumber(rs.getString("cc_number"));
            newCreditCard.setCcName(rs.getString("cc_name"));
            newCreditCard.setCvv(rs.getInt("cvv"));
            newCreditCard.setExpDate(rs.getString("exp_date"));
            newCreditCard.setZip(rs.getInt("zip"));
            newCreditCard.setLimits(rs.getDouble("limits"));
            newCreditCard.setCustomerUsername(rs.getString("customer_username"));

            return newCreditCard;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public CreditCardModel updateCreditCard(String ccName, Integer cvv, String expDate, Integer zip, double limits, String customerUsername, String ccNumber) {
        Connection conn = ConnectionFactory.getInstance().getConnection();

        String sql = "update credit_card set cc_name = ?, cvv = ?, exp_date = ?, zip = ?, limits = ?, customer_username = ? where cc_number = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setString(1, ccName);
            ps.setInt(2, cvv);
            ps.setString(3, expDate);
            ps.setInt(4, zip);
            ps.setDouble(5, limits);
            ps.setString(6, customerUsername);
            ps.setString(7,ccNumber);

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException();
            }

            followUPUpdateCreditCard(ccNumber);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public CreditCardModel followUPUpdateCreditCard(String ccNumber){
        Connection conn = ConnectionFactory.getInstance().getConnection();

        try {
            String sql = "select * from credit_card where cc_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ccNumber);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            CreditCardModel updateCreditCard = new CreditCardModel();

            updateCreditCard.setCcNumber(rs.getString("cc_number"));
            updateCreditCard.setCcName(rs.getString("cc_name"));
            updateCreditCard.setCvv(rs.getInt("cvv"));
            updateCreditCard.setExpDate(rs.getString("exp_date"));
            updateCreditCard.setZip(rs.getInt("zip"));
            updateCreditCard.setLimits(rs.getDouble("limits"));
            updateCreditCard.setCustomerUsername(rs.getString("customer_username"));

            return updateCreditCard;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    //    // MVP - delete credit card
    public boolean deleteByCCNumber(String ccNumber) {
        Connection conn = ConnectionFactory.getInstance().getConnection();{
            String sql = "delete from credit_card where cc_number = ?";

            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, ccNumber);

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

    //    // MVP - update credit card

}