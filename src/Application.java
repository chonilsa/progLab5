import object.*;
import command.*;
import file.*;
import io.UserIO;


import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The type Application.
 */
public class Application {
    /**
     * The Space marine collection.
     */
    SpaceMarineCollection spaceMarineCollection;

    /**
     * The File manager.
     */
    FileManager fileManager;

    /**
     * The Csv parser.
     */
    CsvParsing csvParser;

    /**
     * The User io.
     */
    UserIO userIO;

    private final String inputFile = "/home/studs/s367056/db.csv";

    /**
     * The Command invoker.
     */
    CommandInvoker commandInvoker;

    /**
     * The Space marine fields reader.
     */
    SpaceMarineFieldsReader spaceMarineFieldsReader;

    /**
     * Start.
     *
     * @param inputFile the input file
     */
    public void start(String inputFile) {

        spaceMarineCollection = new SpaceMarineCollection();
        fileManager = new FileManager();
        csvParser = new CsvParsing();
        userIO = new UserIO();

        spaceMarineFieldsReader = new SpaceMarineFieldsReader(userIO);

        this.commandInvoker = new CommandInvoker(spaceMarineCollection, userIO, inputFile, spaceMarineFieldsReader);

        CsvParsing.filePath = inputFile;

        try {

            File ioFile = new File(inputFile);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();
            String file = fileManager.readFromFile();

            ArrayList<SpaceMarine> spaceMarines = csvParser.parseToCollection(file);
            for (SpaceMarine spaceMarine : spaceMarines) {
                spaceMarineCollection.add(spaceMarine);
            }
            userIO.printCommandText("Элементы коллекций из указанного файла были загружены\n");
        } catch (IOException e) {
            System.err.println("По указанному адресу нет подходящего файла");
            System.exit(0);
        }
        try {
            cycle();
        } catch (NoSuchElementException ex) {
            System.err.println("Ctrl + D exception");
        }
    }

    /**
     * Start from input file.
     */
    public void startFromInputFile() {

        spaceMarineCollection = new SpaceMarineCollection();
        fileManager = new FileManager();
        csvParser = new CsvParsing();
        userIO = new UserIO();

        spaceMarineFieldsReader = new SpaceMarineFieldsReader(userIO);

        this.commandInvoker = new CommandInvoker(spaceMarineCollection, userIO, inputFile, spaceMarineFieldsReader);

        CsvParsing.filePath = inputFile;

        try {

            File ioFile = new File(inputFile);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();
            String file = fileManager.readFromFile();

            ArrayList<SpaceMarine> spaceMarines = csvParser.parseToCollection(file);
            for (SpaceMarine spaceMarine : spaceMarines) {
                spaceMarineCollection.add(spaceMarine);
            }
            userIO.printCommandText("Элементы коллекций из указанного файла были загружены\n");
        } catch (IOException e) {
            System.err.println("По указанному адресу нет подходящего файла");
            System.exit(0);
        }
        try {
            cycle();
        } catch (NoSuchElementException ex) {
            System.err.println("Ctrl + D exception");
        }
    }

    /**
     * Cycle.
     */
    public void cycle() {
        userIO.printCommandText("Программа была запущена.\n");
        while (true) {
            userIO.printPreamble();
            String line = userIO.readLine();
            commandInvoker.execute(line);
        }
    }
}
