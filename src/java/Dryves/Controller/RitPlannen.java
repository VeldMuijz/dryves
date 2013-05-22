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
//        // Instantieren van objecten
//        Rit rit = new Rit();
//        RitDao ritDao = new RitDao();
//
//        // Haal de huidige sessie op
//        HttpSession session = request.getSession();
//        // Maak in de sessie een object rit aan met naam sessieRit
//        session.setAttribute("sessieRit", rit);
//        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
//        Lid user = (Lid) session.getAttribute("currentSessionUser");
//
//        System.out.println("**************** \n dit is rinr:   " + request.getParameter("ritnr"));
//
//        rit.setRitnr(Integer.parseInt(request.getParameter("ritnr")));
//        System.out.println("Dit is het ritnummer± " + rit.getRitnr());
//        ritDao.enkeleRitOphalen(rit.getRitnr(), rit);
//        System.out.println("dit is het eindpunt" + rit.getEindpunt());

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/rit_plannen.jsp");
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

		// Instantieren van objecten
		Rit rit = new Rit();
		RitDao ritDao = new RitDao();
		DatumConverter dc = new DatumConverter();
		// Haal de huidige sessie op
		HttpSession session = request.getSession();
		// Maak in de sessie een object rit aan met naam sessieRit
		session.setAttribute("sessieRit", rit);
		//Haal de userbean (dit moet sessiebean worden) op uit de sessie
		Lid user = (Lid) session.getAttribute("currentSessionUser");


		//Haal alle gegevens op en zet ze in Rit
		//Bouw ingevoerde datum om naar een timestamp
//        Date datum;
//        Date einddatum;
//        stringDatum = request.getParameter("begindatum");
//		System.out.println("Dit is begindatum met het keuze menu'tje: "+ stringDatum);
//        stringTijd = request.getParameter("tijd");
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm");
//        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//
//        try {
//            datum = dateFormat.parse(stringDatum + 'T' + stringTijd);
//            timestamp = timestampFormat.format(datum);
//
//            ritDao.setBegindatum(DateTime.parse(timestamp));
//
//        } catch (ParseException ex) {
//            Logger.getLogger(RitPlannen.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("************ Programma snapt Timestamp niet!");
//        }
		System.out.println("request.getParameter(\"begindatum\") = " + request.getParameter("begindatum"));

		ritDao.setBegindatum(dc.convertUSTimestamp(request.getParameter("begindatum"), request.getParameter("tijd")));

		String ma = request.getParameter("ma");
		String di = request.getParameter("di");
		String wo = request.getParameter("wo");
		String don = request.getParameter("don");
		String vr = request.getParameter("vr");
		String za = request.getParameter("za");
		String zo = request.getParameter("zo");
		

		//Checken of herhaling aangevinkt is, zo ja vul de dagen van de week
		if (request.getParameter("herhaling") != null && !request.getParameter("einddatum").isEmpty()) {
			ritDao.setEinddatum(dc.convertTimestamp(request.getParameter("einddatum"), "23:59"));
			if (ma!= null && !ma.isEmpty()) {
				ritDao.setMa(1);
				System.out.println("ma:" + ritDao.getMa());
				ritDao.setMeerdere(1);
			}
			if (di != null && !di.isEmpty()) {
				ritDao.setDi(2);
				System.out.println("di:" + ritDao.getDi());
				ritDao.setMeerdere(1);
			}
			if (wo != null && !wo.isEmpty()) {
				ritDao.setWo(3);
				System.out.println("wo:" + ritDao.getWo());
				ritDao.setMeerdere(1);
			}
			if (don != null && !don.isEmpty()) {
				ritDao.setDon(4);
				System.out.println("don:" + ritDao.getDon());
				ritDao.setMeerdere(1);
			}
			if (vr != null && !vr.isEmpty()) {
				ritDao.setVr(5);
				System.out.println("vr:" + ritDao.getVr());
				ritDao.setMeerdere(1);
			}
			if (za != null && !za.isEmpty()) {
				ritDao.setZa(6);
				System.out.println("za:" + ritDao.getZa());
				ritDao.setMeerdere(1);
			}
			if (zo != null && !zo.isEmpty()) {
				ritDao.setZo(7);
				System.out.println("zo:" + ritDao.getZo());
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

		String formatprijs = String.format("%.2f", prijs);
		System.out.println(formatprijs);
		formatprijs = formatprijs.replace(",", ".");
		System.out.println(formatprijs);
		rit.setPrijs(Double.parseDouble(formatprijs));
		rit.setGekocht(0);
		rit.setZitplaatsen(Integer.parseInt(request.getParameter("aantalZitplaatsen")));

		//check of rit direct aangeboden mag worden
		if (request.getParameter("aanbieden") != null) {
			rit.setAangeboden(1);
		} else {
			rit.setAangeboden(0);
		}
		rit.setBrandstof(request.getParameter("soortBrandstof"));

		String referer = request.getHeader("Referer");
		System.out.println("Dit is de referer: " + referer);
		if (referer.contains("ritwijzigen.jsp")) {
			System.out.println("ritwijzigen.jsp gevonden.");
		}

		ritDao.vulRitDao(rit);
		if (referer.contains("RitWijzigen")) {

			if (ritDao.updateRit(Integer.parseInt(request.getParameter("ritnr")))) {
				response.sendRedirect("MijnRitten");

			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/oops.jsp");
				dispatcher.forward(request, response);
			}

		} else {
			if (ritDao.getMeerdere() == 0) {
				if (ritDao.saveRit()) {
					response.sendRedirect("MijnRitten");
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/oops.jsp");
					dispatcher.forward(request, response);
				}

			} else {
				//ritDao.saveMeerdereRitten();
				if (ritDao.saveMeerdereRitten()) {
					response.sendRedirect("MijnRitten");
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/oops.jsp");
					dispatcher.forward(request, response);
				}
			}

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
