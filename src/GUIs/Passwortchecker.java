package GUIs;

import java.awt.*;
import javax.swing.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 20.12.2020
 * @author 
 */

public class Passwortchecker extends JFrame {
  // Anfang Attribute
  private JTextField jTextField1 = new JTextField();
  private JLabel lb_passwort = new JLabel();
  private JLabel lBewertung = new JLabel();
  private JLabel lDasPasswortistsicher = new JLabel();
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
    
    jTextField1.setBounds(142, 11, 406, 28);
    cp.add(jTextField1);
    lb_passwort.setBounds(13, 9, 118, 28);
    lb_passwort.setText("Passwort");
    cp.add(lb_passwort);
    lBewertung.setBounds(13, 63, 110, 20);
    lBewertung.setText("Bewertung:");
    cp.add(lBewertung);
    lDasPasswortistsicher.setBounds(146, 60, 398, 28);
    lDasPasswortistsicher.setText("Das Passwort ist sicher");
    cp.add(lDasPasswortistsicher);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Passwortchecker
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Passwortchecker();
  } // end of main
  
  // Ende Methoden
} // end of class Passwortchecker

