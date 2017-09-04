/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.awt.image.BufferedImage;

/**
 *
 * @author GerardoAGL
 */
public class Persona {
    
    private String nombre;
    private String cedula;
    private BufferedImage picture;
    private boolean stored;
    
    public Persona(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }
    
    
    public Persona(String nombre, String cedula, BufferedImage picture) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.picture = picture;
        this.stored = true;
    }

    public BufferedImage getPicture() {
        return picture;
    }

    public void setPicture(BufferedImage picture) {
        this.picture = picture;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }
}
