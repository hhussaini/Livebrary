/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.Item;
import org.json.simple.JSONObject;
 
/**
 *
 * @author Kevin_Setayesh
 */
public class UpdateAverageRatingServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateAverageRatingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAverageRatingServlet at " + request.getContextPath() + "</h1>");
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
      //  processRequest(request, response);
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
      //  processRequest(request, response);
      response.setContentType("application/json"); 
      String isbn = request.getParameter("isbn");
      String numOfStars = request.getParameter("numOfStars");
     // String yo = "{\"yo\":\"yolo\"}";
     
      //dummy database to update avg rating for the item (textfile) 
      
      JSONObject jsonObj = new JSONObject(); 
      jsonObj.put("isbn", isbn); 
      if(numOfStars != null){
          jsonObj.put("numOfStars", numOfStars);
          jsonObj.put("avgNumOfStars", updateItem(isbn, numOfStars));
      }
      else{
          jsonObj.put("avgNumOfStars", getItem(isbn).getAvgRating());
      }
      response.getWriter().print(jsonObj.toJSONString()); 
        
    }
    
    private Item getItem(String isbn) throws IOException{
        return FileController.getInstance().searchForItem(isbn);
    }
    
    private double updateItem(String isbn, String numOfStars) throws IOException{
        int numOfStarsInt = Integer.parseInt(numOfStars);
        Item item = FileController.getInstance().searchForItem(isbn);
        item.setAvgRating(numOfStarsInt);
        return item.getAvgRating();
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
