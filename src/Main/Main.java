/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controllers.*;
import DAO.AssignmentDAO;
import DAO.Connection;
import DAO.DeviceDAO;
import DAO.PersonDAO;
import DAO.UserDAO;
import Models.Assignment;
import Models.Device;
import Models.Usuario;
import Views.Login;


/**
 *
 * @author GerardoAGL
 */
public class Main {
    
    public static void main(String args[]){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        IntroductionController main = new IntroductionController();
        //UserDAO dao = new UserDAO(new Usuario("Administrador", "1234", "Gerardo García", "25040214"));
        //dao.save();
    }
}
