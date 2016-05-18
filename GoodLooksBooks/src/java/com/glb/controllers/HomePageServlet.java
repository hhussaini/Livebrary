/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.goToSignIn;
import com.glb.objects.Book;
import com.glb.objects.Item;
import com.glb.objects.User;
import com.glb.services.BookService;
import com.glb.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hamza
 */
public class HomePageServlet extends HttpServlet {
    
    UserService userService;
    BookService bookService;
    
    public void init() {
        System.out.println(getServletName() + ": initialised" );
        userService = ServiceFactory.getUserService();
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
            out.println("<title>Servlet HomePageServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomePageServlet at " + request.getContextPath() + "</h1>");
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
        
        
        HttpSession session = request.getSession();
        
        int count1 = 0;
        int count3 = 0;
        
      
        Map<String, Book> recommended = bookService.getAllRecommendedBooks();
        session.setAttribute("recommendedMap", recommended);
        
        
        List<Book> allBooklist;
        List<Book> bestSellerlist = new ArrayList<Book>();
        allBooklist = bookService.getAllBooks();
        for (Book book : allBooklist) {
            if (book.getCopiesLeft() < 10){
                bestSellerlist.add(book);
                count1++;
                if (count1 == 8){
                break;
            }
            }
        }
        Item.ItemSorting.sort = 5;
        Collections.sort(bestSellerlist);
        session.setAttribute("bestSellerlist", bestSellerlist);
        
        
        List<Book> newBooklist = new ArrayList<Book>();
        for (Book book : allBooklist) {      
                newBooklist.add(book);
                count3++;
                if (count3 == 8){
                    break;
                }
        }
        Item.ItemSorting.sort = 3;
        Collections.sort(newBooklist);
        session.setAttribute("newBooklist", newBooklist);
        
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
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

}
