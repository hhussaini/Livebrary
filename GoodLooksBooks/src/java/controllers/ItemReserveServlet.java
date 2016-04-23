/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import constants.UserTypes;
import java.io.IOException;
import java.io.PrintWriter; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Customer; 
import objects.Item;
import objects.User;

/**
 *
 * @author Kevin_Setayesh
 */
public class ItemReserveServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String userType = (String)session.getAttribute("userType");
        String url = "";  
        
        if(userType.equalsIgnoreCase(UserTypes.CUSTOMER.toString())){      // is true
            User user = (Customer)session.getAttribute("user");
            String isbn = request.getParameter("isbn");
            Item item = FileController.getInstance().searchForItem(isbn);  //returning null
            ((Customer)user).putInItemsList(item);
            
//            List<Item>list = ((Customer)user).getItemsList();
//            String str = "";
//            for(int i = 0; i<list.size(); i++){
//              str = str + (list.get(i).getIsbn() + "\n");
//            }
//              printToPage(request, response, str);
            
           
            session.setAttribute("userItemsList", ((Customer)user).getItemsList());
            url = "/customerFullCatalog.jsp";
        }    
        else{
            //url = "/customerFullCatalog.jsp";
        }
        
         
         
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response); 
    }
    
    private void printToPage(HttpServletRequest request, HttpServletResponse response, String str)
            throws ServletException, IOException {
    
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(str); 
        }
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
