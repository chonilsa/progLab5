package command;

import object.SpaceMarineCollection;

/**
 * The type Info command.
 */
public class InfoCommand implements Command{
    private SpaceMarineCollection spaceMarineCollection;

    /**
     * Instantiates a new Info command.
     *
     * @param spaceMarineCollection the space marine collection
     */
    public InfoCommand(SpaceMarineCollection spaceMarineCollection) {
        this.spaceMarineCollection = spaceMarineCollection;
    }

    @Override
    public void execute() {
        spaceMarineCollection.info();
    }

    @Override
    public String getDescription() {
        return "Команда получает информацию о коллекции";
    }
}
