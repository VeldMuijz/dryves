/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

/**
 *
 * @author RickSpijker
 */
public class Admin implements java.io.Serializable {
    
    private String achtergrond;
    private String ritprijs;
    
    
    public Admin(){
    
    }
    
    public Admin(String achtergrond, String ritprijs){
        this.achtergrond = achtergrond;
        this.ritprijs = ritprijs;
    }

    public String getAchtergrond() {
        return achtergrond;
    }

    public void setAchtergrond(String achtergrond) {
        this.achtergrond = achtergrond;
    }

    public String getRitprijs() {
        return ritprijs;
    }

    public void setRitprijs(String ritprijs) {
        this.ritprijs = ritprijs;
    }
    
    
    
    
    
}
