/**
 * Nhóm 10_QUẢN LÝ THÔNG TIN DU LỊCH
 */
package entity;

public class QuanLy {
	private Tour quanLyTour;
	private KhachHang quanLyKH;
	private NhanVien quanLyNV;
	
	public QuanLy(Tour quanLyTour, KhachHang quanLyKH, NhanVien quanLyNV) {
		this.quanLyTour = quanLyTour;
		this.quanLyKH = quanLyKH;
		this.quanLyNV = quanLyNV;
	}

	public Tour getQuanLyTour() {
		return quanLyTour;
	}

	public void setQuanLyTour(Tour quanLyTour) {
		this.quanLyTour = quanLyTour;
	}

	public KhachHang getQuanLyKH() {
		return quanLyKH;
	}

	public void setQuanLyKH(KhachHang quanLyKH) {
		this.quanLyKH = quanLyKH;
	}

	public NhanVien getQuanLyNV() {
		return quanLyNV;
	}

	public void setQuanLyNV(NhanVien quanLyNV) {
		this.quanLyNV = quanLyNV;
	}

	@Override
	public String toString() {
		return "QuanLy [quanLyTour=" + quanLyTour + ", quanLyKH=" + quanLyKH + ", quanLyNV=" + quanLyNV + "]";
	}
	
	/**
	 * Đổi mật khẩu
	 */
	
	//còn hàm đó viết sau
	
	

}
