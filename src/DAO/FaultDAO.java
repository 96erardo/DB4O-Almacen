/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Fault;

/**
 *
 * @author GerardoAGL
 */
public class FaultDAO {
    
    private Fault fault;
    
    public FaultDAO(Fault fault){
        this.fault = fault;
        this.fault.setCode(size() + 1);
    }
    
    public void save(){
        Connection db = new Connection();
        db.saveFault(this.fault);
        db.close();
    }
    
    public static boolean update(Fault fault){
        Connection db = new Connection();
        boolean updated = db.updateFault(fault);
        db.close();
        
        return updated;
    }
    
    public Fault faultById(int id){
        Connection db = new Connection();
        Fault fault = db.faultById(id);
        db.close();
        
        return fault;
    }
    
    public static Fault[] search(String search){
        Connection db = new Connection();
        Fault f[] = db.searchFault(search);
        db.close();
        
        return f;
    }

    public static int costs(){
        Connection db = new Connection();
        int cost = db.totalCost();
        db.close();
        
        return cost;
    }
    
    private int size(){
        Connection db = new Connection();
        int size = db.faultSize();
        db.close();
        
        return size;
    }
}
