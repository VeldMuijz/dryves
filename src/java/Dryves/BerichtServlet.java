/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author H
 */
public class BerichtServlet extends HttpServlet {
String berichtid;
  String h=null;
  BerichtClass berichtclas = new BerichtClass();
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      
            
            
            
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
     
double DOUBLEVAR=Double.parseDouble( request.getParameter("berichtid"));
    
     
BerichtClass berichtclassobject= new BerichtClass();

       berichtclassobject= berichtclas.Haalinbox(DOUBLEVAR);

        
        berichtclas.checkgelezen(DOUBLEVAR);
        
            
        
        
              
        
        String x1= berichtclassobject.getDatum();
        String x2= berichtclassobject.getInhoud();
        String x3=berichtclassobject.getOnderwerp();
     
        request.setAttribute("datum", x1);
        request.setAttribute("inhoud", x2);
        request.setAttribute("onderwerp", x3);
        
        
        request.getRequestDispatcher("helebericht.jsp").forward(request, response);
     
  

       
    }
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
