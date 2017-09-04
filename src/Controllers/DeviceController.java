/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.Decorations.Colors;
import Views.Device;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author GerardoAGL
 */
public class DeviceController extends MouseAdapter{
    
    private Device d;
    
    public DeviceController(Models.Device device){
        start(device);
    }
    
    public void start(Models.Device device){
        
        d = new Device(device);
        
        //LISTENERS
        d.btn_accept.addMouseListener(this);
        if(!device.getCharacteristics().isEmpty()){
            d.btn_cha.addMouseListener(this);
            d.btn_cha.setBackground(Colors.green());
        }
        if(!device.getApps().isEmpty()){
            d.btn_apps.addMouseListener(this);
            d.btn_apps.setBackground(Colors.green());
        }
        
        
        //VISIBLE
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            case "characteristics":{
                characteristics(e);
            }break;
            case "apps":{
                apps(e);
            }break;
            case "accept":{
                accept(e);
            }
        }
    }
    
    public void characteristics(MouseEvent e){
        CharacteristicController cc = new CharacteristicController(d.device.getCharacteristics());
    }
    
    public void apps(MouseEvent e){
        AppController ac = new AppController(d.device.getApps());
    }
    
    public void accept(MouseEvent e){
        d.dispose();
    }    
}
