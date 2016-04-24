package com.glb.controllers;

import static com.glb.helpers.Helpers.*;
import com.glb.services.BookService;
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


/**
 * @author mobile-mann
 */
public class WishlistServlet extends HttpServlet {
    
    //BookDao bookDao;
    BookService bookService;
    List<Book> wishlist;
    User user;
    
    public void init() {
        System.out.println(getServletName() + ": initialised" );
        //bookDao = new BookDao();
        bookService = ServiceFactory.getBookService();
    }
    
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, java.io.IOException {
//        println("IN SERVICE");
//        println(req.getMethod().toString());
//        if(req.getMethod().equals("remove")){
//           println("MATHCED METHOD NAME");
//            doRemove(req,resp);
//        }else {
//            println("UNMATHCED METHOD NAME");
//            super.service(req, resp);
//        }
//        
//        
//    }
    
    public void doRemove(HttpServletRequest req, HttpServletResponse res) {
        println("HERE");
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
        user = (User)session.getAttribute("user");
        wishlist = bookService.getWishlist(user.getUsername());
        println("Wishlist: " + wishlist.size());
        session.setAttribute("customerWishlist", wishlist);
        session.setAttribute("wishlistSize", wishlist.size());
        request.getRequestDispatcher("/wishlist.jsp").include(request, response);
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
        
        
         println(this.getServletName() + " : " + "doDelete");
        HttpSession session = request.getSession();
        String isbn = request.getParameter("isbn");
        System.out.println(isbn);
        
        bookService.removeFromWishlist(user.getUsername(), isbn);
        doGet(request, response);
//        session.setAttribute("customerWishlist", wishlist);
//        request.getRequestDispatcher("/wishlist.jsp").include(request, response);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        println(this.getServletName() + " : " + "doDelete");
        HttpSession session = request.getSession();
        String isbn = request.getParameter("isbn");
        System.out.println(isbn);
        
        bookService.removeFromWishlist(user.getUsername(), isbn);
        doGet(request, response);
//        session.setAttribute("customerWishlist", wishlist);
//        request.getRequestDispatcher("/wishlist.jsp").include(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    } // </editor-fold>
}
