/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.DatumConverter;
import Dryves.Model.Lid;
import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import java.io.IOException;
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
public class RitWijzigen extends HttpServlet {

	String stringDatum;
	String stringEindDatum;
	String stringTijd;
	String timestamp;
	String eindTimestamp;

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

		if (session.getAttribute("currentSessionUser") != null) {
			// Maak in de sessie een object rit aan met naam sessieRit
			session.setAttribute("sessieRit", rit);
			Rit sessieRit = (Rit) session.getAttribute("sessieRit");
			//Haal de userbean (dit moet sessiebean worden) op uit de sessie
			Lid user = (Lid) session.getAttribute("currentSessionUser");

			rit.setRitnr(Integer.parseInt(request.getParameter("ritnr")));
			System.out.println("Dit is het ritnummer: " + rit.getRitnr());
			ritDao.enkeleRitOphalen(rit.getRitnr(), rit);

			System.out.println("+++++++++++++Security Check++++++++++++++++++\n Lidnr uit rit: " + rit.getLidnr() + " lidnr uit lid: " + user.getLidnr());
			//check of dit lid wel bij deze rit hoort
			if (rit.getLidnr() != user.getLidnr()) {

				System.out.println("Dit lid mag deze rit niet aanpassen, terug naar MijnRitten!");
				response.sendRedirect("MijnRitten");

			} else {

				DatumConverter dc = new DatumConverter();

				rit.setDatumkort(dc.korteDatum(sessieRit.getDatum()));
				rit.setTijd(dc.korteTijd(sessieRit.getDatum()));

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ritwijzigen.jsp");
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
