package org.candyShop.commands.adminCommands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.command.impl.CommandClientImpl;
import net.dv8tion.jda.api.entities.Role;
import org.candyShop.Main;
import org.candyShop.commands.Treasure;
import org.candyShop.helpers.CandyBotUtils;

import java.util.ArrayList;
import java.util.List;

public class ChangeTreasure extends Command {

    public ChangeTreasure() {
        this.name = "changetreasure";
    }

    @Override
    protected void execute(CommandEvent event) {

        if (event.getGuild().getIdLong() == Main.CANDY_SHOP_ID) { //TODO
        boolean  access = CandyBotUtils.isAdmin(event.getMember());

            if (access) {

                String[] args = event.getArgs().split(" ");


                List<Object> listeners = event.getJDA().getRegisteredListeners();

                for (Object o : listeners) {
                    if (o instanceof CommandClientImpl) {
                        CommandClientImpl client = (CommandClientImpl) o;

                        client.getCommands().forEach(command -> System.out.println(command.getName()));
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

                        System.out.println("      " + args[0] + "         TEST");
                        TreasureHunt.treasureName = args[0];

                        client.addCommand(new Treasure(args[0]));
                        client.addCommand(new TreasureHunt(args[0] + "hunt"));
                        client.addCommand(new TreasureHuntRate(args[0] + "rate"));
                        client.addCommand(new GiveTreasure("give" + args[0]));


                    }
                }
            }
        }
    }
}
