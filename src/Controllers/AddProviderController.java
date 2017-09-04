/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Validations.TextField;
import DAO.ProviderDAO;
import Models.Provider;
import Views.Dialogs.AddProvider;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class AddProviderController extends MouseAdapter{
    
    private AddProvider ap;
    private Frame parent;
    
    public AddProviderController(Frame parent, boolean modal){
        start(parent, modal);
        this.parent = parent;
    }
    
    private void start(Frame parent, boolean modal){
        
        ap = new AddProvider(parent, modal);
        
        //LISTENERS
        ap.btn_accept.addMouseListener(this);
        ap.btn_cancel.addMouseListener(this);
                
        //VISIBLE
        ap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ap.setLocationRelativeTo(null);
        ap.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            //FAULT
            case "accept provider":{
                acceptProvider(e);
            }break;
            case "cancel provider":{
                cancelProvider(e);
            }break;
        }       
    }
    
    private void acceptProvider(MouseEvent e){
        
        if(TextField.filled(ap.txt_name, "Nombre") &&
                TextField.filled(ap.txt_phone, "Nro de telefono")){
            
            String name = ap.txt_name.getText();
            String phone = ap.txt_phone.getText();
            
            Provider provider = new Provider(name, phone);
            ProviderDAO dao = new ProviderDAO(provider);
            dao.save();
            ap.dispose();
        }else{ 
            ErrorController ec = new ErrorController(parent, true, "Error", "Existen campos vacios");
        }
    }
    
    private void cancelProvider(MouseEvent e){
        ap.dispose();
    }
}
