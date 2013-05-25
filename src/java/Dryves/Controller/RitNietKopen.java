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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeroen
 */
public class RitNietKopen extends HttpServlet {

	
	

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
		RitDao ritDao = new RitDao();
		Aankoop aankoop = new Aankoop();
		AankoopDao aankoopDao = new AankoopDao();

		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Lid user = (Lid) session.getAttribute("currentSessionUser");
		Rit rit = (Rit) session.getAttribute("sessieRit");
		
		//String vorigepagina = request.getParameter("vorigepagina");
		
		//System.out.println("Dit is de vorige pagina: " + vorigepagina);
		if(ritDao.updateZitplaatsOphogen(rit.getRitnr(), 1)){
			response.sendRedirect("MijnDryves");
		};
		
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
