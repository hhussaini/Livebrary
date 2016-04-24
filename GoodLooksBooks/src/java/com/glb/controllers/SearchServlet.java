package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.*;
import com.glb.objects.Book;
import com.glb.services.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author PaulMan
 * With help from FileController.java
 */
public class SearchServlet extends HttpServlet {
    
    BookService bookService;
    List<Book> searchResults;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void init() {
        System.out.println(getServletName() + ": initialised" );
        //bookDao = new BookDao();
        bookService = ServiceFactory.getBookService();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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
        try {
            
            //processRequest(request, response);
            HttpSession session = request.getSession();
            String term = request.getParameter("searchTerm");
            println("Searched! " + term);
            term = (term == null) ? "" : term;
            
            int page = 1;
            int recordsPerPage = 18;
            
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            
            int offset = (page-1) * recordsPerPage;
            println(offset);
            
            searchResults = bookService.searchBooks(term, offset, recordsPerPage);
            
            int numOfResults = bookService.getNumberOfResults();
            println("Num results " + numOfResults);
            int numOfPages = (int) Math.ceil(numOfResults * 1.0 / recordsPerPage);
            session.setAttribute("searchResults", searchResults);
            request.setAttribute("numOfPages", numOfPages - 1);
            request.setAttribute("currentPage", page);
            
            int firstPage = (page - 5 < 1) ? 1 : page - 5;
            int lastPage = (page + 5 > numOfPages) ? numOfPages - 1 : page + 5;
            request.setAttribute("firstPage", firstPage);
            request.setAttribute("lastPage", lastPage);
            session.setAttribute("resultSize", numOfResults);
            request.getRequestDispatcher("/customerFullCatalog.jsp").forward(request, response);
        } catch (Exception ex) {
            println(ex.getClass().toString() + " : " + ex.getMessage());
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
