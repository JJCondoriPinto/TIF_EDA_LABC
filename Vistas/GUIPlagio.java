package Vistas;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class GUIPlagio extends JFrame {
    
    public JTextArea texto;
    public JTextArea textShow;
    public JButton addTextDb;
    public JButton addTextDbFile;
    public JButton removeFile;
    public JButton diselect;

    public JButton verify;
    public JButton clearText;

    public DefaultTableModel modelTable;

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
        textoPanel.setBorder(new TitledBorder("TextoPanel a analizar"));
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
        actionsPanel.setBounds(400, 10, 170, 100);
        
        JPanel plagioPanel = new JPanel();
        plagioPanel.setBorder(new TitledBorder("Resultados de detección"));
        plagioPanel.setBounds(400, 110, 170, 420);
        

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
        this.addTextDbFile = new JButton("Añadir desde archivo");
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
        modelTable.setColumnIdentifiers(new String[]{"ID","Nombre de archivo", "Direccion"});
        JTable tabla = new JTable(modelTable);
        JScrollPane scrollTable = new JScrollPane(tabla);

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int viewRow = tabla.getSelectedRow();
            if (!e.getValueIsAdjusting() && viewRow != -1) {
                this.diselect.setEnabled(true);
                this.removeFile.setEnabled(true);
            }
        });

        JPanel textPanelShow = new JPanel();
        textPanelShow.setLayout(null);
        textPanelShow.setBorder(new TitledBorder("Vista de texto"));
        secundario.add(textPanelShow);
        textPanelShow.setBounds(10, 210, 560, 320);

        this.textShow = new JTextArea();
        this.textShow.setMargin(new Insets(10, 10, 10, 10));
        this.textShow.setLineWrap(true);
        this.textShow.setWrapStyleWord(true);
        JScrollPane scrollTextShow = new JScrollPane(this.textShow);
        textPanelShow.add(scrollTextShow);
        scrollTextShow.setBounds(10, 25, 540, 285);
        
        tablePanel.add(scrollTable);
        scrollTable.setBounds(10, 20, 360, 170);

        panel.addTab("Detector de plagio", principal);
        panel.addTab("Base de datos", secundario);

        this.initButtons(actionsPanel);
    }

    private void initButtons(JPanel panel) {
        panel.setLayout(null);

        this.verify = new JButton("Analizar");
        verify.setBounds(10, 20, 150, 30);
        
        this.clearText = new JButton("Limpiar");
        clearText.setBounds(10, 60, 150, 30);

        panel.add(this.verify);
        panel.add(this.clearText);
    }
}