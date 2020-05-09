package org.candyShop.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.candyShop.Main;
import org.candyShop.helpers.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Treasure extends Command {

    public Treasure(String name) {
        this.name = name;
        this.aliases = new String[1];
        this.aliases[0] = name + "s";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {


            boolean found = false;

            JSONObject treasure = FileUtils.readJSON("treasure");

            if (treasure != null) {
                 JSONArray list = (JSONArray) treasure.get("Members");

                 for (Object o : list) {
                     if (o instanceof JSONObject) {
                         if (((JSONObject) o).get("MemberID").equals(commandEvent.getAuthor().getId())) {
                             found = true;
                             commandEvent.reply(commandEvent.getMember().getAsMention() + " now has " + ((JSONObject) o).get("Content") + " " + name + "!");
                         }
                     }
                 }
             }

            if (!found) {
                commandEvent.reply(commandEvent.getMember().getAsMention() + " has no " + name + " yet.");
            }

        }


}
