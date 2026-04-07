package com.lab.controller;

import com.lab.dao.ProductDAO;
import com.lab.model.Product;
import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO(); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // 1. Capture parameters from the HTML form
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // 2. Create a new Product object
            // This requires the (String, String, double, int) constructor in Product.java
            Product newProduct = new Product(name, category, price, quantity);

            // 3. Call DAO to insert into database
            productDAO.insertProduct(newProduct);

            // 4. Redirect to the View servlet
            response.sendRedirect("ViewProductServlet");
            
        }catch (Exception e) {
            e.printStackTrace();       
        }
    }
}