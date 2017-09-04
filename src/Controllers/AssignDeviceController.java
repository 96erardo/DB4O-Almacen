/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.AssignmentDAO;
import DAO.DeviceDAO;
import DAO.PersonDAO;
import Models.Assignment;
import Models.Departament;
import Models.Device;
import Models.Persona;
import Views.AssignDevice;
import Views.Decorations.Colors;
import Views.SearchDevice;
import Views.SearchPerson;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author GerardoAGL public class AssignDeviceController extends MouseAdapter{
 *
 */
public class AssignDeviceController extends MouseAdapter {

    private AssignDevice ad;
    private SearchPerson sp;
    private SearchDevice sd;

    public AssignDeviceController() {
        start();

    }

    private void start() {

        ad = new AssignDevice();

        //LISTENERS
        ad.btn_person.addMouseListener(this);
        ad.btn_device.addMouseListener(this);
        ad.btn_accept.addMouseListener(this);
        ad.btn_cancel.addMouseListener(this);

        //VISIBLE
        ad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ad.setLocationRelativeTo(null);
        ad.setVisible(true);
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
            case "accept person": {
                acceptPerson(e);
            }
            break;
            case "cancel person": {
                cancelPerson(e);
            }
            break;
            case "search device": {
                searchDevice(e);
            }
            break;
            case "accept device": {
                acceptDevice(e);
            }
            break;
            case "cancel device": {
                cancelDevice(e);
            }
            break;
            case "info device": {
                infoDevice(e);
            }
            break;
            case "accept": {
                accept(e);
            }
            break;
            case "cancel": {
                cancel(e);
            }
            break;
            case "person": {
                person(e);
            }
            break;
            case "device": {
                device(e);
            }
            break;
        }
    }

    public void person(MouseEvent e) {
        sp = new SearchPerson();
        sp.btn_search.addMouseListener(this);
        sp.btn_accept.addMouseListener(this);
        sp.btn_cancel.addMouseListener(this);
        sp.setLocationRelativeTo(null);
        sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sp.setVisible(true);
    }

    public void device(MouseEvent e) {
        sd = new SearchDevice();
        sd.btn_search.addMouseListener(this);
        sd.btn_info.addMouseListener(this);
        sd.btn_accept.addMouseListener(this);
        sd.btn_cancel.addMouseListener(this);
        sd.setLocationRelativeTo(null);
        sd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sd.setVisible(true);
    }

    public void accept(MouseEvent e) {

        if (ad.person != null && ad.device != null) {
            if (!ad.device.getStatus().equals("Ocupado")) {
                String person = ad.person.getCedula();
                int device = ad.device.getId();
                ad.device.setStatus("Ocupado");
                String departament = (String) ad.cb_departament.getSelectedItem();

                Assignment assigment = new Assignment(person, device, departament);
                AssignmentDAO dao = new AssignmentDAO(assigment);
                dao.save();
                DeviceDAO.update(ad.device);
                ad.dispose();
            }else{
                ErrorController error = new ErrorController(ad, true, "Error", "Este dispositivo ya está ocupado");
            }
        } else {
            ErrorController error = new ErrorController(ad, true, "Error", "No ha seleccionado una persona o dispositivo");
        }
    }

    public void cancel(MouseEvent e) {
        ad.dispose();
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

    public void acceptPerson(MouseEvent e) {
        int index = sp.lst_results.getSelectedIndex();
        String text = sp.lst_results.getSelectedValue();
        if (index != -1 && !text.equals("No hay resultados para la busqueda")) {
            ad.person = sp.persons[index];
            sp.dispose();
            ad.lbl_person.setText(ad.person.getNombre());
            ad.btn_person.setBackground(Colors.green());
        }
    }

    public void cancelPerson(MouseEvent e) {
        ad.person = null;
        ad.lbl_person.setText("Buscar personal");
        ad.btn_person.setBackground(Colors.grey());
        sp.dispose();
    }

    public void searchDevice(MouseEvent e) {
        String s = sd.txt_search.getText();
        sd.devices = DeviceDAO.search(s);
        sd.dlmDev.removeAllElements();
        String name;

        if (sd.devices != null) {

            for (int i = 0; i < sd.devices.length; i++) {

                name = sd.devices[i].getBrand() + " " + sd.devices[i].getModel();
                sd.dlmDev.addElement(name);
            }
        } else {
            sd.dlmDev.addElement("No hay resultados para la busqueda");
        }
    }

    public void infoDevice(MouseEvent e) {
        int index = sd.lst_results.getSelectedIndex();
        String text = sd.lst_results.getSelectedValue();
        if (index != -1 && !text.equals("No hay resultados para la busqueda")) {
            Device device = sd.devices[index];
            DeviceController dc = new DeviceController(device);
        }
    }

    public void acceptDevice(MouseEvent e) {
        int index = sd.lst_results.getSelectedIndex();

        if (index != -1) {
            ad.device = sd.devices[index];
            sd.dispose();
            ad.lbl_device.setText(ad.device.getBrand() + " " + ad.device.getModel());
            ad.btn_device.setBackground(Colors.green());
        }
    }

    public void cancelDevice(MouseEvent e) {
        ad.device = null;
        ad.lbl_device.setText("Buscar dispositivo");
        ad.btn_device.setBackground(Colors.grey());
        sd.dispose();
    }
}
