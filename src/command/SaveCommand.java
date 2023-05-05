package command;

import object.SpaceMarineCollection;

/**
 * The type Save command.
 */
public class SaveCommand implements Command {
    private SpaceMarineCollection spaceMarineCollection;

    /**
     * Instantiates a new Save command.
     *
     * @param spaceMarineCollection the space marine collection
     */
    public SaveCommand(SpaceMarineCollection spaceMarineCollection) {
        this.spaceMarineCollection = spaceMarineCollection;
    }

    @Override
    public void execute() {
        spaceMarineCollection.save();
        System.out.println("Коллекция была сохранена");
    }

    @Override
    public String getDescription() {
        return "Сохраняет коллекцию в файл";
    }
}
