package org.candyShop;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.candyShop.commands.Test;
import org.candyShop.commands.Treasure;
import org.candyShop.commands.adminCommands.*;
import org.candyShop.eventListeners.MessageRecievedListener;
import org.candyShop.eventListeners.TestListener;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    //guilds
    public static final long TESTY_TESY_ID = 683800435844186135L;
    public static final long CANDY_SHOP_ID = 625986582469410826L;
    //roles
    public static final long ADMINION_ID = 629021975788126219L;
    public static final long ADMIN_ID = 625988757333475328L;

    public static void main(String[] args) throws LoginException {
        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder();

        client.useDefaultGame();
        client.setOwnerId("127899667584385024");
        client.setPrefix("!");
        client.addCommand(new Test());

        client.addCommand(new TreasureHunt("treasureHunt")); //done
        client.addCommand(new TreasureHuntRate("TreasureRate")); //no change needed
        client.addCommand(new GiveTreasure("giveTreasure")); //done
        client.addCommand(new Treasure("treasure")); //done
        client.addCommand(new ChangeTreasure());
        client.addCommand(new SetEmote());

        client.setActivity(Activity.watching("All the littles"));




        JDA jda = JDABuilder.create(args[0], GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))

                .setStatus(OnlineStatus.ONLINE)


//                .addEventListeners(waiter, new PresenceListener(), client.build())
                .addEventListeners(waiter,new MessageRecievedListener(), client.build(), new TestListener())
                .build();






    }
}
