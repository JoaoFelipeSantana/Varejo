package com.joao.domain;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.joao.application.DTOs.ProductDTOinput;
import com.joao.infrastructure.DAOs.ProductDAO;
import com.joao.infrastructure.entity.Product;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;

public class ProductService {

    public JSONObject createProduct(ProductDTOinput productDTO) throws SQLException, ClassNotFoundException {
        ResourceBundle rb = ResourceBundle.getBundle("messages");
        JSONObject data = new JSONObject();
        ProductDAO productDAO = new ProductDAO();

        String name = productDTO.getName_product();
        String description = productDTO.getDesription_product();
        double price = productDTO.getPrice();
        int amount = productDTO.getAmount();
        int stock_min = productDTO.getStock_min();

        String str_msg;


        if (name.equals("")) {
            str_msg = rb.getString("error.emptyName");
        }
        else if(description.equals("")) {
            str_msg = rb.getString("error.emptyDescription");
        }
        else if (price == 0) {
            str_msg = rb.getString("error.priceValue");
        }
        else if (stock_min <= 0) {
            str_msg = rb.getString("error.stock_minValue");
        }
        else if (amount < stock_min) {
            str_msg = rb.getString("error.amountBottomMin");
        }
        else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dtcreate = LocalDateTime.now();

            String dtupdate = "null";

            Boolean status_product = true;

            Product product = new Product(name, description, price, amount, stock_min, dtf.format(dtcreate), dtupdate, status_product);

            str_msg = productDAO.createProduct(product);
        }

        data.append("msg", str_msg);
        return data;
    }

    public JsonArray selectALLproducts() throws SQLException, ClassNotFoundException {
        ProductDAO productDAO = new ProductDAO();

        ResultSet allProducts = productDAO.selectALL();

        JsonArray products_list = new JsonArray();
        JsonObject product = new JsonObject();

        while(allProducts.next()) {
            product.addProperty("id", allProducts.getInt("id"));
            product.addProperty("name_product", allProducts.getString("name_product"));
            product.addProperty("description", allProducts.getString("description"));
            product.addProperty("price", allProducts.getFloat("price"));
            product.addProperty("amount", allProducts.getInt("amount"));
            product.addProperty("stock_min", allProducts.getInt("stock_min"));
            product.addProperty("dtcreate", allProducts.getString("dtcreate"));
            product.addProperty("dtupdate", allProducts.getString("dtupdate"));
            product.addProperty("status", allProducts.getBoolean("status"));

            products_list.add(product);
        }

        return products_list;
    }

    public JsonObject selectONEproduct(int id) throws SQLException, ClassNotFoundException {
        ProductDAO productDAO = new ProductDAO();
        JsonObject oneProduct = new JsonObject();

        ResourceBundle rb = ResourceBundle.getBundle("messages");

        ResultSet allProducts = productDAO.selectALL();

        int flag = 2;

        while (allProducts.next()) {
            if (allProducts.getInt("id") == id) {
                flag = 1;
            }
        }

        if (flag == 1) {
            ResultSet product = productDAO.selectONE(id);

            while(product.next()) {
                oneProduct.addProperty("id", product.getInt("id"));
                oneProduct.addProperty("name_product", product.getString("name_product"));
                oneProduct.addProperty("description", product.getString("description"));
                oneProduct.addProperty("price", product.getFloat("price"));
                oneProduct.addProperty("amount", product.getInt("amount"));
                oneProduct.addProperty("stock_min", product.getInt("stock_min"));
                oneProduct.addProperty("dtcreate", product.getString("dtcreate"));
                oneProduct.addProperty("dtupdate", product.getString("dtupdate"));
                oneProduct.addProperty("status", product.getBoolean("status"));
            }
        }
        else if(flag == 2) {
            oneProduct.addProperty("msg", rb.getString("error.findProduct"));
        }
        return oneProduct;
    }

    public JsonObject deleteProduct(int id) throws SQLException, ClassNotFoundException {
        ProductDAO productDAO = new ProductDAO();
        JsonObject product = new JsonObject();

        ResourceBundle rb = ResourceBundle.getBundle("messages");
        ResultSet allProducts = productDAO.selectALL();

        int flag = 2;
        while (allProducts.next()) {
            if (allProducts.getInt("id") == id) {
                flag = 1;
            }
        }

        if (flag == 1) {
            String msg_delete = productDAO.delete(id);

            product.addProperty("msg", msg_delete);
        }
        else if (flag == 2) {
            product.addProperty("msg", rb.getString("error.findProduct"));
        }

    return product;
    }
}
