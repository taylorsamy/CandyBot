package org.candyShop.commands.adminCommands.treasureHunt;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import org.candyShop.helpers.CandyBotUtils;
import org.candyShop.helpers.FileUtils;
import org.candyShop.helpers.TreasureHuntUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GiveTreasure extends Command {


    public GiveTreasure(String name) {
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
                String userID = args[0].replaceAll("[^\\d.]", "");

                if (args.length != 2 || userID.length() != 18 || !args[1].matches("^[0-9]*$")) {
                    event.reply("Incorrect Usage.");

                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("!" + name);
                    eb.addField("Usage", "", false);
                    eb.addField("!" + name + " <@person> <number>",
                            "Example: !" + name + event.getMember().getAsMention() + " 10", false);
                    event.reply(eb.build());
                    return;
                }

                Member m = event.getGuild().getMemberById(userID);
                FileUtils.writeJSON("treasure", userID, Integer.parseInt(args[1]));

                JSONObject treasure = FileUtils.readJSON("treasure");

                JSONArray list = (JSONArray) treasure.get("Members");

                list.forEach(o -> {

                    if (o instanceof JSONObject) {
                        if (((JSONObject) o).get("MemberID").equals(userID)) {

                            event.reply(m.getAsMention() + " now has " + ((JSONObject) o).get("Content") + " " + name.substring(4) + "!");

                        }
                    }

                });
                TreasureHuntUtils.addRoles(event.getGuild(), userID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
