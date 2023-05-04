package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.KhachSan;
import entity.LoTrinh;
import entity.NhanVien;
import entity.PhuongTien;
import entity.Tour;
import entity.thongTinDatTour;

public class ThongTinDatTour_DAO {

	public ThongTinDatTour_DAO() {

	}

	public List<thongTinDatTour> getAllVe() {
		List<thongTinDatTour> ves = new ArrayList<>();
		ConnectDB db = ConnectDB.getInstance();
		@SuppressWarnings("static-access")
		Connection con = db.getConnection();
		try {
			String sql = "SELECT * FROM ThongTinDatTour " + "INNER JOIN KhachHang ON ThongTinDatTour.thongTinKH = KhachHang.maKH "
					+ "INNER JOIN Tour ON ThongTinDatTour.thongTinTour = Tour.maTour "
					+ "INNER JOIN LoTrinh ON Tour.thongTinLoTrinh = LoTrinh.maLoTrinh "
					+ "INNER JOIN KhachSan ON Tour.thongTinKhachSan = KhachSan.maKS "
					+ "INNER JOIN PhuongTien ON Tour.thongTinPhuongTien = PhuongTien.maPhuongTien "
					+ "INNER JOIN NhanVien ON ThongTinDatTour.thongTinNV = NhanVien.maNV ";

			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				thongTinDatTour ve = new thongTinDatTour();
				ve.setMaVe(rs.getString("maVe"));

				KhachHang khachHang = new KhachHang();
				khachHang.setMaKH(rs.getString("maKH"));
				khachHang.setTenKH(rs.getString("tenKH"));
				khachHang.setSDT(rs.getString("SDT"));
				khachHang.setEmail(rs.getString("email"));
				khachHang.setCCCD(rs.getString("CCCD"));
				khachHang.setHoChieu(rs.getBoolean("hoChieu"));
				khachHang.setGioiTinh(rs.getBoolean("gioiTinh"));
				ve.setThongTinKhachhang(khachHang);

				Tour tour = new Tour();
				tour.setMaTour(rs.getString("maTour"));
				tour.setTenTour(rs.getString("tenTour"));
				tour.setGiaTour(rs.getDouble("giaTour"));

				LoTrinh loTrinh = new LoTrinh();
				loTrinh.setMaLoTrinh(rs.getString("maLoTrinh"));
				loTrinh.setTenLoTrinh(rs.getString("tenLoTrinh"));
				loTrinh.setNgayKhoiHanh(rs.getDate("ngayKhoiHanh").toLocalDate());
				loTrinh.setNgayKetThuc(rs.getDate("ngayKetThuc").toLocalDate());
				loTrinh.setLoaiTour(rs.getBoolean("loaiTour"));
				tour.setThongTinLoTrinh(loTrinh);

				KhachSan khachSan = new KhachSan();
				khachSan.setMaKS(rs.getString("maKS"));
				khachSan.setTenKS(rs.getString("tenKS"));
				khachSan.setDiaChi(rs.getString("diaChi"));
				khachSan.setSDT(rs.getString("SDT"));
				tour.setThongTinKhachSan(khachSan);

				PhuongTien phuongTien = new PhuongTien();
				phuongTien.setMaPhuongTien(rs.getString("maPhuongTien"));
				phuongTien.setLoaiPhuongTien(rs.getString("loaiPhuongTien"));
				phuongTien.setTenTaiXe(rs.getString("tenTaiXe"));
				phuongTien.setSDT(rs.getString("SDT"));
				tour.setThongTinPhuongTien(phuongTien);

				ve.setThongTinTour(tour);
				ve.setSoLuong(rs.getInt("soLuong"));

				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getString("maNV"));
				nv.setTenNV(rs.getString("tenNV"));
				nv.setSDT(rs.getString("SDT"));
				nv.setEmail(rs.getString("email"));
				nv.setMatKhau(rs.getString("matKhau"));
				ve.setThongTinNV(nv);

				ves.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ves;
	}

