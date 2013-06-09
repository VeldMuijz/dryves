/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Lid;
import Dryves.Model.LidDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;

/**
 *
 * @author RickSpijker
 */
public class Login extends HttpServlet {

	Session session = null;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
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

		try {
//Maakt nieuw lid object aan en haalt de waarde uit het veld email en wachtwoord op
//Deze worden weg gezet met een setter, vervolgens worden deze credentials mee gegeven aan de functie 
//LidDao.login(user)                  
                    
			Lid user = new Lid();

			user.setEmail(request.getParameter("email"));
			user.setWachtwoord(request.getParameter("wachtwoord"));

			user = LidDao.login(user);

			if (user.isValid()) {

				

				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);

				LidDao dao = new LidDao();
				dao.adminLogin(user);
                                //Hieronder wordt bepaald of het lid admin is of niet.
				if (user.getRol() == 1) {
                                    //Doorsturen naar een nadere pagina inclusief met data uit het lid object.
					request.getRequestDispatcher("WEB-INF/mijndryves.jsp").forward(request, response);

				} else if (user.getRol() == 2) {
					request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);

				}
			} else {
                            //Standaard redirect zonder data.
				System.out.println("Het inloggen is niet gelukt, probeer het opnieuw!");
				response.sendRedirect("login.jsp"); //Retry login 
			}
		} catch (Throwable theException) {
			System.out.println(theException);
		}
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
