package com.joao.domain;

import com.joao.application.DTOs.ProductDTOinput;
import com.joao.infrastructure.DAOs.ProductDAO;
import com.joao.infrastructure.entity.Product;

import org.json.JSONObject;

import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;

public class ProductService {

    public JSONObject createProduct(ProductDTOinput productDTO) throws SQLException, ClassNotFoundException {
        ResourceBundle rb = ResourceBundle.getBundle("messages");
        JSONObject data = new JSONObject();
        ProductDAO productDAO = new ProductDAO();

        String name;
        String description;
        double price;
        int amount;
        int stock_min;

        String str_msg;

        name = productDTO.getName_product();
        description = productDTO.getDesription_product();
        price = productDTO.getPrice();
        amount = productDTO.getAmount();
        stock_min = productDTO.getStock_min();

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

            // encaminhar a entidade Product para a DAO
            str_msg = productDAO.createProduct(product);
        }

        data.append("msg", str_msg);

        return data;
    }
}
