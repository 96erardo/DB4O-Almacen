/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.PersonDAO;
import Models.Management;
import Models.Persona;
import Views.SearchPerson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class SearchPersonController extends MouseAdapter{
    
    private SearchPerson sp;
    
    public SearchPersonController(){
        
        start();
    }
    
    private void start(){
        
        sp = new SearchPerson();
        
        //LISTENERS
        sp.btn_search.addMouseListener(this);
        sp.btn_info.addMouseListener(this);
        sp.btn_accept.addMouseListener(this);
        sp.btn_cancel.addMouseListener(this);
        
        //VISIBLE
        sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sp.setLocationRelativeTo(null);
        sp.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            case "search person":{
                search(e);
            }break;
            case "info person":{
                info(e);
            }break;
            case "accept person":{
                accept(e);
            }break;
            case "cancel person":{
                cancel(e);
            }break;
        }
    }
    
    public void search(MouseEvent e){
        
        String search = sp.txt_search.getText();
        sp.persons = PersonDAO.search(search);
        sp.dlmPerson.clear();
        
        if(sp.persons != null){
            
            for(int i = 0; i < sp.persons.length; i++){
                sp.dlmPerson.addElement(sp.persons[i].getNombre() + " " + sp.persons[i].getCedula());
            }
        }else{
            sp.dlmPerson.addElement("No hay resultados para la busqueda");
        }
    }
    
    public void info(MouseEvent e){
        int index = sp.lst_results.getSelectedIndex();
        String text = sp.lst_results.getSelectedValue();
        if(index != -1 && !text.equals("No hay resultados para la busqueda")){
            Persona person = sp.persons[index];
            PersonController pc = new PersonController(person);
        }
    }
    
    public void accept(MouseEvent e){
        sp.dispose();
    }
    
    public void cancel(MouseEvent e){
        sp.dispose();
    }
}
