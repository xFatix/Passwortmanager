package Main;

import Utils.DiscordPresence;
import Utils.Setup;

public class Main {
    //1 in setup file == already setup
    //0 in setup file == masterpasswort setup
    public static void main(String[] args) {

        Setup setup = new Setup();
        setup.checkSetup();

        //Discord Richpresence
        DiscordPresence.start();

    }

}
