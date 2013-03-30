/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RickSpijker
 */
public class Login extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        HttpSession session = request.getSession();
        String gebruiker = request.getParameter("email");
        String wachtwoord = request.getParameter("wachtwoord");
        session.setAttribute("error", null);
        String query = "SELECT id FROM resource WHERE username = ? AND password = ?" ;
        String resultPage = "";
       
        
        System.out.println(gebruiker + " " + wachtwoord);
        
    }
    
}
