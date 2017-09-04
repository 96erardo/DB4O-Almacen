/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.PersonDAO;
import DAO.ProviderDAO;
import Models.Provider;
import Views.Dialogs.Confirm;
import Views.SearchProvider;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author GerardoAGL
 */
public class SearchProviderController extends MouseAdapter{
    
    private SearchProvider sp;
    
    public SearchProviderController(){
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
  
        sp.dispose();
    }
    
    private void cancelProvider(MouseEvent e){
        sp.dispose();
    }
    
    
}
