package command;

import io.UserIO;
import object.Chapter;
import object.Coordinates;
import object.SpaceMarine;
import object.SpaceMarineCollection;
import file.SpaceMarineFieldsReader;

/**
 * The type Add element command.
 */
public class AddElementCommand implements Command{

    private SpaceMarineCollection spaceMarineCollection;

    private UserIO userIO;

    private SpaceMarineFieldsReader spaceMarineFieldsReader;
//    long id, String name, Integer x, float y, String date, float health, String category, String weaponType,
//                    String meleeWeapon, String name, long marinesCount

    /**
     * Instantiates a new Add element command.
     *
     * @param spaceMarineCollection   the space marine collection
     * @param userIO                  the user io
     * @param spaceMarineFieldsReader the space marine fields reader
     */
    public AddElementCommand(SpaceMarineCollection spaceMarineCollection, UserIO userIO, SpaceMarineFieldsReader spaceMarineFieldsReader){
        this.spaceMarineCollection = spaceMarineCollection;
        this.userIO = userIO;
        this.spaceMarineFieldsReader = spaceMarineFieldsReader;
    }

    @Override
    public void execute() {
        SpaceMarine spaceMarine = new SpaceMarine();
        SpaceMarineFieldsReader spaceMarineFieldsReader = new SpaceMarineFieldsReader(userIO);
        userIO.printCommandText("Введите значение полей для элемента коллекции");
        spaceMarine.setName(spaceMarineFieldsReader.readName());

        Coordinates coordinates = new Coordinates();
        coordinates.setX(spaceMarineFieldsReader.readCoordinateX());
        coordinates.setY(spaceMarineFieldsReader.readCoordinateY());

        spaceMarine.setHealth(spaceMarineFieldsReader.readHealth());
        spaceMarine.setCategory(spaceMarineFieldsReader.readCategory());
        spaceMarine.setWeaponType(spaceMarineFieldsReader.readWeaponType());
        spaceMarine.setMeleeWeapon(spaceMarineFieldsReader.readMeleeWeapon());

        Chapter chapter = new Chapter();
        chapter.setName(spaceMarineFieldsReader.readChapterName());
        chapter.setMarinesCount(spaceMarineFieldsReader.readMarinesCount());

        spaceMarineCollection.add(new SpaceMarine(spaceMarine.getName(), coordinates, spaceMarine.getHealth(), spaceMarine.getCategory(),
                spaceMarine.getWeaponType(), spaceMarine.getMeleeWeapon(), chapter));
        userIO.printCommandText("Элемент добавлен в коллекцию\n");

    }

    @Override
    public String getDescription() {
        return "Добавляет элемент в коллекцию";
    }
}
