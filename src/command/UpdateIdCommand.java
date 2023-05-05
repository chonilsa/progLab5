package command;

import io.UserIO;
import object.SpaceMarineCollection;

/**
 * The type Update id command.
 */
public class UpdateIdCommand implements CommandWithArgs {
    private SpaceMarineCollection spaceMarineCollection;

    private String[] commandArgs;

    private UserIO userIO;

    /**
     * Instantiates a new Update id command.
     *
     * @param spaceMarineCollection the space marine collection
     * @param userIO                the user io
     */
    public UpdateIdCommand(SpaceMarineCollection spaceMarineCollection, UserIO userIO) {
        this.spaceMarineCollection = spaceMarineCollection;
        this.userIO = userIO;
    }

    @Override
    public void execute() {
        try {
            if (spaceMarineCollection.containsKey(Long.parseLong(commandArgs[0]))) {
                userIO.printCommandText(spaceMarineCollection.getFieldNames());

                userIO.printCommandText("Введите название поля и его значение через пробел для обновления элемента коллекции (как только измените все нужные Вам поля, введите stop):\n");
                String[] commandWords = new String[0];
                do {
                    try {
                        commandWords = userIO.readLine().trim().split(" ");
                        if (commandWords.length == 1) {
                            spaceMarineCollection.update(Integer.parseInt(commandArgs[0]), commandWords[0], "");
                        } else
                            spaceMarineCollection.update(Integer.parseInt(commandArgs[0]), commandWords[0], commandWords[1]);
                    } catch (IndexOutOfBoundsException ex) {
                        System.err.println("Не указано поле/значение.");
                    } catch (NullPointerException ex) {
                        System.err.println("Поле/значение ссылаются на null");
                    }
                } while (!commandWords[0].equals("stop"));
            } else System.err.println("Элемента с данным ключом в коллекции не существует.");
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("Не указаны все аргументы команды.");
        } catch (NumberFormatException ex) {
            System.err.println("Формат аргумента не соответствует целочисленному ");
        } catch (NullPointerException ex) {
            System.err.println("Поле/значение ссылаются на null");
        }
    }

    @Override
    public String getDescription() {
        return "Обновляет значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void setCommandArguments(String[] arguments) {
        this.commandArgs = arguments;
    }
}
