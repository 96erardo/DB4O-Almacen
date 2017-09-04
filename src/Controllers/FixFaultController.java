/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Validations.TextField;
import DAO.DeviceDAO;
import DAO.FaultDAO;
import Models.Device;
import Models.Fault;
import Views.FixFault;
import Views.SearchFault;
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
public class FixFaultController extends MouseAdapter{
    
    private SearchFault sf;
    private FixFault ff;
    private Fault fault;
    
    public FixFaultController(){
        start();
    }
    
    private void start(){
        
        sf = new SearchFault();
        
        //LISTENERS
        sf.btn_search.addMouseListener(this);
        sf.btn_info.addMouseListener(this);
        sf.btn_accept.addMouseListener(this);
        sf.btn_cancel.addMouseListener(this);
        
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
            case "accept fix":{
                acceptFix(e);
            }break;
            case "cancel fix":{
                cancelFix(e);
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
        
        int index = sf.lst_results.getSelectedIndex();
        String text = sf.lst_results.getSelectedValue();
        
        if(index != -1 && !text.equals("No hay resultados para la busqueda")){
            this.fault = sf.faults[index];
            if(!fault.isFixed()){
                ff = new FixFault(fault);
                ff.btn_accept.addMouseListener(this);
                ff.btn_cancel.addMouseListener(this);
                ff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ff.setLocationRelativeTo(sf);
                ff.setVisible(true);
                sf.dispose();
            }else{
                ErrorController ec = new ErrorController(sf, true, "Error", "Este reporte ya fué solucionado");
            }
        }
    }

    private void cancelFault(MouseEvent e){
        sf.dispose();
    }
    
    private void acceptFix(MouseEvent e){
        if(TextField.filled(ff.txt_cost, "Costo de reparación (Bsf)") &&
                TextField.filled(ff.txt_solution, "Ej: se cambió el disco duro")){
            
            Device device = DeviceDAO.deviceById(this.fault.getDevice());
            int cost = Integer.parseInt(ff.txt_cost.getText());
            String solution = ff.txt_solution.getText();
            
            this.fault.setFixed(true);
            this.fault.setCost(cost);
            this.fault.setSolution(solution);
            this.fault.setSolutionDate(new Date());
            device.setCondition("Funcional");
            device.setStatus("Disponible");
            
            DeviceDAO.update(device);
            FaultDAO.update(this.fault);
            
            ff.dispose();
        }
    }
    
    private void cancelFix(MouseEvent e){
        ff.dispose();
    }
}
