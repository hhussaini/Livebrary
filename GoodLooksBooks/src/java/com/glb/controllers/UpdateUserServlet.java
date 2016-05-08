/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.goToSignIn;
import com.glb.objects.User;
import com.glb.services.UserService;
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
 * @author Hamza
 */
public class UpdateUserServlet extends HttpServlet {

    
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
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
           goToSignIn(request, response);
           return;
        }
        String userType = user.getType();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");        
       
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setStreet(street);
        user.setCity(city);
        user.setState(state);
        user.setZipcode(zipcode);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        
        RequestDispatcher dispatcher = null;
        if (isNull(firstName, lastName, street, city, state, zipcode, phoneNumber, email)) {
            throw new ServletException("Please fill in all fields.");
        }
        
        int status = 0;
        try {
            status = userService.update(user);
            if (status == 1) {
                session.setAttribute("user", user);
                String url = "";
                switch (userType) {
                    case "admin":
                        url = "/adminIndex.jsp";
                        break;
                    case "customer":
                        url = "/customerIndex.jsp";
                        break;
                    case "librarian": 
                        url = "/librarianIndex.jsp";
                        break;
                    case "publisher":
                        url = "/publisherIndex.jsp";
                        break;
                }        
                dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            } else {
                throw new ServletException("SQL Error.");
            }
        } catch (Exception e) {
            throw new ServletException("Exception");
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
    
    

}
