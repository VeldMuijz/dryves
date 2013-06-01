/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import Dryves.Pager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vincent
 */
public class RitZoeken extends HttpServlet {

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
        //processRequest(request, response);

        Rit rit = new Rit();
        RitDao ritDao = new RitDao();
        
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        // Maak in de sessie een object rit aan met naam sessieRit
        session.setAttribute("sessieRit", rit);
        //   Lid user = (Lid) session.getAttribute("currentSessionUser");
        String startpunt = request.getParameter("zoekritbegin");
        String eindpunt = request.getParameter("zoekriteind");

        List<Rit> ritten;


        ritDao.vulRitDao(rit);
        //ritten = ritDao.getAlleRitten(startpunt, eindpunt);




        Pager pager = new Pager();
        pager.setOffset(0);
        // Haal de huidige sessie op


        // Maak in de sessie een object rit aan met naam sessieRit
        session.setAttribute("sessieRit", rit);
        //   Lid user = (Lid) session.getAttribute("currentSessionUser");
        pager.setStartpunt(request.getParameter("zoekritbegin"));
        pager.setEindpunt(request.getParameter("zoekriteind"));

        int offset = 0;
        String knop = request.getParameter("knop");

        if (request.getParameter("offset") == null) {
            pager.setOffset(0);

        } else {

            offset = Integer.parseInt(request.getParameter("offset"));
        }






        if ("volgende".equals(knop)) {
            pager.setOffset(offset + 5);
        } else if ("vorige".equals(knop)) {
            pager.setOffset(offset - 5);
        }




            ritDao.vulRitDao(rit);
            ritten = ritDao.getAlleRitten(pager.getStartpunt(),pager.getEindpunt(), pager.getOffset());
           


        if (ritten != null) {
            request.setAttribute("ritten", ritten);


            // rit.setLidnr(user.getLidnr());
            ritDao.vulRitDao(rit);
            ritten = ritDao.getAlleRitten(pager.getStartpunt(), pager.getEindpunt(), pager.getOffset());


            pager.setAantalritten(ritDao.aantalZoekRitten(pager.getStartpunt(), pager.getEindpunt()));
            pager.setMaxPositie(ritDao.aantalZoekRitten(pager.getStartpunt(), pager.getEindpunt()) - 5);
            pager.setStatusTotaalPager((int) Math.ceil(ritDao.aantalZoekRitten(pager.getStartpunt(), pager.getEindpunt()) / 5.0));
            pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset() + 5) / 5.0));
            pager.setAantalZoekResultaten(ritDao.aantalZoekRitten(pager.getStartpunt(), pager.getEindpunt()));
            request.setAttribute("ritten", ritten);
            session.setAttribute("pager", pager);
//

            RequestDispatcher dispatcher = request.getRequestDispatcher("zoek_ritten.jsp");
            dispatcher.forward(request, response);
        } else {
            //ritten konden niet opgehaald worden
            request.getRequestDispatcher("oops.jsp").forward(request, response);
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
