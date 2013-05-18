/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Berichten;
import Dryves.Model.BerichtenDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class VerstuurBericht extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        Berichten bericht= new Berichten();
        BerichtenDao berichtDao= new BerichtenDao();
        
        Date dateNow = new Date ();
        SimpleDateFormat datumformat = new SimpleDateFormat("dd/mm/yyyy hh:mm");
        StringBuilder tijdstipNu = new StringBuilder( datumformat.format( dateNow ) );
        
        
        
        bericht.setRitnr(Integer.parseInt(request.getParameter("ritnr")));
        bericht.setLidnr(Integer.parseInt(request.getParameter("lidnr")));
        bericht.setInhoud(request.getParameter("inhoud"));
        bericht.setDatum(tijdstipNu.toString());
        System.out.println(bericht.getInhoud());
        
        
        try {
            berichtDao.BerichtVersturen(bericht);
            response.sendRedirect("succesvol.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(Berichtverzenden.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("oops.jsp");
           
        }
        
        
        
        
        
        
        
        
        
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
