package entity;

import java.time.LocalDate;

public class LoTrinh {
	private String maLoTrinh;
	private String tenLoTrinh;
	private LocalDate ngayKhoiHanh;
	private LocalDate ngayKetThuc;
	private boolean loaiTour;

	public LoTrinh(String maLoTrinh, String tenLoTrinh, LocalDate ngayKhoiHanh, LocalDate ngayKetThuc,
			boolean loaiTour) {
		setLoaiTour(loaiTour);
		setMaLoTrinh(maLoTrinh);
//	      setNgayKetThuc(ngayKetThuc);
//	      setNgayKhoiHanh(ngayKhoiHanh);
		this.ngayKetThuc = ngayKetThuc;
		this.ngayKhoiHanh = ngayKhoiHanh;
		setTenLoTrinh(tenLoTrinh);
	}

	public LoTrinh(String maLoTrinh) {
		super();
		this.maLoTrinh = maLoTrinh;
	}

	public LoTrinh() {
		super();
	}

	public String getMaLoTrinh() {
		return maLoTrinh;
	}

	public void setMaLoTrinh(String maLoTrinh) {
		if (!maLoTrinh.trim().equals(""))
			this.maLoTrinh = maLoTrinh;
		else
			this.maLoTrinh = "un-know";
	}

	public String getTenLoTrinh() {
		return tenLoTrinh;
	}

	public void setTenLoTrinh(String tenLoTrinh) {
		if (!tenLoTrinh.trim().equals(""))
			this.tenLoTrinh = tenLoTrinh;
		else
			this.tenLoTrinh = "un-know";
	}

	public LocalDate getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}

	public void setNgayKhoiHanh(LocalDate ngayKhoiHanh) {
		if (ngayKhoiHanh.isAfter(LocalDate.now()))
			this.ngayKhoiHanh = ngayKhoiHanh;
		else
			this.ngayKhoiHanh = LocalDate.now();
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		if (ngayKetThuc.isAfter(ngayKhoiHanh))
			this.ngayKetThuc = ngayKetThuc;
		else
			this.ngayKetThuc = ngayKhoiHanh;
	}

	public boolean isLoaiTour() {
		return loaiTour;
	}

	public void setLoaiTour(boolean loaiTour) {
		this.loaiTour = loaiTour;
	}

	@Override
	public String toString() {
		return "LoTrinh [maLoTrinh=" + maLoTrinh + ", tenLoTrinh=" + tenLoTrinh + ", ngayKhoiHanh=" + ngayKhoiHanh
				+ ", ngayKetThuc=" + ngayKetThuc + ", loaiTour=" + loaiTour + "]";
	}
}
