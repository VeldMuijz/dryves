/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Berichten;
import Dryves.Model.BerichtenDao;
import Dryves.Model.Lid;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author H
 */
public class BerichtBeantwoorden extends HttpServlet {

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
        HttpSession session = request.getSession();
        //Maak een nieuw lid aan
        Lid user;
        if (session.getAttribute("currentSessionUser") != null) {


            //maak objecten aan
            Berichten bericht = new Berichten();
            BerichtenDao berichtDao = new BerichtenDao();
            Date datum = new Date();

            bericht.setAfzender(Integer.parseInt(request.getParameter("afzender")));
            bericht.setLidnr(Integer.parseInt(request.getParameter("naar")));
            bericht.setInhoud(request.getParameter("inhoud"));
            bericht.setDatum(new Timestamp(datum.getTime()));
            bericht.setRitnr(Integer.parseInt(request.getParameter("ritnr")));

            System.out.println(bericht.getAfzender());
            System.out.println(bericht.getLidnr());
            System.out.println(bericht.getInhoud());
            System.out.println(bericht.getRitnr());
            System.out.println(bericht.getDatum());


            if (berichtDao.beantwoordBericht(bericht)) {
                request.getRequestDispatcher("WEB-INF/succesvol.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("oops.jsp").forward(request, response);
            }

        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);

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
