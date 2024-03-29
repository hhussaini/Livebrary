package com.glb.controllers;

import com.glb.constants.UserTypes;
import com.glb.factories.ServiceFactory;
import com.glb.objects.Book;
import com.glb.services.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.Review;
import com.glb.objects.User;
import java.util.Map;

/**
 *
 * @author Kevin_Setayesh
 */
public class BookDescriptionServlet extends HttpServlet {
    
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
        println("Inside BookDescriptionServlet.processRequest");
        // returns the isbn of the book clicked, so we can go to the database and query for that book
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
        println("Inside BookDescriptionServlet.doGet");
        // Check for any expired checkouts
        bookService.checkExpiredCheckouts();
        bookService.checkHolds();
        //processRequest(request, response);
        String isbn = request.getParameter("isbn"); 
        Book book = bookService.getBookByIsbn(isbn);
        String url = "/bookDescription.jsp";
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(book != null){ 
             println("Clicked " + isbn);
             Map<String, Review> reviewsMap = bookService.getAllReviewsForBook(isbn);
             book.setReviews(reviewsMap);
             if(user != null){
                if (user.getType().equalsIgnoreCase(UserTypes.CUSTOMER.toString())) {
                  book.updateOrderOfReviews(user.getUsername());
                }
             }
             session.setAttribute("itemClicked", book);
             String itemAccess = "borrow";
             if (user != null) {
                itemAccess = bookService.getItemAccess(user, book);
             }
             session.setAttribute("itemAccess", itemAccess);
             RequestDispatcher dispatcher = request.getRequestDispatcher(url);
             dispatcher.forward(request, response); 
        } 
        else{
             throw new ServletException("Error getting book with isbn = " + (isbn == null ? "NULL" : isbn));
        }
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
          //processRequest(request, response);
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
}
