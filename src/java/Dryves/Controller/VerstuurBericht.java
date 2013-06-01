/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Berichten;
import Dryves.Model.BerichtenDao;
import Dryves.Model.verstuurEmail;
import java.io.IOException;
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
 * @author H
 */
public class VerstuurBericht extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("currentSessionUser") != null) {
			Berichten bericht = new Berichten();
			BerichtenDao berichtDao = new BerichtenDao();

			//huidige tijdstip opvragen
			Date datum = new Date();

			bericht.setRitnr(Integer.parseInt(request.getParameter("ritnr")));
			bericht.setLidnr(Integer.parseInt(request.getParameter("lidnr")));
			System.out.println("Bericht lidnummer: " + bericht.getLidnr());
			bericht.setInhoud(request.getParameter("inhoud"));
			bericht.setDatum(new Timestamp(datum.getTime()));
			System.out.println(bericht.getInhoud());

			if (berichtDao.BerichtVersturen(bericht)) {

				verstuurEmail ve = new verstuurEmail();

				String naar = "";
				String onderwerp = "U heeft een nieuw bericht!";
				String mail = "Dit is een test!";

				System.out.println("Dit is de afzender: " + bericht.getAfzender());

				//ve.verstuurEmailZonderBijlage(naar, onderwerp, mail);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/succesvol.jsp");
				dispatcher.forward(request, response);

			} else {
				//Bericht versturen is niet gelukt
				response.sendRedirect("oops.jsp");
			}
		} else {
			//Lid is niet ingelogd konden niet opgehaald worden
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
