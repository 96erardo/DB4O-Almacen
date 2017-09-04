/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Validations.TextField;
import DAO.DeviceDAO;
import Models.Category;
import Models.Characteristic;
import Models.Device;
import Views.AddDevice;
import Views.Decorations.Colors;
import Views.Dialogs.AddApp;
import Views.Dialogs.AddCharacteristic;
import Views.SearchDevice;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
public class EditDeviceController extends MouseAdapter{
    
    private SearchDevice sd;
    private AddDevice ed;
    private AddCharacteristic ac;
    private AddApp aa;
    private Device d;
    
    public EditDeviceController(){
        start();
    }
    
    private void start(){
        
        sd = new SearchDevice();
        
        //LISTENERS
        sd.btn_search.addMouseListener(this);
        sd.btn_info.addMouseListener(this);
        sd.btn_accept.addMouseListener(this);
        sd.btn_cancel.addMouseListener(this);
        
        //VISIBLE
        sd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sd.setLocationRelativeTo(null);
        sd.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            //MAIN
            case "search device":{
                searchDevice(e);
            }break;
            case "info device":{
                infoDevice(e);
            }break;
            case "accept device":{
                acceptDevice(e);
            }break;
            
            case "cancel device":{
                cancelDevice(e);
            }break;
            
            //MAIN 2
            case "save add":{
                saveAdd(e);
            }break;
            case "cancel add":{
                cancelAdd(e);
            }break;
            case "picture":{
                picture(e);
            }break;
            //CHARACTERISTICS
            case "removeCha":{
                removeCha(e);
            }break;
            case "addCha":{
                addCha(e);
            }break;
            case "acceptCha":{
                acceptCha(e);
            }break;
            case "cancelCha":{
                cancelCha(e);
            }break;
            
            //APPS
            case "removeApp":{
                removeApp(e);
            }break;
            case "addApp":{
                addApp(e);
            }break;
            case "acceptApp":{
                acceptApp(e);
            }break;
            case "cancelApp":{
                cancelApp(e);
            }break;
        }
    }
    
    public void searchDevice(MouseEvent e){
        String s = sd.txt_search.getText();
        sd.devices = DeviceDAO.search(s);
        sd.dlmDev.removeAllElements();
        String name;
        
        if(sd.devices != null){            
            
            for(int i = 0; i < sd.devices.length; i++){
                
                name = sd.devices[i].getBrand()+" "+sd.devices[i].getModel();
                sd.dlmDev.addElement(name);
            }            
        }else{
            sd.dlmDev.addElement("No hay resultados para la busqueda");
        }
    }
    
    public void infoDevice(MouseEvent e){
        int index = sd.lst_results.getSelectedIndex();
        String text = sd.lst_results.getSelectedValue();
        if(index != -1 && !text.equals("No hay resultados para la busqueda")){
            Device device = sd.devices[index];
            DeviceController dc = new DeviceController(device);
        }
    }
    
    public void acceptDevice(MouseEvent e){
        int index = sd.lst_results.getSelectedIndex();
        
        if(index != -1){
            
            this.d = sd.devices[index];
            ed = new AddDevice();
            ed.btn_picture.addMouseListener(this);
            ed.cha_add.addMouseListener(this);
            ed.cha_remove.addMouseListener(this);
            ed.app_add.addMouseListener(this);
            ed.app_remove.addMouseListener(this);
            ed.btn_save.addMouseListener(this);
            ed.btn_cancel.addMouseListener(this);
            
            ed.lbl_title.setText("Editar dispositivo");
            for(int i = 0; i < ed.categories.length; i++){
                if(ed.categories[i].getId() == d.getCategory())
                    ed.cb_type.setSelectedIndex(i);
            }
            ed.cb_type.setEnabled(false);
            ed.txt_brand.setText(d.getBrand());
            ed.txt_model.setText(d.getModel());
            for(int i = 0; i < d.getCharacteristics().size(); i++){
                ed.dlmCha.addElement(d.getCharacteristics().get(i).getName());
            }
            ed.characteristic = d.getCharacteristics();
            for(int i = 0; i < d.getApps().size(); i++){
                ed.dlmApp.addElement(d.getApps().get(i));
            }
            ed.apps = d.getApps();
            ed.picture = d.getPhoto();
            ed.btn_picture.setBackground(Colors.green());
            ed.btn_picture.setText("Cambiar imagen");
            
            ed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ed.setLocationRelativeTo(null);
            ed.setVisible(true);
            
            sd.dispose();
        }
    }
    
    public void cancelDevice(MouseEvent e){
        sd.dispose();
    }

    //CHARACTERISTICS
    public void addCha(MouseEvent e){
        
        ac = new AddCharacteristic(ed, true);
        ac.btn_accept.addMouseListener(this);
        ac.btn_cancel.addMouseListener(this);
        ac.setLocationRelativeTo(ed);
        ac.setVisible(true);
    }
    
    public void removeCha(MouseEvent e){
        int index = ed.lst_cha.getSelectedIndex();
        
        if(index != -1){
            
            ed.dlmCha.remove(index);
            ed.characteristic.remove(index);      
        }
    }
    
    public void acceptCha(MouseEvent e){
        if(TextField.filled(ac.txt_name, "Ej: Procesador") &&
                TextField.filled(ac.txt_value, "Ej: 2.60 Ghz") &&
                TextField.filled(ac.txt_info, "Ej: Intel i7 2330K")){
            
            String name = ac.txt_name.getText();
            String value = ac.txt_value.getText();
            String info = ac.txt_info.getText();
            
            Characteristic c = new Characteristic(name, value, info);
            ed.dlmCha.addElement(c.getName());
            ed.characteristic.add(c);
            ac.dispose();
        }else{
            
            ErrorController ec = new ErrorController(ed, true, "Error", "Existen campos vacios");
        }
    }
    
    public void cancelCha(MouseEvent e){
        ac.dispose();
    }
    
    //APPS
    public void addApp(MouseEvent e){
        if(ed.lst_app.isEnabled()){
            
            aa = new AddApp(ed, true);
            aa.btn_accept.addMouseListener(this);
            aa.btn_cancel.addMouseListener(this);
            aa.setLocationRelativeTo(null);
            aa.setVisible(true);        
        }else{
      
        }
    }
    
    public void removeApp(MouseEvent e){
        int index = ed.lst_app.getSelectedIndex();
        
        if(index != -1){
            
            ed.dlmApp.remove(index);
            ed.apps.remove(index);
        }
    }
    
    public void acceptApp(MouseEvent e){
        if(TextField.filled(aa.txt_name, "Nombre")){
            
            String name = aa.txt_name.getText();
            ed.dlmApp.addElement(name);
            ed.apps.add(name);
            aa.dispose();
        }else{
            
            ErrorController ec = new ErrorController(ed, true, "Error", "Existen campos vacios");
        }
    }
    
    public void cancelApp(MouseEvent e){
        aa.dispose();
    }
    
    //PICTURE
    public void picture(MouseEvent e){
        JFileChooser fichero = new JFileChooser();
        String file;
        String name;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagen", "jpg", "jpeg", "png");
        fichero.setFileFilter(filter);

        int answer = fichero.showOpenDialog(ed);
        
        if (answer == 0) {

            file = fichero.getSelectedFile().toString();
            name = fichero.getSelectedFile().getName();
            
            try {
                File filetitle = new File(file);
                ed.picture = ImageIO.read(filetitle);
                
                ed.btn_picture.setText(name);
                ed.btn_picture.setBackground(new Color(67, 160, 71));
                
            } catch (IOException ex) {
                ErrorController ec = new ErrorController(ed, true, "Error", "Ha ocurrido un error");
            }
        }
    }
    
    //MAIN 2
    public void saveAdd(MouseEvent e){
        if(TextField.filled(ed.txt_brand, "Marca") &&
                TextField.filled(ed.txt_model, "Modelo")){
           
            d.setBrand(ed.txt_brand.getText());
            d.setModel(ed.txt_model.getText());
            d.setCharacteristics(ed.characteristic);
            d.setApps(ed.apps);
            d.setPhoto(ed.picture);
                    
            DeviceDAO.update(d);         
            
            ed.dispose();
        }
    }
    
    public void cancelAdd(MouseEvent e ){
        ed.dispose();
    }
}
