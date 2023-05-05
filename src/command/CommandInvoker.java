package command;

import file.*;
import io.UserIO;
import object.SpaceMarineCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/**
 * The type Command invoker.
 */
public class CommandInvoker {

    private HashMap<String, Command> commandsWithoutArguments;

    private HashMap<String, CommandWithArgs> commandsWithArguments;

    private SpaceMarineCollection spaceMarineCollection;

    private UserIO userIO;

    private String inputFile;

    private SpaceMarineFieldsReader spaceMarineFieldsReader;

    /**
     * The Script.
     */
    ExecuteScriptCommand.Script script;

    /**
     * The History.
     */
    ArrayList<String> history;

    /**
     * Instantiates a new Command invoker.
     *
     * @param spaceMarineCollection   the space marine collection
     * @param userIO                  the user io
     * @param inputFile               the input file
     * @param spaceMarineFieldsReader the space marine fields reader
     */
    public CommandInvoker(SpaceMarineCollection spaceMarineCollection, UserIO userIO, String inputFile, SpaceMarineFieldsReader spaceMarineFieldsReader) {
        this.spaceMarineCollection = spaceMarineCollection;
        this.userIO = userIO;
        this.inputFile = inputFile;
        this.spaceMarineFieldsReader = spaceMarineFieldsReader;

        history = new ArrayList<>();

        commandsWithoutArguments = new HashMap<>();
        commandsWithArguments = new HashMap<>();
        this.script = new ExecuteScriptCommand.Script();

        this.putCommands();
    }

    /**
     * Instantiates a new Command invoker.
     *
     * @param spaceMarineCollection   the space marine collection
     * @param userIO                  the user io
     * @param spaceMarineFieldsReader the space marine fields reader
     * @param script                  the script
     */
    public CommandInvoker(SpaceMarineCollection spaceMarineCollection, UserIO userIO, SpaceMarineFieldsReader spaceMarineFieldsReader, ExecuteScriptCommand.Script script) {
        this.spaceMarineCollection = spaceMarineCollection;
        this.userIO = userIO;
        this.spaceMarineFieldsReader = spaceMarineFieldsReader;

        history = new ArrayList<>();

        commandsWithoutArguments = new HashMap<>();
        commandsWithArguments = new HashMap<>();
        this.script = script;
        this.putCommands();
    }

    private void putCommands() {
        commandsWithoutArguments.put("info", new InfoCommand(spaceMarineCollection));
        commandsWithoutArguments.put("show", new ShowCommand(spaceMarineCollection));
        commandsWithoutArguments.put("clear", new ClearCommand(spaceMarineCollection));
        commandsWithoutArguments.put("exit", new ExitCommand());
        commandsWithoutArguments.put("add", new AddElementCommand(spaceMarineCollection, userIO, spaceMarineFieldsReader));
        commandsWithoutArguments.put("save", new SaveCommand(spaceMarineCollection));
        commandsWithArguments.put("execute_script", new ExecuteScriptCommand(spaceMarineCollection, spaceMarineFieldsReader, script));
        commandsWithoutArguments.put("help", new HelpCommand());
        commandsWithoutArguments.put("head", new HeadCommand(spaceMarineCollection));
        commandsWithoutArguments.put("print_unique_chapter", new PrintUniqueChapterCommand(spaceMarineCollection));
        commandsWithoutArguments.put("print_field_descending_category", new PrintFieldDescendingCategoryCommand(spaceMarineCollection));
        commandsWithoutArguments.put("add_if_min", new AddIfMinCommand(spaceMarineCollection, userIO, spaceMarineFieldsReader));

        commandsWithArguments.put("remove_by_id", new RemoveByIdCommand(spaceMarineCollection, spaceMarineFieldsReader));
        commandsWithArguments.put("update", new UpdateIdCommand(spaceMarineCollection, userIO));
        commandsWithArguments.put("filter_less_than_category", new FilterLessThanCategoryCommand(spaceMarineCollection, userIO));
    }

    /**
     * Execute.
     *
     * @param firstCommandLine the first command line
     */
    public void execute(String firstCommandLine) {
        String[] words = firstCommandLine.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(words, 1, words.length); //проверка на размер больше 1

        history.add(words[0]);

        if(words[0].equals("history")){
            for(int i = history.size() - 1, j = 0; i >= 0 && j < 13; j++, i--)
                System.out.println(history.get(i));
        }
        else if (commandsWithArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            CommandWithArgs command;

            command = commandsWithArguments.get(words[0].toLowerCase(Locale.ROOT));
            command.setCommandArguments(args);
            command.execute();
        } else if (commandsWithoutArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            Command command;

            command = commandsWithoutArguments.get(words[0].toLowerCase(Locale.ROOT));
            command.execute();
        } else {
            System.err.println("Команда " + words[0] + " не распознана, для получения справки введите команду help");
        }
    }
}

