/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

/**
 *
 * @author H
 */
public class Pager {
      int aantalberichten;

    
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
