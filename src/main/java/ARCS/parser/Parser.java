package ARCS.parser;

import ARCS.commands.AddRouteCommand;
import ARCS.commands.Command;
import ARCS.commands.ExitCommand;
import ARCS.commands.ListRouteCommand;

public class Parser {
    public Command parseCommand(String userInput) {
        String[] fullInput = userInput.split(" ", 2);
        String commandWord = fullInput[0];
        String argumentLine = fullInput.length > 1? fullInput[1] : null;
        Command command = null;

        switch (commandWord) {
        case AddRouteCommand.COMMAND_WORD:
            command = prepareAddRoute(argumentLine);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case ListRouteCommand.COMMAND_WORD:
            command = new ListRouteCommand();
            break;
        default:
            break;
        }
        return command;
    }

    private Command prepareAddRoute(String argumentLine) {
        String[] args = argumentLine.split(" ");
        String fId = "0";
        String date = null;
        String time = null;
        String from = null;
        String to = null;
        int capacity = 0;
        for (String arg: args) {
            arg = arg.trim();
            if (arg.isEmpty()) {
                continue;
            }
            String[] argSplit = arg.split("/");
            String field = argSplit[0].trim();
            String value = argSplit[1].trim();
            switch (field) {
            case "fid":
                fId = value;
                break;
            case "fd":
                date = value;
                break;
            case "ft":
                time = value;
                break;
            case "d":
                to = value;
                break;
            case "s":
                from = value;
                break;
            case "c":
                capacity = Integer.parseInt(value);
                break;
            default:
                break;
            }
        }
        return new AddRouteCommand(fId, date, time, from, to, capacity);
    }
}
