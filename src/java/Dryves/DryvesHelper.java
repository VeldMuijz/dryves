/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author RickSpijker
 */
public class DryvesHelper {
    
    Session session = null;
    
    public DryvesHelper(){
    
        this.session = NewHibernateUtil.getSessionFactory().getCurrentSession();
    
    }
    
    public List getAccountNumber(int startID, int endID) {
        
        List<Lid> memberList = null;
        
        try {
                org.hibernate.Transaction tx = session.beginTransaction();
                Query q = session.createQuery("From Lid as Leden where lid.Lidnr between '"+startID+"' and '" +endID+"'");
                memberList = (List<Lid>) q.list();
        } catch (Exception e){
        
                e.printStackTrace();
        
        }
        
        return memberList;
    
        
    
    }
    
    
    public void loginGebruiker(String email, String wachtwoord) {
    
        
        try {
        
                org.hibernate.Transaction tx = session.beginTransaction();
                Query q = session.createQuery("From Lid where lidnr = ");
        
        } catch (Exception e) {
        
            e.printStackTrace();
            
        }
        
        
    
    }
    
}
