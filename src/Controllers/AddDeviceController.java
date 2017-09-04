/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Validations.TextField;
import DAO.CategoryDAO;
import DAO.DeviceDAO;
import Models.Category;
import Models.Characteristic;
import Models.Device;
import Views.AddDevice;
import Views.Dialogs.AddApp;
import Views.Dialogs.AddCharacteristic;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author GerardoAGL
 */
public class AddDeviceController extends MouseAdapter {

    private AddDevice ad;
    private AddCharacteristic ac;
    private AddApp aa;
    private ErrorController ec;

    public AddDeviceController(Frame parent) {
        start();

    }

    private void start() {

        ad = new AddDevice();

        //LISTENERS
        //add devie
        ad.app_add.addMouseListener(this);
        ad.app_remove.addMouseListener(this);
        ad.cha_add.addMouseListener(this);
        ad.cha_remove.addMouseListener(this);
        ad.btn_picture.addMouseListener(this);
        ad.btn_cancel.addMouseListener(this);
        ad.btn_save.addMouseListener(this);

        //VISIBLE
        ad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ad.setLocationRelativeTo(null);
        ad.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        JLabel button = (JLabel) e.getSource();
        String option = button.getToolTipText();

        switch (option) {

            case "save add": {
                save(e);
            }
            break;
            case "cancel add": {
                cancel(e);
            }
            break;
            case "acceptCha": {
                acceptCha(e);
            }
            break;
            case "cancelCha": {
                cancelCha(e);
            }
            break;
            case "acceptApp": {
                acceptApp(e);
            }
            break;
            case "cancelApp": {
                cancelApp(e);
            }
            break;
            case "addCha": {
                addCha(e);
            }
            break;
            case "removeCha": {
                removeCha(e);
            }
            break;
            case "addApp": {
                addApp(e);
            }
            break;
            case "removeApp": {
                removeApp(e);
            }
            break;
            case "picture": {
                addPicture(e);
            }
        }
    }

    //DEVICE
    public void save(MouseEvent e) {

        if (TextField.filled(ad.txt_brand, "Marca")
                && TextField.filled(ad.txt_model, "Modelo")) {

            int category = ad.categories[ad.cb_type.getSelectedIndex()].getId();
            String brand = ad.txt_brand.getText();
            String model = ad.txt_model.getText();
            ArrayList<Characteristic> charac = ad.characteristic;
            ArrayList<String> apps = ad.apps;
            BufferedImage picture = ad.picture;

            Device device = new Device(category, brand, model, charac, apps, picture);

            DeviceDAO deviceDAO = new DeviceDAO(device);
            deviceDAO.save();

            ad.dispose();
        }
    }

    private void cancel(MouseEvent e) {
        ad.dispose();
    }

    //APPS
    public void removeApp(MouseEvent e) {

        int index = ad.lst_app.getSelectedIndex();

        if (index != -1) {

            ad.dlmApp.remove(index);
            ad.apps.remove(index);
        }
    }

    public void addApp(MouseEvent e) {

        if (ad.lst_app.isEnabled()) {

            aa = new AddApp(ad, true);
            aa.btn_accept.addMouseListener(this);
            aa.btn_cancel.addMouseListener(this);
            aa.setLocationRelativeTo(null);
            aa.setVisible(true);

        }
    }

    public void acceptApp(MouseEvent e) {

        if (TextField.filled(aa.txt_name, "Nombre")) {

            String name = aa.txt_name.getText();
            ad.dlmApp.addElement(name);
            ad.apps.add(name);
            aa.dispose();
        } else {

            ErrorController ec = new ErrorController(ad, true, "Error", "Existen campos vacios");
        }
    }

    public void cancelApp(MouseEvent e) {

        aa.dispose();
    }

    //CHARACTERISTICS
    public void acceptCha(MouseEvent e) {

        if (TextField.filled(ac.txt_name, "Ej: Procesador")
                && TextField.filled(ac.txt_value, "Ej: 2.60 Ghz")
                && TextField.filled(ac.txt_info, "Ej: Intel i7 2330K")) {

            String name = ac.txt_name.getText();
            String value = ac.txt_value.getText();
            String info = ac.txt_info.getText();

            Characteristic c = new Characteristic(name, value, info);
            ad.dlmCha.addElement(c.getName());
            ad.characteristic.add(c);
            ac.dispose();
        } else {

            ErrorController ec = new ErrorController(ad, true, "Error", "Existen campos vacios");
        }
    }

    public void cancelCha(MouseEvent e) {
        ac.dispose();
    }

    public void removeCha(MouseEvent e) {

        int index = ad.lst_cha.getSelectedIndex();

        if (index != -1) {

            ad.dlmCha.remove(index);
            ad.characteristic.remove(index);
        }
    }

    public void addCha(MouseEvent e) {
        ac = new AddCharacteristic(ad, true);
        ac.btn_accept.addMouseListener(this);
        ac.btn_cancel.addMouseListener(this);
        ac.setLocationRelativeTo(ad);
        ac.setVisible(true);
    }

    //PICTURE
    public void addPicture(MouseEvent e) {

        JFileChooser fichero = new JFileChooser();
        String file;
        String name;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagen", "jpg", "jpeg", "png");
        fichero.setFileFilter(filter);
        int answer = fichero.showOpenDialog(ad);

        if (answer == 0) {

            file = fichero.getSelectedFile().toString();
            name = fichero.getSelectedFile().getName();

            try {
                File filetitle = new File(file);
                ad.picture = ImageIO.read(filetitle);

                ad.btn_picture.setText(name);
                ad.btn_picture.setBackground(new Color(67, 160, 71));

            } catch (IOException ex) {
                ErrorController ec = new ErrorController(ad, true, "Error", "Ha ocurrido un error");
            }
        }
    }
}
