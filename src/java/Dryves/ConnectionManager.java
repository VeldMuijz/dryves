/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

/**
 *
 * @author RickSpijker
 */
import java.sql.*;
   import java.util.*;


   public class ConnectionManager {

      static Connection con;
      static String url;

      public static Connection getConnection()
      {

         try
         {

            Class.forName("org.postgresql.Driver");
            System.out.println(" Defing the URL");

             String url= "jdbc:postgresql://localhost:1234/pdl6";
            // assuming "DataSource" is your DataSource name

           // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            try
            {   


                System.out.println(" Defing the username");
                String username= "pdl6"; 
                System.out.println(" Defing the password");
                String password= "h2n431ht";
                System.out.println(" Defing the connection");
                con = DriverManager.getConnection(url,username,password); 
                System.out.println(" connection done" +con);





            }

            catch (SQLException ex)
            {
               ex.printStackTrace();
            }
         }

         catch(ClassNotFoundException e)
         {
            System.out.println(e);
         }

      return con;
}
   }
