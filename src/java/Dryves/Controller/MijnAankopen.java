/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Aankoop;
import Dryves.Model.AankoopDao;
import Dryves.Model.Lid;
import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import Dryves.Pager;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author jeroen
 */
public class MijnAankopen extends HttpServlet {

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
        Pager pager = new Pager();
	Rit rit = new Rit();
        RitDao ritDao = new RitDao();
        Aankoop aankoop = new Aankoop();
        AankoopDao aankoopDao = new AankoopDao();
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        // Maak in de sessie een object rit aan met naam sessieRit, en sessieAankoop
        session.setAttribute("sessieRit", rit);
        session.setAttribute("sessieAankoop", aankoop);
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Lid user = (Lid) session.getAttribute("currentSessionUser");
        pager.setOffset(0);
        try {
            List<Rit> ritten;
            
            rit.setLidnr(user.getLidnr());
            ritDao.vulRitDao(rit);
            ritten = ritDao.getAlleGekochteRittenPerLid();

            List<Aankoop> aankopen;
            aankopen = aankoopDao.getAlleAankopenPerLid(user.getLidnr(), pager.getOffset());
            
             pager.setAantalAankopen(aankoopDao.aantalAankopen(user.getLidnr()));
             pager.setMaxPositie(pager.getAantalAankopen()-5);
             
            pager.setStatusTotaalPager((int) Math.ceil(aankoopDao.aantalAankopen(user.getLidnr()) / 5.0));
            pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset()+5) / 5.0));
                        
            request.setAttribute("ritten", ritten);
            request.setAttribute("aankopen", aankopen);
            session.setAttribute("pager", pager);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/mijnaankopen.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain products from DB", e);
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
