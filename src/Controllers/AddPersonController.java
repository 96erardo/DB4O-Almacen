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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author GerardoAGL
 */
public class AddPersonController extends MouseAdapter {

    private AddPerson ap;

    public AddPersonController() {
        start();
    }

    private void start() {

        ap = new AddPerson();

        //LISTENERS
        ap.btn_photo.addMouseListener(this);
        ap.btn_accept.addMouseListener(this);
        ap.btn_cancel.addMouseListener(this);

        //VISIBLE
        ap.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ap.setLocationRelativeTo(null);
        ap.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        JLabel button = (JLabel) e.getSource();
        String text = button.getToolTipText();

        switch (text) {

            case "photo person": {
                photoPerson(e);
            }
            break;
            case "accept add": {
                acceptPerson(e);
            }
            break;
            case "cancel add": {
                cancelPerson(e);
            }
            break;
        }
    }

    private void photoPerson(MouseEvent e) {
        JFileChooser fichero = new JFileChooser();
        String file;
        String name;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagen", "jpg", "jpeg", "png");
        fichero.setFileFilter(filter);

        int answer = fichero.showOpenDialog(ap);

        if (answer == 0) {

            file = fichero.getSelectedFile().toString();
            name = fichero.getSelectedFile().getName();

            try {
                File filetitle = new File(file);
                ap.photo = ImageIO.read(filetitle);

                ap.btn_photo.setText(name);
                ap.btn_photo.setBackground(Colors.green());

            } catch (IOException ex) {
                ErrorController ec = new ErrorController(ap, true, "Error", "Ha ocurrido un error");
            }
        } else {

            ap.photo = null;
            ap.btn_photo.setText("Foto");
            ap.btn_photo.setBackground(Colors.grey());
        }
    }

    private void acceptPerson(MouseEvent e) {
        if (TextField.filled(ap.txt_name, "Nombre y apellido")
                && TextField.filled(ap.txt_id, "Nro de cédula")) {

            String name = ap.txt_name.getText();
            String id = ap.txt_id.getText();
            if (!PersonDAO.isPerson(id)) {
                Persona person = new Persona(name, id, ap.photo);
                PersonDAO dao = new PersonDAO(person);
                dao.save();
                ap.dispose();
            } else {
                ErrorController ec = new ErrorController(ap, true, "Error", "Esta cedula ya existe en la base de datos");
            }
        }
    }

    private void cancelPerson(MouseEvent e) {
        ap.dispose();
    }
}
