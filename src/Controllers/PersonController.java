/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Persona;
import Views.Person;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class PersonController extends MouseAdapter{
    
    private Person p;
    
    public PersonController(Persona person){
        start(person);
    }
    
    private void start(Persona person){
        
        p = new Person(person);
        
        //LISTENERS
        p.btn_accept.addMouseListener(this);
        
        //VISIBLE
        p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        p.setLocationRelativeTo(null);
        p.setVisible(true);                
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
        p.dispose();
    }
}
