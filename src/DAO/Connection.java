/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Assignment;
import Models.Category;
import Models.Device;
import Models.Fault;
import Models.Multimedia;
import Models.Persona;
import Models.Provider;
import Models.Usuario;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import java.util.List;

/**
 *
 * @author GerardoAGL
 */
public class Connection {

    private ObjectContainer db;
    private final String dbname = "Storehouse.db4o";

    public Connection() {
        this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Storehouse.db4o");
    }

    //PERSONS
    public void savePerson(Persona person) {
        this.db.store(person);
    }
    
    public boolean updatePerson(Persona person){
        String cedula = person.getCedula();
        List<Persona> result = db.query(new Predicate<Persona>(){
            @Override
            public boolean match(Persona et) {
                return et.getCedula().equals(cedula);
            }            
        });
        
        if(result.isEmpty()){
            return false;
        }else{
            Persona toUpdate = result.get(0);
            toUpdate.setCedula(person.getCedula());
            toUpdate.setNombre(person.getNombre());
            toUpdate.setPicture(person.getPicture());
            toUpdate.setStored(person.isStored());
            db.store(toUpdate);
            
            return true;
        }        
    }
    
    public boolean deletePerson(){

        List<Persona> result = db.query(new Predicate<Persona>(){
            @Override
            public boolean match(Persona et) {
                return true;
            }            
        });
        
        if(result.isEmpty()){
            return false;
        }else{
            Persona toDelete = result.get(0);
            db.delete(toDelete);
            
            return true;
        }
    }
    
    public Persona personById(String id) {
        
        List<Persona> result = db.query(new Predicate<Persona>() {
            @Override
            public boolean match(Persona et) {
                return et.getCedula().equals(id);
            }
        });

        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }
    
    public boolean isPerson(String id) {
        
        List<Persona> result = db.query(new Predicate<Persona>() {
            @Override
            public boolean match(Persona et) {
                return et.getCedula().equals(id) && et.isStored();
            }
        });

        if (result.isEmpty())
            return false;
        else
            return true;
    }

    public Persona[] searchPersons(String s) {
        
        String search = s.toLowerCase();

        List<Persona> p = db.query(new Predicate<Persona>() {
            @Override
            public boolean match(Persona et) {

                String name = et.getNombre().toLowerCase();
                String id = et.getCedula().toLowerCase();

                return ((name.contains(search) || id.contains(search)) && et.isStored());
            }

        });
        if (p.isEmpty()) {

            return null;
        } else {

            Persona persons[] = new Persona[p.size()];

            for (int i = 0; i < persons.length; i++) {
                persons[i] = p.get(i);
            }

            return persons;
        }
    }
    
    public int personSize() {
        
        List<Persona> p = db.query(new Predicate<Persona>() {
            @Override
            public boolean match(Persona et) {
                return true;
            }
        });
        return p.size();
    }

    //USERS
    public void saveUser(Usuario user) {
        this.db.store(user);
    }

