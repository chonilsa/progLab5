package command;

import object.SpaceMarineCollection;

/**
 * The type Exit command.
 */
public class ExitCommand implements Command{
    /**
     * Instantiates a new Exit command.
     */
    public ExitCommand() {
    }

    @Override
    public void execute() {
        System.out.println("Завершение работы программы.");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "завершает работу программы";
    }
}
