/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import com.glb.objects.Book;
import com.glb.services.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kevin_Setayesh
 */
public class EditRecommendedItemServlet extends HttpServlet {
    
    BookService bookService;
    
    public void init() {
        System.out.println(getServletName() + ": initialised" );
        bookService = ServiceFactory.getBookService();
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
            out.println("<title>Servlet EditRecommendedItemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditRecommendedItemServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        String method = request.getParameter("editRecommendedBookMethod");
        String isbn = request.getParameter("isbn");
      //  String username = request.getParameter("username");
        int status = 0;
       
        if(method.equalsIgnoreCase("dismiss")){
            status = bookService.removeRecommendedItem(isbn);
            Map<String, Book> recommendedMap = bookService.getAllRecommendedBooks();
            HttpSession session = request.getSession();
            session.setAttribute("recommendedMap", recommendedMap);
        }
        else if(method.equalsIgnoreCase("purchase")){
           
        }
        else{
            //throw some exception
        }
        
        String url = "/adminRecommendedItemsList.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
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

    public void print(HttpServletRequest request, HttpServletResponse response, String str) throws IOException{
         try (PrintWriter out = response.getWriter()) {
             out.println(str);
         }
    }
    
}
