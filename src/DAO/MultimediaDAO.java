/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Multimedia;

/**
 *
 * @author GerardoAGL
 */
public class MultimediaDAO {
    
    private Multimedia multimedia;
    
    public MultimediaDAO(Multimedia multimedia){
        this.multimedia = multimedia;
        this.multimedia.setId(size() + 1);
    }
    
    public void save(){
        Connection db = new Connection();
        db.saveMultimedia(this.multimedia);
        db.close();
    }
    
    public static int size(){
        Connection db = new Connection();
        int size = db.multimediaSize();
        db.close();
        
        return size;
    }
    
    public static Multimedia[] search(String search){
        Connection db = new Connection();
        Multimedia multimedia[] = db.searchMultimedia(search);
        db.close();
        
        return multimedia;
    }
    
}
