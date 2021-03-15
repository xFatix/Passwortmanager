package Utils;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class DiscordPresence {

    public static void start(){
        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "799776601897435146";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();

        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.details = "Managing Passwords..";
        presence.state = "Use a Passwordmanager";
        presence.largeImageKey = "ico";
        presence.joinSecret = "MTI4NzM0OjFpMmhuZToxMjMxMjM= ";
        lib.Discord_UpdatePresence(presence);


        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                lib.Discord_RunCallbacks();
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException ignored){
                }
            }
        }, "RPC-Callback-Handler").start();
    }

}
