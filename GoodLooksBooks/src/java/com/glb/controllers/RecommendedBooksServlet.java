/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.Book;
import com.glb.objects.SearchResult;
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
public class RecommendedBooksServlet extends HttpServlet {
   
    BookService bookService;
     
    public void init() {
        println(getServletName() + ": initialised" );
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
            out.println("<title>Servlet RecommendedBooksServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RecommendedBooksServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

          Map<String, Book> recommendedMap = bookService.getAllRecommendedBooks();
          HttpSession session = request.getSession();
          session.setAttribute("recommendedMap", recommendedMap);
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
    
    private void print(HttpServletRequest request, HttpServletResponse response, Map<String,Book>map) throws IOException{
//        String str = "";
//        for (String key : map.keySet()) {
//            str = str + map.get(key).toString() + "\n\n";
//        }
        
          try (PrintWriter out = response.getWriter()) {
             if(map == null){
                 out.println("Is null");
             }
             else{
                  out.println("Is not null");
             }
          }
    }
    
     private void print(HttpServletRequest request, HttpServletResponse response, String str) throws IOException{
        
          try (PrintWriter out = response.getWriter()) {
              out.println(str);
          }
    }

}
