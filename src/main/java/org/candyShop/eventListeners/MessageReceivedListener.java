package org.candyShop.eventListeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.candyShop.Main;
import org.candyShop.helpers.TreasureHuntUtils;

import javax.annotation.Nonnull;
import java.util.Random;

public class MessageReceivedListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {


//        if (event.getGuild().getIdLong() == Main.TESTY_TESY_ID) {

            if (!event.getAuthor().isBot()) {
                Random rand = new Random(System.currentTimeMillis());
                int chance = rand.nextInt(TreasureHuntUtils.frequency) + 1;

                if (chance == 1) {
                    String messageID = event.getMessageId();
                    ReactionListener listener = new ReactionListener(messageID);
                    if (rand.nextInt(TreasureHuntUtils.specialFreq + 1) == 1) {

                        if (TreasureHuntUtils.emote[1] != null) {

                            String newMessageID = event.getChannel().sendMessage(TreasureHuntUtils.emote[1].getAsMention()).complete().getId();
                            event.getTextChannel().retrieveMessageById(newMessageID).complete().addReaction(TreasureHuntUtils.emote[1]).complete();
                            listener = new ReactionListener(newMessageID);

                        } else {
                            if (TreasureHuntUtils.emote[0] != null) {
                                event.getMessage().addReaction(TreasureHuntUtils.emote[0]).complete();
                            } else {
                                event.getMessage().addReaction(TreasureHuntUtils.emoji[0]).complete();
                            }
                        }
                    } else {
                        if (TreasureHuntUtils.emote[0] != null) {
                            event.getMessage().addReaction(TreasureHuntUtils.emote[0]).complete();
                        } else {
                            event.getMessage().addReaction(TreasureHuntUtils.emoji[0]).complete();
                        }
                    }
                    event.getJDA().addEventListener(listener);
                }
            }
        }
//    }

    public void terminate(JDA jda) {
        jda.removeEventListener(this);
    }
}
