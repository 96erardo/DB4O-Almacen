/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author GerardoAGL
 */
public class Device {
    
    private int id;
    private int category;
    private String brand;
    private String model;
    private String condition;
    private String status;
    private boolean stored;
    private ArrayList<Characteristic> characteristics;
    private ArrayList<String> apps;
    private BufferedImage photo;

    public Device(int category, String brand, String model, ArrayList<Characteristic> characteristics, ArrayList<String> apps, BufferedImage photo) {
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.condition = "Funcional";
        this.status = "Disponible";
        this.characteristics = characteristics;
        this.apps = apps;
        this.photo = photo;
        this.stored = true;
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }
    
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(ArrayList<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public ArrayList<String> getApps() {
        return apps;
    }

    public void setApps(ArrayList<String> apps) {
        this.apps = apps;
    }

    public BufferedImage getPhoto() {
        return photo;
    }

    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }
    
    
}