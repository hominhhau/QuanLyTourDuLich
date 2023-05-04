/**
 * Nhóm 10_QUẢN LÝ THÔNG TIN DU LỊCH
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
	public NhanVien_DAO() {

	}

	public ArrayList<NhanVien> getalltbNhanVien() {
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select *from NhanVien";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String sdt = rs.getString(3);
				String email = rs.getString(4);
				String matKhau = rs.getString(5);

				NhanVien nv = new NhanVien(maNV, tenNV, sdt, email, matKhau);
				dsnv.add(nv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}

	public ArrayList<NhanVien> getNhanVienTheoMaNV(String id) {
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;

		try {
			String sql = "Select *from NhanVien where maNV = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);

			// Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String sdt = rs.getString(3);
				String email = rs.getString(4);
				String matKhau = rs.getString(5);

				NhanVien nv = new NhanVien(maNV, tenNV, sdt, email, matKhau);
				dsnv.add(nv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsnv;
	}

	/**
	 * Thêm nhân viên
	 */
	public boolean createNV(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("insert into" + "NhanVien values(?, ?, ?, ?, ?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setString(3, nv.getSDT());
			stmt.setString(4, nv.getEmail());
			stmt.setString(5, nv.getMatKhau());

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

	/**
	 * Cập nhật nhân viên
	 */
	public boolean updateNV(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("update NhanVien set tenNV= ?, SDT= ?, email= ?, matKhau= ?" + "where maNV= ?");
			stmt.setString(1, nv.getTenNV());
			stmt.setString(2, nv.getSDT());
			stmt.setString(3, nv.getEmail());
			stmt.setString(4, nv.getMatKhau());
			stmt.setString(5, nv.getMaNV());
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
