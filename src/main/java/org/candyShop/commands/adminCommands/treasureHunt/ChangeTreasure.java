package org.candyShop.commands.adminCommands.treasureHunt;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.command.impl.CommandClientImpl;
import org.candyShop.commands.Treasure;
import org.candyShop.helpers.CandyBotUtils;
import org.candyShop.helpers.TreasureHuntUtils;

import java.util.ArrayList;
import java.util.List;

public class ChangeTreasure extends Command {

    public ChangeTreasure() {
        this.name = "setTreasureName";
    }

    @Override
    protected void execute(CommandEvent event) {

        //TODO

        boolean access = CandyBotUtils.isAdmin(event.getMember());

        if (access) {

            String[] args = event.getArgs().split(" ");


            List<Object> listeners = event.getJDA().getRegisteredListeners();

            for (Object o : listeners) {
                if (o instanceof CommandClientImpl) {
                    CommandClientImpl client = (CommandClientImpl) o;

                    ArrayList<String> names = new ArrayList<>();

                    for (Command c : client.getCommands()) {

                        if (c instanceof TreasureHunt) {
                            names.add(c.getName());
                        }
                        if (c instanceof Treasure) {
                            names.add(c.getName());
                        }
                        if (c instanceof TreasureHuntRate) {
                            names.add(c.getName());
                        }
                        if (c instanceof GiveTreasure) {
                            names.add(c.getName());
                        }

                    }

                    for (String s : names) {
                        client.removeCommand(s);
                    }

                    TreasureHuntUtils.treasureName = args[0];

                    client.addCommand(new Treasure(args[0]));
                    client.addCommand(new TreasureHunt(args[0] + "hunt"));
                    client.addCommand(new TreasureHuntRate(args[0] + "rate"));
                    client.addCommand(new GiveTreasure("give" + args[0]));


                }
            }
        }
    }
}
