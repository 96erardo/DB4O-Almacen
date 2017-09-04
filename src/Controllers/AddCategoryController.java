/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Validations.TextField;
import DAO.CategoryDAO;
import Models.Category;
import Views.AddCategory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class AddCategoryController extends MouseAdapter {
    
    private AddCategory ac;
    
    public AddCategoryController(){
        start();
    }
    
    private void start(){
        
        ac = new AddCategory();
        ac.btn_save.addMouseListener(this);
        ac.btn_cancel.addMouseListener(this);
        ac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ac.setLocationRelativeTo(null);
        ac.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getText();
        
        switch(text){
            
            case "Guardar":{
                save(e);
            }break;
            case "Cancelar":{
                cancel(e);
            }break;
        }
    }
    
    private void save(MouseEvent e){
        
        if(TextField.filled(ac.txt_name, "Nombre")){
            
            String name = ac.txt_name.getText();
            boolean apps = ac.cb_apps.isSelected();
            
            CategoryDAO cdao = new CategoryDAO(new Category(name, apps));
            cdao.save();    
            ac.dispose();
            
        }else{
            
            ErrorController ec = new ErrorController(ac, true, "Error", "Existen campos vacios");
        }  
    }
    
    private void cancel(MouseEvent e){
        ac.dispose();
    }
}
