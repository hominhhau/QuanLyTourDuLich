/**
 * Nhóm 10_QUẢN LÝ THÔNG TIN DU LỊCH
 */
package entity;

public class PhieuDangKy {
	private KhachHang thongtinKH;
	private Tour thongTinTour;
	private int soNguoiThamGia;
	
	public PhieuDangKy(KhachHang thongtinKH, Tour thongTinTour, int soNguoiThamGia) {
		this.thongtinKH = thongtinKH;
		this.thongTinTour = thongTinTour;
		this.soNguoiThamGia = soNguoiThamGia;
	}

	public KhachHang getThongtinKH() {
		return thongtinKH;
	}

	public void setThongtinKH(KhachHang thongtinKH) {
		this.thongtinKH = thongtinKH;
	}

	public Tour getThongTinTour() {
		return thongTinTour;
	}

	public void setThongTinTour(Tour thongTinTour) {
		this.thongTinTour = thongTinTour;
	}

	public int getSoNguoiThamGia() {
		return soNguoiThamGia;
	}

	/**
	 * Phải lớn hơn 0
	 * @param soNguoiThamGia
	 */
	public void setSoNguoiThamGia(int soNguoiThamGia) {
		if(soNguoiThamGia > 0)
			this.soNguoiThamGia = soNguoiThamGia;
		else
			this.soNguoiThamGia = 0;
	}

	@Override
	public String toString() {
		return "PhieuDangKy [thongtinKH=" + thongtinKH + ", thongTinTour=" + thongTinTour + ", soNguoiThamGia="
				+ soNguoiThamGia + "]";
	}
	

}
