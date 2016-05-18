package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.Book;
import com.glb.services.BookService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kevin Young
 */
public class CollectionsServlet extends HttpServlet {

   BookService bookService;
    
   public void init() {
       println(getServletName() + ": initialised" );
       bookService = ServiceFactory.getBookService();
   }
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
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
      List<Book> recentlyAddedItems;
      List<Book> mostBorrowedItems;
      List<Book> unpopularItems;
      mostBorrowedItems = bookService.getMostBorrowed();
      recentlyAddedItems = bookService.getRecentlyAdded();
      unpopularItems = bookService.getUnpopularBooks(10);
      session.setAttribute("mostBorrowedItems", mostBorrowedItems);
      session.setAttribute("recentlyAddedItems", recentlyAddedItems);
      session.setAttribute("unpopularItems", unpopularItems);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/collections.jsp");
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
