package org.candyShop.commands.adminCommands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public class GiveEgg extends Command {


    public GiveEgg() {
        this.name = "giveegg";
        this.userPermissions = new Permission[1];
        this.userPermissions[0] = Permission.MANAGE_ROLES;
    }
    @Override
    protected void execute(CommandEvent commandEvent) {
        String[] args = commandEvent.getArgs().split(" ");
        String userID = args[0].replaceAll("[^\\d.]", "");
        Member m = commandEvent.getGuild().getMemberById(userID);

        EggHunt.eggs.forEach((s, integer) -> {

            if (EggHunt.eggs.containsKey(commandEvent.getMember().getId())) {
                EggHunt.eggs.put(commandEvent.getMember().getId(), EggHunt.eggs.get(commandEvent.getMember().getId()) + 1);
            } else {
                EggHunt.eggs.put(commandEvent.getMember().getId(),1);
            }

        });

        commandEvent.reply(m.getAsMention() + " now has " +  EggHunt.eggs.get(commandEvent.getMember().getId()) + " eggs!" );
    }
}
