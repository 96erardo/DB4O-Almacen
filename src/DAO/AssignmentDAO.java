/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Assignment;
import Models.Device;

/**
 *
 * @author GerardoAGL
 */
public class AssignmentDAO {
    
    private Assignment assignment;
    
    public AssignmentDAO(Assignment assignment){
        this.assignment = assignment;
        this.assignment.setId(this.size() + 1);
    }
    
    public void save(){
        Connection db = new Connection();
        db.saveAssignment(this.assignment);
        db.close();
    }
    
    public static void update(Assignment assignment){
        Connection db = new Connection();
        db.updateAssignment(assignment);
        db.close();
    }
    
    public static int size(){
        Connection db = new Connection();
        int size = db.assignmentSize();
        db.close();
        
        return size;
    }
    
    public static Assignment assignmentById(int id){
        Connection db = new Connection();
        Assignment assignment = db.assignmentById(id);
        db.close();
        
        return assignment;
    }
    
    public static Assignment assignmentByDevice(Device device){
        Connection db = new Connection();
        Assignment assignment = db.assignmentByDevice(device);
        db.close();
        
        return assignment;
    }
    
    public static Assignment[] search(String search){
        Connection db = new Connection();
        Assignment assignments[] = db.searchAssignment(search);
        db.close();
        
        return assignments;
    }
}
