/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Lid;
import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vincent
 */
@WebServlet(name = "MijnRitten", urlPatterns = {"/mijn_ritten"})
public class MijnRitten extends HttpServlet {

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
			//Haal de userbean (dit moet sessiebean worden) op uit de sessie
			Lid user = (Lid) session.getAttribute("currentSessionUser");

			List<Rit> ritten;
			rit.setLidnr(user.getLidnr());
			ritDao.vulRitDao(rit);
			ritten = ritDao.getAlleRittenPerLid();

			if (ritten != null) {
				request.setAttribute("ritten", ritten);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mijn_ritten.jsp");
				dispatcher.forward(request, response);
			}else {
				//ophalen van ritten is mislukt
				RequestDispatcher dispatcher = request.getRequestDispatcher("oops.jsp");
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
