package com.joao.infrastructure.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductDAO {
    public Connection conectionDB_products() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mercado", "root", "");

        return connect;
    }


}
