package com.github.iliya.project.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class CatCommand extends IliyaCommand {
    @Override
    public void execute(String cmd, String[] args, Message message, TextChannel channel, Guild guild, User author, User bot) {
        // send req
        final String API_URL = "https://aws.random.cat/meow";
        URL obj = null;
        try {
            obj = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                channel.sendMessage(author.getAsMention() + " 🐈 エラーが発生しました。").queue();
                return;
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            final String IMAGE_URL = response.toString().replaceFirst(".*\"file\":\"", "").split("\"")[0];
            channel.sendMessageEmbeds(new EmbedBuilder()
                    .setTitle("🐈")
                    .setImage(IMAGE_URL.replaceAll("\\\\", ""))
                    .build()).queue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
