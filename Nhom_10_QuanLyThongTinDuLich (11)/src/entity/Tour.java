/**
 * Nhóm 10_QUẢN LÝ THÔNG TIN DU LỊCH
 */
package entity;

public class Tour {
    private String maTour;
    private String tenTour;
    private double giaTour;
    private KhachSan thongTinKhachSan;
    private PhuongTien thongTinPhuongTien;
    private LoTrinh thongTinLoTrinh;

    public Tour(String maTour, String tenTour, double giaTour, KhachSan thongTinKhachSan, PhuongTien thongTinPhuongTien, LoTrinh thongTinLoTrinh) {
       setGiaTour(giaTour);
       setMaTour(maTour);
       setTenTour(tenTour);
       setThongTinKhachSan(thongTinKhachSan);
       setThongTinPhuongTien(thongTinPhuongTien);
       setThongTinLoTrinh(thongTinLoTrinh);
      
    }
    

    public Tour(String maTour, String tenTour, double giaTour) {
		super();
		this.maTour = maTour;
		this.tenTour = tenTour;
		this.giaTour = giaTour;
	}


	public String getMaTour() {
        return maTour;
    }
    

    public Tour() {
		
	}

	public void setMaTour(String maTour) {
    	if (!maTour.trim().equals(""))
			this.maTour = maTour;
		else
			this.maTour = "un-know";
    }

    public String getTenTour() {
        return tenTour;
    }

    public void setTenTour(String tenTour) {
    	if (!tenTour.trim().equals(""))
			this.tenTour = tenTour;
		else
			this.tenTour = "un-know";
    }

    public double getGiaTour() {
        return giaTour;
    }

    public void setGiaTour(double giaTour) {
    	if (giaTour > 0)
			this.giaTour = giaTour;
		else
			this.giaTour = 1;
    }

    public KhachSan getThongTinKhachSan() {
        return thongTinKhachSan;
    }

    public void setThongTinKhachSan(KhachSan thongTinKhachSan) {
        this.thongTinKhachSan = thongTinKhachSan;
    }

    public PhuongTien getThongTinPhuongTien() {
        return thongTinPhuongTien;
    }

    public void setThongTinPhuongTien(PhuongTien thongTinPhuongTien) {
        this.thongTinPhuongTien = thongTinPhuongTien;
    }

    public LoTrinh getThongTinLoTrinh() {
        return thongTinLoTrinh;
    }

    public void setThongTinLoTrinh(LoTrinh thongTinLoTrinh) {
        this.thongTinLoTrinh = thongTinLoTrinh;
    }
}



