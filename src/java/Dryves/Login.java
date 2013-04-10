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

     Sessie user = new Sessie();
     user.setEmail(request.getParameter("email"));
     user.setWachtwoord(request.getParameter("wachtwoord"));

     user = SessieDao.login(user);

     if (user.isValid())
     {

          HttpSession session = request.getSession(true);       
          session.setAttribute("currentSessionUser",user); 
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

    

