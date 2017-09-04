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
public class Fault {
    
    private int code;
    private int cost;
    private int device;
    private String info;
    private String solution;
    private Date faultDate;
    private Date solutionDate;

    private boolean fixed;

    public Fault(int device, String info) {
        this.device = device;
        this.info = info;
        faultDate = new Date();
    }    

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setSolutionDate(Date solutionDate) {
        this.solutionDate = solutionDate;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
    
    public int getCost() {
        return cost;
    }

    public String getInfo() {
        return info;
    }

    public String getSolution() {
        return solution;
    }

    public Date getFaultDate() {
        return faultDate;
    }

    public Date getSolutionDate() {
        return solutionDate;
    }

    public int getDevice() {
        return device;
    }

    public int getCode() {
        return code;
    }

    public boolean isFixed() {
        return fixed;
    }
    
    

}
