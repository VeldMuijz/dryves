/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dryves.Controller;

import Dryves.Model.Lid;
import Dryves.Model.LidDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author heuvenk
 */
public class LidWijzigen extends HttpServlet {
       /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         // Instantieren van objecten
        Lid lid = new Lid();
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Lid user = (Lid) session.getAttribute("currentSessionUser");
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Lid lid = (Lid) session.getAttribute("currentSessionUser");

                //check of de email bestaat, zo niet dan wordt de gebruiker toegevoegd in de database

           
              //email komt niet in de database voor
              
              
        //Zet de voornaam
        lid.setVnaam(request.getParameter("vnaam"));
        //Print voornaam naar console
        System.out.println("Dit is de voornaam: " + lid.getVnaam());
        
        //Zet de achternaam
        lid.setAnaam(request.getParameter("anaam"));
        //Print achternaam naar console
        System.out.println("Dit is de achternaam: " + lid.getAnaam());
        
        //Zet tvoegsel
        lid.setTvoegsel(request.getParameter("tvoegsel"));
        //Print tvoegsel naar de console
        System.out.println("Dit is de tussenvoegsel: " + lid.getTvoegsel());
        
        //Zet het geslacht
        lid.setGeslacht(request.getParameter("geslacht"));
        //Print geslacht naar de console
        System.out.println("Dit is het geslacht: " + lid.getGeslacht());
        
        //Zet de straat
        lid.setStraat(request.getParameter("straat"));
        //Print de straat naar de console
        System.out.println("Dit is de straat: " + lid.getStraat());
        
//        //Zet het huisnummer
//        lid.setHuisnummer(request.getParameter("huisnummer"));
//        //Print het huisnummer naar de console
//        System.out.println("Dit is het huisnummer: " + lid.getHuisnummer());
//        
//        lid.setStraat(request.getParameter("straat") + " " + request.getParameter("huisnummer"));
        //Zet de postcode
        lid.setPostcode(request.getParameter("postcode"));
        //Print de postcode naar de console
        System.out.println("Dit is de postcode: " + lid.getPostcode());
        
        //Zet stad
        lid.setStad(request.getParameter("stad"));
        //Print stad naar de console
        System.out.println("Dit is de stad: " + lid.getStad());
        
        //Zet het telefoonummer
        lid.setTelnr(request.getParameter("telnr"));
        //Schrijf telefoonnummer naar de console
        System.out.println("Dit is het telefoonnummer: " + lid.getTelnr());
        
        //Zet het rekeningnummer 
        lid.setReknr(request.getParameter("reknr"));
        //Schrijf het rekeningnummer naar de console
        System.out.println("Dit is het rekeningnummer: " + lid.getReknr());
        
        //Zet het email adres
        lid.setEmail(request.getParameter("email"));
        //Schrijf het email adres naar de console
        System.out.println("Dit is het email adres: " + lid.getEmail());
        
        //Zet het wachtwoord
        lid.setWachtwoord(request.getParameter("wachtwoord"));
        //Schrijf het wachtwoord naar de console
        System.out.println("Dit is het wachtwoord: " + lid.getWachtwoord());
        
        //Zet de fotourl
        lid.setFotoUrl(request.getParameter("fotoUrl"));
        //Schrijf de fotourl naar de console
        System.out.println("Dit is de fotourl: " + lid.getFotoUrl());
        
        //Zet de langnotify
        lid.setLangnotify(request.getParameter("locale"));
        //Schrijf langnotify naar de console
        System.out.println("Dit is de langnotify: " + lid.getLangnotify());
        
        
          LidDao lidDao = new LidDao();
       
           // hier wordt de gebruiker in de database opgeslagen
           lidDao.enkelLidUpdaten(lid);
          
          // en hier wordt de gebruiker door gelinked naar mijndryves
          response.sendRedirect("MijnDryves"); //logged-in page  
           
           
 
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}