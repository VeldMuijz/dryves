/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

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
 * @author H
 */
public class RitPager extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RitPager</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RitPager at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          Pager pager = new Pager();
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Dryves.Model.Lid user = (Dryves.Model.Lid) session.getAttribute("currentSessionUser");
        Dryves.Model.Rit rit = new Dryves.Model.Rit();
        Dryves.Model.RitDao ritDao = new Dryves.Model.RitDao();

        int offset = Integer.parseInt(request.getParameter("offset"));
        String knop = request.getParameter("knop");


        if ("volgende".equals(knop)) {
            pager.setOffset(offset + 5);
        } else if ("vorige".equals(knop)) {
            pager.setOffset(offset - 5);
        }



        

        // Maak in de sessie een object rit aan met naam sessieRit
        session.setAttribute("sessieRit", rit);
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie

        try {
            List<Dryves.Model.Rit> ritten;
            
            rit.setLidnr(user.getLidnr());
            ritDao.vulRitDao(rit);
            ritten = ritDao.getAlleRittenPerLid(pager.getOffset());

            pager.setAantalritten(ritDao.aantalRitten(user.getLidnr()));
            pager.setMaxPositie(pager.getAantalritten() - 5);
            pager.setStatusTotaalPager((int) Math.ceil(ritDao.aantalRitten(user.getLidnr()) / 5.0));
            pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset()+5) / 5.0));
            
           
            
            request.setAttribute("ritten", ritten);
            session.setAttribute("pager", pager);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mijn_ritten.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Kan gegevens niet ophalen uit database", e);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
