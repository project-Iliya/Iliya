package com.github.iliya.project.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class RollCommand extends IliyaCommand {


    @Override
    public void execute(String cmd, String[] args, Message message, TextChannel channel, Guild guild, User author, User bot) {
        if (args.length == 0) {
            channel.sendMessage(author.getAsMention() + " 数値を入力してください。").queue();
            return;
        }

        try {
            int max = Integer.parseInt(args[0]);
            int roll = (int) (Math.random() * max) + 1;
            channel.sendMessage(author.getAsMention() + " 🎲 " + roll + "!").queue();
        } catch (NumberFormatException e) {
            channel.sendMessage(author.getAsMention() + " 数値を入力してください。").queue();
        }
    }
}
