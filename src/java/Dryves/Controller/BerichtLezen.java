/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Berichten;
import Dryves.Model.BerichtenDao;
import Dryves.Model.Lid;
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
 * @author H
 */
public class BerichtLezen extends HttpServlet {

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
		Berichten bericht = new Berichten();
		BerichtenDao berichtDao = new BerichtenDao();
		// Haal de huidige sessie op
		HttpSession session = request.getSession();

		if (session.getAttribute("currentSessionUser") != null) {
			// Maak in de sessie een object rit aan met naam sessieRit
			session.setAttribute("sessieRit", bericht);
			//Haal de userbean (dit moet sessiebean worden) op uit de sessie

			int berichtnr = Integer.parseInt(request.getParameter("berichtid"));
			System.out.println(berichtnr);
			Lid user = (Lid) session.getAttribute("currentSessionUser");
			berichtDao.markeerBericht(berichtnr);

			List<Berichten> ritten;
			bericht.setLidnr(user.getLidnr());
			berichtDao.vulBerichtDao(bericht);
			ritten = berichtDao.getAlleBerichtenbijId(berichtnr);
			
			if (ritten == null) {
				//Wanneer er iets fout gaat bij het vullen van het object ga naar oops-pagina
				request.getRequestDispatcher("oops.jsp").forward(request, response);

			} else {
				request.setAttribute("berichten", ritten);

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/helebericht.jsp");
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