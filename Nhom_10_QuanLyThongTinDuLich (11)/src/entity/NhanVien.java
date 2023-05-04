/**
 * Nhóm 10_QUẢN LÝ THÔNG TIN DU LỊCH
 */
package entity;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String SDT;
	private String email;
	private String matKhau;

	public NhanVien(String maNV, String tenNV, String sDT, String email, String matKhau) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.SDT = sDT;
		this.email = email;
		this.matKhau = matKhau;
	}
	
	

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}



	public NhanVien(String maNV) {
		this.maNV = maNV;
	}

	public NhanVien(String maNV, String tenNV) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
	}

	public String getMaNV() {
		return maNV;
	}

	/**
	 * Không được rỗng
	 * 
	 * @param maNV
	 */
	public void setMaNV(String maNV) {
		if (!maNV.trim().equals(""))
			this.maNV = maNV;
		else
			this.maNV = "un-know";
	}

	public String getTenNV() {
		return tenNV;
	}

	/**
	 * Không được rỗng
	 * 
	 * @param tenNV
	 */
	public void setTenNV(String tenNV) {
		if (!tenNV.trim().equals(""))
			this.tenNV = tenNV;
		else
			this.tenNV = "un-know";
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
		if (!sDT.equals(""))
			SDT = sDT;
		else
			SDT = "1234567891";
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

	public String getMatKhau() {
		return matKhau;
	}

	/**
	 * Không được rỗng
	 * 
	 * @param matKhau
	 */
	public void setMatKhau(String matKhau) {
		if (!matKhau.trim().equals(""))
			this.matKhau = matKhau;
		else
			this.matKhau = "un-know";
	}



	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", SDT=" + SDT + ", email=" + email + ", matKhau="
				+ matKhau + "]";
	}





}
