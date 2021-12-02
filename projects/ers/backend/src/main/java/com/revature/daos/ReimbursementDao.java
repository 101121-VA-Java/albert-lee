package com.revature.daos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;
import com.revature.util.LogUtil;

public class ReimbursementDao implements GenericDao<Reimbursement> {

    @Override
    public int add(Reimbursement r) {
    	int result = -1;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "insert into ers_reimbursement" +
            "(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_type_id, reimb_receipt)" + 
			"values (?,?,?,?,?,?) returning reimb_id;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, r.getAmount());
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setString(3, r.getDescription());
			ps.setInt(4, r.getAuthorId());
			ps.setInt(5, r.getTypeId());
			ps.setBinaryStream(6, r.getImage());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("reimb_id");
			}
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in ReimbursementDao");
			e.printStackTrace();
		}
		return result;
    }

    @Override
    public List<Reimbursement> getAll() {
        List<Reimbursement> rmbs = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_reimbursement;";

			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				InputStream image = rs.getBinaryStream("reimb_receipt");
				Reimbursement r = null;
				if(image == null) {
					r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id"));
				} else {
					r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"),
						rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id"),
						true);
				}
				rmbs.add(r);
			}
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in ReimbursementDao");
			e.printStackTrace();
		}
		return rmbs;
    }

    @Override
    public int update(Reimbursement r) {
        int result = 0;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "UPDATE ers_reimbursement SET reimb_resolved = ?, reimb_resolver= ?, reimb_status_id = ? WHERE reimb_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, r.getResolverId());
            ps.setInt(3, r.getStatusId());
            ps.setInt(4, r.getId());
			result = ps.executeUpdate();
			if (result > 0) return result;
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in ReimbursementDao");
			e.printStackTrace();
		}
		return result;
    }

    @Override
    public void delete(int id) {
        
    }
    
	public List<Reimbursement> getByEmployeeId(int id) {
        List<Reimbursement> rmbs = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "select * from ers_reimbursement where reimb_author = ?";
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Reimbursement r = new Reimbursement(
					rs.getInt("reimb_id"),
					rs.getInt("reimb_amount"),
					rs.getTimestamp("reimb_submitted"),
					rs.getTimestamp("reimb_resolved"),
					rs.getString("reimb_description"),
					rs.getInt("reimb_author"),
					rs.getInt("reimb_resolver"),
					rs.getInt("reimb_status_id"),
					rs.getInt("reimb_type_id"));
				rmbs.add(r);
			}
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in ReimbursementDao");
			e.printStackTrace();
		}
		return rmbs;
    }

	public Reimbursement getById(int id){
		Reimbursement r = null;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "select * from ers_reimbursement where reimb_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r = new Reimbursement(
					rs.getInt("reimb_id"),
					rs.getInt("reimb_amount"),
					rs.getTimestamp("reimb_submitted"),
					rs.getTimestamp("reimb_resolved"),
					rs.getString("reimb_description"),
					rs.getInt("reimb_author"),
					rs.getInt("reimb_resolver"),
					rs.getInt("reimb_status_id"),
					rs.getInt("reimb_type_id"));
			}
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in ReimbursementDao");
			e.printStackTrace();
		}
		return r;	
	}
}
