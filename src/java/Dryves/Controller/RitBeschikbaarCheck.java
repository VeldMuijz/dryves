/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Aankoop;
import Dryves.Model.AankoopDao;
import Dryves.Model.Lid;
import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import java.io.IOException;
import java.io.PrintWriter;
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
public class RitBeschikbaarCheck extends HttpServlet {

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
		Aankoop aankoop = new Aankoop();
		AankoopDao aankoopDao = new AankoopDao();


		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieRit", rit);
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Lid user = (Lid) session.getAttribute("currentSessionUser");

		rit.setRitnr(Integer.parseInt(request.getParameter("ritnr")));

		String referer = request.getHeader("Referer");

		//check of dit lid wel bij deze rit hoort
		if (!ritDao.checkBeschikbaarheidRit(rit.getRitnr())) {

			System.out.println("Deze rit is uitverkocht!");
			response.sendRedirect("ritnietbeschikbaar.jsp");

		} else if (!referer.contains("RitKopen") && ritDao.updateZitplaatsVerlagen(rit.getRitnr(), 1)) {
			System.out.println("Rit is beschikbaar en kan gekocht worden, door naar RitKopen servlet");
			response.sendRedirect("RitKopen");

		} else if (referer.contains("RitKopen") && ritDao.updateZitplaatsOphogen(rit.getRitnr(), 1)) {
		} else {
			System.out.println("Deze rit is uitverkocht!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ritnietbeschikbaar.jsp");
			dispatcher.forward(request, response);

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
