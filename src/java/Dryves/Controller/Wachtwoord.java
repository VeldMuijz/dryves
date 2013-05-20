/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Lid;
import Dryves.Model.LidDao;
import Dryves.Model.verstuurEmail;
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
public class Wachtwoord extends HttpServlet {

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

		//De objecten worden aangemmakt
		Lid user = new Lid();
		String email = request.getParameter("email");
                user.setEmail(email);
		LidDao lidDao = new LidDao();
                verstuurEmail ve = new verstuurEmail();
		
		//check of de email bestaat, zo niet dan wordt de gebruiker toegevoegd in de database
		if (!lidDao.checkDuplicate(email)) {
                    
                    System.out.println("Dit is het email adres, wat niet is gevonden in de database: " + email);
                    
                    ve.verstuurEmailZonderBijlage(email, "E-mail adres niet gevonden", "Uw e-mail adres is niet gevonden.");
                    
                    response.sendRedirect("login.jsp");
                    
                }else{
                    
                    System.out.println("Dit is het email adres, waar het wachtwoord naartoe wordt verzonden: " + email);
                    
                    lidDao.verstuurWachtwoord(user);
                    
                    String naar = email;
                    String onderwerp = "Wachtwoord vergeten";
                    String bericht = "Hoi " + user.getVnaam() + " " + user.getAnaam() +
                            "\n" + "Dit is uw wachtwoord: " + user.getWachtwoord() +
                            "\n" + "Veel plezier op Dryves.eu." ;
                    
                    
                    ve.verstuurEmailZonderBijlage(naar, onderwerp, bericht);
                    
                    response.sendRedirect("login.jsp");
                
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
