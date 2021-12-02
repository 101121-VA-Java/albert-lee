package com.revature.daos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionUtil;

public class ReceiptDao {
    public InputStream getById(int id){
        InputStream result = null;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "select * from ers_reimbursement where reimb_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getBinaryStream("reimb_receipt");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
        return result;
    }
}
