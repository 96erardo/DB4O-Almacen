/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Usuario;

/**
 *
 * @author GerardoAGL
 */
public class UserDAO {
    
    private Usuario user;
    
    public UserDAO(Usuario user){
        this.user = user;
    }
    
    public UserDAO(String username){
        this.user = searchByUsername(username);
    }
    
    public void save(){
        Connection db = new Connection();
        db.saveUser(this.user);
        db.close();
    }
    
    public static boolean login(String username, String pass){
        Connection db = new Connection();
        boolean isit = db.login(username, pass);
        db.close();
    
        return isit;
    }
    
    public void delete(){
        Connection db = new Connection();
        db.deleteUser(this.user);
        db.close();
    }
    
    public void update(String name, String id, String username){
        Connection db = new Connection();
        db.updateUser(this.user, name, id, username);
        db.close();
    }
    
    public void update(String name, String id, String username, String pass){
        Connection db = new Connection();
        db.updateUser(this.user, name, id, username, pass);
        db.close();
    }
    
    private Usuario searchByUsername(String username){
        Connection db = new Connection();
        Usuario user = db.searchUser(username);
        db.close();
        
        return user;
    }
    
    public Usuario getUser(){
        return this.user;
    }
}
