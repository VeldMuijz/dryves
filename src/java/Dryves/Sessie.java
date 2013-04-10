/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

/**
 *
 * @author RickSpijker
 */

public class Sessie {

      private String email;
      private String wachtwoord;
      private String vnaam;
      private String anaam;
      public boolean valid;


      public String getVnaam() {
         return vnaam;
    }

      public void setVnaam(String newVnaam) {
         vnaam = newVnaam;
    }


      public String getAnaam() {
         return anaam;
            }

      public void setAnaam(String newAnaam) {
         anaam = newAnaam;
            }


      public String getWachtwoord() {
         return wachtwoord;
    }

      public void setWachtwoord(String newWachtwoord) {
         wachtwoord = newWachtwoord;
    }


      public String getEmail() {
         return email;
            }

      public void setEmail(String newEmail) {
         email = newEmail;
            }


      public boolean isValid() {
         return valid;
    }

      public void setValid(boolean newValid) {
         valid = newValid;

}

}
