package command;

import object.SpaceMarineCollection;

/**
 * The type Head command.
 */
public class HeadCommand implements Command{

    private SpaceMarineCollection spaceMarineCollection;

    /**
     * Instantiates a new Head command.
     *
     * @param spaceMarineCollection the space marine collection
     */
    public HeadCommand(SpaceMarineCollection spaceMarineCollection) {
        this.spaceMarineCollection = spaceMarineCollection;
    }

    @Override
    public void execute() {
        spaceMarineCollection.head();
        System.out.println("Первый элемент коллекции был выведен");
    }

    @Override
    public String getDescription() {
        return "Выводит первый элемент коллекции";
    }
}
