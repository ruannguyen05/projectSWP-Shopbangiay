/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author MSI GF
 */
public class Order {
    private int id;
    private int AccountID;
    private int ProductID;
    private int size;
    private int quantity;
    private float totalMoney;
    private String CreateDate;

    public Order() {
    }

    public Order(int id, int AccountID, int ProductID, int size, int quantity, float totalMoney, String CreateDate) {
        this.id = id;
        this.AccountID = AccountID;
        this.ProductID = ProductID;
        this.size = size;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
        this.CreateDate = CreateDate;
    }

    public Order(int aInt, Product p, int aInt0, int aInt1, int aInt2, float aFloat, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Order(int aInt, Product p, int aInt0, double aDouble, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }
    
    
}
