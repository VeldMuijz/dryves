/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Lid;
import Dryves.Model.LidDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author H
 */
public class WijzigTaal extends HttpServlet {

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

		LidDao lidDao = new LidDao();
		HttpSession session = request.getSession();
		Lid user;
		if (session.getAttribute("currentSessionUser") != null){
			user = (Lid) session.getAttribute("currentSessionUser");
		}else{
			user = new Lid();
		}
		String taal = request.getParameter("land");

		if (user.getLidnr() > 0 ) {
			System.out.println("SESSIE OBJECT IS GEVULD");
			int lidnr = Integer.parseInt(request.getParameter("lidnr"));
			if ("nl_NL".equals(taal)) {

				lidDao.wijzigtaal("nl_NL", lidnr);

			} else if ("en_GB".equals(taal)) {

				lidDao.wijzigtaal("en_GB", lidnr);
			}
			user = lidDao.enkelLidOphalen(user.getLidnr(), user);
			user.setLocale(taal);
			user.setLangnotify(taal);
			user.setLocaleStr(taal);
			System.out.println("taal = " + taal);
			session.setAttribute("currentSessionUser", user);
		} else {
			System.out.println("ANONIEME SESSIE!");
//			user = new Lid();
			user.setLocale(taal);
			
			session.setAttribute("currentSessionUser", user);

		}
		
		String referer = request.getHeader("Referer"); 
         if(referer.contains("LoginServlet")){
			 String mijnDryves = "MijnDryves";
			 referer = referer.replace("LoginServlet", mijnDryves);
			 System.out.println("Referer na omzetten = " + referer);
			 response.sendRedirect(referer);
		 }else{
			 response.sendRedirect(referer);
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
