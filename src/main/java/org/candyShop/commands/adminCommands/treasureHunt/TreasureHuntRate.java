package org.candyShop.commands.adminCommands.treasureHunt;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import org.candyShop.helpers.CandyBotUtils;
import org.candyShop.helpers.TreasureHuntUtils;

public class TreasureHuntRate extends Command {

    public TreasureHuntRate(String name) {
        this.name = name;
        this.userPermissions = new Permission[1];
        this.userPermissions[0] = Permission.MANAGE_ROLES;
    }

    @Override
    protected void execute(CommandEvent event) {

        boolean access = CandyBotUtils.isAdmin(event.getMember());
        try {
            if (access) {

                String[] args = event.getArgs().split(" ");
                if (args.length > 1 || !args[0].matches("^[0-9]*$")) {
                    event.reply("Incorrect Usage.");

                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("!" + name);
                    eb.addField("Usage", "", false);
                    eb.addField("!" + name + " <number>",
                            "Example: !" + name + " 10", false);
                    event.reply(eb.build());
                    return;
                }


                TreasureHuntUtils.frequency = Integer.parseInt(event.getArgs().split(" ")[0]);
                event.reply("The frequency of eggs has been set to 1 in every " + TreasureHuntUtils.frequency + " messages");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
