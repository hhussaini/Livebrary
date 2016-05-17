package com.glb.controllers;

import com.glb.constants.UserTypes;
import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.createReturnTag;
import static com.glb.helpers.Helpers.goToSignIn;
import static com.glb.helpers.Helpers.outputToHtml;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.Book;
import com.glb.objects.Item;
import com.glb.objects.User;
import com.glb.services.BookService;
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
public class ItemAccessServlet extends HttpServlet {
   
    UserService userService;
    List<Book> publisherItems;
    User publisher;
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
        if (method.equals("doBorrow")){
            doBorrow(request, response);
        } else if (method.equals("requestHold")) {
            requestHold(request, response);
        } else if (method.equals("doHold")) {
            doHold(request, response);
        } else if (method.equals("doReserve")) {
            doReserve(request, response);
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
   }
   
   protected void doBorrow(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      String isbn = request.getParameter("isbn");
      int status = 0;
      if (user == null) {
          goToSignIn(request, response);
          return;
      }
      if(user.getType().equalsIgnoreCase(UserTypes.CUSTOMER.toString())){
         status = bookService.addBookToUserItems(user.getUsername(), isbn); 
         if (status == 1) {
            outputToHtml(response, "Item checked out successfully. " + createReturnTag("Return", "BookDescriptionServlet?isbn=" + isbn));
         }
         else {
            throw new ServletException("Error checking out item.");
         }
      }
   }
   
   protected void requestHold(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      String isbn = request.getParameter("isbn");
      if (user == null) {
          goToSignIn(request, response);
          return;
      }
      Item item = bookService.getBookByIsbn(isbn);
      session.setAttribute("itemHeld", item);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/requestHold.jsp");
      dispatcher.forward(request, response); 
   }
   
   protected void doHold(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      String isbn = request.getParameter("isbn");
      String email = request.getParameter("email");
      String automaticCheckout = request.getParameter("automaticCheckout");
      if (user == null) {
          goToSignIn(request, response);
          return;
      }
      int status = userService.putOnHold(user.getUsername(), isbn, email, automaticCheckout);
      if (status == 1) {
        outputToHtml(response, "Item put on hold successfully. " + createReturnTag("Return", "BookDescriptionServlet?isbn=" + isbn));
      } else {
        throw new ServletException("Item is already on hold.");
      }
   }
   
   // TODO. Implement me
   protected void doReserve(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      HttpSession session = request.getSession();
      User user = (User)session.getAttribute("user");
      String isbn = request.getParameter("isbn");
      if (user == null) {
          goToSignIn(request, response);
          return;
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