    public boolean login(String username, String pass) {

        Usuario usuario = new Usuario(username, pass, null, null);
        try {
            ObjectSet result = db.queryByExample(usuario);
            if (result.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } finally {
            db.close();
        }
    }

    public Usuario searchUser(String username) {

        Usuario user = new Usuario(username, null, null, null);

        ObjectSet result = db.queryByExample(user);
        if (result.isEmpty()) {
            return null;
        } else {
            return (Usuario) result.next();
        }
    }

    public boolean deleteUser(Usuario user) {

        String username = user.getUsuario();
        List<Usuario> result = db.query(new Predicate<Usuario>(){
            @Override
            public boolean match(Usuario et) {
                return et.getUsuario().equals(username);
            }            
        });
        if(result.isEmpty())
            return false;
        else{
            Usuario toDelete = result.get(0);
            db.delete(toDelete);
            return true;
        }
    }

    public boolean updateUser(Usuario user, String name, String id, String username) {

        Usuario search = new Usuario(user.getUsuario(), null, null, null);

        ObjectSet result = db.queryByExample(search);
        if (result.isEmpty()) {
            return false;
        } else {
            Usuario toUpdate = (Usuario) result.next();
            toUpdate.setNombre(name);
            toUpdate.setCedula(id);
            toUpdate.setUsuario(username);
            db.store(toUpdate);
            return true;
        }
    }

    public boolean updateUser(Usuario user, String name, String id, String username, String pass) {

        Usuario search = new Usuario(user.getUsuario(), null, null, null);

        ObjectSet result = db.queryByExample(search);
        if (result.isEmpty()) {
            return false;
        } else {
            Usuario toUpdate = (Usuario) result.next();
            toUpdate.setNombre(name);
            toUpdate.setCedula(id);
            toUpdate.setUsuario(username);
            toUpdate.setClave(pass);
            db.store(toUpdate);
            return true;
        }
    }
    
    //CATEGORY
    public void saveCategory(Category cat) {
        this.db.store(cat);
    }
    
    public Category categoryById(int id){
        
        List<Category> c = db.query(new Predicate<Category>() {
            @Override
            public boolean match(Category et) {
                return et.getId() == id;
            }
        });
        
        if(c.isEmpty())
            return null;
        else        
            return c.get(0);
    }
    
    public boolean updateCategory(Category category){
        int id = category.getId();        
        List<Category> search = db.query(new Predicate<Category>(){
            @Override
            public boolean match(Category et) {
                
                return (et.getId() == id);
            }            
        });
        
        if(search.isEmpty()){
           return false;            
        }else{
            
            Category toUpdate = search.get(0);
            toUpdate.setName(category.getName());
            toUpdate.setApps(category.hasApps());
            toUpdate.setStored(category.isStored());
            db.store(toUpdate);
            
            return true;
        }
    }
    
    public Category[] allCategories() {

        List<Category> c = db.query(new Predicate<Category>() {
            @Override
            public boolean match(Category et) {
                return et.isStored();
            }
        });
        
        if(c.isEmpty()){
            return null;
        }else{
            Category categories[] = new Category[c.size()];
            for(int i = 0; i < c.size(); i++){
                categories[i] = c.get(i);
            }
            
            return categories;
        }
    }
    
    public int availableCategorySize(){
        List<Category> results = db.query(new Predicate<Category>() {
            @Override
            public boolean match(Category et) {
                return et.isStored();
            }
        });
        return results.size();
    }
    
    public int categorySize() {
        List<Category> results = db.query(new Predicate<Category>() {
            @Override
            public boolean match(Category et) {
                return true;
            }
        });
        return results.size();
    }
    
    public boolean deleteCategory(){

        List<Category> result = db.query(new Predicate<Category>(){
            @Override
            public boolean match(Category et) {
                return true;
            }            
        });
        
        if(result.isEmpty()){
            return false;
        }else{
            Category toDelete = result.get(0);
            db.delete(toDelete);
            
            return true;
        }
    }
    
    //DEVICES
    public void saveDevice(Device device) {
        db.store(device);
    }
    
    public Device deviceById(int id){
        List<Device> result = db.query(new Predicate<Device>() {
            @Override
            public boolean match(Device et) {
                return et.getId() == id;
            }
        });

        if (result.isEmpty())
            return null;
        else
            return result.get(0);
        
    }
    
    public boolean updateDevice(Device device){
        
        int id = device.getId();        
        List<Device> search = db.query(new Predicate<Device>(){
            @Override
            public boolean match(Device et) {
                
                return (et.getId() == id);
            }            
        });
        
        if(search.isEmpty()){
           return false;            
        }else{
            
            Device toUpdate = search.get(0);
            toUpdate.setBrand(device.getBrand());
            toUpdate.setModel(device.getModel());
            toUpdate.setCharacteristics(device.getCharacteristics());
            toUpdate.setApps(device.getApps());
            toUpdate.setPhoto(device.getPhoto());
            toUpdate.setCondition(device.getCondition());
            toUpdate.setStatus(device.getStatus());
            toUpdate.setStored(device.isStored());
            db.store(toUpdate);
            
            return true;
        }
    }
    
    public boolean deleteDevice(){
               
        List<Device> search = db.query(new Predicate<Device>(){
            @Override
            public boolean match(Device et) {
                
                return true;
            }
        });
        
        if(search.isEmpty()){
           return false;
            
        }else{
            
            Device toDelete = search.get(0);
            db.delete(toDelete);
            
            return true;
        }
    }
       
    public Device[] searchDevices(String s) {

        String search = s.toLowerCase();

        List<Device> d = db.query(new Predicate<Device>() {
            @Override
            public boolean match(Device et) {

                String marca = et.getBrand().toLowerCase();
                String modelo = et.getModel().toLowerCase();

                return ((marca.contains(search) || modelo.contains(search)) && et.isStored());
            }

        });
        if (d.isEmpty()) {

            return null;
        } else {

            Device devices[] = new Device[d.size()];

            for (int i = 0; i < devices.length; i++) {
                devices[i] = d.get(i);
            }

            return devices;
        }
    }
    
    public int deviceSize(){
        List<Device> result = db.query(new Predicate<Device>() {
            @Override
            public boolean match(Device et) {
                return true;
            }
        });

        return result.size();
    }
    
    //ASSIGNMENTS
    public void saveAssignment(Assignment assignment){
        db.store(assignment);
    }
    
    public boolean updateAssignment(Assignment assignment){
        
        int id = assignment.getId();        
        List<Assignment> search = db.query(new Predicate<Assignment>(){
            @Override
            public boolean match(Assignment et) {
                
                return (et.getId() == id);
            }            
        });
        
        if(search.isEmpty()){
           return false;            
        }else{
            
            Assignment toUpdate = search.get(0);
            toUpdate.setPerson(assignment.getPerson());
            toUpdate.setDevice(assignment.getDevice());
            toUpdate.setDepartament(assignment.getDepartament());
            toUpdate.setDevolutionDate(assignment.getDevolutionDate());
            toUpdate.setDone(assignment.isDone());
            db.store(toUpdate);
            
            return true;
        }
    }
    
    public Assignment assignmentById(int id){
        
        List<Assignment> result = db.query(new Predicate<Assignment>() {
            @Override
            public boolean match(Assignment et) {
                return et.getId() == id;
            }
        });

        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }
    
    public Assignment assignmentByDevice(Device device){
        
        int id = device.getId();        
        List<Assignment> result = db.query(new Predicate<Assignment>() {
            @Override
            public boolean match(Assignment et) {
                return et.getDevice()==id && !et.isDone();
            }
        });

        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }
    
    public int assignmentSize(){
        List<Assignment> result = db.query(new Predicate<Assignment>() {
            @Override
            public boolean match(Assignment et) {
                return true;
            }
        });

        return result.size();
    }
    
    public Assignment[] searchAssignment(String s){
        
        String search = s.toLowerCase();

        List<Assignment> a = db.query(new Predicate<Assignment>() {
            @Override
            public boolean match(Assignment et) {
                return (search.contains(et.getId()+""));
            }
        });        
        if(a.isEmpty()) {
            return null;
        }else{

            Assignment assignments[] = new Assignment[a.size()];

            for (int i = 0; i < assignments.length; i++) {
                assignments[i] = a.get(i);
            }
            return assignments;
        }
    }
    
    public boolean deleteAssignment(){
        
        List<Assignment> search = db.query(new Predicate<Assignment>(){
            @Override
            public boolean match(Assignment et) {                
                return true;
            }            
        });
        
        if(search.isEmpty()){
           return false;            
        }else{
            
            Assignment toDelete = search.get(0);
            db.delete(toDelete);            
            return true;
        }
    }
    
    //FAULT
    public void saveFault(Fault fault){
        db.store(fault);
    }
    
    public boolean updateFault(Fault fault){
        
        int id = fault.getCode();
        List<Fault> search = db.query(new Predicate<Fault>(){
            @Override
            public boolean match(Fault et) {
                
                return (et.getCode() == id);
            }            
        });
        
        if(search.isEmpty()){
           return false;            
        }else{
            
            Fault toUpdate = search.get(0);
            toUpdate.setCost(fault.getCost());
            toUpdate.setFixed(fault.isFixed());
            toUpdate.setSolution(fault.getSolution());
            toUpdate.setInfo(fault.getInfo());
            toUpdate.setSolutionDate(fault.getSolutionDate());
            db.store(toUpdate);
            
            return true;
        }
    }
    
    public Fault faultById(int id){
        List<Fault> result = db.query(new Predicate<Fault>() {
            @Override
            public boolean match(Fault et) {
                return et.getCode() == id;
            }
        });

        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }
    
    public Fault[] searchFault(String s){
        
        String search = s.toLowerCase();

        List<Fault> f = db.query(new Predicate<Fault>() {
            @Override
            public boolean match(Fault et) {

                String id = ""+et.getCode();

                return (id.contains(search));
            }
        });
        
        if (f.isEmpty()) {
            return null;
            
        } else {
            Fault faults[] = new Fault[f.size()];

            for (int i = 0; i < faults.length; i++) {
                faults[i] = f.get(i);
            }

            return faults;
        }
    }
    
    public int faultSize(){
        
        List<Fault> f = db.query(new Predicate<Fault>(){
            @Override
            public boolean match(Fault et) {
                return true;
            }
            
        });
        
        return f.size();
    }
    
    public int totalCost(){

        List<Fault> f = db.query(new Predicate<Fault>() {
            @Override
            public boolean match(Fault et) {
                return et.isFixed();
            }
        });
        
        if (f.isEmpty()) {
            return 0;
            
        } else {
            int costs = 0;

            for (int i = 0; i < f.size(); i++) {
                costs = f.get(i).getCost();
            }
            return costs;
        }
    }
    
    //PROVIDER
    public void saveProvider(Provider provider){
        db.store(provider);
    }
    
    public Provider providerById(int id){
        List<Provider> result = db.query(new Predicate<Provider>() {
            @Override
            public boolean match(Provider et) {
                return et.getId() == id;
            }
        });

        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }
    
    public Provider[] searchProvider(String s){
        
        String search = s.toLowerCase();

        List<Provider> results = db.query(new Predicate<Provider>() {
            @Override
            public boolean match(Provider et) {

                String name = et.getName().toLowerCase();
                return (name.contains(search));
            }
        });
        
        if (results.isEmpty()) {
            return null;
            
        } else {
            Provider provider[] = new Provider[results.size()];
            for (int i = 0; i < provider.length; i++) {
                provider[i] = results.get(i);
            }
            return provider;
        }
    }
    
    public int providerSize(){
        
        List<Fault> f = db.query(new Predicate<Fault>(){
            @Override
            public boolean match(Fault et) {
                return true;
            }
            
        });
        
        return f.size();
    }
    
    public boolean deleteProvider(Provider provider){
        
        int id = provider.getId();
        
        List<Provider> search = db.query(new Predicate<Provider>(){
            @Override
            public boolean match(Provider et) {                
                return (et.getId() == id);
            }            
        });
        
        if(search.isEmpty()){
           return false;            
        }else{
            
            Provider toDelete = search.get(0);
            db.delete(toDelete);            
            return true;
        }
    }
    
    //MULTIMEDIA
    public void saveMultimedia(Multimedia multimedia){
        db.store(multimedia);
    }
    
    public int multimediaSize(){
        List<Multimedia> m = db.query(new Predicate<Multimedia>(){
            @Override
            public boolean match(Multimedia et) {
                return true;
            } 
        });        
        return m.size();
    }
    
    public Multimedia[] searchMultimedia(String s){
        String search = s.toLowerCase();

        List<Multimedia> results = db.query(new Predicate<Multimedia>() {
            @Override
            public boolean match(Multimedia et) {
                String name = et.getName().toLowerCase();
                return (name.contains(search));
            }
        });
        
        if (results.isEmpty())
            return null;            
        else {
            Multimedia multimedias[] = new Multimedia[results.size()];
            for (int i = 0; i < multimedias.length; i++) {
                multimedias[i] = results.get(i);
            }
            return multimedias;
        }
    }
    
    //DB
    public void close() {
        this.db.close();
    }
}
