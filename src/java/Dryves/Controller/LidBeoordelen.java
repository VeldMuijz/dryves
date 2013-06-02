/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.DatumConverter;
import Dryves.Model.AankoopDao;
import Dryves.Model.Beoordeling;
import Dryves.Model.BeoordelingDao;
import Dryves.Model.Lid;
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
public class LidBeoordelen extends HttpServlet {

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
		Beoordeling beoordeling = new Beoordeling();
		AankoopDao aankoopDao = new AankoopDao();
		DatumConverter dc = new DatumConverter();
		// Haal de huidige sessie op
		HttpSession session = request.getSession();

		//Als een ingelogd lid naar een servlet gaat doe het volgende:
		if (session.getAttribute("currentSessionUser") != null) {

			// Maak in de sessie een object rit aan met naam sessieRit
			session.setAttribute("sessieBeoordeling", beoordeling);
			//Haal de userbean (dit moet sessiebean worden) op uit de sessie
			Lid user = (Lid) session.getAttribute("currentSessionUser");
			Beoordeling sessieBeoordeling = (Beoordeling) session.getAttribute("sessieBeoordeling");
			System.out.println("Dit gaat in sessieBeoordeling: " + request.getParameter("aankoopnr"));
			sessieBeoordeling.setAankoopnr(Integer.parseInt(request.getParameter("aankoopnr")));
			sessieBeoordeling.setLidnr(user.getLidnr());

			System.out.println("+++++++++++++Security Check++++++++++++++++++\n Aankoopnr: " + beoordeling.getAankoopnr() + " Lidnr: " + user.getLidnr());
			//check of dit lid wel bij deze rit hoort
			if (!aankoopDao.checkBestaanAankoop(sessieBeoordeling.getAankoopnr(), user.getLidnr())) {
				System.out.println("Er zijn geen aankopen gevonden voor lid" + user.getLidnr() + " en aankoopnr: " + beoordeling.getAankoopnr());
				System.out.println("Dit lid mag deze aankoop niet beoordelen, terug naar MijnAankopen!");
				response.sendRedirect("MijnAankopen");

			} else {
				System.out.println("Lid mag deze aankoop beoordelen");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/beoordelen.jsp");
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
		// Instantieren van objecten
		Beoordeling beoordeling = new Beoordeling();
		BeoordelingDao beoordelingDao = new BeoordelingDao();
		DatumConverter dc = new DatumConverter();
		// Haal de huidige sessie op
		HttpSession session = request.getSession();

		//Als een ingelogd lid naar een servlet gaat doe het volgende:
		if (session.getAttribute("currentSessionUser") != null) {
			// Maak in de sessie een object rit aan met naam sessieRit
			session.setAttribute("sessieBeoordeling", beoordeling);
			//Haal de userbean (dit moet sessiebean worden) op uit de sessie
			Lid user = (Lid) session.getAttribute("currentSessionUser");

			System.out.println("Dit is sessie aankoopnr: " + beoordeling.getAankoopnr());
			int aankoopnr = Integer.parseInt(request.getParameter("aankoopnr"));
			int stiptheid = Integer.parseInt(request.getParameter("stiptheid"));
			int betrouwbaarheid = Integer.parseInt(request.getParameter("betrouwbaarheid"));
			int gezelligheid = Integer.parseInt(request.getParameter("gezelligheid"));
			int rijstijl = Integer.parseInt(request.getParameter("rijstijl"));
			double waardering = (stiptheid + betrouwbaarheid + gezelligheid + rijstijl) / 4;

			String opmerking = request.getParameter("opmerking");
			System.out.println(waardering + "," + stiptheid + "," + rijstijl + "," + gezelligheid + "," + betrouwbaarheid + "," + opmerking + "," + user.getLidnr() + "," + aankoopnr);
			if (beoordelingDao.beoordelingAanmaken(waardering, stiptheid, rijstijl, gezelligheid, betrouwbaarheid, opmerking, user.getLidnr(), aankoopnr)) {
				System.out.println("Beoordeling aangemaakt");
				response.sendRedirect("MijnAankopen");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/oops.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			//niet ingelogd dus naar login pagina
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

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
