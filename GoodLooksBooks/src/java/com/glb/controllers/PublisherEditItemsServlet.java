package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.goToSignIn;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.Book;
import com.glb.objects.User;
import com.glb.services.BookService;
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
public class PublisherEditItemsServlet extends HttpServlet {
    
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
        if (method.equals("submitEdit")){
            doEdit(request, response);
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
        println(this.getServletName() + " : " + "doGet");
        HttpSession session = request.getSession();
        publisher = (User)session.getAttribute("user");
        publisherItems = userService.getPublisherItems(publisher);
        session.setAttribute("publisherItems", publisherItems);
        session.setAttribute("publisherItemsSize", publisherItems.size());
        request.getRequestDispatcher("/publisherEditItems.jsp").include(request, response);
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
        println(this.getServletName() + " : " + "doPost");
        String isbn = request.getParameter("isbn");
        HttpSession session = request.getSession();
        Book book = bookService.getBookByIsbn(isbn);
        session.setAttribute("itemClicked", book);
        request.getRequestDispatcher("/editItem.jsp").include(request, response);
    }
    
    protected void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        println(this.getServletName() + " : " + "doEdit");
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title"); 
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        bookService.submitEditRequest(isbn);
        doGet(request, response);
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
