package org.candyShop.commands.adminCommands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Role;
import org.apache.commons.collections4.Bag;
import org.candyShop.Main;
import org.candyShop.helpers.CandyBotUtils;

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
        }
    }

        private void setEmote (CommandEvent commandEvent, String[]args,int i){

            if (args[i].length() < 18) {
                TreasureHunt.emoji[i] = new Activity.Emoji(args[0]).getAsMention();

            } else {
                TreasureHunt.emote[i] = commandEvent.getGuild().getEmoteById(args[i].replaceAll("[^\\d.]", ""));
            }
        }

}
