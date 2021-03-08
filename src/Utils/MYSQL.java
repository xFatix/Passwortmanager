package Utils;

import GUIs.Login;
import GUIs.Passwortmanager;
import de.leonhard.storage.Yaml;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

public class MYSQL {

    final static Yaml yaml = new Yaml("settings", "files");
    final static String ip = AES256.decrypt(yaml.getString("database.ip"));
    final static String port = AES256.decrypt(yaml.getString("database.port"));
    final static String username = AES256.decrypt(yaml.getString("database.username"));
    final static String password = AES256.decrypt(yaml.getString("database.password"));
    final static String db = AES256.decrypt(yaml.getString("database.db"));


    public static void create_MasterTB() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            Statement stmt = con.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS "+db;
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS " + db + ".master"+
                    " (MASTER TEXT)";
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void create_PassTB() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            Statement stmt = con.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS "+db;
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS "+db+".passwords ("+
                    "UID INT NOT NULL AUTO_INCREMENT," +
                    "appname TEXT," +
                    "username TEXT," +
                    "passwort TEXT," +
                    "url TEXT," +
                    "email TEXT," +
                    "PRIMARY KEY (UID)" +
                    ");";
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void setMasterpasswort(String Master){
        Master = HASH.hash(Master);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO "+db+".master VALUES ('"+Master+"')";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("setup.txt");
            myWriter.write("1");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Login lg = new Login();
    }

    public static String getMasterpasswort(){
        String master = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM "+db+".master";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                master = rs.getString("mpw");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return master;
    }

    public static int getPasswordCount(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            Statement stmt = con.createStatement();
            String sql = "SELECT COUNT(*) from "+db+".passwords";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void showPasswords(){
        String uid = "";
        String appname = "";
        String user = "";
        String pw = "";
        String url = "";
        String email = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            Statement stmt = con.createStatement();
            String sql = "SELECT * from "+db+".passwords";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            /*
            Hier werden die vorhandenen Zeilen gelöscht damit keine Einträge in der Tabelle doppelt stehen.
            Daher braucht das Programm kurz zum laden der Passwörter, da erst alles gelöscht wird um dann alles zu laden.
             */
            DefaultTableModel dm = (DefaultTableModel) Passwortmanager.tb_data.getModel();
            int rowCount = dm.getRowCount();

            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {

                    uid = rs.getString(1);
                    appname = rs.getString(2);
                    user = rs.getString(3);
                    pw = rs.getString(4);
                    url = rs.getString(5);
                    email = rs.getString(6);

                    /*if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));*/

                }
                System.out.println(uid + " | " + appname + " | " + user + " | " + pw + " | " + url + " | " + email);
                Vector row = new Vector();
                row.add(uid);
                row.add(AES256.decrypt(appname));
                row.add(AES256.decrypt(user));
                row.add(AES256.decrypt(pw));
                row.add(AES256.decrypt(url));
                row.add(AES256.decrypt(email));
                Passwortmanager.tb_dataModel.addRow(row);
            }
            Passwortmanager passwortmanager = new Passwortmanager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Diese Methode wird benutzt um die ausgewählte Reihe aus der Datenbank zulöschen
    public static void delRow(String value){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            String sql = "DELETE FROM "+db+".passwords WHERE UID='"+value+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Diese Methode wird benutzt um ein neuen Eintrag in die Datenbank zuerstellen.
    public static void addEntry(String appname, String user, String pw, String URL, String EMail){
        appname = AES256.encrypt(appname);
        user = AES256.encrypt(user);
        pw = AES256.encrypt(pw);
        URL = AES256.encrypt(URL);
        EMail = AES256.encrypt(EMail);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO "+db+".passwords (appname, username, passwort, url, email) VALUES ('"+appname+"','"+
                    user+"','"+pw+"','"+URL+"','"+EMail+"')";
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void updateEntry(String uid, String appname, String user, String pw, String URL, String EMail){
        appname = AES256.encrypt(appname);
        user = AES256.encrypt(user);
        pw = AES256.encrypt(pw);
        URL = AES256.encrypt(URL);
        EMail = AES256.encrypt(EMail);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            String sql = "UPDATE "+db+".passwords SET appname = '"+ appname + "', username = '"+ username +"', passwort = '"+ password +"', url = '"+ URL +"', email = '"+ EMail +"' WHERE UID='"+uid+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
