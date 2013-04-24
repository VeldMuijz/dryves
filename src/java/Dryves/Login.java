/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

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

try
{       

     
     
     Lid lid= new Lid();
     
     lid.setEmail(request.getParameter("email"));
     lid.setWachtwoord(request.getParameter("wachtwoord"));

     lid = SessieDao.login(lid);

     if (lid.isValid())
     {

          HttpSession session = request.getSession(true);       
          session.setAttribute("currentSessionUser", lid); 
		  //TODO: Hier gebruik maken van een login servlet om door te sturen naar WEB-INF/login.jsp
          response.sendRedirect("mijndryves.jsp"); //logged-in page             
     }

     else 
          response.sendRedirect("login.jsp"); //Retry login 
} 


catch (Throwable theException)      
{
     System.out.println(theException); 
}
       }
    }

    

