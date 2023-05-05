package command;

import object.SpaceMarineCollection;

/**
 * The type Show command.
 */
public class ShowCommand implements Command {
    private SpaceMarineCollection spaceMarineCollection;

    /**
     * Instantiates a new Show command.
     *
     * @param spaceMarineCollection the space marine collection
     */
    public ShowCommand(SpaceMarineCollection spaceMarineCollection) {
        this.spaceMarineCollection = spaceMarineCollection;
    }

    @Override
    public void execute() {
        spaceMarineCollection.show();
    }

    @Override
    public String getDescription() {
        return "Показывает подробное строковое представление элементов коллекции";
    }

}
