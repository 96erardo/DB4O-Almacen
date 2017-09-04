/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Category;
import Models.Device;
import java.util.List;

/**
 *
 * @author GerardoAGL
 */
public class DeviceDAO {
    
    private Device device;
    
    public DeviceDAO(Device device){
        this.device = device;
        int id = size() + 1;
        device.setId(id);
    }
    
    public void save(){
        
        Connection db = new Connection();
        db.saveDevice(device);
        db.close();
    }
    
    public static boolean update(Device device){
        Connection db = new Connection();
        boolean answer = db.updateDevice(device);
        db.close();
        
        return answer;
    }
    
    public static boolean delete(Device device){
        Connection db = new Connection();
        boolean answer = db.updateDevice(device);
        db.close();
        
        return answer;
    }
        
    public static Device[] search(String search){
        
        Connection db = new Connection();
        Device devices[] = db.searchDevices(search);
        db.close();
        
        return devices;
    }
    
    public static Device deviceById(int id){
        Connection db = new Connection();
        Device device = db.deviceById(id);
        db.close();
        
        return device;
    }
    
    public static int size(){
        Connection db = new Connection();
        int size = db.deviceSize();
        db.close();
        
        return size;
    }
}
