package org.candyShop.helpers;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import org.candyShop.Main;

public class CandyBotUtils {

    public static boolean isAdmin(Member member) {
        boolean access = false;

        if (member.getGuild().getIdLong() == Main.TESTY_TESY_ID) {
            return true;
        }


        for (Role r : member.getRoles()) {
            access = (r.getIdLong() == Main.ADMIN_ID) || (r.getIdLong() == Main.ADMINION_ID);
            if (access) {
                break;
            }
        }
        return access;
    }
}
