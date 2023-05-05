package command;

import exception.ValidValuesRangeException;
import file.SpaceMarineFieldsReader;
import io.UserIO;
import object.AstartesCategory;
import object.SpaceMarineCollection;

import java.util.Arrays;

/**
 * The type Filter less than category command.
 */
public class FilterLessThanCategoryCommand implements CommandWithArgs {
    private SpaceMarineCollection spaceMarineCollection;

    private UserIO userIO;

    private String[] commandArgs;

    /**
     * Instantiates a new Filter less than category command.
     *
     * @param spaceMarineCollection the space marine collection
     * @param userIO                the user io
     */
    public FilterLessThanCategoryCommand(SpaceMarineCollection spaceMarineCollection,UserIO userIO) {
        this.spaceMarineCollection = spaceMarineCollection;
        this.userIO = userIO;
    }

    @Override
    public void execute() {
        try{
//            AstartesCategory str = AstartesCategory.valueOf(commandArgs[0]);
//            userIO.printCommandText("Введите значение поля Category: " + Arrays.toString(AstartesCategory.values()));
            spaceMarineCollection.filterLessThanCategory(AstartesCategory.valueOf(commandArgs[0].toUpperCase().trim()));

            System.out.println("Элементы, значение поля category которых меньше заданного были выведены");
        } catch (IndexOutOfBoundsException ex){
            System.err.println("Не указан аргумент");
        } catch (IllegalArgumentException ex){
            System.err.println("Невозможное имя аргумента, введите одно из полей Category:" + Arrays.toString(AstartesCategory.values()));
        } catch (NullPointerException ex){
            System.err.println("Аргумент ссылается на null, выберите из списка: " + Arrays.toString(AstartesCategory.values()));
        }
    }

    @Override
    public String getDescription() {
        return "Выводит элементы, значение поля category которых меньше заданного";
    }

    @Override
    public void setCommandArguments(String[] arguments) {
        this.commandArgs = arguments;
    }
}
