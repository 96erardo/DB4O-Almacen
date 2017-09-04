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
public class NumberDouble implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        
        int key = e.getKeyChar();
        if((key < 48 || key >57) &&
                (key != 8) &&
                (key != 46)){
            
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
