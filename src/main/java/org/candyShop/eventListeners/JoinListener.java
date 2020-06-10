package org.candyShop.eventListeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.io.File;

public class JoinListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        Guild guild = event.getGuild();
        Member member = event.getMember();
        File file = new File("src/main/resources/images/candyWelcome.png");

        guild.getTextChannelById(640609641683746898L).sendMessage(":candy::lollipop:Hey " + member.getAsMention() +" :candy::lollipop:\n" +
                "Please take a moment to read through the "+ guild.getTextChannelById(626471687935885332L).getAsMention() +" A member of the Candy Crew will be with you shortly :sparkles:").addFile(file).queue();
//        guild.getTextChannelById(640609641683746898L).sendFile(file).queue();

    }
}
