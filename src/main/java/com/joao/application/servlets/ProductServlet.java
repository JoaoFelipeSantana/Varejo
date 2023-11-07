package com.joao.application.servlets;

import com.google.gson.JsonObject;
import com.joao.application.DTOs.ProductDTOinput;
import com.joao.domain.ProductService;

import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder json = new StringBuilder();
        ProductService productService = new ProductService();

        JSONObject data;
        String line;

        try {

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductService productService = new ProductService();

        PrintWriter out = response.getWriter();

        try {

            if(request.getParameter("id") == null) {
                ResultSet allProducts = productService.selectALLproducts();

                JsonObject products_list = new JsonObject();

                while(allProducts.next()) {
                    products_list.addProperty("id", allProducts.getInt("id"));
                    products_list.addProperty("name_product", allProducts.getString("name_product"));
                    products_list.addProperty("description", allProducts.getString("description"));
                    products_list.addProperty("price", allProducts.getFloat("price"));
                    products_list.addProperty("amount", allProducts.getInt("amount"));
                    products_list.addProperty("stock_min", allProducts.getInt("stock_min"));
                    products_list.addProperty("dtcreate", allProducts.getString("dtcreate"));
                    products_list.addProperty("dtupdate", allProducts.getString("dtupdate"));
                    products_list.addProperty("status", allProducts.getBoolean("status"));

                    out.println(products_list.toString());
                }
            }
            else{
                int id = Integer.parseInt(request.getParameter("id"));

                ResultSet oneProduct = productService.selectONEproduct(id);

                JsonObject product = new JsonObject();

                while(oneProduct.next()) {
                    product.addProperty("id", oneProduct.getInt("id"));
                    product.addProperty("name_product", oneProduct.getString("name_product"));
                    product.addProperty("description", oneProduct.getString("description"));
                    product.addProperty("price", oneProduct.getFloat("price"));
                    product.addProperty("amount", oneProduct.getInt("amount"));
                    product.addProperty("stock_min", oneProduct.getInt("stock_min"));
                    product.addProperty("dtcreate", oneProduct.getString("dtcreate"));
                    product.addProperty("dtupdate", oneProduct.getString("dtupdate"));
                    product.addProperty("status", oneProduct.getBoolean("status"));

                    out.println(product.toString());
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
