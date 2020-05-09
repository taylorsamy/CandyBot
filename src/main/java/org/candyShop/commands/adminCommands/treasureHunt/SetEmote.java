package org.candyShop.commands.adminCommands.treasureHunt;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Activity;
import org.candyShop.helpers.CandyBotUtils;
import org.candyShop.helpers.TreasureHuntUtils;

public class SetEmote extends Command {

    public SetEmote() {
        this.name = "setTreasure";
    }

    @Override
    protected void execute(CommandEvent event) {
        boolean access = CandyBotUtils.isAdmin(event.getMember());
        if (access) {

            String[] args = event.getArgs().split(" ");


                if (args.length == 1) {
                    setEmote(event, args, 0);
                } else if (args.length == 2) {
                    setEmote(event, args, 0);
                    setEmote(event, args, 1);
                } else if (args.length == 3) {
                    setEmote(event, args, 0);
                    setEmote(event, args, 1);
                    setEmote(event, args, 2);
                }


            event.reply("The emotes have been set to " +
                    (TreasureHuntUtils.emote[0] == null ? TreasureHuntUtils.emoji[0] : TreasureHuntUtils.emote[0].getAsMention()) +
                    (TreasureHuntUtils.emote[1] == null ? TreasureHuntUtils.emoji[1] : TreasureHuntUtils.emote[1].getAsMention()) +
                    (TreasureHuntUtils.emote[2] == null ? TreasureHuntUtils.emoji[2] : TreasureHuntUtils.emote[2].getAsMention()));
        }
    }

    private void setEmote(CommandEvent commandEvent, String[] args, int i) {

        if (args[i].length() < 18) {
            TreasureHuntUtils.emoji[i] = new Activity.Emoji(args[0]).getAsMention();

        } else {
            String emote =  args[i].replaceAll("[^\\d.]", "");
            TreasureHuntUtils.emote[i] = commandEvent.getGuild().getEmoteById(emote.substring(emote.length()-18));
        }
    }

}
