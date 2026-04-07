/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lab.controller;

import com.lab.dao.ProductDAO;
import com.lab.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    // 1. Display the Edit Form with existing product data
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productDAO.selectProduct(id); // Call DAO [cite: 935]

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Edit Product</h2>");
        out.println("<form action='UpdateProductServlet' method='POST'>");
        
        // Hidden field to keep track of the ID during update
        out.println("<input type='hidden' name='id' value='" + existingProduct.getId() + "'>");

        out.println("Product Name: <input type='text' name='name' value='" + existingProduct.getName() + "' required><br><br>");
        out.println("Category: <input type='text' name='category' value='" + existingProduct.getCategory() + "' required><br><br>");
        out.println("Price: <input type='number' step='0.01' name='price' value='" + existingProduct.getPrice() + "' required><br><br>");
        out.println("Quantity: <input type='number' name='quantity' value='" + existingProduct.getQuantity() + "' required><br><br>");

        out.println("<input type='submit' value='Save Changes'>");
        out.println("</form>");
        out.println("<br><a href='ViewProductServlet'>Back to List</a>");
        out.println("</body></html>");
    }

    // 2. Process the updated data and save it to the database
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Capture modified parameters from the form [cite: 968]
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Create a product object with the ID
        Product updatedProduct = new Product(id, name, category, price, quantity);

        // Call DAO to update (No direct JDBC code here!) [cite: 969, 985]
        productDAO.updateProduct(updatedProduct);

        // Redirect back to the view list [cite: 970]
        response.sendRedirect("ViewProductServlet");
    }
}