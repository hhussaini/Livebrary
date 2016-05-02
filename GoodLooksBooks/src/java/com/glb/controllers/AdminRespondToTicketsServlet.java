package com.glb.controllers;

import com.glb.factories.ServiceFactory;
import static com.glb.helpers.Helpers.outputToHtml;
import static com.glb.helpers.Helpers.println;
import com.glb.objects.Ticket;
import com.glb.services.BookService;
import com.glb.services.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 *
 * @author Kevin Young
 */
public class AdminRespondToTicketsServlet extends HttpServlet {
    
    UserService userService;
    List<Ticket> publisherTickets;
    BookService bookService;
    
    public void init() {
        System.out.println(getServletName() + ": initialised" );
        bookService = ServiceFactory.getBookService();        
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        println(request.getMethod().toString());
        String method = request.getParameter("method");
        method = (method == null) ? "" : method;
        if (method.equals("viewTicket")){
            viewTicket(request, response);
        } else if (method.equals("acceptTicket")){
            acceptTicket(request, response);
        } else if (method.equals("denyTicket")){
            denyTicket(request, response);
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
        publisherTickets = bookService.getTickets("n");
        session.setAttribute("publisherTickets", publisherTickets);
        session.setAttribute("publisherTicketsSize", publisherTickets.size());
        request.getRequestDispatcher("/respondToTickets.jsp").include(request, response);
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
    
    protected void viewTicket(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        Ticket ticket = getTicketById(ticketId);
        if (ticket == null) {
            throw new ServletException("Error getting this ticket.");
        }
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.append("<Ticket id=\"" + ticket.getId() + "\">");
        out.append(ticket.getXmlStr());
        out.append("</Ticket>");
    }
    
    protected void acceptTicket(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        Ticket ticket = getTicketById(ticketId);
        if (ticket == null) {
            throw new ServletException("Error getting this ticket.");
        }
        int status = 0;
        status = bookService.acceptTicket(ticketId);
        if (status != 1) {
            throw new ServletException("SQL Error");
        }
        outputToHtml(response, "This ticket has been accepted.");
    } 
    
    protected void denyTicket(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        Ticket ticket = getTicketById(ticketId);
        if (ticket == null) {
            throw new ServletException("Error getting this ticket.");
        }
        int status = 0;
        status = bookService.resolveTicket(ticketId, "n");
        if (status != 1) {
            throw new ServletException("SQL Error");
        }
        outputToHtml(response, "This ticket has been denied.");
    }
    
    private Ticket getTicketById(int ticketId) {
        for (Ticket ticket: publisherTickets) {
            if (ticket.getId() == ticketId) {
                return ticket;
            }
        }
        return null;
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
