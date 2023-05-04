package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.LoTrinh;

public class LoTrinh_DAO {
	public LoTrinh_DAO() {

	}

	public ArrayList<LoTrinh> getAllLoTrinh() {
		ArrayList<LoTrinh> dsloTrinh = new ArrayList<LoTrinh>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM LoTrinh";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLoTrinh = rs.getString(1);
				String tenLoTrinh = rs.getString(2);
				LocalDate ngayKhoiHanh = rs.getDate(3).toLocalDate();
				LocalDate ngayKetThuc = rs.getDate(4).toLocalDate();
				boolean loaiTour = rs.getBoolean(5);
				LoTrinh loTrinh = new LoTrinh(maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour);
				dsloTrinh.add(loTrinh);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsloTrinh;
	}

	public boolean create(LoTrinh loTrinh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour) VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1, loTrinh.getMaLoTrinh());
			stmt.setString(2, loTrinh.getTenLoTrinh());
			stmt.setDate(3, java.sql.Date.valueOf(loTrinh.getNgayKhoiHanh()));
			stmt.setDate(4, java.sql.Date.valueOf(loTrinh.getNgayKetThuc()));
			stmt.setBoolean(5, loTrinh.isLoaiTour());
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

	public boolean delete(String maLoTrinh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM LoTrinh WHERE maLoTrinh = ?");
			stmt.setString(1, maLoTrinh);
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

	public boolean update(LoTrinh loTrinh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"UPDATE LoTrinh SET tenLoTrinh = ?, ngayKhoiHanh = ?, ngayKetThuc = ?, lotrinh = ? WHERE maLoTrinh = ?");
			stmt.setString(1, loTrinh.getTenLoTrinh());
			stmt.setDate(2, java.sql.Date.valueOf(loTrinh.getNgayKhoiHanh()));
			stmt.setDate(3, java.sql.Date.valueOf(loTrinh.getNgayKetThuc()));
			stmt.setBoolean(4, loTrinh.isLoaiTour());
			stmt.setString(5, loTrinh.getMaLoTrinh());
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

	public LoTrinh getLoTrinhByID(String maLoTrinh) {
		LoTrinh loTrinh = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM LoTrinh WHERE maLoTrinh = ?");
			stmt.setString(1, maLoTrinh);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String tenLoTrinh = rs.getString(2);
				LocalDate ngayKhoiHanh = rs.getDate(3).toLocalDate();
				LocalDate ngayKetThuc = rs.getDate(4).toLocalDate();
				boolean loaiTour = rs.getBoolean(5);
				loTrinh = new LoTrinh(maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loTrinh;
	}
}