/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.db4o.*;
import com.db4o.query.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GerardoAGL
 */
public class Management {
    /*
    private static final String DBNAME = "Storehouse";
    
    //USER MANAGEMENT    
    public static boolean login(String username, String password){
        
        Usuario usuario = new Usuario(username, password, null, null);
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        try{            
            ObjectSet result = db.queryByExample(usuario);
            if(result.isEmpty())
                return false;
            else
                return true;
        }finally{
            db.close();
        }
    }
    
    public static void addUser(String username, String pass, String name, String id){
        
        Usuario user = new Usuario(username, pass, name, id);
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        try{
            db.store(user);
        }finally{
            db.close();
        }
    }
    
    public static boolean deleteByUsername(String username){
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        Usuario usuario = new Usuario(username, null, null, null);
        try{
            ObjectSet result = db.queryByExample(usuario);
            /*while(result.hasNext()){
                Usuario found = (Usuario)result.next();
                db.delete(found);
            }*//*
            Usuario found = (Usuario)result.next();
            db.delete(found);
            return true;
        }finally{
            db.close();
        }
    }
    
    public static void allUsers() {
        
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        Usuario search = new Usuario(null, null, null, null);
        try{
            ObjectSet result = db.queryByExample(search);
            listResult(result);
        }finally{
            db.close();
        }
    }
    
    public static Usuario getUser(String username) {
        
        Usuario user = new Usuario(username, null, null, null);
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        try{
            
            ObjectSet result = db.queryByExample(user);
            if(result.isEmpty())
                return null;
            else
                return (Usuario)result.next();
        }finally{
            
            db.close();
        }
    }
    
    public static Persona getPerson(String cedula) {
        
    Persona person = new Persona(null, cedula);
    ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
    try{

        ObjectSet result = db.queryByExample(person);
        if(result.isEmpty())
            return null;
        else
            return (Persona)result.next();
    }finally{

        db.close();
    }
    }
    
    public static boolean updateUser(String user, String name, String id, String username){
        
        Usuario search = new Usuario(user, null, null, null);
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        try{
            ObjectSet result = db.queryByExample(search);
            if(result.isEmpty())
                return false;
            else{
                Usuario toUpdate = (Usuario)result.next();
                toUpdate.setNombre(name);
                toUpdate.setCedula(id);
                toUpdate.setUsuario(username);
                db.store(toUpdate);
                return true;
            }               
        }finally{
            db.close();
        }
    }
    
    public static boolean updateUser(String user, String name, String id, String username, String pass){
        
        Usuario search = new Usuario(user, null, null, null);
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        try{
            ObjectSet result = db.queryByExample(search);
            if(result.isEmpty())
                return false;
            else{
                Usuario toUpdate = (Usuario)result.next();
                toUpdate.setNombre(name);
                toUpdate.setCedula(id);
                toUpdate.setUsuario(username);
                toUpdate.setClave(pass);
                db.store(toUpdate);
                return true;
            }               
        }finally{
            db.close();
        }
    }
    
    private static void listResult(List<?> result){
        System.out.println(result.size());
        for (Object o : result) {
            System.out.println(o.toString());
        }
    }
    
    public static Persona[] search(String s){
        
        String search = s.toLowerCase();
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            List<Persona> p = db.query(new Predicate<Persona>(){
                @Override
                public boolean match(Persona et) {
                    
                    String nombre = et.getNombre().toLowerCase();
                    return (nombre.contains(search));
                }
               
            });
            if(p.isEmpty()){
                
                return null;
            }else{
                
                Persona person[] = new Persona[p.size()];
                
                for(int i = 0; i < person.length; i++){
                    person[i] = p.get(i);
                }
                
                return person;
            }            
        }finally{
            db.close();
        }
    }

    //DEVICE MANAGEMENT
    public static Device getDevice(int code){
        
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            List<Device> result = db.query(new Predicate<Device>(){
                @Override
                public boolean match(Device et) {
                    
                    return code == et.getId();
                }                
            });
            
            if(result.isEmpty())
                return null;
            else
                return result.get(0);
        }finally{
            db.close();
        }
    }
    
    public static int getNumberOfDevices() {
        
        Device d = new Device(null, null, null, null, 0);
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            ObjectSet result = db.queryByExample(d);
            
            return result.size();
        }finally{
            db.close();
        }        
    }
    
    public static Device[] searchDevices(String s) {
        
        String search = s.toLowerCase();
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            List<Device> d = db.query(new Predicate<Device>(){
                @Override
                public boolean match(Device et) {
                    
                    String marca = et.getMarca().toLowerCase();
                    String modelo = et.getModelo().toLowerCase();
       
                    return (marca.contains(search) || modelo.contains(search));
                }
               
            });
            if(d.isEmpty()){
                
                return null;
            }else{
                
                Device devices[] = new Device[d.size()];
                
                for(int i = 0; i < devices.length; i++){
                    devices[i] = d.get(i);
                }
                
                return devices;
            }            
        }finally{
            db.close();
        }
    }
    
    //PCS
    public static void addPC(String ty, String os, double fp, double ram, int storage, String brand, String mod){
        
        int id = getNumberOfDevices() + 1;
        
        Computadora pc = new Computadora(id, ty, os, fp, ram, storage, brand, mod, "Bueno", "Disponible");
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            db.store(pc);
        }finally{
            db.close();
        }
    }
    
    public static Computadora[] getPCs(){
                
        Computadora search = new Computadora(0, null, null, 0, 0, 0, null, null, null, null);
        Computadora array[]= null;
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            ObjectSet result = db.queryByExample(search);
            array = new Computadora[result.size()];
            for(int i = 0; i<array.length; i++){
                
                array[i] = (Computadora)result.next();
            }
            
            return array;
        }finally{
            db.close();
        }
    }
    //PRINTERS
    public static void addPrinter(String ti, String mar, String mod){
        
        int id = getNumberOfDevices() + 1;
        
        Impresora printer = new Impresora(id, ti, mar, mod, "Bueno", "Disponible");
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            db.store(printer);
        }finally{
            db.close();
        }
    }
    
    public static Impresora[] getPrinters(){
        
        Impresora search = new Impresora(0, null, null, null, null, null);
        Impresora array[]= null;
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            ObjectSet result = db.queryByExample(search);
            array = new Impresora[result.size()];
            
            for(int i = 0; i<array.length; i++){
                
                array[i] = (Impresora)result.next();
            }
            
            return array;
        }finally{
            db.close();
        }
    }
    
    //RED
    public static void addRed(String ti, String mar, String mod){
        
        int id = getNumberOfDevices() + 1;
        
        Red printer = new Red(0, ti, mar, mod, "Bueno", "Disponible");
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            db.store(printer);
        }finally{
            db.close();
        }
    }
    
    public static Red[] getReds(){
        
        Red search = new Red(0, null, null, null, null, null);
        Red array[]= null;
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            ObjectSet result = db.queryByExample(search);
            array = new Red[result.size()];
            
            for(int i = 0; i<array.length; i++){
                
                array[i] = (Red)result.next();
            }            
            return array;
        }finally{
            db.close();
        }
    }
    
    //IO
    public static void addIO(String ti, String mar, String mod){
        
        int id = getNumberOfDevices() + 1;
        
        IO printer = new IO(id, ti, mar, mod, "Bueno", "Disponible");
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            db.store(printer);
        }finally{
            db.close();
        }
    }
    
    public static IO[] getIOs(){
        
        IO search = new IO(0, null, null, null, null, null);
        IO array[]= null;
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            ObjectSet result = db.queryByExample(search);
            array = new IO[result.size()];
            
            for(int i = 0; i<array.length; i++){
                
                array[i] = (IO)result.next();
            }
            
            return array;
        }finally{
            db.close();
        }
    }
    
    //USERS
    
    
    //DAMAGED    
    public static void addDamaged(Device device, String desc){
       
        int code = damageSize() + 1;
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{                   
            ObjectSet result = db.queryByExample(new Device(device.getId()));
            Device update = (Device)result.next();
            update.setEstado("Dañado");
            update.setSituacion("Prestado");
            Averia damaged = new Averia(desc, update, code);
            db.store(update);
            db.store(damaged);
            
        }finally{
            db.close();
        }
    }
    
    public static int damageSize(){
        
        Averia d = new Averia(null, null, 0);
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        
        try{
            
            List<Averia> result = db.query(new Predicate<Averia>(){
                @Override
                public boolean match(Averia et) {
                    return true;
                }
                
            });
            
            return result.size();
            
        }finally{
            db.close();
        }
    }

    public static Averia[] getDamaged(){
        
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        Averia damaged[];
        
        try{            
            List<Averia> result = db.query(new Predicate<Averia>(){
                @Override
                public boolean match(Averia et) {
                    return true;
                }
                
            });
            
            if(result.isEmpty()){
                
                return null;
            }else{
                
                int size = result.size();
                damaged = new Averia[size];

                for(int i =0; i < damaged.length; i++){
                    damaged[i] = (Averia)result.get(0);
                }
                return damaged;
            }         
        }finally{
            db.close();
        }
    } 
    
    public static Averia[] searchDamages(String s){
        
        Averia damages[];
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBNAME);
        String search = s.toLowerCase();
        
        try{
            
            List<Averia> results = db.query(new Predicate<Averia>(){
                @Override
                public boolean match(Averia et) {
                    
                    Device damaged = et.getDispositivo();
                    String marca = damaged.getMarca().toLowerCase();
                    String modelo = damaged.getModelo().toLowerCase();
                    
                    return (marca.contains(search) || modelo.contains(search));
                }                
            });
            
            if(results.isEmpty()){
                return null;
            
            }else{
                int size = results.size();
                damages = new Averia[size];
                
                for(int i = 0; i < damages.length; i++){
                    damages[i] = (Averia)results.get(i);
                }
                
                return damages;
            }
        }finally{
            db.close();
        }
    }*/
}
