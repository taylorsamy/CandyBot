package org.candyShop.eventListeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.candyShop.commands.adminCommands.EggHunt;

import javax.annotation.Nonnull;
import java.util.Random;

public class MessageRecievedListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {

        if (!event.getAuthor().isBot()) {
            Random rand = new Random(System.currentTimeMillis());
            int chance = rand.nextInt(EggHunt.frequency) + 1;

            System.out.println(chance);

            if ( chance == 1) {
                String messageID = event.getMessageId();

                ReactionListener listener = new ReactionListener(messageID);
                event.getMessage().addReaction("U+1f95a").complete();
                event.getJDA().addEventListener(listener);
            }
        }
    }
}
