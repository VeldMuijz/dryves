/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.PDF;
import Dryves.Model.Aankoop;
import Dryves.DatumConverter;
import Dryves.Model.AankoopDao;
import Dryves.Model.Lid;
import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import Dryves.Model.verstuurEmail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
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
public class RitKopen extends HttpServlet {

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

		// Instantieren van objecten
		RitDao ritDao = new RitDao();
		Aankoop aankoop = new Aankoop();
		AankoopDao aankoopDao = new AankoopDao();

		// Haal de huidige sessie op
		HttpSession session = request.getSession();

		if (session.getAttribute("currentSessionUser") != null) {
			//Haal de userbean (dit moet sessiebean worden) op uit de sessie
			Lid user = (Lid) session.getAttribute("currentSessionUser");
			//Haal de ritbean op uit de sessie
			Rit rit = (Rit) session.getAttribute("sessieRit");

			int ritnr = rit.getRitnr();

			ritDao.enkeleRitOphalen(ritnr, rit);

			//check of dit lid wel bij deze rit hoort
			if (rit.getAangeboden() < 1) {

				System.out.println("Dit lid mag deze rit niet kopen, terug naar MijnRitten!");
				response.sendRedirect("MijnRitten");

			} else {

				DatumConverter dc = new DatumConverter();

				rit.setDatumkort(dc.korteDatum(rit.getDatum()));
				rit.setTijd(dc.korteTijd(rit.getDatum()));

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ritkopen.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			//niet ingelogd dus naar login pagina
			request.getRequestDispatcher("login.jsp").forward(request, response);
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
		//processRequest(request, response);
		// Instantieren van objecten
		Rit rit = new Rit();
		RitDao ritDao = new RitDao();
		Aankoop aankoop = new Aankoop();
		AankoopDao aankoopDao = new AankoopDao();

		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieRit", rit);
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
                //explictiete converter, er word een lid object aangemaakt op basis van de CurrentSession user
                //attributen.
		Lid lid = (Lid) session.getAttribute("currentSessionUser");

		aankoop.setRitnr(Integer.parseInt(request.getParameter("ritnr")));
		aankoop.setBetaalwijze(request.getParameter("betaalwijze"));
		Date date = new Date();
		aankoop.setDatum(new Timestamp(date.getTime()));
		aankoop.setOntmoetingnr(1);
		aankoop.setFactuurnr(1);
		aankoop.setLidnr(lid.getLidnr());

		//Haal rit op
		ritDao.enkeleRitOphalen(aankoop.getRitnr(), rit);

		//Voer aankoop uit
		aankoopDao.vulAankoopDao(aankoop);
		if (!aankoopDao.aankoopDoen(rit.getPrijs())) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/oops.jsp");
			dispatcher.forward(request, response);
		} else {

			PDF pdf = new PDF();

			//Vullen van de gegevens, in de PDF
			pdf.vulDePDF(lid.getVnaam(), lid.getAnaam(), lid.getEmail(), rit.getRitnr(), aankoop.getBetaalwijze(), aankoop.getFactuurnr());

			//Hier wordt de PDF opgesteld. 
			//TODO PDF wordt nog niet meegegeven in de mail, vandaar uitgeremd.
			//pdf.bouwPDF();

			String[] arrSplit = rit.getStartpunt().split(", ");

			rit.setStraatnummer(arrSplit[0]);
			rit.setPostcodeplaats(arrSplit[1].substring(5));
			rit.setLand(arrSplit[2]);

			String[] arrSplit2 = rit.getEindpunt().split(", ");

			rit.setStraatnummerEnd(arrSplit2[0]);
			rit.setPostcodeplaatsEnd(arrSplit2[1].substring(5));
			rit.setLandEnd(arrSplit[2]);

			//Mailfunctie! Hier moet alleen nog de PDf aan toegevoegd worden.
			//TODO PDF toevoegen aan de mail.
			String van = "dryveseu@gmail.com";
			String naar = lid.getEmail();
			String onderwerp = "Dryves factuurnummer " + aankoop.getFactuurnr();
			String bericht = "Ritnummer: " + rit.getRitnr()
					+ "\n" + "Factuurnummer: " + aankoop.getFactuurnr()
					+ "\n"
					+ "\n" + "Van: "
					+ "\n" + rit.getStraatnummer()
					+ "\n" + rit.getPostcodeplaats()
					+ "\n" + rit.getLand()
					+ "\n"
					+ "\n" + "Naar: "
					+ "\n" + rit.getStraatnummerEnd()
					+ "\n" + rit.getPostcodeplaatsEnd()
					+ "\n" + rit.getLandEnd()
					+ "\n"
					+ "\n" + "Datum & Tijd: " + rit.getDatum()
					+ "\n"
					+ "\n" + "U heeft deze rit gekocht via: " + aankoop.getBetaalwijze()
					+ "\n" + "Totaalbedrag: " + rit.getPrijs()
					+ "\n"
					+ "\n" + "Bedankt voor uw aankoop en graag tot ziens!";

			String attachment = "/Users/RickSpijker/Desktop/FirstPdf.pdf";
			String attachmentName = "Dryves Factuur: " + aankoop.getFactuurnr() + ".pdf";

			verstuurEmail ve = new verstuurEmail();

			ve.verstuurEmail(van, naar, onderwerp, bericht, attachment, attachmentName);

			response.sendRedirect("MijnAankopen");
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
	}
}
