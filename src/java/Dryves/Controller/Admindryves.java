/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.AdmindryvesDao;
import Dryves.Model.Lid;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RickSpijker
 */
public class Admindryves extends HttpServlet {

	private String achtergrond;
	private String ritprijs;

	public Admindryves() {
	}

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

		HttpSession session = request.getSession();
		//Maak een nieuw lid aan
		Lid user;
		if (session.getAttribute("currentSessionUser") != null) {
			user = (Lid) session.getAttribute("currentSessionUser");
			//Haal de nieuwe waardes op van de admin.jsp
			user.setAchtergrond(request.getParameter("achtergrond"));

			System.out.println("Waarde uit de admin jsp - achtergrond: " + user.getAchtergrond());

			user.setRitprijs(request.getParameter("ritprijs"));

			System.out.println("Waarde uit de admin jsp - ritprijs: " + user.getRitprijs());

			AdmindryvesDao add = new AdmindryvesDao();

			add.vulAdd(user);

			add.AdmindryvesDao();

			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			if (user.getLidnr() < 1) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else {
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
