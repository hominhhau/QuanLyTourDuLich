package entity;

public class PhuongTien {
	private String maPhuongTien;
	private String loaiPhuongTien;
	private String tenTaiXe;
	private String SDT;

	public PhuongTien(String maPhuongTien, String loaiPhuongTien, String tenTaiXe, String SDT) {
		setLoaiPhuongTien(loaiPhuongTien);
		setMaPhuongTien(maPhuongTien);
		setSDT(SDT);
		setTenTaiXe(tenTaiXe);
	}
	

	public PhuongTien() {
		super();
	}


	public String getMaPhuongTien() {
		return maPhuongTien;
	}

	public void setMaPhuongTien(String maPhuongTien) {
		if (!maPhuongTien.trim().equals(""))
			this.maPhuongTien = maPhuongTien;
		else
			this.maPhuongTien = "un-know";
	}

	public String getLoaiPhuongTien() {
		return loaiPhuongTien;
	}

	public void setLoaiPhuongTien(String loaiPhuongTien) {
		if (!loaiPhuongTien.trim().equals(""))
			this.loaiPhuongTien = loaiPhuongTien;
		else
			this.loaiPhuongTien = "un-know";
	}

	public String getTenTaiXe() {
		return tenTaiXe;
	}

	public void setTenTaiXe(String tenTaiXe) {
		if (!tenTaiXe.trim().equals(""))
			this.tenTaiXe = tenTaiXe;
		else
			this.tenTaiXe = "un-know";
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
		return "PhuongTien [maPhuongTien=" + maPhuongTien + ", loaiPhuongTien=" + loaiPhuongTien + ", tenTaiXe="
				+ tenTaiXe + ", SDT=" + SDT + "]";
	}

}
