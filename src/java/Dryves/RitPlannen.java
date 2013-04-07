/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeroen
 */
public class RitPlannen extends HttpServlet {

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
			out.println("<title>Servlet RitPlannen</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet RitPlannen at " + request.getContextPath() + "</h1>");
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
		//processRequest(request, response);

		Rit rit = new Rit();
		UserBean user = new UserBean();
		
		//HttpSession session = request.getSession(true);       
          //session.setAttribute("currentSessionUser",user);
		  
		
		
		// Maak van de datum ipv een String een type Date
		Date datum = null;
		String stringDatum = request.getParameter("begindatum");
		String stringTijd = request.getParameter("tijd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				
		try {
			
			datum = dateFormat.parse(stringDatum +" "+ stringTijd);
			
		} catch (ParseException ex) {
			Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		System.out.println("Stringdatum: " + stringDatum);
		 
		
		//rit.setLidnr(rit.getLidnr());
		rit.setStartpunt(request.getParameter("start"));
		rit.setEindpunt(request.getParameter("end"));
		rit.setWaypoint(request.getParameter("waypoint"));
		rit.setAfstand(BigDecimal.ZERO);
		rit.setPrijs(BigDecimal.ZERO);
		rit.setGekocht(0);
		rit.setDatum(datum);
		rit.setZitplaatsen(Integer.parseInt(request.getParameter("aantalZitplaatsen")));

		//check of rit direct aangeboden mag worden
		if (request.getParameter("aanbieden") != null) {
			rit.setAangeboden(1);
		} else {
			rit.setAangeboden(0);
		}
		rit.setBrandstof(request.getParameter("soortBrandstof"));

			  	  
		  
		  
		  
		 //rit.setLidnr(user.getLidnr());
		
		rit = RitDao.ritplannen(rit);


		/**
		 * String invoerRitQuery = "insert into Rit (lidnr, startpunt, eindpunt,
		 * waypoint, afstand, prijs, gekocht, datum, zitplaatsen, brandstof,
		 * aangeboden) \n"+ "Values("+ rit.getLidnr()+"," + rit.getStartpunt()
		 * +"," + rit.getEindpunt() +"," + rit.getWaypoint() +"," +
		 * rit.getAfstand() +"," + rit.getPrijs() +"," + rit.getGekocht() +"," +
		 * rit.getDatum() +"," + rit.getZitplaatsen() +"," + rit.getBrandstof()
		 * +"," + rit.getAangeboden() + ");";
		 *
		 *
*/
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