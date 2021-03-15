package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 20.12.2020
 * @author 
 */

public class Passwortgenerator extends JFrame {
  // Anfang Attribute
  private JTextField tf = new JTextField();
  private JButton bNeuesPasswort = new JButton();
  private JButton bKopieren = new JButton();
  private JLabel lEinstellungen = new JLabel();
  private JSpinner sp_length = new JSpinner();
  private SpinnerNumberModel sp_lengthModel = new SpinnerNumberModel(12, 12, 100, 1);
  private JLabel lb_length = new JLabel();
  // Ende Attribute
  
  public Passwortgenerator() {     // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 407; 
    int frameHeight = 251;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Passwortgenerator");
    setIconImage(new ImageIcon("icon4.png").getImage());
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    tf.setBounds(14, 6, 358, 36);
    tf.setText("");
    tf.setFont(new Font("Dialog", Font.PLAIN, 14));
    cp.add(tf);
    bNeuesPasswort.setBounds(14, 48, 235, 49);
    bNeuesPasswort.setText("Neues Passwort");
    bNeuesPasswort.setMargin(new Insets(2, 2, 2, 2));
    bNeuesPasswort.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bNeuesPasswort_ActionPerformed(evt);
      }
    });
    cp.add(bNeuesPasswort);
    bKopieren.setBounds(265, 48, 107, 49);
    bKopieren.setText("Kopieren");
    bKopieren.setMargin(new Insets(2, 2, 2, 2));
    bKopieren.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bKopieren_ActionPerformed(evt);
      }
    });
    cp.add(bKopieren);
    lEinstellungen.setBounds(14, 110, 110, 20);
    lEinstellungen.setText("Einstellungen");
    cp.add(lEinstellungen);
    sp_length.setBounds(77, 139, 46, 24);
    sp_length.setValue(12);
    sp_length.setModel(sp_lengthModel);
    cp.add(sp_length);

    lb_length.setBounds(14, 139, 62, 20);
    lb_length.setText("Länge");
    cp.add(lb_length);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Passwortgenerator
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Passwortgenerator();
  } // end of main
  
  private void bNeuesPasswort_ActionPerformed(ActionEvent evt) {
    //Generiert ein neues Passwort mit der Länge vom Spinner
    tf.setText(createPassword((Integer) sp_length.getValue()));
  }

  private void bKopieren_ActionPerformed(ActionEvent evt) {
    writeTextToClipboard(tf.getText());
  }


  /*
  Methode von: http://javatricks.de/tricks/passwort-generieren-mit-java
  Generate new Password
   */
  private static String createPassword(int length){
    final String allowedChars = "0123456789abcdefghijklmnopqrstuvwABCDEFGHIJKLMNOP!§$%&?*+#";
    SecureRandom random = new SecureRandom();
    StringBuilder pass = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      pass.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
    }
    return pass.toString();
  }


  /*
  Methode von: https://alvinalexander.com/blog/post/jfc-swing/how-write-text-string-system-clipboard-java-swing/
  Copy String to Clipboard
   */
  private static void writeTextToClipboard(String s) {
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    Transferable transferable = new StringSelection(s);
    clipboard.setContents(transferable, null);
  }
  // Ende Methoden
} // end of class Passwortgenerator

