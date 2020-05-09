package org.candyShop.commands.adminCommands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Member;
import org.candyShop.helpers.CandyBotUtils;

public class JokeWarning extends Command {

    public JokeWarning() {
        this.name = "jokeWarn";
        this.ownerCommand = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        boolean access = CandyBotUtils.isAdmin(event.getMember());

        if (access) {

            String[] args = event.getArgs().split(" ");
            String userID = args[0].replaceAll("[^\\d.]", "");
            Member m = event.getGuild().getMemberById(userID);

            if (CandyBotUtils.isAdmin(m)) {
                event.reply("Even the power invested in my lollipop is unable to issue a joke warning to an admin.");
            } else {
                event.reply("By the power invested in my lollipop, I herby issue the most serious of serious warnings to " + m.getAsMention() + "!");
            }
        }
    }
}
