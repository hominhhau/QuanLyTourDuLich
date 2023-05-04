package giaoDien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.text.NumberFormat;
import java.util.Locale;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import javax.swing.table.DefaultTableCellRenderer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.KhachSan_DAO;
import dao.LoTrinh_DAO;
import dao.NhanVien_DAO;
import dao.PhuongTien_DAO;
import dao.Tour_DAO;
import dao.ThongTinDatTour_DAO;
import entity.KhachHang;
import entity.KhachSan;
import entity.LoTrinh;
import entity.NhanVien;
import entity.PhuongTien;
import entity.Tour;
import entity.thongTinDatTour;
import roundedButtonUI.CustomInputDialog;
import roundedButtonUI.CustomJOptionPane;
import roundedButtonUI.RoundedButtonUI_v1;
import roundedButtonUI.RoundedButtonUI_v2;
import roundedButtonUI.RoundedButtonUI_v3;

public class GUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * bố cục cho frame 3 phần
	 */
	private JPanel pnlNorth, pnlCenter, pnlWest;

	private JButton btnTrangChu, btnKhachHang, btnTour, btnBooking, btnThongKe, btnDangXuat;

	private KhachHang_DAO kh_dao;
	private Tour_DAO tour_dao;
	private NhanVien_DAO nv_dao;
	private KhachSan_DAO khachsan_dao;
	private PhuongTien_DAO phuongtien_dao;
	private LoTrinh_DAO lotrinh_dao;
	private ThongTinDatTour_DAO ve_dao;

	/**
	 * màu chủ đạo
	 */
	Color mainColor = new Color(0, 139, 139);
	Color secondColor = new Color(255, 255, 255);
	// Color secondColor = new Color(245, 222, 179);
	Color thirdColor = new Color(255, 0, 0);
	Color fourColor = new Color(210, 180, 140);

	// các biến trung gian giá trị tạm thời, không dùng cập nhật CSDL

	int countImage = 0;

	boolean bkSex, bkHoChieu, bkLoaiTour;

	String bkCccd;

	double bkGiaTour;

	String bkMaVe, bkMaKH, bkTenKH, bkEmail, bkMaT, bkTenT, bkMaKs, bkTenKs, bkDiaChiKs, bkMaLt, bkTenLt, bkMaPT,
			bkLoaiPT, bkTenTX, bkSdtTaiXe, bkSdtKs, bkSdtKh, hinhAnh;

	LocalDate bkNgayKH, bkNgayKt;

	String tmp1;
	int tmp2, tmp3;

	String ma;
	String ten;
	String sdt;
	String emailnv;
	String mknv;

	//

	public GUI() {

		// khởi tạo kết nối đến CSDL
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		nv_dao = new NhanVien_DAO();
		kh_dao = new KhachHang_DAO();

		setSize(1280, 720);
		setTitle("Quản lý thông tin du lịch");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		// tạo giao diện
		createGUI();
	}

	private void createGUI() {

		// setLayout cho frame
		setLayout(new BorderLayout());

		// tao vùng chứa North
		pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		pnlNorth.setBackground(mainColor);
		pnlNorth.setPreferredSize(new Dimension(1280, 50));

		ImageIcon icon = new ImageIcon("demo\\logo.png");
		setIconImage(icon.getImage());

		// tao vùng chứa West
		pnlWest = new JPanel();
		add(pnlWest, BorderLayout.WEST);
		pnlWest.setLayout(new GridLayout(13, 2, 2, 2));
		pnlWest.setBackground(secondColor);
		pnlWest.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 4, secondColor));
		pnlWest.setPreferredSize(new Dimension(200, 670));

		// tao vùng chứa Center
		pnlCenter = new JPanel();
		add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout());
		pnlCenter.setBackground(secondColor);
		pnlCenter.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, fourColor));
		pnlCenter.setPreferredSize(new Dimension(1280, 670));

		pnlCenter.setLayout(new BorderLayout());

		// component phần north
		Box bn = Box.createHorizontalBox();
		Box bn1 = Box.createVerticalBox();
		Box bn2 = Box.createHorizontalBox();
		Box bn3 = Box.createVerticalBox();
		Box bn4 = Box.createHorizontalBox();

		pnlNorth.add(bn);

		JLabel lblTenTY = new JLabel("CÔNG TY LỮ HÀNH TRAVELVN");
		JLabel lblDC = new JLabel("12 Nguyễn Văn Bảo Phường 14 Gò Vấp TP.HCM");

		lblTenTY.setFont(new Font("", Font.BOLD, 20));
		lblTenTY.setForeground(Color.RED);

		lblDC.setFont(new Font("", Font.ITALIC, 12));
		lblDC.setForeground(secondColor);

		bn1.add(lblTenTY);
		bn1.add(lblDC);
		bn.add(bn1);
		bn.add(Box.createHorizontalStrut(150));

		ImageIcon logo = new ImageIcon("demo\\logo.png");
		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(logo);
		bn2.add(lblLogo);
		bn.add(bn2);

		JLabel lblTVN = new JLabel("TravelVN");
		JLabel lblComent = new JLabel("Đi khắp Việt Nam cùng TravelVN");

		lblTVN.setFont(new Font("", Font.ITALIC, 20));
		lblTVN.setForeground(Color.WHITE);

		lblComent.setFont(new Font("", Font.ITALIC, 12));
		lblComent.setForeground(Color.WHITE);

		bn3.add(lblTVN);
		bn3.add(lblComent);
		bn.add(bn3);
		bn.add(Box.createHorizontalStrut(150));

		try {
			File file = new File("temp\\tmp.txt");
			FileInputStream fis = new FileInputStream(file);
			byte[] bytesArray = new byte[(int) file.length()];
			fis.read(bytesArray);
			tmp1 = new String(bytesArray);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		tmp2 = Integer.parseInt(tmp1);

		List<NhanVien> list = nv_dao.getalltbNhanVien();
		tmp3 = 0;
		for (NhanVien nv : list) {
			if (tmp3 == tmp2) {

				ma = nv.getMaNV();
				ten = nv.getTenNV();
				sdt = nv.getSDT();
				emailnv = nv.getEmail();
				mknv = nv.getMatKhau();
			}
			tmp3++;
		}

		LoginForm loginForm = new LoginForm();
		loginForm.actionPerformed(new ActionEvent(new Object(), 0, ""));
		// ... code để đăng nhập và gán giá trị cho ma, ten và sdt

		JLabel manv = new JLabel("MÃ NHÂN VIÊN: " + ma);
		JLabel tennv = new JLabel("TÊN NHÂN VIÊN: " + ten);
		JLabel quay = new JLabel("SDT: " + sdt);

		manv.setFont(new Font("", Font.ITALIC, 12));
		manv.setForeground(secondColor);

		tennv.setFont(new Font("", Font.ITALIC, 12));
		tennv.setForeground(secondColor);

		quay.setFont(new Font("", Font.ITALIC, 12));
		quay.setForeground(secondColor);

		bn4.add(manv);
		bn4.add(Box.createHorizontalStrut(6));
		bn4.add(tennv);
		bn4.add(Box.createHorizontalStrut(6));
		bn4.add(quay);
		bn.add(bn4);

		// component phần west
		Font font = new Font("", Font.BOLD, 15);

		btnTrangChu = new JButton("|     Trang chủ" + " " + " " + " ", new ImageIcon("icon\\home.png"));
		btnTrangChu.setPreferredSize(new Dimension(100, 50));
		btnTrangChu.setForeground(secondColor);
		btnTrangChu.setOpaque(true);
		btnTrangChu.setBorderPainted(false);
		btnTrangChu.setFocusPainted(false);
		btnTrangChu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnTrangChu.setUI(new RoundedButtonUI_v1());
		btnTrangChu.setFont(font);

		btnKhachHang = new JButton("|     Khách hàng" + " " + " ", new ImageIcon("icon\\customers.png"));
		btnKhachHang.setPreferredSize(new Dimension(100, 50));
		btnKhachHang.setForeground(secondColor);
		btnKhachHang.setOpaque(true);
		btnKhachHang.setBorderPainted(false);
		btnKhachHang.setFocusPainted(false);
		btnKhachHang.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnKhachHang.setUI(new RoundedButtonUI_v1());
		btnKhachHang.setFont(font);

		btnTour = new JButton(
				"|     Tour" + " " + " " + " " + " " + " " + " " + " " + " " + " " + " " + " " + " " + " ",
				new ImageIcon("icon\\tour.png"));
		btnTour.setPreferredSize(new Dimension(100, 50));
		btnTour.setForeground(secondColor);
		btnTour.setOpaque(true);
		btnTour.setBorderPainted(false);
		btnTour.setFocusPainted(false);
		btnTour.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnTour.setUI(new RoundedButtonUI_v1());
		btnTour.setFont(font);

		btnBooking = new JButton("|     Booking" + " " + " " + " " + " " + " " + " " + " " + " ",
				new ImageIcon("icon\\booking.png"));
		btnBooking.setPreferredSize(new Dimension(100, 50));
		btnBooking.setForeground(secondColor);
		btnBooking.setOpaque(true);
		btnBooking.setBorderPainted(false);
		btnBooking.setFocusPainted(false);
		btnBooking.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnBooking.setUI(new RoundedButtonUI_v1());
		btnBooking.setFont(font);

		btnThongKe = new JButton("|     Thống kê" + " " + " ", new ImageIcon("icon\\stats.png"));
		btnThongKe.setPreferredSize(new Dimension(100, 50));
		btnThongKe.setForeground(secondColor);
		btnThongKe.setOpaque(true);
		btnThongKe.setBorderPainted(false);
		btnThongKe.setFocusPainted(false);
		btnThongKe.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnThongKe.setUI(new RoundedButtonUI_v2());
		btnThongKe.setFont(font);

		btnDangXuat = new JButton("|     Đăng xuất" + " " + " ", new ImageIcon("icon\\logout.png"));
		btnDangXuat.setPreferredSize(new Dimension(100, 50));
		btnDangXuat.setForeground(secondColor);
		btnDangXuat.setOpaque(true);
		btnDangXuat.setBorderPainted(false);
		btnDangXuat.setFocusPainted(false);
		btnDangXuat.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		btnDangXuat.setUI(new RoundedButtonUI_v3());
		btnDangXuat.setFont(font);

		pnlWest.add(Box.createVerticalStrut(0));
		pnlWest.add(btnTrangChu);
		pnlWest.add(Box.createVerticalStrut(20));
		pnlWest.add(btnKhachHang);
		pnlWest.add(Box.createVerticalStrut(20));
		pnlWest.add(btnTour);
		pnlWest.add(Box.createVerticalStrut(20));
		pnlWest.add(btnBooking);
		pnlWest.add(Box.createVerticalStrut(20));
		pnlWest.add(btnThongKe);
		pnlWest.add(Box.createVerticalStrut(20));
		pnlWest.add(btnDangXuat);

		btnTrangChu.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnTour.addActionListener(this);
		btnBooking.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnDangXuat.addActionListener(this);

		btnTrangChu.doClick();
	}

	// phần sự kiện

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == btnTrangChu) {

			// hiển thị trang chủ
			pnlCenter.removeAll();
			pnlCenter.add(new trangChu());
			pnlCenter.revalidate();
			pnlCenter.repaint();

			btnTrangChu.setForeground(thirdColor);
			btnKhachHang.setForeground(secondColor);
			btnTour.setForeground(secondColor);
			btnBooking.setForeground(secondColor);
			btnThongKe.setForeground(secondColor);

		} else if (obj == btnKhachHang) {

			// hiển thị thông tin khách hàng
			pnlCenter.removeAll();
			pnlCenter.add(new khachHang());
			pnlCenter.revalidate();
			pnlCenter.repaint();

			btnTrangChu.setForeground(secondColor);
			btnKhachHang.setForeground(thirdColor);
			btnTour.setForeground(secondColor);
			btnBooking.setForeground(secondColor);
			btnThongKe.setForeground(secondColor);

		} else if (obj == btnTour) {

			// hiển thị thông tin tour
			pnlCenter.removeAll();
			pnlCenter.add(new tourQLTT());
			pnlCenter.revalidate();
			pnlCenter.repaint();

			btnTrangChu.setForeground(secondColor);
			btnKhachHang.setForeground(secondColor);
			btnTour.setForeground(thirdColor);
			btnBooking.setForeground(secondColor);
			btnThongKe.setForeground(secondColor);

		} else if (obj == btnBooking) {

			btnTrangChu.setForeground(secondColor);
			btnKhachHang.setForeground(secondColor);
			btnTour.setForeground(secondColor);
			btnBooking.setForeground(thirdColor);
			btnThongKe.setForeground(secondColor);

			// hiển thị booking
			pnlCenter.removeAll();
			pnlCenter.add(new bookingQLTT());
			pnlCenter.revalidate();
			pnlCenter.repaint();

		} else if (obj == btnThongKe) {

			// hiển thị booking
			pnlCenter.removeAll();
			pnlCenter.add(new thongKe());
			pnlCenter.revalidate();
			pnlCenter.repaint();

			btnTrangChu.setForeground(secondColor);
			btnKhachHang.setForeground(secondColor);
			btnTour.setForeground(secondColor);
			btnBooking.setForeground(secondColor);
			btnThongKe.setForeground(thirdColor);

		} else if (obj == btnDangXuat) {
			// đăng xuât
			new LoginForm().setVisible(true);
			dispose();
		}
	}

	// đem qua test
	public static void main(String[] args) {
		new LoginForm().setVisible(true);
	}

	////////////////////////////// Panel trang chủ

	private class trangChu extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private JPanel pnlCenterContent, pnlCenterFooter;

		public trangChu() {

			// hao vùng chứa Center__Content
			pnlCenterContent = new JPanel();
			pnlCenter.add(pnlCenterContent, BorderLayout.CENTER);
			pnlCenterContent.setBackground(secondColor);
			pnlCenterContent.setPreferredSize(new Dimension(1080, 608));

			// hao vùng chứa Center__Footer
			pnlCenterFooter = new JPanel();
			pnlCenter.add(pnlCenterFooter, BorderLayout.SOUTH);
			pnlCenterFooter.setBackground(secondColor);
			pnlCenterFooter.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, fourColor));
			pnlCenterFooter.setPreferredSize(new Dimension(1080, 62));

			Timer timer;
			JLabel chBackgroud;
			JPanel pnlHome;

			ImageIcon image1 = new ImageIcon("demo\\vinhhalong.png");
			ImageIcon image2 = new ImageIcon("demo\\tb.png");
			ImageIcon image3 = new ImageIcon("demo\\tay.png");
			ImageIcon image4 = new ImageIcon("demo\\viewall.png");
			ImageIcon image5 = new ImageIcon("demo\\old.png");
			ImageIcon image6 = new ImageIcon("demo\\city.png");
			ImageIcon image7 = new ImageIcon("demo\\mientay.png");

			ImageIcon[] images = { image1, image2, image3, image4, image5, image6, image7 };

			pnlHome = new JPanel();
			pnlHome.setLayout(new BorderLayout());
			chBackgroud = new JLabel(images[countImage]);
			pnlHome.add(chBackgroud, BorderLayout.CENTER);

			add(pnlHome);

			/**
			 * sau 3 giây sẽ đổi màn hình chờ
			 */
			timer = new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					countImage = (countImage + 1) % images.length;
					chBackgroud.setIcon(images[countImage]);
				}
			});
			timer.start();

			setVisible(true);
			setBackground(fourColor);

			Box b = Box.createVerticalBox();
			Box b1 = Box.createHorizontalBox();
			Box b2 = Box.createHorizontalBox();
			Box b3 = Box.createHorizontalBox();

			pnlCenterFooter.add(b);

			JLabel lblTrangChu = new JLabel("SÁNG: HDV đón khách tại điểm hẹn và dùng điểm tâm tại trên xe" + " " + " "
					+ " " + "|" + " " + " " + " " + " "
					+ "TRƯA: Đoàn dùng bữa trưa tại nhà hàng, sau đó về khách sạn nhận phòng nghỉ ngơi");
			JLabel lblTrangChu2 = new JLabel("CHIỀU: Xe và HDV đưa đoàn đi tham quan" + " " + " " + " " + "|" + " "
					+ " " + " " + " " + "TỐI: Quý khách dùng bữa tối tự túc");
			JLabel lblTrangChu3 = new JLabel(
					"CHÚ Ý: Quý khách nào không đi tham quan, có thể nghỉ ngơi tự do tại khách sạn. Tự do ăn tối");

			b.add(b1);
			b1.add(lblTrangChu);

			b.add(b2);
			b2.add(lblTrangChu2);

			b.add(b3);
			b3.add(lblTrangChu3);

		}

	}

	////////////////////////////// Panel khách hàng

	private class khachHang extends JPanel implements ActionListener, MouseListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private JPanel pnlCenterContent, pnlCenterFooter;

		private JTable tblKhachHang;
		private DefaultTableModel dtmKhachHang;

		private JLabel lblMaKH;
		private JLabel lblTenKH;
		private JLabel lblSdt;
		private JLabel lblEmail;
		private JLabel lblCccd;
		private JLabel lblHoChieu;
		private JLabel lblGioiTinh;

		private JTextField txtMaKH;
		private JTextField txtTenKH;
		private JTextField txtSdt;
		private JTextField txtEmail;
		private JTextField txtCccd;

		private JRadioButton rdbYes;
		private JRadioButton rdbNo;
		private ButtonGroup groupHoChieu;

		private JRadioButton rdbNam;
		private JRadioButton rdbNu;
		private ButtonGroup groupSẽ;

		private JTextField txtTim;
		private JButton btnTim, btnThem, btnXoa, btnSua, btnClear, btnTienHanhChonTour;

		public khachHang() {

			// hao vùng chứa Center__Content
			pnlCenterContent = new JPanel();
			pnlCenter.add(pnlCenterContent, BorderLayout.CENTER);
			pnlCenterContent.setPreferredSize(new Dimension(1080, 608));

			// hao vùng chứa Center__Footer
			pnlCenterFooter = new JPanel();
			pnlCenter.add(pnlCenterFooter, BorderLayout.SOUTH);
			pnlCenterFooter.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, fourColor));
			pnlCenterFooter.setPreferredSize(new Dimension(1080, 62));

			Box b = Box.createVerticalBox();
			Box b1 = Box.createHorizontalBox();
			Box b2 = Box.createHorizontalBox();
			Box b3 = Box.createHorizontalBox();
			Box b4 = Box.createHorizontalBox();
			Box b5 = Box.createHorizontalBox();

			add(b);

			b.add(b1);
			JLabel lblTittle = new JLabel("Thông tin khách hàng");
			lblTittle.setFont(new Font("", Font.BOLD, 25));
			lblTittle.setForeground(Color.RED);
			b1.add(lblTittle);
			b.add(Box.createVerticalStrut(20));

			b.add(b2);

			JPanel pnlTable = new JPanel();
			dtmKhachHang = new DefaultTableModel();
			tblKhachHang = new JTable(dtmKhachHang) {
				/**
				 * không cho edit trên table
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int collumn) {
					return false;
				}
			};
			dtmKhachHang.addColumn("Mã khách hàng");
			dtmKhachHang.addColumn("Tên khách hàng");
			dtmKhachHang.addColumn("Số điện thoại");
			dtmKhachHang.addColumn("Email");
			dtmKhachHang.addColumn("CCCD");
			dtmKhachHang.addColumn("Hộ chiếu");
			dtmKhachHang.addColumn("Giới tính");

			TableColumn column1 = tblKhachHang.getColumnModel().getColumn(1);
			column1.setPreferredWidth(150);
			TableColumn column3 = tblKhachHang.getColumnModel().getColumn(3);
			column3.setPreferredWidth(150);

			Font font = new Font("Arial", Font.PLAIN, 14);
			tblKhachHang.setFont(font);

			JTableHeader header = tblKhachHang.getTableHeader();
			header.setFont(font.deriveFont(Font.BOLD));

			tblKhachHang.setBorder(BorderFactory.createLineBorder(Color.black));

			// thêm hiệu ứng hover cho các hàng trong bảng
			tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					int row = tblKhachHang.rowAtPoint(evt.getPoint());
					if (row >= 0) {
						tblKhachHang.setRowSelectionInterval(row, row);
					}
				}
			});

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);

			for (int i = 0; i < tblKhachHang.getColumnCount(); i++) {
				tblKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}

			pnlTable.add(tblKhachHang);

			JScrollPane sp = new JScrollPane(tblKhachHang, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			sp.setPreferredSize(new Dimension(1060, 250));

			b2.add(sp);
			b.add(Box.createVerticalStrut(15));

			/**
			 * đoc vào table, do có được list
			 */
			DocDuLieuDatabaseVaoTable();

			b.add(Box.createVerticalStrut(20));

			Border roundedBorder = BorderFactory.createLineBorder(fourColor, 2, true);

			lblMaKH = new JLabel("Mã khách hàng: ");
			txtMaKH = new JTextField();
			lblMaKH.setFont(new Font("", Font.BOLD, 15));
			lblMaKH.setForeground(mainColor);
			txtMaKH.setPreferredSize(new Dimension(30, 30));
			txtMaKH.setBorder(roundedBorder);
			txtMaKH.setFont(new Font("", Font.BOLD, 12));

			lblTenKH = new JLabel("Tên khách hàng: ");
			txtTenKH = new JTextField();
			lblTenKH.setFont(new Font("", Font.BOLD, 15));
			lblTenKH.setForeground(mainColor);
			txtTenKH.setPreferredSize(new Dimension(30, 30));
			txtTenKH.setBorder(roundedBorder);
			txtTenKH.setFont(new Font("", Font.BOLD, 12));

			lblSdt = new JLabel("Số điện thoại: ");
			txtSdt = new JTextField();
			lblSdt.setFont(new Font("", Font.BOLD, 15));
			lblSdt.setForeground(mainColor);
			txtSdt.setPreferredSize(new Dimension(30, 30));
			txtSdt.setBorder(roundedBorder);
			txtSdt.setFont(new Font("", Font.BOLD, 12));

			lblEmail = new JLabel("Email: ");
			txtEmail = new JTextField();
			lblEmail.setFont(new Font("", Font.BOLD, 15));
			lblEmail.setForeground(mainColor);
			txtEmail.setPreferredSize(new Dimension(30, 30));
			txtEmail.setBorder(roundedBorder);
			txtEmail.setFont(new Font("", Font.BOLD, 12));

			lblCccd = new JLabel("CCCD: ");
			txtCccd = new JTextField();
			lblCccd.setFont(new Font("", Font.BOLD, 15));
			lblCccd.setForeground(mainColor);
			txtCccd.setPreferredSize(new Dimension(30, 30));
			txtCccd.setBorder(roundedBorder);
			txtCccd.setFont(new Font("", Font.BOLD, 12));

			lblHoChieu = new JLabel("Hộ chiếu: ");
			rdbYes = new JRadioButton("Có");
			rdbNo = new JRadioButton("Không");
			groupHoChieu = new ButtonGroup();
			groupHoChieu.add(rdbYes);
			groupHoChieu.add(rdbNo);
			lblHoChieu.setFont(new Font("", Font.BOLD, 15));
			lblHoChieu.setForeground(mainColor);

			lblGioiTinh = new JLabel("Giới tính: ");
			rdbNam = new JRadioButton("Nam");
			rdbNu = new JRadioButton("Nữ");
			groupSẽ = new ButtonGroup();
			groupSẽ.add(rdbNam);
			groupSẽ.add(rdbNu);
			lblGioiTinh.setFont(new Font("", Font.BOLD, 15));
			lblGioiTinh.setForeground(mainColor);

			b.add(b3);
			b3.add(Box.createHorizontalStrut(20));
			b3.add(lblMaKH);
			b3.add(txtMaKH);
			b3.add(Box.createHorizontalStrut(80));
			b3.add(lblTenKH);
			b3.add(txtTenKH);
			b3.add(Box.createHorizontalStrut(20));
			b.add(Box.createVerticalStrut(50));

			b.add(b4);
			b4.add(Box.createHorizontalStrut(20));
			b4.add(lblSdt);
			lblSdt.setPreferredSize(lblMaKH.getPreferredSize());
			b4.add(txtSdt);
			b4.add(Box.createHorizontalStrut(80));
			b4.add(lblCccd);
			lblCccd.setPreferredSize(lblTenKH.getPreferredSize());
			b4.add(txtCccd);
			b4.add(Box.createHorizontalStrut(20));
			b.add(Box.createVerticalStrut(50));

			b.add(b5);
			b5.add(Box.createHorizontalStrut(20));
			b5.add(lblEmail);
			lblEmail.setPreferredSize(lblMaKH.getPreferredSize());
			b5.add(txtEmail);
			b5.add(Box.createHorizontalStrut(80));
			b5.add(lblHoChieu);
			b5.add(rdbYes);
			b5.add(rdbNo);
			b5.add(Box.createHorizontalStrut(20));
			rdbYes.setSelected(true);

			b5.add(Box.createHorizontalStrut(60));
			b5.add(lblGioiTinh);
			b5.add(rdbNam);
			b5.add(rdbNu);
			b5.add(Box.createHorizontalStrut(60));
			rdbNam.setSelected(true);

			JLabel lblTim = new JLabel("Tìm theo SDT: ");
			lblTim.setFont(new Font("", Font.BOLD, 18));
			lblTim.setForeground(thirdColor);
			txtTim = new JTextField(12);
			txtTim.setBorder(roundedBorder);
			txtTim.setFont(new Font("", Font.BOLD, 12));

			btnTim = new JButton("Tìm");
			btnTim.setPreferredSize(new Dimension(90, 50));
			btnTim.setForeground(Color.WHITE);
			btnTim.setOpaque(true);
			btnTim.setBorderPainted(false);
			btnTim.setFocusPainted(false);
			btnTim.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTim.setUI(new RoundedButtonUI_v1());

			btnThem = new JButton("Thêm");
			btnThem.setPreferredSize(new Dimension(90, 50));
			btnThem.setForeground(Color.WHITE);
			btnThem.setOpaque(true);
			btnThem.setBorderPainted(false);
			btnThem.setFocusPainted(false);
			btnThem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnThem.setUI(new RoundedButtonUI_v1());

			btnXoa = new JButton("Xóa");
			btnXoa.setPreferredSize(new Dimension(90, 50));
			btnXoa.setForeground(Color.WHITE);
			btnXoa.setOpaque(true);
			btnXoa.setBorderPainted(false);
			btnXoa.setFocusPainted(false);
			btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnXoa.setUI(new RoundedButtonUI_v1());

			btnSua = new JButton("Sữa");
			btnSua.setPreferredSize(new Dimension(90, 50));
			btnSua.setForeground(Color.WHITE);
			btnSua.setOpaque(true);
			btnSua.setBorderPainted(false);
			btnSua.setFocusPainted(false);
			btnSua.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnSua.setUI(new RoundedButtonUI_v1());

			btnClear = new JButton("Clear");
			btnClear.setPreferredSize(new Dimension(90, 50));
			btnClear.setForeground(Color.WHITE);
			btnClear.setOpaque(true);
			btnClear.setBorderPainted(false);
			btnClear.setFocusPainted(false);
			btnClear.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnClear.setUI(new RoundedButtonUI_v1());

			btnTienHanhChonTour = new JButton("Chọn tour");
			btnTienHanhChonTour.setPreferredSize(new Dimension(90, 50));
			btnTienHanhChonTour.setForeground(Color.WHITE);
			btnTienHanhChonTour.setOpaque(true);
			btnTienHanhChonTour.setBorderPainted(false);
			btnTienHanhChonTour.setFocusPainted(false);
			btnTienHanhChonTour.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTienHanhChonTour.setUI(new RoundedButtonUI_v3());

			pnlCenterFooter.setBackground(secondColor);

			pnlCenterFooter.add(lblTim);
			pnlCenterFooter.add(txtTim);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnTim);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnThem);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnXoa);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnSua);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnClear);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnTienHanhChonTour);

			btnTim.addActionListener(this);
			btnThem.addActionListener(this);
			btnXoa.addActionListener(this);
			btnSua.addActionListener(this);
			btnClear.addActionListener(this);
			btnTienHanhChonTour.addActionListener(this);

			tblKhachHang.addMouseListener(this);

			tblKhachHang.setRowSelectionInterval(0, 0);

			int row = 0;
			try {
				kh_dao = new KhachHang_DAO();
				List<KhachHang> khs = kh_dao.getalltbKhachHang();
				int i = 0;
				for (KhachHang kh : khs) {
					if (row == i) {
						txtMaKH.setText(kh.getMaKH());
						txtTenKH.setText(kh.getTenKH());
						String a = String.valueOf(kh.getSDT());
						txtSdt.setText(a);
						txtEmail.setText(kh.getEmail());
						String z = String.valueOf(kh.getCCCD());
						txtCccd.setText(z);

						boolean c = kh.isHoChieu();
						if (c) {
							rdbYes.setSelected(true);
							rdbNo.setSelected(false);
						} else {
							rdbNo.setSelected(true);
							rdbYes.setSelected(false);
						}

						boolean d = kh.isGioiTinh();
						if (d) {
							rdbNam.setSelected(true);
							rdbNu.setSelected(false);
						} else {
							rdbNu.setSelected(true);
							rdbNam.setSelected(false);
						}
					}
					i++;
				}

			} catch (Exception e2) {
			}
			txtMaKH.setEditable(false);
			txtCccd.setEditable(false);

		}

		public void clearDaTaOnModel() {
			DefaultTableModel dtm = (DefaultTableModel) tblKhachHang.getModel();
			dtm.getDataVector().removeAllElements();
		}

		public void clearTextField() {
			txtMaKH.setText("");
			txtTenKH.setText("");
			txtSdt.setText("");
			txtEmail.setText("");
			txtCccd.setText("");
			rdbYes.setSelected(false);
			rdbNam.setSelected(true);
			txtTim.setText("");

			txtMaKH.setEditable(false);
			txtCccd.setEditable(true);

			tblKhachHang.clearSelection();

			txtMaKH.setText("");
			txtTenKH.setText("");
			txtSdt.setText("");
			txtCccd.setText("");
			txtEmail.setText("");
			rdbNo.setSelected(true);
			rdbNu.setSelected(true);

			bkMaKH = null;
			bkTenKH = null;
			bkSdtKh = null;
			bkEmail = null;
			bkCccd = null;
			bkHoChieu = rdbNo.isSelected();
			bkSex = rdbNu.isSelected();

			bkMaT = null;
			bkTenT = null;
			bkGiaTour = 0;

			bkMaKs = null;
			bkTenKs = null;
			bkDiaChiKs = null;
			bkSdtKs = null;

			bkMaLt = null;
			bkTenLt = null;

			bkNgayKH = null;
			bkNgayKt = null;

			bkLoaiTour = false;

			bkMaPT = null;
			bkLoaiPT = null;
			bkTenTX = null;
			bkSdtTaiXe = null;

		}

		public String phatSinhMaKH() {

			List<KhachHang> khs = kh_dao.getalltbKhachHang();
			String temp = null;
			for (KhachHang kh : khs) {
				temp = kh.getMaKH();
			}
			int count = laySoDuoi(temp);

			count++;

			String maVe = String.format("KH%03d", count);

			return maVe;
		}

		public int laySoDuoi(String str) {
			if (str == null) {
				return 0;
			} else {
				String numberStr = str.substring(2); // loại bỏ ký tự "V"
				int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
				return number;
			}

		}

		public void DocDuLieuDatabaseVaoTable() {
			List<KhachHang> list = kh_dao.getalltbKhachHang();
			for (KhachHang kh : list) {
				dtmKhachHang.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSDT(), kh.getEmail(),
						kh.getCCCD(), kh.isHoChieu() ? "Có" : "Không", kh.isGioiTinh() ? "Nam" : "Nữ" });
			}
		}

		private KhachHang revertKhacchHangFromTextfields() {
			// String maKH = txtMaKH.getText().trim();
			String maKH = phatSinhMaKH();
			String tenKH = txtTenKH.getText().trim();
			String sdt = txtSdt.getText().trim();
			String email = txtEmail.getText().trim();
			String cccd = txtCccd.getText().trim();
			boolean hoChieu = rdbNam.isSelected();
			boolean gioiTinh = rdbNam.isSelected(); // assuming two radio buttons for gender selection

			return new KhachHang(maKH, tenKH, sdt, email, cccd, hoChieu, gioiTinh);
		}

		/**
		 * BIỂU THỨC CHÍNH QUY
		 * 
		 * @return
		 */
		public boolean validDataKH() {
			// String maKH = txtMaKH.getText().trim();
			String maKH = phatSinhMaKH();
			String tenKH = txtTenKH.getText().trim();
			String sdt = txtSdt.getText().trim();
			String email = txtEmail.getText().trim();
			String cccd = txtCccd.getText().trim();

			/**
			 * Mã khách hàng theo định dạng KH001
			 */
			if (!(maKH.length() > 0 && maKH.matches("(KH)\\d{3}"))) {
				CustomJOptionPane.showMessageDialog("Mã KH bắt đầu bằng 2 chữ cái 'KH' và theo sau là 3 chữ số!!!");
				return false;
			}

			/**
			 * Tên khách hàng: + Chỉ chứa các ký tự a – z + Chỉ chứa các ký tự A – Z + Chứa
			 * ký tự khoảng trắng và ko chứa ký tự số + Tối thiểu 3 kí tự, tối đa 25 kí tự
			 */
			if (!(tenKH.length() > 0 && tenKH.matches("^[a-zA-Z\s_-]{3,25}$"))) {
				CustomJOptionPane
						.showMessageDialog("Tên KH không chứa kí tự số và tối thiểu là 3 kí tự, tối đa là 25 kí tự!!!");
				return false;
			}

			/**
			 * Số điện thoại gồm 12 số
			 */
			if (!(sdt.length() > 0 && sdt.matches("\\d{10}"))) {
				CustomJOptionPane.showMessageDialog("Số điện thoại gồm 10 số!!!");
				return false;
			}
			/**
			 * Email
			 */
			if (!(email.length() > 0 && email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))) {
				CustomJOptionPane
						.showMessageDialog("Email không đúng định dạng !!! Phải theo định dạng : ten@gmail.com");
				return false;
			}

			/**
			 * CCCD: 12 số
			 */
			if (!(cccd.length() > 0 && cccd.matches("\\d{12}"))) {
				CustomJOptionPane.showMessageDialog("Căn cước công dân gồm 12 số !!!");
				return false;
			}
			return true;

		}

		@SuppressWarnings("unused")
		public boolean validDataKH1() {
			String maKH = txtMaKH.getText().trim();
			String tenKH = txtTenKH.getText().trim();
			String sdt = txtSdt.getText().trim();
			String email = txtEmail.getText().trim();
			String cccd = txtCccd.getText().trim();

			/**
			 * Mã khách hàng theo định dạng KH001
			 */
			if (!(maKH.length() > 0 && maKH.matches("(KH)\\d{3}"))) {
				CustomJOptionPane.showMessageDialog("Mã KH bắt đầu bằng 2 chữ cái 'KH' và theo sau là 3 chữ số!!!");
				return false;
			}

			/**
			 * Tên khách hàng: + Chỉ chứa các ký tự a – z + Chỉ chứa các ký tự A – Z + Chứa
			 * ký tự khoảng trắng và ko chứa ký tự số + Tối thiểu 3 kí tự, tối đa 25 kí tự
			 */
			if (!(tenKH.length() > 0 && tenKH.matches("^[a-zA-Z\s_-]{3,25}$"))) {
				CustomJOptionPane
						.showMessageDialog("Tên KH không chứa kí tự số và tối thiểu là 3 kí tự, tối đa là 25 kí tự!!!");
				return false;
			}

			/**
			 * Số điện thoại gồm 12 số
			 */
			if (!(sdt.length() > 0 && sdt.matches("\\d{10}"))) {
				CustomJOptionPane.showMessageDialog("Số điện thoại gồm 10 số !!!");
				return false;
			}
			/**
			 * Email
			 */
			if (!(email.length() > 0 && email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))) {
				CustomJOptionPane
						.showMessageDialog("Email không đúng định dạng !!! Phải theo định dạng : ten@gmail.com");
				return false;
			}

			/**
			 * CCCD: 12 số
			 */
			if (!(cccd.length() > 0 && cccd.matches("\\d{12}"))) {
				CustomJOptionPane.showMessageDialog("Căn cước công dân gồm 12 số !!!");
				return false;
			}
			return true;

		}

		private KhachHang createFormKH() {
			KhachHang kh;
			boolean hochieu;
			if (rdbYes.isSelected()) {
				hochieu = true;
			} else
				hochieu = false;
			boolean gioitinh;
			if (rdbNam.isSelected()) {
				gioitinh = true;
			} else
				gioitinh = false;
			kh = new KhachHang(txtMaKH.getText(), txtTenKH.getText(), txtSdt.getText(), txtEmail.getText(),
					txtCccd.getText(), hochieu, gioitinh);
			return kh;
		}

		@SuppressWarnings("unused")
		private boolean checkMa1() {
			ve_dao = new ThongTinDatTour_DAO();
			List<thongTinDatTour> ves = ve_dao.getAllVe();
			for (thongTinDatTour ve : ves) {
				if (ve.getThongTinKhachhang().getMaKH().equals(txtMaKH.getText())) {
					return false;
				}
			}
			return true;
		}

		private boolean checkMa2() {
			kh_dao = new KhachHang_DAO();
			List<KhachHang> khs = kh_dao.getalltbKhachHang();
			for (KhachHang kh : khs) {
				if (kh.getMaKH().equals(txtMaKH.getText()) || kh.getCCCD().equals(txtCccd.getText())) {
					return false;
				}
			}
			return true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();

			if (obj == btnTim) {

				String sdtkh = txtTim.getText().trim();
				if (!(sdtkh.length() > 0 && sdtkh.matches("\\d{10}"))) {
					CustomJOptionPane.showMessageDialog("Số điện thoại gồm 10 số !!!");
				} else {

					String maTim = txtTim.getText();
					ArrayList<KhachHang> dsKHtim = null;

					for (KhachHang kh : kh_dao.getalltbKhachHang()) {
						if (kh.getSDT().equals(maTim)) {
							dsKHtim = new ArrayList<KhachHang>();
							dsKHtim.add(kh);
						}
					}
					if (dsKHtim != null) {
						clearDaTaOnModel();
						for (KhachHang kh : dsKHtim) {
							dtmKhachHang.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getSDT(), kh.getEmail(),
									kh.getCCCD(), kh.isHoChieu() ? "Có" : "Không", kh.isGioiTinh() ? "Nam" : "Nữ" });
						}
					} else if (dsKHtim == null) {
						CustomJOptionPane.showMessageDialog("không tìm thấy");
					}
				}

			}

			if (obj == btnThem) {
				if (validDataKH()) {
					KhachHang kh = revertKhacchHangFromTextfields();
					if (!kh_dao.getalltbKhachHang().contains(kh)) {

						if (checkMa2()) {
							kh_dao.createKH(kh);
							clearTextField();
							clearDaTaOnModel();
							DocDuLieuDatabaseVaoTable();

						} else {
							CustomJOptionPane.showMessageDialog("Các khoá không được trùng giá trị!!!");
						}
					} else {
						CustomJOptionPane.showMessageDialog("Mã trùng!!!");
					}
				} else {
					CustomJOptionPane.showMessageDialog("Vui lòng nhập đầy đủ thông tin!!!");
				}
			}

			if (obj == btnXoa) {
				ve_dao = new ThongTinDatTour_DAO();
				if (tblKhachHang.getSelectedRowCount() > 0) {

					if (JOptionPane.showConfirmDialog(this, "Xác nhận  dòng đã chọn" + " !!!", "Warring",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {

						int[] selectedRows = tblKhachHang.getSelectedRows();
						for (int i = selectedRows.length - 1; i >= 0; i--) {
							List<KhachHang> khs = kh_dao.getalltbKhachHang();
							KhachHang kh = khs.get(selectedRows[i]);
							try {
								List<thongTinDatTour> ves = ve_dao.getAllVe();
								for (thongTinDatTour ve : ves) {
									if (ve.getThongTinKhachhang().getMaKH().equals(kh.getMaKH())) {
										String maVe = ve.getMaVe();
										ve_dao.xoaVe(maVe);
									}
								}
							} catch (Exception e2) {
							}
							kh_dao.xoaKH(kh.getMaKH());

						}
						clearDaTaOnModel();
						DocDuLieuDatabaseVaoTable();
						tblKhachHang.clearSelection();
						CustomJOptionPane.showMessageDialog("Xoa thanh cong!!!");

					}

				} else
					CustomJOptionPane.showMessageDialog("Chọn dòng cần xoá!!!");
			}

			if (obj == btnSua) {

				if (validDataKH()) {
					if (tblKhachHang.getSelectedRowCount() > 0) {
						KhachHang kh = createFormKH();
						kh_dao.update(kh);
						clearDaTaOnModel();
						DocDuLieuDatabaseVaoTable();
						clearTextField();
					} else
						CustomJOptionPane.showMessageDialog("Chọn dòng cần sửa!!!");
				}

			}

			if (obj == btnClear) {
				clearTextField();
				clearDaTaOnModel();
				tblKhachHang.clearSelection();
				DocDuLieuDatabaseVaoTable();
				txtCccd.setEditable(true);
				txtMaKH.requestFocus();
			}

			if (obj == btnTienHanhChonTour) {

				if (txtMaKH.getText().equals("")) {
					CustomJOptionPane.showMessageDialog("Chưa chọn khách hàng!!!");
				} else {

//					if (!checkMa()) {
//						CustomJOptionPane.showMessageDialog( "Khách hàng đã dặt Tour rồi");
//					} else {

					bkMaKH = txtMaKH.getText();
					bkTenKH = txtTenKH.getText();
					bkSdtKh = txtSdt.getText();
					bkEmail = txtEmail.getText();
					bkCccd = txtCccd.getText();
					bkHoChieu = rdbYes.isSelected();
					bkSex = rdbNam.isSelected();

					btnTour.doClick();
//					}

				}

			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tblKhachHang.getSelectedRow();
			try {
				kh_dao = new KhachHang_DAO();
				List<KhachHang> khs = kh_dao.getalltbKhachHang();
				int i = 0;
				for (KhachHang kh : khs) {
					if (row == i) {
						txtMaKH.setText(kh.getMaKH());
						txtTenKH.setText(kh.getTenKH());
						String a = String.valueOf(kh.getSDT());
						txtSdt.setText(a);
						txtEmail.setText(kh.getEmail());
						String b = String.valueOf(kh.getCCCD());
						txtCccd.setText(b);

						boolean c = kh.isHoChieu();
						if (c) {
							rdbYes.setSelected(true);
							rdbNo.setSelected(false);
						} else {
							rdbNo.setSelected(true);
							rdbYes.setSelected(false);
						}

						boolean d = kh.isGioiTinh();
						if (d) {
							rdbNam.setSelected(true);
							rdbNu.setSelected(false);
						} else {
							rdbNu.setSelected(true);
							rdbNam.setSelected(false);
						}
					}
					i++;
				}
			} catch (Exception e2) {
			}

			txtMaKH.setEditable(false);
			txtCccd.setEditable(false);

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}

	////////////////////////////// Panel Tour

	private class tourQLTT extends JPanel implements ActionListener, MouseListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private JPanel pnlCenterNavBar, pnlCenterFooter, pnlCenterContentLeft, pnlCenterContentRight;

		private JTable tblTour;
		private DefaultTableModel dtmTour;

		private JTextField txtMaT;
		private JTextField txtTenT;
		private JTextField txtGiaT;

		private JTextField txtMaKs;
		private JTextField txtTenKs;
		private JTextField txtDiaChiKs;
		private JTextField txtSdtKs;

		private JTextField txtMaLt;
		private JTextField txtTenLt;
		private JDateChooser ngayKH;
		private JDateChooser ngayKT;
		public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
//		public static final SimpleDateFormat DATE_FORMAT_SQL = new SimpleDateFormat("yyyy-MM-dd");
		private JRadioButton rdbSang;
		private JRadioButton rdbThuong;
		private ButtonGroup groupLoaiTour;

		private JTextField txtMaPT;
		private JTextField txtLoaiPT;
		private JTextField txtTenTX;
		private JTextField txtSdtTaiXe;

		private JTextField txtTim;
		private JButton btnTim, btnThem, btnXoa, btnSua, btnClear, btnDatTour, btnTtTour, btnTtKS, btnTtLt, btnTtPt;

		private JLabel lblMaT;
		private JLabel lblTenT;
		private JLabel lblGiaT;
		private JLabel lblMaKs;
		private JLabel lblTenKs;
		private JLabel lblDiaChiKs;
		private JLabel lblSdtKs;
		private JLabel lblMaLt;
		private JLabel lblTenLt;
		private JLabel lblNgayKH;
		private JLabel lblNgayKT;
		private JLabel lblLoaiTour;
		private JLabel lblMaPT;
		private JLabel lblLoaiPT;
		private JLabel lblTenTX;
		private JLabel lblSdtTaiXe;

		private String hinhAnh;

		public tourQLTT() {

			pnlCenterNavBar = new JPanel();
			pnlCenter.add(pnlCenterNavBar, BorderLayout.NORTH);
			pnlCenterNavBar.setPreferredSize(new Dimension(1080, 280));

			pnlCenterContentLeft = new JPanel();
			pnlCenter.add(pnlCenterContentLeft, BorderLayout.WEST);
			pnlCenterContentLeft.setPreferredSize(new Dimension(200, 328));
			pnlCenterContentLeft.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, fourColor));

			pnlCenterContentRight = new JPanel();
			pnlCenter.add(pnlCenterContentRight, BorderLayout.EAST);
			pnlCenterContentRight.setPreferredSize(new Dimension(880, 328));
			pnlCenterContentRight.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, fourColor));

			pnlCenterFooter = new JPanel();
			pnlCenter.add(pnlCenterFooter, BorderLayout.SOUTH);
			pnlCenterFooter.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, fourColor));
			pnlCenterFooter.setPreferredSize(new Dimension(1080, 62));

			Box b = Box.createVerticalBox();
			Box b1 = Box.createHorizontalBox();
			Box b2 = Box.createHorizontalBox();

			b.add(b1);
			JLabel lblTittle = new JLabel("Thông tin Tour");
			lblTittle.setFont(new Font("", Font.BOLD, 25));
			lblTittle.setForeground(Color.RED);
			b1.add(lblTittle);
			b.add(Box.createVerticalStrut(20));

			b.add(b2);
			JPanel pnlTable = new JPanel();
			dtmTour = new DefaultTableModel();
			tblTour = new JTable(dtmTour) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int collumn) {
					return false;
				}
			};

			dtmTour.addColumn("Mã Tour");
			dtmTour.addColumn("Tên Tour");
			dtmTour.addColumn("Giá Tour");
			dtmTour.addColumn("Thông tin khách sạn");
			dtmTour.addColumn("Thông tin lộ trình");
			dtmTour.addColumn("Thông tin phương tiện");

			TableColumn column1 = tblTour.getColumnModel().getColumn(1);
			column1.setPreferredWidth(100);
			TableColumn column3 = tblTour.getColumnModel().getColumn(3);
			column3.setPreferredWidth(170);
			TableColumn column4 = tblTour.getColumnModel().getColumn(4);
			column4.setPreferredWidth(170);
			TableColumn column5 = tblTour.getColumnModel().getColumn(5);
			column5.setPreferredWidth(100);

			Font font = new Font("Arial", Font.PLAIN, 14);
			tblTour.setFont(font);

			JTableHeader header = tblTour.getTableHeader();
			header.setFont(font.deriveFont(Font.BOLD));

			tblTour.setBorder(BorderFactory.createLineBorder(Color.black));

			// thêm hiệu ứng hover cho các hàng trong bảng
			tblTour.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					int row = tblTour.rowAtPoint(evt.getPoint());
					if (row >= 0) {
						tblTour.setRowSelectionInterval(row, row);
					}
				}
			});

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);

			for (int i = 0; i < tblTour.getColumnCount(); i++) {
				tblTour.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}

			pnlTable.add(tblTour);

			JScrollPane sp = new JScrollPane(tblTour, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			sp.setPreferredSize(new Dimension(1060, 222));
			b2.add(sp);

			/**
			 * đoc vào table, do có được list
			 */
			DocDuLieuDatabaseVaoTable();

			btnTtTour = new JButton("Chi tiết tour");
			btnTtTour.setPreferredSize(new Dimension(160, 40));
			btnTtTour.setForeground(secondColor);
			btnTtTour.setOpaque(true);
			btnTtTour.setBorderPainted(false);
			btnTtTour.setFocusPainted(false);
			btnTtTour.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTtTour.setUI(new RoundedButtonUI_v1());
			btnTtTour.setFont(font);

			btnTtKS = new JButton("Chi tiết khách Sạn");
			btnTtKS.setPreferredSize(new Dimension(160, 40));
			btnTtKS.setForeground(secondColor);
			btnTtKS.setOpaque(true);
			btnTtKS.setBorderPainted(false);
			btnTtKS.setFocusPainted(false);
			btnTtKS.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTtKS.setUI(new RoundedButtonUI_v1());
			btnTtKS.setFont(font);

			btnTtLt = new JButton("Chi tiết lộ trình");
			btnTtLt.setPreferredSize(new Dimension(160, 40));
			btnTtLt.setForeground(secondColor);
			btnTtLt.setOpaque(true);
			btnTtLt.setBorderPainted(false);
			btnTtLt.setFocusPainted(false);
			btnTtLt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTtLt.setUI(new RoundedButtonUI_v1());
			btnTtLt.setFont(font);

			btnTtPt = new JButton("Chi tiết phương tiện");
			btnTtPt.setPreferredSize(new Dimension(160, 40));
			btnTtPt.setForeground(secondColor);
			btnTtPt.setOpaque(true);
			btnTtPt.setBorderPainted(false);
			btnTtPt.setFocusPainted(false);
			btnTtPt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTtPt.setUI(new RoundedButtonUI_v1());
			btnTtPt.setFont(font);

			Border roundedBorder = BorderFactory.createLineBorder(fourColor, 2, true);

			lblMaT = new JLabel("Mã Tour: ");
			txtMaT = new JTextField();

			lblMaT.setForeground(Color.BLUE);
			lblMaT.setFont(new Font("", Font.BOLD, 18));
			txtMaT.setPreferredSize(new Dimension(150, 20));
			txtMaT.setBorder(roundedBorder);
			txtMaT.setFont(new Font("", Font.BOLD, 12));

			lblTenT = new JLabel("Tên Tour: ");
			txtTenT = new JTextField();

			lblTenT.setForeground(Color.BLUE);
			lblTenT.setFont(new Font("", Font.BOLD, 18));
			lblTenT.setForeground(thirdColor);
			txtTenT.setPreferredSize(new Dimension(180, 20));
			txtTenT.setBorder(roundedBorder);
			txtTenT.setFont(new Font("", Font.BOLD, 12));

			lblGiaT = new JLabel("Giá Tour: ");
			txtGiaT = new JTextField();

			lblGiaT.setForeground(Color.BLUE);
			lblGiaT.setFont(new Font("", Font.BOLD, 18));
			lblGiaT.setForeground(thirdColor);
			txtGiaT.setPreferredSize(new Dimension(180, 20));
			txtGiaT.setBorder(roundedBorder);
			txtGiaT.setFont(new Font("", Font.BOLD, 12));

			lblMaKs = new JLabel("Mã khách sạn: ");
			txtMaKs = new JTextField();

			lblMaKs.setForeground(Color.BLUE);
			lblMaKs.setFont(new Font("", Font.BOLD, 18));
			txtMaKs.setPreferredSize(new Dimension(180, 20));
			txtMaKs.setBorder(roundedBorder);
			txtMaKs.setFont(new Font("", Font.BOLD, 12));

			lblTenKs = new JLabel("Tên khách sạn: ");
			txtTenKs = new JTextField();

			lblTenKs.setForeground(Color.BLUE);
			lblTenKs.setFont(new Font("", Font.BOLD, 18));
			lblTenKs.setForeground(thirdColor);
			txtTenKs.setPreferredSize(new Dimension(180, 20));
			txtTenKs.setBorder(roundedBorder);
			txtTenKs.setFont(new Font("", Font.BOLD, 12));

			lblDiaChiKs = new JLabel("Địa chỉ: ");
			txtDiaChiKs = new JTextField();

			lblDiaChiKs.setBackground(Color.BLUE);
			lblDiaChiKs.setForeground(Color.BLUE);
			lblDiaChiKs.setFont(new Font("", Font.BOLD, 18));
			lblDiaChiKs.setForeground(thirdColor);
			txtDiaChiKs.setPreferredSize(new Dimension(180, 20));
			txtDiaChiKs.setBorder(roundedBorder);
			txtDiaChiKs.setFont(new Font("", Font.BOLD, 12));

			lblSdtKs = new JLabel("SDT KS: ");
			txtSdtKs = new JTextField();

			lblSdtKs.setForeground(Color.BLUE);
			lblSdtKs.setFont(new Font("", Font.BOLD, 18));
			lblSdtKs.setForeground(thirdColor);
			txtSdtKs.setPreferredSize(new Dimension(180, 20));
			txtSdtKs.setBorder(roundedBorder);
			txtSdtKs.setFont(new Font("", Font.BOLD, 12));

			lblMaLt = new JLabel("Mã lộ trình: ");
			txtMaLt = new JTextField();

			lblMaLt.setForeground(Color.BLUE);
			lblMaLt.setFont(new Font("", Font.BOLD, 18));
			txtMaLt.setPreferredSize(new Dimension(180, 20));
			txtMaLt.setBorder(roundedBorder);
			txtMaLt.setFont(new Font("", Font.BOLD, 12));

			lblTenLt = new JLabel("Tên lộ trình: ");
			txtTenLt = new JTextField();

			lblTenLt.setForeground(Color.BLUE);
			lblTenLt.setFont(new Font("", Font.BOLD, 18));
			lblTenLt.setForeground(thirdColor);
			txtTenLt.setPreferredSize(new Dimension(180, 20));
			txtTenLt.setBorder(roundedBorder);
			txtTenLt.setFont(new Font("", Font.BOLD, 12));

			lblNgayKH = new JLabel("Ngày khởi hành: ");
			lblNgayKH.setForeground(Color.BLUE);
			lblNgayKH.setFont(new Font("", Font.BOLD, 18));
			lblNgayKH.setForeground(thirdColor);
			ngayKH = new JDateChooser(DATE_FORMAT.toPattern(), "##/##/####", '_');
			ngayKH.setFont(new Font("Tahoma", Font.PLAIN, 13));

			lblNgayKT = new JLabel("Ngày kết thúc: ");
			lblNgayKT.setForeground(Color.BLUE);
			lblNgayKT.setFont(new Font("", Font.BOLD, 18));
			lblNgayKT.setForeground(thirdColor);
			ngayKT = new JDateChooser(DATE_FORMAT.toPattern(), "##/##/####", '_');
			ngayKT.setFont(new Font("Tahoma", Font.PLAIN, 13));

			lblLoaiTour = new JLabel("Loại Tour: ");
			lblLoaiTour.setForeground(Color.BLUE);
			lblLoaiTour.setFont(new Font("", Font.BOLD, 18));
			lblLoaiTour.setForeground(thirdColor);
			rdbSang = new JRadioButton("Sang");
			rdbThuong = new JRadioButton("Thuòng");
			groupLoaiTour = new ButtonGroup();
			groupLoaiTour.add(rdbSang);
			groupLoaiTour.add(rdbThuong);

			lblMaPT = new JLabel("Mã Phương tiện: ");
			txtMaPT = new JTextField();

			lblMaPT.setForeground(Color.BLUE);
			lblMaPT.setFont(new Font("", Font.BOLD, 18));
			txtMaPT.setPreferredSize(new Dimension(177, 20));
			txtMaPT.setBorder(roundedBorder);
			txtMaPT.setFont(new Font("", Font.BOLD, 12));

			lblLoaiPT = new JLabel("Loại phương tiện: ");
			txtLoaiPT = new JTextField();

			lblLoaiPT.setForeground(Color.BLUE);
			lblLoaiPT.setFont(new Font("", Font.BOLD, 18));
			lblLoaiPT.setForeground(thirdColor);
			txtLoaiPT.setPreferredSize(new Dimension(177, 20));
			txtLoaiPT.setBorder(roundedBorder);
			txtLoaiPT.setFont(new Font("", Font.BOLD, 12));

			lblTenTX = new JLabel("Tên tài xế: ");
			txtTenTX = new JTextField();

			lblTenTX.setForeground(Color.BLUE);
			lblTenTX.setFont(new Font("", Font.BOLD, 18));
			lblTenTX.setForeground(thirdColor);
			txtTenTX.setPreferredSize(new Dimension(177, 20));
			txtTenTX.setBorder(roundedBorder);
			txtTenTX.setFont(new Font("", Font.BOLD, 12));

			lblSdtTaiXe = new JLabel("SDT Tài Xế: ");
			txtSdtTaiXe = new JTextField();

			lblSdtTaiXe.setForeground(Color.BLUE);
			lblSdtTaiXe.setFont(new Font("", Font.BOLD, 18));
			lblSdtTaiXe.setForeground(thirdColor);
			txtSdtTaiXe.setPreferredSize(new Dimension(177, 20));
			txtSdtTaiXe.setBorder(roundedBorder);
			txtSdtTaiXe.setFont(new Font("", Font.BOLD, 12));

			pnlCenterNavBar.add(b);

			pnlCenterContentLeft.add(Box.createVerticalStrut(60));
			pnlCenterContentLeft.add(btnTtTour);
			pnlCenterContentLeft.add(Box.createVerticalStrut(60));
			pnlCenterContentLeft.add(btnTtKS);
			pnlCenterContentLeft.add(Box.createVerticalStrut(60));
			pnlCenterContentLeft.add(btnTtLt);
			pnlCenterContentLeft.add(Box.createVerticalStrut(60));
			pnlCenterContentLeft.add(btnTtPt);
			pnlCenterContentLeft.add(Box.createVerticalStrut(60));

			JLabel lblTim = new JLabel("Tìm gần đúng tên tour: ");
			lblTim.setForeground(Color.BLUE);
			lblTim.setFont(new Font("", Font.BOLD, 18));
			lblTim.setForeground(thirdColor);
			txtTim = new JTextField(12);
			txtTim.setPreferredSize(new Dimension(90, 30));
			txtTim.setBorder(roundedBorder);
			txtTim.setFont(new Font("", Font.BOLD, 12));

			btnTim = new JButton("Tìm");
			btnTim.setPreferredSize(new Dimension(90, 50));
			btnTim.setForeground(Color.WHITE);
			btnTim.setOpaque(true);
			btnTim.setBorderPainted(false);
			btnTim.setFocusPainted(false);
			btnTim.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTim.setUI(new RoundedButtonUI_v1());

			btnThem = new JButton("Thêm");
			btnThem.setPreferredSize(new Dimension(90, 50));
			btnThem.setForeground(Color.WHITE);
			btnThem.setOpaque(true);
			btnThem.setBorderPainted(false);
			btnThem.setFocusPainted(false);
			btnThem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnThem.setUI(new RoundedButtonUI_v1());

			btnXoa = new JButton("Xóa");
			btnXoa.setPreferredSize(new Dimension(90, 50));
			btnXoa.setForeground(Color.WHITE);
			btnXoa.setOpaque(true);
			btnXoa.setBorderPainted(false);
			btnXoa.setFocusPainted(false);
			btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnXoa.setUI(new RoundedButtonUI_v1());

			btnSua = new JButton("Sữa");
			btnSua.setPreferredSize(new Dimension(90, 50));
			btnSua.setForeground(Color.WHITE);
			btnSua.setOpaque(true);
			btnSua.setBorderPainted(false);
			btnSua.setFocusPainted(false);
			btnSua.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnSua.setUI(new RoundedButtonUI_v1());

			btnClear = new JButton("Clear");
			btnClear.setPreferredSize(new Dimension(90, 50));
			btnClear.setForeground(Color.WHITE);
			btnClear.setOpaque(true);
			btnClear.setBorderPainted(false);
			btnClear.setFocusPainted(false);
			btnClear.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnClear.setUI(new RoundedButtonUI_v1());

			btnDatTour = new JButton("Đặt tour");
			btnDatTour.setPreferredSize(new Dimension(90, 50));
			btnDatTour.setForeground(Color.WHITE);
			btnDatTour.setOpaque(true);
			btnDatTour.setBorderPainted(false);
			btnDatTour.setFocusPainted(false);
			btnDatTour.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnDatTour.setUI(new RoundedButtonUI_v3());

			pnlCenterFooter.setBackground(secondColor);

			pnlCenterFooter.add(lblTim);
			pnlCenterFooter.add(txtTim);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnTim);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnThem);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnXoa);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnSua);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnClear);
			pnlCenterFooter.add(Box.createHorizontalStrut(20));
			pnlCenterFooter.add(btnDatTour);

			btnTim.addActionListener(this);
			btnThem.addActionListener(this);
			btnXoa.addActionListener(this);
			btnSua.addActionListener(this);
			btnClear.addActionListener(this);
			btnDatTour.addActionListener(this);
			tblTour.addMouseListener(this);
			btnTtTour.addActionListener(this);
			btnTtKS.addActionListener(this);
			btnTtLt.addActionListener(this);
			btnTtPt.addActionListener(this);

			tblTour.setRowSelectionInterval(0, 0);

			int count = 0;
			try {
				tour_dao = new Tour_DAO();
				List<Tour> tours = tour_dao.getAllTours();
				int i = 0;
				for (Tour tour : tours) {

					if (count == i) {
						txtMaT.setText(tour.getMaTour());
						txtTenT.setText(tour.getTenTour());
						String a = String.valueOf(tour.getGiaTour());
						txtGiaT.setText(a);

						txtMaKs.setText(tour.getThongTinKhachSan().getMaKS());
						txtTenKs.setText(tour.getThongTinKhachSan().getTenKS());
						txtDiaChiKs.setText(tour.getThongTinKhachSan().getDiaChi());
						txtSdtKs.setText(tour.getThongTinKhachSan().getSDT());

						txtMaLt.setText(tour.getThongTinLoTrinh().getMaLoTrinh());
						txtTenLt.setText(tour.getThongTinLoTrinh().getTenLoTrinh());
						Instant instant1 = tour.getThongTinLoTrinh().getNgayKhoiHanh().atStartOfDay()
								.atZone(ZoneId.systemDefault()).toInstant();
						java.util.Date date1 = Date.from(instant1);
						ngayKH.setDate(date1);
						Instant instant2 = tour.getThongTinLoTrinh().getNgayKetThuc().atStartOfDay()
								.atZone(ZoneId.systemDefault()).toInstant();
						java.util.Date date2 = Date.from(instant2);
						ngayKT.setDate(date2);
						boolean tmp = tour.getThongTinLoTrinh().isLoaiTour();
						if (tmp) {
							rdbSang.setSelected(true);
							rdbThuong.setSelected(false);
						} else {
							rdbThuong.setSelected(true);
							rdbSang.setSelected(false);
						}

						txtMaPT.setText(tour.getThongTinPhuongTien().getMaPhuongTien());
						txtLoaiPT.setText(tour.getThongTinPhuongTien().getLoaiPhuongTien());
						txtTenTX.setText(tour.getThongTinPhuongTien().getTenTaiXe());
						txtSdtTaiXe.setText(tour.getThongTinPhuongTien().getSDT());

						hinhAnh = "media\\\\" + tour.getMaTour() + ".png";

						btnTtTour.doClick();
					}
					i++;
				}

			} catch (Exception e2) {
			}

			txtMaT.setEditable(false);
			txtMaKs.setEditable(false);
			txtMaLt.setEditable(false);
			txtMaPT.setEditable(false);
		}

		public void clearDaTaOnModel() {
			DefaultTableModel dtm = (DefaultTableModel) tblTour.getModel();
			dtm.getDataVector().removeAllElements();
		}

		public void clearTextField() {
			txtMaT.setText("");
			txtTenT.setText("");
			txtGiaT.setText("");

			txtMaKs.setText("");
			txtTenKs.setText("");
			txtDiaChiKs.setText("");
			txtSdtKs.setText("");

			txtMaLt.setText("");
			txtTenLt.setText("");
			ngayKH.setDate(null);
			ngayKT.setDate(null);
			rdbThuong.setSelected(true);

			txtMaPT.setText("");
			txtLoaiPT.setText("");
			txtTenTX.setText("");
			txtSdtTaiXe.setText("");

			txtTim.setText("");

			bkMaKH = null;
			bkTenKH = null;
			bkSdtKh = null;
			bkEmail = null;
			bkCccd = null;
			bkHoChieu = false;
			bkSex = false;

			bkMaT = null;
			bkTenT = null;
			bkGiaTour = 0;

			bkMaKs = null;
			bkTenKs = null;
			bkDiaChiKs = null;
			bkSdtKs = null;

			bkMaLt = null;
			bkTenLt = null;

			bkNgayKH = null;
			bkNgayKt = null;

			bkLoaiTour = false;

			bkMaPT = null;
			bkLoaiPT = null;
			bkTenTX = null;
			bkSdtTaiXe = null;

		}

		private void DocDuLieuDatabaseVaoTable() {
			DefaultTableModel model = (DefaultTableModel) tblTour.getModel();
			model.setRowCount(0);

			try {
				tour_dao = new Tour_DAO();
				khachsan_dao = new KhachSan_DAO();
				lotrinh_dao = new LoTrinh_DAO();
				phuongtien_dao = new PhuongTien_DAO();

				List<Tour> tours = tour_dao.getAllTours();
				for (Tour tour : tours) {
					Object[] row = new Object[6];
					row[0] = tour.getMaTour();
					row[1] = tour.getTenTour();

					Locale locale = new Locale("vi", "VN"); // định dạng địa phương cho tiền Việt Nam
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
					String formattedMoney = currencyFormatter.format(tour.getGiaTour());

					row[2] = formattedMoney;
					row[3] = tour.getThongTinKhachSan().getTenKS() + " " + tour.getThongTinKhachSan().getDiaChi();
					row[4] = tour.getThongTinLoTrinh().getTenLoTrinh() + " "
							+ tour.getThongTinLoTrinh().getNgayKhoiHanh() + " "
							+ tour.getThongTinLoTrinh().getNgayKetThuc();
					row[5] = tour.getThongTinPhuongTien().getLoaiPhuongTien();
					model.addRow(row);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();

			}
		}

		public String phatSinhMaT() {

			List<Tour> ts1 = null;
			try {
				ts1 = tour_dao.getAllTours();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			List<Tour> ts = ts1;
			String temp = null;
			for (Tour t : ts) {
				temp = t.getMaTour();
			}
			int count = laySoDuoiT(temp);

			count++;

			String maVe = String.format("T%03d", count);

			return maVe;
		}

		public int laySoDuoiT(String str) {
			if (str == null) {
				return 0;
			} else {
				String numberStr = str.substring(1); // loại bỏ ký tự "V"
				int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
				return number;
			}

		}

		public String phatSinhMaLT() {
			List<LoTrinh> lts = lotrinh_dao.getAllLoTrinh();
			String temp = null;
			for (LoTrinh lt : lts) {
				temp = lt.getMaLoTrinh();
			}
			int count = laySoDuoiLT(temp);

			count++;

			String maVe = String.format("LT%03d", count);

			return maVe;
		}

		public int laySoDuoiLT(String str) {
			if (str == null) {
				return 0;
			} else {
				String numberStr = str.substring(2); // loại bỏ ký tự "V"
				int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
				return number;
			}

		}

		public String phatSinhMaPT() {
			List<PhuongTien> pts = phuongtien_dao.getAllPhuongTien();
			String temp = null;
			for (PhuongTien pt : pts) {
				temp = pt.getMaPhuongTien();
			}
			int count = laySoDuoiPT(temp);

			count++;

			String maVe = String.format("PT%03d", count);

			return maVe;
		}

		public int laySoDuoiPT(String str) {
			if (str == null) {
				return 0;
			} else {
				String numberStr = str.substring(2); // loại bỏ ký tự "V"
				int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
				return number;
			}

		}

		public String phatSinhMaKS() {
			List<KhachSan> kss = khachsan_dao.getalltbKhachSan();
			String temp = null;
			for (KhachSan ks : kss) {
				temp = ks.getMaKS();
			}
			int count = laySoDuoiKS(temp);

			count++;

			String maVe = String.format("KS%03d", count);

			return maVe;
		}

		public int laySoDuoiKS(String str) {
			if (str == null) {
				return 0;
			} else {
				String numberStr = str.substring(2); // loại bỏ ký tự "V"
				int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
				return number;
			}

		}

		private boolean validDataLT() {
			// Lấy giá trị các trường và loại bỏ khoảng trắng thừa
			String maLoTrinh = phatSinhMaLT().trim();
			String tenLoTrinh = txtTenLt.getText().trim();
			LocalDate ngayKhoiHanh = null;
			LocalDate ngayKetThuc = null;

			if (ngayKH.getDate() != null) {
				Instant instantKhoiHanh = ngayKH.getDate().toInstant();
				ngayKhoiHanh = instantKhoiHanh.atZone(ZoneId.systemDefault()).toLocalDate();
			}

			if (ngayKT.getDate() != null) {
				Instant instantKetThuc = ngayKT.getDate().toInstant();
				ngayKetThuc = instantKetThuc.atZone(ZoneId.systemDefault()).toLocalDate();
			}

			@SuppressWarnings("unused")
			boolean loaiTour = rdbThuong.isSelected();

			// Kiểm tra mã lộ trình
			if (maLoTrinh.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Mã lộ trình không được để trống!!!");
				return false;
			}

			// Kiểm tra tên lộ trình
			if (tenLoTrinh.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Tên lộ trình không được để trống!!!");
				return false;
			}

			// Kiểm tra ngày khởi hành
			if (ngayKhoiHanh == null) {
				CustomJOptionPane.showMessageDialog("Ngày khởi hành không được để trống!!!");
				return false;
			}

			// Kiểm tra ngày kết thúc
			if (ngayKetThuc == null) {
				CustomJOptionPane.showMessageDialog("Ngày kết thúc không được để trống!!!");
				return false;
			}

			// Kiểm tra ngày kết thúc có lớn hơn ngày khởi hành hay không
			if (ngayKetThuc.isBefore(ngayKhoiHanh)) {
				CustomJOptionPane.showMessageDialog("Ngày kết thúc phải lớn hơn hoặc bằng ngày khởi hành!!!");
				return false;
			}

			// Các giá trị đều hợp lệ
			return true;
		}

		private boolean validDataPT() {
			// Lấy giá trị các trường và loại bỏ khoảng trắng thừa
			String maPT = phatSinhMaPT().trim();
			String tenTX = txtTenTX.getText().trim();
			String sdtTX = txtSdtTaiXe.getText().trim();
			String loaiPT = txtLoaiPT.getText().trim();

			// Kiểm tra mã phương tiện
			if (maPT.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Mã phương tiện không được để trống!!!");
				return false;
			}

			// Kiểm tra định dạng mã phương tiện
			if (!maPT.matches("^PT\\d{3}$")) {
				CustomJOptionPane.showMessageDialog("Mã phương tiện phải có định dạng PTXXX, trong đó XXX là 3 ký số!!!");
				return false;
			}

			// Kiểm tra tên tài xế
			if (tenTX.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Tên tài xế  không được để trống!!!");
				return false;
			}
			// Kiểm tra loại phương tiện
			if (loaiPT.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Loại phương tiện  không được để trống!!!");
				return false;
			}

			// Kiểm tra số điện thoại
			if (sdtTX.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Số điện thoại tài xế  không được để trống!!!");
				return false;
			}

			// Kiểm tra định dạng số điện thoại
			if (!sdtTX.matches("^\\d{10}$")) {
				CustomJOptionPane.showMessageDialog("Số điện thoại tài xế phải có 10 chữ số!!!");
				return false;
			}

			// Các giá trị đều hợp lệ
			return true;
		}

		private boolean validDataTout() {
			// Lấy giá trị các trường và loại bỏ khoảng trắng thừa
			String maTour = phatSinhMaT().trim();
			String tenTour = txtTenT.getText().trim();
			String giaTour = txtGiaT.getText().trim();

			// Kiểm tra mã tour
			if (maTour.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Mã tour không được để trống!!!");
				return false;
			}

			// Kiểm tra mã tour bắt đầu bằng chữ "T"
			if (!maTour.matches("^T\\d{3}$")) {
				CustomJOptionPane.showMessageDialog("Mã tour phải có định dạng TXXX, trong đó XXX là 3 ký số!!!");
				return false;
			}
			// Kiểm tra tên tour
			if (tenTour.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Tên tour không được để trống!!!");
				return false;
			}
			// Kiểm tra giá tour là số dương
			try {
				double gia = Double.parseDouble(giaTour);
				if (gia <= 0) {
					CustomJOptionPane.showMessageDialog("Giá tour phải là số dương!!!");
					return false;
				}
			} catch (NumberFormatException e) {
				CustomJOptionPane.showMessageDialog("Giá tour không hợp lệ!!!");
				return false;
			}

			// Các giá trị đều hợp lệ
			return true;
		}

		private Tour revertTourFromTextfields() {
			String maTour = phatSinhMaT().trim();
			String tenTour = txtTenT.getText().trim();

			List<KhachSan> kss = khachsan_dao.getalltbKhachSan();
			String temp1 = null;
			for (KhachSan ks : kss) {
				temp1 = ks.getMaKS();
			}
			String maKS = temp1;

			List<LoTrinh> lts = lotrinh_dao.getAllLoTrinh();
			String temp2 = null;
			for (LoTrinh lt : lts) {
				temp2 = lt.getMaLoTrinh();
			}
			String maLT = temp2;

			List<PhuongTien> pts = phuongtien_dao.getAllPhuongTien();
			String temp3 = null;
			for (PhuongTien pt : pts) {
				temp3 = pt.getMaPhuongTien();
			}
			String maPT = temp3;

			String gia = txtGiaT.getText().trim();
			double giaTour = gia.equals("") ? 0 : Double.parseDouble(gia);

			KhachSan khachSan = new KhachSan(maKS, txtTenKs.getText(), txtDiaChiKs.getText().trim(),
					txtSdtKs.getText().trim());

			PhuongTien pTien = new PhuongTien(maPT, txtLoaiPT.getText().trim(), txtTenTX.getText().trim(),
					txtSdtTaiXe.getText().trim());

			Instant instant = ngayKH.getDate().toInstant();
			LocalDate ngayKhoiHanh = instant.atZone(ZoneId.systemDefault()).toLocalDate();

			Instant instant2 = ngayKT.getDate().toInstant();
			LocalDate ngayKetThuc = instant2.atZone(ZoneId.systemDefault()).toLocalDate();
			boolean loai = rdbThuong.isSelected();
			LoTrinh lTrinh = new LoTrinh(maLT, txtTenLt.getText().trim(), ngayKhoiHanh, ngayKetThuc, loai);

			return new Tour(maTour, tenTour, giaTour, khachSan, pTien, lTrinh);
		}

		private Tour revertTourFromTextfields2() {
			String maTour = txtMaT.getText().trim();
			String tenTour = txtTenT.getText().trim();

//			List<KhachSan> kss = khachsan_dao.getalltbKhachSan();
//			String temp1 = null;
//			for (KhachSan ks : kss) {
//				temp1 = ks.getMaKS();
//			}
//			String maKS = temp1;
//
//			List<LoTrinh> lts = lotrinh_dao.getAllLoTrinh();
//			String temp2 = null;
//			for (LoTrinh lt : lts) {
//				temp2 = lt.getMaLoTrinh();
//			}
//			String maLT = temp2;
//
//			List<PhuongTien> pts = phuongtien_dao.getAllPhuongTien();
//			String temp3 = null;
//			for (PhuongTien pt : pts) {
//				temp3 = pt.getMaPhuongTien();
//			}
//			String maPT = temp3;

			String gia = txtGiaT.getText().trim();
			double giaTour = gia.equals("") ? 0 : Double.parseDouble(gia);

			KhachSan khachSan = new KhachSan(txtMaKs.getText(), txtTenKs.getText(), txtDiaChiKs.getText().trim(),
					txtSdtKs.getText().trim());

			PhuongTien pTien = new PhuongTien(txtMaPT.getText(), txtLoaiPT.getText().trim(), txtTenTX.getText().trim(),
					txtSdtTaiXe.getText().trim());

			Instant instant = ngayKH.getDate().toInstant();
			LocalDate ngayKhoiHanh = instant.atZone(ZoneId.systemDefault()).toLocalDate();

			Instant instant2 = ngayKT.getDate().toInstant();
			LocalDate ngayKetThuc = instant2.atZone(ZoneId.systemDefault()).toLocalDate();
			boolean loai = rdbThuong.isSelected();
			LoTrinh lTrinh = new LoTrinh(txtMaLt.getText(), txtTenLt.getText().trim(), ngayKhoiHanh, ngayKetThuc, loai);

			return new Tour(maTour, tenTour, giaTour, khachSan, pTien, lTrinh);
		}

		private boolean validDataTout2() {
			// Lấy giá trị các trường và loại bỏ khoảng trắng thừa
			String maTour = txtMaT.getText().trim();
			String tenTour = txtTenT.getText().trim();
			String giaTour = txtGiaT.getText().trim();

			// Kiểm tra mã tour
			if (maTour.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Mã tour không được để trống.");
				return false;
			}

			// Kiểm tra mã tour bắt đầu bằng chữ "T"
			if (!maTour.matches("^T\\d{3}$")) {
				CustomJOptionPane.showMessageDialog("Mã tour phải có định dạng TXXX, trong đó XXX là 3 ký số!!!");
				return false;
			}
			// Kiểm tra tên tour
			if (tenTour.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Tên tour không được để trống!!!");
				return false;
			}
			// Kiểm tra giá tour là số dương
			try {
				double gia = Double.parseDouble(giaTour);
				if (gia <= 0) {
					CustomJOptionPane.showMessageDialog("Giá tour phải là số dương!!!");
					return false;
				}
			} catch (NumberFormatException e) {
				CustomJOptionPane.showMessageDialog("Giá tour không hợp lệ!!!");
				return false;
			}

			// Các giá trị đều hợp lệ
			return true;
		}

		private PhuongTien revertPhuongTienFromTextfields() {
			String maPT = phatSinhMaPT().trim();
			String tenTX = txtTenTX.getText().trim();
			String sdtTX = txtSdtTaiXe.getText().trim();
			String loaiPT = txtLoaiPT.getText().trim();

			return new PhuongTien(maPT, loaiPT, tenTX, sdtTX);
		}

		private LoTrinh revertLoTrinhFromTextfields() {
			String maLoTrinh = phatSinhMaLT();
			String tenLoTrinh = txtTenLt.getText().trim();

			LocalDate ngayKhoiHanh = null;
			LocalDate ngayKetThuc = null;

			if (ngayKH.getDate() != null) {
				ngayKhoiHanh = ngayKH.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}

			if (ngayKT.getDate() != null) {
				ngayKetThuc = ngayKT.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}

			boolean loaiTour = rdbThuong.isSelected();

			return new LoTrinh(maLoTrinh, tenLoTrinh, ngayKhoiHanh, ngayKetThuc, loaiTour);
		}

		private boolean validDataKS() {
			// Lấy giá trị các trường và loại bỏ khoảng trắng thừa
			String maKS = phatSinhMaKS().trim();
			String tenKS = txtTenKs.getText().trim();
			String diaChi = txtDiaChiKs.getText().trim();
			String SDT = txtSdtKs.getText().trim();

			// Kiểm tra mã khách sạn
			if (maKS.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Mã khách sạn không được để trống!!!");
				return false;
			}

			// Kiểm tra định dạng mã khách sạn
			if (!maKS.matches("^KS\\d{3}$")) {
				CustomJOptionPane.showMessageDialog("Mã khách sạn phải có định dạng KSXXX, trong đó XXX là 3 ký số!!!");
				return false;
			}

			// Kiểm tra tên khách sạn
			if (tenKS.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Tên khách sạn không được để trống!!!");
				return false;
			}

			// Kiểm tra địa chỉ
			if (diaChi.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Địa chỉ không được để trống!!!");
				return false;
			}

			// Kiểm tra số điện thoại
			if (SDT.isEmpty()) {
				CustomJOptionPane.showMessageDialog("Số điện thoại khách sạn không được để trống!!!");
				return false;
			}

			// Kiểm tra định dạng số điện thoại
			if (!SDT.matches("^\\d{10}$")) {
				CustomJOptionPane.showMessageDialog("Số điện thoại khach sạn phải có 10 chữ số!!!");
				return false;
			}

			// Các giá trị đều hợp lệ
			return true;
		}

		private KhachSan revertKhachSanFromTextfields() {
			String maKS = phatSinhMaKS().trim();
			String tenKS = txtTenKs.getText().trim();
			String diaChi = txtDiaChiKs.getText().trim();
			String sdt = txtSdtKs.getText().trim();
			return new KhachSan(maKS, tenKS, diaChi, sdt);
		}

		@SuppressWarnings("unused")
		private Tour createFormTour() {
			KhachSan ks;
			ks = new KhachSan();
			LoTrinh lt;
			lt = new LoTrinh();
			Tour t;
			t = new Tour();

			return t;
		}

		private int levenshteinDistance(String s1, String s2) {
			int[][] dp = new int[s1.length() + 1][s2.length() + 1];
			for (int i = 0; i <= s1.length(); i++) {
				dp[i][0] = i;
			}
			for (int j = 0; j <= s2.length(); j++) {
				dp[0][j] = j;
			}
			for (int i = 1; i <= s1.length(); i++) {
				for (int j = 1; j <= s2.length(); j++) {
					if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
					}
				}
			}
			return dp[s1.length()][s2.length()];
		}

		private boolean checkMa() {
			tour_dao = new Tour_DAO();
			List<Tour> tours = null;
			try {
				tours = tour_dao.getAllTours();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (Tour tour : tours) {
				if (tour.getMaTour().equals(txtMaT.getText())) {
					return false;
				}
			}
			return true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();

			if (obj == btnTim) {
				String searchString = txtTim.getText();
				if (!(searchString.length() > 0)) {
					CustomJOptionPane.showMessageDialog("vui lòng nhập đầy đủ!!!");
				} else {
					try {
						tour_dao = new Tour_DAO();
						List<Tour> tours = tour_dao.getAllTours();
						Tour closestTour = null;
						int minDistance = Integer.MAX_VALUE;
						for (Tour tour : tours) {
							int distance = levenshteinDistance(searchString, tour.getTenTour());
							if (distance < minDistance) {
								closestTour = tour;
								minDistance = distance;
							}
						}

						clearDaTaOnModel();
						Object[] row = new Object[6];
						row[0] = closestTour.getMaTour();
						row[1] = closestTour.getTenTour();
						row[2] = closestTour.getGiaTour();
						row[3] = closestTour.getThongTinKhachSan().getTenKS() + " "
								+ closestTour.getThongTinKhachSan().getDiaChi() + " "
								+ closestTour.getThongTinKhachSan().getMaKS();
						row[4] = closestTour.getThongTinLoTrinh().getTenLoTrinh() + " "
								+ closestTour.getThongTinLoTrinh().getNgayKhoiHanh() + " "
								+ closestTour.getThongTinLoTrinh().getNgayKetThuc();
						row[5] = closestTour.getThongTinPhuongTien().getLoaiPhuongTien();

						dtmTour.addRow(row);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}

			if (obj == btnThem) {
				// Kiểm tra tính hợp lệ của dữ liệu cho khách sạn, lộ trình, phương tiện và tour
				boolean isKhachSanValid = validDataKS();
				boolean isLoTrinhValid = validDataLT();
				boolean isPhuongTienValid = validDataPT();
				boolean isTourValid = validDataTout();

				// Kiểm tra nếu tất cả dữ liệu hợp lệ, thêm vào cơ sở dữ liệu
				if (isKhachSanValid && isLoTrinhValid && isPhuongTienValid && isTourValid) {
					// Thêm thông tin vào cơ sở dữ liệu
					KhachSan khachSan = revertKhachSanFromTextfields();
					khachsan_dao.create(khachSan);

					LoTrinh loTrinh = revertLoTrinhFromTextfields();
					lotrinh_dao.create(loTrinh);

					PhuongTien phuongTien = revertPhuongTienFromTextfields();
					phuongtien_dao.create(phuongTien);

					Tour tourr = revertTourFromTextfields();

					if (checkMa()) {
						try {
							tour_dao.addTour(tourr);

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// Thông báo thành công và làm sạch dữ liệu trên mô hình
						clearDaTaOnModel();
						clearTextField();
						tblTour.clearSelection();
						DocDuLieuDatabaseVaoTable();
					} else {
						CustomJOptionPane.showMessageDialog("Trùng mã!!!");
					}

				}
			}

			if (obj == btnXoa) {

				ve_dao = new ThongTinDatTour_DAO();
				if (tblTour.getSelectedRowCount() > 0) {

					if (JOptionPane.showConfirmDialog(this, "Xác nhận các dòng đã chọn" + " !!!", "Warring",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {

						int[] selectedRows = tblTour.getSelectedRows();
						for (int i = selectedRows.length - 1; i >= 0; i--) {
							List<Tour> tours = null;
							try {
								tours = tour_dao.getAllTours();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							Tour tour = tours.get(selectedRows[i]);

							String maKS = tour.getThongTinKhachSan().getMaKS();
							String maLt = tour.getThongTinLoTrinh().getMaLoTrinh();
							String maPt = tour.getThongTinPhuongTien().getMaPhuongTien();

							try {
								List<thongTinDatTour> ves = ve_dao.getAllVe();
								for (thongTinDatTour ve : ves) {
									if (ve.getThongTinTour().getMaTour().equals(tour.getMaTour())) {
										String maVe = ve.getMaVe();
										ve_dao.xoaVe(maVe);
									}
								}
							} catch (Exception e2) {
							}
							try {
								tour_dao.deleteTour(tour.getMaTour());
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

							khachsan_dao.delete(maKS);
							lotrinh_dao.delete(maLt);
							phuongtien_dao.delete(maPt);

						}
						clearDaTaOnModel();
						DocDuLieuDatabaseVaoTable();
						CustomJOptionPane.showMessageDialog("Xoá thành công!!!");

					}

				} else
					CustomJOptionPane.showMessageDialog("Chọn dòng cần xoá!!!");

			}

			if (obj == btnSua) {
				int selectedRow = tblTour.getSelectedRow();
				if (selectedRow >= 0) {
					Tour tour = revertTourFromTextfields2();
					if (validDataTout2()) {
						try {
							// Update tour in the database
							tour_dao.updateTour(tour);

							// Clear data in the model and reload data from the database into the table
							clearDaTaOnModel();
							DocDuLieuDatabaseVaoTable();
							clearTextField();
							tblTour.clearSelection();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						CustomJOptionPane.showMessageDialog("Vui lòng nhập đầy đủ thông tin!!!");
					}
				} else {
					CustomJOptionPane.showMessageDialog("Chọn dòng cần sửa!!!");
				}
			}

			if (obj == btnClear) {
				clearTextField();
				clearDaTaOnModel();
				tblTour.clearSelection();
				DocDuLieuDatabaseVaoTable();
				txtMaT.requestFocus();

			}

			if (obj == btnDatTour) {

				if (bkMaKH == null) {
					CustomJOptionPane.showMessageDialog("Chưa chọn khách hàng!!");
					btnKhachHang.doClick();

				} else {

					if (txtMaT.getText().equals("")) {
						CustomJOptionPane.showMessageDialog("Chưa chọn Tour!!!");

					} else {

						bkMaT = txtMaT.getText();
						bkTenT = txtTenT.getText();
						bkGiaTour = Float.parseFloat(txtGiaT.getText());

						bkMaKs = txtMaKs.getText();
						bkTenKs = txtTenKs.getText();
						bkDiaChiKs = txtDiaChiKs.getText();
						bkSdtKs = txtSdtKs.getText();

						bkMaLt = txtMaLt.getText();
						bkTenLt = txtTenLt.getText();

						java.util.Date date = ngayKH.getDate();
						Instant instant = date.toInstant();
						ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
						bkNgayKH = zonedDateTime.toLocalDate();

						java.util.Date date2 = ngayKT.getDate();
						Instant instant2 = date2.toInstant();
						ZonedDateTime zonedDateTime2 = instant2.atZone(ZoneId.systemDefault());
						bkNgayKt = zonedDateTime2.toLocalDate();

						bkLoaiTour = rdbThuong.isSelected();

						bkMaPT = txtMaLt.getText();
						bkLoaiPT = txtLoaiPT.getText();
						bkTenTX = txtTenTX.getText();
						bkSdtTaiXe = txtSdtTaiXe.getText();

						btnBooking.doClick();
					}

				}

			}

			if (obj == btnTtTour) {
				pnlCenterContentRight.removeAll();
				pnlCenterContentRight.revalidate();
				pnlCenterContentRight.repaint();

				btnTtTour.setForeground(thirdColor);
				btnTtKS.setForeground(secondColor);
				btnTtLt.setForeground(secondColor);
				btnTtPt.setForeground(secondColor);

				ImageIcon image1 = new ImageIcon(hinhAnh);
				Image img1 = image1.getImage();
				Image newImg = img1.getScaledInstance(450, 280, java.awt.Image.SCALE_SMOOTH);
				image1 = new ImageIcon(newImg);
				JLabel lblHinhAnh = new JLabel();
				lblHinhAnh.setIcon(image1);
				pnlCenterContentRight.add(lblHinhAnh, BorderLayout.WEST);

				Box bt = Box.createVerticalBox();
				Box b3 = Box.createHorizontalBox();
				Box b4 = Box.createHorizontalBox();
				Box b5 = Box.createHorizontalBox();
				pnlCenterContentRight.add(bt, BorderLayout.EAST);

				b3.add(Box.createHorizontalStrut(20));
				b3.add(lblMaT);
				lblMaT.setPreferredSize(lblLoaiPT.getPreferredSize());
				b3.add(txtMaT);
				b4.add(Box.createHorizontalStrut(20));
				b4.add(lblTenT);
				lblTenT.setPreferredSize(lblLoaiPT.getPreferredSize());
				b4.add(txtTenT);
				b5.add(Box.createHorizontalStrut(20));
				b5.add(lblGiaT);
				lblGiaT.setPreferredSize(lblLoaiPT.getPreferredSize());
				b5.add(txtGiaT);

				bt.add(b3);
				bt.add(Box.createVerticalStrut(30));
				bt.add(b4);
				bt.add(Box.createVerticalStrut(30));
				bt.add(b5);
			}

			if (obj == btnTtKS) {
				pnlCenterContentRight.removeAll();
				pnlCenterContentRight.revalidate();
				pnlCenterContentRight.repaint();

				btnTtTour.setForeground(secondColor);
				btnTtKS.setForeground(thirdColor);
				btnTtLt.setForeground(secondColor);
				btnTtPt.setForeground(secondColor);

				ImageIcon image1 = new ImageIcon(hinhAnh);
				Image img1 = image1.getImage();
				Image newImg = img1.getScaledInstance(450, 280, java.awt.Image.SCALE_SMOOTH);
				image1 = new ImageIcon(newImg);
				JLabel lblHinhAnh = new JLabel();
				lblHinhAnh.setIcon(image1);
				pnlCenterContentRight.add(lblHinhAnh, BorderLayout.WEST);

				Box bks = Box.createVerticalBox();
				Box b6 = Box.createHorizontalBox();
				Box b7 = Box.createHorizontalBox();
				Box b8 = Box.createHorizontalBox();
				Box b9 = Box.createHorizontalBox();
				pnlCenterContentRight.add(bks, BorderLayout.EAST);

				b6.add(Box.createHorizontalStrut(20));
				b6.add(lblMaKs);
				lblMaKs.setPreferredSize(lblLoaiPT.getPreferredSize());
				b6.add(txtMaKs);
				b7.add(Box.createHorizontalStrut(20));
				b7.add(lblTenKs);
				lblTenKs.setPreferredSize(lblLoaiPT.getPreferredSize());
				b7.add(txtTenKs);
				b8.add(Box.createHorizontalStrut(20));
				b8.add(lblDiaChiKs);
				lblDiaChiKs.setPreferredSize(lblLoaiPT.getPreferredSize());
				b8.add(txtDiaChiKs);
				b9.add(Box.createHorizontalStrut(20));
				b9.add(lblSdtKs);
				lblSdtKs.setPreferredSize(lblLoaiPT.getPreferredSize());
				b9.add(txtSdtKs);

				bks.add(b6);
				bks.add(Box.createVerticalStrut(30));
				bks.add(b7);
				bks.add(Box.createVerticalStrut(30));
				bks.add(b8);
				bks.add(Box.createVerticalStrut(30));
				bks.add(b9);
			}

			if (obj == btnTtLt) {
				pnlCenterContentRight.removeAll();
				pnlCenterContentRight.revalidate();
				pnlCenterContentRight.repaint();

				btnTtTour.setForeground(secondColor);
				btnTtKS.setForeground(secondColor);
				btnTtLt.setForeground(thirdColor);
				btnTtPt.setForeground(secondColor);

				ImageIcon image1 = new ImageIcon(hinhAnh);
				Image img1 = image1.getImage();
				Image newImg = img1.getScaledInstance(450, 280, java.awt.Image.SCALE_SMOOTH);
				image1 = new ImageIcon(newImg);
				JLabel lblHinhAnh = new JLabel();
				lblHinhAnh.setIcon(image1);
				pnlCenterContentRight.add(lblHinhAnh, BorderLayout.WEST);

				Box blt = Box.createVerticalBox();
				Box b10 = Box.createHorizontalBox();
				Box b11 = Box.createHorizontalBox();
				Box b12 = Box.createHorizontalBox();
				Box b13 = Box.createHorizontalBox();
				Box b14 = Box.createHorizontalBox();
				pnlCenterContentRight.add(blt, BorderLayout.EAST);

				b10.add(Box.createHorizontalStrut(20));
				b10.add(lblMaLt);
				lblMaLt.setPreferredSize(lblLoaiPT.getPreferredSize());
				b10.add(txtMaLt);
				b11.add(Box.createHorizontalStrut(20));
				b11.add(lblTenLt);
				lblTenLt.setPreferredSize(lblLoaiPT.getPreferredSize());
				b11.add(txtTenLt);
				b12.add(Box.createHorizontalStrut(20));
				b12.add(lblNgayKH);
				lblNgayKH.setPreferredSize(lblLoaiPT.getPreferredSize());
				b12.add(ngayKH);
				b13.add(Box.createHorizontalStrut(20));
				b13.add(lblNgayKT);
				lblNgayKT.setPreferredSize(lblLoaiPT.getPreferredSize());
				b13.add(ngayKT);
				b14.add(Box.createHorizontalStrut(20));
				b14.add(lblLoaiTour);
				lblLoaiTour.setPreferredSize(lblLoaiPT.getPreferredSize());
				b14.add(rdbSang);
				b14.add(rdbThuong);
				rdbThuong.setSelected(true);

				blt.add(b10);
				blt.add(Box.createVerticalStrut(30));
				blt.add(b11);
				blt.add(Box.createVerticalStrut(30));
				blt.add(b12);
				blt.add(Box.createVerticalStrut(30));
				blt.add(b13);
				blt.add(Box.createVerticalStrut(30));
				blt.add(b14);
			}

			if (obj == btnTtPt) {
				pnlCenterContentRight.removeAll();
				pnlCenterContentRight.revalidate();
				pnlCenterContentRight.repaint();

				btnTtTour.setForeground(secondColor);
				btnTtKS.setForeground(secondColor);
				btnTtLt.setForeground(secondColor);
				btnTtPt.setForeground(thirdColor);

				ImageIcon image1 = new ImageIcon(hinhAnh);
				Image img1 = image1.getImage();
				Image newImg = img1.getScaledInstance(450, 280, java.awt.Image.SCALE_SMOOTH);
				image1 = new ImageIcon(newImg);
				JLabel lblHinhAnh = new JLabel();
				lblHinhAnh.setIcon(image1);
				pnlCenterContentRight.add(lblHinhAnh, BorderLayout.WEST);

				Box bpt = Box.createVerticalBox();
				Box b15 = Box.createHorizontalBox();
				Box b16 = Box.createHorizontalBox();
				Box b17 = Box.createHorizontalBox();
				Box b18 = Box.createHorizontalBox();
				pnlCenterContentRight.add(bpt, BorderLayout.EAST);

				b15.add(Box.createHorizontalStrut(20));
				b15.add(lblMaPT);
				lblMaPT.setPreferredSize(lblLoaiPT.getPreferredSize());
				b15.add(txtMaPT);
				b16.add(Box.createHorizontalStrut(20));
				b16.add(lblLoaiPT);
				b16.add(txtLoaiPT);
				b17.add(Box.createHorizontalStrut(20));
				b17.add(lblTenTX);
				lblTenTX.setPreferredSize(lblLoaiPT.getPreferredSize());
				b17.add(txtTenTX);
				b18.add(Box.createHorizontalStrut(20));
				b18.add(lblSdtTaiXe);
				lblSdtTaiXe.setPreferredSize(lblLoaiPT.getPreferredSize());
				b18.add(txtSdtTaiXe);

				bpt.add(b15);
				bpt.add(Box.createVerticalStrut(30));
				bpt.add(b16);
				bpt.add(Box.createVerticalStrut(30));
				bpt.add(b17);
				bpt.add(Box.createVerticalStrut(30));
				bpt.add(b18);
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tblTour.getSelectedRow();

			try {
				tour_dao = new Tour_DAO();
				List<Tour> tours = tour_dao.getAllTours();
				int i = 0;
				for (Tour tour : tours) {

					if (row == i) {
						txtMaT.setText(tour.getMaTour());
						txtTenT.setText(tour.getTenTour());
						String a = String.valueOf(tour.getGiaTour());
						txtGiaT.setText(a);

						txtMaKs.setText(tour.getThongTinKhachSan().getMaKS());
						txtTenKs.setText(tour.getThongTinKhachSan().getTenKS());
						txtDiaChiKs.setText(tour.getThongTinKhachSan().getDiaChi());
						txtSdtKs.setText(tour.getThongTinKhachSan().getSDT());

						txtMaLt.setText(tour.getThongTinLoTrinh().getMaLoTrinh());
						txtTenLt.setText(tour.getThongTinLoTrinh().getTenLoTrinh());
						Instant instant1 = tour.getThongTinLoTrinh().getNgayKhoiHanh().atStartOfDay()
								.atZone(ZoneId.systemDefault()).toInstant();
						java.util.Date date1 = Date.from(instant1);
						ngayKH.setDate(date1);
						Instant instant2 = tour.getThongTinLoTrinh().getNgayKetThuc().atStartOfDay()
								.atZone(ZoneId.systemDefault()).toInstant();
						java.util.Date date2 = Date.from(instant2);
						ngayKT.setDate(date2);
						boolean b = tour.getThongTinLoTrinh().isLoaiTour();
						if (b) {
							rdbSang.setSelected(true);
							rdbThuong.setSelected(false);
						} else {
							rdbThuong.setSelected(true);
							rdbSang.setSelected(false);
						}

						txtMaPT.setText(tour.getThongTinPhuongTien().getMaPhuongTien());
						txtLoaiPT.setText(tour.getThongTinPhuongTien().getLoaiPhuongTien());
						txtTenTX.setText(tour.getThongTinPhuongTien().getTenTaiXe());
						txtSdtTaiXe.setText(tour.getThongTinPhuongTien().getSDT());

						hinhAnh = "media\\\\" + tour.getMaTour() + ".png";

						btnTtTour.doClick();
					}
					i++;
				}

			} catch (Exception e2) {
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}

	////////////////////////////// Panel Booking

	private class bookingQLTT extends JPanel implements ActionListener, MouseListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private JPanel pnlCenterNavBar, pnlCenterFooter, pnlCenterContentLeft, pnlCenterContentRight;

		private JTable tblBooking;
		private DefaultTableModel dtmBooking;

		private JLabel lblMaKH;
		private JLabel lblTenKH;
		private JLabel lblSdt;
		private JLabel lblEmail;
		private JLabel lblCccd;
		private JLabel lblHoChieu;
		private JLabel lblGioiTinh;

		private JTextField txtMaKH;
		private JTextField txtTenKH;
		private JTextField txtSdt;
		private JTextField txtEmail;
		private JTextField txtCccd;

		private JRadioButton rdbYes;
		private JRadioButton rdbNo;
		private ButtonGroup groupHoChieu;

		private JRadioButton rdbNam;
		private JRadioButton rdbNu;
		private ButtonGroup groupSẽ;

		private JTextField txtMaT;
		private JTextField txtTenT;
		private JTextField txtGiaT;

		private JTextField txtMaKs;
		private JTextField txtTenKs;
		private JTextField txtDiaChiKs;
		private JTextField txtSdtKs;

		private JTextField txtMaLt;
		private JTextField txtTenLt;
		private JDateChooser ngayKH;
		private JDateChooser ngayKT;
		public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
//		public static final SimpleDateFormat DATE_FORMAT_SQL = new SimpleDateFormat("yyyy-MM-dd");
		private JRadioButton rdbSang;
		private JRadioButton rdbThuong;
		private ButtonGroup groupLoaiTour;

		private JTextField txtMaPT;
		private JTextField txtLoaiPT;
		private JTextField txtTenTX;
		private JTextField txtSdtTaiXe;

		private JTextField txtTim, txtSoLuong;
		private JButton btnTim, btnTim2, btnXoa, btnSua, btnClear, btnDatve, btnXuatFile, btnTtKH, btnTtTour, btnTtKS,
				btnTtLt, btnTtPt;

		private JLabel lblMaT;
		private JLabel lblTenT;
		private JLabel lblGiaT;
		private JLabel lblMaKs;
		private JLabel lblTenKs;
		private JLabel lblDiaChiKs;
		private JLabel lblSdtKs;
		private JLabel lblMaLt;
		private JLabel lblTenLt;
		private JLabel lblNgayKH;
		private JLabel lblNgayKT;
		private JLabel lblLoaiTour;
		private JLabel lblMaPT;
		private JLabel lblLoaiPT;
		private JLabel lblTenTX;
		private JLabel lblSdtTaiXe;

		public bookingQLTT() {

			pnlCenterNavBar = new JPanel();
			pnlCenter.add(pnlCenterNavBar, BorderLayout.NORTH);
			pnlCenterNavBar.setPreferredSize(new Dimension(1080, 280));

			pnlCenterContentLeft = new JPanel();
			pnlCenter.add(pnlCenterContentLeft, BorderLayout.WEST);
			pnlCenterContentLeft.setPreferredSize(new Dimension(200, 328));
			pnlCenterContentLeft.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, fourColor));

			pnlCenterContentRight = new JPanel();
			pnlCenter.add(pnlCenterContentRight, BorderLayout.EAST);
			pnlCenterContentRight.setPreferredSize(new Dimension(880, 328));
			pnlCenterContentRight.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, fourColor));

			pnlCenterFooter = new JPanel();
			pnlCenter.add(pnlCenterFooter, BorderLayout.SOUTH);
			pnlCenterFooter.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, fourColor));
			pnlCenterFooter.setPreferredSize(new Dimension(1080, 62));

			Box b = Box.createVerticalBox();
			Box b1 = Box.createHorizontalBox();
			Box b2 = Box.createHorizontalBox();

			b.add(b1);
			JLabel lblTittle = new JLabel("Thông tin đặt vé");
			lblTittle.setFont(new Font("", Font.BOLD, 25));
			lblTittle.setForeground(Color.RED);
			b1.add(lblTittle);
			b.add(Box.createVerticalStrut(20));

			b.add(b2);
			JPanel pnlTable = new JPanel();
			dtmBooking = new DefaultTableModel();
			tblBooking = new JTable(dtmBooking) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int collumn) {
					return false;
				}
			};

			dtmBooking.addColumn("Mã vé");
			dtmBooking.addColumn("Tên khách hàng");
			dtmBooking.addColumn("Tên tour");
			dtmBooking.addColumn("Thông tin khách sạn");
			dtmBooking.addColumn("Thông tin lộ trình");
			dtmBooking.addColumn("Chi tiết phương tiện");
			dtmBooking.addColumn("Thành tiền");

			TableColumn column0 = tblBooking.getColumnModel().getColumn(0);
			column0.setPreferredWidth(20);
			TableColumn column2 = tblBooking.getColumnModel().getColumn(2);
			column2.setPreferredWidth(150);
			TableColumn column3 = tblBooking.getColumnModel().getColumn(3);
			column3.setPreferredWidth(120);
			TableColumn column4 = tblBooking.getColumnModel().getColumn(4);
			column4.setPreferredWidth(120);
			TableColumn column5 = tblBooking.getColumnModel().getColumn(5);
			column5.setPreferredWidth(100);

			Font font = new Font("Arial", Font.PLAIN, 14);
			tblBooking.setFont(font);

			JTableHeader header = tblBooking.getTableHeader();
			header.setFont(font.deriveFont(Font.BOLD));

			tblBooking.setBorder(BorderFactory.createLineBorder(Color.black));

			// thêm hiệu ứng hover cho các hàng trong bảng
			tblBooking.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					int row = tblBooking.rowAtPoint(evt.getPoint());
					if (row >= 0) {
						tblBooking.setRowSelectionInterval(row, row);
					}
				}
			});

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);

			for (int i = 0; i < tblBooking.getColumnCount(); i++) {
				tblBooking.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}

			pnlTable.add(tblBooking);

			JScrollPane sp = new JScrollPane(tblBooking, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			sp.setPreferredSize(new Dimension(1060, 222));
			b2.add(sp);

			/**
			 * đoc vào table, do có được list
			 */
			DocDuLieuDatabaseVaoTable();

			btnTtKH = new JButton("Chi tiết khách hàng");
			btnTtKH.setPreferredSize(new Dimension(160, 40));
			btnTtKH.setForeground(secondColor);
			btnTtKH.setOpaque(true);
			btnTtKH.setBorderPainted(false);
			btnTtKH.setFocusPainted(false);
			btnTtKH.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTtKH.setUI(new RoundedButtonUI_v1());
			btnTtKH.setFont(font);

			btnTtTour = new JButton("Chi tiết tour");
			btnTtTour.setPreferredSize(new Dimension(160, 40));
			btnTtTour.setForeground(secondColor);
			btnTtTour.setOpaque(true);
			btnTtTour.setBorderPainted(false);
			btnTtTour.setFocusPainted(false);
			btnTtTour.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTtTour.setUI(new RoundedButtonUI_v1());
			btnTtTour.setFont(font);

			btnTtKS = new JButton("Chi tiết khách Sạn");
			btnTtKS.setPreferredSize(new Dimension(160, 40));
			btnTtKS.setForeground(secondColor);
			btnTtKS.setOpaque(true);
			btnTtKS.setBorderPainted(false);
			btnTtKS.setFocusPainted(false);
			btnTtKS.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTtKS.setUI(new RoundedButtonUI_v1());
			btnTtKS.setFont(font);

			btnTtLt = new JButton("Chi tiết lộ trình");
			btnTtLt.setPreferredSize(new Dimension(160, 40));
			btnTtLt.setForeground(secondColor);
			btnTtLt.setOpaque(true);
			btnTtLt.setBorderPainted(false);
			btnTtLt.setFocusPainted(false);
			btnTtLt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTtLt.setUI(new RoundedButtonUI_v1());
			btnTtLt.setFont(font);

			btnTtPt = new JButton("Chi tiết phương tiện");
			btnTtPt.setPreferredSize(new Dimension(160, 40));
			btnTtPt.setForeground(secondColor);
			btnTtPt.setOpaque(true);
			btnTtPt.setBorderPainted(false);
			btnTtPt.setFocusPainted(false);
			btnTtPt.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTtPt.setUI(new RoundedButtonUI_v1());
			btnTtPt.setFont(font);

			Border roundedBorder = BorderFactory.createLineBorder(fourColor, 2, true);

			lblMaKH = new JLabel("Mã khách hàng: ");
			txtMaKH = new JTextField();
			lblMaKH.setForeground(Color.BLUE);
			lblMaKH.setFont(new Font("", Font.BOLD, 15));
			txtMaKH.setPreferredSize(new Dimension(180, 20));
			txtMaKH.setBorder(roundedBorder);
			txtMaKH.setFont(new Font("", Font.BOLD, 12));

			lblTenKH = new JLabel("Tên khách hàng: ");
			txtTenKH = new JTextField();
			lblTenKH.setFont(new Font("", Font.BOLD, 15));
			lblTenKH.setForeground(thirdColor);
			txtTenKH.setPreferredSize(new Dimension(180, 20));
			txtTenKH.setBorder(roundedBorder);
			txtTenKH.setFont(new Font("", Font.BOLD, 12));

			lblSdt = new JLabel("Số điện thoại: ");
			txtSdt = new JTextField();
			lblSdt.setFont(new Font("", Font.BOLD, 15));
			lblSdt.setForeground(thirdColor);
			txtSdt.setPreferredSize(new Dimension(180, 20));
			txtSdt.setBorder(roundedBorder);
			txtSdt.setFont(new Font("", Font.BOLD, 12));

			lblEmail = new JLabel("Email: ");
			txtEmail = new JTextField();
			lblEmail.setFont(new Font("", Font.BOLD, 15));
			lblEmail.setForeground(thirdColor);
			txtEmail.setPreferredSize(new Dimension(180, 20));
			txtEmail.setBorder(roundedBorder);
			txtEmail.setFont(new Font("", Font.BOLD, 12));

			lblCccd = new JLabel("CCCD: ");
			txtCccd = new JTextField();
			lblCccd.setFont(new Font("", Font.BOLD, 15));
			lblCccd.setForeground(thirdColor);
			txtCccd.setPreferredSize(new Dimension(180, 20));
			txtCccd.setBorder(roundedBorder);
			txtCccd.setFont(new Font("", Font.BOLD, 12));

			lblHoChieu = new JLabel("Hộ chiếu: ");
			lblHoChieu.setForeground(thirdColor);
			lblHoChieu.setFont(new Font("", Font.BOLD, 18));
			rdbYes = new JRadioButton("Có");
			rdbNo = new JRadioButton("Không");
			groupHoChieu = new ButtonGroup();
			groupHoChieu.add(rdbYes);
			groupHoChieu.add(rdbNo);
			lblHoChieu.setFont(new Font("", Font.BOLD, 15));
			lblHoChieu.setForeground(mainColor);

			lblGioiTinh = new JLabel("Giới tính: ");
			lblGioiTinh.setForeground(thirdColor);
			lblGioiTinh.setFont(new Font("", Font.BOLD, 18));
			rdbNam = new JRadioButton("Nam");
			rdbNu = new JRadioButton("Nữ");
			groupSẽ = new ButtonGroup();
			groupSẽ.add(rdbNam);
			groupSẽ.add(rdbNu);
			lblGioiTinh.setFont(new Font("", Font.BOLD, 15));
			lblGioiTinh.setForeground(mainColor);

			lblMaT = new JLabel("Mã Tour: ");
			txtMaT = new JTextField();

			lblMaT.setBackground(Color.BLUE);
			lblMaT.setForeground(Color.BLUE);
			lblMaT.setFont(new Font("", Font.BOLD, 18));
			txtMaT.setPreferredSize(new Dimension(180, 20));
			txtMaT.setBorder(roundedBorder);
			txtMaT.setFont(new Font("", Font.BOLD, 12));

			lblTenT = new JLabel("Tên Tour: ");
			txtTenT = new JTextField();

			lblTenT.setForeground(Color.BLUE);
			lblTenT.setFont(new Font("", Font.BOLD, 18));
			lblTenT.setForeground(thirdColor);
			txtTenT.setPreferredSize(new Dimension(180, 20));
			txtTenT.setBorder(roundedBorder);
			txtTenT.setFont(new Font("", Font.BOLD, 12));

			lblGiaT = new JLabel("Giá Tour: ");
			txtGiaT = new JTextField();

			lblGiaT.setForeground(Color.BLUE);
			lblGiaT.setFont(new Font("", Font.BOLD, 18));
			lblGiaT.setForeground(thirdColor);
			txtGiaT.setPreferredSize(new Dimension(180, 20));
			txtGiaT.setBorder(roundedBorder);
			txtGiaT.setFont(new Font("", Font.BOLD, 12));

			lblMaKs = new JLabel("Mã khách sạn: ");
			txtMaKs = new JTextField();

			lblMaKs.setForeground(Color.BLUE);
			lblMaKs.setFont(new Font("", Font.BOLD, 18));
			txtMaKs.setPreferredSize(new Dimension(180, 20));
			txtMaKs.setBorder(roundedBorder);
			txtMaKs.setFont(new Font("", Font.BOLD, 12));

			lblTenKs = new JLabel("Tên khách sạn: ");
			txtTenKs = new JTextField();

			lblTenKs.setForeground(Color.BLUE);
			lblTenKs.setFont(new Font("", Font.BOLD, 18));
			lblTenKs.setForeground(thirdColor);
			txtTenKs.setPreferredSize(new Dimension(180, 20));
			txtTenKs.setBorder(roundedBorder);
			txtTenKs.setFont(new Font("", Font.BOLD, 12));

			lblDiaChiKs = new JLabel("Địa chỉ: ");
			txtDiaChiKs = new JTextField();

			lblDiaChiKs.setForeground(Color.BLUE);
			lblDiaChiKs.setFont(new Font("", Font.BOLD, 18));
			lblDiaChiKs.setForeground(thirdColor);
			txtDiaChiKs.setPreferredSize(new Dimension(180, 20));
			txtDiaChiKs.setBorder(roundedBorder);
			txtDiaChiKs.setFont(new Font("", Font.BOLD, 12));

			lblSdtKs = new JLabel("SDT KS: ");
			txtSdtKs = new JTextField();

			lblSdtKs.setForeground(Color.BLUE);
			lblSdtKs.setFont(new Font("", Font.BOLD, 18));
			lblSdtKs.setForeground(thirdColor);
			txtSdtKs.setPreferredSize(new Dimension(180, 20));
			txtSdtKs.setBorder(roundedBorder);
			txtSdtKs.setFont(new Font("", Font.BOLD, 12));

			lblMaLt = new JLabel("Mã lộ trình: ");
			txtMaLt = new JTextField();

			lblMaLt.setForeground(Color.BLUE);
			lblMaLt.setFont(new Font("", Font.BOLD, 18));
			txtMaLt.setPreferredSize(new Dimension(180, 20));
			txtMaLt.setBorder(roundedBorder);
			txtMaLt.setFont(new Font("", Font.BOLD, 12));

			lblTenLt = new JLabel("Tên lộ trình: ");
			txtTenLt = new JTextField();

			lblTenLt.setForeground(Color.BLUE);
			lblTenLt.setFont(new Font("", Font.BOLD, 18));
			lblTenLt.setForeground(thirdColor);
			txtTenLt.setPreferredSize(new Dimension(180, 20));
			txtTenLt.setBorder(roundedBorder);
			txtTenLt.setFont(new Font("", Font.BOLD, 12));

			lblNgayKH = new JLabel("Ngày khởi hành: ");
			lblNgayKH.setForeground(Color.BLUE);
			lblNgayKH.setFont(new Font("", Font.BOLD, 18));
			lblNgayKH.setForeground(thirdColor);
			ngayKH = new JDateChooser(DATE_FORMAT.toPattern(), "##/##/####", '_');
			ngayKH.setFont(new Font("Tahoma", Font.PLAIN, 13));

			lblNgayKT = new JLabel("Ngày kết thúc: ");
			lblNgayKT.setForeground(Color.BLUE);
			lblNgayKT.setFont(new Font("", Font.BOLD, 18));
			lblNgayKT.setForeground(thirdColor);
			ngayKT = new JDateChooser(DATE_FORMAT.toPattern(), "##/##/####", '_');
			ngayKT.setFont(new Font("Tahoma", Font.PLAIN, 13));

			lblLoaiTour = new JLabel("Loại Tour: ");
			lblLoaiTour.setForeground(Color.BLUE);
			lblLoaiTour.setFont(new Font("", Font.BOLD, 18));
			lblLoaiTour.setForeground(thirdColor);
			rdbSang = new JRadioButton("Sang");
			rdbThuong = new JRadioButton("Thuòng");
			groupLoaiTour = new ButtonGroup();
			groupLoaiTour.add(rdbSang);
			groupLoaiTour.add(rdbThuong);

			lblMaPT = new JLabel("Mã Phương tiện: ");
			txtMaPT = new JTextField();

			lblMaPT.setForeground(Color.BLUE);
			lblMaPT.setFont(new Font("", Font.BOLD, 18));
			txtMaPT.setPreferredSize(new Dimension(177, 20));
			txtMaPT.setBorder(roundedBorder);
			txtMaPT.setFont(new Font("", Font.BOLD, 12));

			lblLoaiPT = new JLabel("Loại phương tiện: ");
			txtLoaiPT = new JTextField();

			lblLoaiPT.setForeground(Color.BLUE);
			lblLoaiPT.setFont(new Font("", Font.BOLD, 18));
			lblLoaiPT.setForeground(thirdColor);
			txtLoaiPT.setPreferredSize(new Dimension(177, 20));
			txtLoaiPT.setBorder(roundedBorder);
			txtLoaiPT.setFont(new Font("", Font.BOLD, 12));

			lblTenTX = new JLabel("Tên tài xế: ");
			txtTenTX = new JTextField();

			lblTenTX.setForeground(Color.BLUE);
			lblTenTX.setFont(new Font("", Font.BOLD, 18));
			lblTenTX.setForeground(thirdColor);
			txtTenTX.setPreferredSize(new Dimension(177, 20));
			txtTenTX.setBorder(roundedBorder);
			txtTenTX.setFont(new Font("", Font.BOLD, 12));

			lblSdtTaiXe = new JLabel("SDT Tài Xế: ");
			txtSdtTaiXe = new JTextField();

			lblSdtTaiXe.setForeground(Color.BLUE);
			lblSdtTaiXe.setFont(new Font("", Font.BOLD, 18));
			lblSdtTaiXe.setForeground(thirdColor);
			txtSdtTaiXe.setPreferredSize(new Dimension(177, 20));
			txtSdtTaiXe.setBorder(roundedBorder);
			txtSdtTaiXe.setFont(new Font("", Font.BOLD, 12));

			pnlCenterNavBar.add(b);
			pnlCenterContentLeft.add(Box.createVerticalStrut(50));
			pnlCenterContentLeft.add(btnTtKH);
			pnlCenterContentLeft.add(Box.createVerticalStrut(50));
			pnlCenterContentLeft.add(btnTtTour);
			pnlCenterContentLeft.add(Box.createVerticalStrut(50));
			pnlCenterContentLeft.add(btnTtKS);
			pnlCenterContentLeft.add(Box.createVerticalStrut(50));
			pnlCenterContentLeft.add(btnTtLt);
			pnlCenterContentLeft.add(Box.createVerticalStrut(50));
			pnlCenterContentLeft.add(btnTtPt);
			pnlCenterContentLeft.add(Box.createVerticalStrut(50));

			// phần footer

			JLabel lblTim = new JLabel("Tìm theo SDT: ");
			lblTim.setForeground(Color.BLUE);
			lblTim.setFont(new Font("", Font.BOLD, 18));
			lblTim.setForeground(thirdColor);
			txtTim = new JTextField(8);
			txtTim.setHorizontalAlignment(JTextField.CENTER);
			txtTim.setPreferredSize(new Dimension(30, 30));
			txtTim.setBorder(roundedBorder);
			txtTim.setFont(new Font("", Font.BOLD, 12));

			btnTim = new JButton("Tìm");
			btnTim.setPreferredSize(new Dimension(90, 50));
			btnTim.setForeground(Color.WHITE);
			btnTim.setOpaque(true);
			btnTim.setBorderPainted(false);
			btnTim.setFocusPainted(false);
			btnTim.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTim.setUI(new RoundedButtonUI_v1());

			btnTim2 = new JButton("Tìm tên KH");
			btnTim2.setPreferredSize(new Dimension(100, 50));
			btnTim2.setForeground(Color.WHITE);
			btnTim2.setOpaque(true);
			btnTim2.setBorderPainted(false);
			btnTim2.setFocusPainted(false);
			btnTim2.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnTim2.setUI(new RoundedButtonUI_v2());

			btnXoa = new JButton("Xóa Vé");
			btnXoa.setPreferredSize(new Dimension(90, 50));
			btnXoa.setForeground(Color.WHITE);
			btnXoa.setOpaque(true);
			btnXoa.setBorderPainted(false);
			btnXoa.setFocusPainted(false);
			btnXoa.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnXoa.setUI(new RoundedButtonUI_v1());

			btnClear = new JButton("Clear");
			btnClear.setPreferredSize(new Dimension(90, 50));
			btnClear.setForeground(Color.WHITE);
			btnClear.setOpaque(true);
			btnClear.setBorderPainted(false);
			btnClear.setFocusPainted(false);
			btnClear.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnClear.setUI(new RoundedButtonUI_v1());

			JLabel lblSoLuong = new JLabel("Số Lượng: ");
			lblSoLuong.setForeground(Color.BLUE);
			lblSoLuong.setFont(new Font("", Font.BOLD, 18));
			lblSoLuong.setForeground(thirdColor);
			txtSoLuong = new JTextField(4);
			txtSoLuong.setHorizontalAlignment(JTextField.CENTER);
			txtSoLuong.setPreferredSize(new Dimension(30, 30));
			txtSoLuong.setBorder(roundedBorder);
			txtSoLuong.setFont(new Font("", Font.BOLD, 12));

			btnDatve = new JButton("Đặt vé");
			btnDatve.setPreferredSize(new Dimension(90, 50));
			btnDatve.setForeground(Color.WHITE);
			btnDatve.setOpaque(true);
			btnDatve.setBorderPainted(false);
			btnDatve.setFocusPainted(false);
			btnDatve.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnDatve.setUI(new RoundedButtonUI_v3());

			btnXuatFile = new JButton("Xuất file");
			btnXuatFile.setPreferredSize(new Dimension(90, 50));
			btnXuatFile.setForeground(Color.WHITE);
			btnXuatFile.setOpaque(true);
			btnXuatFile.setBorderPainted(false);
			btnXuatFile.setFocusPainted(false);
			btnXuatFile.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			btnXuatFile.setUI(new RoundedButtonUI_v2());

			pnlCenterFooter.setBackground(secondColor);
			pnlCenterFooter.add(lblTim);
			pnlCenterFooter.add(txtTim);
			pnlCenterFooter.add(Box.createHorizontalStrut(10));
			pnlCenterFooter.add(btnTim);
			pnlCenterFooter.add(Box.createHorizontalStrut(10));
			pnlCenterFooter.add(btnTim2);
			pnlCenterFooter.add(Box.createHorizontalStrut(10));
			pnlCenterFooter.add(btnXoa);
			pnlCenterFooter.add(Box.createHorizontalStrut(10));
			pnlCenterFooter.add(btnClear);
			pnlCenterFooter.add(lblSoLuong);
			pnlCenterFooter.add(txtSoLuong);
			pnlCenterFooter.add(Box.createHorizontalStrut(10));
			pnlCenterFooter.add(btnDatve);
			pnlCenterFooter.add(Box.createHorizontalStrut(10));
			pnlCenterFooter.add(btnXuatFile);

			btnTim.addActionListener(this);
			btnTim2.addActionListener(this);
			btnDatve.addActionListener(this);
			btnXoa.addActionListener(this);
			btnClear.addActionListener(this);
			btnXuatFile.addActionListener(this);
			tblBooking.addMouseListener(this);
			btnTtKH.addActionListener(this);
			btnTtTour.addActionListener(this);
			btnTtKS.addActionListener(this);
			btnTtLt.addActionListener(this);
			btnTtPt.addActionListener(this);

			if (bkMaKH == null) {

				tblBooking.setRowSelectionInterval(0, 0);

				int row = 0;

				try {
					ve_dao = new ThongTinDatTour_DAO();
					List<thongTinDatTour> ves = ve_dao.getAllVe();
					int i = 0;
					for (thongTinDatTour ve : ves) {

						if (row == i) {

							txtMaKH.setText(ve.getThongTinKhachhang().getMaKH());
							txtTenKH.setText(ve.getThongTinKhachhang().getTenKH());
							txtSdt.setText(ve.getThongTinKhachhang().getSDT());
							String aa = String.valueOf(ve.getThongTinKhachhang().getCCCD());
							txtCccd.setText(aa);
							txtEmail.setText(ve.getThongTinKhachhang().getEmail());

							boolean bb = ve.getThongTinKhachhang().isHoChieu();
							if (bb) {
								rdbYes.setSelected(true);
								rdbNo.setSelected(false);
							} else {
								rdbNo.setSelected(true);
								rdbYes.setSelected(false);
							}

							boolean c = ve.getThongTinKhachhang().isGioiTinh();
							if (c) {
								rdbNam.setSelected(true);
								rdbNu.setSelected(false);
							} else {
								rdbNu.setSelected(true);
								rdbNam.setSelected(false);
							}

							txtMaT.setText(ve.getThongTinTour().getMaTour());
							txtTenT.setText(ve.getThongTinTour().getTenTour());
							String d = String.valueOf(ve.getThongTinTour().getGiaTour());
							txtGiaT.setText(d);

							txtMaKs.setText(ve.getThongTinTour().getThongTinKhachSan().getMaKS());
							txtTenKs.setText(ve.getThongTinTour().getThongTinKhachSan().getTenKS());
							txtDiaChiKs.setText(ve.getThongTinTour().getThongTinKhachSan().getDiaChi());
							txtSdtKs.setText(ve.getThongTinTour().getThongTinKhachSan().getSDT());

							txtMaLt.setText(ve.getThongTinTour().getThongTinLoTrinh().getMaLoTrinh());
							txtTenLt.setText(ve.getThongTinTour().getThongTinLoTrinh().getTenLoTrinh());
							Instant instant11 = ve.getThongTinTour().getThongTinLoTrinh().getNgayKhoiHanh()
									.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
							java.util.Date date11 = Date.from(instant11);
							ngayKH.setDate(date11);
							Instant instant22 = ve.getThongTinTour().getThongTinLoTrinh().getNgayKetThuc()
									.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
							java.util.Date date22 = Date.from(instant22);
							ngayKT.setDate(date22);
							boolean ee = ve.getThongTinTour().getThongTinLoTrinh().isLoaiTour();
							if (ee) {
								rdbSang.setSelected(true);
								rdbThuong.setSelected(false);
							} else {
								rdbThuong.setSelected(true);
								rdbSang.setSelected(false);
							}

							txtMaPT.setText(ve.getThongTinTour().getThongTinPhuongTien().getMaPhuongTien());
							txtLoaiPT.setText(ve.getThongTinTour().getThongTinPhuongTien().getLoaiPhuongTien());
							txtTenTX.setText(ve.getThongTinTour().getThongTinPhuongTien().getTenTaiXe());
							txtSdtTaiXe.setText(ve.getThongTinTour().getThongTinPhuongTien().getSDT());

							String ff = String.valueOf(ve.getSoLuong());
							txtSoLuong.setText(ff);

							hinhAnh = "media\\\\" + ve.getThongTinTour().getMaTour() + ".png";

							btnTtKH.doClick();
							btnDatve.setEnabled(false);
						}
						i++;
					}

				} catch (Exception e2) {
				}

			} else {

				txtMaKH.setText(bkMaKH);
				txtTenKH.setText(bkTenKH);

				txtSdt.setText(bkSdtKh);
				String w = String.valueOf(bkCccd);
				txtCccd.setText(w);
				txtEmail.setText(bkEmail);
				groupHoChieu.clearSelection();
				groupSẽ.clearSelection();

				if (bkHoChieu) {
					rdbYes.setSelected(true);
					rdbNo.setSelected(false);
				} else {
					rdbYes.setSelected(false);
					rdbNo.setSelected(true);
				}
				if (bkSex) {
					rdbNam.setSelected(true);
					rdbNu.setSelected(false);
				} else {
					rdbNam.setSelected(false);
					rdbNu.setSelected(true);
				}

				txtMaT.setText(bkMaT);
				txtTenT.setText(bkTenT);
				String r = String.valueOf(bkGiaTour);
				txtGiaT.setText(r);

				txtMaKs.setText(bkMaKs);
				txtTenKs.setText(bkTenKs);
				txtDiaChiKs.setText(bkDiaChiKs);
				String t = String.valueOf(bkSdtKs);
				txtSdtKs.setText(t);

				txtMaLt.setText(bkMaLt);
				txtTenLt.setText(bkTenLt);

				Instant instant1 = bkNgayKH.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				java.util.Date date1 = Date.from(instant1);
				ngayKH.setDate(date1);
				Instant instant2 = bkNgayKt.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				java.util.Date date2 = Date.from(instant2);
				ngayKT.setDate(date2);

				if (bkLoaiTour) {
					rdbSang.setSelected(true);
					rdbThuong.setSelected(false);
				} else {
					rdbSang.setSelected(false);
					rdbThuong.setSelected(true);
				}

				txtMaPT.setText(bkMaPT);
				txtLoaiPT.setText(bkLoaiPT);
				txtTenTX.setText(bkTenTX);
				txtSdtTaiXe.setText(bkSdtTaiXe);

				hinhAnh = "media\\\\" + bkMaT + ".png";
				btnDatve.setEnabled(true);
				btnTtKH.doClick();

			}

			txtMaKH.setEditable(false);
			txtTenKH.setEditable(false);
			txtSdt.setEditable(false);
			txtCccd.setEditable(false);
			txtEmail.setEditable(false);

			rdbYes.setEnabled(false);
			rdbNo.setEnabled(false);

			rdbNam.setEnabled(false);
			rdbNu.setEnabled(false);

			txtMaT.setEditable(false);
			txtTenT.setEditable(false);
			txtGiaT.setEditable(false);

			txtMaKs.setEditable(false);
			txtTenKs.setEditable(false);
			txtDiaChiKs.setEditable(false);
			txtSdtKs.setEditable(false);

			txtMaLt.setEditable(false);
			txtTenLt.setEditable(false);

			ngayKH.setEnabled(false);

			ngayKT.setEnabled(false);

			rdbSang.setEnabled(false);
			rdbThuong.setEnabled(false);

			txtMaPT.setEditable(false);
			txtLoaiPT.setEditable(false);
			txtTenTX.setEditable(false);
			txtSdtTaiXe.setEditable(false);

		}

		private void DocDuLieuDatabaseVaoTable() {
			DefaultTableModel model = (DefaultTableModel) tblBooking.getModel();
			model.setRowCount(0);

			ve_dao = new ThongTinDatTour_DAO();
			List<thongTinDatTour> ves = ve_dao.getAllVe();
			for (thongTinDatTour ve : ves) {
				Object[] row = new Object[7];
				row[0] = ve.getMaVe();
				row[1] = ve.getThongTinKhachhang().getTenKH();
				row[2] = ve.getThongTinTour().getTenTour();
				row[3] = ve.getThongTinTour().getThongTinKhachSan().getTenKS();
				row[4] = ve.getThongTinTour().getThongTinLoTrinh().getTenLoTrinh();
				row[5] = ve.getThongTinTour().getThongTinPhuongTien().getLoaiPhuongTien();

				double temp1 = ve.getThongTinTour().getGiaTour();
				double temp2 = ve.getSoLuong();
				double temp3 = temp1 * temp2;

				Locale locale = new Locale("vi", "VN"); // định dạng địa phương cho tiền Việt Nam
				NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
				String formattedMoney = currencyFormatter.format(temp3);

				row[6] = (formattedMoney);

				model.addRow(row);
			}

		}

		public void clearDaTaOnModel() {
			DefaultTableModel dtm = (DefaultTableModel) tblBooking.getModel();
			dtm.getDataVector().removeAllElements();
		}

		public String phatSinhMaVe() {

			ve_dao = new ThongTinDatTour_DAO();
			List<thongTinDatTour> ves = ve_dao.getAllVe();
			String temp = null;
			for (thongTinDatTour ve : ves) {
				temp = ve.getMaVe();
			}
			int count = laySoDuoi(temp);

			count++;

			String maVe = String.format("V%03d", count);

			return maVe;
		}

		public int laySoDuoi(String str) {
			if (str == null) {
				return 0;
			} else {
				String numberStr = str.substring(1); // loại bỏ ký tự "V"
				int number = Integer.parseInt(numberStr); // chuyển đổi chuỗi thành số nguyên
				return number;
			}

		}

		public void clearTextField() {

			txtMaKH.setText("");
			txtTenKH.setText("");
			txtSdt.setText("");
			txtCccd.setText("");
			txtEmail.setText("");
			rdbNo.setSelected(true);
			rdbNu.setSelected(true);

			txtMaT.setText("");
			txtTenT.setText("");
			txtGiaT.setText("");

			txtMaKs.setText("");
			txtTenKs.setText("");
			txtDiaChiKs.setText("");
			txtSdtKs.setText("");

			txtMaLt.setText("");
			txtTenLt.setText("");
			ngayKH.setDate(null);
			ngayKT.setDate(null);
			rdbThuong.setSelected(true);

			txtMaPT.setText("");
			txtLoaiPT.setText("");
			txtTenTX.setText("");
			txtSdtTaiXe.setText("");

			txtTim.setText("");
			txtSoLuong.setText("");
			txtSoLuong.setEditable(true);

			bkMaKH = null;
			bkTenKH = null;
			bkSdtKh = null;
			bkEmail = null;
			bkCccd = null;
			bkHoChieu = rdbNo.isSelected();
			bkSex = rdbNu.isSelected();

			bkMaT = null;
			bkTenT = null;
			bkGiaTour = 0;

			bkMaKs = null;
			bkTenKs = null;
			bkDiaChiKs = null;
			bkSdtKs = null;

			bkMaLt = null;
			bkTenLt = null;

			bkNgayKH = null;
			bkNgayKt = null;

			bkLoaiTour = rdbThuong.isSelected();

			bkMaPT = null;
			bkLoaiPT = null;
			bkTenTX = null;
			bkSdtTaiXe = null;

		}

		private thongTinDatTour createFormVe() {
			thongTinDatTour ve;
			String maVe = phatSinhMaVe();
			boolean hochieu;
			if (rdbYes.isSelected()) {
				hochieu = true;
			} else
				hochieu = false;
			boolean gioitinh;
			if (rdbNam.isSelected()) {
				gioitinh = true;
			} else
				gioitinh = false;
			KhachHang ttkh = new KhachHang(txtMaKH.getText(), txtTenKH.getText(), txtSdt.getText(), txtEmail.getText(),
					txtCccd.getText(), hochieu, gioitinh);

			String maT = txtMaT.getText();
			String tenT = txtTenT.getText();
			double giaT = Double.parseDouble(txtGiaT.getText());
			KhachSan khachSan = new KhachSan(txtMaKs.getText(), txtTenKs.getText(), txtDiaChiKs.getText(),
					txtSdtKs.getText());
			PhuongTien pTien = new PhuongTien(txtMaPT.getText(), txtLoaiPT.getText(), txtTenTX.getText(),
					txtSdtTaiXe.getText());

			java.util.Date selectedDateKH = ngayKH.getDate();
			LocalDate ngayKh = selectedDateKH.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			java.util.Date selectedDateKT = ngayKT.getDate();
			LocalDate ngayKt = selectedDateKT.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			boolean loaiT;
			if (rdbSang.isSelected()) {
				loaiT = true;
			} else
				loaiT = false;
			LoTrinh lTrinh = new LoTrinh(txtMaLt.getText(), txtTenLt.getText(), ngayKh, ngayKt, loaiT);

			Tour t = new Tour(maT, tenT, giaT, khachSan, pTien, lTrinh);

			NhanVien nv = new NhanVien(ma, ten, sdt, emailnv, mknv);

			ve = new thongTinDatTour(maVe, ttkh, t, Integer.parseInt(txtSoLuong.getText()), nv);

			return ve;
		}

		public boolean checkSL() {

			String temp = txtSoLuong.getText().trim();

			if (temp.matches("(\\d+)")) {
				int sl = Integer.parseInt(temp);
				if (sl <= 0) {
					CustomJOptionPane.showMessageDialog("Nhập số lượng không hợp lệ!!!");
					return false;
				}
			} else {
				CustomJOptionPane.showMessageDialog("Nhập số lượng không hợp lệ!!!");
				return false;
			}
			return true;

		}

		private int levenshteinDistance(String s1, String s2) {
			int[][] dp = new int[s1.length() + 1][s2.length() + 1];
			for (int i = 0; i <= s1.length(); i++) {
				dp[i][0] = i;
			}
			for (int j = 0; j <= s2.length(); j++) {
				dp[0][j] = j;
			}
			for (int i = 1; i <= s1.length(); i++) {
				for (int j = 1; j <= s2.length(); j++) {
					if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
					}
				}
			}
			return dp[s1.length()][s2.length()];
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();

			if (obj == btnTim) {

				String sdtkh = txtTim.getText().trim();
				if (!(sdtkh.length() > 0 && sdtkh.matches("\\d{10}"))) {
					CustomJOptionPane.showMessageDialog("Số điện thoại gồm 10 số !!!");
				} else {

					String maTim = txtTim.getText();
					ArrayList<thongTinDatTour> dsKHtim = null;

					for (thongTinDatTour ve : ve_dao.getAllVe()) {
						if (ve.getThongTinKhachhang().getSDT().equals(maTim)) {
							dsKHtim = new ArrayList<thongTinDatTour>();
							dsKHtim.add(ve);
						}
					}
					if (dsKHtim != null) {
						clearDaTaOnModel();
						for (thongTinDatTour ve : dsKHtim) {
							Object[] row = new Object[7];
							row[0] = ve.getMaVe();
							row[1] = ve.getThongTinKhachhang().getTenKH();
							row[2] = ve.getThongTinTour().getTenTour();
							row[3] = ve.getThongTinTour().getThongTinKhachSan().getTenKS();
							row[4] = ve.getThongTinTour().getThongTinLoTrinh().getTenLoTrinh();
							row[5] = ve.getThongTinTour().getThongTinPhuongTien().getLoaiPhuongTien();

							Locale locale = new Locale("vi", "VN"); // định dạng địa phương cho tiền Việt Nam
							NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
							String formattedMoney = currencyFormatter.format(ve.getThongTinTour().getGiaTour());

							row[6] = (formattedMoney);

							dtmBooking.addRow(row);
						}
					} else if (dsKHtim == null) {
						CustomJOptionPane.showMessageDialog("không tìm thấy!!!");
					}
				}

			}
			if (obj == btnTim2) {
				String regex = CustomInputDialog.showInputDialog("Nhập tên Khách Hàng cần tìm: ", "");
				if (regex != null && !regex.trim().equals("")) {
//					try {
					ve_dao = new ThongTinDatTour_DAO();
					List<thongTinDatTour> ves = ve_dao.getAllVe();
					thongTinDatTour closesVe = null;
					int minDistance = Integer.MAX_VALUE;
					for (thongTinDatTour ve : ves) {
						int distance = levenshteinDistance(regex, ve.getThongTinKhachhang().getTenKH());
						if (distance < minDistance) {
							closesVe = ve;
							minDistance = distance;
						}
					}

					clearDaTaOnModel();
					Object[] row = new Object[7];
					row[0] = closesVe.getMaVe();
					row[1] = closesVe.getThongTinKhachhang().getTenKH();
					row[2] = closesVe.getThongTinTour().getTenTour();
					row[3] = closesVe.getThongTinTour().getThongTinKhachSan().getTenKS();
					row[4] = closesVe.getThongTinTour().getThongTinLoTrinh().getTenLoTrinh();
					row[5] = closesVe.getThongTinTour().getThongTinPhuongTien().getLoaiPhuongTien();

					double temp1 = closesVe.getThongTinTour().getGiaTour();
					double temp2 = closesVe.getSoLuong();
					double temp3 = temp1 * temp2;

					Locale locale = new Locale("vi", "VN"); // định dạng địa phương cho tiền Việt Nam
					NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
					String formattedMoney = currencyFormatter.format(temp3);

					row[6] = (formattedMoney);

					dtmBooking.addRow(row);

//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
				}
			}

			if (obj == btnDatve) {

				if (checkSL()) {
					thongTinDatTour ve = createFormVe();
					if (!ve_dao.getAllVe().contains(ve)) {
						try {
							if (ve_dao.addTour(ve)) {
								clearDaTaOnModel();
								DocDuLieuDatabaseVaoTable();
							} else
								CustomJOptionPane.showMessageDialog("Các khoá không được trùng giá trị!!!");
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else
						CustomJOptionPane.showMessageDialog("Mã trùng!!!");
				}

			}

			if (obj == btnXoa) {

				ve_dao = new ThongTinDatTour_DAO();
				if (tblBooking.getSelectedRowCount() > 0) {

					if (JOptionPane.showConfirmDialog(this, "Xác nhận các dòng đã chọn" + " !!!", "Warring",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {

						int[] selectedRows = tblBooking.getSelectedRows();
						for (int i = selectedRows.length - 1; i >= 0; i--) {
							List<thongTinDatTour> bks = ve_dao.getAllVe();
							thongTinDatTour bk = bks.get(selectedRows[i]);
							try {
								List<thongTinDatTour> ves = ve_dao.getAllVe();
								for (thongTinDatTour ve : ves) {
									if (ve.getMaVe().equals(bk.getMaVe())) {
										String maVe = ve.getMaVe();
										ve_dao.xoaVe(maVe);
									}
								}
							} catch (Exception e2) {
							}
						}
						clearDaTaOnModel();
						DocDuLieuDatabaseVaoTable();
						tblBooking.clearSelection();
						CustomJOptionPane.showMessageDialog("Xoá thành công!!!");
					}

				} else
					CustomJOptionPane.showMessageDialog("Chọn dòng cần xoá");

			}

			if (obj == btnSua) {

			}

			if (obj == btnClear) {
				clearTextField();
				clearDaTaOnModel();
				tblBooking.clearSelection();
				txtMaT.requestFocus();
				DocDuLieuDatabaseVaoTable();

			}

			if (obj == btnTtKH) {
				pnlCenterContentRight.removeAll();
				pnlCenterContentRight.revalidate();
				pnlCenterContentRight.repaint();

				btnTtKH.setForeground(thirdColor);
				btnTtTour.setForeground(secondColor);
				btnTtKS.setForeground(secondColor);
				btnTtLt.setForeground(secondColor);
				btnTtPt.setForeground(secondColor);

				ImageIcon image1 = new ImageIcon(hinhAnh);
				Image img1 = image1.getImage();
				Image newImg = img1.getScaledInstance(450, 280, java.awt.Image.SCALE_SMOOTH);
				image1 = new ImageIcon(newImg);
				JLabel lblHinhAnh = new JLabel();
				lblHinhAnh.setIcon(image1);
				pnlCenterContentRight.add(lblHinhAnh, BorderLayout.WEST);

				Box bkh = Box.createVerticalBox();
				Box b1 = Box.createHorizontalBox();
				Box b2 = Box.createHorizontalBox();
				Box b3 = Box.createHorizontalBox();
				Box b4 = Box.createHorizontalBox();
				Box b5 = Box.createHorizontalBox();
				Box b6 = Box.createHorizontalBox();
				Box b7 = Box.createHorizontalBox();
				pnlCenterContentRight.add(bkh, BorderLayout.EAST);

				b1.add(Box.createHorizontalStrut(20));
				b1.add(lblMaKH);
				lblMaKH.setPreferredSize(lblLoaiPT.getPreferredSize());
				b1.add(txtMaKH);
				b2.add(Box.createHorizontalStrut(20));
				b2.add(lblTenKH);
				lblTenKH.setPreferredSize(lblLoaiPT.getPreferredSize());
				b2.add(txtTenKH);
				b3.add(Box.createHorizontalStrut(20));
				b3.add(lblSdt);
				lblSdt.setPreferredSize(lblLoaiPT.getPreferredSize());
				b3.add(txtSdt);
				b4.add(Box.createHorizontalStrut(20));
				b4.add(lblCccd);
				lblCccd.setPreferredSize(lblLoaiPT.getPreferredSize());
				b4.add(txtCccd);
				b5.add(Box.createHorizontalStrut(20));
				b5.add(lblEmail);
				lblEmail.setPreferredSize(lblLoaiPT.getPreferredSize());
				b5.add(txtEmail);

				b6.add(Box.createHorizontalStrut(10));
				b6.add(lblHoChieu);
				b6.add(rdbYes);
				b6.add(rdbNo);
				// rdbYes.setSelected(true);
				b7.add(Box.createHorizontalStrut(10));
				b7.add(lblGioiTinh);
				b7.add(rdbNam);
				b7.add(rdbNu);
				// rdbNam.setSelected(true);

				bkh.add(b1);
				bkh.add(Box.createVerticalStrut(10));
				bkh.add(b2);
				bkh.add(Box.createVerticalStrut(10));
				bkh.add(b3);
				bkh.add(Box.createVerticalStrut(10));
				bkh.add(b4);
				bkh.add(Box.createVerticalStrut(10));
				bkh.add(b5);
				bkh.add(Box.createVerticalStrut(10));
				bkh.add(b6);
				bkh.add(Box.createVerticalStrut(10));
				bkh.add(b7);

			}

			if (obj == btnTtTour) {
				pnlCenterContentRight.removeAll();
				pnlCenterContentRight.revalidate();
				pnlCenterContentRight.repaint();

				btnTtKH.setForeground(secondColor);
				btnTtTour.setForeground(thirdColor);
				btnTtKS.setForeground(secondColor);
				btnTtLt.setForeground(secondColor);
				btnTtPt.setForeground(secondColor);

				ImageIcon image1 = new ImageIcon(hinhAnh);
				Image img1 = image1.getImage();
				Image newImg = img1.getScaledInstance(450, 280, java.awt.Image.SCALE_SMOOTH);
				image1 = new ImageIcon(newImg);
				JLabel lblHinhAnh = new JLabel();
				lblHinhAnh.setIcon(image1);
				pnlCenterContentRight.add(lblHinhAnh, BorderLayout.WEST);

				Box bt = Box.createVerticalBox();
				Box b3 = Box.createHorizontalBox();
				Box b4 = Box.createHorizontalBox();
				Box b5 = Box.createHorizontalBox();
				pnlCenterContentRight.add(bt, BorderLayout.EAST);

				b3.add(Box.createHorizontalStrut(20));
				b3.add(lblMaT);
				lblMaT.setPreferredSize(lblLoaiPT.getPreferredSize());
				b3.add(txtMaT);
				b4.add(Box.createHorizontalStrut(20));
				b4.add(lblTenT);
				lblTenT.setPreferredSize(lblLoaiPT.getPreferredSize());
				b4.add(txtTenT);
				b5.add(Box.createHorizontalStrut(20));
				b5.add(lblGiaT);
				lblGiaT.setPreferredSize(lblLoaiPT.getPreferredSize());
				b5.add(txtGiaT);

				bt.add(b3);
				bt.add(Box.createVerticalStrut(30));
				bt.add(b4);
				bt.add(Box.createVerticalStrut(30));
				bt.add(b5);
			}

			if (obj == btnTtKS) {
				pnlCenterContentRight.removeAll();
				pnlCenterContentRight.revalidate();
				pnlCenterContentRight.repaint();

				btnTtKH.setForeground(secondColor);
				btnTtTour.setForeground(secondColor);
				btnTtKS.setForeground(thirdColor);
				btnTtLt.setForeground(secondColor);
				btnTtPt.setForeground(secondColor);

				ImageIcon image1 = new ImageIcon(hinhAnh);
				Image img1 = image1.getImage();
				Image newImg = img1.getScaledInstance(450, 280, java.awt.Image.SCALE_SMOOTH);
				image1 = new ImageIcon(newImg);
				JLabel lblHinhAnh = new JLabel();
				lblHinhAnh.setIcon(image1);
				pnlCenterContentRight.add(lblHinhAnh, BorderLayout.WEST);

				Box bks = Box.createVerticalBox();
				Box b6 = Box.createHorizontalBox();
				Box b7 = Box.createHorizontalBox();
				Box b8 = Box.createHorizontalBox();
				Box b9 = Box.createHorizontalBox();
				pnlCenterContentRight.add(bks, BorderLayout.EAST);

				b6.add(Box.createHorizontalStrut(20));
				b6.add(lblMaKs);
				lblMaKs.setPreferredSize(lblLoaiPT.getPreferredSize());
				b6.add(txtMaKs);
				b7.add(Box.createHorizontalStrut(20));
				b7.add(lblTenKs);
				lblTenKs.setPreferredSize(lblLoaiPT.getPreferredSize());
				b7.add(txtTenKs);
				b8.add(Box.createHorizontalStrut(20));
				b8.add(lblDiaChiKs);
				lblDiaChiKs.setPreferredSize(lblLoaiPT.getPreferredSize());
				b8.add(txtDiaChiKs);
				b9.add(Box.createHorizontalStrut(20));
				b9.add(lblSdtKs);
				lblSdtKs.setPreferredSize(lblLoaiPT.getPreferredSize());
				b9.add(txtSdtKs);

				bks.add(b6);
				bks.add(Box.createVerticalStrut(30));
				bks.add(b7);
				bks.add(Box.createVerticalStrut(30));
				bks.add(b8);
				bks.add(Box.createVerticalStrut(30));
				bks.add(b9);
			}

			if (obj == btnTtLt) {
				pnlCenterContentRight.removeAll();
				pnlCenterContentRight.revalidate();
				pnlCenterContentRight.repaint();

				btnTtKH.setForeground(secondColor);
				btnTtTour.setForeground(secondColor);
				btnTtKS.setForeground(secondColor);
				btnTtLt.setForeground(thirdColor);
				btnTtPt.setForeground(secondColor);

				ImageIcon image1 = new ImageIcon(hinhAnh);
				Image img1 = image1.getImage();
				Image newImg = img1.getScaledInstance(450, 280, java.awt.Image.SCALE_SMOOTH);
				image1 = new ImageIcon(newImg);
				JLabel lblHinhAnh = new JLabel();
				lblHinhAnh.setIcon(image1);
				pnlCenterContentRight.add(lblHinhAnh, BorderLayout.WEST);

				Box blt = Box.createVerticalBox();
				Box b10 = Box.createHorizontalBox();
				Box b11 = Box.createHorizontalBox();
				Box b12 = Box.createHorizontalBox();
				Box b13 = Box.createHorizontalBox();
				Box b14 = Box.createHorizontalBox();
				pnlCenterContentRight.add(blt, BorderLayout.EAST);

				b10.add(Box.createHorizontalStrut(20));
				b10.add(lblMaLt);
				lblMaLt.setPreferredSize(lblLoaiPT.getPreferredSize());
				b10.add(txtMaLt);
				b11.add(Box.createHorizontalStrut(20));
				b11.add(lblTenLt);
				lblTenLt.setPreferredSize(lblLoaiPT.getPreferredSize());
				b11.add(txtTenLt);
				b12.add(Box.createHorizontalStrut(20));
				b12.add(lblNgayKH);
				lblNgayKH.setPreferredSize(lblLoaiPT.getPreferredSize());
				b12.add(ngayKH);
				b13.add(Box.createHorizontalStrut(20));
				b13.add(lblNgayKT);
				lblNgayKT.setPreferredSize(lblLoaiPT.getPreferredSize());
				b13.add(ngayKT);
				b14.add(Box.createHorizontalStrut(20));
				b14.add(lblLoaiTour);
				lblLoaiTour.setPreferredSize(lblLoaiPT.getPreferredSize());
				b14.add(rdbSang);
				b14.add(rdbThuong);
				rdbThuong.setSelected(true);

				blt.add(b10);
				blt.add(Box.createVerticalStrut(30));
				blt.add(b11);
				blt.add(Box.createVerticalStrut(30));
				blt.add(b12);
				blt.add(Box.createVerticalStrut(30));
				blt.add(b13);
				blt.add(Box.createVerticalStrut(30));
				blt.add(b14);
			}

			if (obj == btnTtPt) {
				pnlCenterContentRight.removeAll();
				pnlCenterContentRight.revalidate();
				pnlCenterContentRight.repaint();

				btnTtKH.setForeground(secondColor);
				btnTtTour.setForeground(secondColor);
				btnTtKS.setForeground(secondColor);
				btnTtLt.setForeground(secondColor);
				btnTtPt.setForeground(thirdColor);

				ImageIcon image1 = new ImageIcon(hinhAnh);
				Image img1 = image1.getImage();
				Image newImg = img1.getScaledInstance(450, 280, java.awt.Image.SCALE_SMOOTH);
				image1 = new ImageIcon(newImg);
				JLabel lblHinhAnh = new JLabel();
				lblHinhAnh.setIcon(image1);
				pnlCenterContentRight.add(lblHinhAnh, BorderLayout.WEST);

				Box bpt = Box.createVerticalBox();
				Box b15 = Box.createHorizontalBox();
				Box b16 = Box.createHorizontalBox();
				Box b17 = Box.createHorizontalBox();
				Box b18 = Box.createHorizontalBox();
				pnlCenterContentRight.add(bpt, BorderLayout.EAST);

				b15.add(Box.createHorizontalStrut(20));
				b15.add(lblMaPT);
				lblMaPT.setPreferredSize(lblLoaiPT.getPreferredSize());
				b15.add(txtMaPT);
				b16.add(Box.createHorizontalStrut(20));
				b16.add(lblLoaiPT);
				b16.add(txtLoaiPT);
				b17.add(Box.createHorizontalStrut(20));
				b17.add(lblTenTX);
				lblTenTX.setPreferredSize(lblLoaiPT.getPreferredSize());
				b17.add(txtTenTX);
				b18.add(Box.createHorizontalStrut(20));
				b18.add(lblSdtTaiXe);
				lblSdtTaiXe.setPreferredSize(lblLoaiPT.getPreferredSize());
				b18.add(txtSdtTaiXe);

				bpt.add(b15);
				bpt.add(Box.createVerticalStrut(30));
				bpt.add(b16);
				bpt.add(Box.createVerticalStrut(30));
				bpt.add(b17);
				bpt.add(Box.createVerticalStrut(30));
				bpt.add(b18);
			}
			if (obj == btnXuatFile) {

				int row1 = tblBooking.getSelectedRow();

				try {
					ve_dao = new ThongTinDatTour_DAO();
					List<thongTinDatTour> ves = ve_dao.getAllVe();
					int i = 0;

					// Create a new PDF document
					Document document = new Document();
					String filePath = "data/ve.pdf"; // Relative path to the data folder
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
					document.open();
					@SuppressWarnings("unused")
					Paragraph header = new Paragraph();

					document.add(new Paragraph("TRAVLVN"));
					document.add(new Paragraph("Dia Chi : 12 , Nguyen Van Bao , Phuong 14 , Go Vap,TPHCM"));
					document.add(new Paragraph("\n")); // Add spacing after the header
					document.add(new Paragraph("\n"));
					document.add(new Paragraph("Thong Tin Ve")); // Add spacing after the header

					for (thongTinDatTour ve : ves) {
						if (row1 == i) {

							String aaa = ve.getMaVe();
							String bbb = ve.getThongTinKhachhang().getMaKH();
							String ccc = ve.getThongTinKhachhang().getTenKH();
							String ddd = ve.getThongTinTour().getMaTour();
							String eee = ve.getThongTinTour().getTenTour();
							String fff = String.valueOf(ve.getThongTinTour().getThongTinLoTrinh().getNgayKhoiHanh());
							String ggg = String.valueOf(ve.getThongTinTour().getThongTinLoTrinh().getNgayKetThuc());
							String hhh = ve.getThongTinTour().getThongTinKhachSan().getTenKS();
							String iii = ve.getThongTinTour().getThongTinKhachSan().getDiaChi();
							String jjj = ve.getThongTinTour().getThongTinPhuongTien().getLoaiPhuongTien();
							String kkk = ve.getThongTinTour().getThongTinPhuongTien().getSDT();
							String lll = ma;
							String mmm = ten;
							int soluong = ve.getSoLuong();
							double totalCost = ve.getThongTinTour().getGiaTour() * ve.getSoLuong();

							Locale locale = new Locale("vi", "VN"); // định dạng địa phương cho tiền Việt Nam
							NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
							String formattedMoney = currencyFormatter.format(totalCost);

							document.add(new Paragraph("Ma Ve: " + aaa));
							document.add(new Paragraph("Ma Khach Hang: " + bbb));
							document.add(new Paragraph("Ten Khach Hang: " + ccc));
							document.add(new Paragraph("Ma Tour: " + ddd));
							document.add(new Paragraph("Tên Tour: " + eee));
							document.add(new Paragraph("Ngay Khoi Hanh: " + fff));
							document.add(new Paragraph("Ngay Ket Thuc: " + ggg));
							document.add(new Paragraph("Ten Khach San: " + hhh));
							document.add(new Paragraph("Dia Chi: " + iii));
							document.add(new Paragraph("Loai Phuong Tien: " + jjj));
							document.add(new Paragraph("So Dien Thoai: " + kkk));
							document.add(new Paragraph("Ma Nhan Vien Phu Trach: " + lll));
							document.add(new Paragraph("Ten Nhan Vien Phu Trach: " + mmm));
							document.add(new Paragraph("\n")); // Add some spacing between entries
							document.add(new Paragraph("-----------------------------------------------------------"));
							document.add(new Paragraph("So Luong: " + soluong));
							document.add(new Paragraph("Thanh Tien: " + formattedMoney));
						}
						i++;
					}

					document.close();
					writer.close();

					CustomJOptionPane.showMessageDialog("Tệp ve.pdf trong folder data");

					File file = new File("data\\ve.pdf");
					Desktop desktop = Desktop.getDesktop();
					if (file.exists()) {
						desktop.open(file);
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tblBooking.getSelectedRow();

			try {
				ve_dao = new ThongTinDatTour_DAO();
				List<thongTinDatTour> ves = ve_dao.getAllVe();
				int i = 0;
				for (thongTinDatTour ve : ves) {

					if (row == i) {

						txtMaKH.setText(ve.getThongTinKhachhang().getMaKH());
						txtTenKH.setText(ve.getThongTinKhachhang().getTenKH());
						txtSdt.setText(ve.getThongTinKhachhang().getSDT());
						String aa = String.valueOf(ve.getThongTinKhachhang().getCCCD());
						txtCccd.setText(aa);
						txtEmail.setText(ve.getThongTinKhachhang().getEmail());

						boolean b = ve.getThongTinKhachhang().isHoChieu();
						if (b) {
							rdbYes.setSelected(true);
							rdbNo.setSelected(false);
						} else {
							rdbNo.setSelected(true);
							rdbYes.setSelected(false);
						}

						boolean c = ve.getThongTinKhachhang().isGioiTinh();
						if (c) {
							rdbNam.setSelected(true);
							rdbNu.setSelected(false);
						} else {
							rdbNu.setSelected(true);
							rdbNam.setSelected(false);
						}

						txtMaT.setText(ve.getThongTinTour().getMaTour());
						txtTenT.setText(ve.getThongTinTour().getTenTour());

						Locale locale = new Locale("vi", "VN"); // định dạng địa phương cho tiền Việt Nam
						NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
						String formattedMoney = currencyFormatter.format(ve.getThongTinTour().getGiaTour());
						txtGiaT.setText(formattedMoney);

						txtMaKs.setText(ve.getThongTinTour().getThongTinKhachSan().getMaKS());
						txtTenKs.setText(ve.getThongTinTour().getThongTinKhachSan().getTenKS());
						txtDiaChiKs.setText(ve.getThongTinTour().getThongTinKhachSan().getDiaChi());
						txtSdtKs.setText(ve.getThongTinTour().getThongTinKhachSan().getSDT());

						txtMaLt.setText(ve.getThongTinTour().getThongTinLoTrinh().getMaLoTrinh());
						txtTenLt.setText(ve.getThongTinTour().getThongTinLoTrinh().getTenLoTrinh());
						Instant instant1 = ve.getThongTinTour().getThongTinLoTrinh().getNgayKhoiHanh().atStartOfDay()
								.atZone(ZoneId.systemDefault()).toInstant();
						java.util.Date date1 = Date.from(instant1);
						ngayKH.setDate(date1);
						Instant instant2 = ve.getThongTinTour().getThongTinLoTrinh().getNgayKetThuc().atStartOfDay()
								.atZone(ZoneId.systemDefault()).toInstant();
						java.util.Date date2 = Date.from(instant2);
						ngayKT.setDate(date2);

						boolean ee = ve.getThongTinTour().getThongTinLoTrinh().isLoaiTour();
						if (ee) {
							rdbSang.setSelected(true);
							rdbThuong.setSelected(false);
						} else {
							rdbThuong.setSelected(true);
							rdbSang.setSelected(false);
						}

						txtMaPT.setText(ve.getThongTinTour().getThongTinPhuongTien().getMaPhuongTien());
						txtLoaiPT.setText(ve.getThongTinTour().getThongTinPhuongTien().getLoaiPhuongTien());
						txtTenTX.setText(ve.getThongTinTour().getThongTinPhuongTien().getTenTaiXe());
						txtSdtTaiXe.setText(ve.getThongTinTour().getThongTinPhuongTien().getSDT());

						String ff = String.valueOf(ve.getSoLuong());
						txtSoLuong.setText(ff);
						txtSoLuong.setEditable(false);

						hinhAnh = "media\\\\" + ve.getThongTinTour().getMaTour() + ".png";

						btnTtKH.doClick();
					}
					i++;
				}

			} catch (Exception e2) {
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}

	public class thongKe extends JPanel implements ActionListener, MouseListener {
		private static final long serialVersionUID = 1L;

		public thongKe() {
			try {
				ConnectDB db = ConnectDB.getInstance();
				db.connect();
				@SuppressWarnings("static-access")
				Connection con = db.getConnection();

				// Thực hiện truy vấn để lấy dữ liệu thống kê từ cơ sở dữ liệu
				String sql = "SELECT Tour.maTour, Tour.tenTour, SUM(ThongTinDatTour.soLuong) AS SoLuong " + "FROM Tour "
						+ "LEFT JOIN ThongTinDatTour ON Tour.maTour = ThongTinDatTour.thongTinTour "
						+ "GROUP BY Tour.maTour, Tour.tenTour " + "ORDER BY SoLuong DESC";
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();

				// Tạo dataset cho biểu đồ
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();

				// Đổ dữ liệu từ ResultSet vào dataset
				while (rs.next()) {
					@SuppressWarnings("unused")
					String maTour = rs.getString("maTour");
					String tenTour = rs.getString("tenTour");
					int soLuong = rs.getInt("SoLuong");
					dataset.addValue(soLuong, "Số lượng", tenTour);
				}

				// Tạo biểu đồ cột
				JFreeChart chart = ChartFactory.createBarChart("Thống kê số lượng người tham gia của từng tour", // Tiêu
																													// đề
																													// biểu
																													// đồ
						"Tên tour", // Nhãn trục x
						"Số lượng người tham gia", // Nhãn trục y
						dataset, // Dataset chứa dữ liệu
						PlotOrientation.VERTICAL, // Hướng biểu đồ (dọc)
						true, // Có hiển thị legend hay không
						true, // Có tooltips (gợi ý) hay không
						false // Có URL hay không
				);
				Color darkCyan = new Color(0, 139, 139);
				chart.getCategoryPlot().getRenderer().setSeriesPaint(0, darkCyan);

				// Tạo và chèn ChartPanel chứa biểu đồ vào giao diện
				ChartPanel chartPanel = new ChartPanel(chart);
				setLayout(new BorderLayout());
				add(chartPanel, BorderLayout.CENTER);

				// Đóng kết nối cơ sở dữ liệu
				// db.disconnect();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

}