package org.candyShop.commands.adminCommands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emote;
import org.candyShop.helpers.CandyBotUtils;
import org.candyShop.helpers.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreasureHunt extends Command {

    //TODO finish roles
    //TODO add start/stop commands
    //TODO make embeds for TreasureHunt, Treasure

    //TODO save all of this static information into a file, and reload it when bot restarts.

    public static int frequency = 1;
    public static int specialFreq = 2;

    public static String treasureName = "Candy";

    public static Emote[] emote = new Emote[3];
    public static String[] emoji = new String[3];

    public static Map<Integer, String> roles = new HashMap<>();

    public TreasureHunt(String name) {
        this.name = name;
        this.userPermissions = new Permission[1];
        this.userPermissions[0] = Permission.MANAGE_ROLES;
        emoji[0] = "🍬";
    }

    @Override
    protected void execute(CommandEvent event) {
        boolean access = CandyBotUtils.isAdmin(event.getMember());

        if (access) {

            JSONObject obj = FileUtils.readJSON("treasure");
            if (obj != null) {
                JSONArray members = (JSONArray) obj.get("Members");

                for (Object o : members) {
                    if (o instanceof JSONObject) {
                        event.reply(((JSONObject) o).get("Content").toString());
                    }
                }
            }
        }
    }
}
