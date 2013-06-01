/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import Dryves.Model.AankoopDao;
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
public class AankoopPager extends HttpServlet {

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
            out.println("<title>Servlet AankoopPager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AankoopPager at " + request.getContextPath() + "</h1>");
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
        Pager pager = new Pager();
        Dryves.Model.Rit rit = new Dryves.Model.Rit();
        Dryves.Model.RitDao ritDao = new Dryves.Model.RitDao();
        Dryves.Model.Aankoop aankoop = new Dryves.Model.Aankoop();
        AankoopDao aankoopDao = new AankoopDao();
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        // Maak in de sessie een object rit aan met naam sessieRit, en sessieAankoop
        session.setAttribute("sessieRit", rit);
        session.setAttribute("sessieAankoop", aankoop);
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Dryves.Model.Lid user = (Dryves.Model.Lid) session.getAttribute("currentSessionUser");
        
        
        int offset = Integer.parseInt(request.getParameter("offset"));
        String knop = request.getParameter("knop");
        
        
        if ("volgende".equals(knop)) {
            pager.setOffset(offset + 5);
        } else if ("vorige".equals(knop)) {
            pager.setOffset(offset - 5);
        }

             
        
        
	
        
        try {
            List<Dryves.Model.Rit> ritten;
            
            rit.setLidnr(user.getLidnr());
            ritDao.vulRitDao(rit);
            ritten = ritDao.getAlleGekochteRittenPerLid();

            List<Dryves.Model.Aankoop> aankopen;
            aankopen = aankoopDao.getAlleAankopenPerLid(user.getLidnr(), pager.getOffset());
            
            pager.setStatusTotaalPager((int) Math.ceil(6 / 5.0));
            pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset()+5) / 5.0));
            
            System.out.print("getal is ddfd jjjjjjjjjjjjjjjjjjjjjj"+pager.getOffset());
             pager.setAantalAankopen(aankoopDao.aantalAankopen(user.getLidnr()));
             pager.setMaxPositie(pager.getAantalAankopen()-5);
            request.setAttribute("ritten", ritten);
            request.setAttribute("aankopen", aankopen);
            session.setAttribute("pager", pager);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/mijnaankopen.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain products from DB", e);
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
    }
}
