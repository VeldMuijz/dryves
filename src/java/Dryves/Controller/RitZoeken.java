/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import Dryves.Pager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Vincent
 */
public class RitZoeken extends HttpServlet {

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
            out.println("<title>Servlet RitZoeken</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RitZoeken at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        
        	Rit rit = new Rit();
		RitDao ritDao = new RitDao();
                Pager pager = new Pager();
                pager.setOffset(0);
		// Haal de huidige sessie op
                
		HttpSession session = request.getSession(true);
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieRit", rit);
		//   Lid user = (Lid) session.getAttribute("currentSessionUser");
                pager.setStartpunt(request.getParameter("zoekritbegin"));
                pager.setEindpunt(request.getParameter("zoekriteind"));
        try {
            List<Rit> ritten;
           // rit.setLidnr(user.getLidnr());
            ritDao.vulRitDao(rit);
            ritten = ritDao.getAlleRitten(pager.getStartpunt(),pager.getEindpunt(), pager.getOffset());
             
            
             pager.setAantalritten(ritDao.aantalZoekRitten(pager.getStartpunt(),pager.getEindpunt()));
             pager.setMaxPositie(ritDao.aantalZoekRitten(pager.getStartpunt(), pager.getEindpunt())-5);
             pager.setStatusTotaalPager((int) Math.ceil(ritDao.aantalZoekRitten(pager.getStartpunt(), pager.getEindpunt()) / 5.0));
             pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset()+5) / 5.0));
             pager.setAantalZoekResultaten(ritDao.aantalZoekRitten(pager.getStartpunt(), pager.getEindpunt()));
             request.setAttribute("ritten", ritten);
             session.setAttribute("pager", pager);
//			String formatprijs = ritten.get(4).toString();
//				String format = String.format("€ %.2f", formatprijs);
//				String format1 = String.format("€ %.2f", ritten.get(4));
            RequestDispatcher dispatcher = request.getRequestDispatcher("zoek_ritten.jsp");
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
