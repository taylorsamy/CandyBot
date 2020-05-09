package org.candyShop.commands.adminCommands.treasureHunt;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.candyShop.helpers.CandyBotUtils;
import org.candyShop.helpers.TreasureHuntUtils;

import java.awt.*;

public class TreasureHelp extends Command {

    public TreasureHelp() {
        this.name = "treasureHelp";
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Treasure Hunt Help");
        eb.setColor(Color.cyan);
        if (CandyBotUtils.isAdmin(event.getMember())) {
            eb.addField("Usage: ", "<mandatory> [optional]", false);
            eb.addField("!Give" + TreasureHuntUtils.treasureName + " <@person> <number>",
                    "Example: !Give" + TreasureHuntUtils.treasureName + event.getMember().getAsMention() + " 10",
                    false);
            eb.addField("!SetTreasureName <name>", "Sets the name of the treasure. \n Example: !SetTreasureName Cupcake", false);
            eb.addField("!SetTreasure <emote> [emote] [emote]", "Sets the emotes for the hunt \n Example: !SetTreasure 🍬 🍬 🍬", false);
            eb.addField("", "The first emote is the normal emote. The second emote is for special items, and the third emote is what the special emote turns into when it gets taken.", false);
            eb.addField("!StartHunt", "Starts the hunt", false);
            eb.addField("!StopHunt", "Stops the hunt", false);
            eb.addField("!" + TreasureHuntUtils.treasureName + "Hunt", "Sends a leaderboard of the current hunt", false);
            eb.addField("!" + TreasureHuntUtils.treasureName + "Rate <rate>", "Sets the rate of treasure. Default 50", false);
            eb.addField("!" + TreasureHuntUtils.treasureName, "Sends the amount of treasure the sender has.", false);

        } else {
            eb.addField("!" + TreasureHuntUtils.treasureName, "Sends the amount of treasure the sender has.", false);
        }

        event.reply(eb.build());
    }
}
