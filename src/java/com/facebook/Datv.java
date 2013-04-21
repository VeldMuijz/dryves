
package com.facebook;

import static com.facebook.Datv.driverName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Datv {
 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/facebook";


   static final String USER = "root";
   static final String PASS = "";
     static String driverName = "com.mysql.jdbc.Driver";
 static String url = "jdbc:mysql://localhost:3306/";
    static String dbName = "facebook";
 static String userName = "root";
 static String password = "";
    public boolean lees(String idx){
    
   
        Connection con=null;
        Statement stmt=null;
        boolean chk=false;
        try{
                Class.forName(driverName).newInstance();
                con = DriverManager.getConnection(url+dbName, userName, password);
                try{
                  stmt = con.createStatement();
                  String query = "SELECT * FROM facebook where id="+ idx;
                  ResultSet rs=stmt.executeQuery(query);
                 
                
                 if(rs.next()){
                     
                     chk=true;

                        }else{chk=false;}
             
                   rs.close();
                  } catch(SQLException s){                                              
                                s.printStackTrace();
                         }
               
                con.close();
                stmt.close();
                }catch (Exception e){
                        e.printStackTrace();
                 }
        return chk;
  }

    
    
    
    
    
    
 
  
   
   public void gebruikertoevoegen(String id, String voornaam, String achternaam, String sex) {
   Connection conn = null;
   Statement stmt = null;
   try{
  
      Class.forName("com.mysql.jdbc.Driver");
   
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
   
      System.out.println("Inserting records into the table...");
      stmt = conn.createStatement();
      
     
      
      String  sql = "INSERT INTO facebook " +
                   "VALUES (" +id+", '"+voornaam +achternaam+ sex+"')";
      stmt.executeUpdate(sql);
     
    
 
      System.out.println("Inserted records into the table...");

   }catch(SQLException se){
      
      se.printStackTrace();
   }catch(Exception e){
  
      e.printStackTrace();
   }finally{
     
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   
}
}
    
    
    
    
    
    
    
    
    
    
    

