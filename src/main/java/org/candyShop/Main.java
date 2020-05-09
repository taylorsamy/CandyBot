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
import org.candyShop.commands.adminCommands.JokeWarning;
import org.candyShop.commands.adminCommands.treasureHunt.*;
import org.candyShop.helpers.TreasureHuntUtils;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    //guilds
    public static final long TESTY_TESY_ID = 683800435844186135L;
    public static final long CANDY_SHOP_ID = 625986582469410826L;
    //roles
    public static final long ADMINION_ID = 629021975788126219L;
    public static final long ADMIN_ID = 625988757333475328L;
    public static final long CANDY_BOT_ID = 698816939270733934L;

    public static void main(String[] args) throws LoginException {
        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder();

        client.useDefaultGame();
        client.setOwnerId("127899667584385024");
        client.setPrefix("!");
        client.addCommand(new Test());

        client.addCommand(new TreasureHunt(TreasureHuntUtils.treasureName +"Hunt")); //done
        client.addCommand(new TreasureHuntRate(TreasureHuntUtils.treasureName +"Rate")); //no change needed
        client.addCommand(new GiveTreasure("give" +TreasureHuntUtils.treasureName )); //done
        client.addCommand(new Treasure(TreasureHuntUtils.treasureName)); //done
        client.addCommand(new StartTreasureHunt());
        client.addCommand(new StopTreasureHunt());
        client.addCommand(new ChangeTreasure());
        client.addCommand(new SetEmote());
        client.addCommand(new TreasureHelp());
        client.addCommand(new JokeWarning());

        client.setActivity(Activity.watching("All the littles hunt for treasure!"));


        JDA jda = JDABuilder.create(args[0], GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))

                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(waiter, client.build())
                .build();


    }
}
