
/*moi
database design by group 10 
*/
create database QLTourDuLich
go
use QLTourDuLich

--drop database QLTourDuLich
go

CREATE TABLE NhanVien (
  maNV VARCHAR(10) PRIMARY KEY,
  tenNV VARCHAR(50) NOT NULL,
  SDT VARCHAR(10) NOT NULL,
  email VARCHAR(50) UNIQUE,
  matKhau VARCHAR(50) NOT NULL
);
INSERT INTO NhanVien (maNV, tenNV, SDT, email, matKhau) VALUES ('NV001', 'Nguyen Van An', 1234567891, 'nv001@example.com', 'password');
INSERT INTO NhanVien (maNV, tenNV, SDT, matKhau) VALUES ('NV002', 'Tran Thi Be', 9876543211, 'password123');
INSERT INTO NhanVien (maNV, tenNV, SDT, email, matKhau) VALUES ('NV003', 'Le Van Cuong', 9876543211, 'nv003@example.com', 'abcdefg');

CREATE TABLE KhachHang (
  maKH VARCHAR(10) PRIMARY KEY,
  tenKH VARCHAR(50) NOT NULL,
  SDT VARCHAR(10) NOT NULL,
  email VARCHAR(50) UNIQUE,
  CCCD VARCHAR(50) NOT NULL,
  hoChieu BIT NOT NULL,
  gioiTinh BIT NOT NULL
);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES ('KH001', 'Nguyen Van An', '1234567891', 'nguyenvana@gmail.com', '111123456789', 1, 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES ('KH002', 'Tran Thi Be', '9876543211', 'tranthib@gmail.com', '111987654321', 0, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES ('KH003', 'Le Van Cuong', '4561237891', 'levanc@gmail.com', '111456123789', 1, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES ('KH004', 'Pham Thi Duyen', '3216549871', 'phamthid@gmail.com', '111321654987', 0, 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES ('KH005', 'Hoang Van Em', '7418529631', 'hoangvane@gmail.com', '111741852963', 1, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES ('KH006', 'Lam Van Chien', '7418529631', 'lamvanchien@gmail.com', '111673497532', 0, 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES ('KH007', 'Mai Cong Hieu', '7418529631', 'maiconghieu@gmail.com', '11132476543', 1, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH008', 'Son Tung', '2164588835', 'sontung@gmail.com', '215897775656', 1, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH009', 'Jack 97', '124698635', 'jack@gmail.com', '330254682288', 1, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH010', 'Mai Am Nhac', '2103696633', 'maiamnhac@gmail.com', '332111005648', 0, 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH011', 'Dieu Nhi', '3236003668', 'dieunhi@gmail.com', '326649905202', 0, 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH012', 'Nguyen Van Ba', '3545556289', 'nguyvanba@gmail.com', '022031655822', 1, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH013', 'Ho Quang Hieu', '1754686330', 'hoquanghieu@gmail.com', '321055784963', 1, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH014', 'Ho Minh Hau', '2588898744', 'hominhhau@gmail.com', '208849775565', 1, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH015', 'Pham Nhat Vuong', '3566984213', 'phamnhantvuong@gmail.com', '369987483377', 1, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH016', 'Tran Duc Bo', '3203225678', 'tranducbo@gmail.com', '779858825441', 0, 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH017', 'Tran Thanh Tam', '7418522963', 'tranthanhtam@gmail.com', '986654577421', 0, 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH018', 'Nguyen Phong Nhi', '7418532963', 'nguyenphuongnhi@gmail.com', '969877885586', 0, 1);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH019', 'Nguyen Dinh Chieu', '7412852963', 'nguyendinhvhieu@gmail.com', '337887721440', 1, 0);
INSERT INTO KhachHang (maKH, tenKH, SDT, email, CCCD, hoChieu, gioiTinh)
VALUES('KH020', 'Vo Van Tho', '7421852963', 'vovantho@gmail.com', '665542328357', 1, 0);

CREATE TABLE PhuongTien (
  maPhuongTien VARCHAR(10) PRIMARY KEY,
  loaiPhuongTien VARCHAR(50) NOT NULL,
  tenTaiXe VARCHAR(50) NOT NULL,
  SDT VARCHAR(10) NOT NULL
);
INSERT INTO PhuongTien (maPhuongTien, loaiPhuongTien, tenTaiXe, SDT) VALUES
('PT001', 'May bay', 'Nguyen Van An', '0987654321'),
('PT002', 'Xe khach', 'Tran Thi Be', '0123456789'),
('PT003', 'Xe 16 cho', 'Le Van Cuong', '0987654321'),
('PT004', 'May bay', 'Nguyen Van An', '0987654321'),
('PT005', 'Xe khach', 'Tran Thi Be', '0123456789'),
('PT006', 'May bay', 'Nguyen Van An', '0258654321'),
('PT007', 'Xe khach', 'Tran Thi Be', '0966456789'),
('PT008', 'Xe 16 cho', 'Le Van Cuong', '0468854321'),
('PT009', 'May bay', 'Nguyen Van An', '0977654321'),
('PT010', 'Xe khach', 'Tran Thi Be', '098856789'),
('PT011', 'May bay', 'Nguyen Van An', '0955654321'),
('PT012', 'Xe khach', 'Tran Thi Be', '093356789'),
('PT013', 'Xe 16 cho', 'Le Van Cuong', '0922654321'),
('PT014', 'May bay', 'Nguyen Van An', '0911654321'),
('PT015', 'Xe khach', 'Tran Thi Be', '0921456789'),
('PT016', 'May bay', 'Nguyen Van An', '0932654321'),
('PT017', 'Xe khach', 'Tran Thi Be', '0555456789'),
('PT018', 'Xe 16 cho', 'Le Van Cuong', '0552654321'),
('PT019', 'May bay', 'Nguyen Van An', '0551654321'),
('PT020', 'Xe khach', 'Tran Thi Be', '0532456789');



CREATE TABLE KhachSan (
  maKS VARCHAR(10) PRIMARY KEY,
  tenKS VARCHAR(50) NOT NULL,
  diaChi VARCHAR(100) NOT NULL,
  SDT VARCHAR(10) NOT NULL
);
INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES ('KS001', 'Khach san AnhDuong', '123 Duong A', '1234567891');
INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES ('KS002', 'Khach san BaoViet', '456 Duong B', '9876543211');
INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES ('KS003', 'Khach san CongHoang', '789 Duong C', '4561237891');
INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES ('KS004', 'Khach san DatTien', 'hotel Duong C', '4561237891');
INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES ('KS005', 'Khach san EnXuan', 'seehotel Duong C', '4561237891');
INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS006', 'Khach san GoVap', '456 Duong A', '9866645321');
INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES ('KS007', 'Khach san TrangTien', 'hotel Duong C', '4444432789');
INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES ('KS008', 'Khach san TrangTien', 'hotel Duong C', '4465320789');
INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES ('KS009', 'Khach san TuongVi', 'seehotel Duong C', '4325233789');
 INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS010', 'Khach san AnhSang', '123 Duong A', '1232456789');
 INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS011', 'Khach san BaoBao', '456 Duong B', '9872654321');
 INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS012', 'Khach san LacHong', '789 Duong C', '9681323789');
 INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS013', 'Khach san LeDuan', 'hotel Duong C', '3262453789');
 INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS014', 'Khach san HaiBaTrung', 'seehotel Duong C', '4274437789');
 INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS015', 'Khach san BaoAn', '456 Duong B', '9877654321');
 INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS016', 'Khach san CongCong', '789 Duong C', '3687223789');
 INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS017', 'Khach san Pessi', 'hotel Duong A', '4566362789');
INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES ('KS018', 'Khach san Messi', 'seehotel Duong B', '4562123789');
 INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS019', 'Khach san DatTien', 'hotel Duong C', '4314576789');
 INSERT INTO KhachSan (maKS, tenKS, diaChi, SDT)
VALUES('KS020', 'Khach san EnXuan', 'seehotel Duong C', '3666223789');



CREATE TABLE LoTrinh (
  maLoTrinh VARCHAR(10) PRIMARY KEY,
  tenLoTrinh VARCHAR(50) NOT NULL,
  ngayKhoiHanh DATE NOT NULL,
  ngayKetThuc DATE NOT NULL,
  loaiTour BIT NOT NULL
);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT001', 'Da Lat - Nha Trang', '2023-05-01', '2023-05-05', 1);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT002', 'Ha Noi - Ha Long', '2023-06-01', '2023-06-03', 0);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT003', 'Da Nang - Hue - Hoi An', '2023-07-01', '2023-07-04', 1);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT004', 'Sai Gon - Da Lat -Mui Ne', '2023-08-01', '2023-08-04', 1);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT005', 'Nha Trang - Da Nang - Hoi An', '2023-09-01', '2023-09-05', 1);
 INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES('LT006', 'Da Lat - vung tau', '2023-09-01', '2023-09-05', 1);
 INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES('LT007', 'Ha Noi - Hai Duong', '2023-09-01', '2023-09-05', 1);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT008', 'Da Nang - Ha Noi', '2023-09-01', '2023-09-05', 1);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT009', 'Sai Gon - Da Lat', '2023-09-01', '2023-09-05', 1);
 INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES('LT010', 'Nha Trang - My Tho', '2023-09-01', '2023-09-05', 1);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT011', 'Ha Noi - Phan Thiet', '2023-09-01', '2023-09-05', 1);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT012', 'Da Nang - Nghe An', '2023-09-01', '2023-09-05', 1);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT013', 'Sai Gon - Tay Ninh', '2023-09-01', '2023-09-05', 1);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT014', 'ha Trang - Vung Tau', '2023-09-01', '2023-09-05', 1);
INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES ('LT015', 'Ha Noi - Vinh', '2023-09-01', '2023-09-05', 1);
 INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES('LT016', 'Da Nang - Hue', '2023-09-01', '2023-09-05', 1);
 INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES('LT017', 'Sai Gon - Hue', '2023-09-01', '2023-09-05', 1);
 INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES('LT018', 'Nha Trang - Hue', '2023-09-01', '2023-09-05', 1);
 INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES('LT019', 'Ha Noi - Sai Gon', '2023-09-01', '2023-09-05', 1);
 INSERT INTO LoTrinh (maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour)
VALUES('LT020', 'Da Nang - Ha Noi', '2023-09-01', '2023-09-05', 1);

go
CREATE TABLE Tour (
  maTour VARCHAR(10) PRIMARY KEY,
  tenTour VARCHAR(50) NOT NULL,
  giaTour DECIMAL(18,2) NOT NULL,
  thongTinKhachSan VARCHAR(10) NOT NULL,
  thongTinPhuongTien VARCHAR(10) NOT NULL,
  thongTinLoTrinh VARCHAR(10) NOT NULL,
  FOREIGN KEY (thongTinKhachSan) REFERENCES KhachSan(maKS),
  FOREIGN KEY (thongTinPhuongTien) REFERENCES PhuongTien(maPhuongTien),
  FOREIGN KEY (thongTinLoTrinh) REFERENCES LoTrinh(maLoTrinh)
);

INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES ('T001', 'Tour Da Lat - Nha Trang', 5000000, 'KS001', 'PT001', 'LT001');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T002', 'Tour Ha Noi - Ha Long', 3500000, 'KS002', 'PT002', 'LT002');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T003', 'Tour Da Nang - Hue - Hoi An', 4000000, 'KS003', 'PT003', 'LT003');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T004', 'Tour Sai Gon - Da Lat - Mui Ne', 4500000, 'KS004', 'PT004', 'LT004');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T005', 'Tour Nha Trang - Da Nang - Hoi An', 4200000, 'KS005', 'PT005', 'LT005');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES ('T006', 'Tour Da Lat - vung tau', 5000000, 'KS006', 'PT006', 'LT006');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T007', 'Tour Ha Noi - Hai Duong', 3500000, 'KS007', 'PT007', 'LT007');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T008', 'Tour Da Nang - Ha Noi', 4000000, 'KS008', 'PT008', 'LT008');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T009', 'Tour Sai Gon - Da Lat', 4500000, 'KS009', 'PT009', 'LT009');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T010', 'Tour Nha Trang - My Tho', 4200000, 'KS010', 'PT010', 'LT010');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T011', 'Tour Ha Noi - Phan Thiet', 3500000, 'KS011', 'PT011', 'LT011');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T012', 'Tour Da Nang - Nghe An', 4000000, 'KS012', 'PT012', 'LT012');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T013', 'Tour Sai Gon - Tay Ninh', 4500000, 'KS013', 'PT013', 'LT013');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T014', 'Tour Nha Trang - Vung Tau', 4200000, 'KS014', 'PT014', 'LT014');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T015', 'Tour Ha Noi - Vinh', 3500000, 'KS015', 'PT015', 'LT015');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T016', 'Tour Da Nang - Hue', 4000000, 'KS016', 'PT016', 'LT016');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T017', 'Tour Sai Gon - Hue', 4500000, 'KS017', 'PT017', 'LT017');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T018', 'Tour Nha Trang - Hue', 4200000, 'KS018', 'PT018', 'LT018');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T019', 'Tour Ha Noi - Sai Gon', 3500000, 'KS019', 'PT019', 'LT019');
INSERT INTO Tour (maTour, tenTour, giaTour, thongTinKhachSan, thongTinPhuongTien, thongTinLoTrinh)
VALUES('T020', 'Tour Da Nang - Ha Noi', 4000000, 'KS020', 'PT020', 'LT020');

