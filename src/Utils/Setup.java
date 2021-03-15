package Utils;

import GUIs.Login;
import GUIs.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Setup {

    public void checkSetup(){

        //Die Folgenden Try-Catch Blöcke erstellen - falls nicht vorhanden - eine Datei welche abspeichert
        // ob die Konfiguration des Masterpasswortes erfolgte oder nicht.
        String setupCheck = "";
        boolean check = false;
        try {
            File file = new File("setup.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
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
            File file = new File("setup.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                setupCheck = myReader.nextLine();
                //System.out.println(setupCheck);
                if(setupCheck.equalsIgnoreCase("0")){
                    Settings settings = new Settings();
                }else if(setupCheck.equalsIgnoreCase("1")){
                    Login lg = new Login();
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
