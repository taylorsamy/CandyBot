package org.candyShop.commands.adminCommands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;

public class EggHuntRate extends Command {

    public EggHuntRate() {
        this.name = "egghuntrate";
        this.userPermissions = new Permission[1];
        this.userPermissions[0] = Permission.MANAGE_ROLES;
    }
    @Override
    protected void execute(CommandEvent commandEvent) {

       EggHunt.frequency = Integer.parseInt( commandEvent.getArgs().split(" ")[0]);
       commandEvent.reply("The frequency of eggs has been set to 1 in every " + EggHunt.frequency + " messages");

    }
}
