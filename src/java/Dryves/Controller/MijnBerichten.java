/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Berichten;
import Dryves.Model.BerichtenDao;
import Dryves.Model.Lid;
import Dryves.Pager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class MijnBerichten extends HttpServlet {

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
		Berichten berichten = new Berichten();
		BerichtenDao berichtendao = new BerichtenDao();
		Pager pager = new Pager();

		// Haal de huidige sessie op
		HttpSession session = request.getSession();

		if (session.getAttribute("currentSessionUser") != null) {
			//Haal de userbean (dit moet sessiebean worden) op uit de sessie
			Lid user = (Lid) session.getAttribute("currentSessionUser");
			int afzenderLidnr;
			System.out.println("berichtobject Afzender = " + berichten.getAfzender());

			int offset = 0;
			String knop = request.getParameter("knop");
			// Haal query offset op, is deze leeg ga dan naar de eerste resultaat pagina 
			// door offset gelijk aan 0 te zetten.
			if (request.getParameter("offset") == null) {
				pager.setOffset(0);
			} else {
				offset = Integer.parseInt(request.getParameter("offset"));
			}

			// Hiermee kunnen we zien welke knop de gebruiker heeft gedrukt en stellen
			// zo de offset aan.
			if ("volgende".equals(knop)) {
				pager.setOffset(offset + 5);
			} else if ("vorige".equals(knop)) {
				pager.setOffset(offset - 5);
			}

			List<Berichten> bericht;
			int userid = user.getLidnr();
			ArrayList<Lid> afzender = new ArrayList<Lid>();
			Lid afzenderLid;
			
			bericht = berichtendao.haalberichten(userid, pager.getOffset());

			if (bericht != null) {
				for (int i = 0; i < bericht.size(); i++) {
					Berichten berichtobject;
					berichtobject = bericht.get(i);
					afzenderLid = new Lid();
					System.out.println("berichtobject Afzender = " + berichtobject.getAfzender());

					afzenderLidnr = berichtobject.getAfzender();

					afzender.add(i, berichtendao.afzender(afzenderLidnr));

				}
				request.setAttribute("afzender", afzender);

				pager.setAantalberichten(berichtendao.aantalBerichten(userid));
				pager.setMaxPositie(pager.getAantalberichten() - 5);
				pager.setStatusTotaalPager((int) Math.ceil(berichtendao.aantalBerichten(userid) / 5.0));
				pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset() + 5) / 5.0));
				session.setAttribute("pager", pager);

				request.setAttribute("berichten", bericht);

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/mijnberichten.jsp");
				dispatcher.forward(request, response);
			} else {
				//ophalen van berichten is mislukt
				RequestDispatcher dispatcher = request.getRequestDispatcher("oops.jsp");
				dispatcher.forward(request, response);
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