go
CREATE TABLE ThongTinDatTour (
  maVe VARCHAR(10) PRIMARY KEY,
  thongTinKH VARCHAR(10) NOT NULL,
  thongTinTour VARCHAR(10) NOT NULL,
  soLuong INT NOT NULL,
  thongTinNV VARCHAR(10) NOT NULL,
  FOREIGN KEY (thongTinKH) REFERENCES KhachHang(maKH),
  FOREIGN KEY (thongTinTour) REFERENCES Tour(maTour),
  FOREIGN KEY (thongTinNV) REFERENCES NhanVien(maNV)
);

INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES ('V001', 'KH002','T003',2,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V002', 'KH003','T003',3,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V003', 'KH002','T001',4,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V004', 'KH007','T001',5,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V005', 'KH004','T004',2,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V006', 'KH001','T004',6,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V007', 'KH003','T003',3,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V008', 'KH002','T001',4,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V009', 'KH007','T001',5,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V010', 'KH004','T004',2,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V011', 'KH001','T004',6,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V012', 'KH003','T003',3,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V013', 'KH002','T001',4,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V014', 'KH007','T001',5,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V015', 'KH004','T004',2,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V016', 'KH001','T004',6,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V017', 'KH003','T003',3,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V018', 'KH002','T001',4,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V019', 'KH007','T001',5,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V020', 'KH004','T004',2,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V021', 'KH004','T004',2,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V022', 'KH001','T004',6,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V023', 'KH003','T003',3,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V024', 'KH002','T001',4,'NV001');
INSERT INTO ThongTinDatTour (maVe, thongTinKH, thongTinTour, soLuong, thongTinNV)
VALUES('V025', 'KH007','T001',5,'NV001');

ALTER TABLE NhanVien ALTER COLUMN SDT NVARCHAR(50);
ALTER TABLE PhuongTien ALTER COLUMN SDT NVARCHAR(50);
ALTER TABLE KhachSan ALTER COLUMN SDT NVARCHAR(50);
ALTER TABLE KhachHang ALTER COLUMN SDT NVARCHAR(50);
ALTER TABLE KhachHang ALTER COLUMN CCCD NVARCHAR(50);




