package com.glb.controllers;

import static com.glb.helpers.Helpers.createReturnTag;
import static com.glb.helpers.Helpers.outputToHtml;
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
public class ContactSupportServlet extends HttpServlet {
    
    private String host;
    private String port;
    private String doNotReplyEmail;
    private String doNotReplyPass;
    private String supportEmail;
    
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        doNotReplyEmail = context.getInitParameter("doNotReplyEmail");
        doNotReplyPass = context.getInitParameter("doNotReplyPass");
        supportEmail = context.getInitParameter("supportEmail");
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
        String from = "";
        if (user == null) {
            from = request.getParameter("from");
        } else {
            from = user.getEmail();
        }
        String subject = from + " Requests Support";
        String message = "GoodLooksBooks user,\n\n";
        message += from + " requests support with the following message:\n\n";
        message += "'" + request.getParameter("message") + "'";
        try {
            EmailUtility.sendEmail(host, port, doNotReplyEmail, doNotReplyPass, supportEmail, subject,
                    message);
            outputToHtml(response, "Your message has been sent. Contact will send you "
                        + "an email soon. " + createReturnTag("Return", "help.jsp"));
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
