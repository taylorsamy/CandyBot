package org.candyShop.commands.truthOrDare;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.candyShop.eventListeners.ReactionListener;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;
import java.util.Random;

public class Play extends Command {

    public Play() {
        this.name = "play";
    }

    @Override
    protected void execute(CommandEvent event) {

        for (Object o : event.getJDA().getEventManager().getRegisteredListeners()) {
            if (o instanceof ReactionListener) {

//                List<Member> players = ((ReactionListener) o).getMembers();

                Random random = new Random();
//                Member player = players.get(random.nextInt(players.size()));


//                event.reply("Let's Play! There are " + players.size() + " players. It's " + player.getEffectiveName() + "'s turn! type !truthordare truth or !truthordare dare");


            }
        }
    }
}
