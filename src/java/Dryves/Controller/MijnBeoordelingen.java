/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Beoordeling;
import Dryves.Model.BeoordelingDao;
import Dryves.Model.Lid;
import Dryves.Model.LidDao;
import Dryves.Pager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class MijnBeoordelingen extends HttpServlet {

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
		Beoordeling beoordeling = new Beoordeling();
		BeoordelingDao beoordelingDao = new BeoordelingDao();
		LidDao lidDao = new LidDao();
		Lid beoordelaar = new Lid();
                Pager pager= new Pager();
		// Haal de huidige sessie op
		HttpSession session = request.getSession();
                
                
                
                Lid user = (Lid) session.getAttribute("currentSessionUser");
		
		user = lidDao.enkelLidOphalen(user.getLidnr(), user);
		session.setAttribute("currentSessionUser", user);
		pager.setOffset(0);
		
                int offset = 0;
        String knop = request.getParameter("knop");
        
        if (request.getParameter("offset") == null) {
            pager.setOffset(0);

        } else {

            offset = Integer.parseInt(request.getParameter("offset"));
        }      
        
        
        if ("volgende".equals(knop)) {
            pager.setOffset(offset + 5);
        } else if ("vorige".equals(knop)) {
            pager.setOffset(offset - 5);
        }
                
                
                ////
                
		// I
		
                
                
                ////
                
		if (session.getAttribute("currentSessionUser") != null) {
			//Haal de userbean (dit moet sessiebean worden) op uit de sessie
	

			user = lidDao.enkelLidOphalen(user.getLidnr(), user);
			if (user != null) {
				session.setAttribute("currentSessionUser", user);

				ArrayList<Lid> beoordelaars = new ArrayList<Lid>();
				List<Beoordeling> beoordelingen;
				
                                
                                
                                ///////
                                
                                
                        
			
			beoordelingen = beoordelingDao.getAlleBeoordelingenPerLid(user.getLidnr(),pager.getOffset());
                        pager.setAantalbeoordelingen(beoordelingDao.aantalBeoordelingen(user.getLidnr()));
                        pager.setMaxPositie(pager.getAantalbeoordelingen()-5);
                        pager.setStatusTotaalPager((int) Math.ceil(beoordelingDao.aantalBeoordelingen(user.getLidnr()) / 5.0));
                        pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset()+5) / 5.0));
			request.setAttribute("beoordelingen", beoordelingen);
			
			for(int i = 0; i < beoordelingen.size(); i++){
				Beoordeling beoordeel;
				beoordeel =	beoordelingen.get(i);
				
				lidDao.enkelLidOphalen(beoordeel.getLidnr(), beoordelaar);
				
				beoordelaars.add(beoordelaar);
				
			}
                        request.setAttribute("pager", pager);
			request.setAttribute("beoordelaars", beoordelaars);


                                
                                ////////
                                

				if (beoordelingen != null) {
					request.setAttribute("beoordelingen", beoordelingen);

					for (int i = 0; i < beoordelingen.size(); i++) {
						Beoordeling beoordeel;
						beoordeel = beoordelingen.get(i);

						lidDao.enkelLidOphalen(beoordeel.getLidnr(), beoordelaar);
						beoordelaars.add(beoordelaar);

					}

					request.setAttribute("beoordelaars", beoordelaars);

					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/mijnbeoordelingen.jsp");
					dispatcher.forward(request, response);
				} else {
					//ophalen van beoordelingen is mislukt
					RequestDispatcher dispatcher = request.getRequestDispatcher("oops.jsp");
					dispatcher.forward(request, response);
				}

			} else {
				//ophalen van lid is mislukt
				RequestDispatcher dispatcher = request.getRequestDispatcher("oops.jsp");
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
