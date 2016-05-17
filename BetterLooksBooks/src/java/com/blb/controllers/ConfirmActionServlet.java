package com.blb.controllers;

import static com.blb.helpers.Helpers.appendParameter;
import com.blb.objects.Item;
import com.blb.objects.User;
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
public class ConfirmActionServlet extends HttpServlet {

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
        // processRequest(request, response);
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
        // processRequest(request, response);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String url= "";
        if (user == null) {
            url = "http://localhost:29462/BetterLooksBooks/signIn.jsp";
        }
        else {
            String action = request.getParameter("action");
            Item item = (Item)session.getAttribute("itemClicked");
            if (item == null) {
                throw new ServletException("Error getting the selected item.");
            }
            String isbn = item.getIsbn();
            url = "http://localhost:8080/GoodLooksBooks/SecondServerResponseServlet";
            url = appendParameter(url, "isbn", isbn, true);
            url = appendParameter(url, "firstServerUsername", user.getFirstServerUsername(), false);
            url = appendParameter(url, "action", action, false);
        }
        response.sendRedirect(url);
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