package com.revature.daos;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDao implements GenericDao<Reimbursement> {

    @Override
    public int add(Reimbursement r) {
// insert into ers_reimbursement 
// (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_type_id)
// values
// (100, current_timestamp, 'lol', 3, 3);
    int result = -1;
		
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "insert into ers_reimbursement" +
            "(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_type_id)" + 
			"values (?,?,?,?,?) returning reimb_id;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, r.getAmount());
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setString(3, r.getDescription());
			ps.setInt(4, r.getAuthorId());
			ps.setInt(5, r.getTypeId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("reimb_id");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return result;
    }

    @Override
    public List<Reimbursement> getAll() {
        return null;
    }

    @Override
    public int update(Reimbursement t) {
        return 0;
    }

    @Override
    public void delete(int id) {
        
    }
    
}
