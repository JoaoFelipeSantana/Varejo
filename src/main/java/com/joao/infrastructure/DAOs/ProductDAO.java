package com.joao.infrastructure.DAOs;

import com.joao.infrastructure.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ResourceBundle;

public class ProductDAO {
    public Connection conectionDB_products() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/varejo", "root", "");

        return connect;
    }

    public String createProduct(Product product) throws SQLException, ClassNotFoundException {
        PreparedStatement stm;
        ResourceBundle rb = ResourceBundle.getBundle("messages");

        String dtupdate = null;

        String sql_command = "INSERT INTO products(name_product, description, price, amount, stock_min, dtcreate, dtupdate, status) VALUES (?, ?, ?, ?, ?, ?, ?, ? )";

        stm = conectionDB_products().prepareStatement(sql_command);
        stm.setString(1, product.getName_product());
        stm.setString(2, product.getDescription_product());
        stm.setDouble(3, product.getPrice());
        stm.setInt(4, product.getAmount());
        stm.setInt(5, product.getStock_min());
        stm.setString(6, product.getDtcreate());
        stm.setString(7, dtupdate);
        stm.setBoolean(8, product.isStatus_product());

        stm.execute();

        return rb.getString("success.createProduct");
    }
}
