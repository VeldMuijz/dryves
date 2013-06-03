/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Lid;
import Dryves.Model.LidDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author H
 */
public class RegisFBgebruiker extends HttpServlet {

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
            out.println("<title>Servlet RegisFBgebruiker</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisFBgebruiker at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     	

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
                 
                       
            
            
			Lid lid = new Lid();

			//velden worden ingevuld en de facebook gebruiker wordt toegervoegd in de database
                        try{
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
                        
                        //Zet facxebookid 
			lid.setFacebookid(request.getParameter("facebookid"));
			//Schrijf het facebookid naar de console
			System.out.println("Dit is het facebookid: " + lid.getFacebookid());

			//Zet het email adres
			lid.setEmail(request.getParameter("email"));
			//Schrijf het email adres naar de console
			System.out.println("Dit is het email adres: " + lid.getEmail());

			//Zet het wachtwoord
			lid.setWachtwoord(lid.getFacebookid());
			//Schrijf het wachtwoord naar de console
			System.out.println("Facebook gebruikers hebben geen wachtwoord nodig, wachtwoord is facebookid als placeholder: " + lid.getFacebookid());

			//Zet de fotourl
			lid.setFotoUrl(request.getParameter("fotoUrl"));
			//Schrijf de fotourl naar de console
			System.out.println("Dit is de fotourl: " + lid.getFotoUrl());

			//Zet de langnotify
			lid.setLangnotify(request.getParameter("locale"));
			//Schrijf langnotify naar de console
			System.out.println("Dit is de langnotify: " + lid.getLangnotify());
                        
                        

			LidDao lidDao = new LidDao();

			                        
                	lidDao.addFacebookLid(lid);
                            
			// en hier wordt de gebruiker door gelinked naar mijndryves
                        HttpSession session = request.getSession(true);
			
                        Lid lid2 = new Lid();

			//lid2 = lidDao.loginFacebook(lid2.getFacebookid());


                        
                        //Hier maken we een neiuwe sessie voor de gebruiker
			
//			Lid lid2 = new Lid();

			lid2 = lidDao.loginFacebook(lid.getFacebookid());

			//Hier maken we een neiuwe sessie voor de gebruiker
			session = request.getSession(true);
			session.setAttribute("currentSessionUser", lid2);
			lidDao.adminLogin(lid2);
			request.getRequestDispatcher("WEB-INF/mijndryves.jsp").forward(request, response);

                        
                        }   catch(Exception e){
                        System.out.print("Foutmelding tijdens het registreen van een facebook user in de regisFBgebruiker servlet:  ");
                           request.getRequestDispatcher("oops.jsp").forward(request, response);                     
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
