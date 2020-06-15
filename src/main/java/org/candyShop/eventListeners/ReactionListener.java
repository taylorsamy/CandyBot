package org.candyShop.eventListeners;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.candyShop.helpers.FileUtils;
import org.candyShop.helpers.TreasureHuntUtils;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class ReactionListener extends ListenerAdapter {
    private String messageID;
//    private int find = 0;
//    private int chance = new Random().nextInt(2);

//    LinkedList<Integer> messageQueue = new LinkedList<>();


    public ReactionListener(String messageID) {
        this.messageID = messageID;
//        initQueue();


    }

//    private void initQueue() {
//        messageQueue.add(0);
//        messageQueue.add(1);
//        messageQueue.add(2);
//        messageQueue.add(3);
//        messageQueue.add(4);
//        messageQueue.add(5);
//        messageQueue.add(6);
//        Collections.shuffle(messageQueue);
//    }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {


        if (!event.getUser().isBot()) {

            if (event.getMessageId().equals(messageID)) {

//                System.out.println(find + " :: " + chance + "::" + TreasureHuntUtils.lastCollected.getEffectiveName());
//                if (event.getMember().getIdLong() != TreasureHuntUtils.lastCollected.getIdLong()) {


//                    if (chance == find) {
//                        TreasureHuntUtils.lastCollected = event.getMember();
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
//                    } else {
//                        sendTryAgainMessage(event);
//                        find++;
//                    }


//                } else { //member collected the last one
//                    sendTryAgainMessage(event);
//                }
            }
        }
    }
//
//    private void sendTryAgainMessage(@Nonnull MessageReactionAddEvent event) {
//        if (messageQueue.size() == 0) {
//            initQueue();
//        }
//        int rand = messageQueue.pop();
//
//
//        if (rand == 0) {
//            event.getChannel().sendMessage("The " + TreasureHuntUtils.treasureName + " slips out of " + event.getMember().getEffectiveName() + "'s tired arms").queue();
//
//        } else if (rand == 1) {
//            event.getChannel().sendMessage("There is no room in " + event.getMember().getEffectiveName() + "'s backpack for the " + TreasureHuntUtils.treasureName).queue();
//
//        } else if (rand == 2) {
//            event.getChannel().sendMessage("The " + TreasureHuntUtils.treasureName + " doesnt budge!").queue();
//
//        } else if (rand == 3) {
//            event.getChannel().sendMessage("It was a fake" + TreasureHuntUtils.treasureName + "! Back to searching!").queue();
//
//        } else if (rand == 4) {
//            event.getChannel().sendMessage("The sun is in " + event.getMember().getEffectiveName() + "'s eyes and someone else grabs the " + TreasureHuntUtils.treasureName + " before they can see it!").queue();
//        } else if (rand == 5) {
//            event.getChannel().sendMessage(event.getMember().getEffectiveName() + " has a sugar high and is too hyper to get the " + TreasureHuntUtils.treasureName + "!").queue();
//        } else if (rand == 6) {
//            event.getChannel().sendMessage(event.getMember().getEffectiveName() + " doesn't like that " + TreasureHuntUtils.treasureName + " so they put it back!").queue();
//        }
//    }

}
