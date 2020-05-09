package org.candyShop.eventListeners;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.candyShop.helpers.FileUtils;
import org.candyShop.helpers.TreasureHuntUtils;

import javax.annotation.Nonnull;

public class ReactionListener extends ListenerAdapter {
    private String messageID;

    public ReactionListener(String messageID) {
        this.messageID = messageID;

    }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {

        if (!event.getUser().isBot()) {

            if (event.getMessageId().equals(messageID)) {

                if (event.getTextChannel().retrieveMessageById(messageID).complete().getAuthor().isBot()) {
                    if (TreasureHuntUtils.emote[2] != null) {
                        event.getTextChannel().retrieveMessageById(messageID).complete().editMessage(TreasureHuntUtils.emote[2].getAsMention()).complete();

                    } else {
                        event.getTextChannel().retrieveMessageById(messageID).complete().delete().queue();
                    }
                    FileUtils.writeJSON("treasure", event.getUserId(), 10);
                    event.getChannel().sendMessage("A very special " + TreasureHuntUtils.treasureName + " was found by " + event.getUser().getAsMention()).queue();

                } else {
                    event.getChannel().sendMessage(TreasureHuntUtils.treasureName + " found by " + event.getUser().getAsMention()).queue();

                    event.getChannel().retrieveMessageById(messageID).complete().clearReactions().complete();


                    FileUtils.writeJSON("treasure", event.getUserId(), 1);
                }

                TreasureHuntUtils.addRoles(event.getGuild(), event.getUserId());

                event.getJDA().removeEventListener(this);
            }
        }
    }

}
