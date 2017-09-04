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
public class Aplicacion {
    
    private String nombre;
    private String fichaTecnica;

    public Aplicacion(String nombre, String fichaTecnica) {
        this.nombre = nombre;
        this.fichaTecnica = fichaTecnica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(String fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }
}
