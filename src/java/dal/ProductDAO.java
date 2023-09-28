/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;
import model.ProductSize;
import model.Size;

public class ProductDAO extends DBContext {

    Connection connection = null;

    public List<Product> getProduct(String[] category, String[] size, String search, String sort, String price) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where 1=1 ";

        try {
            connection = new DBContext().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (size != null && size.length > 0) {
                String subQuery = "select * from product p inner join ProductSize ps on p.id = ps.pid WHERE 1=1 ";
                subQuery += " AND sizeid IN (";
                for (int i = 0; i < size.length; i++) {
                    if (i == size.length - 1) {
                        subQuery += size[i] + ")";
                    } else {
                        subQuery += size[i] + ", ";
                    }
                }
                query = subQuery;
            }

            if (category != null && category.length > 0) {
                query += " AND categoryid IN (";
                for (int i = 0; i < category.length; i++) {
                    if (i == category.length - 1) {
                        query += category[i] + ")";
                    } else {
                        query += category[i] + ", ";
                    }
                }
            }

            if (search != null && !search.isEmpty()) {
                query += " AND name like '%" + search + "%'";
            }

            if (price != null && !price.isEmpty()) {
                double priceFrom = 0;
                double priceTo = 0;
                switch (price) {
                    case "100-150":
                        priceFrom = 1000000;
                        priceTo = 1500000;
                        break;
                    case "150-200":
                        priceFrom = 1500000;
                        priceTo = 2000000;
                        break;
                    case "200-250":
                        priceFrom = 2000000;
                        priceTo = 2500000;
                        break;
                    case "250-300":
                        priceFrom = 2500000;
                        priceTo = 3000000;
                        break;
                    case "300-350":
                        priceFrom = 3000000;
                        priceTo = 3500000;
                        break;
                    case "350-more":
                        priceFrom = 3500000;
                        priceTo = getLastPrice();
                }

                query += " AND price between " + priceFrom + " AND " + priceTo;
            }

            if (sort != null && !sort.isEmpty()) {
                switch (sort) {
                    case "1":
                        query += " ORDER BY price ASC";
                        break;
                    case "2":
                        query += " ORDER BY price DESC";
                        break;
                    case "3":
                        query += " ORDER BY name ASC";
                        break;
                }
            }

            while (rs.next()) {

                int pid = rs.getInt("id");

                Category c = getCategoryByID(rs.getInt("categoryid"));

                List<Size> s = getSizeByPid(pid);

                Product p = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                        rs.getString("description"), rs.getString("imageUrl"), rs.getInt("quantitySaled"),
                        rs.getInt("managerId"), rs.getDate("CreateDate"), rs.getDate("UpdateDate"),
                        rs.getInt("status"), c, s);
                list.add(p);
            }

        } catch (Exception e) {
//            System.err.println("ProductDAO -> getProduct:\n" + e);
        }

        return list;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product";
        try {
            connection = new DBContext().getConnection();

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            rs = ps.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("id");

                Category c = getCategoryByID(rs.getInt("categoryid"));

                List<Size> s = getSizeByPid(pid);

                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                        rs.getString("description"), rs.getString("imageUrl"), rs.getInt("quantitySaled"),
                        rs.getInt("managerId"), rs.getDate("CreateDate"), rs.getDate("UpdateDate"),
                        rs.getInt("status"), c, s));
            }
        } catch (Exception e) {

        }

        return list;
    }

    public List<Size> getSizeByPid(int pid) {
        List<Size> list = new ArrayList<>();

        try {

            String query = "select * from Size s inner join ProductSize ps\n"
                    + "on s.Id = ps.sizeId\n"
                    + "where ps.pid = ?";
            connection = new DBContext().getConnection();

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Size s = new Size(rs.getInt("id"), rs.getString("size"), rs.getInt("quantity"));
                list.add(s);
            }

        } catch (Exception e) {
//            System.err.println("ProductDAO -> getSizeByPid:\n" + e);
        }
        return list;
    }

    public Category getCategoryByID(int cid) {
        try {

            String query = "select * from Categories where cateid = ?";
            connection = new DBContext().getConnection();

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cid);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString(2));
                return c;
            }

        } catch (Exception e) {
//            System.err.println("ProductDAO -> getCategoryByID:\n" + e);
        }
        return null;
    }

    public List<Product> pagination(List<Product> before, int start, int end) {
        List<Product> after = new ArrayList<>();

        for (int i = start; i < end; i++) {
            after.add(before.get(i));
        }

        return after;
    }

    public double getLastPrice() {
        try {

            String query = "select MAX(price) from product";
            connection = new DBContext().getConnection();

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
//            System.err.println("ProductDAO -> getCategoryByID:\n" + e);
        }
        return 0;
    }

    public Product getProductByID(int id) {
        try {
            String query = "select * from Product where id = ?";
            connection = new DBContext().getConnection();

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

    public List<Product> get4ProductNew() {
        List<Product> list = new ArrayList<>();

        try {
            String query = "SELECT TOP 4 * FROM Product order by id desc;";
            connection = new DBContext().getConnection();

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getDate(9), rs.getDate(10), rs.getBoolean(11)));
                int pid = rs.getInt("id");

                Category c = getCategoryByID(rs.getInt("categoryid"));

                List<Size> s = getSizeByPid(pid);

                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                        rs.getString("description"), rs.getString("imageUrl"), rs.getInt("quantitySaled"),
                        rs.getInt("managerId"), rs.getDate("CreateDate"), rs.getDate("UpdateDate"),
                        rs.getInt("status"), c, s));
            }
        } catch (Exception e) {
        }
        return list;

    }

    public List<ProductSize> getListSize(int pid) {
        List<ProductSize> list = new ArrayList<>();
        String query = "SELECT Product.id, Size.size, ProductSize.quantity\n"
                + "FROM (Product INNER JOIN ProductSize ON Product.id = ProductSize.pid)\n"
                + "INNER JOIN Size ON ProductSize.sizeId = Size.Id\n"
                + "Where Product.id = ? AND ProductSize.quantity > 0";

        try {
            connection = new DBContext().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductSize(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
        } catch (Exception e) {

        }

        return list;
    }

    public Category getCategoryByPid(int pid) {
        try {
            String query = "SELECT Product.id, Categories.Name\n"
                    + "FROM Product LEFT JOIN Categories ON Product.categoryid = Categories.cateid\n"
                    + "Where Product.id = ?";
            connection = new DBContext().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Category(rs.getInt(1), rs.getString(2));
            }
        } catch (Exception e) {
        }
        return null;
    }

