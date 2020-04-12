package org.candyShop.eventListeners;

import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PresenceListener extends ListenerAdapter {
    @Override
    public void onGenericUserPresence(@Nonnull GenericUserPresenceEvent event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        event.getGuild().getTextChannelById(683800435844186220L).sendMessage(event.getMember().getEffectiveName() + " Has changed their presence to " + event.getMember().getOnlineStatus()).queue(message -> {
           message.getChannel().sendMessage(dtf.format(now)).queue();
       });
    }

}
