/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author GerardoAGL
 */
public class Category {
    
    private int id;
    private String name;
    private boolean apps;
    private boolean stored;
    
    public Category(String name, boolean apps) {
       
        this.name = name;
        this.apps = apps;
        this.stored = true;
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }    

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public boolean hasApps() {
        return apps;
    }

    public void setApps(boolean apps) {
        this.apps = apps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }       
}
