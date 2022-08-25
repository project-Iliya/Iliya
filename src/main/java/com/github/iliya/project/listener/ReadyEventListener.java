package com.github.iliya.project.listener;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReadyEventListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);

        System.out.println("Bot is now ready!");
    }
}
