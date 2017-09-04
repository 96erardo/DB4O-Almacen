/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Validations.TextField;
import DAO.AssignmentDAO;
import DAO.DeviceDAO;
import DAO.FaultDAO;
import Models.Assignment;
import Models.Device;
import Models.Fault;
import Views.AddFault;
import Views.Decorations.Colors;
import Views.SearchDevice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author GerardoAGL
 */
public class AddFaultController extends MouseAdapter{
    
    private AddFault af;
    private SearchDevice sd;
    
    public AddFaultController(){
        start();
    }
    
    private void start(){
        
        af = new AddFault();
        
        //LISTENERS
        af.btn_device.addMouseListener(this);
        af.btn_accept.addMouseListener(this);
        af.btn_cancel.addMouseListener(this);
                
        //VISIBLE
        af.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        af.setLocationRelativeTo(null);
        af.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            //FAULT
            case "device":{
                device(e);
            }break;
            case "accept fault":{
                acceptFault(e);
            }break;
            case "cancel fault":{
                cancelFault(e);
            }break;
            
            //SEARCH DEVICE
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
        }       
    }
    
    private void device(MouseEvent e){
        
        sd = new SearchDevice();
        sd.btn_search.addMouseListener(this);
        sd.btn_info.addMouseListener(this);
        sd.btn_accept.addMouseListener(this);
        sd.btn_cancel.addMouseListener(this);
        sd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sd.setLocationRelativeTo(null);
        sd.setVisible(true);
    }
    
    private void acceptFault(MouseEvent e){
        
        if(af.device != null &&
                TextField.filled(af.txt_info, "Ej: No enciende la pantalla")){
            
            Assignment assign = AssignmentDAO.assignmentByDevice(af.device);
            if(assign != null) {
                System.out.println("SI");
                assign.setDevolutionDate(new Date());
                assign.setDone(true);
                AssignmentDAO.update(assign);
            }
            
            af.info = af.txt_info.getText();
            af.device.setCondition("Dañado");
            af.device.setStatus("Ocupado");
            Fault fault = new Fault(af.device.getId(), af.info);
            
            
            FaultDAO dao = new FaultDAO(fault);
            DeviceDAO.update(af.device);
            dao.save();
            
            af.dispose();
        }
    }
    
    private void cancelFault(MouseEvent e){
        af.dispose();
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
            af.device = sd.devices[index];
            sd.dispose();
            af.lbl_device.setText(af.device.getBrand() + " " + af.device.getModel());
            af.btn_device.setBackground(Colors.green());
        }
    }
    
    public void cancelDevice(MouseEvent e){
        af.device = null;
        af.lbl_device.setText("Buscar dispositivo");
        af.btn_device.setBackground(Colors.grey());
        sd.dispose();
    }
}
