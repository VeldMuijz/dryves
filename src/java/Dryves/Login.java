/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

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
public class Login extends HttpServlet {

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

            Lid user = new Lid();

            user.setEmail(request.getParameter("email"));
            user.setWachtwoord(request.getParameter("wachtwoord"));

            user = LidDao.login(user);

            if (user.isValid()) {

                //Hieronder wordt bepaald of het lid admin is of niet.
                                    
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);
                
                LidDao dao = new LidDao();
				dao.adminLogin(user);

                if (user.getRol() == 1) {

                    request.getRequestDispatcher("WEB-INF/mijndryves.jsp").forward(request, response);

                } else if (user.getRol() == 2) {
                    request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);

                }
            } else {

                System.out.println("Het inloggen is niet gelukt, probeer het opnieuw!");
                response.sendRedirect("login.jsp"); //Retry login 
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
