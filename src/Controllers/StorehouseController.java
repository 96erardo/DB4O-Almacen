/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.Storehouse;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class StorehouseController extends MouseAdapter{
    
    private Storehouse s;
    
    public StorehouseController(){
        start();
    }
    
    public void start(){
        
        s = new Storehouse();
        
        //LISTENERS
        s.btn_accept.addMouseListener(this);
        
        //VISIBLE
        s.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        s.setLocationRelativeTo(null);
        s.setVisible(true);
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            case "accept":{
                accept(e);
            }
        }
    }
    
    public void accept(MouseEvent e){
        s.dispose();
    }
}
