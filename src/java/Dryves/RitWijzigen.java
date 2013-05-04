/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

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
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Lid user = (Lid) session.getAttribute("currentSessionUser");

        System.out.println("**************** \n dit is rinr:   " + request.getParameter("ritnr"));

        rit.setRitnr(Integer.parseInt(request.getParameter("ritnr")));
        System.out.println("Dit is het ritnummer± " + rit.getRitnr());
        ritDao.enkeleRitOphalen(rit.getRitnr(), rit);
        System.out.println("dit is het eindpunt" + rit.getEindpunt());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ritwijzigen.jsp");
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
