package com.blb.controllers;

import com.blb.objects.User;
import com.blb.factories.ServiceFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.blb.services.UserService;

/**
 *
 * @author Kevin Young
 */
public class SignUpServlet extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String userType = request.getParameter("userType");    
        RequestDispatcher dispatcher = null;
        if (isNull(username, password, firstName, lastName, street, city, state, zipcode, phoneNumber, email)) {
            throw new ServletException("Please fill in all fields.");
        }
        User user = new User(username, password, firstName, lastName, street, city, state, zipcode, phoneNumber, email, userType);   
        HttpSession session = request.getSession();
        String firstServerUsername = (String) session.getAttribute("firstServerUsername");
        user.setFirstServerUsername(firstServerUsername);
        int status = 0;
        try {
            status = userService.save(user);
            if (status == 1) {
                session.setAttribute("user", user);
                dispatcher = request.getRequestDispatcher("/bookDescription.jsp");
                dispatcher.forward(request, response);
            } else {
                throw new ServletException("SQL Error.");
            }
        } catch (Exception e) {
            throw new ServletException("Exception");
        }
    }
    
    /**
     * Checks if any of the objects in args is null
     * @param args
     * @return True if any of the objects in args is null
     */
    private boolean isNull(Object... args) {
        for (Object arg : args) {
            if (arg == null || ((String)arg).isEmpty()) {
                System.out.println("ARG = " + arg + " is null or empty");
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles signing up a Customer, Publisher, or Librarian";
    } // </editor-fold>
}
