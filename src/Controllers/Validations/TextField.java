/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Validations;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author GerardoAGL
 */
public class TextField {
    
    public static boolean filled(JTextField field, String hint){
        
        String text = field.getText();        
        if(text.equals(hint) || text.equals(""))
            return false;
        else
            return true;        
    }
    
    public static boolean filled(JTextArea field, String hint){
        
        String text = field.getText();        
        if(text.equals(hint) || text.equals(""))
            return false;
        else
            return true;        
    }
}
