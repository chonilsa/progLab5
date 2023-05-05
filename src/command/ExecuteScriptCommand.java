package command;

import io.UserIO;
import object.SpaceMarineCollection;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import exception.RecoursiveCallException;
import file.SpaceMarineFieldsReader;

/**
 * The type Execute script command.
 */
public class ExecuteScriptCommand implements CommandWithArgs{

    private String[] commandArguments;

    private SpaceMarineCollection spaceMarineCollection;

    private UserIO userIO;

    private SpaceMarineFieldsReader spaceMarineFieldsReader;

    private String scriptPath;

    private Script script;

    /**
     * Instantiates a new Execute script command.
     *
     * @param spaceMarineCollection   the space marine collection
     * @param spaceMarineFieldsReader the space marine fields reader
     * @param script                  the script
     */
    public ExecuteScriptCommand(SpaceMarineCollection spaceMarineCollection, SpaceMarineFieldsReader spaceMarineFieldsReader, Script script) {
        this.spaceMarineCollection = spaceMarineCollection;
        this.spaceMarineFieldsReader = spaceMarineFieldsReader;
        this.script = script;
    }

    @Override
    public void execute() {
        try {
            if (commandArguments.length == 1) {
                scriptPath = commandArguments[0];
                if (script.scriptPaths.contains(scriptPath)) throw new RecoursiveCallException();
                else script.putScript(scriptPath);
            } else throw new IllegalArgumentException();

            File ioFile = new File(scriptPath);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();
            FileInputStream fileInputStream = new FileInputStream(scriptPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            Scanner scanner = new Scanner(inputStreamReader);
            userIO = new UserIO(scanner);
            CommandInvoker commandInvoker = new CommandInvoker(spaceMarineCollection, userIO, spaceMarineFieldsReader, script);

            while (scanner.hasNext()) {
                commandInvoker.execute(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Файл скрипта не найден");
        } catch (NullPointerException ex) {
            System.err.println("Не выбран файл, из которого читать скрипт");
        } catch(IOException ex){
            System.err.println("Доступ к файлу невозможен");
        }  catch (IllegalArgumentException ex) {
            System.err.println("скрипт не передан в качестве аргумента команды, либо кол-во агрументов больше 1");
        } catch (RecoursiveCallException ex) {
            System.err.println("Скрипт " + scriptPath + " уже существует (Рекурсивный вызов)");
        }
        script.removeScript(scriptPath);

    }

    @Override
    public void setCommandArguments(String[] commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public String getDescription() {
        return "выполняет команды, описанные в скрипте";
    }

    /**
     * The type Script.
     */
    static class Script {

        private ArrayList<String> scriptPaths = new ArrayList<String>();

        /**
         * Put script.
         *
         * @param scriptPath the script path
         */
        public void putScript(String scriptPath) {
            scriptPaths.add(scriptPath);
        }

        /**
         * Remove script.
         *
         * @param scriptPath the script path
         */
        public void removeScript(String scriptPath) {
            scriptPaths.remove(scriptPath);
        }
    }
}
