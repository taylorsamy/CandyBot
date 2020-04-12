package org.candyShop.commands.adminCommands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import org.candyShop.helpers.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.TreeMap;

public class EggHunt extends Command {

   public static TreeMap<String, Integer> eggs = new TreeMap<>();
   public static int frequency = 50;


    public EggHunt() {
        this.name = "egghunt";
        this.userPermissions = new Permission[1];
        this.userPermissions[0] = Permission.MANAGE_ROLES;
    }

    @Override
    protected void execute(CommandEvent event) {
        eggs.forEach((s, integer) -> {

            User u = event.getJDA().getUserById(s);

            event.reply( u.getAsMention() + " has collected " + integer + " eggs!");

        });

    }
}
