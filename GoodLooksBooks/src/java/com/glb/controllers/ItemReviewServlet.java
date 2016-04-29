/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.println;
import com.glb.helpers.JsonHandler;
import com.glb.objects.Book;
import com.glb.objects.Review;
import com.glb.objects.User;
import com.glb.services.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
import static com.glb.helpers.Helpers.println;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
 


/**
 *
 * @author Kevin_Setayesh
 */
public class ItemReviewServlet extends HttpServlet {
    
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
            out.println("<title>Servlet ItemReviewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemReviewServlet at " + request.getContextPath() + "</h1>");
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
      //  processRequest(request, response);
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
    //    processRequest(request, response);
        response.setContentType("application/json");
        int numOfStars = Integer.parseInt(request.getParameter("numOfStars"));
        String isbn = request.getParameter("isbn"); 
        HttpSession session = request.getSession();
       
        User user = (User)session.getAttribute("user");
        Review review = new Review();
        review.setRating(numOfStars);  
        Book book = bookService.getBookByIsbn(isbn);  
        book = bookService.addReview(review, book, user);
        
        try {
           response.getWriter().print(JsonHandler.createJSONObj(book));
        } catch (JSONException ex) {
            Logger.getLogger(ItemReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonobj = new JSONObject(); 
//        try {
//            jsonobj.put("isbn", book.getIsbn());
//            jsonobj.put("title", book.getTitle());
//            jsonArray.put(jsonobj);
//            
//            // response.getWriter().print(jso.);
//        } catch (JSONException ex) {
//            Logger.getLogger(ItemReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          response.getWriter().print(jsonArray);
         
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

    public void printData(HttpServletRequest request, HttpServletResponse response, String str) throws IOException{
          try (PrintWriter out = response.getWriter()) { 
            out.println(str);
          }
    }
    
}
