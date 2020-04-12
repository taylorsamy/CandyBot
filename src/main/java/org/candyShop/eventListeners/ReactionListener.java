package org.candyShop.eventListeners;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.candyShop.commands.adminCommands.EggHunt;
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

            System.out.println(event.getReactionEmote().getAsCodepoints());
            if (event.getMessageId().equals(messageID)) {
                event.getChannel().sendMessage("Egg found").queue();

                event.getChannel().retrieveMessageById(messageID).complete().clearReactions().complete();

                if (EggHunt.eggs.containsKey(event.getUserId())) {
                   EggHunt.eggs.put(event.getUserId(), EggHunt.eggs.get(event.getUserId()) + 1);
                } else {
                    EggHunt.eggs.put(event.getUserId(),1);
                }
                event.getJDA().removeEventListener(this);

            }
        }
    }

}
