package command;

import object.SpaceMarineCollection;

/**
 * The type Clear command.
 */
public class ClearCommand implements Command {

    private SpaceMarineCollection spaceMarineCollection;

    /**
     * Instantiates a new Clear command.
     *
     * @param spaceMarineCollection the space marine collection
     */
    public ClearCommand(SpaceMarineCollection spaceMarineCollection) {
        this.spaceMarineCollection = spaceMarineCollection;
    }

    @Override
    public void execute() {
        spaceMarineCollection.clear();
        System.out.println("Коллекция была очищена");
    }

    @Override
    public String getDescription() {
        return "Очищает все элементы коллекции";
    }
}

