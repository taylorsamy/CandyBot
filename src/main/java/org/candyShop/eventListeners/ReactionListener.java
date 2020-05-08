package org.candyShop.eventListeners;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.candyShop.commands.adminCommands.TreasureHunt;
import org.candyShop.helpers.FileUtils;

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
                    if (TreasureHunt.emote[2] != null) {
                        event.getTextChannel().retrieveMessageById(messageID).complete().editMessage(TreasureHunt.emote[2].getAsMention()).complete();
                    } else {
                        event.getTextChannel().retrieveMessageById(messageID).complete().delete().queue();
                    }
                }
                event.getChannel().sendMessage(TreasureHunt.treasureName + " found by " + event.getUser().getAsTag()).queue();

                event.getChannel().retrieveMessageById(messageID).complete().clearReactions().complete();


                FileUtils.writeJSON("treasure", event.getUserId(), 1);


                event.getJDA().removeEventListener(this);

            }
        }
    }

}
