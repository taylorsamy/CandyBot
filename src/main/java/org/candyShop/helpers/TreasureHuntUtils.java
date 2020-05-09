package org.candyShop.helpers;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.restaction.RoleAction;
import net.dv8tion.jda.api.requests.restaction.order.RoleOrderAction;
import org.candyShop.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TreasureHuntUtils {

    public static int frequency = 50;
    public static int specialFreq = 10;

    public static String treasureName = "Candy";

    public static Emote[] emote = new Emote[3];
    public static String[] emoji = new String[3];

    public static Map<Integer, String> roles = new HashMap<>();


    private static Color[] colours = new Color[20];

    static{
        colours[0] = new Color(0xCE, 0x14, 0x1F);
        colours[1] = new Color(0xE5, 0x7C, 0x00);
        colours[2] = new Color(0xe5, 0xe3, 0x3F);
        colours[3] = new Color(0x24, 0xc4, 0x42);
        colours[4] = new Color(0x35, 0x7E, 0xCD);
        colours[5] = new Color(0x4C, 0x05, 0x94);
        colours[6] = new Color(0xED, 0x53, 0x14);
        colours[7] = new Color(0xFF, 0xB9, 0x2A);
        colours[8] = new Color(0xFE, 0xEB, 0x51);
        colours[9] = new Color(0x9B, 0xCA, 0x3E);
        colours[10] = new Color(0x3A, 0xBB, 0xC9);
        colours[11] = new Color(0x66, 0x6D, 0xCB);
        colours[12] = new Color(0xA0, 0x33, 0x2F);
        colours[13] = new Color(0xCB, 0x7B, 0x42);
        colours[14] = new Color(0xDC, 0xBF, 0x53);
        colours[15] = new Color(0x93, 0xA8, 0x64);
        colours[16] = new Color(0x5E, 0x89, 0xA8);
        colours[17] = new Color(0x52, 0x4A, 0x66);
        colours[18] = new Color(0xEF, 0xEF, 0xA8);
        colours[19] = new Color(0xC2, 0xE0, 0xAE);



    }


    public static void addRoles(Guild guild, String memberID) {
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


            if (numTreasure > 10) {

                int level = (int) Math.floorDiv(numTreasure, 10);

                if (!roles.containsKey(level)) {
                    createRole(guild, level);
                }
                String roleID = roles.get(level);
                Role r = guild.getRoleById(roleID);

//                roles.forEach((integer, s) -> guild.removeRoleFromMember(m, guild.getRoleById(s)).queue());

                if (level > 1) {
                    try {
                        Role oldRole = guild.getRoleById(roles.get(level - 1));
                        guild.removeRoleFromMember(m, oldRole).queue();
                    }catch (Exception e) {
                        System.out.println("roles exception");
                        e.printStackTrace();
                    }
                }

                guild.addRoleToMember(m, r).queue();


            }
    }


    private static void createRole(Guild guild, int level) { //TODO finish. this  method not yet started.
        RoleAction roleAction = guild.createRole();
        roleAction.setName(">" + level*10);
        try {
            roleAction.setColor(colours[level - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            roleAction.setColor(Color.WHITE);
        }

        Role role = roleAction.complete();
        roles.put(level, role.getId());

        RoleOrderAction roleOrderAction = guild.modifyRolePositions();
        roleOrderAction.selectPosition(guild.getRoleById(role.getId()));
        List<Role> roles = roleOrderAction.getCurrentOrder();

        int pos = 0;
        for (Role r : roles) {

            if (r.getIdLong() == Main.CANDY_BOT_ID || r.getIdLong() == 683800502206595092L) {
                pos = roles.indexOf(r);
            }
        }

        roleOrderAction.moveTo(pos - 1);
        roleOrderAction.queue();
    }

    public static void removeRoles(Guild guild) {

        roles.forEach((integer, s) -> {
            guild.getRoleById(s).delete().queue();
        });

    }
}
