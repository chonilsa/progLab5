package command;

import file.SpaceMarineFieldsReader;
import object.SpaceMarineCollection;

/**
 * The type Remove by id command.
 */
public class RemoveByIdCommand implements CommandWithArgs {
    private SpaceMarineCollection spaceMarineCollection;

    private SpaceMarineFieldsReader spaceMarineFieldsReader;
    private String[] commandArgs;

    /**
     * Instantiates a new Remove by id command.
     *
     * @param spaceMarineCollection   the space marine collection
     * @param spaceMarineFieldsReader the space marine fields reader
     */
    public RemoveByIdCommand(SpaceMarineCollection spaceMarineCollection, SpaceMarineFieldsReader spaceMarineFieldsReader) {
        this.spaceMarineCollection = spaceMarineCollection;
        this.spaceMarineFieldsReader = spaceMarineFieldsReader;
    }

    @Override
    public void execute() {
        try {
//            if (Long.parseLong(commandArgs[0]) <= 0) {
//                throw new IllegalArgumentException();
//            } else {
//                spaceMarineCollection.removeById(Long.parseLong(commandArgs[0]));
//                System.out.println("Удаление элемента коллекции по id было выполнено");
//            }
            long localId = Long.parseLong(commandArgs[0]);
            spaceMarineCollection.removeById(localId);
            System.out.println("Элемент, полученный из коллекции по id удален");
        } catch (IllegalArgumentException ex) {
            System.err.println("Выбранный аргумент обязан быть типа long");
        } catch (NullPointerException ex){
            System.err.println("Элемента с таким id нет в коллекции");
        }
    }

    @Override
    public String getDescription() {
        return "Удаляет элемент коллекции по его id";
    }

    @Override
    public void setCommandArguments(String[] arguments) {
        this.commandArgs = arguments;
    }
}
