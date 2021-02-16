package Main;

import GUIs.Login;
import GUIs.Masterpasswort;
import Utils.DiscordPresence;
import Utils.MYSQL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    //1 in setup file == already setup
    //0 in setup file == masterpasswort setup
    public static void main(String[] args) {
        //Erstellt die Datenbank und die Tabellen die benötigt werden
        MYSQL.create_MasterTB();
        MYSQL.create_PassTB();
        //Die Folgenden Try-Catch Blöcke erstellen - falls nicht vorhanden - eine Datei welche abspeichert
        // ob die Konfiguration des Masterpasswortes erfolgte oder nicht.
        String setupCheck = "";
        try {
            File myObj = new File("setup.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                try {
                    FileWriter myWriter = new FileWriter("setup.txt");
                    myWriter.write("0");
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            File myObj = new File("setup.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                setupCheck = myReader.nextLine();
                System.out.println(setupCheck);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        //Überprüft ob die Konfiguration des Masterpasswortes bereits erfolgte.
        if(setupCheck.equalsIgnoreCase("0")){
            Masterpasswort mp = new Masterpasswort();
        }else if(setupCheck.equalsIgnoreCase("1")){
            Login lg = new Login();
        }

        //Discord Richpresence
        DiscordPresence.start();

    }

}
