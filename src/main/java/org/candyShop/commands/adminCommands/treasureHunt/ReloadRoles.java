package org.candyShop.commands.adminCommands.treasureHunt;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Role;
import org.candyShop.helpers.TreasureHuntUtils;

import java.util.List;

public class ReloadRoles extends Command {

    public ReloadRoles() {
        this.name = "reloadRoles";
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
       List<Role> roles =commandEvent.getGuild().getRoles();

       for (Role r : roles) {
           if (r.getName().startsWith(">")) {
               String temp = r.getName().substring(1);
               int level = Integer.parseInt(temp)/10;

               TreasureHuntUtils.roles.put(level, r.getId());
           }
       }
    }
}
