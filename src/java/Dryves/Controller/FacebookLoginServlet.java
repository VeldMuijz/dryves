package Dryves.Controller;

import Dryves.Model.Lid;
import Dryves.Model.LidDao;
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
public class FacebookLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//De objecten worden aangemaakt
		Lid lid = new Lid();
		LidDao lidDao = new LidDao();
		HttpSession session = request.getSession();

		lid.setAnaam(request.getParameter("achternaam"));
		lid.setVnaam(request.getParameter("voornaam"));
		lid.setFacebookid(request.getParameter("id"));
		lid.setEmail(request.getParameter("email"));
		lid.setLocaleStr("Facebook");

		String facebookid2 = lid.getFacebookid();
		
		if (session.getAttribute("currentSessionUser") != null) {
		//Met een facebookid gaan we kijken of de huidige gebruiker al eerder een keer heeft aangemeld
		//check of de email bestaat, zo niet dan wordt de gebruiker toegevoegd in de database
		if (!lidDao.checkDuplicateFacebookID(facebookid2)) {


			//Wanneer de email niet voorkomt in de database
			//wordt de gebruiker hier toegevoegd 
			lidDao.vulLidDao(lid);
			lidDao.addFacebookLid();

			Lid lid2 = new Lid();
			LidDao dao = new LidDao();
			lid2 = dao.loginFacebook(facebookid2);
			if (lid2 != null) {
				//Hier maken we een neiuwe sessie voor de gebruiker
				session = request.getSession(true);
				session.setAttribute("currentSessionUser", lid2);
				dao.adminLogin(lid2);
				request.getRequestDispatcher("WEB-INF/mijndryves.jsp").forward(request, response);
			} else {
				//Als het fout gaat bij het inloggen met een facebook account door naar oopspagina
				request.getRequestDispatcher("oops.jsp").forward(request, response);
			}

		} else {
			Lid lid2 = new Lid();

			lid2 = lidDao.loginFacebook(facebookid2);

			//Hier maken we een neiuwe sessie voor de gebruiker
			session = request.getSession(true);
			session.setAttribute("currentSessionUser", lid2);
			lidDao.adminLogin(lid2);
			request.getRequestDispatcher("WEB-INF/mijndryves.jsp").forward(request, response);

		}
		}else{
			//niet ingelogd dus naar login pagina
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}
}
