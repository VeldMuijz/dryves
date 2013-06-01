/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import Dryves.Controller.PagerServlet;
import Dryves.Model.Berichten;
import Dryves.Model.BerichtenDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author H
 */
public class BerichtenPager extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BerichtenPager</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BerichtenPager at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                 // Instantieren van objecten
         BerichtenDao berichtendao = new BerichtenDao();
         Pager pager = new Pager();
        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Dryves.Model.Lid user = (Dryves.Model.Lid) session.getAttribute("currentSessionUser");
        
        int offset = Integer.parseInt(request.getParameter("offset"));
        String knop = request.getParameter("knop");
       
             
        

        if ("volgende".equals(knop)) {
           pager.setOffset(offset+5); 
             }
           
           
        
        else if("vorige".equals(knop)){            
            pager.setOffset(offset-5 );
        
        }
        
        
        
         try {
            List<Berichten> bericht;
            int userid = user.getLidnr();
            ArrayList<Lid> afzender = new ArrayList<Lid>();
            bericht = berichtendao.haalberichten(userid, pager.getOffset());
           
            request.setAttribute("berichten", bericht);
            request.setAttribute("pager", pager);
            pager.setAantalberichten(berichtendao.aantalBerichten(userid));
            int maximalePositie= pager.getAantalberichten() -5;
            pager.setMaxPositie(maximalePositie);
            pager.setStatusTotaalPager((int) Math.ceil(berichtendao.aantalBerichten(userid) / 5.0));
            pager.setStatusHuidigePage((int) Math.ceil((pager.getOffset()+5) / 5.0));
            
            for (int i = 0; i < bericht.size(); i++) {
                Berichten berichtobject;
                berichtobject = bericht.get(i);
                int afzenderint;
                System.out.println("berichtobject Afzender = " + berichtobject.getAfzender());

                afzenderint = berichtobject.getAfzender();

                //afzender.add(berichtendao.afzender(afzenderint));
                System.out.println("dit is i =" + i);
            }
            request.setAttribute("afzender", afzender);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mijnberichten.jsp");
            dispatcher.forward(request, response);
             }   catch (SQLException ex) {
            Logger.getLogger(PagerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>
}
