/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.Fault;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author GerardoAGL
 */
public class FaultController extends MouseAdapter{
    
    private Fault f;

    public FaultController(Models.Fault fault){
        start(fault);
    }
    
    private void start(Models.Fault fault){
        
        f = new Fault(fault);
        
        //LISTENERS
        f.btn_accept.addMouseListener(this);
        
        //VISIBLE
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            case "accept":{
                accept(e);
            }break;
        }
    }
    
    private void accept(MouseEvent e){
        f.dispose();
    }
}
