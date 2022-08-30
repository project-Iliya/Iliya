package com.github.iliya.project.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Map;

public class CommandManager {


    private Map<String, IliyaCommand> commands;
    private String prefix;

    public CommandManager(String prefix) {
        commands = new java.util.HashMap<>();
        this.prefix = prefix;
    }

    public void register(String name, IliyaCommand command) {
        commands.put(name, command);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public IliyaCommand getCommand(String name) {
        String lower = name.toLowerCase();
        for (String key : commands.keySet()) {
            if (key.toLowerCase().equals(lower)) {
                return commands.get(key);
            }
        }
        return null;
    }

    public Map<String, IliyaCommand> getCommands() {
        return commands;
    }

    public void run(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith(prefix) && !event.getMessage().getAuthor().isBot()) {
            String[] split = event.getMessage().getContentRaw().split(" ");
            String cmd = split[0].substring(prefix.length());
            String[] args = new String[split.length - 1];
            System.arraycopy(split, 1, args, 0, split.length - 1);
            if (getCommand(cmd) != null) {
                getCommand(cmd).execute(cmd, args, event.getMessage(), event.getMessage().getChannel().asTextChannel(), event.getGuild(), event.getAuthor(), event.getJDA().getSelfUser());
            }
        }
    }


}
