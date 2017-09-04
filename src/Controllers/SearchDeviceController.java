/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DeviceDAO;
import Models.Device;
import Models.Management;
import Views.SearchDevice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author GerardoAGL
 */
public class SearchDeviceController extends MouseAdapter{
    
    private SearchDevice sd;
    
    public SearchDeviceController(){
        start();
    }
    
    private void start(){
        
        sd = new SearchDevice();
        
        //LISTENERS
        sd.btn_accept.addMouseListener(this);
        sd.btn_cancel.addMouseListener(this);
        sd.btn_info.addMouseListener(this);
        sd.btn_search.addMouseListener(this);
        
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
            
            case "accept device":{
                cancel(e);
            }break;
            case "cancel device":{
                cancel(e);
            }break;
            case "info device":{
                info(e);
            }break;
            case "search device":{
                search(e);
            }break;
        }
    }
        
    private void cancel(MouseEvent e){
        sd.dispose();
    }
    
    private void info(MouseEvent e){
        
        int index = sd.lst_results.getSelectedIndex();
        String text = sd.lst_results.getSelectedValue();
        if(index != -1 && !text.equals("No hay resultados para la busqueda")){
            Device device = sd.devices[index];
            DeviceController dc = new DeviceController(device);
        }                
    }
    
    private void search(MouseEvent e){
        
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
}
