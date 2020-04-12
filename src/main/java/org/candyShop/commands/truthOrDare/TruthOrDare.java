package org.candyShop.commands.truthOrDare;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.candyShop.eventListeners.ReactionListener;

public class TruthOrDare extends Command {

    public TruthOrDare() {
        this.name = "truthordare";

        Command[] children = new Command[3];
        children[0] = new Play();
        children[1] = new Truth();
        children[2] = new Dare();

        this.children = children;

    }

    @Override
    protected void execute(CommandEvent event) {


        event.reply("Let's play truth or dare! React to this message to join", message -> {
            ReactionListener listener = new ReactionListener(message.getId());
            event.getJDA().addEventListener(listener);

        });

    }
}
