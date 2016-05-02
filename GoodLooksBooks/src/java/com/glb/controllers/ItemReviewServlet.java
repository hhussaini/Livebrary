/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.controllers;

import com.glb.factories.ServiceFactory;
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
import org.json.JSONException;

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
   
        response.setContentType("application/json");
        String method = request.getParameter("method");
        String isbn = request.getParameter("isbn"); 
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        switch(method){
            case "add"    :  addReview(request, response, user, isbn, session);     break;
            case "delete" :  deleteReview(request, response, user, isbn);           break;
            case "edit"   :  editReview(request, response, user, isbn, session);      
        } 
         
    }  
    
    private void addReview(HttpServletRequest request, HttpServletResponse response, User user, String isbn, HttpSession session) throws IOException{
       
        String text = request.getParameter("text"); 
        int numOfStars = Integer.parseInt(request.getParameter("numOfStars")); 
        Review review = new Review(numOfStars, text); 
        Book book = bookService.getBookByIsbn(isbn);  
        Map<String,Review>reviewsMap = bookService.getAllReviewsForBook(isbn);
        bookService.addReview(review, book, user);  //returns an int (status)
        reviewsMap.put(user.getUsername(), review);
        book.setReviews(reviewsMap);
        book.updateOrderOfReviews(user.getUsername());
        session.setAttribute("itemClicked", book); 
        try {
            response.getWriter().print(JsonHandler.createJSONObj(user, book));
        } catch (JSONException ex) {
            Logger.getLogger(ItemReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    private void deleteReview(HttpServletRequest request, HttpServletResponse response, User user, String isbn) throws IOException{
        bookService.deleteReview(isbn, user.getUsername());
        double newAvgRating = bookService.getBookByIsbn(isbn).getAvgRating();
        response.getWriter().print(newAvgRating);
        
    }
    
    private void editReview(HttpServletRequest request, HttpServletResponse response, User user, String isbn, HttpSession session) throws IOException{
        String text = request.getParameter("text"); 
        int numOfStars = Integer.parseInt(request.getParameter("numOfStars")); 
        Review review = new Review(numOfStars, text); 
        Book book = bookService.getBookByIsbn(isbn);  
        Map<String,Review>reviewsMap = bookService.getAllReviewsForBook(isbn);
        bookService.editReview(review, book.getIsbn(), user.getUsername());  //returns an int (status)
        reviewsMap.put(user.getUsername(), review);
        book.setReviews(reviewsMap);
        book.updateOrderOfReviews(user.getUsername());
        session.setAttribute("itemClicked", book); 
        try {
            response.getWriter().print(JsonHandler.createJSONObj(user, book));
        } catch (JSONException ex) {
            Logger.getLogger(ItemReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
     
    public static void printData(HttpServletRequest request, HttpServletResponse response, Map<String, Review>map) throws IOException{
        try (PrintWriter out = response.getWriter()) {
            out.println("In the print statement");
            for(String username : map.keySet()){
                out.println(username + ": " + map.get(username));
           }
        }
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
