package com.glb.controllers;

import static com.glb.helpers.Helpers.*;
import com.glb.services.UserService;
import com.glb.factories.ServiceFactory;
import com.glb.objects.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.glb.objects.Book;
import java.util.ArrayList;

/**
 * @author mobile-mann
 */
public class WishlistServlet extends HttpServlet {
    
    UserService userService;
    
    User user;
    
    public void init() {
        System.out.println(getServletName() + ": initialised" );
        userService = ServiceFactory.getUserService();
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {
        println(req.getMethod().toString());
        String method = req.getParameter("method");
        method = (method == null) ? "" : method;
        if(method.equals("delete")){
            doDelete(req,resp);
        }else {
            super.service(req, resp);
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WishlistServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WishlistServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
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
        List<Book> allWishlist;
        List<Book> availWishlist;
        user = (User)session.getAttribute("user");
        if (user == null) {
            goToSignIn(request, response);
            return;
        }
        
        allWishlist = userService.getWishlist(user);
        availWishlist = new ArrayList<Book>();
        for (Book book : allWishlist) {
            if (book.getCopiesLeft() > 0)
                availWishlist.add(book);
        }
        session.setAttribute("allWishlist", allWishlist);
        session.setAttribute("availWishlist", availWishlist);
        request.getRequestDispatcher("/wishlist.jsp").forward(request, response);
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
        user = (User)session.getAttribute("user");
        if (user == null) {
            goToSignIn(request, response);
            return;
        }
        userService.addToWishlist(user, isbn);
        request.getRequestDispatcher("/bookDescription.jsp").include(request, response);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        println(this.getServletName() + " : " + "doDelete");
        String isbn = request.getParameter("isbn");
        HttpSession session = request.getSession();
        user = (User)session.getAttribute("user");
        if (user == null) {
            goToSignIn(request, response);
            return;
        }
        userService.removeFromWishlist(user, isbn);
       request.getRequestDispatcher("/CustomerServlet").forward(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
