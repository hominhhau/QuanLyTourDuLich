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
import entity.KhachHang;

public class KhachHang_DAO {
	public KhachHang_DAO() {
		
	}
	public ArrayList<KhachHang> getalltbKhachHang(){
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			ConnectDB db = ConnectDB.getInstance();
	        db.connect(); // establish database connection
	        @SuppressWarnings("static-access")
			Connection con = db.getConnection(); 
	        String sql = "Select *from KhachHang ";
	        Statement statement = con.createStatement();
			
			
			//Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			//Duyệt trên kết quả trả về
			//maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh
			while(rs.next()) {//Di chuyển con trỏ xuống bản ghi kế tiếp
				
//				String maKH = rs.getString("maKH");
//				String tenKH = rs.getString("tenKH");
//				String sdt = rs.getString("SDT");
//				String email = rs.getString("email");
//				int CCCD = rs.getInt("CCCD");
//				boolean hoChieu = rs.getBoolean("hoChieu");
//				boolean gioiTinh = rs.getBoolean("gioiTinh");
				
				
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String sdt = rs.getString(3);
				String email = rs.getString(4);
				String CCCD = rs.getString(5);
				//int CCCD = rs.getInt(5);
				boolean hoChieu = rs.getBoolean(6);
				boolean gioiTinh = rs.getBoolean(7);
				
//				KhachHang kh = new KhachHang();
//				kh.setMaKH(rs.getString(1));
//				kh.setTenKH(rs.getString(2));
//				kh.setSDT(rs.getString(3));
//				kh.setEmail(rs.getString(4));
//				kh.setCCCD(rs.getInt(5));
//				kh.setHoChieu(rs.getBoolean(6));
//				kh.setGioiTinh(rs.getBoolean(7));
				
				
				KhachHang kh = new KhachHang(maKH, tenKH, sdt, email, CCCD, hoChieu, gioiTinh);
				dskh.add(kh);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}
	
	public ArrayList<KhachHang> getKhachHangTheoMaKH(String id){
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		
		
		try {
			String sql = "Select *from KhachHang where maKH= ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			
			//Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			
			//Duyệt trên kết quả trả về
			while(rs.next()) {//Di chuyển con trỏ xuống bản ghi kế tiếp

				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String sdt = rs.getString(3);
				String email = rs.getString(4);
				String CCCD = rs.getString(5);
				boolean hoChieu = rs.getBoolean(6);
				boolean gioiTinh = rs.getBoolean(7);
				
				KhachHang kh = new KhachHang(maKH, tenKH, sdt, email, CCCD, hoChieu, gioiTinh);
				dskh.add(kh);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return dskh;
	}
	
	
	
	public ArrayList<KhachHang> getKhachHangTheoSdtKH(String id){
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		
		
		try {
			String sql = "Select *from KhachHang where SDT= ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			
			//Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			
			//Duyệt trên kết quả trả về
			while(rs.next()) {//Di chuyển con trỏ xuống bản ghi kế tiếp

				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String sdt = rs.getString(3);
				String email = rs.getString(4);
				String CCCD = rs.getString(5);
				boolean hoChieu = rs.getBoolean(6);
				boolean gioiTinh = rs.getBoolean(7);
				
				KhachHang kh = new KhachHang(maKH, tenKH, sdt, email, CCCD, hoChieu, gioiTinh);
				dskh.add(kh);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return dskh;
	}
	
	
	/**
	 * Thêm khách hàng
	 */
	public boolean createKH(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into " + 
		"KhachHang(maKH,tenKH,SDT,email,CCCD,hoChieu,gioiTinh) values (?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getTenKH());
			stmt.setString(3, kh.getSDT());
			stmt.setString(4, kh.getEmail());
			stmt.setString(5, kh.getCCCD());
			stmt.setBoolean(6, kh.isHoChieu());
			stmt.setBoolean(7, kh.isGioiTinh());	
			n = stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
	
	/**
	 * Xóa khách hàng
	 */
	public boolean xoaKH(String maKH) {
		@SuppressWarnings("static-access")
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("delete KhachHang from KhachHang where maKH= ?");
			stmt.setString(1, maKH);
			n = stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	/**
	 * Tìm khách hàng
	 */
	public KhachHang timKiem(String maTim) {
		KhachHang kh = null;
		@SuppressWarnings("static-access")
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "Select *from KhachHang where maKH= ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maTim);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String sdt = rs.getString(3);
				String email = rs.getString(4);
				String CCCD = rs.getString(5);
				boolean hoChieu = rs.getBoolean(6);
				boolean gioiTinh = rs.getBoolean(7);
				
				kh = new KhachHang(maKH, tenKH, sdt, email, CCCD, hoChieu, gioiTinh);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return kh;
	}
	
//	sửa khách hàng
	
	public boolean update(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			stmt = con.prepareStatement("update " + 
		"KhachHang set tenKH=?,SDT=?,email=?,CCCD=?,hoChieu=?,gioiTinh=? where maKH=?");
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getSDT());
			stmt.setString(3, kh.getEmail());
			stmt.setString(4, kh.getCCCD());
			stmt.setBoolean(5, kh.isHoChieu());
			stmt.setBoolean(6, kh.isGioiTinh());
			stmt.setString(7, kh.getMaKH());
			n = stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
}
