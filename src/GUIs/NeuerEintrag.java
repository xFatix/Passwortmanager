package GUIs;

import Utils.Mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 14.01.2021
 * @author 
 */

public class NeuerEintrag extends JFrame {
  // Anfang Attribute
  private JTextField tf_appname = new JTextField();
  private JTextField tf_username = new JTextField();
  private JTextField tf_passwort = new JTextField();
  private JTextField tf_url = new JTextField();
  private JTextField tf_email = new JTextField();
  private JLabel lAppname = new JLabel();
  private JLabel lUsername = new JLabel();
  private JLabel lPassword = new JLabel();
  private JLabel lURL = new JLabel();
  private JLabel lEMail = new JLabel();
  private JButton bt_OK = new JButton();
  private JButton bt_cancel = new JButton();
  // Ende Attribute
  
  public NeuerEintrag() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 306; 
    int frameHeight = 509;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Neuer Eintrag");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    setIconImage(new ImageIcon("src/icons/icon4.png").getImage());
    // Anfang Komponenten

    tf_passwort.setText("");
    tf_username.setText("");
    tf_appname.setText("");
    tf_email.setText("");
    tf_url.setText("");

    tf_appname.setBounds(14, 45, 150, 20);
    cp.add(tf_appname);
    tf_username.setBounds(14, 119, 150, 20);
    cp.add(tf_username);
    tf_passwort.setBounds(14, 199, 150, 20);
    cp.add(tf_passwort);
    tf_url.setBounds(14, 282, 150, 20);
    cp.add(tf_url);
    tf_email.setBounds(14, 363, 150, 20);
    cp.add(tf_email);
    lAppname.setBounds(14, 19, 110, 20);
    lAppname.setText("Appname");
    cp.add(lAppname);
    lUsername.setBounds(14, 93, 110, 20);
    lUsername.setText("Username");
    cp.add(lUsername);
    lPassword.setBounds(14, 173, 110, 20);
    lPassword.setText("Password");
    cp.add(lPassword);
    lURL.setBounds(14, 255, 110, 20);
    lURL.setText("URL");
    cp.add(lURL);
    lEMail.setBounds(14, 337, 110, 20);
    lEMail.setText("E-Mail");
    cp.add(lEMail);
    bt_OK.setBounds(14, 426, 99, 33);
    bt_OK.setText("OK");
    bt_OK.setMargin(new Insets(2, 2, 2, 2));
    bt_OK.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bt_OK_ActionPerformed(evt);
      }
    });
    cp.add(bt_OK);
    bt_cancel.setBounds(181, 426, 99, 33);
    bt_cancel.setText("Cancel");
    bt_cancel.setMargin(new Insets(2, 2, 2, 2));
    bt_cancel.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bt_cancel_ActionPerformed(evt);
      }
    });
    cp.add(bt_cancel);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public NeuerEintrag
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new NeuerEintrag();
  } // end of main

  private void bt_OK_ActionPerformed(ActionEvent evt) {
    Mysql mysql = new Mysql();
    System.out.println(tf_appname.getText());
    System.out.println(tf_username.getText());
    System.out.println(tf_passwort.getText());

    if (!tf_appname.getText().equals("") && !tf_username.getText().equals("") && !tf_passwort.getText().equals("")){
      mysql.addEntry(tf_appname.getText(), tf_username.getText(), tf_passwort.getText(), tf_url.getText(), tf_email.getText());
      setVisible(false);
      dispose();
      mysql.showPasswords();
    }else{
      JOptionPane.showMessageDialog(null, "Es muss ein Appname, Username und Passwort angegeben sein!", "Achtung ", JOptionPane.INFORMATION_MESSAGE);
    }
    
  } // end of bt_OK_ActionPerformed

  private void bt_cancel_ActionPerformed(ActionEvent evt) {
    Mysql mysql = new Mysql();
    setVisible(false);
    dispose();
    mysql.showPasswords();
  } // end of bt_cancel_ActionPerformed

  // Ende Methoden
} // end of class NeuerEintrag

