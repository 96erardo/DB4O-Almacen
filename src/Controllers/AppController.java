/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.Apps;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class AppController extends MouseAdapter{
    
    private Apps a;
    
    public AppController(ArrayList<String> apps){
        
        start(apps);
    }
    
    private void start(ArrayList<String> apps){
        
        a = new Apps(apps);
        
        //LISTENERS
        a.btn_accept.addMouseListener(this);
        
        //VISIBLE
        a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
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
       a.dispose();
   }
}
