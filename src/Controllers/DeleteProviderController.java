/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.ProviderDAO;
import Models.Provider;
import Views.Dialogs.Confirm;
import Views.SearchProvider;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class DeleteProviderController extends MouseAdapter{
    
    private SearchProvider sp;
    private Confirm c;
    
    public DeleteProviderController(){
        start();
    }
    
    public void start(){
        
        sp = new SearchProvider();
        
        //LISTENERS
        sp.btn_search.addMouseListener(this);
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
            
            case "search provider":{
                searchProvider(e);
            }break;
            case "accept provider":{
                acceptProvider(e);
            }break;
            case "cancel provider":{
                cancelProvider(e);
            }break;
            case "accept confirm":{
                acceptConfirm(e);
            }break;
            case "cancel confirm":{
                cancelConfirm(e);
            }
        }
    }
    
    private void searchProvider(MouseEvent e){
        String search = sp.txt_search.getText();
        sp.provider = ProviderDAO.search(search);
        sp.dlmProvider.clear();
        
        if(sp.provider != null){
            
            for(int i = 0; i < sp.provider.length; i++){
                sp.dlmProvider.addElement(sp.provider[i].getName() +" "+sp.provider[i].getPhone());
            }
        }else{
            sp.dlmProvider.addElement("No hay resultados para la busqueda");
        }
    }
    
    private void acceptProvider(MouseEvent e){
        
        int index = sp.lst_results.getSelectedIndex();
        if(index != -1){
            c = new Confirm(sp, true, "¿Seguro que desea eliminar este proveedor?");
            c.btn_accept.addMouseListener(this);
            c.btn_cancel.addMouseListener(this);
            c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            c.setLocationRelativeTo(null);
            c.setVisible(true);
        }        
    }
    
    private void cancelProvider(MouseEvent e){
        sp.dispose();
    }
    
    private void acceptConfirm(MouseEvent e){
        int index = sp.lst_results.getSelectedIndex();
        Provider provider = sp.provider[index];
        ProviderDAO dao = new ProviderDAO(provider);
        dao.delete();
        c.dispose();
        sp.dispose();
    }
    
    private void cancelConfirm(MouseEvent e){
        c.dispose();
    }
}
