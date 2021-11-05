package com.revature.repositories;

import com.revature.models.Payment;
import com.revature.models.Payment;
import com.revature.utils.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentPostgres implements GenericDao<Payment>{
    @Override
    public int add(Payment payment) {
        int resultId = -1;
        String sql = "insert into payments (payee_id, item_id, amount) "
                + "values (?, ?, ?) returning payment_id;";
        try(Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, payment.getPayeeId());
            ps.setInt(2, payment.getItemId());
            ps.setInt(3, payment.getAmount());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                resultId = rs.getInt("payment_id");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return resultId;
    }

    @Override
    public List<Payment> getAll() {
        String sql = "select * from payments;";
        List<Payment> payments = new ArrayList<>();

        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                int paymentId = rs.getInt("payment_id");
                int payeeId = rs.getInt("payee_id");
                int itemId = rs.getInt("item_id");
                int amount = rs.getInt("amount");
                Payment newPayment = new Payment(paymentId, payeeId, itemId, amount);
                payments.add(newPayment);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public Payment getById(int id) {
        return null;
    }

    @Override
    public int update(Payment payment) {
        return 0;
    }

    @Override
    public void delete(int itemId) {
        String sql = "delete from payments where item_id = ?";
        try (Connection con = ConnectionUtil.getConnectionFromFile()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
