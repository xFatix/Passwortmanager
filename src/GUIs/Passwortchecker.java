package GUIs;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 20.12.2020
 * @author 
 */

public class Passwortchecker extends JFrame {
  // Anfang Attribute
  private JTextField tf_eingabe = new JTextField();
  private JLabel lb_passwort = new JLabel();
  private JLabel lBewertung = new JLabel();
  private JLabel lDasPasswortistsicher = new JLabel();
  private JButton bt_openLink = new JButton();
  // Ende Attribute
  
  public Passwortchecker() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 577; 
    int frameHeight = 198;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Passwortchecker");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    setIconImage(new ImageIcon("icon4.png").getImage());
    // Anfang Komponenten
    
    tf_eingabe.setBounds(142, 11, 406, 28);
    cp.add(tf_eingabe);
    lb_passwort.setBounds(13, 9, 118, 28);
    lb_passwort.setText("Passwort");
    cp.add(lb_passwort);
    lBewertung.setBounds(13, 63, 110, 20);
    lBewertung.setText("Bewertung:");
    cp.add(lBewertung);
    lDasPasswortistsicher.setBounds(146, 60, 398, 28);
    lDasPasswortistsicher.setText("Das Passwort ist sicher");
    cp.add(lDasPasswortistsicher);

    bt_openLink.setBounds(13, 113, 190, 35);
    bt_openLink.setText("Klick mich für mehr Infos!");
    bt_openLink.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        action_bt_openListener(e);
      }
    });
    cp.add(bt_openLink);


    // Ende Komponenten

    tf_eingabe.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        lb_safe(lDasPasswortistsicher);
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        lb_safe(lDasPasswortistsicher);
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        lb_safe(lDasPasswortistsicher);
      }
    });
    
    setVisible(true);
  } // end of public Passwortchecker
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Passwortchecker();
  } // end of main

  private void lb_safe(JLabel label){
    String eingabe = tf_eingabe.getText();
    String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,10}$";
    if (eingabe.matches(regex) && eingabe.length() >= 12){
      label.setText("Das Passwort ist sicher!");
    }else{
      label.setText("Das Passwort ist leider unsicher! :(");
    }
  }

  private void action_bt_openListener(ActionEvent event){
    try {
      Desktop.getDesktop().browse(new URL("https://www.dataroomx.de/blog/10-kriterien-fuer-ein-sicheres-passwort/").toURI());
    } catch (Exception e) {}
  }

  
  // Ende Methoden
} // end of class Passwortchecker

