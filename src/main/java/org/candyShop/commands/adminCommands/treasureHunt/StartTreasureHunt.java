package org.candyShop.commands.adminCommands.treasureHunt;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.candyShop.eventListeners.MessageReceivedListener;
import org.candyShop.helpers.CandyBotUtils;
import org.candyShop.helpers.TreasureHuntUtils;

public class StartTreasureHunt extends Command {

    public StartTreasureHunt() {
        this.name = "startHunt";
    }

    @Override
    protected void execute(CommandEvent event) {

        boolean access = CandyBotUtils.isAdmin(event.getMember());

        if (access) {
            event.getJDA().addEventListener(new MessageReceivedListener());
            event.reply("The hunt has begun!");
            TreasureHuntUtils.lastCollected = event.getMember();
        }

    }
}
