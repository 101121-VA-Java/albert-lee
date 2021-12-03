package com.revature.daos;

import com.revature.models.Requirement;
import java.io.IOException;
import java.sql.*;

import com.revature.util.ConnectionUtil;
import com.revature.util.LogUtil;
public class RequirementDao {

    public int update(Requirement r) {
        int result = 0;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "UPDATE requirements SET req_status = ? WHERE req_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, r.getStatus());
            ps.setInt(2, r.getId());
			result = ps.executeUpdate();
			if (result > 0) return result;
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in RequirementDao");
			e.printStackTrace();
		}
		return result;
    }
    
    public Requirement getById(int id){
        Requirement r = null;
		try (Connection con = ConnectionUtil.getConnection()){
			String sql = "select * from requirements where req_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r = new Requirement(
					rs.getInt("req_id"),
					rs.getString("req_description"),
					rs.getInt("req_status"));
			}
		} catch (SQLException | IOException e) {
			LogUtil.descriptiveError("SQL or IO Exception in RequirementDao");
			e.printStackTrace();
		}
		return r;	
    }
}
