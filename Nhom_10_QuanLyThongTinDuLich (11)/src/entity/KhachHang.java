/**
 * Nhóm 10_QUẢN LÝ THÔNG TIN DU LỊCH
 */
package entity;

public class KhachHang {
	public KhachHang() {
		super();
	}

	private String maKH;
	private String tenKH;
	private String SDT;
	private String email;
	private String CCCD;
	private boolean hoChieu;
	private boolean gioiTinh;

	public KhachHang(String maKH, String tenKH, String sDT, String email, String cCCD, boolean hoChieu,
			boolean gioiTinh) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.SDT = sDT;
		this.email = email;
		this.CCCD = cCCD;
		this.hoChieu = hoChieu;
		this.gioiTinh = gioiTinh;
	}

	public KhachHang(String maKH) {
		this.maKH = maKH;
	}

	public KhachHang(String maKH, String tenKH) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
	}

	public String getMaKH() {
		return maKH;
	}

	/**
	 * Không được rỗng
	 * 
	 * @param maKH
	 */
	public void setMaKH(String maKH) {
		if (!maKH.trim().equals(""))
			this.maKH = maKH;
		else
			this.maKH = "un-know";
	}

	public String getTenKH() {
		return tenKH;
	}

	/**
	 * Không được rỗng
	 * 
	 * @param tenKH
	 */
	public void setTenKH(String tenKH) {
		if (!tenKH.trim().equals(""))
			this.tenKH = tenKH;
		else
			this.tenKH = "un-know";
	}

	public String getSDT() {
		return SDT;
	}

	/**
	 * Không được rỗng
	 * 
	 * @param sDT
	 */
	public void setSDT(String sDT) {
		this.SDT = sDT;
	}

	public String getEmail() {
		return email;
	}

	/**
	 * Không được rỗng
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		if (!email.trim().equals(""))
			this.email = email;
		else
			this.email = "un-know";
	}

	public String getCCCD() {
		return CCCD;
	}

	/**
	 * Không là số âm
	 * 
	 * @param cCCD
	 */

	public boolean isHoChieu() {
		return hoChieu;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public void setHoChieu(boolean hoChieu) {
		this.hoChieu = hoChieu;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", SDT=" + SDT + ", email=" + email + ", CCCD=" + CCCD
				+ ", hoChieu=" + hoChieu + ", gioiTinh=" + gioiTinh + "]";
	}

}
