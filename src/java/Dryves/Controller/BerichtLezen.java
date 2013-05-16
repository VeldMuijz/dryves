/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves.Controller;

import Dryves.Model.Berichten;
import Dryves.Model.BerichtenDao;
import Dryves.Model.Lid;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class BerichtLezen extends HttpServlet {

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
            out.println("<title>Servlet BerichtLezen</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BerichtLezen at " + request.getContextPath() + "</h1>");
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
        
        
        
        //processRequest(request, response);
        // Instantieren van objecten
        Berichten berichten = new Berichten();
        BerichtenDao berichtendao = new BerichtenDao();

        // Haal de huidige sessie op
        HttpSession session = request.getSession();
        // Maak in de sessie een object rit aan met naam sessieRit
        
       // session.setAttribute("sessieRit", berichten);
        //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Lid user = (Lid) session.getAttribute("currentSessionUser");
        
        try {
            List<Berichten> bericht;
            int berichtid=Integer.parseInt(request.getParameter("berichtid"));
           
           bericht = berichtendao.haalHetGeheleBericht2(berichtid);
           request.setAttribute("gehelebericht", bericht );

            RequestDispatcher dispatcher = request.getRequestDispatcher("/helebericht.jsp");
            dispatcher.forward(request, response);
                       
       
            
            
            
            
            
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain products from DB", e);
     
        }

        
        
        
        
        
        


/*


        Berichten bericht = new Berichten();
        BerichtenDao berichtDao = new BerichtenDao();
        
    
		try {
			List<Berichten> berichtlijst;
			int berichtid=Integer.parseInt(request.getParameter("berichtid"));
                        
			berichtlijst = berichtDao.haalHetGeheleBericht2(berichtid);
			request.setAttribute("gehelebericht", berichtlijst);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/helebericht.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			throw new ServletException("Cannot obtain products from DB", e);
		}

        
        
/* Haal de huidige sessie op
        HttpSession session = request.getSession();
// Maak in de sessie een object rit aan met naam sessieRit
        session.setAttribute("berichten", bericht);
        
        int berichtid=Integer.parseInt(request.getParameter("berichtid"));
        
        
//Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Lid user = (Lid) session.getAttribute("currentSessionUser");
        try {
            List<Berichten> berichtenlijst;
           berichtenlijst= berichtDao.haalHetGeheleBericht2(berichtid);
        
            
            request.setAttribute("gehelebericht", berichtenlijst);

            RequestDispatcher dispatcher = request.getRequestDispatcher("helebericht.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Cannot obtain products from DB", e);
        }



*/





        /* processRequest(request, response);
        
         //processRequest(request, response);
         // Instantieren van objecten
         Berichten berichten = new Berichten();
         BerichtenDao berichtendao = new BerichtenDao();

         // Haal de huidige sessie op
         //  HttpSession session = request.getSession();
         // Maak in de sessie een object rit aan met naam sessieRit
        
         //session.setAttribute("sessieRit", berichten);
         //Haal de userbean (dit moet sessiebean worden) op uit de sessie
         //Lid user = (Lid) session.getAttribute("currentSessionUser");
        
         try {
         List<Berichten> bericht;
         int berichtid=Integer.parseInt(request.getParameter("berichtid"));
           
         bericht = berichtendao.haalHetGeheleBericht(berichtid);
         request.setAttribute("gehelebericht", bericht );

         RequestDispatcher dispatcher = request.getRequestDispatcher("/helebericht.jsp");
         dispatcher.forward(request, response);
                       
       
            
            
            
            
            
         } catch (Exception e) {
         throw new ServletException("Cannot obtain products from DB", e);
     
         }

        
        
        
         /*
        
         BerichtenDao berichtdao=new BerichtenDao(); 
         Berichten berichtclas=new Berichten();
         int berichtid=Integer.parseInt(request.getParameter("berichtid"));        
         List<Berichten> lijstbericht=new ArrayList<Berichten>();
        
        
         try {
                      
         lijstbericht.add( berichtclas=berichtdao.haalHetGeheleBericht(berichtid));
         request.setAttribute("gehelebericht", lijstbericht);
         System.out.println(lijstbericht.toString());
         RequestDispatcher dispatcher = request.getRequestDispatcher("helebericht.jsp");
         dispatcher.forward(request, response);
         } catch (Exception e) {
         System.out.println(e);
         throw new ServletException(e);
                        
         }
        
        
         */


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
