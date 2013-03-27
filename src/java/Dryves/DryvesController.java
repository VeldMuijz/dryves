/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dryves;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

/**
 *
 * @author RickSpijker
 */
@ManagedBean
@SessionScoped
public class DryvesController {
    
    int startId;
    int endId;
    DataModel memberNames;
    DryvesHelper helper;
    private int recordCount = 1000;
    private int pageSize = 10;

    /**
     * Creates a new instance of DryvesController
     */
    public DryvesController() {
        
        helper = new DryvesHelper();
        startId = 1;
        endId = 4;
    }
    
    public DryvesController(int startId, int endId) {
        
        helper = new DryvesHelper();
        this.startId = startId;
        this.endId = endId;
        
    }
    
}
