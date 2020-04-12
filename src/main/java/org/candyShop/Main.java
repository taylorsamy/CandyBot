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
import org.candyShop.commands.adminCommands.ActivityCheck;
import org.candyShop.commands.adminCommands.EggHunt;
import org.candyShop.commands.adminCommands.EggHuntRate;
import org.candyShop.commands.adminCommands.GiveEgg;
import org.candyShop.commands.truthOrDare.TruthOrDare;
import org.candyShop.eventListeners.MessageRecievedListener;
import org.candyShop.eventListeners.ReactionListener;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder();

        client.useDefaultGame();
        client.setOwnerId("127899667584385024");
        client.setPrefix("!");
//        client.addCommand(new Test());
//        client.addCommand(new TruthOrDare());
//        client.addCommand(new ActivityCheck());
        client.addCommand(new EggHunt());
        client.addCommand(new EggHuntRate());
        client.addCommand(new GiveEgg());

        JDA jda = JDABuilder.create(args[0], GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))

                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Easter Egg Hunt"))
//                .addEventListeners(waiter, new PresenceListener(), client.build())
                .addEventListeners(waiter, new MessageRecievedListener(), client.build())
                .build();


    }
}
