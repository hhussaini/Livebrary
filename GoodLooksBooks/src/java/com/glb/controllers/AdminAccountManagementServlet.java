package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.outputToHtml;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.User;
import com.glb.services.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kevin Young
 */
public class AdminAccountManagementServlet extends HttpServlet {
   
   UserService userService;
   List<User> allUsers;
    
   public void init() {
       System.out.println(getServletName() + ": initialised" );
       userService = ServiceFactory.getUserService();        
   }

   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, java.io.IOException {
       println(request.getMethod().toString());
       String method = request.getParameter("method");
       method = (method == null) ? "" : method;
       if (method.equals("doEdit")){
           doEdit(request, response);
       } else if (method.equals("doDelete")){
           doDelete(request, response);
       } else {
           super.service(request, response);
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
      HttpSession session = request.getSession();
      allUsers = userService.getAllUsers();
      if (allUsers == null) {
         throw new ServletException("Error getting all users");
      }
      session.setAttribute("allUsers", allUsers);
      request.getRequestDispatcher("/accountManagement.jsp").include(request, response);
    }
    
    protected void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String username = request.getParameter("username");
      int status = userService.deleteUser(username);
      if (status != 1) {
            throw new ServletException("SQL Error");
      }
      outputToHtml(response, "User " + username + " has been deleted.");
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
