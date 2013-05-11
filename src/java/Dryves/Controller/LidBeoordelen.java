/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.DatumConverter;
import Dryves.Model.Beoordeling;
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
public class LidBeoordelen extends HttpServlet {

	/**
	 * Processes requests for both HTTP
	 * <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet LidBeoordelen</title>");			
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet LidBeoordelen at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		} finally {			
			out.close();
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
		DatumConverter dc = new DatumConverter();
		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieBeoordeling", beoordeling);
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Lid user = (Lid) session.getAttribute("currentSessionUser");
		Beoordeling sessieBeoordeling = (Beoordeling) session.getAttribute("sessieBeoordeling");	
		
		sessieBeoordeling.setAankoopnr(Integer.parseInt(request.getParameter("aankoopnr")));
		sessieBeoordeling.setLidnr(user.getLidnr());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/beoordelen.jsp");
		dispatcher.forward(request, response);
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
		DatumConverter dc = new DatumConverter();
		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieBeoordeling", beoordeling);
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Lid user = (Lid) session.getAttribute("currentSessionUser");
		Beoordeling sessieBeoordeling = (Beoordeling) session.getAttribute("sessieBeoordeling");
		
		request.getParameter("aankoopnr");
		

		System.out.println("+++++++++++++Security Check++++++++++++++++++\n Lidnr uit aankoop: " + beoordeling.getLidnr() + " lidnr uit lid: " + user.getLidnr());
		//check of dit lid wel bij deze rit hoort
		if (beoordeling.getLidnr() != user.getLidnr()) {
			
			System.out.println("Dit lid mag deze aankoop niet beoordelen, terug naar MijnAankopen!");
			response.sendRedirect("MijnAankopen");

		}else{
			
		}
		


			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ritwijzigen.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
