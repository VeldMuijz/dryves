/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.io.IOException;
import java.io.PrintWriter;

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
import org.joda.time.DateTime;

/**
 *
 * @author jeroen
 */
public class RitPlannen extends HttpServlet {

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
		// Instantieren van objecten
		Rit rit = new Rit();
		RitDao ritDao = new RitDao();
		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieRit", rit);
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Sessie user = (Sessie) session.getAttribute("currentSessionUser");

		//Haal alle gegevens op en zet ze in Rit
		//Bouw ingevoerde datum om naar een timestamp
		Date datum;
		Date einddatum;
		stringDatum = request.getParameter("begindatum");
		stringTijd = request.getParameter("tijd");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm");
		SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		try {
			datum = dateFormat.parse(stringDatum + 'T' + stringTijd);


			timestamp = timestampFormat.format(datum);

			ritDao.setBegindatum(DateTime.parse(timestamp));

			//Alleen einddatum verwerken als hij ingevuld is
			if (!request.getParameter("einddatum").isEmpty()) {

				stringEindDatum = request.getParameter("einddatum");
				System.out.println("StringEinddatum: " + stringEindDatum);
				einddatum = dateFormat.parse(stringEindDatum + 'T' + stringTijd);
				eindTimestamp = timestampFormat.format(einddatum);
				ritDao.setEinddatum(DateTime.parse(eindTimestamp));
			}

		} catch (ParseException ex) {
			Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("************ Programma snapt Timestamp niet!");
		}

		//Checken of herhaling aangevinkt is, zo ja vul de dagen van de week
		if (request.getParameter("herhaling") != null && !request.getParameter("einddatum").isEmpty()) {

			if (!request.getParameter("ma").isEmpty()) {
				ritDao.setMa(1);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("di").isEmpty()) {
				ritDao.setDi(2);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("wo").isEmpty()) {
				ritDao.setWo(3);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("don").isEmpty()) {
				ritDao.setDon(4);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("vr").isEmpty()) {
				ritDao.setVr(5);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("za").isEmpty()) {
				ritDao.setZa(6);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("zo").isEmpty()) {
				ritDao.setZo(7);
				ritDao.setMeerdere(1);
			}

		} else {
			ritDao.setMeerdere(0);
		}


		rit.setLidnr(user.getLidnr());
		rit.setStartpunt(request.getParameter("hiddenstart"));
		rit.setEindpunt(request.getParameter("hiddenend"));
		rit.setWaypoint(request.getParameter("hiddenwaypoints"));
		try {
			rit.setAfstand(Double.parseDouble(request.getParameter("hiddenafstand")));
		} catch (Exception e) {
			System.out.println(e);
		}

		double prijs = (rit.getAfstand() * 0.21); //TODO ophalen vanuit Configuratie
		rit.setPrijs(prijs);
		rit.setGekocht(0);
		rit.setZitplaatsen(Integer.parseInt(request.getParameter("aantalZitplaatsen")));

		//check of rit direct aangeboden mag worden
		if (request.getParameter("aanbieden") != null) {
			rit.setAangeboden(1);
		} else {
			rit.setAangeboden(0);
		}
		rit.setBrandstof(request.getParameter("soortBrandstof"));


		//voer de insert query uit om rit op te slaan
		ritDao.ritplannen(rit);


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
		Rit rit = new Rit();
		RitDao ritDao = new RitDao();
		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieRit", rit);
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Sessie user = (Sessie) session.getAttribute("currentSessionUser");

		//Haal alle gegevens op en zet ze in Rit
		//Bouw ingevoerde datum om naar een timestamp
		Date datum;
		Date einddatum;
		stringDatum = request.getParameter("begindatum");
		stringTijd = request.getParameter("tijd");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm");
		SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		try {
			datum = dateFormat.parse(stringDatum + 'T' + stringTijd);


			timestamp = timestampFormat.format(datum);

			ritDao.setBegindatum(DateTime.parse(timestamp));

			//Alleen einddatum verwerken als hij ingevuld is
			if (!request.getParameter("einddatum").isEmpty()) {

				stringEindDatum = request.getParameter("einddatum");
				System.out.println("StringEinddatum: " + stringEindDatum);
				einddatum = dateFormat.parse(stringEindDatum + 'T' + stringTijd);
				eindTimestamp = timestampFormat.format(einddatum);
				ritDao.setEinddatum(DateTime.parse(eindTimestamp));
			}

		} catch (ParseException ex) {
			Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("************ Programma snapt Timestamp niet!");
		}

		//Checken of herhaling aangevinkt is, zo ja vul de dagen van de week
		if (request.getParameter("herhaling") != null && !request.getParameter("einddatum").isEmpty()) {

			if (!request.getParameter("ma").isEmpty()) {
				ritDao.setMa(1);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("di").isEmpty()) {
				ritDao.setDi(2);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("wo").isEmpty()) {
				ritDao.setWo(3);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("don").isEmpty()) {
				ritDao.setDon(4);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("vr").isEmpty()) {
				ritDao.setVr(5);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("za").isEmpty()) {
				ritDao.setZa(6);
				ritDao.setMeerdere(1);
			} else if (!request.getParameter("zo").isEmpty()) {
				ritDao.setZo(7);
				ritDao.setMeerdere(1);
			}

		} else {
			ritDao.setMeerdere(0);
		}


		rit.setLidnr(user.getLidnr());
		rit.setStartpunt(request.getParameter("hiddenstart"));
		rit.setEindpunt(request.getParameter("hiddenend"));
		rit.setWaypoint(request.getParameter("hiddenwaypoints"));
		try {
			rit.setAfstand(Double.parseDouble(request.getParameter("hiddenafstand")));
		} catch (Exception e) {
			System.out.println(e);
		}

		double prijs = (rit.getAfstand() * 0.21); //TODO ophalen vanuit Configuratie
		rit.setPrijs(prijs);
		rit.setGekocht(0);
		rit.setZitplaatsen(Integer.parseInt(request.getParameter("aantalZitplaatsen")));
		
		//check of rit direct aangeboden mag worden
		if (request.getParameter("aanbieden") != null) {
			rit.setAangeboden(1);
		} else {
			rit.setAangeboden(0);
		}
		rit.setBrandstof(request.getParameter("soortBrandstof"));


		//voer de insert query uit om rit op te slaan
		ritDao.ritplannen(rit);
		
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

	public String getStringDatum() {
		return stringDatum;
	}

	public void setStringDatum(String stringDatum) {
		this.stringDatum = stringDatum;
	}

	public String getStringEindDatum() {
		return stringEindDatum;
	}

	public void setStringEindDatum(String stringEindDatum) {
		this.stringEindDatum = stringEindDatum;
	}

	public String getStringTijd() {
		return stringTijd;
	}

	public void setStringTijd(String stringTijd) {
		this.stringTijd = stringTijd;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getEindTimestamp() {
		return eindTimestamp;
	}

	public void setEindTimestamp(String eindTimestamp) {
		this.eindTimestamp = eindTimestamp;
	}
}
