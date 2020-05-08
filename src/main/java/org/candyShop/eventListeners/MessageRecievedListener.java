package org.candyShop.eventListeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.candyShop.Main;
import org.candyShop.commands.adminCommands.TreasureHunt;

import javax.annotation.Nonnull;
import java.util.Random;

public class MessageRecievedListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {

        if (event.getGuild().getIdLong() == Main.TESTY_TESY_ID) { //TODO

            if (!event.getAuthor().isBot()) {
                Random rand = new Random(System.currentTimeMillis());
                int chance = rand.nextInt(TreasureHunt.frequency) + 1;

                if (chance == 1) {
                    String messageID = event.getMessageId();
                    ReactionListener listener = new ReactionListener(messageID);
                    if (rand.nextInt(TreasureHunt.specialFreq) == 1) {

                        if (TreasureHunt.emote[1] != null) {

                            String newMessageID = event.getChannel().sendMessage(TreasureHunt.emote[1].getAsMention()).complete().getId();
                            event.getTextChannel().retrieveMessageById(newMessageID).complete().addReaction(TreasureHunt.emote[1]).complete();
                            listener = new ReactionListener(newMessageID);

                        } else {
                            if (TreasureHunt.emote[0] != null) {
                                event.getMessage().addReaction(TreasureHunt.emote[0]).complete();
                            } else {
                                event.getMessage().addReaction(TreasureHunt.emoji[0]).complete();
                            }
                        }
                    } else {
                        if (TreasureHunt.emote[0] != null) {
                            event.getMessage().addReaction(TreasureHunt.emote[0]).complete();
                        } else {
                            event.getMessage().addReaction(TreasureHunt.emoji[0]).complete();
                        }
                    }
                    event.getJDA().addEventListener(listener);
                }
            }
        }
    }
}