//    public List<Product> getProductByPrice(int priceMin, int priceMax) {
//        List<Product> list = new ArrayList<>();
//        String query = "select * from Product as p\n"
//                + "where p.price between ? and ?";
//        try {
//            conn = new DBContext().getConnection();//mo ket noi voi sql
//            ps = conn.prepareStatement(query);
//            ps.setInt(1, priceMin);
//            ps.setInt(2, priceMax);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Product(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getDouble(3),
//                        rs.getDouble(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getInt(7),
//                        rs.getInt(8),
//                        rs.getInt(9),
//                        rs.getInt(10),
//                        rs.getInt(11)));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
//
//    public List<Product> getAllProductOderByAsc() {
//        List<Product> list = new ArrayList<>();
//        String query = "select * from Product\n"
//                + "order by price asc";
//        try {
//            conn = new DBContext().getConnection();//mo ket noi voi sql
//            ps = conn.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Product(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getDouble(3),
//                        rs.getDouble(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getInt(7),
//                        rs.getInt(8),
//                        rs.getInt(9),
//                        rs.getInt(10),
//                        rs.getInt(11)));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//
//    }
//
//    public List<Product> getAllProductOderByDesc() {
//        List<Product> list = new ArrayList<>();
//        String query = "select * from Product\n"
//                + "order by price desc";
//        try {
//            conn = new DBContext().getConnection();//mo ket noi voi sql
//            ps = conn.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Product(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getDouble(3),
//                        rs.getDouble(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getInt(7),
//                        rs.getInt(8),
//                        rs.getInt(9),
//                        rs.getInt(10),
//                        rs.getInt(11)));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
}
