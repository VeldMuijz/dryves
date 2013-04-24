package Dryves;

import java.io.IOException;
import java.io.PrintWriter;
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
        String email = request.getParameter("email");
        RegistrerenDao registerdao = new RegistrerenDao();
        
        
        
                //check of de email bestaat, zo niet dan wordt de gebruiker toegevoegd in de database
          if (!registerdao.checkDuplicate(email)) { 
           
              //email komt niet in de database voor
              
        //Zet de voornaam
        lid.setVnaam(request.getParameter("voornaam"));
        //Print voornaam naar console
        System.out.println("Dit is de voornaam: " + lid.getVnaam());
        
        //Zet de achternaam
        lid.setAnaam(request.getParameter("achternaam"));
        //Print achternaam naar console
        System.out.println("Dit is de achternaam: " + lid.getAnaam());
        
        //Zet tvoegsel
        lid.setTvoegsel("facebook");
        //Print tvoegsel naar de console
        System.out.println("Dit is de tussenvoegsel: " + lid.getTvoegsel());
        
        //Zet het geslacht
        lid.setGeslacht(request.getParameter("sex"));
        //Print geslacht naar de console
        System.out.println("Dit is het geslacht: " + lid.getGeslacht());
        
        //Zet de straat
        lid.setStraat("facebook");
        //Print de straat naar de console
        System.out.println("Dit is de straat: " + lid.getStraat());
        
        //Zet het huisnummer
        lid.setHuisnummer("facebook");
        //Print het huisnummer naar de console
        System.out.println("Dit is het huisnummer: " + lid.getHuisnummer());
        
        //Zet de postcode
        lid.setPostcode("facebook");
        //Print de postcode naar de console
        System.out.println("Dit is de postcode: " + lid.getPostcode());
        
        //Zet stad
        lid.setStad("facebook");
        //Print stad naar de console
        System.out.println("Dit is de stad: " + lid.getStad());
        
        //Zet het telefoonummer
        lid.setTelnr("facebook");
        //Schrijf telefoonnummer naar de console
        System.out.println("Dit is het telefoonnummer: " + lid.getTelnr());
        
        //Zet het rekeningnummer 
        lid.setReknr("11111");
        //Schrijf het rekeningnummer naar de console
        System.out.println("Dit is het rekeningnummer: " + lid.getReknr());
        
        //Zet het email adres
        lid.setEmail(request.getParameter("email"));
        //Schrijf het email adres naar de console
        System.out.println("Dit is het email adres: " + lid.getEmail());
        
        //Zet het wachtwoord
        lid.setWachtwoord("facebook");
        //Schrijf het wachtwoord naar de console
        System.out.println("Dit is het wachtwoord: " + lid.getWachtwoord());
        
        //Zet de fotourl
        lid.setFotoUrl("facebook");
        //Schrijf de fotourl naar de console
        System.out.println("Dit is de fotourl: " + lid.getFotoUrl());
        
        //Zet de langnotify
        lid.setLangnotify("facebook");
        //Schrijf langnotify naar de console
        System.out.println("Dit is de langnotify: " + lid.getLangnotify());

           RegistrerenDao newregistratie = new RegistrerenDao();
       
           // hier wordt de gebruiker in de database opgeslagen
           newregistratie.RegistrerenDao(lid);
           
           //Hier maken we een neiuwe sessie voor de gebruiker
          HttpSession session = request.getSession(true);       
          session.setAttribute("currentSessionUser",lid); 
          
          // en hier wordt de gebruiker door gelinked naar mijndryves
          response.sendRedirect("mijndryves.jsp"); //logged-in page  

           
       } else { 

           //Indien het email bestaat wordt er een melding weergegeven.
           // Moet nog gedaan worden  
              
           //Hier maken we een neiuwe sessie voor de gebruiker
          HttpSession session = request.getSession(true);       
          session.setAttribute("currentSessionUser",lid); 
          
          // en hier wordt de gebruiker door gelinked naar mijndryves
          response.sendRedirect("mijndryves.jsp"); //logged-in page 

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
