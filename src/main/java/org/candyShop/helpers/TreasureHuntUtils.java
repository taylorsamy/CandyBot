package org.candyShop.helpers;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.restaction.RoleAction;
import net.dv8tion.jda.api.requests.restaction.order.RoleOrderAction;
import org.candyShop.Main;
import org.candyShop.commands.adminCommands.TreasureHunt;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;


public class TreasureHuntUtils {

    public void addRoles(Guild guild, String memberID) {
        Member m = guild.getMemberById(memberID);

        JSONObject treasure = FileUtils.readJSON("treasure");
        JSONArray list = (JSONArray) treasure.get("Members");
        long numTreasure = 0;
        for (Object o : list) {
            if (o instanceof JSONObject) {
                if (((JSONObject) o).get("MemberID").equals(memberID)) {
                   numTreasure = (long) ((JSONObject) o).get("Content");
                }
            }
        }

        if (numTreasure % 10 == 0) {

            int level = (int) Math.floorDiv(numTreasure, 10);

            if (!TreasureHunt.roles.containsKey(level)) {
                createRole(guild, level);
            } else {
                String roleID = TreasureHunt.roles.get(10);
                Role r = guild.getRoleById(roleID);

                TreasureHunt.roles.forEach((integer, s) -> {
                    guild.removeRoleFromMember(m, guild.getRoleById(s)).queue();
                });
                guild.addRoleToMember(m, r).queue();
            }
        }






    }


    private void createRole(Guild guild, int level) { //TODO finish. this  method not yet started.
        RoleAction roleAction = guild.createRole();
        roleAction.setName("Test role");
        Role role = roleAction.complete();

        RoleOrderAction roleOrderAction = guild.modifyRolePositions();
        roleOrderAction.selectPosition(guild.getRoleById(role.getId()));
        List<Role> roles = roleOrderAction.getCurrentOrder();

        int pos = 0;
        for (Role r : roles) {
            if (r.getIdLong() == Main.ADMINION_ID) {
                pos = roles.indexOf(r);
            }
        }

        roleOrderAction.moveTo(pos - 1);
        roleOrderAction.queue();
    }
}
