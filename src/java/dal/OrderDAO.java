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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Category;
import model.Item;
import model.Order;
import model.Product;
import model.Size;

/**
 *
 * @author admin
 */
public class OrderDAO extends DBContext {

    protected Connection connection;

    public void insertOrder(Account a, Item i) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();

        String sql = "INSERT INTO [dbo].[Order] ([AccountID], [ProductID], [quantity], [totalMoney],[date] )\n"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, a.getId());
            stm.setInt(2, i.getProduct().getId());
            stm.setInt(3, i.getQuantity());
            stm.setDouble(4, i.getPrice() * i.getQuantity());
            stm.setString(5, date);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Category getCategoryByID(int cid) {
        try {

            String query = "select * from Categories where cateid = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cid);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString(2));
                return c;
            }

        } catch (SQLException e) {
            System.err.println("ProductDAO -> getCategoryByID:\n" + e);
        }
        return null;
    }

    public List<Size> getSizeByPid(int pid) {
        List<Size> list = new ArrayList<>();

        try {

            String query = "select * from Size s inner join ProductSize ps\n"
                    + "on s.Id = ps.sizeId\n"
                    + "where ps.pid = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Size s = new Size(rs.getInt("id"), rs.getString("size"), rs.getInt("quantity"));
                list.add(s);
            }

        } catch (SQLException e) {
            System.err.println("ProductDAO -> getSizeByPid:\n" + e);
        }
        return list;
    }

    public Product getProductByID(int id) {
        try {
            String query = "select * from Product\n" + "where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int pid = rs.getInt("id");

                Category c = getCategoryByID(rs.getInt("categoryid"));

                List<Size> s = getSizeByPid(pid);

                return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                        rs.getString("description"), rs.getString("imageUrl"), rs.getInt("quantitySaled"),
                        rs.getInt("managerId"), rs.getDate("CreateDate"), rs.getDate("UpdateDate"),
                        rs.getInt("status"), c, s);

//                return new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getDate(9), rs.getDate(10), rs.getBoolean(11));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void insertOrderWithBuy(Account a, Product p, int quantity) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();

        String sql = "INSERT INTO [dbo].[Order] ([AccountID], [ProductID], [quantity], [totalMoney],[date] )\n"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, a.getId());
            stm.setInt(2, p.getId());
            stm.setInt(3, quantity);
            stm.setDouble(4, p.getPrice() * quantity);
            stm.setString(5, date);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Order> getOrderByAccID(int aid) throws Exception {
        List<Order> list = new ArrayList<>();
        String sql = "select * from [Order] where AccountID = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, aid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductDAO pdao = new ProductDAO();
                Product p = pdao.getProductByID(rs.getInt(3));
                Order o = new Order(rs.getInt(2), p, rs.getInt(4), rs.getDouble(5), rs.getDate(6));
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> getProductOfAccID(int aid) {
        List<Product> list = new ArrayList<>();

        String sql = "select p.* from [Order] o \n"
                + "  inner join [product] p on o.ProductID = p.id\n"
                + "  where AccountID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, aid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
//                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getInt(6),rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getDate(10), rs.getDate(11), rs.getBoolean(12)));
                int pid = rs.getInt("id");

                Category c = getCategoryByID(rs.getInt("categoryid"));

                List<Size> s = getSizeByPid(pid);

                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                        rs.getString("description"), rs.getString("imageUrl"), rs.getInt("quantitySaled"),
                        rs.getInt("managerId"), rs.getDate("CreateDate"), rs.getDate("UpdateDate"),
                        rs.getInt("status"), c, s));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
