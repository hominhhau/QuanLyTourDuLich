/**
 * Nhóm 10_QUẢN LÝ THÔNG TIN DU LỊCH
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachSan;
import entity.LoTrinh;
import entity.PhuongTien;
import entity.Tour;

public class Tour_DAO {

	public Tour_DAO() {

	}

	public List<Tour> getAllTours() throws SQLException {
		List<Tour> tours = new ArrayList<Tour>();
		ConnectDB db = ConnectDB.getInstance();
		db.connect();
		@SuppressWarnings("static-access")
		Connection con = db.getConnection();
		String sql = "SELECT * FROM Tour \r\n" + "INNER JOIN LoTrinh ON Tour.thongTinLoTrinh = LoTrinh.MaLoTrinh \r\n"
				+ "INNER JOIN KhachSan ON Tour.thongTinKhachSan = KhachSan.MaKS \r\n"
				+ "INNER JOIN PhuongTien ON Tour.thongTinPhuongTien = PhuongTien.MaPhuongTien";

		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Tour tour = new Tour();
			tour.setMaTour(rs.getString(1));
			tour.setTenTour(rs.getString(2));
			tour.setGiaTour(rs.getDouble(3));
			LoTrinh loTrinh = new LoTrinh();
			loTrinh.setMaLoTrinh(rs.getString(7));
			loTrinh.setTenLoTrinh(rs.getString(8));
			loTrinh.setNgayKhoiHanh(rs.getDate(9).toLocalDate());
			loTrinh.setNgayKetThuc(rs.getDate(10).toLocalDate());
			loTrinh.setLoaiTour(rs.getBoolean(11));
			KhachSan khachSan = new KhachSan();
			khachSan.setMaKS(rs.getString(12));
			khachSan.setTenKS(rs.getString(13));
			khachSan.setDiaChi(rs.getString(14));
			khachSan.setSDT(rs.getString(15));
			tour.setThongTinKhachSan(khachSan);
			PhuongTien phuongTien = new PhuongTien();
			phuongTien.setMaPhuongTien(rs.getString(16));
			phuongTien.setLoaiPhuongTien(rs.getString(17));
			phuongTien.setTenTaiXe(rs.getString(18));
			phuongTien.setSDT(rs.getString(19));
			tour.setThongTinPhuongTien(phuongTien);
			tour.setThongTinKhachSan(khachSan);
			tour.setThongTinLoTrinh(loTrinh);
			tours.add(tour);
		}
		return tours;
	}

	public Tour getTourByMaTour(String maTour) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM tour INNER JOIN lo_trinh ON tour.maLoTrinh = lo_trinh.maLoTrinh INNER JOIN khach_san ON tour.maKS = khach_san.maKS INNER JOIN phuong_tien ON tour.maPhuongTien = phuong_tien.maPhuongTien WHERE tour.maTour = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maTour);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			Tour tour = new Tour();
			tour.setMaTour(rs.getString("maTour"));
			tour.setTenTour(rs.getString("tenTour"));
			tour.setGiaTour(rs.getDouble("giaTour"));
			LoTrinh loTrinh = new LoTrinh();
			loTrinh.setMaLoTrinh(rs.getString("lo_trinh.maLoTrinh"));
			loTrinh.setTenLoTrinh(rs.getString("lo_trinh.tenLoTrinh"));
			loTrinh.setNgayKhoiHanh(rs.getDate("lo_trinh.ngayKhoiHanh").toLocalDate());
			loTrinh.setNgayKetThuc(rs.getDate("lo_trinh.ngayKetThuc").toLocalDate());
			loTrinh.setLoaiTour(rs.getBoolean("lo_trinh.loaiTour"));
			tour.setThongTinLoTrinh(loTrinh);
			KhachSan khachSan = new KhachSan();
			khachSan.setMaKS(rs.getString("khach_san.maKS"));
			khachSan.setTenKS(rs.getString("khach_san.tenKS"));
			khachSan.setDiaChi(rs.getString("khach_san.diaChi"));
			khachSan.setSDT(rs.getString("khach_san.SDT"));
			tour.setThongTinKhachSan(khachSan);
			PhuongTien phuongTien = new PhuongTien();
			phuongTien.setMaPhuongTien(rs.getString("phuong_tien.maPhuongTien"));
			phuongTien.setLoaiPhuongTien(rs.getString("phuong_tien.loaiPhuongTien"));
			phuongTien.setTenTaiXe(rs.getString("phuong_tien.tenTaiXe"));
			phuongTien.setSDT(rs.getString("phuong_tien.SDT"));
			tour.setThongTinPhuongTien(phuongTien);
			return tour;
		} else {
			return null;
		}
	}

	public void addTour(Tour tour) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "INSERT INTO tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, tour.getMaTour());
		stmt.setString(2, tour.getTenTour());
		stmt.setDouble(3, tour.getGiaTour());
		stmt.setString(4, tour.getThongTinKhachSan().getMaKS());
		stmt.setString(5, tour.getThongTinPhuongTien().getMaPhuongTien());
		stmt.setString(6, tour.getThongTinLoTrinh().getMaLoTrinh());
		stmt.executeUpdate();
	}

	public void updateTour(Tour tour) throws SQLException {
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();

	    // Cập nhật thông tin KhachSan
	    String ksSql = "UPDATE KhachSan SET tenKS = ?, diaChi = ?, SDT = ? WHERE maKS = ?";
	    PreparedStatement ksStmt = con.prepareStatement(ksSql);
	    ksStmt.setString(1, tour.getThongTinKhachSan().getTenKS());
	    ksStmt.setString(2, tour.getThongTinKhachSan().getDiaChi());
	    ksStmt.setString(3, tour.getThongTinKhachSan().getSDT());
	    ksStmt.setString(4, tour.getThongTinKhachSan().getMaKS());
	    ksStmt.executeUpdate();

	    // Cập nhật thông tin LoTrinh
	    String ltSql = "UPDATE LoTrinh SET tenLoTrinh = ?, ngayKhoiHanh = ?, ngayKetThuc = ?, loaiTour = ? WHERE maLoTrinh = ?";
	    PreparedStatement ltStmt = con.prepareStatement(ltSql);
	    ltStmt.setString(1, tour.getThongTinLoTrinh().getTenLoTrinh());
	    ltStmt.setDate(2, Date.valueOf(tour.getThongTinLoTrinh().getNgayKhoiHanh()));
	    ltStmt.setDate(3, Date.valueOf(tour.getThongTinLoTrinh().getNgayKetThuc()));
	    ltStmt.setBoolean(4, tour.getThongTinLoTrinh().isLoaiTour());
	    ltStmt.setString(5, tour.getThongTinLoTrinh().getMaLoTrinh());
	    ltStmt.executeUpdate();

	    // Cập nhật thông tin PhuongTien
	    String ptSql = "UPDATE PhuongTien SET loaiPhuongTien = ?, tenTaiXe = ?, SDT = ? WHERE maPhuongTien = ?";
	    PreparedStatement ptStmt = con.prepareStatement(ptSql);
	    ptStmt.setString(1, tour.getThongTinPhuongTien().getLoaiPhuongTien());
	    ptStmt.setString(2, tour.getThongTinPhuongTien().getTenTaiXe());
	    ptStmt.setString(3, tour.getThongTinPhuongTien().getSDT());
	    ptStmt.setString(4, tour.getThongTinPhuongTien().getMaPhuongTien());
	    ptStmt.executeUpdate();
	    
	 // Cập nhật thông tin Tour
	    String tourSql = "UPDATE Tour SET tenTour = ?, giaTour = ?, thongTinKhachSan = ?, thongTinPhuongTien = ?, thongTinLoTrinh = ? WHERE maTour = ?";
	    PreparedStatement tourStmt = con.prepareStatement(tourSql);
	    tourStmt.setString(1, tour.getTenTour());
	    tourStmt.setDouble(2, tour.getGiaTour());
	    tourStmt.setString(3, tour.getThongTinKhachSan().getMaKS());
	    tourStmt.setString(4, tour.getThongTinPhuongTien().getMaPhuongTien());
	    tourStmt.setString(5, tour.getThongTinLoTrinh().getMaLoTrinh());
	    tourStmt.setString(6, tour.getMaTour());
	    tourStmt.executeUpdate();

	
	}



//	public void updateTour(Tour tour) throws SQLException {
//	    ConnectDB.getInstance();
//	    Connection con = ConnectDB.getConnection();
//		String sql = "UPDATE tour SET tenTour = ?, giaTour = ?, maLoTrinh = ?, maKS = ?, maPhuongTien = ? WHERE maTour = ?";
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setString(1, tour.getTenTour());
//		stmt.setDouble(2, tour.getGiaTour());
//		stmt.setString(3, tour.getThongTinLoTrinh().getMaLoTrinh());
//		stmt.setString(4, tour.getThongTinKhachSan().getMaKS());
//		stmt.setString(5, tour.getThongTinPhuongTien().getMaPhuongTien());
//		stmt.setString(6, tour.getMaTour());
//		stmt.executeUpdate();
//	}

	public void deleteTour(String maTour) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "DELETE FROM tour WHERE maTour = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maTour);
		stmt.executeUpdate();
	}
}
