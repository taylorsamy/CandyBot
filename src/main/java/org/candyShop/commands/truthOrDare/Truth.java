package org.candyShop.commands.truthOrDare;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Truth extends Command {

    private List<String> truths;

    public Truth() {
        truths = new ArrayList<>();
        init();

        this.name = "truth";
    }

    private void init() {
        try {
            Scanner s = new Scanner(new File("src/main/resources/truth-or-dare/truths.txt"));

            while (s.hasNextLine()) {
                truths.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void execute(CommandEvent event) {
        Random random = new Random();
        String truth = truths.get(random.nextInt(truths.size()));

        event.reply(truth);

    }


}
