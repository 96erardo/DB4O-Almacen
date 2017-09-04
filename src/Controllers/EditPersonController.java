/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Validations.TextField;
import DAO.PersonDAO;
import Models.Persona;
import Views.AddPerson;
import Views.Decorations.Colors;
import Views.SearchPerson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author GerardoAGL
 */
public class EditPersonController extends MouseAdapter{
    
    private SearchPerson sp;
    private AddPerson ep;
    
    public EditPersonController(){
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
                searchPerson(e);
            }break;
            case "info person":{
                infoSearch(e);
            }break;
            case "accept person":{
                acceptSearch(e);
            }break;
            case "cancel person":{
                cancelSearch(e);
            }break;
            case "photo person":{
                photoPerson(e);
            }break;
            case "accept add":{
                acceptPerson(e);
            }break;
            case "cancel add":{
                cancelPerson(e);
            }break;
        }          
    }
    
    public void searchPerson(MouseEvent e){
        
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
    
    public void infoSearch(MouseEvent e){
        int index = sp.lst_results.getSelectedIndex();
        String text = sp.lst_results.getSelectedValue();
        if(index != -1 && !text.equals("No hay resultados para la busqueda")){
            Persona person = sp.persons[index];
            PersonController pc = new PersonController(person);
        }
    }
    
    public void acceptSearch(MouseEvent e){
        int index = sp.lst_results.getSelectedIndex();
        String text = sp.lst_results.getSelectedValue();
        if(index != -1 && !text.equals("No hay resultados para la busqueda")){
            ep = new AddPerson();
            ep.btn_photo.addMouseListener(this);
            ep.btn_accept.addMouseListener(this);
            ep.btn_cancel.addMouseListener(this);
            ep.setLocationRelativeTo(null);
            ep.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ep.setVisible(true);
            sp.dispose();
            
            Persona person = sp.persons[index];
            ep.txt_name.setText(person.getNombre());
            ep.txt_id.setText(person.getCedula());
            ep.photo = person.getPicture();
            if(ep.photo != null){
                ep.btn_photo.setBackground(Colors.green());
            }else{
                ep.btn_photo.setText("No tiene foto");
            }
        }
    }
    
    public void cancelSearch(MouseEvent e){
        sp.dispose();
    }
    
    private void photoPerson(MouseEvent e){
        JFileChooser fichero = new JFileChooser();
        String file;
        String name;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagen", "jpg", "jpeg", "png");
        fichero.setFileFilter(filter);

        int answer = fichero.showOpenDialog(ep);
        
        if (answer == 0) {

            file = fichero.getSelectedFile().toString();
            name = fichero.getSelectedFile().getName();
            
            try {
                File filetitle = new File(file);
                ep.photo = ImageIO.read(filetitle);
                
                ep.btn_photo.setText(name);
                ep.btn_photo.setBackground(Colors.green());
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            
            ep.photo = null;
            ep.btn_photo.setText("Foto");
            ep.btn_photo.setBackground(Colors.grey());
        }      
    }
    
    private void acceptPerson(MouseEvent e){
        if(TextField.filled(ep.txt_name, "Nombre y apellido") &&
                TextField.filled(ep.txt_id, "Nro de cédula")){
            
            String name = ep.txt_name.getText();
            String id = ep.txt_id.getText();
            
            Persona person = new Persona(name, id, ep.photo);
            PersonDAO dao = new PersonDAO(person);
            dao.update();
            ep.dispose();
        }
    }
    
    private void cancelPerson(MouseEvent e){
        ep.dispose();
    }
}
