package org.candyShop.commands.adminCommands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import org.candyShop.helpers.CandyBotUtils;

public class TreasureHuntRate extends Command {

    public TreasureHuntRate(String name) {
        this.name = name;
        this.userPermissions = new Permission[1];
        this.userPermissions[0] = Permission.MANAGE_ROLES;
    }

    @Override
    protected void execute(CommandEvent event) {

        boolean access = CandyBotUtils.isAdmin(event.getMember());

        if (access) {
            TreasureHunt.frequency = Integer.parseInt(event.getArgs().split(" ")[0]);
            event.reply("The frequency of eggs has been set to 1 in every " + TreasureHunt.frequency + " messages");
        }
    }
}
