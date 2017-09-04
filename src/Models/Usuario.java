/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author GerardoAGL
 */
public class Usuario{
    
    private String usuario;
    private String clave;
    private String nombre;
    private String cedula;

    public Usuario(String usuario, String clave, String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.usuario = usuario;
        if(clave == null)
            this.clave = clave;
        else
            this.clave = DigestUtils.sha1Hex(clave);
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
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = DigestUtils.sha1Hex(clave);
    }
   
    public String toString(){
        
        return getNombre() +" "+ usuario +" "+ clave; 
    }
}
