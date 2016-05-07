package com.glb.controllers;

import static com.glb.helpers.Helpers.createReturnTag;
import static com.glb.helpers.Helpers.goToSignIn;
import static com.glb.helpers.Helpers.outputToHtml;
import com.glb.objects.Item;
import com.glb.objects.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

/**
 *
 * @author Kevin Young
 */
public class EmailItemServlet extends HttpServlet {
    
    private String host;
    private String port;
    private String doNotReplyEmail;
    private String doNotReplyPass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        doNotReplyEmail = context.getInitParameter("doNotReplyEmail");
        doNotReplyPass = context.getInitParameter("doNotReplyPass");
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
        // processRequest(request, response); 
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            goToSignIn(request, response);
            return;
        }
        Item item = (Item)session.getAttribute("itemClicked");
        if (item == null) {
            throw new ServletException("Error getting the selected item.");
        }
        String from = request.getParameter("from");
        String subject = request.getParameter("subject");
        String message = "GoodLooksBooks user,\n\n";
        message += from + " thought you might be interested in the following "
                + "downloadable title available now at GoodLooksBooks ";
        message += "http://localhost:8080/GoodLooksBooks/" +"\n\n";
        message += item.getTitle() + "\n";
        message += "by " + item.getAuthor() + "\n\n";
        message += "To find out more details about this title, simply click on "
                + "the link below or copy and paste it into your browser:\n";
        message += "http://localhost:8080/GoodLooksBooks/BookDescriptionServlet?isbn=" 
                + item.getIsbn();
        message += "\n\nHere's a special message to you from your friend " + from + ":\n\n";
        message += "'" + request.getParameter("message") + "'";
        String recipient = request.getParameter("recipient");
        try {
            EmailUtility.sendEmail(host, port, doNotReplyEmail, doNotReplyPass, recipient, subject,
                    message);
            outputToHtml(response, "Your email has been sent. " + createReturnTag("Return", "BookDescriptionServlet?isbn="
                + item.getIsbn()));
        } catch (Exception ex) {
            throw new ServletException(ex.getMessage());
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
