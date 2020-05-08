package org.candyShop.commands.adminCommands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ActivityCheck extends Command {

    public ActivityCheck() {
        this.name = "activity";
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) { //TODO add roles
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("src/main/resources/members.json")) {

           JSONObject root = (JSONObject) parser.parse(reader);
           JSONArray members = (JSONArray) root.get("members");
           members.forEach(o -> {
               StringBuilder sb = new StringBuilder();

               String id = (String) ((JSONObject)o).get("MemberID");
               User u = event.getGuild().getMemberById(id).getUser();

               sb.append(u.getAsTag()).append(" : ").append((String) ((JSONObject)o).get("Time"));

               event.reply(sb.toString());

           });

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
