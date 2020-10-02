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
import java.util.Random;


public class TreasureHuntUtils {

    public static int frequency = 50;
    public static int specialFreq = 6;

    public static String treasureName = "Candy";

    public static Emote[] emote = new Emote[3];
    public static String[] emoji = new String[3];

    public static Map<Integer, String> roles = new HashMap<>();

//    public static Member lastCollected = null;


    private static Color[] colours = new Color[38];

    static {
//        colours[0] = new Color(0x37, 0x63, 0x69);
//        colours[1] = new Color(0xf1, 0xa9, 0x5b);
//        colours[2] = new Color(0xe5, 0xe3, 0x3F);
//        colours[3] = new Color(0x24, 0xc4, 0x42);
//        colours[4] = new Color(0x35, 0x7E, 0xCD);
//        colours[5] = new Color(0x4C, 0x05, 0x94);
//        colours[6] = new Color(0xED, 0x53, 0x14);
//        colours[7] = new Color(0xFF, 0xB9, 0x2A);
//        colours[8] = new Color(0xFE, 0xEB, 0x51);
//        colours[9] = new Color(0x9B, 0xCA, 0x3E);
//        colours[10] = new Color(0x3A, 0xBB, 0xC9);
//        colours[11] = new Color(0x66, 0x6D, 0xCB);
//        colours[12] = new Color(0xA0, 0x33, 0x2F);
//        colours[13] = new Color(0xCB, 0x7B, 0x42);
//        colours[14] = new Color(0xDC, 0xBF, 0x53);
//        colours[15] = new Color(0x93, 0xA8, 0x64);
//        colours[16] = new Color(0x5E, 0x89, 0xA8);
//        colours[17] = new Color(0x52, 0x4A, 0x66);
//        colours[18] = new Color(0xEF, 0xEF, 0xA8);
//        colours[19] = new Color(0xC2, 0xE0, 0xAE);


        colours[0] = new Color(0xf5, 0x90, 0x55);
        colours[1] = new Color(0x27, 0x28, 0x2e);
        colours[2] = new Color(0xa8, 0x32, 0x00);
        colours[3] = new Color(0xa1, 0xf0, 0x24);
        colours[4] = new Color(0xb8, 0xb8, 0xc7);
        colours[5] = new Color(0xf7, 0x71, 0x23);
        colours[6] = new Color(0x36, 0x91, 0x00);
        colours[7] = new Color(0xfa, 0xbd, 0x84);
        colours[8] = new Color(0x7b, 0x06, 0xe4);
        colours[9] = new Color(0xeb, 0xeb, 0xeb);
        colours[10] = new Color(0xf0, 0xb5, 0x24);
        colours[11] = new Color(0xa8, 0x3f, 0xff);
        colours[12] = new Color(0x56, 0x8b, 0x01);
        colours[13] = new Color(0x3c, 0x15, 0x63);
        colours[14] = new Color(0xff, 0x5e, 0x00);
        colours[15] = new Color(0xbd, 0x45, 0x00);
        colours[16] = new Color(0x76, 0xd8, 0x3b);
        colours[17] = new Color(0x40, 0x40, 0x42);
        colours[18] = new Color(0xda, 0x9c, 0x00);
        colours[19] = new Color(0x40, 0x02, 0x74);
        colours[20] = new Color(0xff, 0xac, 0x5d);
        colours[21] = new Color(0xdd, 0xdd, 0xff);
        colours[22] = new Color(0x1e, 0x5c, 0x00);
        colours[23] = new Color(0xd3, 0x50, 0x04);
        colours[24] = new Color(0x8d, 0xe, 0x54);
        colours[25] = new Color(0xab, 0x63, 0xe6);
        colours[26] = new Color(0xd4, 0x5b, 0x18);
        colours[27] = new Color(0x83, 0x83, 0x8b);
        colours[28] = new Color(0x9a, 0x61, 0xf3);
        colours[29] = new Color(0xda, 0x7e, 0x2d);
        colours[30] = new Color(0xff, 0x73, 0x00);
        colours[31] = new Color(0x63, 0xfc, 0xc9);
        colours[32] = new Color(0x62, 0x0d, 0xa8);
        colours[33] = new Color(0x6b, 0xb3, 0x3e);
        colours[34] = new Color(0x80, 0x4a, 0xaa);
        colours[35] = new Color(0xe4, 0x80, 0x47);
        colours[36] = new Color(0x81, 0x3f, 0x36);
        colours[37] = new Color(0x50, 0xd3, 0x00);


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
                } catch (Exception e) {
                    System.out.println("roles exception");
                    e.printStackTrace();
                }
            }

            guild.addRoleToMember(m, r).queue();


        }
    }


    private static void createRole(Guild guild, int level) { //TODO finish. this  method not yet started.
        RoleAction roleAction = guild.createRole();
        roleAction.setName(">" + level * 10);
        try {
            roleAction.setColor(colours[level - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            int R = (int) (Math.random() * 256);
            int G = (int) (Math.random() * 256);
            int B = (int) (Math.random() * 256);
            Color color = new Color(R, G, B); //random color, but can be bright or dull

//to get rainbow, pastel colors
            Random random = new Random();
            final float hue = random.nextFloat();
            final float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
            final float luminance = 1.0f; //1.0 for brighter, 0.0 for black
            color = Color.getHSBColor(hue, saturation, luminance);
            roleAction.setColor(color);

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
