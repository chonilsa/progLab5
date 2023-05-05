package command;

import object.SpaceMarineCollection;

/**
 * The type Print unique chapter command.
 */
public class PrintUniqueChapterCommand implements Command{
    private SpaceMarineCollection spaceMarineCollection;

    /**
     * Instantiates a new Print unique chapter command.
     *
     * @param spaceMarineCollection the space marine collection
     */
    public PrintUniqueChapterCommand(SpaceMarineCollection spaceMarineCollection) {
        this.spaceMarineCollection = spaceMarineCollection;
    }

    @Override
    public void execute() {
        spaceMarineCollection.printUniqueChapter();
        System.out.println("Выведены уникальные значения поля chapter всех элементов в коллекции");
    }

    @Override
    public String getDescription() {
        return "Выводит уникальные значения поля chapter всех элементов в коллекции";
    }
}
