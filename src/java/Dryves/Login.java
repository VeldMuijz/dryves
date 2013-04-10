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

     UserBean user = new UserBean();
     user.setUserName(request.getParameter("email"));
     user.setPassword(request.getParameter("wachtwoord"));

     user = userdao.login(user);

     if (user.isValid())
     {

          HttpSession session = request.getSession(true);       
          session.setAttribute("currentSessionUser",user); 
          response.sendRedirect("mijndryves.jsp"); //logged-in page             
     }

     else 
          user.valid=true;
          response.sendRedirect("login.jsp"); //error page 
} 


catch (Throwable theException)      
{
     System.out.println(theException); 
}
       }
    }

    

