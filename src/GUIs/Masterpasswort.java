package GUIs;

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

public class Masterpasswort extends JFrame {
  // Anfang Attribute
  private JLabel lb_master = new JLabel();
  private JPasswordField tf_master = new JPasswordField();
  private JLabel lb_masterRe = new JLabel();
  private JPasswordField tf_masterRe = new JPasswordField();
  private JLabel lPasswortmanager = new JLabel();
  private JLabel lb_creator = new JLabel();
  private JLabel lBetreuerHerrSchmidt = new JLabel();
  private JButton bt_login = new JButton();
  // Ende Attribute
  
  public Masterpasswort() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 650; 
    int frameHeight = 341;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Setup Masterpassword");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    setIconImage(new ImageIcon("icon4.png").getImage());
    // Anfang Komponenten
    
    lb_master.setBounds(30, 98, 126, 44);
    lb_master.setText("Masterpasswort");
    cp.add(lb_master);
    tf_master.setBounds(171, 98, 430, 44);
    cp.add(tf_master);
    lb_masterRe.setBounds(30, 155, 126, 44);
    lb_masterRe.setText("Masterpasswort");
    cp.add(lb_masterRe);
    tf_masterRe.setBounds(171, 155, 430, 44);
    cp.add(tf_masterRe);
    lPasswortmanager.setBounds(9, 10, 614, 76);
    lPasswortmanager.setText("Passwortmanager");
    lPasswortmanager.setFont(new Font("Dialog", Font.BOLD, 22));
    lPasswortmanager.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lPasswortmanager);
    lb_creator.setBounds(8, 274, 112, 20);
    lb_creator.setText("Marlon Müller 2020");
    cp.add(lb_creator);
    lBetreuerHerrSchmidt.setBounds(485, 274, 135, 20);
    lBetreuerHerrSchmidt.setText("Betreuer: Herr Schmidt");
    cp.add(lBetreuerHerrSchmidt);
    bt_login.setBounds(22, 216, 170, 44);
    bt_login.setText("Masterpasswort setzen");
    bt_login.setMargin(new Insets(2, 2, 2, 2));
    bt_login.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bt_login_ActionPerformed(evt);
      }
    });
    cp.add(bt_login);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Masterpasswort
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Masterpasswort();
  } // end of main

  private void bt_login_ActionPerformed(ActionEvent evt) {
    String master = tf_master.getText();
    String master2 = tf_masterRe.getText();
    MYSQL mysql = new MYSQL();

    if (!master.equals("") || !master2.equals("")){ //Überprüfung ob die textfelder leer sind. das ! steht für die ver
      if(master.equals(master2)){ //Überprüfung ob der Inhalt der Textfelder gleich ist
        //die for-Schleife zählt die anzahl
        int upperCase = 0;
        for(int i = 0; i < master.length(); i++){
          char ch = master.charAt(i);
          if(Character.isUpperCase(ch)){
            upperCase++;
          }
        }
        System.out.println(upperCase);
        if (master.length() > 8 && upperCase >= 2){
          JOptionPane.showMessageDialog(null, "Masterpasswort erfolgreich hinterlegt!", "Information ", JOptionPane.INFORMATION_MESSAGE);
          mysql.setMasterpasswort(master);
          setVisible(false);
          dispose();
        }else{
          JOptionPane.showMessageDialog(null, "Das Passwort muss mindestens 9 Zeichen lang sein und 2 Großbuchstaben haben!", "Achtung ", JOptionPane.INFORMATION_MESSAGE);
        }
      }else{
        JOptionPane.showMessageDialog(null, "Die Passwörter müssen gleich sein!", "Achtung ", JOptionPane.INFORMATION_MESSAGE);
      }
    }else{
      JOptionPane.showMessageDialog(null, "Das Masterpasswort kann nicht leer sein.", "Achtung ", JOptionPane.INFORMATION_MESSAGE);
    }

    
  }

}

