/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.Introduction;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class IntroductionController extends MouseAdapter{
    
    private Introduction i;
    
    public IntroductionController(){
        start();
    }
    
    private void start(){
        
        i = new Introduction();
        
        //LISTENERS
        i.btn_start.addMouseListener(this);
        i.btn_close.addMouseListener(this);
        
        //VISIBLE
        i.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        i.setLocationRelativeTo(null);
        i.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            case "start":{
                accept(e);
            }break;
            case "close":{
                close(e);
            }
        }
    }
    
    private void accept(MouseEvent e){
        LoginController lc = new LoginController();
        i.dispose();
    }
    
    private void close(MouseEvent e){
        System.exit(0);
    }
}
