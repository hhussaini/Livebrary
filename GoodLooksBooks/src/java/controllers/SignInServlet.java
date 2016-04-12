package controllers;

import daos.BookDao;
import objects.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Book;

/**
 *
 * @author Kevin Young
 */
public class SignInServlet extends HttpServlet {

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
        // processRequest(request, response);
        UserController userController = UserController.getInstance();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // TODO: Prettier error message
        if (!userController.verifyUser(username, password)) {
            PrintWriter out = response.getWriter();  
            response.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('This user does not exist');");  
            out.println("</script>");
            return;
        }
        
        User user = userController.getUser(username, password);
        String userType = user.getType();
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("userType", userType);
        
        String url = "";
        switch (userType) {
            case "admin":
                url = "/adminIndex.jsp";
                break;
            case "customer":
                url = "/customerIndex.jsp";
                break;
            case "librarian": 
                url = "/librarianIndex.jsp";
                break;
            case "publisher":
                url = "/publisherIndex.jsp";
                break;
        }        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response); 
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles an admin, customer, libararian, or publisher logging in";
    }

}
