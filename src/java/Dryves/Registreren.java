/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




    /**
 *
 * @author heuvenk
 */
public class Registreren extends HttpServlet {

    
     private int lidnr;
     private String vnaam;
     private String anaam;
     private String geslacht;
     private String straat;
     private String huisnummer;
     private String postcode;
     private String stad;
     private String telnr;
     private String reknr;
     private String email;
     private String wachtwoord;
     private String fotoUrl;
     private String tvoegsel;
     private String langnotify;
     private Hashtable errors;

    public Registreren() {
    }

	

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registreren</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registreren at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
  
    public String getErrorMsg(String s) {
        String errorMsg = (String) errors.get(s.trim());
        return (errorMsg == null) ? "" : errorMsg;
    }
         
    public String isRbSelected(String s) {
        return (geslacht.equals(s)) ? "checked" : "";   
    }
    public int getLidnr() {
        return this.lidnr;
    }
    
    public void setLidnr(int lidnr) {
        this.lidnr = lidnr;
    }
    public String getVnaam() {
        return this.vnaam;
    }
    
    public void setVnaam(String vnaam) {
        this.vnaam = vnaam;
    }
    public String getAnaam() {
        return this.anaam;
    }
    
    public void setAnaam(String anaam) {
        this.anaam = anaam;
    }
    public String getGeslacht() {
        return this.geslacht;
    }
    
    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }
    public String getStraat() {
        return this.straat;
    }
    
    public void setStraat(String adres) {
        this.straat = adres;
    }
    public String getHuisnummer() {
        return this.huisnummer;
            }
    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }
    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getStad() {
        return this.stad;
    }
    
    public void setStad(String stad) {
        this.stad = stad;
    }
    public String getTelnr() {
        return this.telnr;
    }
    
    public void setTelnr(String telnr) {
        this.telnr = telnr;
    }
    public String getReknr() {
        return this.reknr;
    }
    
    public void setReknr(String reknr) {
        this.reknr = reknr;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
    public String getFotoUrl() {
        return this.fotoUrl;
    }
    
    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
    public String getTvoegsel() {
        return this.tvoegsel;
    }
    
    public void setTvoegsel(String tvoegsel) {
        this.tvoegsel = tvoegsel;
    }

    public String getLangnotify() {
        return langnotify;
    }

    public void setLangnotify(String langnotify) {
        this.langnotify = langnotify;

    }
    
    public void setErrors(String key, String msg) {
        errors.put(key, msg);
        

}

    private static class request {

        public request() {
        }
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
        processRequest(request, response);
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
