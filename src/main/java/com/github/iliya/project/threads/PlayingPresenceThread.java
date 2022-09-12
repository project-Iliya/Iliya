package com.github.iliya.project.threads;

import com.github.iliya.project.Main;
import net.dv8tion.jda.api.entities.Activity;

public class PlayingPresenceThread extends Thread {

    public PlayingPresenceThread() {
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                assert Main.jda != null;
                Main.jda.getPresence().setActivity(Activity.playing("y!help"));
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
