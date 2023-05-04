package entity;

public class thongTinDatTour {
	private String maVe;
	private KhachHang thongTinKhachhang;
	private Tour thongTinTour;
	private int soLuong;
	private NhanVien thongTinNV;

	public thongTinDatTour() {
		super();
	}

	public thongTinDatTour(String maVe) {
		super();
		this.maVe = maVe;
	}

	public thongTinDatTour(String maVe, KhachHang thongTinKhachhang, Tour thongTinTour, int soLuong, NhanVien thongTinNV) {
		super();
		this.maVe = maVe;
		this.thongTinKhachhang = thongTinKhachhang;
		this.thongTinTour = thongTinTour;
		this.soLuong = soLuong;
		this.thongTinNV = thongTinNV;
	}

	public String getMaVe() {
		return maVe;
	}

	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}

	public KhachHang getThongTinKhachhang() {
		return thongTinKhachhang;
	}

	public void setThongTinKhachhang(KhachHang thongTinKhachhang) {
		this.thongTinKhachhang = thongTinKhachhang;
	}

	public Tour getThongTinTour() {
		return thongTinTour;
	}

	public void setThongTinTour(Tour thongTinTour) {
		this.thongTinTour = thongTinTour;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public NhanVien getThongTinNV() {
		return thongTinNV;
	}

	public void setThongTinNV(NhanVien thongTinNV) {
		this.thongTinNV = thongTinNV;
	}

}