package GUIs;

import Utils.AES256;
import de.leonhard.storage.Yaml;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 04.03.2021
 * @author Marrrrrrrlon
 */

public class ChangeSettings extends JFrame {
    // Anfang Attribute
    private JLabel lEinstellungen = new JLabel();
    private JLabel lIPAdresse = new JLabel();
    private JTextField tf_ip = new JTextField();
    private JTextField tf_user = new JTextField();
    private JTextField tf_pass = new JTextField();
    private JTextField tf_db = new JTextField();
    private JLabel lUsername = new JLabel();
    private JLabel lPassword = new JLabel();
    private JLabel lDatenbankName = new JLabel();
    private JLabel lPort = new JLabel();
    private JTextField tf_port = new JTextField();
    private JButton bt_save = new JButton();
    private JButton bt_cancel = new JButton();
    // Ende Attribute

    public ChangeSettings() {
        // Frame-Initialisierung
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 673;
        int frameHeight = 395;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Settings");
        setIconImage(new ImageIcon("src/icons/icon4.png").getImage());
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        // Anfang Komponenten

        lEinstellungen.setBounds(10, 8, 110, 20);
        lEinstellungen.setText("Einstellungen");
        cp.add(lEinstellungen);
        lIPAdresse.setBounds(10, 45, 110, 20);
        lIPAdresse.setText("IP-Adresse");
        cp.add(lIPAdresse);
        tf_ip.setBounds(147, 45, 150, 20);
        cp.add(tf_ip);
        tf_user.setBounds(147, 83, 150, 20);
        cp.add(tf_user);
        tf_pass.setBounds(147, 121, 150, 20);
        cp.add(tf_pass);
        tf_db.setBounds(147, 159, 150, 20);
        cp.add(tf_db);
        lUsername.setBounds(10, 83, 110, 20);
        lUsername.setText("username");
        cp.add(lUsername);
        lPassword.setBounds(10, 121, 110, 20);
        lPassword.setText("password");
        cp.add(lPassword);
        lDatenbankName.setBounds(10, 159, 110, 20);
        lDatenbankName.setText("Datenbank-Name");
        cp.add(lDatenbankName);
        lPort.setBounds(340, 45, 110, 20);
        lPort.setText("Port");
        lPort.setHorizontalAlignment(SwingConstants.CENTER);
        cp.add(lPort);
        tf_port.setBounds(459, 45, 150, 20);
        cp.add(tf_port);
        bt_save.setBounds(532, 306, 115, 41);
        bt_save.setText("Speichern");
        bt_save.setMargin(new Insets(2, 2, 2, 2));
        bt_save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bt_save_ActionPerformed(evt);
            }
        });
        cp.add(bt_save);
        bt_cancel.setBounds(412, 306, 115, 41);
        bt_cancel.setText("Abbrechen");
        bt_cancel.setMargin(new Insets(2, 2, 2, 2));
        bt_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bt_cancel_ActionPerformed(evt);
            }
        });
        cp.add(bt_cancel);
        // Ende Komponenten

        setVisible(true);

    } // end of public Settings

    // Anfang Methoden

    public static void main(String[] args) {
        new Settings();
        JOptionPane.showMessageDialog(null, "Wenn die Datenbank gewechselt wird gehen auch alle Daten verloren! \n" +
                "Beachte außerdem das ein neuer Benutzer auch Rechte auf die alte Datenbank haben muss! \n");
    } // end of main

    public void bt_save_ActionPerformed(ActionEvent evt) {
        Yaml yaml = new Yaml("settings", "files");
        String ip = AES256.encrypt(tf_ip.getText());
        String port = AES256.encrypt(tf_port.getText());
        String username = AES256.encrypt(tf_user.getText());
        String password = AES256.encrypt(tf_pass.getText());
        String db = AES256.encrypt(tf_db.getText());

        if (ip != "" || port != "" || username != "" || password != "" || db != ""){
            yaml.set("database.ip", ip);
            yaml.set("database.port", port);
            yaml.set("database.username", username);
            yaml.set("database.password", password);
            yaml.set("database.db", db);


            Masterpasswort mp = new Masterpasswort();
            setVisible(false);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Es müssen alle Felder ausgefüllt werden!", "Achtung ", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

    } // end of bt_save_ActionPerformed

    public void bt_cancel_ActionPerformed(ActionEvent evt) {
        //setup file auf 0 setzten damit man ganz normal von vorne anfangen kann
        File file = new File("setup.txt");
        try {
            FileWriter myWriter = new FileWriter("setup.txt");
            myWriter.write("0");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } // end of bt_cancel_ActionPerformed

    // Ende Methoden
} // end of class Settings

