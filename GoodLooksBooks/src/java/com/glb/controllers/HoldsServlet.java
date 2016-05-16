package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.createReturnTag;
import static com.glb.helpers.Helpers.goToSignIn;
import static com.glb.helpers.Helpers.outputToHtml;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.User;
import com.glb.services.BookService;
import com.glb.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kevin Young
 */
public class HoldsServlet extends HttpServlet {

   UserService userService;
   BookService bookService;
    
    public void init() {
        System.out.println(getServletName() + ": initialised" );
        userService = ServiceFactory.getUserService();
        bookService = ServiceFactory.getBookService();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        println(request.getMethod().toString());
        String method = request.getParameter("method");
        method = (method == null) ? "" : method;
        if (method.equals("editEmail")){
           editEmail(request, response);
        } else if(method.equals("suspendHold")) {
           suspendHold(request, response);
        } else if (method.equals("autoCheckout")) {
           autoCheckout(request, response);
        } else if (method.equals("removeHold")) {
           removeHold(request, response);
        } else {
           super.service(request, response);
        } 
    }
    
    protected void editEmail(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      String isbn = request.getParameter("isbn");
      String email = request.getParameter("email");
      int status = 0;
      if (user == null) {
          goToSignIn(request, response);
          return;
      }
      bookService.editHoldEmail(user.getUsername(), email, isbn);
      outputToHtml(response, "Your email has successfully been changed. " + createReturnTag("Return", "CustomerServlet"));
   }
    
    protected void removeHold(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      String isbn = request.getParameter("isbn");
      if (user == null) {
          goToSignIn(request, response);
          return;
      }
      bookService.removeHold(user.getUsername(), isbn);
      outputToHtml(response, "The hold has successfully been removed. " + createReturnTag("Return", "CustomerServlet"));
   }
    
    protected void autoCheckout(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      String isbn = request.getParameter("isbn");
      String autoCheckout = request.getParameter("autoCheckout");
      int status = 0;
      if (user == null) {
          goToSignIn(request, response);
          return;
      }
      bookService.editHoldAutoCheckout(user.getUsername(), autoCheckout, isbn);
      outputToHtml(response, "Your auto checkout preference has successfully been changed. " + createReturnTag("Return", "CustomerServlet"));
   }
    
    protected void suspendHold(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      if (user == null) {
          goToSignIn(request, response);
          return;
      }
      String isbn = request.getParameter("isbn");
      String durationStr = request.getParameter("duration");
      int status = 0;
      if (durationStr.equals("Cancel")) {
         status = bookService.cancelSuspension(user.getUsername(), isbn);
         if (status == 1) {
            outputToHtml(response, "Your suspension has successfully been cancelled. " + createReturnTag("Return", "CustomerServlet"));
         } else {
            outputToHtml(response, "There is no suspension on this item. " + createReturnTag("Return", "CustomerServlet"));
         }
      } else {
         int duration = Integer.parseInt(durationStr);
         status = bookService.suspendHold(user.getUsername(), isbn, duration);
         if (status == 1) {
            outputToHtml(response, "Your suspension has successfully been placed. " + createReturnTag("Return", "CustomerServlet"));
         } else {
            outputToHtml(response, "Suspension not placed. There is already a suspension on this item until " + 
                    bookService.getHoldSuspensionDate(user.getUsername(), isbn) + " " +
                    createReturnTag("Return", "CustomerServlet"));
         }
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
