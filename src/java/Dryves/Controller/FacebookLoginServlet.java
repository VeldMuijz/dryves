package Dryves.Controller;

import Dryves.Model.Lid;
import Dryves.Model.LidDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author H
 */
public class FacebookLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        //De objecten worden aangemaakt
        Lid lid = new Lid();
        LidDao lidDao = new LidDao();
        HttpSession session = request.getSession();

        lid.setAnaam(request.getParameter("achternaam"));
        lid.setVnaam(request.getParameter("voornaam"));
        lid.setFacebookid(request.getParameter("id"));
        lid.setEmail(request.getParameter("email"));
        lid.setWachtwoord(request.getParameter("id"));
        String geslacht = request.getParameter("sex");




        lid.setTvoegsel("");
        lid.setStraat("");
        lid.setHuisnummer("");

        lid.setPostcode("");
        lid.setStad("");
        lid.setTelnr("");
        lid.setReknr("");

        
        lid.setFotoUrl("");



        if (geslacht.equals("male") || geslacht.equals("man")) {
            lid.setGeslacht("M");
        } else if (geslacht.equals("female") || geslacht.equals("vrouw")) {
            lid.setGeslacht("V");
        }

        lid.setLocale("nl_NL");
        lid.setLangnotify("nl_NL");

        Lid lid2 = new Lid();

        String facebookid2 = lid.getFacebookid();


        //Met een facebookid gaan we kijken of de huidige gebruiker al eerder een keer heeft aangemeld
        //check of de email bestaat, zo niet dan wordt de gebruiker toegevoegd in de database
        if (!lidDao.checkDuplicateFacebookID(facebookid2)) {


            //Wanneer de email niet voorkomt in de database
            //wordt de gebruiker hier toegevoegd 

            LidDao dao = new LidDao();

            session.setAttribute("facebooklid", lid);
            dao.adminLogin(lid2);
            request.getRequestDispatcher("registreerfacebookuser.jsp").forward(request, response);


        } else {
            //facebook gebruiker komt voor in onze database en wordt  hier ingelogd en krijg dan vervolgens een sessie

            lid2 = lidDao.loginFacebook(facebookid2);


                
                //Hier maken we een neiuwe sessie voor de gebruiker
                session = request.getSession(true);
                session.setAttribute("currentSessionUser", lid2);
                lidDao.adminLogin(lid2);
                request.getRequestDispatcher("WEB-INF/mijndryves.jsp").forward(request, response);


        }
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
