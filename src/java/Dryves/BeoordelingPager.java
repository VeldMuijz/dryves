/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import Dryves.Controller.MijnBeoordelingen;
import Dryves.Model.Beoordeling;
import Dryves.Model.BeoordelingDao;
import Dryves.Model.LidDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class BeoordelingPager extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BeoordelingPager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BeoordelingPager at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Instantieren van objecten
        Beoordeling beoordeling = new Beoordeling();
        BeoordelingDao beoordelingDao = new BeoordelingDao();
        LidDao lidDao = new LidDao();
        Dryves.Model.Lid beoordelaar = new Dryves.Model.Lid();
        
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Dryves.Model.Lid user = (Dryves.Model.Lid) session.getAttribute("currentSessionUser");
        Pager pager = new Pager();
        user = lidDao.enkelLidOphalen(user.getLidnr(), user);
        session.setAttribute("currentSessionUser", user);
       
        
        int offset = Integer.parseInt(request.getParameter("offset"));
        String knop = request.getParameter("knop");
        
        
        if ("volgende".equals(knop)) {
            pager.setOffset(offset + 5);
        } else if ("vorige".equals(knop)) {
            pager.setOffset(offset - 5);
        }

        

        try {
            ArrayList<Dryves.Model.Lid> beoordelaars = new ArrayList<Dryves.Model.Lid>();
            List<Beoordeling> beoordelingen;
            beoordelingen = beoordelingDao.getAlleBeoordelingenPerLid(user.getLidnr(), pager.getOffset());
            pager.setAantalbeoordelingen(beoordelingDao.aantalBeoordelingen(user.getLidnr()));
            pager.setMaxPositie(pager.getAantalbeoordelingen() - 5);
            pager.setStatusTotaalPager((int) Math.ceil(beoordelingDao.aantalBeoordelingen(user.getLidnr()) / 5.0));
            pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset()+5) / 5.0));
            request.setAttribute("beoordelingen", beoordelingen);

            for (int i = 0; i < beoordelingen.size(); i++) {
                Beoordeling beoordeel;
                beoordeel = beoordelingen.get(i);

                lidDao.enkelLidOphalen(beoordeel.getLidnr(), beoordelaar);

                beoordelaars.add(beoordelaar);

            }
            request.setAttribute("pager", pager);
            request.setAttribute("beoordelaars", beoordelaars);


        } catch (SQLException ex) {
            Logger.getLogger(MijnBeoordelingen.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException("Kan gegevens niet ophalen uit database", ex);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/mijnbeoordelingen.jsp");
        dispatcher.forward(request, response);




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
