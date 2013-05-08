package Dryves;

import static Dryves.RitDao.currentCon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
        } finally {            
            out.close();
        }
    }

   
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       //De objecten worden aangemmakt
        Lid lid = new Lid();  
        RegistrerenDao registerdao = new RegistrerenDao();
        
        lid.setAnaam(request.getParameter("achternaam"));
        lid.setVnaam(request.getParameter("voornaam"));
        lid.setFacebookid(request.getParameter("id"));
        lid.setEmail(request.getParameter("email"));
        lid.setLocaleStr("Facebook");
     
        String facebookid2=lid.getFacebookid();
      
        //Met een facebookid gaan we kijken of de huidige gebruiker al eerder een keer heeft aangemeld
       
       
        
        
 
        
        
                //check of de email bestaat, zo niet dan wordt de gebruiker toegevoegd in de database
          if (!registerdao.checkDuplicateFacebookID(facebookid2)) { 
         
                           
             //Wanneer de email niet voorkomt in de database
              //wordt de gebruiker hier toegevoegd 
              
         
              try {
			currentCon = ConnectionManager.getConnection();
			PreparedStatement addfacebook;

			String queryString = ("INSERT INTO lid ( vnaam, anaam,geslacht,straat,postcode,stad,telnr,reknr,email, beoordeling,fotourl,tvoegsel,wachtwoord,langnotify,rol,facebookid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			addfacebook = currentCon.prepareStatement(queryString);

			addfacebook.setString(1, lid.getVnaam());
                        addfacebook.setString(2, lid.getAnaam());
                        addfacebook.setString(3, "");
                        addfacebook.setString(4,"");
                        addfacebook.setString(5, "");
                        addfacebook.setString(6, "");
                        addfacebook.setInt(7,0);
                        addfacebook.setInt(8, 0);
                        
			
			addfacebook.setString(9, lid.getEmail());
			addfacebook.setInt(10, 0);
                        addfacebook.setString(11, "");
                        addfacebook.setString(12, "");
                        addfacebook.setString(13, "");
                        addfacebook.setString(14, "nl_NL");
                        addfacebook.setInt(15, 1);
                        addfacebook.setString(16, facebookid2);
			


    
			System.out.println("De query is: " + addfacebook);

			addfacebook.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex);
		}
              
       
        Lid lid2= new Lid();
        SessieDao dao = new SessieDao();
      lid2=  dao.loginFacebook(facebookid2);
           //Hier maken we een neiuwe sessie voor de gebruiker
          HttpSession session = request.getSession(true);       
          session.setAttribute("currentSessionUser",lid2); 
          dao.adminLogin(lid2);
          request.getRequestDispatcher("WEB-INF/mijndryves.jsp").forward(request, response);
          
          
              
        
           
           
           
       } else { 
           
           
           Lid lid2= new Lid();
        SessieDao dao = new SessieDao();
      lid2=  dao.loginFacebook(facebookid2);
	  
           //Hier maken we een neiuwe sessie voor de gebruiker
          HttpSession session = request.getSession(true);       
          session.setAttribute("currentSessionUser",lid2); 
          dao.adminLogin(lid2);
          request.getRequestDispatcher("WEB-INF/mijndryves.jsp").forward(request, response);
       
       }
        

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
