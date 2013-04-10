/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

/**
 *
 * @author RickSpijker
 */
   import java.text.*;
   import java.util.*;
   import java.sql.*;

   /**
 *
 * @author RickSpijker
 */

    

public class SessieDao     
   
   {
      static Connection currentCon = null;
      static ResultSet rs = null;  



      public static Sessie login(Sessie bean) {

         //preparing some objects for connection 
         Statement stmt = null;    

         String email = bean.getEmail();    
         String wachtwoord = bean.getWachtwoord();   

         String zoekQuery = "select lid.vnaam, lid.anaam, lid.wachtwoord, lid.lidnr from Lid as lid where lid.email='" + email +"';";
             

      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("Your user name is " + email);  
      System.out.println("Your wachtwoord is " + wachtwoord);
      System.out.println("Query: " + zoekQuery);

      try 
      {
         //connect to DB 
         currentCon = ConnectionManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(zoekQuery);           
         boolean more = rs.next();

         // if user does not exist set the isValid variable to false
         if (!more) 
         {
            System.out.println("Sorry, je bent niet geregistreerd. Registreer eerst alstublieft.");
            bean.setValid(false);
         } 

         //if user exists set the isValid variable to true
         else if (more) 
         { 
             
            String wachtwoordUser = rs.getString(3);
            
            System.out.println("Dit is het ww uit de DB: " + wachtwoordUser);
            
            System.out.println("Dit is het ww van de JSP: " + wachtwoord);
            
           if (wachtwoord.equals(wachtwoordUser)) {
            
            
            
            String vnaam = rs.getString(1);
            String anaam = rs.getString(2);
			int lidnr = rs.getInt(4);

            System.out.println("Welkom! " + vnaam);
			
			bean.setLidnr(lidnr);
            bean.setVnaam(vnaam);
            bean.setAnaam(anaam);
            bean.setValid(true);
            
           }
           else {
                    System.out.println("Sorry, you are not a registered user! Please sign up first");
                    bean.setValid(false);
           }
         }
      } 

      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      } 

      //some exception handling
      finally 
      {
         if (rs != null)    {
            try {
               rs.close();
            } catch (Exception e) {}
               rs = null;
            }

         if (stmt != null) {
            try {
               stmt.close();
            } catch (Exception e) {}
               stmt = null;
            }

         if (currentCon != null) {
            try {
               currentCon.close();
            } catch (Exception e) {
            }

            currentCon = null;
         }
      }

return bean;

      } 
   }

