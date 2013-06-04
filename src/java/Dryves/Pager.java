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
    int statusHuidigePage;
    String startpunt, eindpunt;

    /**
     * Met deze getter halen we het startpunt die is opgegeven in de zoekquery
     *
     * @return String
     */
    public String getStartpunt() {
        return startpunt;
    }

    /**
     * Met deze setter stellen we het startpunt die vanuit de zoekquery is
     * opgegeven
     *
     * @param startpunt
     */
    public void setStartpunt(String startpunt) {
        this.startpunt = startpunt;
    }

    /**
     * Met deze getter halen we het eindpunt die is opgegeven in de zoekquery
     *
     * @return String
     */
    public String getEindpunt() {
        return eindpunt;
    }

    /**
     * Met deze setter stellen we het eindpunt die vanuit de zoekquery is
     * opgegeven
     *
     * @param eindpunt
     */
    public void setEindpunt(String eindpunt) {
        this.eindpunt = eindpunt;
    }

    /**
     * Hier halen we het aantal zoekresultaten op.
     *
     * @return int
     */
    public int getAantalZoekResultaten() {
        return aantalZoekResultaten;
    }

    /**
     * Hier stellen we het aantal zoekresultaten in.
     *
     * @param aantalZoekResultaten
     */
    public void setAantalZoekResultaten(int aantalZoekResultaten) {
        this.aantalZoekResultaten = aantalZoekResultaten;
    }

    /**
     * Hier halen we het totale pagers op
     *
     * @return int
     */
    public int getStatusTotaalPager() {
        return statusTotaalPager;
    }

    /**
     * Hier stellen we het totale pagers op.
     *
     * @param statusTotaalPager
     */
    public void setStatusTotaalPager(int statusTotaalPager) {
        this.statusTotaalPager = statusTotaalPager;
    }

    /**
     * Hiermee halen we de juiste pagers op.
     *
     * @return int
     */
    public int getStatusHuidigePage() {
        return statusHuidigePage;
    }

    /**
     * Hiermee stellen we de pager in.
     *
     * @param statusHuidigePage
     */
    public void setStatusHuidigePage(int statusHuidigePage) {
        this.statusHuidigePage = statusHuidigePage;
    }
    /**
     * Hiermee halen we de beoordelingen op
     * @return int
     */
    public int getAantalbeoordelingen() {
        return aantalbeoordelingen;
    }
    /**
     * Hiermee stellen we de beoordeelingen in
     * @param aantalbeoordelingen 
     */
    public void setAantalbeoordelingen(int aantalbeoordelingen) {
        this.aantalbeoordelingen = aantalbeoordelingen;
    }
/**
 * Hiermee halen we het aantalaankopen
 * @return  int
 */
    public int getAantalAankopen() {
        return aantalAankopen;
    }
/**
 * Hiermee stellen we het aantal aankopen.
 * @param aantalAankopen 
 */
    public void setAantalAankopen(int aantalAankopen) {
        this.aantalAankopen = aantalAankopen;
    }
/**
 * Met de offset kunnen we de juiste recordes opvragen uit de database.
 * Met de String knop kunnen we zien welke knop de gebruiker heeft ingedrukt.  

 * @param offset
 * @param knop 
 */
    public void pagerRitten(int offset, String knop) {
    }
/**
 * Hiermee stellen we het aantal ritten in.
 * @return int
 */
    public int getAantalritten() {
        return aantalritten;
    }
/**
 * Hiermee stellen we het aantal ritten in.
 * @param aantalritten 
 */
    public void setAantalritten(int aantalritten) {
        this.aantalritten = aantalritten;
    }
/**
 * Hiermee halen we het aantal berichten op.
 * @return int
 */
    public int getAantalberichten() {
        return aantalberichten;
    }
/**
 * Hiermee stellen we het aantal berichten in.
 * @param aantalberichten 
 */
    public void setAantalberichten(int aantalberichten) {
        this.aantalberichten = aantalberichten;
    }
    int offset;
    int maxPositie;
/**
 * Met de offset kunnen we bepalen welke recordes we uit de databasde kunnen halen.
 * @return 
 */
    public int getOffset() {
        return offset;
    }
/**
 * * Met de offset kunnen we bepalen welke recordes we uit de databasde kunnen halen.
 * @param offset 
 */
    public void setOffset(int offset) {
        this.offset = offset;
    }
/**
 Hier stellen we de maximale positie in voor de pager.
 * @return int
 */
    public int getMaxPositie() {
        return maxPositie;
    }
/*
 * Hiermee halen we de maximale positie in zodat we de pager af kunnen stellen.
 */
    public void setMaxPositie(int maxPositie) {
        this.maxPositie = maxPositie;
    }
}
