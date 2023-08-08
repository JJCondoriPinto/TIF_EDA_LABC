package Vistas;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class GUIPlagio extends JFrame {
    
    public JTextArea texto;
    public JTextArea textShow;
    public JButton addTextDb;
    public JButton addTextDbFile;
    public JButton loadFileText;
    public JButton removeFile;
    public JButton diselect;

    public JButton verify;
    public JButton clearText;

    public DefaultTableModel modelTable;
    public JTable tabla;

    // Campos para inserción de texto
    public JTextField titulo;
    public JTextField autor;
    public JTextArea descripcion;

    public GUIPlagio() {

        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.initTabbed();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initTabbed() {
        JTabbedPane panel = new JTabbedPane();
        this.setContentPane(panel);
        
        this.initComponents(panel);
    }
    
    private void initComponents(JTabbedPane panel) {
        JPanel principal = new JPanel();
        principal.setLayout(null);
        JPanel textoPanel = new JPanel();
        textoPanel.setLayout(null);
        textoPanel.setBorder(new TitledBorder("Texto a analizar"));
        textoPanel.setBounds(10, 10, 380, 520);
        
        texto = new JTextArea();
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollText = new JScrollPane(texto);
        textoPanel.add(scrollText);
        scrollText.setBounds(10, 20, 360, 490);
        
        JPanel actionsPanel = new JPanel();
        actionsPanel.setBorder(new TitledBorder("Acciones"));
        actionsPanel.setBounds(400, 10, 170, 140);
        
        JPanel plagioPanel = new JPanel();
        plagioPanel.setBorder(new TitledBorder("Resultados de detección"));
        plagioPanel.setBounds(400, 150, 170, 380);
        

        principal.add(textoPanel);
        principal.add(actionsPanel);
        principal.add(plagioPanel);

        JPanel secundario = new JPanel();
        secundario.setLayout(null);
        
        JPanel actions2 = new JPanel();
        actions2.setLayout(null);
        actions2.setBorder(new TitledBorder("Acciones"));
        secundario.add(actions2);
        actions2.setBounds(10, 10, 170, 200);

        this.addTextDb = new JButton("Añadir texto");
        this.addTextDbFile = new JButton("Cargar archivo");
        this.removeFile = new JButton("Eliminar texto");
        this.diselect = new JButton("Deseleccionar");

        this.diselect.setEnabled(false);
        this.removeFile.setEnabled(false);

        actions2.add(addTextDb);
        actions2.add(addTextDbFile);
        actions2.add(removeFile);
        actions2.add(diselect);

        addTextDb.setBounds(10, 25, 150, 35);
        addTextDbFile.setBounds(10, 65, 150, 35);
        removeFile.setBounds(10, 105, 150, 35);
        diselect.setBounds(10, 145, 150, 35);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(null);
        tablePanel.setBorder(new TitledBorder("Textos ingresados"));
        secundario.add(tablePanel);
        tablePanel.setBounds(190, 10, 380, 200);

        this.modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(new String[]{"ID","Titulo","Autor"});
        this.tabla = new JTable(modelTable);
        JScrollPane scrollTable = new JScrollPane(tabla);

        JPanel textPanelShow = new JPanel();
        textPanelShow.setLayout(null);
        textPanelShow.setBorder(new TitledBorder("Vista de texto"));
        secundario.add(textPanelShow);
        textPanelShow.setBounds(190, 210, 380, 320);

        this.textShow = new JTextArea();
        this.textShow.setMargin(new Insets(10, 10, 10, 10));
        this.textShow.setLineWrap(true);
        this.textShow.setWrapStyleWord(true);
        JScrollPane scrollTextShow = new JScrollPane(this.textShow);
        textPanelShow.add(scrollTextShow);
        scrollTextShow.setBounds(10, 25, 360, 285);
        
        tablePanel.add(scrollTable);
        scrollTable.setBounds(10, 20, 360, 170);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setBorder(new TitledBorder("Información de texto"));
        fieldsPanel.setBounds(10, 210, 170, 320);
        secundario.add(fieldsPanel);
        fieldsPanel.setLayout(null);

        JLabel label1 = new JLabel("Titulo");
        JLabel label2 = new JLabel("Autor");
        JLabel label3 = new JLabel("Descripcion");

        this.titulo = new JTextField();
        this.autor = new JTextField();
        this.descripcion = new JTextArea();

        fieldsPanel.add(this.titulo);
        fieldsPanel.add(this.autor);
        
        label1.setBounds(10, 20, 150, 20);
        this.titulo.setBounds(10, 40, 150, 25);
        
        label2.setBounds(10, 80, 150, 20);
        this.autor.setBounds(10, 100, 150, 25);
        
        label3.setBounds(10, 140, 150, 20);
        JScrollPane scrollDescription = new JScrollPane(this.descripcion);
        this.descripcion.setMargin(new Insets(5, 5, 5, 5));
        this.descripcion.setLineWrap(true);
        this.descripcion.setWrapStyleWord(true);
        scrollDescription.setBounds(10, 160, 150, 150);
        
        fieldsPanel.add(scrollDescription);
        fieldsPanel.add(label1);
        fieldsPanel.add(label2);
        fieldsPanel.add(label3);

        panel.addTab("Detector de plagio", principal);
        panel.addTab("Base de datos", secundario);

        this.initButtons(actionsPanel);
    }

    private void initButtons(JPanel panel) {
        panel.setLayout(null);

        this.verify = new JButton("Analizar");
        this.verify.setBounds(10, 20, 150, 30);
        
        this.loadFileText = new JButton("Cargar archivo");
        this.loadFileText.setBounds(10, 60, 150, 30);
        
        this.clearText = new JButton("Limpiar");
        this.clearText.setBounds(10, 100, 150, 30);

        panel.add(this.verify);
        panel.add(this.loadFileText);
        panel.add(this.clearText);
    }
}