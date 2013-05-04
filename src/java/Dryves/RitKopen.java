/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
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
public class RitKopen extends HttpServlet {

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
			out.println("<title>Servlet RitKopen</title>");			
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet RitKopen at " + request.getContextPath() + "</h1>");
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
		
		// Instantieren van objecten
        Rit rit = new Rit();
        RitDao ritDao = new RitDao();
		Aankoop aankoop = new Aankoop();
		AankoopDao aankoopDao = new AankoopDao();
		
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        // Maak in de sessie een object rit aan met naam sessieRit
        session.setAttribute("sessieRit", rit);
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Lid user = (Lid) session.getAttribute("currentSessionUser");
		
		int ritnr = Integer.parseInt(request.getParameter("ritnr"));
		ritDao.enkeleRitOphalen(ritnr, rit);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ritkopen.jsp");
            dispatcher.forward(request, response);
		
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
		//processRequest(request, response);
		// Instantieren van objecten
        Rit rit = new Rit();
        RitDao ritDao = new RitDao();
		Aankoop aankoop = new Aankoop();
		AankoopDao aankoopDao = new AankoopDao();
		
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        // Maak in de sessie een object rit aan met naam sessieRit
        session.setAttribute("sessieRit", rit);
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Lid lid = (Lid) session.getAttribute("currentSessionUser");
		
		aankoop.setRitnr(Integer.parseInt(request.getParameter("ritnr")));
		aankoop.setBetaalwijze(request.getParameter("betaalwijze"));
		Date date = new Date();
		aankoop.setDatum(new Timestamp(date.getTime()));
		aankoop.setOntmoetingnr(1);
		aankoop.setFactuurnr(1);
		aankoop.setLidnr(lid.getLidnr());
		
		//Haal rit op
		ritDao.enkeleRitOphalen(aankoop.getRitnr(), rit);
		
		//Voer aankoop uit
		aankoopDao.vulAankoopDao(aankoop);
		aankoopDao.aankoopDoen();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("MijnDryves");
            dispatcher.forward(request, response);
		
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
