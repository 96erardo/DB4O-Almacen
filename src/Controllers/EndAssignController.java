/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.AssignmentDAO;
import DAO.DeviceDAO;
import Models.Assignment;
import Models.Device;
import Views.Dialogs.Confirm;
import Views.SearchAssign;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author GerardoAGL
 */
public class EndAssignController extends MouseAdapter {

    private SearchAssign sa;
    private Confirm c;
    private Assignment assignment;

    public EndAssignController() {

        start();
    }

    private void start() {

        sa = new SearchAssign();

        //LISTENERS
        sa.btn_search.addMouseListener(this);
        sa.btn_info.addMouseListener(this);
        sa.btn_accept.addMouseListener(this);
        sa.btn_cancel.addMouseListener(this);

        //VISIBLE
        sa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sa.setLocationRelativeTo(null);
        sa.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        JLabel button = (JLabel) e.getSource();
        String text = button.getToolTipText();

        switch (text) {

            case "search assign": {
                search(e);
            }
            break;
            case "info assign": {
                info(e);
            }
            break;
            case "accept assign": {
                accept(e);
            }
            break;
            case "cancel assign": {
                cancel(e);
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

    public void search(MouseEvent e) {

        String search = sa.txt_search.getText();
        sa.assignments = AssignmentDAO.search(search);
        sa.dlmAs.clear();

        if (sa.assignments != null) {

            for (int i = 0; i < sa.assignments.length; i++) {
                sa.dlmAs.addElement("Codigo de asignación: " + sa.assignments[i].getId());
            }
        } else {
            sa.dlmAs.addElement("No hay resultados para la busqueda");
        }
    }

    public void info(MouseEvent e) {
        int index = sa.lst_results.getSelectedIndex();
        String text = sa.lst_results.getSelectedValue();
        if (index != -1 && !text.equals("No hay resultados para la busqueda")) {
            Assignment assign = sa.assignments[index];
            AssignmentController pc = new AssignmentController(assign);
        }
    }

    public void accept(MouseEvent e) {
        int index = sa.lst_results.getSelectedIndex();
        String text = sa.lst_results.getSelectedValue();
        if (index != -1 && !text.equals("No hay resultados para la busqueda")) {
            this.assignment = sa.assignments[index];
            if (!this.assignment.isDone()) {
                c = new Confirm(sa, true, "Presione aceptar para continuar con la devolución");
                c.btn_accept.addMouseListener(this);
                c.btn_cancel.addMouseListener(this);
                c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                c.setLocationRelativeTo(sa);
                c.setVisible(true);
            } else {
                ErrorController ec = new ErrorController(sa, true, "Error", "Este dispositivo ya se devolvió");
            }
        }
    }

    public void cancel(MouseEvent e) {
        sa.dispose();
    }

    public void acceptConfirm(MouseEvent e) {
        this.assignment.setDevolutionDate(new Date());
        this.assignment.setDone(true);
        Device device = DeviceDAO.deviceById(this.assignment.getDevice());
        device.setStatus("Disponible");
        
        DeviceDAO.update(device);
        AssignmentDAO.update(this.assignment);
        c.dispose();
        sa.dispose();
    }

    public void cancelConfirm(MouseEvent e) {
        c.dispose();
    }
}
