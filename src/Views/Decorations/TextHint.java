/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Decorations;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author GerardoAGL
 */
public class TextHint implements FocusListener{
    
    private String hint;
    private JTextField field;
    private JTextArea area;
    
    public TextHint(JTextField field, String hint){
        this.hint = hint;
        this.field = field;
        this.area = null;
    }
    
    public TextHint(JTextArea area, String hint){
        this.hint = hint;
        this.field = null;
        this.area = area;
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        String text;
                
        if(field == null)
             text = area.getText();
        else
            text = field.getText();
        
        if(text.equals("") || text.equals(hint)){
            if(field == null){
               area.setText("");
               area.setForeground(Color.BLACK);
            }
            else{
                field.setText("");
                field.setForeground(Color.BLACK);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        String text;
                
        if(field == null)
             text = area.getText();
        else
            text = field.getText();
        
        if(text.equals("")){
            if(field == null){
               area.setText(hint);
               area.setForeground(new Color(102, 102, 102));
            }
            else{
                field.setText(hint);
                field.setForeground(new Color(102, 102, 102));
            }
        }
    }
    
}
