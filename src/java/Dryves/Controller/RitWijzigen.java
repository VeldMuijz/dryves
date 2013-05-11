/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.DatumConverter;
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
public class RitWijzigen extends HttpServlet {

	String stringDatum;
	String stringEindDatum;
	String stringTijd;
	String timestamp;
	String eindTimestamp;

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
			out.println("<title>Servlet RitWijzigen</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet RitWijzigen at " + request.getContextPath() + "</h1>");
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
		Rit rit = new Rit();
		RitDao ritDao = new RitDao();

		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieRit", rit);
		Rit sessieRit = (Rit) session.getAttribute("sessieRit");
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Lid user = (Lid) session.getAttribute("currentSessionUser");

		rit.setRitnr(Integer.parseInt(request.getParameter("ritnr")));
		System.out.println("Dit is het ritnummer: " + rit.getRitnr());
		ritDao.enkeleRitOphalen(rit.getRitnr(), rit);

		System.out.println("+++++++++++++Security Check++++++++++++++++++\n Lidnr uit rit: " + rit.getLidnr() + " lidnr uit lid: " + user.getLidnr());
		//check of dit lid wel bij deze rit hoort
		if (rit.getLidnr() != user.getLidnr()) {
			
			System.out.println("Dit lid mag deze rit niet aanpassen, terug naar MijnRitten!");
			response.sendRedirect("MijnRitten");

		}else{
//
//		Date datum = null;
//		Date einddatum = null;
//
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//		SimpleDateFormat datumFormat = new SimpleDateFormat("dd/MM/yyyy");
//		SimpleDateFormat tijdFormat = new SimpleDateFormat("HH:mm");
//
//		try {
//			System.out.println(sessieRit.getDatum().toString());
//			datum = dateFormat.parse(sessieRit.getDatum().toString());
//			stringDatum = datumFormat.format(datum);
//			String tijd = tijdFormat.format(datum);
//			rit.setTijd(tijd);
//			rit.setDatumkort(stringDatum);
//			
//
//			System.out.println("Dit is datum na conversie: " + stringDatum);
//			System.out.println("Dit is tijd na conversie: " + tijd);
//
//		} catch (ParseException ex) {
//			Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
//			System.out.println("************ Programma snapt Timestamp niet!");
//		}
			DatumConverter dc = new DatumConverter();

			rit.setDatumkort(dc.korteDatum(sessieRit.getDatum()));
			rit.setTijd(dc.korteTijd(sessieRit.getDatum()));
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ritwijzigen.jsp");
		dispatcher.forward(request, response);
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
		processRequest(request, response);
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
