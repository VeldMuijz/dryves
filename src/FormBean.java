package test;

import java.io.*;
import java.util.*;

public class FormBean {

    private String Voornaam;
    private String Achternaam;
    private String email;
    private String Gebruikersnaam;
    private String password1;
    private String password2;
    private String Postcode;
    private String[] faveTech;
    private String notify;
    private Hashtable errors;

    public boolean validate() {
        boolean bool = true;
        if (Voornaam.equals("")) {
            errors.put("Voornaam", "Typ hier uw voornaam");
            Voornaam = "";
            bool = false;
        }
        if (Achternaam.equals("")) {
            errors.put("Achternaam", "Typ hier uw achternaam");
            Achternaam = "";
            bool = false;
        }
        if (email.equals("") || (email.indexOf('@') == -1)) {
            errors.put("email", "Typ een geldig e-mail adres");
            email = "";
            bool = false;
        }
        if (Gebruikersnaam.equals("")) {
            errors.put("Gebruikersnaam", "Typ hier een gebruikersnaam");
            Gebruikersnaam = "";
            bool = false;
        }
        if (password1.equals("")) {
            errors.put("password1", "Typ een geldig paswoord");
            password1 = "";
            bool = false;
        }
        if (!password1.equals("") && (password2.equals("")
                || !password1.equals(password2))) {
            errors.put("password2", "Bevestig uw wachtwoord");
            password2 = "";
            bool = false;
        }
        if (Postcode.equals("") || Postcode.length() != 6) {
            errors.put("Postcode", "Typ een geldige postcode");
            Postcode = "";
            bool = false;
        } else {
            try {
                int x = Integer.parseInt(Postcode);
            } catch (NumberFormatException e) {
                errors.put("Postcode", "Typ een geldige postcode");
                Postcode = "";
                bool = false;
            }
        }
        return bool;
    }

    public String getErrorMsg(String s) {
        String errorMsg = (String) errors.get(s.trim());
        return (errorMsg == null) ? "" : errorMsg;
    }

    public FormBean() {
        Voornaam = "";
        Achternaam = "";
        email = "";
        Gebruikersnaam = "";
        password1 = "";
        password2 = "";
        Postcode = "";
        faveTech = new String[]{"1"};
        notify = "";
        errors = new Hashtable();
    }

    public String getFirstName() {
        return Voornaam;
    }

    public String getLastName() {
        return Achternaam;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return Gebruikersnaam;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String getZip() {
        return Postcode;
    }

    public String getNotify() {
        return notify;
    }

    public String[] getFaveTech() {
        return faveTech;
    }

    public String isCbSelected(String s) {
        boolean found = false;
        if (!faveTech[0].equals("1")) {
            for (int i = 0; i < faveTech.length; i++) {
                if (faveTech[i].equals(s)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                return "checked";
            }
        }
        return "";
    }

    public String isRbSelected(String s) {
        return (notify.equals(s)) ? "checked" : "";
    }

    public void setFirstName(String fname) {
        Voornaam = fname;
    }

    public void setLastName(String lname) {
        Achternaam = lname;
    }

    public void setEmail(String eml) {
        email = eml;
    }

    public void setUserName(String u) {
        Gebruikersnaam = u;
    }

    public void setPassword1(String p1) {
        password1 = p1;
    }

    public void setPassword2(String p2) {
        password2 = p2;
    }

    public void setZip(String z) {
        Postcode = z;
    }

    public void setFaveTech(String[] music) {
        faveTech = music;
    }

    public void setErrors(String key, String msg) {
        errors.put(key, msg);
    }

    public void setNotify(String n) {
        notify = n;
    }
}

