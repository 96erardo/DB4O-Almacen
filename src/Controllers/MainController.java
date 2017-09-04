/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.CategoryDAO;
import Views.Dialogs.AddUser;
import Views.Dialogs.DeleteUser;
import Views.Dialogs.EditUser;
import Views.Main;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class MainController extends MouseAdapter{
    
    private Main main;
    private String user;
    
    public MainController(String user){
        start(user);
    }
    
    private void start(String user){
        
        this.user = user;
        main = new Main(user);
        //main.setExtendedState(JFrame.MAXIMIZED_BOTH);        
        main.setLocationRelativeTo(null);
        main.lbl_users.addMouseListener(this);
        main.lbl_devices.addMouseListener(this);
        main.lbl_reports.addMouseListener(this);
        main.lbl_contacts.addMouseListener(this);
        main.lbl_storehouse.addMouseListener(this);
        main.lbl_logout.addMouseListener(this);
        main.lbl_op11.addMouseListener(this);
        main.lbl_op12.addMouseListener(this);
        main.lbl_op13.addMouseListener(this);
        main.lbl_op14.addMouseListener(this);
        main.lbl_op21.addMouseListener(this);
        main.lbl_op22.addMouseListener(this);
        main.lbl_op23.addMouseListener(this);
        main.lbl_op24.addMouseListener(this);
        main.lbl_op25.addMouseListener(this);
        main.lbl_op26.addMouseListener(this);
        main.lbl_op27.addMouseListener(this);
        main.lbl_op28.addMouseListener(this);
        main.lbl_op31.addMouseListener(this);
        main.lbl_op32.addMouseListener(this);
        main.lbl_op33.addMouseListener(this);
        main.lbl_op41.addMouseListener(this);
        main.lbl_op42.addMouseListener(this);
        main.lbl_op43.addMouseListener(this);
        main.lbl_op51.addMouseListener(this);
                
        main.show();        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        JLabel button = (JLabel)e.getSource();
        String text = button.getToolTipText();
        
        switch(text){
            
            case "user":{
                pnl_users(e);
            }break;
            case "devices":{
                pnl_devices(e);
            }break;
            case "reports":{
                pnl_reports(e);
            }break;
            case "contacts":{
                pnl_contacts(e);
            }break;
            case "storehouse":{
                pnl_others(e);
            }break;
            case "logout":{
                logout(e);
            }break;
            case "add person":{
                addPerson(e);
            }break;
            case "info person":{
                infoPerson(e);
            }break;
            case "edit person":{
                editPerson(e);
            }break;
            case "delete person":{
                deletePerson(e);
            }break;
            case "search device":{
                seeDevices(e);
            }break;
            case "add device":{
                addDevice(e);
            }break;
            case "edit device":{
                editDevice(e);
            }break;
            case "delete device":{
                deleteDevice(e);
            }break;
            case "assign device":{
                assignDevice(e);
            }break;
            case "end assign":{
                endAssign(e);
            }break;
            case "add category":{
                addCategory(e);
            }break;
            case "delete category":{
                deleteCategory(e);
            }break;
            case "report fault":{
                reportFault(e);
            }break;
            case "fixed fault":{
                fixedFault(e);
            }break;
            case "search fault":{
                searchFault(e);
            }break;
            case "add provider":{
                addProvider(e);
            }break;
            case "delete provider":{
                deleteProvider(e);
            }break;
            case "search provider":{
                searchProvider(e);
            }break;
            case "info storehouse":{
                infoStorehouse(e);
            }                
        }
    }
    
    private void pnl_users(MouseEvent e){
        main.unselectButtons();
        main.selectButton(main.lbl_users);
        main.hideAll();
        main.spnl_user.show();
    }
    
    private void pnl_devices(MouseEvent e){
        main.unselectButtons();
        main.selectButton(main.lbl_devices);
        main.hideAll();
        main.spnl_device.show();
    }
    
    private void pnl_reports(MouseEvent e){
        main.unselectButtons();
        main.selectButton(main.lbl_reports);
        main.hideAll();
        main.spnl_reports.show();
    }
    
    private void pnl_contacts(MouseEvent e){
        main.unselectButtons();
        main.selectButton(main.lbl_contacts);
        main.hideAll();
        main.spnl_contacts.show();
    }
    
    private void pnl_others(MouseEvent e){
        main.unselectButtons();
        main.selectButton(main.lbl_storehouse);
        main.hideAll();
        main.spnl_storehouse.show();
    }
    
    private void logout(MouseEvent e){
        main.dispose();
        LoginController lc = new LoginController();
    }
    
    private void addPerson(MouseEvent e){
        AddPersonController apc = new AddPersonController();
    }
    
    private void infoPerson(MouseEvent e){
        SearchPersonController spc = new SearchPersonController();
    }
    
    private void editPerson(MouseEvent e){
        EditPersonController epc = new EditPersonController();
    }
    
    private void deletePerson(MouseEvent e){
        DeletePersonController dpc = new DeletePersonController();
    }
        
    private void seeDevices(MouseEvent e){
        SearchDeviceController sdc = new SearchDeviceController();
    }
    
    private void addDevice(MouseEvent e){
        int categories = CategoryDAO.availableSize();
        AddDeviceController adc;
        ErrorController ec;
        if(categories > 0)
            adc = new AddDeviceController(main);        
        else{
            ec = new ErrorController(main, true, "Error", "No existen categorias, agregue una para continuar");
        }
    }
    
    private void assignDevice(MouseEvent e){
        AssignDeviceController adc = new AssignDeviceController();
    }
    
    private void endAssign(MouseEvent e){
        EndAssignController eac = new EndAssignController();
    }
    
    private void editDevice(MouseEvent e){
        EditDeviceController edc = new EditDeviceController();
    }
    
    private void deleteDevice(MouseEvent e){
        DeleteDeviceController ddc = new DeleteDeviceController();
    }
    
    public void addCategory(MouseEvent e){
        AddCategoryController acc = new AddCategoryController();
    }
    
    public void deleteCategory(MouseEvent e){
        DeleteCategoryController dcc = new DeleteCategoryController();
    }
    
    public void reportFault(MouseEvent e){
        AddFaultController afc = new AddFaultController();
    }
    
    public void fixedFault(MouseEvent e){
        FixFaultController ffc = new FixFaultController();
    }
    
    public void searchFault(MouseEvent e){
        SearchFaultController sfc = new SearchFaultController();
    }
    
    public void addProvider(MouseEvent e ){
        AddProviderController apc = new AddProviderController(main, true);
    }
    
    public void deleteProvider(MouseEvent e){
        DeleteProviderController dpc = new DeleteProviderController();
    }
    
    public void searchProvider(MouseEvent e){
        SearchProviderController spc = new SearchProviderController();
    }
    
    public void infoStorehouse(MouseEvent e){
        StorehouseController sc = new StorehouseController();
    }
}
