/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

/**
 *
 * @author RickSpijker
 */
import static Dryves.RitDao.currentCon;
import java.text.*;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RickSpijker
 */
public class LidDao {
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
    private String wachtwoord2;
    private String fotoUrl;
    private String tvoegsel;
    private String langnotify;
    private Hashtable errors;
    private boolean success;
    static Connection currentCon;
	static ResultSet rs;

	  /**
     * Ophalen van alle gegevens uit de servlet voor de registreren.jsp
     *
     * @param bean
     * @return
     */
    public Lid vulLidDao(Lid bean) {

		vnaam = bean.getVnaam();
        setVnaam(bean.getVnaam());
        setAnaam(bean.getAnaam());
        setTvoegsel(bean.getTvoegsel());
        setStraat(bean.getStraat());
        setHuisnummer(bean.getHuisnummer());
        setGeslacht(bean.getGeslacht());
        setPostcode(bean.getPostcode());
        setStad(bean.getStad());
        setTelnr(bean.getTelnr());
        setReknr(bean.getReknr());
        setEmail(bean.getEmail());
        setWachtwoord(bean.getWachtwoord());
        setFotoUrl(bean.getFotoUrl());
        setLangnotify(bean.getLangnotify());


        System.out.println("Lid DAO GEGEVENS:");
        System.out.println("*******************************");
        System.out.println("voornaam: " + vnaam);
        System.out.println("achternaam: " + anaam);
        System.out.println("tussenvoegsel: " + tvoegsel);
        System.out.println("geslacht: " + geslacht);
        System.out.println("straat: " + straat);
        System.out.println("husinummer: " + huisnummer);
        System.out.println("postcode: " + postcode);
        System.out.println("stad: " + stad);
        System.out.println("telefoonnummer: " + telnr);
        System.out.println("reneningnummer: " + reknr);
        System.out.println("email: " + email);
        System.out.println("wachtwoord: " + wachtwoord);
        System.out.println("wachtwoord2: " + wachtwoord2);
        System.out.println("fotourl: " + fotoUrl);
        System.out.println("locale: " + langnotify);
        System.out.println("*******************************");
        System.out.println("EIND DAO Lid GEGEVENS");

        saveRegistratie();

        return bean;
    }

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
                    System.out.println("Dit is de locale vanuit de getter van sessie :" + bean.getLocaleStr());


                    System.out.println("Dit is de rol van de gebruiker: " + bean.getRol());

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


    //Hier kijken of de gebruiker al bestaat met behyulp van een facebookid
    public Boolean checkDuplicateFacebookID(String facebookid) {

        boolean more=false;

        try {


            Connection con = Dryves.ConnectionManager.getConnection();

            ResultSet rs;
            PreparedStatement pstmt = con.prepareStatement("SELECT facebookid FROM lid  WHERE facebookid=?");

            pstmt.setString(1, facebookid);
            
            rs = pstmt.executeQuery();
            if(rs.next()){
            
            more=true;
            }
            
            

        }catch(SQLException e){System.out.println();}

    
    return more;
    
    }
          
      

