/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author heuvenk
 */
public class Registreren extends HttpServlet {

	private String vnaam;
	private String anaam;
	private String geslacht;
	private String straat;
	private String huisnummer;
	private String postcode;
	private String stad;
	private String telnr;
	private String reknr;
	private String email;
	private String wachtwoord;
	private String fotoUrl;
	private String tvoegsel;
	private String langnotify;
	private Hashtable errors;

	public Registreren() {
	}

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
			out.println("<title>Servlet Registreren</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet Registreren at " + request.getContextPath() + "</h1>");
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

		//De objecten worden aangemmakt
		Lid lid = new Lid();
		String email = request.getParameter("email");
		LidDao lidDao = new LidDao();
		
		//check of de email bestaat, zo niet dan wordt de gebruiker toegevoegd in de database
		if (!lidDao.checkDuplicate(email)) {

			//email komt niet in de database voor

			//Zet de voornaam
			lid.setVnaam(request.getParameter("vnaam"));
			//Print voornaam naar console
			System.out.println("Dit is de voornaam: " + lid.getVnaam());

			//Zet de achternaam
			lid.setAnaam(request.getParameter("anaam"));
			//Print achternaam naar console
			System.out.println("Dit is de achternaam: " + lid.getAnaam());

			//Zet tvoegsel
			lid.setTvoegsel(request.getParameter("tvoegsel"));
			//Print tvoegsel naar de console
			System.out.println("Dit is de tussenvoegsel: " + lid.getTvoegsel());

			//Zet het geslacht
			lid.setGeslacht(request.getParameter("geslacht"));
			//Print geslacht naar de console
			System.out.println("Dit is het geslacht: " + lid.getGeslacht());

			//Zet de straat
			lid.setStraat(request.getParameter("straat"));
			//Print de straat naar de console
			System.out.println("Dit is de straat: " + lid.getStraat());

			//Zet het huisnummer
			lid.setHuisnummer(request.getParameter("huisnummer"));
			//Print het huisnummer naar de console
			System.out.println("Dit is het huisnummer: " + lid.getHuisnummer());

			//Zet de postcode
			lid.setPostcode(request.getParameter("postcode"));
			//Print de postcode naar de console
			System.out.println("Dit is de postcode: " + lid.getPostcode());

			//Zet stad
			lid.setStad(request.getParameter("stad"));
			//Print stad naar de console
			System.out.println("Dit is de stad: " + lid.getStad());

			//Zet het telefoonummer
			lid.setTelnr(request.getParameter("telnr"));
			//Schrijf telefoonnummer naar de console
			System.out.println("Dit is het telefoonnummer: " + lid.getTelnr());

			//Zet het rekeningnummer 
			lid.setReknr(request.getParameter("reknr"));
			//Schrijf het rekeningnummer naar de console
			System.out.println("Dit is het rekeningnummer: " + lid.getReknr());

			//Zet het email adres
			lid.setEmail(request.getParameter("email"));
			//Schrijf het email adres naar de console
			System.out.println("Dit is het email adres: " + lid.getEmail());

			//Zet het wachtwoord
			lid.setWachtwoord(request.getParameter("wachtwoord"));
			//Schrijf het wachtwoord naar de console
			System.out.println("Dit is het wachtwoord: " + lid.getWachtwoord());

			//Zet de fotourl
			lid.setFotoUrl(request.getParameter("fotoUrl"));
			//Schrijf de fotourl naar de console
			System.out.println("Dit is de fotourl: " + lid.getFotoUrl());

			//Zet de langnotify
			lid.setLangnotify(request.getParameter("locale"));
			//Schrijf langnotify naar de console
			System.out.println("Dit is de langnotify: " + lid.getLangnotify());
		
			lidDao.vulLidDao(lid);
			// hier wordt de gebruiker in de database opgeslagen
			lidDao.saveRegistratie();

//           //Hier maken we een nieuwe sessie voor de nieuwe gebruiker
//          HttpSession session = request.getSession(true);       
//          session.setAttribute("currentSessionUser",lid); 

			// en hier wordt de gebruiker door gelinked naar mijndryves
			response.sendRedirect("login.jsp"); //logged-in page  


		} else {
			//Indien het email bestaat wordt er een melding weergegeven.
			// Moet nog gedaan worden    
			response.sendRedirect("login_error");
		}

	}

	private static class request {

		public request() {
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
