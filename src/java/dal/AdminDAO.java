/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Account;

/**
 *
 * @author MSI GF
 */
public class AdminDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4), 
                        rs.getInt(5), 
                        rs.getString(6),
                        rs.getString(7), 
                        rs.getInt(8), 
                        rs.getInt(9), 
                        rs.getDate(10), 
                        rs.getDate(11),
                        rs.getDate(12),
                        rs.getInt(13)));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public void insertAccount(String email, String password, String fullname,
            int gender, String phoneNumber, String address) {

        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();

        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([Email]\n"
                + "           ,[Password]\n"
                + "           ,[Fullname]\n"
                + "           ,[Gender]\n"
                + "           ,[Phone]\n"
                + "           ,[Address]\n"
                + "           ,[LoginWith]\n"
                + "           ,[Status]\n"
                + "           ,[LastDateLogin]\n"
                + "           ,[CreateDate]\n"
                + "           ,[UpdateDate]\n"
                + "           ,[roleid])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setInt(4, gender);
            ps.setString(5, phoneNumber);
            ps.setString(6, address);
            ps.setInt(7, 1);
            ps.setInt(8, 1);
            ps.setString(9, date);
            ps.setString(10, date);
            ps.setString(11, date);
            ps.setInt(12, 2);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Account getAccountByEmail(String email) {
        String sql = "select * from Account\n"
                + "where Email = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getDate(12),
                        rs.getInt(13));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account getAccountById(int id) {
        String sql = "select * from Account\n"
                + "where Id = ?\n";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getDate(12),
                        rs.getInt(13));
            }
        } catch (Exception e) {
        }
        return null;
    }


    public void updateAccount(String email, String fullname,
            String phoneNumber, String address, int status, int roleId, int id) {
        
         LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [Email] = ?\n"
                + "      ,[Fullname] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Status] = ?\n"
                + "      ,[UpdateDate] = ?\n"
                + "      ,[roleid] = ?\n"
                + " WHERE id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, fullname);
            ps.setString(3, phoneNumber);
            ps.setString(4, address);
            ps.setInt(5, status);
            ps.setString(6, date);
            ps.setInt(7, roleId);
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void insertAccount(String email, String password, String fullname, 
            int gender, int phoneNumber, String address, int loginWith, int status, 
            Date lastDateLogin, Date createDate, Date updateDate, int roleId){
        String sql = "INSERT INTO [dbo].[Account]\n" +
"           ([Email]\n" +
"           ,[Password]\n" +
"           ,[Fullname]\n" +
"           ,[Gender]\n" +
"           ,[Phone]\n" +
"           ,[Address]\n" +
"           ,[LoginWith]\n" +
"           ,[Status]\n" +
"           ,[LastDateLogin]\n" +
"           ,[CreateDate]\n" +
"           ,[UpdateDate]\n" +
"           ,[roleid])\n" +
"     VALUES\n" +
"           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setInt(4, gender);
            ps.setInt(5, phoneNumber);
            ps.setString(6, address);
            ps.setInt(7, loginWith);
            ps.setInt(8, status);
            ps.setDate(9, lastDateLogin);
            ps.setDate(10, createDate);
            ps.setDate(11, updateDate);
            ps.setInt(12, roleId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
   public List<Account> getAllAccountsByRoleId(int roleId) {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account\n"
                 + "where roleid = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4), 
                        rs.getInt(5), 
                        rs.getString(6),
                        rs.getString(7), 
                        rs.getInt(8), 
                        rs.getInt(9), 
                        rs.getDate(10), 
                        rs.getDate(11),
                        rs.getDate(12),
                        rs.getInt(13)));
            }
        } catch (Exception e) {

        }
        return list;
    }
    
    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
        List<Account> list = dao.getAllAccountsByRoleId(1);
        
        for (Account a : list) {
            System.out.println(a);
        }
        
        
        
    }
}
