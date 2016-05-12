package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import com.glb.helpers.Helpers;
import static com.glb.helpers.Helpers.goToSignIn;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.Item;
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
public class SecondServerServlet extends HttpServlet {
   
   BookService bookService;
    
   public void init() {
      System.out.println(getServletName() + ": initialised" );
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
        println("Inside SecondServerServlet.doPost()");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            goToSignIn(request, response);
            return;
        }
        String isbn = request.getParameter("isbn");
        Item item = bookService.getBookByIsbn(isbn);
        if (item == null) {
            throw new ServletException("Error getting the selected item.");
        }
        String url = "http://localhost:29462/BetterLooksBooks/BuyItemServlet";
        url = Helpers.appendParameter(url, "isbn", isbn, true);
        url = Helpers.appendParameter(url, "title", item.getTitle(), false);
        url = Helpers.appendParameter(url, "author", item.getAuthor(), false);
        url = Helpers.appendParameter(url, "description", item.getDescription(), false);
        url = Helpers.appendParameter(url, "imageUrl", item.getImageUrl(), false);
        url = Helpers.appendParameter(url, "date", item.getDate(), false);
        url = Helpers.appendParameter(url, "numOfDownloads", Integer.toString(item.getNumOfDownloads()), false);
        url = Helpers.appendParameter(url, "language", item.getLanguage(), false);
        url = Helpers.appendParameter(url, "firstServerUsername", user.getUsername(), false);
        response.sendRedirect(url);
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
