/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.UserDAO;
import Views.Login;
import Views.Main;
import Views.Dialogs.Error;
import Models.Management;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class LoginController extends MouseAdapter{
    
    private Login login;
    
    public LoginController(){
        
        start();
    }
    
    public void start(){
        
        login = new Login();
        login.setLocationRelativeTo(null);
        login.btn_login.addMouseListener(this);
        login.btn_cancel.addMouseListener(this);
        login.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            case "login":{
                login(e);
            }break;
            case "close":{
                close(e);
            }break;
        }
    }
    
   private void login(MouseEvent e){
       String username = login.txt_user.getText();
        String password = login.txt_pass.getText();
        if(UserDAO.login(username, password)){
            login.loader.show();
            login.login.hide();
            
            new Timer().schedule(new TimerTask(){
                @Override
                public void run() {
                    
                    MainController main = new MainController(username);
                    login.dispose();
                }
            }, 1000*3);
        }else{
            
            ErrorController ec = new ErrorController(login, true, "Error", "EL nombre de usuario o contraseña con incorrectos");            
        }
    }
   
   private void close(MouseEvent e){
       System.exit(0);
   }
}
    

