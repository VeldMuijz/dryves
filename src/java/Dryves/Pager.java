/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 *
 * @author H
 */
public class Pager {
      int aantalberichten;
      int aantalritten;
      int aantalAankopen;
      int aantalbeoordelingen; 
      int statusTotaalPager;
      int aantalZoekResultaten;
      String startpunt, eindpunt;

    public String getStartpunt() {
        return startpunt;
    }

    public void setStartpunt(String startpunt) {
        this.startpunt = startpunt;
    }

    public String getEindpunt() {
        return eindpunt;
    }

    public void setEindpunt(String eindpunt) {
        this.eindpunt = eindpunt;
    }

    public int getAantalZoekResultaten() {
        return aantalZoekResultaten;
    }

    public void setAantalZoekResultaten(int aantalZoekResultaten) {
        this.aantalZoekResultaten = aantalZoekResultaten;
    }

    public int getStatusTotaalPager() {
        return statusTotaalPager;
    }

    public void setStatusTotaalPager(int statusTotaalPager) {
        this.statusTotaalPager = statusTotaalPager;
    }

    public int getStatusHuidigePage() {
        return statusHuidigePage;
    }

    public void setStatusHuidigePage(int statusHuidigePage) {
        this.statusHuidigePage = statusHuidigePage;
    }
      int statusHuidigePage;

   
   
     

    

    public int getAantalbeoordelingen() {
        return aantalbeoordelingen;
    }

    public void setAantalbeoordelingen(int aantalbeoordelingen) {
        this.aantalbeoordelingen = aantalbeoordelingen;
    }

   

    public int getAantalAankopen() {
        return aantalAankopen;
    }

    public void setAantalAankopen(int aantalAankopen) {
        this.aantalAankopen = aantalAankopen;
    }
      
      
      
      public void pagerRitten(int offset, String knop){
      
          
         
               
      
      }
      
      
    public int getAantalritten() {
        return aantalritten;
    }

    public void setAantalritten(int aantalritten) {
        this.aantalritten = aantalritten;
    }
    
    public int getAantalberichten() {
        return aantalberichten;
    }

    public void setAantalberichten(int aantalberichten) {
        this.aantalberichten = aantalberichten;
    }
    int offset;
    int maxPositie;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getMaxPositie() {
        return maxPositie;
    }

    public void setMaxPositie(int maxPositie) {
        this.maxPositie = maxPositie;
    }
    
    
}
