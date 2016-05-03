package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.*;
import com.glb.objects.Book;
import com.glb.objects.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.glb.services.UserService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin Young
 */
public class SignInServlet extends HttpServlet {
    
    UserService userService;
    
    public void init() {
        System.out.println(getServletName() + ": initialised" );
        userService = ServiceFactory.getUserService();
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
        // processRequest(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User user = userService.getUser(username, password);
        if (user == null) {
            throw new ServletException("This user does not exist.");
        }
        
        String userType = user.getType();
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        String url = "";
        switch (userType) {
            case "admin":
                url = "/adminIndex.jsp";
                break;
            case "customer":
                url = "/customerIndex.jsp";
                setCustomerLists(session, user);
                break;
            case "librarian": 
                url = "/librarianIndex.jsp";
                break;
            case "publisher":
                url = "/publisherIndex.jsp";
                break;
        }       
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response); 
    }

    public void setCustomerLists(HttpSession session, User user) {
        List<Book> checkedOut = userService.getCheckedOut(user);
        session.setAttribute("checkedOutItems", checkedOut);
        List<Book> reserved = userService.getCheckedOut(user);
        session.setAttribute("onHold", reserved);
        List<Book> wishlist = userService.getWishlist(user);
        session.setAttribute("customerWishlist", wishlist);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles an admin, customer, libararian, or publisher logging in";
    }
}
