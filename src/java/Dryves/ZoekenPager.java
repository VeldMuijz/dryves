/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

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
public class ZoekenPager extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ZoekenPager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ZoekenPager at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Dryves.Model.Rit rit = new Dryves.Model.Rit();
	Pager pager = new Pager();
        Dryves.Model.RitDao ritDao = new Dryves.Model.RitDao();
        HttpSession session = request.getSession(true);
        // Maak in de sessie een object rit aan met naam sessieRit
        session.setAttribute("sessieRit", rit);
        //   Lid user = (Lid) session.getAttribute("currentSessionUser");
        pager.setStartpunt(request.getParameter("zoekritbegin"));
        pager.setEindpunt(request.getParameter("zoekriteind"));
             

        int offset = Integer.parseInt(request.getParameter("offset"));
        String knop = request.getParameter("knop");


        if ("volgende".equals(knop)) {
            pager.setOffset(offset + 5);
        } else if ("vorige".equals(knop)) {
            pager.setOffset(offset - 5);
        }

        try {
            List<Dryves.Model.Rit> ritten;
            // rit.setLidnr(user.getLidnr());
            ritDao.vulRitDao(rit);
            ritten = ritDao.getAlleRitten(pager.getStartpunt(), pager.getEindpunt(), pager.getOffset());


            pager.setAantalZoekResultaten(ritDao.aantalZoekRitten(pager.getStartpunt(), pager.getEindpunt()));
            pager.setMaxPositie(pager.getAantalZoekResultaten() - 5);
            pager.setStatusTotaalPager((int) Math.ceil(ritDao.aantalZoekRitten(pager.getStartpunt(), pager.getEindpunt()) / 5.0));
            pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset() + 5) / 5.0));
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
