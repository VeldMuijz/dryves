/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeroen
 */
public class RitPlannen extends HttpServlet {

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
			out.println("<title>Servlet RitPlannen</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet RitPlannen at " + request.getContextPath() + "</h1>");
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
		// Instantieren van objecten
		Rit rit = new Rit();
		RitDao ritDao = new RitDao();
		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieRit", rit);
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Sessie user = (Sessie) session.getAttribute("currentSessionUser");

		//Haal alle gegevens op en zet ze in Rit
		//Bouw ingevoerde datum om naar een timestamp
		Date datum;
		String stringDatum = request.getParameter("begindatum");
		String stringTijd = request.getParameter("tijd");
		
		String timestamp;
				
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		try {
			datum = dateFormat.parse(stringDatum + " " + stringTijd);
			timestamp = timestampFormat.format(datum);
			timestamp = timestamp + ":00";
			System.out.println("dit is timestamp na conversie: " + timestamp);
			
			rit.setDatum(Timestamp.valueOf(timestamp));
			
		} catch (ParseException ex) {
			Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("************ Programma snapt Timestamp niet!");
		}


		rit.setLidnr(user.getLidnr());
		rit.setStartpunt(request.getParameter("hiddenstart"));
		rit.setEindpunt(request.getParameter("hiddenend"));
		rit.setWaypoint(request.getParameter("hiddenwaypoints"));
		try {
			rit.setAfstand(Double.parseDouble(request.getParameter("hiddenafstand")));
		} catch (Exception e) {
			System.out.println(e);
		}

		double prijs = (rit.getAfstand() * 0.21); //TODO ophalen vanuit Configuratie
		rit.setPrijs(prijs);
		rit.setGekocht(0);
		rit.setZitplaatsen(Integer.parseInt(request.getParameter("aantalZitplaatsen")));

		//check of rit direct aangeboden mag worden
		if (request.getParameter("aanbieden") != null) {
			rit.setAangeboden(1);
		} else {
			rit.setAangeboden(0);
		}
		rit.setBrandstof(request.getParameter("soortBrandstof"));


		//voer de insert query uit om rit op te slaan
		ritDao.ritplannen(rit);

			
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