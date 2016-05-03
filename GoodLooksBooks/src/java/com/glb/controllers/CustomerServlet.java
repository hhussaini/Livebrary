/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.*;
import com.glb.objects.Book;
import com.glb.objects.User;
import com.glb.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author PaulMan
 */
public class CustomerServlet extends HttpServlet {

    
    UserService userService;
    
    public void init() {
        System.out.println(getServletName() + ": initialised" );
        userService = ServiceFactory.getUserService();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        println(this.getServletName() + " : doGet");
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        List<Book> checkedOut = userService.getCheckedOutItems(user);
        session.setAttribute("checkedOutItems", checkedOut);
        List<Book> onHold = userService.getOnHoldItems(user);
        session.setAttribute("onHoldItems", onHold);
        println(onHold.size());
        List<Book> wishlist = userService.getWishlist(user);
        session.setAttribute("customerWishlist", wishlist);
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customerIndex.jsp");
        dispatcher.forward(request, response); 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        println(this.getServletName() + " : doGet");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
