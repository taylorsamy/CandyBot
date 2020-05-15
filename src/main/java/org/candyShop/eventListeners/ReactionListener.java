package org.candyShop.eventListeners;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.candyShop.helpers.FileUtils;
import org.candyShop.helpers.TreasureHuntUtils;

import javax.annotation.Nonnull;
import java.util.Random;

public class ReactionListener extends ListenerAdapter {
    private String messageID;
    private int find = 0;
    private int chance = new Random().nextInt(3);


    public ReactionListener(String messageID) {
        this.messageID = messageID;

    }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {


        if (!event.getUser().isBot()) {

            if (event.getMessageId().equals(messageID)) {

                System.out.println(find + " :: " + chance + "::" + TreasureHuntUtils.lastCollected.getEffectiveName());
                if (event.getMember().getIdLong() != TreasureHuntUtils.lastCollected.getIdLong()) {



                    Random random = new Random();
                    if (chance == find) {
                        TreasureHuntUtils.lastCollected = event.getMember();
                        if (event.getTextChannel().retrieveMessageById(messageID).complete().getAuthor().isBot()) {
                            if (TreasureHuntUtils.emote[2] != null) {
                                event.getTextChannel().retrieveMessageById(messageID).complete().editMessage(TreasureHuntUtils.emote[2].getAsMention()).complete();

                            } else {
                                event.getTextChannel().retrieveMessageById(messageID).complete().delete().queue();
                            }
                            FileUtils.writeJSON("treasure", event.getUserId(), 5);
                            event.getChannel().sendMessage("A very special " + TreasureHuntUtils.treasureName + " was found by " + event.getUser().getAsMention()).queue();

                        } else {
                            event.getChannel().sendMessage(TreasureHuntUtils.treasureName + " found by " + event.getUser().getAsMention()).queue();

                            FileUtils.writeJSON("treasure", event.getUserId(), 1);
                        }
                        event.getChannel().retrieveMessageById(messageID).complete().clearReactions().complete();
                        TreasureHuntUtils.addRoles(event.getGuild(), event.getUserId());
                        event.getJDA().removeEventListener(this);
                    } else {
                        int rand = random.nextInt(3);
                        if (rand == 0) {
                            event.getChannel().sendMessage("The " + TreasureHuntUtils.treasureName + " slips out of " + event.getMember().getEffectiveName()+"'s tired arms").queue();

                        } else if (rand == 1) {
                            event.getChannel().sendMessage("There is no room in " + event.getMember().getEffectiveName()+"'s backpack for the " + TreasureHuntUtils.treasureName).queue();

                        } else if (rand == 2) {
                            event.getChannel().sendMessage("The " + TreasureHuntUtils.treasureName + " doesnt budge!").queue();
                        }
                        find++;
                    }


                } else { //member collected the last one
                    event.getChannel().sendMessage("The " + TreasureHuntUtils.treasureName + " slips out of " + event.getMember().getEffectiveName()+"'s tired arms").queue();
                }
            }
        }
    }

}
