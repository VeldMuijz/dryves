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

    

public class userdao     
   
   {
      static Connection currentCon = null;
      static ResultSet rs = null;  



      public static UserBean login(UserBean bean) {

         //preparing some objects for connection 
         Statement stmt = null;    

         String username = bean.getUsername();    
         String password = bean.getPassword();   

         String searchQuery = "select lid.vnaam, lid.anaam, lid.wachtwoord from Lid as lid where lid.email='" + username +"'";
             

      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("Your user name is " + username);  
      System.out.println("Your password is " + password);
      System.out.println("Query: "+searchQuery);

      try 
      {
         //connect to DB 
         currentCon = ConnectionManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery);           
         boolean more = rs.next();

         // if user does not exist set the isValid variable to false
         if (!more) 
         {
            System.out.println("Sorry, you are not a registered user! Please sign up first");
            bean.setValid(false);
         } 

         //if user exists set the isValid variable to true
         else if (more) 
         { 
             
            String passwordUser = rs.getString(3);
            
            System.out.println("Dit is het ww uit de DB: " + passwordUser);
            
            System.out.println("Dit is het ww van de JSP: " + password);
            
           if (password.equals(passwordUser)) {
            
            
            
            String firstName = rs.getString(1);
            String lastName = rs.getString(2);

            System.out.println("Welkom! " + firstName);
            bean.setFirstName(firstName);
            bean.setLastName(lastName);
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

