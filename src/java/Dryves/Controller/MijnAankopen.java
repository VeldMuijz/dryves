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

        // Instantieren van objecten
        Pager pager = new Pager();
        Rit rit = new Rit();
        RitDao ritDao = new RitDao();
        Aankoop aankoop = new Aankoop();
        AankoopDao aankoopDao = new AankoopDao();
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        Lid user = (Lid) session.getAttribute("currentSessionUser");
        
        // Maak in de sessie een object rit aan met naam sessieRit, en sessieAankoop
        session.setAttribute("sessieRit", rit);
        session.setAttribute("sessieAankoop", aankoop);
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie

        //Hiermee stellen we de offset
        int offset = 0;
        // Haal query offset op, is deze leeg ga dan naar de eerste resultaat pagina 
        // door offset gelijk aan 0 te zetten.

        String knop = request.getParameter("knop");




     

        if (request.getParameter("offset") == null) {
            offset = 0;
        } else {
            offset = Integer.parseInt(request.getParameter("offset"));
        }
        // Hiermee kunnen we zien welke knop de gebruiker heeft gedrukt en stellen
        // zo de offset aan.
        if ("volgende".equals(knop)) {
            pager.setOffset(offset + 5);
        } else if ("vorige".equals(knop)) {
            pager.setOffset(offset - 5);
        }

        

        if (session.getAttribute("currentSessionUser") != null) {
            // Maak in de sessie een object rit aan met naam sessieRit, en sessieAankoop
            session.setAttribute("sessieRit", rit);
            session.setAttribute("sessieAankoop", aankoop);
           
           

            List<Rit> ritten;
            rit.setLidnr(user.getLidnr());
            ritDao.vulRitDao(rit);

            //haal alle gekochte ritten op uit database
            ritten = ritDao.getAlleGekochteRittenPerLid();

            //maak een lijst van aankopen aan vanuit de database
            List<Aankoop> aankopen;
            aankopen = aankoopDao.getAlleAankopenPerLid(user.getLidnr(), pager.getOffset());
            if (aankopen != null) {
                request.setAttribute("ritten", ritten);
                request.setAttribute("aankopen", aankopen);
                
            rit.setLidnr(user.getLidnr());
            ritDao.vulRitDao(rit);
            ritten = ritDao.getAlleGekochteRittenPerLid();

            //List<Aankoop> aankopen;
            aankopen = aankoopDao.getAlleAankopenPerLid(user.getLidnr(), pager.getOffset());

            pager.setAantalAankopen(aankoopDao.aantalAankopen(user.getLidnr()));
            pager.setMaxPositie(pager.getAantalAankopen() - 5);

            pager.setStatusTotaalPager((int) Math.ceil(aankoopDao.aantalAankopen(user.getLidnr()) / 5.0));
            pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset() + 5) / 5.0));

            request.setAttribute("ritten", ritten);
            request.setAttribute("aankopen", aankopen);
            session.setAttribute("pager", pager);
              
                
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/mijnaankopen.jsp");
                dispatcher.forward(request, response);

            } else {
                //ophalen van aankopen is mislukt
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
