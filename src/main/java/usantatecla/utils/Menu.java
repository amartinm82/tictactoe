package usantatecla.utils;

import usantatecla.tictactoe.views.Message;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    private List<Command> commandList;

    public Menu() {
        this.commandList = new ArrayList<>();
    }

    public void execute() {
        ArrayList<Command> commands = new ArrayList<>();
        for (int i = 0; i < this.commandList.size(); i++) {
            if (this.commandList.get(i).isActive()) {
                commands.add(this.commandList.get(i));
            }
        }
        int option;
        Console console = Console.getInstance();
        boolean error;
        Message.CHOOSE_OPTION.writeln();
        do {
            error = false;
            console.writeln();
            for (int i = 0; i < commands.size(); i++) {
                console.writeln((i + 1) + ") " + commands.get(i).getTitle());
            }
            option = console.readInt("") - 1;
            if (!new ClosedInterval(0, commands.size() - 1).isIncluded(option)) {
                error = true;
            }
        } while (error);
        commands.get(option).execute();
    }

    protected void addCommand(Command command) {
        this.commandList.add(command);
    }

}
