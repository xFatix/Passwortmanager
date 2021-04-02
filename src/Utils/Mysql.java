package Utils;

import GUIs.Login;
import GUIs.Passwortmanager;
import de.leonhard.storage.Yaml;

import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

public class Mysql {

    Yaml yaml = new Yaml("settings", "files");
      String ip = Aes.decrypt(yaml.getString("database.ip"));
      String port = Aes.decrypt(yaml.getString("database.port"));
      String username = Aes.decrypt(yaml.getString("database.username"));
      String password = Aes.decrypt(yaml.getString("database.password"));
      String db = Aes.decrypt(yaml.getString("database.db"));


    public void create_MasterTB() {
        System.out.println(ip);
        System.out.println(port);
        System.out.println(username);
        System.out.println(password);
        System.out.println(db);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            Statement stmt = con.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS "+db;
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS " + db + ".master"+
                    " (MID INT NOT NULL AUTO_INCREMENT," +
                    "MASTER TEXT," +
                    "PRIMARY KEY (MID));";
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
            System.out.println("1");
        }
    }

    public void create_PassTB() {
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
            sql = "INSERT INTO "+db+".master (MASTER) VALUES ('123')";
            stmt.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
            System.out.println("2");
        }
    }

    /*public void setMasterpasswort(String Master){
        Master = HASH.hash(Master);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            String sql = "INSERT INTO "+db+".master VALUES ('"+Master+"')";
            Statement stmt = con.prepareStatement(sql);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("3");
        }

        try {
            FileWriter myWriter = new FileWriter("setup.txt");
            myWriter.write("1");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.out.println("4");
        }
        Login lg = new Login();
    }*/


    public void setMasterpasswort(String Master){
        Master = Hash.hash(Master);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            Statement stmt = con.createStatement();
            String sql = "UPDATE "+db+".master SET master='" + Master + "' WHERE "+db+".master.MID=1";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("3");
        }

        try {
            FileWriter myWriter = new FileWriter("setup.txt");
            myWriter.write("1");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.out.println("4");
        }
        Login lg = new Login();
    }

    public String getMasterpasswort(){
        String master = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://"+ip+":"+port+"/", username, password);
            Statement stmt = con.createStatement();
            String sql = "SELECT MASTER FROM "+db+".master WHERE "+db+".master.MID=1";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                master = rs.getString("master");
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("5");
        }
        return master;
    }

    public int getPasswordCount(){
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
            System.out.println("6");
        }
        return 0;
    }

    public void showPasswords(){
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
                row.add(Aes.decrypt(appname));
                row.add(Aes.decrypt(user));
                row.add(Aes.decrypt(pw));
                row.add(Aes.decrypt(url));
                row.add(Aes.decrypt(email));
                Passwortmanager.tb_dataModel.addRow(row);
            }
            Passwortmanager passwortmanager = new Passwortmanager();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("7");
        }
    }

    //Diese Methode wird benutzt um die ausgewählte Reihe aus der Datenbank zulöschen
    public void delRow(String value){
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
            System.out.println("8");
        }
    }

    //Diese Methode wird benutzt um ein neuen Eintrag in die Datenbank zuerstellen.
    public void addEntry(String appname, String user, String pw, String URL, String EMail){
        appname = Aes.encrypt(appname);
        user = Aes.encrypt(user);
        pw = Aes.encrypt(pw);
        URL = Aes.encrypt(URL);
        EMail = Aes.encrypt(EMail);
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
            System.out.println("9");
        }
    }

    public void updateEntry(String uid, String appname, String user, String pw, String URL, String EMail){
        appname = Aes.encrypt(appname);
        user = Aes.encrypt(user);
        pw = Aes.encrypt(pw);
        URL = Aes.encrypt(URL);
        EMail = Aes.encrypt(EMail);
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
            System.out.println("10");
        }
    }

}
