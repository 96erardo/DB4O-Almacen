/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Validations;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author GerardoAGL
 */
public class Name implements KeyListener{
    
    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyChar();
        char num = e.getKeyChar();

        if((key < 65 || key >90) && 
                (key < 97 || key > 122) && 
                (key != 32) && 
                (key != 8)){
            
            e.consume();
            Toolkit.getDefaultToolkit().beep();            
        }  
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
