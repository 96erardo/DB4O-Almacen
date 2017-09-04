/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.UserDAO;
import Views.Dialogs.Confirm;
import Views.Login;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author GerardoAGL
 */
public class DeleteUserController extends MouseAdapter{
    
    private Confirm c;
    private String username;
    private Frame frame;
    
    public DeleteUserController(String username, Frame parent, boolean modal, String question){
        start(parent, modal, question);
        this.username = username;
        this.frame = parent;
    }
    
    public void start(Frame parent, boolean modal, String question){
        
        c = new Confirm(parent, modal, question);
        
        //LISTENERS
        c.btn_accept.addMouseListener(this);
        c.btn_cancel.addMouseListener(this);
        
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
            
            case "accept confirm":{
                accept(e);
            }break;
            case "cancel confirm":{
                cancel(e);
            }break;
        }
    }
    
    private void accept(MouseEvent e){
        UserDAO dao = new UserDAO(this.username);
        dao.delete();
        c.dispose();
        frame.dispose();
        LoginController lc = new LoginController();
    }
    
    private void cancel(MouseEvent e){
        c.dispose();
    }
}
