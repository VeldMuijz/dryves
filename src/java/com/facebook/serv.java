/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author H
 */
public class serv extends HttpServlet {

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
            out.println("<title>Servlet serv</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet serv at " + request.getContextPath() + "</h1>");
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
        
        
        
        Datv v= new Datv();
        response.setContentType("text/html");
        PrintWriter out =response.getWriter();
        String id= request.getParameter("id");
        
        if(v.lees(id)){
        
        //Hier heb ik die sessie nodig waar Jeroen mee bezig was
            //
        
        // dit is voor later response.sendRedirect("mijndryves.jsp");
        
         out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Facebooklogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Facebooklogin " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        
        }else{
        
     
       String voornaam= request.getParameter("voornaam");
        String achternaam= request.getParameter("achternaam");
         String sex= request.getParameter("sex");
       v.gebruikertoevoegen(id, voornaam, achternaam, sex);
          
       
       
       response.sendRedirect("mijndryves.jsp");
        
        
        }
        
        
        
        
        
        
        
        
        
        processRequest(request, response);
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
