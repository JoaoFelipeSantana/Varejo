package com.joao.infrastructure.DAOs;

import com.joao.infrastructure.entity.ProductCreate;
import com.joao.infrastructure.entity.ProductUpdate;

import java.sql.*;

import java.util.ResourceBundle;

public class ProductDAO {
    public Connection conectionDB_products() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/varejo", "root", "");

        return connect;
    }

    public String createProduct(ProductCreate product) throws SQLException, ClassNotFoundException {
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

    public ResultSet selectALL() throws SQLException, ClassNotFoundException {
        String sql_command = "SELECT * FROM varejo.products";

        ResultSet rsProduct = conectionDB_products().createStatement().executeQuery(sql_command);
        return rsProduct;
    }

    public ResultSet selectONE(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm;

        String sql_command = "SELECT * FROM varejo.products WHERE id = ?";

        stm = conectionDB_products().prepareStatement(sql_command);
        stm.setInt(1, id);

        ResultSet rsProduct = stm.executeQuery();
        return rsProduct;
    }

    public String delete(int id) throws SQLException, ClassNotFoundException {
        ResourceBundle rb = ResourceBundle.getBundle("messages");

        PreparedStatement stm;

        String sql_command = "DELETE FROM varejo.products WHERE id = ?";

        stm = conectionDB_products().prepareStatement(sql_command);
        stm.setInt(1, id);

        stm.execute();

        return rb.getString("success.deleteProduct");
    }

    public String update(int id, ProductUpdate product) throws SQLException, ClassNotFoundException {
        ResourceBundle rb = ResourceBundle.getBundle("messages");

        PreparedStatement stm;

        String sql_command = "UPDATE varejo.products SET description = ?, price = ?, amount = ?, stock_min = ?, dtupdate = ? WHERE id = ?";

        stm = conectionDB_products().prepareStatement(sql_command);
        stm.setString(1, product.getDescription());
        stm.setDouble(2, product.getPrice());
        stm.setInt(3, product.getAmount());
        stm.setInt(4, product.getStock_min());
        stm.setString(5, product.getDtupdate());
        stm.setInt(6, id);

        stm.execute();

        return rb.getString("success.updateProduct");
    }
}
