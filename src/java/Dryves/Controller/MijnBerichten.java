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
import java.io.PrintWriter;
import java.sql.SQLException;
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
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Lid user = (Lid) session.getAttribute("currentSessionUser");

		try {
			List<Berichten> bericht;
			int userid = user.getLidnr();
			ArrayList<Lid> afzender = new ArrayList<Lid>();
                        pager.setOffset(0);
			bericht = berichtendao.haalberichten(userid, pager.getOffset());
			request.setAttribute("berichten", bericht);

			for (int i = 0; i < bericht.size(); i++) {
				Berichten berichtobject;
				berichtobject = bericht.get(i);
				int afzenderint;
				System.out.println("berichtobject Afzender = " + berichtobject.getAfzender());

				afzenderint = berichtobject.getAfzender();

				afzender.add(berichtendao.afzender(afzenderint));
				System.out.println("dit is i =" + i);
			}
                        pager.setAantalberichten(berichtendao.aantalBerichten(userid));
                        pager.setMaxPositie(pager.getAantalberichten()-5);
                        pager.setStatusTotaalPager((int) Math.ceil(berichtendao.aantalBerichten(userid) / 5.0));
                        pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset()+5) / 5.0));
			request.setAttribute("afzender", afzender);
                        session.setAttribute("pager", pager);

		} catch (SQLException e) {
			throw new ServletException("Kan gegevens niet ophalen van database.", e);

		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/mijnberichten.jsp");
		dispatcher.forward(request, response);

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
