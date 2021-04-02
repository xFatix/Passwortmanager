package GUIs;

import Utils.Mysql;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 20.12.2020
 * @author 
 */

public class Passwortmanager extends JFrame {
  // Anfang Attribute
  private JMenuBar menubar = new JMenuBar();
  private JButton bt_neuerEintrag = new JButton();
  private JButton bt_delEintrag = new JButton();
  public static JTable tb_data = new JTable(0, 6);
  public static DefaultTableModel tb_dataModel = (DefaultTableModel) tb_data.getModel();
  private JScrollPane tb_dataScrollPane = new JScrollPane(tb_data);
  private JButton bt_pwc = new JButton();
  private JButton bt_passgen = new JButton();
  private JButton bt_settings = new JButton();
  // Ende Attribute

  private static boolean allow_NE = false; //NeuerEintrag GUI is open
  private static boolean allow_PC = false; //Passwortchecker GUI is open


  public Passwortmanager() {
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 591; 
    int frameHeight = 421;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Passwortmanager");
    setIconImage(new ImageIcon("src/icons/icon4.png").getImage());
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten



    setJMenuBar(menubar);
    bt_neuerEintrag.setBounds(25, -6, 200, 25);
    bt_neuerEintrag.setText("Neuer Eintrag");
    bt_neuerEintrag.setMargin(new Insets(2, 2, 2, 2));
   //bt_neuerEintrag.setIcon(new ImageIcon("icons/shell.png"));
    bt_neuerEintrag.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bt_neuerEintrag_ActionPerformed(evt);
      }
    });
    cp.add(bt_neuerEintrag);
    
    bt_delEintrag.setBounds(120, -7, 123, 25);
    bt_delEintrag.setText("Eintrag entfernen");
    bt_delEintrag.setMargin(new Insets(2, 2, 2, 2));
    bt_delEintrag.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bt_delEintrag_ActionPerformed(evt);
      }
    });
    cp.add(bt_delEintrag);
    tb_dataScrollPane.setBounds(8, 14, 556, 318);
    tb_data.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tb_data.setRowSelectionAllowed(true);
    tb_data.getColumnModel().getColumn(0).setHeaderValue("UID");
    tb_data.getColumnModel().getColumn(1).setHeaderValue("APPNAME");
    tb_data.getColumnModel().getColumn(2).setHeaderValue("USERNAME");
    tb_data.getColumnModel().getColumn(3).setHeaderValue("PASSWORD");
    tb_data.getColumnModel().getColumn(4).setHeaderValue("URL");
    tb_data.getColumnModel().getColumn(5).setHeaderValue("E-MAIL");
    cp.add(tb_dataScrollPane);
    bt_pwc.setBounds(238, -9, 115, 25);
    bt_pwc.setText("Passwortchecker");
    bt_pwc.setMargin(new Insets(2, 2, 2, 2));
    bt_pwc.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bt_pwc_ActionPerformed(evt);
      }
    });
    cp.add(bt_pwc);

    bt_passgen.setBounds(347, -12, 139, 25);
    bt_passgen.setText("Passwortgenerator");
    bt_passgen.setMargin(new Insets(2, 2, 2, 2));
    bt_passgen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bt_passgen_ActionPerformed(evt);
      }
    });
    cp.add(bt_passgen);

    bt_settings.setBounds(347, -12, 139, 25);
    bt_settings.setText("Settings");
    bt_settings.setMargin(new Insets(2, 2, 2, 2));
    bt_settings.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        bt_settings_ActionPerformed(evt);
      }
    });
    cp.add(bt_settings);
    // Ende Komponenten

    menubar.add(bt_neuerEintrag);
    menubar.add(bt_delEintrag);
    menubar.add(bt_pwc);
    menubar.add(bt_passgen);
    menubar.add(bt_settings);
    tb_data.getTableHeader().setReorderingAllowed(false);
    setVisible(true);

    /*tb_data.getModel().addTableModelListener(new TableModelListener() {

      public void tableChanged(TableModelEvent e) {
        System.out.println(" ");
        System.out.println("Row: " + e.getFirstRow());
        System.out.println("Column: " + e.getColumn());
        System.out.println("UID: " + tb_dataModel.getValueAt(e.getFirstRow(), 0).toString());
        System.out.println(tb_dataModel.getValueAt(e.getFirstRow(), e.getColumn()).toString());
        MYSQL.updateEntry(tb_dataModel.getValueAt(e.getFirstRow(), 0).toString(),
                tb_dataModel.getValueAt(e.getFirstRow(), 1).toString(),
                tb_dataModel.getValueAt(e.getFirstRow(), 2).toString(),
                tb_dataModel.getValueAt(e.getFirstRow(), 3).toString(),
                tb_dataModel.getValueAt(e.getFirstRow(), 4).toString(),
                tb_dataModel.getValueAt(e.getFirstRow(), 5).toString());
      }
    });*/
  }
  
  public static void main(String[] args) {
    new Passwortmanager();

  } // end of main
  
  private void bt_neuerEintrag_ActionPerformed(ActionEvent evt) {
    NeuerEintrag ne = new NeuerEintrag();
    setVisible(false);
    dispose();
  }

  private void bt_delEintrag_ActionPerformed(ActionEvent evt) {
    Mysql mysql = new Mysql();
    if (tb_data.getSelectedRow() != -1){
      Object obj = tb_dataModel.getValueAt(tb_data.getSelectedRow(), 0);
      String value = obj.toString();
      mysql.delRow(value);
      System.out.println(value);
      tb_dataModel.removeRow(tb_data.getSelectedRow());
    }
  }

  private void bt_pwc_ActionPerformed(ActionEvent evt) {
    Passwortchecker checker = new Passwortchecker();
  }

  private void bt_passgen_ActionPerformed(ActionEvent evt) {
    Passwortgenerator pwg = new Passwortgenerator();
  }

  private void bt_settings_ActionPerformed(ActionEvent evt) {
    Settings settings = new Settings();
    setVisible(false);
    dispose();
  }


}

