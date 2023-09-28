
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Ruan
 */
public class AccountDAO extends DBContext {

    Connection connection = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public Account checkLogin(String email, String password) {

        String sql = "select * from account where [email] = ? and [password] = ?";
        try {
            connection = new DBContext().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt(1),
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
                        rs.getInt(13)
                );

            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account getAccount(int id) {
        String sql = "select*from Account where uID = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
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
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Account getAccountByEmail(String email) {
        String sql = "select * from account where [email] = ?";
        try {
            connection = new DBContext().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt(1),
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
                        rs.getInt(13)
                );

            }
        } catch (Exception e) {
        }
        return null;
    }

    public int getAidByEmail(String email) {
        String sql = "select id from Account where [email] = ?";
        try {
            connection = new DBContext().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public boolean checkAccountExit(String email) {

        String sql = "select * from Account where [email] = ?";
        try {
            connection = new DBContext().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    

    public void newAccount(String email, String password, String fullName, int gender, String phoneNumber, String address, int loginWith) {
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
                + "           ,[roleid])"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            connection = new DBContext().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            stm.setString(3, fullName);
            stm.setInt(4, gender);
            stm.setString(5, phoneNumber);
            stm.setString(6, address);
            stm.setInt(7, loginWith);
            stm.setInt(8, 1);
            stm.setString(9, date);
            stm.setString(10, date);
            stm.setString(11, date);
            stm.setInt(12, 1);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertAcountLoginGoogle(String email, int loginWith) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();

        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([Email]\n"
                + "           ,[LoginWith]\n"
                + "           ,[Status]\n"
                + "           ,[LastDateLogin]\n"
                + "           ,[CreateDate]\n"
                + "           ,[UpdateDate]\n"
                + "           ,[roleid])"
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            connection = new DBContext().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setInt(2, loginWith);
            stm.setInt(3, 1);
            stm.setString(4, date);
            stm.setString(5, date);
            stm.setString(6, date);
            stm.setInt(7, 1);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateLastDateLogin(String email) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();

        String sql = "UPDATE [dbo].[Account]\n"
                + "SET [LastDateLogin] = ?\n"
                + "WHERE [email] = ?;";
        try {
            connection = new DBContext().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, date);
            stm.setString(2, email);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void changePassword(String email, String passwword) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();

        String sql = "UPDATE [dbo].[Account]\n"
                + "SET [Password] = ?,\n"
                + "    [UpdateDate] = ?,\n"
                + "    [LoginWith] = ?\n"
                + "WHERE [Email] = ?;";
        try {
            connection = new DBContext().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, passwword);
            stm.setString(2, date);
            stm.setInt(3, 1);
            stm.setString(4, email);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateProfile(int id ,String email, String fullname, int gender, String phoneNumber, String address) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();

        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [Email] = ?\n"
                + "      ,[Fullname] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[UpdateDate] = ?\n"
                + " WHERE [Id] = ?";
        try {
            connection = new DBContext().getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, fullname);
            stm.setInt(3, gender);
            stm.setString(4, phoneNumber);
            stm.setString(5, address);
            stm.setString(6, date);
            stm.setInt(7, id);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}

