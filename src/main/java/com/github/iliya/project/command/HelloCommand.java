package com.github.iliya.project.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class HelloCommand extends IliyaCommand {

    @Override
    public void execute(String cmd, String[] args, Message message, TextChannel channel, Guild guild, User author, User bot) {
        channel.sendMessage("Hello, " + author.getAsMention() + "!").queue();
    }
}
