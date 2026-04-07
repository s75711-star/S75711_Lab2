/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lab.controller;

import com.lab.dao.ProductDAO;
import com.lab.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve data using the DAO (Strict rule: No JDBC here!)
        List<Product> listProduct = productDAO.selectAllProducts();

        out.println("<html><body>");
        out.println("<h2>Product Inventory List</h2>");
        out.println("<a href='add_product.html'>Add New Product</a><br><br>");
        
        out.println("<table border='1' cellpadding='5'>");
        out.println("<tr><th>ID</th><th>Name</th><th>Category</th><th>Price</th><th>Quantity</th><th>Actions</th></tr>");

        for (Product product : listProduct) {
            out.println("<tr>");
            out.println("<td>" + product.getId() + "</td>");
            out.println("<td>" + product.getName() + "</td>");
            out.println("<td>" + product.getCategory() + "</td>");
            out.println("<td>" + String.format("%.2f", product.getPrice()) + "</td>");
            out.println("<td>" + product.getQuantity() + "</td>");
            out.println("<td><a href='UpdateProductServlet?id=" + product.getId() + "'>Edit</a> | ");
            out.println("<a href='DeleteProductServlet?id=" + product.getId() + "'>Delete</a></td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}