    // in deze functie kijken we of de email bestaat
    public Boolean checkDuplicate(String email) {
        currentCon = ConnectionManager.getConnection();
        Boolean more = null;
        Statement stmt = null;

        try {
            stmt = currentCon.createStatement();
            String query = "SELECT email FROM Lid WHERE email='" + email + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                more = true;
            } else {
                more = false;
            }
            currentCon.close();
        } catch (SQLException s) {
            System.out.println("Er kan geen vebinding worden gemaakt met de database." + s);
        }
        return more;
    }

  

    /**
     * Opslaan van registratie in de database
     */
    public Boolean saveRegistratie() {
        try {
            currentCon = ConnectionManager.getConnection();
            PreparedStatement insertLid;

            String queryString = ("INSERT INTO Lid ("
                    + " vnaam,"
                    + " anaam,"
                    + " tvoegsel,"
                    + " geslacht,"
                    + " straat,"
                    + " postcode,"
                    + " stad,"
                    + " telnr,"
                    + " reknr,"
                    + " email,"
                    + " wachtwoord,"
                    + " fotoUrl,"
                    + " langnotify)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");


            insertLid = currentCon.prepareStatement(queryString);


            insertLid.setString(1, vnaam);
            insertLid.setString(2, anaam);
            insertLid.setString(3, tvoegsel);
            insertLid.setString(4, geslacht);
            insertLid.setString(5, straat);
            insertLid.setString(6, postcode);
            insertLid.setString(7, stad);
            insertLid.setString(8, telnr);
            insertLid.setInt(9, Integer.parseInt(reknr));
            insertLid.setString(10, email);
            insertLid.setString(11, wachtwoord);
            insertLid.setString(12, fotoUrl);
            insertLid.setString(13, langnotify);



            System.out.println("De query is: " + insertLid);

            insertLid.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LidDao.class.getName()).log(Level.SEVERE, null, ex);
				return false;

        }
        return true;

    }

    /**
     *
     * @param beanadmin
     * @return
     */
    public void adminLogin(Lid bean) {
  
        //preparing some objects for connection 
        Statement stmt = null;
        
        Connection currentCon = Dryves.ConnectionManager.getConnection();
  
        try {
            
            PreparedStatement pstmtadmin = currentCon.prepareStatement("SELECT * FROM configuratie WHERE confnr = 1");
            
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            
            
            stmt = currentCon.createStatement();

            rs = pstmtadmin.executeQuery();

            //rs = stmt.executeQuery(zoekQuery);           
            boolean more = rs.next();

            // if user does not exist set the isValid variable to false
            if (!more) {

                System.out.println("Er zijn geen waardes gevonden in de configuratie tabel");

            } //if user exists set the isValid variable to true
            else if (more) {

                //Hieronder wordt de adminbean gevuld met de waarden uit de configuratietabel


                bean.setAchtergrond(rs.getString(2));
                bean.setRitprijs(rs.getString(3));

                System.out.println("Dit is de waarde uit achtergrond: " + bean.getAchtergrond());
                System.out.println("Dit is de waarde uit ritprijs: " + bean.getRitprijs());



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


	}
        
        public Lid enkelLidUpdaten(Lid bean){
            try {
			currentCon = ConnectionManager.getConnection();
                        
			PreparedStatement updateLid;
			String queryString = ("UPDATE lid "
                                + "SET"
                          
                                + " vnaam = ?,"
                                + " anaam = ?,"
                                + " tvoegsel =?,"
                                + " straat = ?,"
                                + " postcode = ?,"
                                + " reknr =?,"
                                + " telnr =?,"
                                + " wachtwoord =?,"
                                + " email =?,"
                                + " stad = ?,"
                                + " geslacht =?,"
                                + " langnotify = ?"
                                + " WHERE lidnr = ?");

			updateLid = currentCon.prepareStatement(queryString);

                        updateLid.setString(1, bean.getVnaam());
                        updateLid.setString(2, bean.getAnaam());
                        updateLid.setString(3, bean.getTvoegsel());
                        updateLid.setString(4, bean.getStraat());                       
                        updateLid.setString(5, bean.getPostcode());
                                               updateLid.setInt(6, Integer.parseInt(bean.getReknr()));
                        updateLid.setString(7, bean.getTelnr());
                        updateLid.setString(8, bean.getWachtwoord());
                        updateLid.setString(9, bean.getEmail());
                        updateLid.setString(10, bean.getStad());
                        updateLid.setString(11, bean.getGeslacht());
                        updateLid.setString(12, bean.getLangnotify());
                        
                        updateLid.setInt(13, bean.getLidnr());
                        
                        
                        
			System.out.println("De query is: " + updateLid);
                        
			updateLid.executeUpdate();
                        
                        
                       
                        
                        
		} catch (SQLException ex) {
			Logger.getLogger(RitDao.class.getName()).log(Level.SEVERE, null, ex);

		}
            
            return bean;
        }


    

        
        
        public Lid loginFacebook(String facebookid){
            
      Connection con = Dryves.ConnectionManager.getConnection();
		Statement stmt = null;
                ResultSet rs;
	
Lid lidfacebook= new Lid();
        
        
        try {
			PreparedStatement pstmt = con.prepareStatement("SELECT lidnr ,vnaam,anaam,geslacht,straat,postcode, stad,telnr,reknr,email,beoordeling,fotourl,tvoegsel,wachtwoord,langnotify FROM lid WHERE facebookid = ?");
			pstmt.setString(1, facebookid);
			//connect to DB 
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();




			rs = pstmt.executeQuery();

			
				while(rs.next()) {






					//de nieuwe
					lidfacebook.setLidnr(rs.getInt(1));
					lidfacebook.setVnaam(rs.getString(2).toString());
					lidfacebook.setAnaam(rs.getString(3));
					lidfacebook.setGeslacht(rs.getString(4));
					lidfacebook.setStraat(rs.getString(5));
					lidfacebook.setPostcode(rs.getString(6));
					lidfacebook.setStad(rs.getString(7));
					lidfacebook.setTelnr(rs.getString(8));
					lidfacebook.setReknr(rs.getString(9));
					lidfacebook.setEmail(rs.getString(10));
					lidfacebook.setBeoordeling(rs.getInt(11));
					lidfacebook.setFotoUrl(rs.getString(12));
					lidfacebook.setTvoegsel(rs.getString(13));;
					lidfacebook.setWachtwoord(rs.getString(14));
					lidfacebook.setLangnotify(rs.getString(15));
					lidfacebook.setLocale(rs.getString(15));//
					lidfacebook.setWachtwoord2(rs.getString(14));
					lidfacebook.setValid(true);


					//Hieronder wordt de locale vanuit de getter geprint naar de console
					System.out.println("Dit is de locale vanuit de getter van sessie :" + lidfacebook.getLocale());

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

		return lidfacebook;

	

        
        
        
        
        
        
        
        
        
        
        
        
        }

	public String getVnaam() {
		return vnaam;
	}

	public void setVnaam(String vnaam) {
		this.vnaam = vnaam;
	}

	public String getAnaam() {
		return anaam;
	}

	public void setAnaam(String anaam) {
		this.anaam = anaam;
	}

	public String getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(String geslacht) {
		this.geslacht = geslacht;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStad() {
		return stad;
	}

	public void setStad(String stad) {
		this.stad = stad;
	}

	public String getTelnr() {
		return telnr;
	}

	public void setTelnr(String telnr) {
		this.telnr = telnr;
	}

	public String getReknr() {
		return reknr;
	}

	public void setReknr(String reknr) {
		this.reknr = reknr;
	}

	public String getEmail() {
		return email;
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

	public String getWachtwoord2() {
		return wachtwoord2;
	}

	public void setWachtwoord2(String wachtwoord2) {
		this.wachtwoord2 = wachtwoord2;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public String getTvoegsel() {
		return tvoegsel;
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

	public Hashtable getErrors() {
		return errors;
	}

	public void setErrors(Hashtable errors) {
		this.errors = errors;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public static Connection getCurrentCon() {
		return currentCon;
	}

	public static void setCurrentCon(Connection currentCon) {
		LidDao.currentCon = currentCon;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static void setRs(ResultSet rs) {
		LidDao.rs = rs;
	}
        
        
	
}
