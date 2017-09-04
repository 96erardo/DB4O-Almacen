/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Persona;

/**
 *
 * @author GerardoAGL
 */
public class PersonDAO {
    
    private Persona person;
    
    public PersonDAO(Persona person){
        this.person = person;
    }
    
    public PersonDAO(String id){
        this.person = personById(id);
    }
    
    public void save(){
        Connection db = new Connection();
        db.savePerson(this.person);
        db.close();
    }
    
    public void update(){
        Connection db = new Connection();
        db.updatePerson(this.person);
        db.close();        
    }
        
    public static Persona personById(String id){
        Connection db = new Connection();
        Persona person = db.personById(id);
        db.close();
        
        return person;
    }
    
    public static Persona[] search(String search){
        Connection db = new Connection();
        Persona[] persons = db.searchPersons(search);
        db.close();
        
        return persons;
    }
    
    public static int size(){
        Connection db = new Connection();
        int size = db.personSize();
        db.close();
        
        return size;
    }
    
    public static boolean isPerson(String id){
        Connection db = new Connection();
        boolean isIt = db.isPerson(id);
        db.close();
        
        return isIt;
    }
}
