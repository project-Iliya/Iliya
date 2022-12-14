package com.github.iliya.project;

import com.github.iliya.project.command.*;
import com.github.iliya.project.listener.MessageReceivedEventListener;
import com.github.iliya.project.listener.ReadyEventListener;
import com.github.iliya.project.listener.SlashCommandInteractionEventListener;
import com.github.iliya.project.threads.PlayingPresenceThread;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    // bot main variable
    public static JDA jda;
    public static CommandManager commandManager;
    public static PlayingPresenceThread playingPresenceThread;

    public static void main(String[] args) {
        System.out.println("Iliya has been started!");

        PlayingPresenceThread playingPresenceThread = new PlayingPresenceThread();

        FileReader fr = null;
        String line = null;
        BufferedReader br = null;
        String token = null;
        try {
            br = new BufferedReader(new FileReader("config.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            while ((line = br.readLine()) != null) {
                String[] split = line.split("=");
                if (split[0].equals("token")) {
                    token = split[1];
                } else if (split[0].equals("guildId")) {
                    String guildId = split[1];
                } else if (split[0].equals("channelId")) {
                    String channelId = split[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (token == null) {
            System.out.println("token is not found.");
            System.exit(1);
        }

        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGE_TYPING);
        builder.enableIntents(GatewayIntent.DIRECT_MESSAGE_REACTIONS);
        builder.enableIntents(GatewayIntent.DIRECT_MESSAGE_TYPING);
        builder.enableIntents(GatewayIntent.DIRECT_MESSAGES);
        builder.enableIntents(GatewayIntent.DIRECT_MESSAGE_REACTIONS);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);

        builder.addEventListeners(new ReadyEventListener());
        builder.addEventListeners(new SlashCommandInteractionEventListener());
        builder.addEventListeners(new MessageReceivedEventListener());

        builder.setAutoReconnect(true);

        commandManager = new CommandManager("y!");
        //commandManager.register("hello", new HelloCommand());
        commandManager.register("roll", new RollCommand(), "dice", "dc");
        commandManager.register("cat", new CatCommand(), "neko");


        try {
            jda = builder.build().awaitReady();
            playingPresenceThread.start();
        } catch (LoginException e) { // if the bot is not able to log in
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}