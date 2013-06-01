/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.BerichtClass;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author H
 */
public class Berichtverzenden extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//haal de sessie op
		HttpSession session = request.getSession();

		if (session.getAttribute("currentSessionUser") != null) {
			int naar = Integer.parseInt(request.getParameter("naar"));
			String onderwerp = request.getParameter("onderwerp");
			String inhoud = request.getParameter("inhoud");
			int afzender = Integer.parseInt(request.getParameter("afzender"));
			String datum = request.getParameter("datum");

			BerichtClass verstuurbericht = new BerichtClass();
			if(verstuurbericht.verstuurbericht(naar, onderwerp, inhoud, datum, afzender)){
				response.sendRedirect("MijnBerichten");
			}else{
				//Als berichtverzenden fout gaat naar oopspagina
				request.getRequestDispatcher("oops.jsp").forward(request, response);
			}

		} else {
			//niet ingelogd dus naar login pagina
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

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