	public thongTinDatTour findVe(String maVe) throws SQLException {
		thongTinDatTour ve = null;
		ConnectDB db = ConnectDB.getInstance();
		db.connect();
		@SuppressWarnings("static-access")
		Connection con = db.getConnection();
		String sql = "SELECT * FROM ThongTinDatTour " + "INNER JOIN KhachHang ON ThongTinDatTour.thongTinKH = KhachHang.maKH "
				+ "INNER JOIN Tour ON ThongTinDatTour.thongTinTour = Tour.maTour "
				+ "INNER JOIN LoTrinh ON Tour.thongTinLoTrinh = LoTrinh.maLoTrinh "
				+ "INNER JOIN KhachSan ON Tour.thongTinKhachSan = KhachSan.maKS "
				+ "INNER JOIN PhuongTien ON Tour.thongTinPhuongTien = PhuongTien.maPhuongTien "
				+ "INNER JOIN NhanVien ON ThongTinDatTour.thongTinNV = NhanVien.maNV "
				+ "WHERE ThongTinDatTour.maVe = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, maVe);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			ve = new thongTinDatTour();
			ve.setMaVe(rs.getString("maVe"));

			KhachHang khachHang = new KhachHang();
			khachHang.setMaKH(rs.getString("maKH"));
			khachHang.setTenKH(rs.getString("tenKH"));
			khachHang.setSDT(rs.getString("SDT"));
			khachHang.setEmail(rs.getString("email"));
			khachHang.setCCCD(rs.getString("CCCD"));
			khachHang.setHoChieu(rs.getBoolean("hoChieu"));
			khachHang.setGioiTinh(rs.getBoolean("gioiTinh"));
			ve.setThongTinKhachhang(khachHang);

			Tour tour = new Tour();
			tour.setMaTour(rs.getString("maTour"));
			tour.setTenTour(rs.getString("tenTour"));
			tour.setGiaTour(rs.getDouble("giaTour"));

			LoTrinh loTrinh = new LoTrinh();
			loTrinh.setMaLoTrinh(rs.getString("maLoTrinh"));
			loTrinh.setTenLoTrinh(rs.getString("tenLoTrinh"));
			loTrinh.setNgayKhoiHanh(rs.getDate("ngayKhoiHanh").toLocalDate());
			loTrinh.setNgayKetThuc(rs.getDate("ngayKetThuc").toLocalDate());
			loTrinh.setLoaiTour(rs.getBoolean("loaiTour"));
			tour.setThongTinLoTrinh(loTrinh);

			KhachSan khachSan = new KhachSan();
			khachSan.setMaKS(rs.getString("maKS"));
			khachSan.setTenKS(rs.getString("tenKS"));
			khachSan.setDiaChi(rs.getString("diaChi"));
			khachSan.setSDT(rs.getString("SDT"));
			tour.setThongTinKhachSan(khachSan);

			PhuongTien phuongTien = new PhuongTien();
			phuongTien.setMaPhuongTien(rs.getString("maPhuongTien"));
			phuongTien.setLoaiPhuongTien(rs.getString("loaiPhuongTien"));
			phuongTien.setTenTaiXe(rs.getString("tenTaiXe"));
			phuongTien.setSDT(rs.getString("SDT"));
			tour.setThongTinPhuongTien(phuongTien);

			ve.setThongTinTour(tour);
			ve.setSoLuong(rs.getInt("soLuong"));
			
			NhanVien nv = new NhanVien();
			nv.setMaNV(rs.getString("maNV"));
			nv.setTenNV(rs.getString("tenNV"));
			nv.setSDT(rs.getString("SDT"));
			nv.setEmail(rs.getString("email"));
			nv.setMatKhau(rs.getString("matKhau"));
			ve.setThongTinNV(nv);
			
		}

		return ve;
	}

	public boolean xoaVe(String maVe) {
		@SuppressWarnings("static-access")
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;

		try {
			stmt = con.prepareStatement("delete from ThongTinDatTour where maVe= ?");
			stmt.setString(1, maVe);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean addTour(thongTinDatTour ve) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV) VALUES (?, ?, ?, ?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		int n = 0;
		try {
			stmt.setString(1, ve.getMaVe());
			stmt.setString(2, ve.getThongTinKhachhang().getMaKH());
			stmt.setString(3, ve.getThongTinTour().getMaTour());
			stmt.setInt(4, ve.getSoLuong());
			stmt.setString(5, ve.getThongTinNV().getMaNV());

			n = stmt.executeUpdate();
		} catch (Exception e) {
		}
		return n > 0;
	}
}
