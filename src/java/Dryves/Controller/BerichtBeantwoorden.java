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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class BerichtBeantwoorden extends HttpServlet {

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
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BerichtBeantwoorden</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BerichtBeantwoorden at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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

        //maak object
        Berichten bericht = new Berichten();
        BerichtenDao berichtDao = new BerichtenDao();
        
//        //huidige tijdstip opvragen
 Date datum = new Date ();
//        SimpleDateFormat datumformat = new SimpleDateFormat("dd/mm/yyyy hh:mm");
//        StringBuilder tijdstipNu = new StringBuilder( datumformat.format( dagMaandJaar ) );
//     
        


        bericht.setAfzender(Integer.parseInt(request.getParameter("afzender")));
        bericht.setLidnr(Integer.parseInt(request.getParameter("naar")));
        bericht.setInhoud(request.getParameter("inhoud"));
//        bericht.setDatum(tijdstipNu.toString());
		bericht.setDatum(new Timestamp(datum.getTime()));
        bericht.setRitnr(Integer.parseInt(request.getParameter("ritnr")));

        System.out.println(bericht.getAfzender());
        System.out.println(bericht.getLidnr());
        System.out.println(bericht.getInhoud());
        System.out.println(bericht.getRitnr());
        System.out.println(bericht.getDatum());

        try {
            berichtDao.beantwoordBericht(bericht);

            response.sendRedirect("succesvol.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(BerichtBeantwoorden.class.getName()).log(Level.SEVERE, null, ex);
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