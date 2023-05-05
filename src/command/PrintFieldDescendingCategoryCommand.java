package command;

import object.SpaceMarineCollection;

/**
 * The type Print field descending category command.
 */
public class PrintFieldDescendingCategoryCommand implements Command{
    private SpaceMarineCollection spaceMarineCollection;

    /**
     * Instantiates a new Print field descending category command.
     *
     * @param spaceMarineCollection the space marine collection
     */
    public PrintFieldDescendingCategoryCommand(SpaceMarineCollection spaceMarineCollection) {
        this.spaceMarineCollection = spaceMarineCollection;
    }

    @Override
    public void execute() {
        spaceMarineCollection.printFieldDescendingCategory();
        System.out.println("Значения полей category всех элементов были выведены в порядке убывания");
    }

    @Override
    public String getDescription() {
        return "Выводит значения полей category всех элементов в порядке убывания";
    }
}
