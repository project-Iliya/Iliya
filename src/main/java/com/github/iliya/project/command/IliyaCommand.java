package com.github.iliya.project.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public abstract class IliyaCommand {

    public abstract void execute(String cmd, String[] args, Message message, TextChannel channel, Guild guild, User author, User bot);

}
