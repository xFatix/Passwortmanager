package GUIs;

import Utils.HASH;
import Utils.MYSQL;

import javax.swing.*;
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

public class Login extends JFrame {
  // Anfang Attribute
  private JLabel lb_login = new JLabel();
  private JLabel lMasterpasswort = new JLabel();
  private JPasswordField tf_masterpw = new JPasswordField();
  private JButton bt_login = new JButton();
  private JLabel lMarlonMueller2020 = new JLabel();
  private JLabel lBetreuerHerrSchmidt = new JLabel();
  // Ende Attribute
  
  public Login() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 545; 
    int frameHeight = 276;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Login");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    setIconImage(new ImageIcon("src/icons/icon4.png").getImage());
    // Anfang Komponenten
    
    lb_login.setBounds(8, 8, 510, 52);
    lb_login.setText("Login");
    lb_login.setFont(new Font("Dialog", Font.BOLD, 22));
    lb_login.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lb_login);
    lMasterpasswort.setBounds(12, 101, 126, 36);
    lMasterpasswort.setText("Masterpasswort");
    lMasterpasswort.setFont(new Font("Dialog", Font.BOLD, 14));
    lMasterpasswort.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lMasterpasswort);
    tf_masterpw.setBounds(154, 98, 358, 44);
    cp.add(tf_masterpw);
    bt_login.setBounds(209, 155, 115, 41);
    bt_login.setText("Login");
    bt_login.setMargin(new Insets(2, 2, 2, 2));
    bt_login.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bt_login_ActionPerformed(evt);
      }
    });
    cp.add(bt_login);
    lMarlonMueller2020.setBounds(8, 210, 112, 20);
    lMarlonMueller2020.setText("Marlon Müller 2020");
    cp.add(lMarlonMueller2020);
    lBetreuerHerrSchmidt.setBounds(384, 211, 135, 20);
    lBetreuerHerrSchmidt.setText("Betreuer: Herr Schmidt");
    cp.add(lBetreuerHerrSchmidt);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Login
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Login();
  } // end of main

  private void bt_login_ActionPerformed(ActionEvent evt) {
    String pw = HASH.hash(tf_masterpw.getText());
    MYSQL mysql = new MYSQL();

    if (pw.equals(mysql.getMasterpasswort())){
      setVisible(false);
      dispose();
      mysql.showPasswords();
    }else{
      tf_masterpw.setText("");
    }
    
  } // end of bt_login_ActionPerformed

  // Ende Methoden
} // end of class Login

