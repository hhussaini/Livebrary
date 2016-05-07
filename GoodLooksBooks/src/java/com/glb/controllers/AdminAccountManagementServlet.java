package com.glb.controllers;

import com.glb.constants.UserTypes;
import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.isNull;
import static com.glb.helpers.Helpers.isNullOrEmpty;
import static com.glb.helpers.Helpers.outputToHtml;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.User;
import com.glb.services.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
       } else if (method.equals("submitEdit")){
           submitEdit(request, response);
       } else if (method.equals("submitAdd")){
           submitAdd(request, response);
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
      RequestDispatcher dispatcher = request.getRequestDispatcher("/accountManagement.jsp");
      dispatcher.forward(request, response); 
      //request.getRequestDispatcher("/accountManagement.jsp").include(request, response);
    }
    
    protected void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String username = request.getParameter("userToEdit");
      HttpSession session = request.getSession();
      User selectedUser = userService.getUser(username);
      session.setAttribute("selectedUser", selectedUser);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/adminAccountSettings.jsp");
      dispatcher.forward(request, response); 
    }
    
    protected void submitEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String street = request.getParameter("street");
      String city = request.getParameter("city");
      String state = request.getParameter("state");
      String zipcode = request.getParameter("zipcode");
      String phoneNumber = request.getParameter("phoneNumber");
      String email = request.getParameter("email");
      String userType = request.getParameter("userType");
      String company = request.getParameter("company");
      if (userType.equalsIgnoreCase(UserTypes.CUSTOMER.toString())) {
         if (!isNullOrEmpty(company)) {
            throw new ServletException("A customer can not have a company.");
         }
      } else if (userType.equalsIgnoreCase(UserTypes.PUBLISHER.toString())) {
         if (isNullOrEmpty(company)) {
            throw new ServletException("A publisher must have a company.");
         }
      }
      
      HttpSession session = request.getSession();
      User userToUpdate = (User)session.getAttribute("selectedUser");
      if (!isNullOrEmpty(firstName))
         userToUpdate.setFirstName(firstName);
      if (!isNullOrEmpty(lastName))
         userToUpdate.setLastName(lastName);
      if (!isNullOrEmpty(street))
         userToUpdate.setStreet(street);
      if (!isNullOrEmpty(city))
         userToUpdate.setCity(city);
      if (!isNullOrEmpty(state))
         userToUpdate.setState(state);
      if (!isNullOrEmpty(zipcode))
         userToUpdate.setZipcode(zipcode);
      if (!isNullOrEmpty(phoneNumber))
         userToUpdate.setPhoneNumber(phoneNumber);
      if (!isNullOrEmpty(email))
         userToUpdate.setEmail(email);
      if (!isNullOrEmpty(userType))
         userToUpdate.setType(userType);
      if (!isNullOrEmpty(company))
         userToUpdate.setCompany(company);
      int status = userService.update(userToUpdate);
      if (status != 1) {
         throw new ServletException("SQL Error when updating user.");
      }
      outputToHtml(response, "User updated successfully.");
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String username = request.getParameter("userToDelete");
      int status = userService.deleteUser(username);
      if (status != 1) {
            throw new ServletException("SQL Error");
      }
      outputToHtml(response, "User " + username + " has been deleted.");
    }
    
    protected void submitAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
      String company = request.getParameter("company");
      if (userType.equalsIgnoreCase(UserTypes.CUSTOMER.toString())) {
         if (!isNullOrEmpty(company)) {
            throw new ServletException("A customer can not have a company.");
         }
      } else if (userType.equalsIgnoreCase(UserTypes.PUBLISHER.toString())) {
         if (isNullOrEmpty(company)) {
            throw new ServletException("A publisher must have a company.");
         }
      }
      
      if (isNull(firstName, lastName, street, city, state, zipcode, phoneNumber, email)) {
           throw new ServletException("Please fill in all fields.");
      }
      
      User user = new User(username, password, firstName, lastName, street, city, state, zipcode, phoneNumber, email, userType, company);        
      int status = 0;
      try {
          status = userService.save(user);
          if (status == 1) {
             outputToHtml(response, "User " + username + " added successfully.");
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
