/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import java.io.IOException;
import java.util.List;
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
		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieRit", rit);
		//   Lid user = (Lid) session.getAttribute("currentSessionUser");
		String startpunt = request.getParameter("zoekritbegin");
		String eindpunt = request.getParameter("zoekriteind");

		List<Rit> ritten;
		// rit.setLidnr(user.getLidnr());
		ritDao.vulRitDao(rit);
		System.out.println("Startpunt = " + startpunt);
		ritten = ritDao.getAlleRitten(startpunt, eindpunt);
		
		if (ritten != null) {
			request.setAttribute("ritten", ritten);
			RequestDispatcher dispatcher = request.getRequestDispatcher("zoek_ritten.jsp");
			dispatcher.forward(request, response);
		} else {
			//ritten konden niet opgehaald worden
			request.getRequestDispatcher("oops.jsp").forward(request, response);
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
