/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author GerardoAGL
 */
public class Assignment {
    
    private String person;
    private int id;
    private int device;
    private String departament;
    private Date assignmentDate;
    private Date devolutionDate;
    private boolean done;

    public Assignment(String person, int device, String departament) {
        this.person = person;
        this.device = device;
        this.departament = departament;
        this.assignmentDate = new Date();
        this.devolutionDate = null;
        this.done = false;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }
 
    public Date getDate() {
        return this.assignmentDate;
    }

    public Date getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(Date devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }  
}
