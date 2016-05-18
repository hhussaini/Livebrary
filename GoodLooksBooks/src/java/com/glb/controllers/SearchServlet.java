package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.*;
import com.glb.objects.SearchResult;
import com.glb.services.BookService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    SearchResult searchResults;
    
    public void init() {
        println(getServletName() + ": initialised" );
        bookService = ServiceFactory.getBookService();
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {
        println(req.getMethod().toString());
        String method = req.getParameter("method");
        method = (method == null) ? "" : method;
        if(method.equals("jump")){
            doJump(req,resp);
        }else {
            super.service(req, resp);
        }
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
            println(this.getServletName() + " : doGet");
            HttpSession session = request.getSession();
            
            searchResults = new SearchResult(request, session, bookService);
            
            session.setAttribute("resultSet", searchResults);
            request.getRequestDispatcher("/fullCatalog.jsp").include(request, response);
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
    
    private void doJump(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        if (page != null) {
                searchResults = (SearchResult)session.getAttribute("resultSet");
                searchResults.goToPage(Integer.parseInt(page), request);
        }
        
        session.setAttribute("resultSet", searchResults);
        try {
            request.getRequestDispatcher("/fullCatalog.jsp").include(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
