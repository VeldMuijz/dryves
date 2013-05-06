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
public class SessieDao {

    static ResultSet rs;

    private static Locale toString(String locale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Lid login(Lid bean2) {



        //preparing some objects for connection 
        Statement stmt = null;

        String email = bean2.getEmail();
        String wachtwoord = bean2.getWachtwoord();

        ResultSet rs;
        Connection con = Dryves.ConnectionManager.getConnection();
        Lid bean = new Lid();


        // "System.out.println" prints in the console; Normally used to trace the process
        System.out.println("Your user name is " + email);
        System.out.println("Your wachtwoord is " + wachtwoord);
        // System.out.println("Query: " + zoekQuery);

        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT lidnr ,vnaam,anaam,geslacht,straat,postcode, stad,telnr,reknr,email,beoordeling,fotourl,tvoegsel,wachtwoord,langnotify,rol FROM lid WHERE email = ?");
            pstmt.setString(1, email);
            //connect to DB 
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();




            rs = pstmt.executeQuery();

            //rs = stmt.executeQuery(zoekQuery);           
            boolean more = rs.next();

            // if user does not exist set the isValid variable to false
            if (!more) {
                System.out.println("Sorry, je bent niet geregistreerd. Registreer eerst alstublieft.");
                bean.setValid(false);
            } //if user exists set the isValid variable to true
            else if (more) {
                //Hieronder wordt het wachtwoord van de user opgehaald uit de database 
                String wachtwoordUser = rs.getString(14);

                //Hieronder worden de wachtwoorden geprint in de console
                System.out.println("Dit is het ww uit de DB: " + wachtwoordUser);

                System.out.println("Dit is het ww van de JSP: " + wachtwoord);

                //Hieronder wordt de locale opgehaald uit de database
                // String locale = rs.getString(5);

                //Hieronder wordt de locale naar de console geprint
                //System.out.println("Dit is de locale van het lid: " + locale);

                if (wachtwoord.equals(wachtwoordUser)) {

                    bean.setLidnr(rs.getInt(1));
                    bean.setVnaam(rs.getString(2).toString());
                    bean.setAnaam(rs.getString(3));
                    bean.setGeslacht(rs.getString(4));
                    bean.setStraat(rs.getString(5));
                    bean.setPostcode(rs.getString(6));
                    bean.setStad(rs.getString(7));
                    bean.setTelnr(rs.getString(8));
                    bean.setReknr(rs.getString(9));
                    bean.setEmail(rs.getString(10));
                    bean.setBeoordeling(rs.getInt(11));
                    bean.setFotoUrl(rs.getString(12));
                    bean.setTvoegsel(rs.getString(13));;
                    bean.setWachtwoord(rs.getString(14));
                    bean.setLangnotify(rs.getString(15));
                    bean.setLocale(rs.getString(15));
                    bean.setWachtwoord2(wachtwoord);
                    bean.setRol(rs.getInt(16));
                    bean.setValid(true);


                    //Hieronder wordt de locale vanuit de getter geprint naar de console
                    System.out.println("Dit is de locale vanuit de getter van sessie :" + bean.getLocale());


                    System.out.println("Dit is de user role: " + bean.getRol());

                } else {
                    System.out.println("Sorry, you are not a registered user! Please sign up first");
                    bean.setValid(false);
                }
            }
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }

                con = null;
            }
        }

        return bean;

    }

    /**
     *
     * @param beanadmin
     * @return
     */
    public static Admin adminLogin(Admin beanadmin) {
  
        //preparing some objects for connection 
        Statement stmt = null;
        
        Connection currentCon = Dryves.ConnectionManager.getConnection();
        
        Admin admin = new Admin();
  
        try {
            
            PreparedStatement pstmtadmin = currentCon.prepareStatement("SELECT * FROM configuratie WHERE confnr = 1");
            
            System.out.println("kom ik hier nog??");
            
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            
            
            stmt = currentCon.createStatement();

            rs = pstmtadmin.executeQuery();
            
            System.out.println("Resultset: " + rs);

            //rs = stmt.executeQuery(zoekQuery);           
            boolean more = rs.next();

            // if user does not exist set the isValid variable to false
            if (!more) {

                System.out.println("Er zijn geen waardes gevonden in de configuratie tabel");

            } //if user exists set the isValid variable to true
            else if (more) {

                //Hieronder wordt de adminbean gevuld met de waarden uit de configuratietabel


                admin.setAchtergrond(rs.getString(2));
                admin.setRitprijs(rs.getString(3));

                System.out.println("Dit is de waarde uit achtergrond: " + admin.getAchtergrond());
                System.out.println("Dit is de waarde uit ritprijs: " + admin.getRitprijs());



            } else {
                System.out.println("Er zijn geen gegevens gevonden in de configuratietabel");

            }
        } catch (Exception ex) {
            System.out.println("Gegevens ophalen uit de configuratietabel is niet gelukt!" + ex);
        } //some exception handling
        finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
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



        return admin;


    }
}
