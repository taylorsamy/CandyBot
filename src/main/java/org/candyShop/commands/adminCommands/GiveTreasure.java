package org.candyShop.commands.adminCommands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import org.candyShop.Main;
import org.candyShop.helpers.CandyBotUtils;
import org.candyShop.helpers.FileUtils;
import org.candyShop.helpers.TreasureHuntUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class GiveTreasure extends Command {


    public GiveTreasure(String name) {
        this.name = name;
        this.userPermissions = new Permission[1];
        this.userPermissions[0] = Permission.MANAGE_ROLES;
    }

    @Override
    protected void execute(CommandEvent event) {

        boolean access = CandyBotUtils.isAdmin(event.getMember());

        if (access) {
            String[] args = event.getArgs().split(" ");
            String userID = args[0].replaceAll("[^\\d.]", "");
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

            new TreasureHuntUtils().addRoles(event.getGuild(), userID);
        }
    }
}
