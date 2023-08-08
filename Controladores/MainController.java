package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Clases.Plagio.FileDB;
import Vistas.GUIPlagio;

public class MainController implements ListSelectionListener, ActionListener{

    private GUIPlagio interfaz;
    private FuncionalidadTrie funcionalidad;

    public MainController() {
        this.interfaz = new GUIPlagio();
        this.interfaz.setVisible(true);

        this.funcionalidad = new FuncionalidadTrie();
        this.initActions();
    }

    private void initActions() {
        this.interfaz.addTextDb.addActionListener(this);
        this.interfaz.addTextDbFile.addActionListener(this);
        this.interfaz.removeFile.addActionListener(this);
        this.interfaz.verify.addActionListener(this);
        this.interfaz.clearText.addActionListener(this);
        this.interfaz.diselect.addActionListener(this);
        this.interfaz.loadFileText.addActionListener(this);

        this.interfaz.tabla.getSelectionModel().addListSelectionListener(this);
    }

    private void showFilesTable() {
        while (this.interfaz.modelTable.getRowCount() > 0)
            this.interfaz.modelTable.removeRow(this.interfaz.modelTable.getRowCount()-1);

        for(FileDB file : this.funcionalidad.getAllFiles()) {
            String[] row = new String[]{Integer.toString(file.getId()), file.getTitulo(), file.getAutor()};
            this.interfaz.modelTable.addRow(row);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.interfaz.addTextDb) {
            String text = this.interfaz.textShow.getText();
            String autor = this.interfaz.autor.getText();
            String titulo = this.interfaz.titulo.getText();
            String descripcion = this.interfaz.descripcion.getText();
            if (!text.equals("")) {
                int id = this.funcionalidad.addFileText(text, titulo, autor, descripcion);
                String[] row = new String[]{Integer.toString(id), titulo, autor};
                this.interfaz.modelTable.addRow(row);
                this.interfaz.clearFields();
            }
        } else if (e.getSource() == this.interfaz.loadFileText) { // Para cargar texto a analizar desde archivo
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
    
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try{
                    FileInputStream input = new FileInputStream(selectedFile);
                    String text = "";
                    Scanner sc = new Scanner(input);
                    while(sc.hasNext()) {
                        text += sc.nextLine() + "\n";
                    }
                    this.interfaz.texto.setText(text);
                    sc.close();
                } catch(FileNotFoundException err) {
                    System.err.println(err.getMessage());
                }
            }
        } else if (e.getSource() == this.interfaz.addTextDbFile) { // Para cargar archivo de texto a insertar como BD
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(true);
            int returnValue = fileChooser.showOpenDialog(null);
    
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File[] selectedFile = fileChooser.getSelectedFiles();
                String[] paths = new String[selectedFile.length];
                int i = 0;
                for(File file : selectedFile) {
                    String path = file.getAbsolutePath();
                    paths[i++] = path;
                }

                if (this.funcionalidad.loadFiles(paths)) {
                    this.showFilesTable();
                }
                
            }
        } else if (e.getSource() == this.interfaz.removeFile) {
            String idStr = this.interfaz.modelTable.getValueAt(this.interfaz.tabla.getSelectedRow(), 0).toString(); // id
            int id = Integer.parseInt(idStr);
            FileDB res = this.funcionalidad.removeFile(id);
            if (res != null) {
                this.interfaz.modelTable.removeRow(this.interfaz.tabla.getSelectedRow());
                this.interfaz.tabla.clearSelection();
            }
        } else if (e.getSource() == this.interfaz.diselect) {
            this.interfaz.clearFields();
            this.interfaz.tabla.clearSelection();
            this.interfaz.diselect.setEnabled(false);
            this.interfaz.removeFile.setEnabled(false);
        } else if (e.getSource() == this.interfaz.verify) {
            String text = this.interfaz.texto.getText();
            this.funcionalidad.verifyPlagiarism(text);

        } else if (e.getSource() == this.interfaz.clearText) {
            this.interfaz.texto.setText("");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int viewRow = this.interfaz.tabla.getSelectedRow();
        if (!e.getValueIsAdjusting() && viewRow != -1) {

            String idStr = this.interfaz.modelTable.getValueAt(viewRow, 0).toString(); // id
            int id = Integer.parseInt(idStr);

            FileDB file = this.funcionalidad.getFile(id);
            if (file != null) {

                this.interfaz.titulo.setText(file.getTitulo());
                this.interfaz.autor.setText(file.getAutor());
                this.interfaz.descripcion.setText(file.getDescripcion());
                this.interfaz.textShow.setText(file.getFile().toString());

                this.interfaz.diselect.setEnabled(true);
                this.interfaz.removeFile.setEnabled(true);
            }
            
        }
    }

    public static void main(String[] args) {
        new MainController();
    }
    
}
