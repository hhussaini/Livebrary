package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.*;
import com.glb.objects.Book;
import com.glb.services.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            HttpSession session = request.getSession();
            HashMap<String, String> searchTermMap = new HashMap<String,String>();
            String keyword = request.getParameter("keyword");
                   keyword = (keyword == null) ? "" : keyword;
                   searchTermMap.put("term", keyword);
            String author = request.getParameter("author");
                   author = author == null ? "" : author;
                   searchTermMap.put("author", author);
            String publisher = request.getParameter("publisher");
                   publisher = publisher == null ? "" : publisher;
                   searchTermMap.put("publisher", publisher);
            String isbn = request.getParameter("isbn");
                   isbn = isbn == null ? "" : isbn;
                   searchTermMap.put("isbn", isbn);
                   
            String[] categories = request.getParameterValues("category");
                     categories = (categories == null) ? new String[]{""} : categories;
            
            int page = 1;
            if (request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            int recordsPerPage = 18;
            int offset = (page-1) * recordsPerPage;
            
            searchResults = bookService.searchBooks(searchTermMap, categories, offset, recordsPerPage);
            int numOfResults = bookService.getNumberOfResults();
            int numOfPages = (int) Math.ceil(numOfResults * 1.0 / recordsPerPage);
            int firstDisplayPage = (page - 5 < 1) ? 1 : page - 5;
            int lastDisplayPage = (page + 5 > numOfPages) ? numOfPages : page + 5;
            lastDisplayPage = (lastDisplayPage == 0) ? lastDisplayPage :  (lastDisplayPage - 1);
            
            session.setAttribute("searchResults", searchResults);
            request.setAttribute("numOfPages", numOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("firstPage", firstDisplayPage);
            request.setAttribute("lastPage", lastDisplayPage);
            request.setAttribute("lastKeywordSearched", keyword);
            session.setAttribute("resultSize", numOfResults);
            request.getRequestDispatcher("/customerFullCatalog.jsp").include(request, response);
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
    }    
}
