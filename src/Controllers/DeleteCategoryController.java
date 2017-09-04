/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.CategoryDAO;
import DAO.PersonDAO;
import Models.Category;
import Models.Persona;
import Views.Dialogs.Confirm;
import Views.SelectCategory;
import Views.SearchPerson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class DeleteCategoryController extends MouseAdapter{
    
    private SelectCategory sc;
    private Confirm c;
    private Category category;
    
    public DeleteCategoryController(){
        
        start();
    }
    
    private void start(){
        
        sc = new SelectCategory();
        
        //LISTENERS
        sc.btn_accept.addMouseListener(this);
        sc.btn_cancel.addMouseListener(this);
        
        //VISIBLE
        sc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sc.setLocationRelativeTo(null);
        sc.setVisible(true);
        
        //OTHERS
        sc.categories = CategoryDAO.all();        
        if(sc.categories != null){            
            for(int i = 0; i < sc.categories.length; i++){
                sc.dlmCateg.addElement(sc.categories[i].getName());
            }
        }else{
            sc.dlmCateg.addElement("No existen categorias");
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            case "accept categ":{
                accept(e);
            }break;
            case "cancel categ":{
                cancel(e);
            }break;
            case "accept confirm":{
                acceptConfirm(e);
            }break;
            case "cancel confirm":{
                cancelConfirm(e);
            }break;
        }
    }
           
    public void accept(MouseEvent e){
        int index = sc.lst_results.getSelectedIndex();
        String text = sc.lst_results.getSelectedValue();
        if(index != -1 && !text.equals("No existen categorias")){
            category = sc.categories[index];
            c = new Confirm(sc, true, "¿Securo que desea eliminar esta categoria?");
            c.btn_accept.addMouseListener(this);
            c.btn_cancel.addMouseListener(this);
            c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            c.setLocationRelativeTo(sc);
            c.setVisible(true);
        }
    }
    
    public void cancel(MouseEvent e){
        sc.dispose();
    }
    
    public void acceptConfirm(MouseEvent e){
        category.setStored(false);
        CategoryDAO.update(category);
        c.dispose();
        sc.dispose();
    }
    
    public void cancelConfirm(MouseEvent e){
        sc.dispose();
    }
}
