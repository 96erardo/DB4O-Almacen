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
import Views.Dialogs.Confirm;
import Views.SearchPerson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author GerardoAGL
 */
public class DeletePersonController extends MouseAdapter {

    private SearchPerson sp;
    private Confirm c;
    private Persona person;

    public DeletePersonController() {
        start();
    }

    private void start() {

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
    public void mouseClicked(MouseEvent e) {

        JLabel button = (JLabel) e.getSource();
        String text = button.getToolTipText();

        switch (text) {
            case "search person": {
                searchPerson(e);
            }
            break;
            case "info person": {
                infoSearch(e);
            }
            break;
            case "accept person": {
                acceptSearch(e);
            }
            break;
            case "cancel person": {
                cancelSearch(e);
            }
            break;
            case "accept confirm": {
                acceptConfirm(e);
            }
            break;
            case "cancel confirm": {
                cancelConfirm(e);
            }
            break;
        }
    }

    public void searchPerson(MouseEvent e) {

        String search = sp.txt_search.getText();
        sp.persons = PersonDAO.search(search);
        sp.dlmPerson.clear();

        if (sp.persons != null) {

            for (int i = 0; i < sp.persons.length; i++) {
                sp.dlmPerson.addElement(sp.persons[i].getNombre() + " " + sp.persons[i].getCedula());
            }
        } else {
            sp.dlmPerson.addElement("No hay resultados para la busqueda");
        }
    }

    public void infoSearch(MouseEvent e) {
        int index = sp.lst_results.getSelectedIndex();
        String text = sp.lst_results.getSelectedValue();
        if (index != -1 && !text.equals("No hay resultados para la busqueda")) {
            Persona person = sp.persons[index];
            PersonController pc = new PersonController(person);
        }
    }

    public void acceptSearch(MouseEvent e) {
        
        int index = sp.lst_results.getSelectedIndex();
        String text = sp.lst_results.getSelectedValue();
        if (index != -1 && !text.equals("No hay resultados para la busqueda")) {
            this.person = sp.persons[index];
            c = new Confirm(sp, true, "¿Seguro que desea eliminar a este trabajador?");
            c.btn_accept.addMouseListener(this);
            c.btn_cancel.addMouseListener(this);
            c.setLocationRelativeTo(sp);
            c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            c.setVisible(true);
        }
    }

    public void cancelSearch(MouseEvent e) {
        sp.dispose();
    }

    private void acceptConfirm(MouseEvent e) {
        
        this.person.setStored(false);
        PersonDAO dao = new PersonDAO(this.person);
        dao.update();
        c.dispose();
        sp.dispose();
    }

    private void cancelConfirm(MouseEvent e) {
        c.dispose();        
    }
}
