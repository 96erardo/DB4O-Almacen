/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.Dialogs.Error;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class ErrorController extends MouseAdapter{
    
    private Error error;
    
    public ErrorController(Frame parent, Boolean b,String title, String text){
        
        start(parent, b,title, text);
    }
    
    public void start(Frame parent, Boolean b, String title, String text){
        
        error = new Error(parent, b, title, text);
        error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        error.setLocationRelativeTo(parent);
        error.btnl_accept.addMouseListener(this);
        error.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getText();
        
        switch(text){
            
            case "Aceptar":{
                error.dispose();
            }break;
        }
        
    }
}
