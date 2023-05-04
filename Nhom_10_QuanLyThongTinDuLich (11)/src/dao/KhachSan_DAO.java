package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachSan;

public class KhachSan_DAO {
	public KhachSan_DAO() {

	}

	public ArrayList<KhachSan> getalltbKhachSan() {
		ArrayList<KhachSan> dsks = new ArrayList<KhachSan>();
		try {
			 ConnectDB.getInstance();
	            Connection con = ConnectDB.getConnection();
	            String sql = "SELECT * FROM KhachSan";
	            Statement statement = con.createStatement();
	            ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKS = rs.getString(1);
				String tenKS = rs.getString(2);
				String diaChi = rs.getString(3);
				String SDT = rs.getString(4);
				KhachSan ks = new KhachSan(maKS, tenKS, diaChi, SDT);
				dsks.add(ks);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsks;
	}

//	public boolean create(KhachSan ks) {
//	    ConnectDB.getInstance();
//	    Connection con = ConnectDB.getConnection();
//	    PreparedStatement stmt = null;
//	    int n = 0;
//	    try {
//	        stmt = con.prepareStatement("INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT) VALUES (?, ?, ?, ?)");
//	        stmt.setString(1, ks.getMaKS());
//	        stmt.setString(2, ks.getTenKS());
//	        stmt.setString(3, ks.getDiaChi());
//	        stmt.setString(4, ks.getSDT());
//	        n = stmt.executeUpdate();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } finally {
//	        try {
//	            stmt.close();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    return n > 0;
//	}
	public boolean create(KhachSan ks) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT) VALUES (?, ?, ?, ?)");
			stmt.setString(1, ks.getMaKS());
			stmt.setString(2, ks.getTenKS());
			stmt.setString(3, ks.getDiaChi());
			stmt.setString(4, ks.getSDT());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
		return n > 0;
	}

	public boolean delete(String maKS) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM KhachSan WHERE maKS = ?");
			stmt.setString(1, maKS);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean update(KhachSan ks) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("UPDATE KhachSan SET tenKS = ?, diaChi = ?, SDT = ? WHERE maKS = ?");
			stmt.setString(1, ks.getTenKS());
			stmt.setString(2, ks.getDiaChi());
			stmt.setString(3, ks.getSDT());
			stmt.setString(4, ks.getMaKS());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

}
