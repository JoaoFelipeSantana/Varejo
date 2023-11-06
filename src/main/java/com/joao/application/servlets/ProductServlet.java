package com.joao.application.servlets;

import com.joao.application.DTOs.ProductDTOinput;
import com.joao.domain.ProductService;

import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

public class ProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder json = new StringBuilder();
        ProductService productService = new ProductService();

        JSONObject data;
        String line;

        PrintWriter out = response.getWriter();
        BufferedReader reader = request.getReader();

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        JSONObject data_request = new JSONObject(json.toString());

        String name = (String) data_request.get("nome");
        String description = (String) data_request.get("description");

        // Convertendo a String price para double
        String str_price = (String) data_request.get("price");
        str_price = str_price.replaceAll(",", ".");
        double price = Double.parseDouble(str_price);

        int amount = (int) data_request.get("amount");
        int stock_min = (int) data_request.get("stock_min");

        ProductDTOinput productDTO = new ProductDTOinput(name, description, price, amount, stock_min);

        try {
            data = productService.createProduct(productDTO);
            out.println(data.get("msg").toString());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}