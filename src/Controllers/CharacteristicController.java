/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Characteristic;
import Views.Characteristics;
import Views.Dialogs.Info;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author GerardoAGL
 */
public class CharacteristicController extends MouseAdapter{
    
    private Characteristics c;
    
    public CharacteristicController(ArrayList<Characteristic> charcacteristics){
        
        start(charcacteristics);
    }
    
    private void start(ArrayList<Characteristic> charcacteristics){
        
        c = new Characteristics(charcacteristics);
        
        //LISTENERS
        c.btn_accept.addMouseListener(this);
        c.btn_info.addMouseListener(this);
        
        //VISIBLE
        c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        c.setLocationRelativeTo(null);
        c.setVisible(true);
    }
    
   @Override
   public void mouseClicked(MouseEvent e){
       
       JLabel button = (JLabel)e.getSource();
       String text = button.getToolTipText();
       
       switch(text){
           
           case "accept":{
               accept(e);
           }break;
           case "info":{
               info(e);
           }break;
       }
   }
   
   private void accept(MouseEvent e){
       c.dispose();
   }
   
   private void info(MouseEvent e){
       int index = c.lst_char.getSelectedIndex();
       
       if(index != 1){
           Info i = new Info(c, true, c.characteristics.get(index).getInfo());
           i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           i.setLocationRelativeTo(null);
           i.setVisible(true);
       }
   }
}
