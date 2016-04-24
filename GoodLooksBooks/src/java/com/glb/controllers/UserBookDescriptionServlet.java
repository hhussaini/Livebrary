package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.println;
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

/**
 *
 * @author Kevin_Setayesh
 */
public class UserBookDescriptionServlet extends HttpServlet {
    
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
        println("Inside UserBookDescriptionServlet.processRequest");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserBookDescriptionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserBookDescriptionServlet at " + request.getContextPath() + "</h1>");
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
        println("Inside UserBookDescriptionServlet.doGet");
        processRequest(request, response);
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
          //  processRequest(request, response);
          // returns the isbn of the book clicked, so we can go to the database and query for that book
          println("Inside UserBookDescriptionServlet.doPost");
          String isbn = request.getParameter("hiddenFormID"); 
          Book book = bookService.getBookByIsbn(isbn);;
          String url = "/userBookDescription.jsp";
          HttpSession session = request.getSession();
          if(book != null){
               session.setAttribute("itemClicked", book); 
               RequestDispatcher dispatcher = request.getRequestDispatcher(url);
               dispatcher.forward(request, response); 
          }
          else{
               throw new ServletException("Error getting book with isbn = " + (isbn == null ? "NULL" : isbn));
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
