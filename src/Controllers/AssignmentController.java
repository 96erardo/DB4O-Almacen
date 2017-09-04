/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Views.Assignment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author GerardoAGL
 */
public class AssignmentController extends MouseAdapter{
    
    private Assignment a;
    
    public AssignmentController(Models.Assignment assignment){
        start(assignment);
    }
    
    private void start(Models.Assignment assignment){
        
        a = new Assignment(assignment);
        
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
