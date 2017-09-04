/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DeviceDAO;
import DAO.FaultDAO;
import Models.Device;
import Models.Fault;
import Views.SearchFault;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author GerardoAGL
 */
public class SearchFaultController extends MouseAdapter{
    
    private SearchFault sf;
    
    public SearchFaultController(){
        start();
    }
    
    private void start(){
        
        sf = new SearchFault();
        
        //LISTENERS
        sf.btn_accept.addMouseListener(this);
        sf.btn_cancel.addMouseListener(this);
        sf.btn_info.addMouseListener(this);
        sf.btn_search.addMouseListener(this);
        
        //VISIBLE
        sf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sf.setLocationRelativeTo(null);
        sf.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            case "search fault":{
                searchFault(e);
            }break;
            case "info fault":{
                infoFault(e);
            }break;
            case "accept fault":{
                acceptFault(e);
            }break;
            case "cancel fault":{
                cancelFault(e);
            }break;
        }
    }
    
    private void searchFault(MouseEvent e){
        String name;
        sf.faults = FaultDAO.search(sf.txt_search.getText());
        sf.dlmFaults.clear();
        Device device;
        if(sf.faults != null){
            for(int i = 0; i < sf.faults.length; i++){
                device = DeviceDAO.deviceById(sf.faults[i].getDevice());
                name = "("+ sf.faults[i].getCode()+ ") " + device.getModel();
                sf.dlmFaults.addElement(name);
            }
            
        }else{
            sf.dlmFaults.addElement("No hay resultados para la busqueda");
        }
    }
    
    private void infoFault(MouseEvent e){
        int index = sf.lst_results.getSelectedIndex();
        String text = sf.lst_results.getSelectedValue();
        if(index != -1 && !text.equals("No hay resultados para la busqueda")){
            Fault fault = sf.faults[index];
            FaultController fc = new FaultController(fault);
        }        
    }
    
    private void acceptFault(MouseEvent e){
        sf.dispose();
    }
    
    private void cancelFault(MouseEvent e){
        sf.dispose();
    }
}
