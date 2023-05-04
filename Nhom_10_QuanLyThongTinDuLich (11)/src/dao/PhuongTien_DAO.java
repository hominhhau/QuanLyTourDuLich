package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.PhuongTien;

public class PhuongTien_DAO {
    public PhuongTien_DAO() {
    	
    }

    public ArrayList<PhuongTien> getAllPhuongTien() {
        ArrayList<PhuongTien> dsPhuongTien = new ArrayList<PhuongTien>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhuongTien";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String maPhuongTien = rs.getString(1);
                String loaiPhuongTien = rs.getString(2);
                String tenTaiXe = rs.getString(3);
                String SDT = rs.getString(4);
                PhuongTien phuongTien = new PhuongTien(maPhuongTien, loaiPhuongTien, tenTaiXe, SDT);
                dsPhuongTien.add(phuongTien);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhuongTien;
    }

    public boolean create(PhuongTien phuongTien) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO PhuongTien (maPhuongTien, loaiPhuongTien, tenTaiXe, SDT) VALUES (?, ?, ?, ?)");
            stmt.setString(1, phuongTien.getMaPhuongTien());
            stmt.setString(2, phuongTien.getLoaiPhuongTien());
            stmt.setString(3, phuongTien.getTenTaiXe());
            stmt.setString(4, phuongTien.getSDT());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public boolean delete(String maPhuongTien) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM PhuongTien WHERE maPhuongTien = ?");
            stmt.setString(1, maPhuongTien);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }public boolean update(PhuongTien phuongTien) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE PhuongTien SET loaiPhuongTien = ?, tenTaiXe = ?, SDT = ? WHERE maPhuongTien = ?");
            stmt.setString(1, phuongTien.getLoaiPhuongTien());
            stmt.setString(2, phuongTien.getTenTaiXe());
            stmt.setString(3, phuongTien.getSDT());
            stmt.setString(4, phuongTien.getMaPhuongTien());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
}