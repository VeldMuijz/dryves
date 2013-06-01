/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Lid;
import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import Dryves.Pager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vincent
 */
@WebServlet(name = "MijnRitten", urlPatterns = {"/mijn_ritten"})
public class MijnRitten extends HttpServlet {

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
        // Instantieren van objecten
        Rit rit = new Rit();
        RitDao ritDao = new RitDao();
        Pager pager = new Pager();
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        pager.setOffset(0);
        session.setAttribute("sessieRit", rit);
        Lid user = (Lid) session.getAttribute("currentSessionUser");


        int offset = 0;
        // Haal query offset op, is deze leeg ga dan naar de eerste resultaat pagina 
        // door offset gelijk aan 0 te zetten.
        if (request.getParameter("offset") == null) {
            pager.setOffset(0);

        } else {

            offset = Integer.parseInt(request.getParameter("offset"));
        }

        String knop = request.getParameter("knop");
        // Hiermee kunnen we zien welke knop de gebruiker heeft gedrukt en stellen
        // zo de offset aan.
        if ("volgende".equals(knop)) {
            pager.setOffset(offset + 5);
        } else if ("vorige".equals(knop)) {
            pager.setOffset(offset - 5);
        }
        if (session.getAttribute("currentSessionUser") != null) {
            // Maak in de sessie een object rit aan met naam sessieRit
            session.setAttribute("sessieRit", rit);
            //Haal de userbean (dit moet sessiebean worden) op uit de sessie
         

            List<Rit> ritten;
            rit.setLidnr(user.getLidnr());
            ritDao.vulRitDao(rit);
            ritten = ritDao.getAlleRittenPerLid(pager.getOffset());

            if (ritten != null) {
                request.setAttribute("ritten", ritten);
                

                rit.setLidnr(user.getLidnr());
                ritDao.vulRitDao(rit);
                ritten = ritDao.getAlleRittenPerLid(pager.getOffset());

                pager.setAantalritten(ritDao.aantalRitten(user.getLidnr()));
                pager.setMaxPositie(pager.getAantalritten() - 5);
                pager.setStatusTotaalPager((int) Math.ceil(ritDao.aantalRitten(user.getLidnr()) / 5.0));
                pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset() + 5) / 5.0));
                request.setAttribute("ritten", ritten);
                session.setAttribute("pager", pager);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mijn_ritten.jsp");
                dispatcher.forward(request, response);
            } else {
                //ophalen van ritten is mislukt
                RequestDispatcher dispatcher = request.getRequestDispatcher("oops.jsp");
                dispatcher.forward(request, response);
            }

        } else {
            //niet ingelogd dus naar login pagina
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
    }
}
