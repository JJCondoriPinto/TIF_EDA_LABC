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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public static void main(String[] args) {
        new MainController();
    }
    
}
