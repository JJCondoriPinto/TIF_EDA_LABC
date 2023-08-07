package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Vistas.GUIPlagio;

public class MainController implements ListSelectionListener, ActionListener{

    private GUIPlagio interfaz;

    public MainController() {
        this.interfaz = new GUIPlagio();
        this.interfaz.setVisible(true);

        this.initActions();
    }

    private void initActions() {
        this.interfaz.addTextDb.addActionListener(this);
        this.interfaz.addTextDbFile.addActionListener(this);
        this.interfaz.removeFile.addActionListener(this);
        this.interfaz.verify.addActionListener(this);
        this.interfaz.clearText.addActionListener(this);
        this.interfaz.diselect.addActionListener(this);

        this.interfaz.tabla.getSelectionModel().addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.interfaz.addTextDb) {

        } else if (e.getSource() == this.interfaz.addTextDbFile) {

        } else if (e.getSource() == this.interfaz.removeFile) {

        } else if (e.getSource() == this.interfaz.diselect) {

        } else if (e.getSource() == this.interfaz.verify) {

        } else if (e.getSource() == this.interfaz.clearText) {

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int viewRow = this.interfaz.tabla.getSelectedRow();
        if (!e.getValueIsAdjusting() && viewRow != -1) {
            this.interfaz.diselect.setEnabled(true);
            this.interfaz.removeFile.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new MainController();
    }
    
}
