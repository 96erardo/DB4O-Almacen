/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DeviceDAO;
import Models.Device;
import Views.Dialogs.Confirm;
import Views.SearchDevice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class DeleteDeviceController extends MouseAdapter{
    
    private SearchDevice sd;
    private Confirm c;
    private Device d;
    
    public DeleteDeviceController(){
        start();
    }
    
    private void start(){
        
        sd = new SearchDevice();
        
        //LISTENERS
        sd.btn_search.addMouseListener(this);
        sd.btn_info.addMouseListener(this);
        sd.btn_accept.addMouseListener(this);
        sd.btn_cancel.addMouseListener(this);        
        
        //VISIBLE
        sd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sd.setLocationRelativeTo(null);
        sd.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            //MAIN
            case "search device":{
                searchDevice(e);
            }break;
            case "info device":{
                infoDevice(e);
            }break;
            case "accept device":{
                acceptDevice(e);
            }break;
            
            case "cancel device":{
                cancelDevice(e);
            }break;
            case "accept confirm":{
                acceptConfirm(e);
            }break;            
            case "cancel confirm":{
                cancelConfirm(e);
            }break;
            
        }
    }
    
    public void searchDevice(MouseEvent e){
        String s = sd.txt_search.getText();
        sd.devices = DeviceDAO.search(s);
        sd.dlmDev.removeAllElements();
        String name;
        
        if(sd.devices != null){            
            
            for(int i = 0; i < sd.devices.length; i++){
                
                name = sd.devices[i].getBrand()+" "+sd.devices[i].getModel();
                sd.dlmDev.addElement(name);
            }            
        }else{
            sd.dlmDev.addElement("No hay resultados para la busqueda");
        }
    }
    
    public void infoDevice(MouseEvent e){
        int index = sd.lst_results.getSelectedIndex();
        String text = sd.lst_results.getSelectedValue();
        if(index != -1 && !text.equals("No hay resultados para la busqueda")){
            Device device = sd.devices[index];
            DeviceController dc = new DeviceController(device);
        }
    }
    
    public void acceptDevice(MouseEvent e){
        int index = sd.lst_results.getSelectedIndex();
        
        if(index != -1){
            d = sd.devices[index];
            c = new Confirm(sd, true, "¿Seguro que desea eliminar este dispositivo?");
            c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            c.setLocationRelativeTo(sd);
            c.btn_accept.addMouseListener(this);
            c.btn_cancel.addMouseListener(this);
            c.setVisible(true);
        }
    }
    
    public void cancelDevice(MouseEvent e){
        sd.dispose();
    }

    //CONFIRM DIALOG
    public void acceptConfirm(MouseEvent e){
        d.setStored(false);
        DeviceDAO.update(d);
        sd.dispose();
    }
    
    public void cancelConfirm(MouseEvent e){
        c.dispose();
    }            
}
