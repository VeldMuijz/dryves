/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Lid;
import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import Dryves.Pager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                Pager pager = new Pager();
                pager.setOffset(0);
		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieRit", rit);
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Lid user = (Lid) session.getAttribute("currentSessionUser");
		try {
			List<Rit> ritten;
                        pager.setOffset(0);
			rit.setLidnr(user.getLidnr());
			ritDao.vulRitDao(rit);
			ritten = ritDao.getAlleRittenPerLid(pager.getOffset());
                        
                        pager.setAantalritten(ritDao.aantalRitten(user.getLidnr()));
                        pager.setMaxPositie(pager.getAantalritten()-5);
                        pager.setStatusTotaalPager((int) Math.ceil(ritDao.aantalRitten(user.getLidnr()) / 5.0));
                        pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset()+5) / 5.0));
			request.setAttribute("ritten", ritten);
                        session.setAttribute("pager", pager);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mijn_ritten.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			throw new ServletException("Kan gegevens niet ophalen uit database", e);
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
