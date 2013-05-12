/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.PDF;
import Dryves.DatumConverter;
import Dryves.Model.AankoopDao;
import Dryves.Model.Lid;
import Dryves.Model.Rit;
import Dryves.Model.RitDao;
import Dryves.Model.verstuurEmail;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
			out.println("<title>Servlet RitKopen</title>");			
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet RitKopen at " + request.getContextPath() + "</h1>");
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
        Lid user = (Lid) session.getAttribute("currentSessionUser");
		
		int ritnr = Integer.parseInt(request.getParameter("ritnr"));
		ritDao.enkeleRitOphalen(ritnr, rit);
//		        // Maak in de sessie een object rit aan met naam sessieRit
//        session.setAttribute("sessieRit", rit);
		
		
		//check of dit lid wel bij deze rit hoort
		if (rit.getAangeboden() < 1) {
			
			System.out.println("Dit lid mag deze rit niet aanpassen, terug naar MijnRitten!");
			response.sendRedirect("MijnRitten");

		}else{

//		Date datum = null;
//		Date einddatum = null;
//		String stringDatum, tijd;
//		
//
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//		SimpleDateFormat datumFormat = new SimpleDateFormat("dd/MM/yyyy");
//		SimpleDateFormat tijdFormat = new SimpleDateFormat("HH:mm");
//
//		try {
//			System.out.println(rit.getDatum().toString());
//			datum = dateFormat.parse(rit.getDatum().toString());
//			stringDatum = datumFormat.format(datum);
//			tijd = tijdFormat.format(datum);
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

			rit.setDatumkort(dc.korteDatum(rit.getDatum()));
			rit.setTijd(dc.korteTijd(rit.getDatum()));

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ritkopen.jsp");
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
		aankoopDao.aankoopDoen();
                
                PDF pdf = new PDF();
                
                //Hier wordt ervoor gezorgd, dat de gegevens van het Lid, gebruikt kunnen worden in de PDF.
                pdf.vulPDF(lid);
                
                //Hier worden de ritgegevens doorgespeeld aan de PDF class.
                pdf.vulRit(rit);
                
                //Hier worden de aankoopgegevens doorgespeeld aan de PDF class.
                pdf.vulAankoop(aankoop);
                
                //Hier wordt de PDF opgesteld. 
                pdf.bouwPDF();
                
                //Mailfunctie! Hier moet alleen nog de PDf aan toegevoegd worden.
                //TODO PDF toevoegen aan de mail.
                String van = "dryveseu@gmail.com";
                String naar = lid.getEmail();
                String onderwerp = "Dryves factuurnummer " + aankoop.getFactuurnr();
                String bericht = "Ritnummer: " + rit.getRitnr() +
                        "\n" + "Factuurnummer: " + aankoop.getFactuurnr() +
                        "\n" +
                        "\n" + "Van: " + rit.getStartpunt() +
                        "\n" + "Naar: " + rit.getEindpunt() +
                        "\n" + "U heeft deze rit gekocht via: " +aankoop.getBetaalwijze() +
                        "\n" + "TEST - Lidnummer van de aanbieder: " + rit.getLidnr();
 
                verstuurEmail ve = new verstuurEmail();
            
                ve.verstuurEmail(van, naar, onderwerp, bericht);

		response.sendRedirect("MijnDryves");
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
