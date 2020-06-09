package org.candyShop.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Role;
import org.candyShop.helpers.TreasureHuntUtils;

public class Test extends Command {

    public Test() {
        this.name = "test";
        this.ownerCommand = true;
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) {




        for (Role r : event.getMember().getRoles()) {
            System.out.println(r.getId());

        }


        event.reply(TreasureHuntUtils.emoji[0]);
        event.reply("emoji");

        if (TreasureHuntUtils.emote[0] != null) {
            event.reply(TreasureHuntUtils.emote[0].getAsMention());
            event.reply("emote");
        }
        if (TreasureHuntUtils.emoji[1] != null) {
            event.reply(TreasureHuntUtils.emoji[1]);
            event.reply( "special emoji");
        }
        if (TreasureHuntUtils.emote[1] != null) {
            event.reply(TreasureHuntUtils.emote[1].getAsMention());
            event.reply( " special emote");
        }
        if (TreasureHuntUtils.emoji[2] != null) {
            event.reply(TreasureHuntUtils.emoji[2]);
            event.reply(" special emoji taken");
        } if (TreasureHuntUtils.emote[2] != null) {
            event.reply(TreasureHuntUtils.emote[2].getAsMention());
            event.reply(" special emote taken");
        }


//        if (event.getGuild().getIdLong() == Main.TESTY_TESY_ID) {
//            String[] args = event.getArgs().split(" ");
//
//
//            List<Object> listeners = event.getJDA().getRegisteredListeners();
//
//            for (Object o : listeners) {
//                if (o instanceof CommandClientImpl) {
//                    CommandClientImpl client = (CommandClientImpl) o;
//
//                    client.getCommands().forEach(command -> System.out.println(command.getName()));
//                    ArrayList<String> names = new ArrayList<>();
//
//                    for (Command c : client.getCommands()) {
//
//                        if (c instanceof TreasureHunt) {
//                            names.add(c.getName());
//                        }
//                        if (c instanceof Treasure) {
//                            names.add(c.getName());
//                        }
//                        if (c instanceof TreasureHuntRate) {
//                            names.add(c.getName());
//                        }
//                        if (c instanceof GiveTreasure) {
//                            names.add(c.getName());
//                        }
//
//                    }
//
//                    for (String s : names) {
//                        client.removeCommand(s);
//                    }
//
//                    System.out.println(args[0]);
//
//                    client.addCommand(new Treasure(args[0]));
//                    client.addCommand(new TreasureHunt(args[0] + "hunt"));
//                    client.addCommand(new TreasureHuntRate(args[0] + "rate"));
//                    client.addCommand(new GiveTreasure("give" + args[0]));
//
//
//                }
//            }
//------------------------------------------------------


//            System.out.println("right guild");
//
//
//            Guild guild = event.getGuild();
//            RoleAction roleAction = guild.createRole();
//            roleAction.setName("Test role");
//            Role role = roleAction.complete();
//
//
//            System.out.println("role");
//
//            RoleOrderAction roleOrderAction = guild.modifyRolePositions();
//            roleOrderAction.selectPosition(guild.getRoleById(role.getId()));
//            List<Role> roles = roleOrderAction.getCurrentOrder();
//
//            int pos = 0;
//            for (Role r : roles) {
//                if (r.getIdLong() == 683800502206595092L) {
//                    pos = roles.indexOf(r);
//                }
//            }
//
//            roleOrderAction.moveTo(pos - 1);
//            roleOrderAction.queue();


    }

//        event.reply("Beep boop imma bot");
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        String[] args = event.getArgs().split(" ");
//        String test = args[0].replaceAll("[^\\d.]", "");
//
////        User u = event.getGuild().getMemberById(test).getUser();
//
//        FileUtils.writeJSON("members",test, dtf.format(now));

//    }
}
