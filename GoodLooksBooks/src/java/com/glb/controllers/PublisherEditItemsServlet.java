package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.createReturnTag;
import static com.glb.helpers.Helpers.goToSignIn;
import static com.glb.helpers.Helpers.outputToHtml;
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
        if (method.equals("doEdit")){
            doEdit(request, response);
        } else if (method.equals("doAdd")) {
            doAdd(request, response);
        } else if (method.equals("doDelete")) {
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
        if (publisher == null) {
            goToSignIn(request, response);
            return;
        }
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
        String oldIsbn = request.getParameter("oldIsbn");
        String newIsbn = request.getParameter("newIsbn");
        String title = request.getParameter("title"); 
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        int status = 0;
        status = bookService.submitEditRequest(oldIsbn, newIsbn, title, author, 
                                            description);
        if (status != 1) {
            throw new ServletException("SQL Error");
        }
        outputToHtml(response, "Your ticket has been sent. The site admin "
                    + "will respond to your ticket soon. " + createReturnTag("Return", this.getServletName()));
    }
    
    protected void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        println(this.getServletName() + " : " + "doAdd");
        HttpSession session = request.getSession();
        publisher = (User)session.getAttribute("user");
        if (publisher == null) {
            goToSignIn(request, response);
            return;
        }
        String publisherName = publisher.getCompany();
        String isbn = request.getParameter("isbn");
        String isbn10 = request.getParameter("isbn10");
        String title = request.getParameter("title"); 
        String author = request.getParameter("author");
        String description = request.getParameter("description");
        String binding = request.getParameter("binding");
        String imageUrl = request.getParameter("imageUrl");
        int pages = Integer.parseInt(request.getParameter("pages"));
        String language = request.getParameter("language");
        double listPrice = Double.parseDouble(request.getParameter("listPrice"));
        String currency = request.getParameter("currency");
        String category = request.getParameter("category");
        int status = 0;
        status = bookService.submitAddRequest(isbn, isbn10, title, author, description, 
                binding, imageUrl, pages, language, listPrice, currency, publisherName, category);
        if (status != 1) {
            throw new ServletException("SQL Error");
        }
        outputToHtml(response, "Your ticket has been sent. The site admin "
                    + "will respond to your ticket soon. " + createReturnTag("Return", this.getServletName()));
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        println(this.getServletName() + " : " + "doDelete");
        String isbn = request.getParameter("isbn");
        int status = 0;
        status = bookService.submitDeleteRequest(isbn);
        if (status != 1) {
            throw new ServletException("SQL Error");
        }
        outputToHtml(response, "Your ticket has been sent. The site admin "
                    + "will respond to your ticket soon. " + createReturnTag("Return", this.getServletName()));
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
