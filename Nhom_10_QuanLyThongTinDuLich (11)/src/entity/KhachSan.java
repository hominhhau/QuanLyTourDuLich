/**
 * Nhóm 10_QUẢN LÝ THÔNG TIN DU LỊCH
 */
package entity;

public class KhachSan {
	private String maKS;
	private String tenKS;
	private String diaChi;
	private String SDT;

	public KhachSan(String maKS, String tenKS, String diaChi, String SDT) {
		setDiaChi(diaChi);
		setMaKS(maKS);
		setTenKS(tenKS);
		setSDT(SDT);
	}

	

	public KhachSan() {
		super();
	}



	public KhachSan(String maKS) {
		super();
		this.maKS = maKS;
	}

	public String getMaKS() {
		return maKS;
	}

	public void setMaKS(String maKS) {
		if (!maKS.trim().equals(""))
			this.maKS = maKS;
		else
			this.maKS = "un-know";
	}

	public String getTenKS() {
		return tenKS;
	}

	public void setTenKS(String tenKS) {
		if (!tenKS.trim().equals(""))
			this.tenKS = tenKS;
		else
			this.tenKS = "un-know";
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		if (!diaChi.trim().equals(""))
			this.diaChi = diaChi;
		else
			this.diaChi = "un-know";
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String SDT) {
		if (!SDT.trim().equals(""))
			this.SDT = SDT;
		else
			this.SDT = "un-know";
	}

	@Override
	public String toString() {
		return "KhachSan [maKS=" + maKS + ", tenKS=" + tenKS + ", diaChi=" + diaChi + ", SDT=" + SDT + "]";
	}

}
