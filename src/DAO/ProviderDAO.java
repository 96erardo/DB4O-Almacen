/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Provider;

/**
 *
 * @author GerardoAGL
 */
public class ProviderDAO {
    
    private Provider provider;
    
    public ProviderDAO(Provider provider){
        this.provider = provider;
        this.provider.setId(size()+1);
    }
    
    public void save(){
        Connection db = new Connection();
        db.saveProvider(this.provider);
        db.close();
    }
    
    public void delete(){
        Connection db = new Connection();
        db.deleteProvider(this.provider);
        db.close();
    }
    
    public static int size(){
        Connection db = new Connection();
        int size = db.providerSize();
        db.close();
        
        return size;
    }
    
    public static Provider providerById(int id){
        Connection db = new Connection();
        Provider provider = db.providerById(id);
        db.close();
        
        return provider;
    }
    
    public static Provider[] search(String search){
        Connection db = new Connection();
        Provider providers[] = db.searchProvider(search);
        db.close();
        
        return providers;
    }
    
}
