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

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreasureHunt extends Command {

    //TODO make embeds for TreasureHunt, Treasure

    //TODO save all of this static information into a file, and reload it when bot restarts.


    public TreasureHunt(String name) {
        this.name = name;
        this.userPermissions = new Permission[1];
        this.userPermissions[0] = Permission.MANAGE_ROLES;
        TreasureHuntUtils.emoji[0] = "🍬";
    }

    @Override
    protected void execute(CommandEvent event) {
        boolean access = CandyBotUtils.isAdmin(event.getMember());

        if (access) {

            try {

                Map<String, Long> results = new HashMap<>();

                JSONObject obj = FileUtils.readJSON("treasure");
                if (obj != null) {
                    JSONArray members = (JSONArray) obj.get("Members");

                    for (Object o : members) {
                        if (o instanceof JSONObject) {

                            long content = (long) ((JSONObject) o).get("Content");
                            String memberID = (String) ((JSONObject) o).get("MemberID");
                            results.put(memberID, content);
                        }
                    }

                    LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>();

                    results.entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));


                    EmbedBuilder eb = new EmbedBuilder();

                    reverseSortedMap.forEach((s, aLong) -> {

                        eb.setColor(Color.green);
                        eb.setTitle(TreasureHuntUtils.treasureName + " hunt results");


                        eb.addField(event.getGuild().getMemberById(s).getEffectiveName(), String.valueOf(aLong), false);



                        eb.setFooter("Thanks for playing!");

                    });

                    event.reply(eb.build());

                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
