package com.blb.controllers;

import com.blb.objects.Item;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kevin Young
 */
public class BuyItemServlet extends HttpServlet {

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
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String description = request.getParameter("description").equals("null") ? "" : request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");
        String date = request.getParameter("date").equals("null") ? "" : request.getParameter("date");
        String language = request.getParameter("language").equals("null") ? "" : request.getParameter("language");
        Item item = new Item();
        item.setIsbn(isbn);
        item.setTitle(title);
        item.setAuthor(author);
        item.setDescription(description);
        item.setImageUrl(imageUrl);
        item.setDate(date);
        item.setLanguage(language);
        HttpSession session = request.getSession();
        session.setAttribute("itemClicked", item);
        response.sendRedirect("http://localhost:29462/BetterLooksBooks/bookDescription.jsp");
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
