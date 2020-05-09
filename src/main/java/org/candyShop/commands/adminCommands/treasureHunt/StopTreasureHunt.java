package org.candyShop.commands.adminCommands.treasureHunt;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.candyShop.eventListeners.MessageReceivedListener;
import org.candyShop.helpers.CandyBotUtils;
import org.candyShop.helpers.TreasureHuntUtils;

import java.util.List;

public class StopTreasureHunt extends Command {

    public StopTreasureHunt() {
        this.name = "stopHunt";
    }

    @Override
    protected void execute(CommandEvent event) {

        if (CandyBotUtils.isAdmin(event.getMember())) {
            List<Object> listeners = event.getJDA().getRegisteredListeners();
            for (Object o : listeners) {
                if (o instanceof MessageReceivedListener) {
                    ((MessageReceivedListener) o).terminate(event.getJDA());
                    TreasureHuntUtils.removeRoles(event.getGuild());
                    event.reply("The hunt has ended!");
                }
            }
        }

    }
}
