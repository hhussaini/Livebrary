package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.createReturnTag;
import static com.glb.helpers.Helpers.goToSignIn;
import static com.glb.helpers.Helpers.outputToHtml;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.User;
import com.glb.services.UserService;
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
public class ItemSettingsServlet extends HttpServlet {
   
   UserService userService;
    
   public void init() {
       println(getServletName() + ": initialised" );
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
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      if (user == null) {
          goToSignIn(request, response);
          return;
      }
      int eBookLendPeriod = Integer.parseInt(request.getParameter("eBookLendPeriod"));
      int audiobookLendPeriod = Integer.parseInt(request.getParameter("audiobookLendPeriod"));
      int videoLendPeriod = Integer.parseInt(request.getParameter("videoLendPeriod"));
      String maturityStart = request.getParameter("maturityStart");
      String maturityEnd = request.getParameter("maturityEnd");
      int status = userService.updateItemSettings(user.getUsername(), eBookLendPeriod, audiobookLendPeriod,
              videoLendPeriod, maturityStart, maturityEnd);
      if (status == 1) {
         ((User)session.getAttribute("user")).setEBookLendPeriod(eBookLendPeriod);
         ((User)session.getAttribute("user")).setAudiobookLendPeriod(audiobookLendPeriod);
         ((User)session.getAttribute("user")).setVideoLendPeriod(videoLendPeriod);
         ((User)session.getAttribute("user")).setMaturityStart(maturityStart);
         ((User)session.getAttribute("user")).setMaturityEnd(maturityEnd);
         outputToHtml(response, "Your settings have been updated. " + 
                 createReturnTag("Return", "CustomerServlet"));
      } else {
         throw new ServletException("Error updating settings.");
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

}
