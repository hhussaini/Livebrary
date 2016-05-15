package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import com.glb.helpers.Helpers;
import static com.glb.helpers.Helpers.createReturnTag;
import static com.glb.helpers.Helpers.outputToHtml;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.User;
import com.glb.services.BookService;
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
public class SecondServerResponseServlet extends HttpServlet {
    
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
        String isbn = request.getParameter("isbn");
        String action = request.getParameter("action");
        int status = 0;        
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            String username;
            if (user == null) {
               username = request.getParameter("firstServerUsername");
            } else {
               username = user.getUsername();
            }
            switch (action) {
//               case "return": status = bookService.returnItem(username, isbn);
//                              break;
            }
            
            if (status == 1) {
               outputToHtml(response, "Your request has been successful on Good Looks Books. "
                           + "<a href=\"http://localhost:29462/BetterLooksBooks/bookDescription.jsp\">Return</a>");
            } else {
                throw new ServletException("SQL Error.");
            }
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
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
