package command;

import file.SpaceMarineFieldsReader;
import io.UserIO;
import object.Chapter;
import object.Coordinates;
import object.SpaceMarine;
import object.SpaceMarineCollection;

/**
 * The type Add if min command.
 */
public class AddIfMinCommand implements Command{

    private SpaceMarineCollection spaceMarineCollection;

    private UserIO userIO;

    private SpaceMarineFieldsReader spaceMarineFieldsReader;

    /**
     * Instantiates a new Add if min command.
     *
     * @param spaceMarineCollection   the space marine collection
     * @param userIO                  the user io
     * @param spaceMarineFieldsReader the space marine fields reader
     */
    public AddIfMinCommand(SpaceMarineCollection spaceMarineCollection, UserIO userIO, SpaceMarineFieldsReader spaceMarineFieldsReader){
        this.spaceMarineCollection = spaceMarineCollection;
        this.userIO = userIO;
        this.spaceMarineFieldsReader = spaceMarineFieldsReader;
    }

    @Override
    public void execute() {
        SpaceMarine spaceMarine = new SpaceMarine();
        SpaceMarineFieldsReader spaceMarineFieldsReader = new SpaceMarineFieldsReader(userIO);
        userIO.printCommandText("Введите значение полей для элемента коллекции\n");
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

        SpaceMarine localSpaceMarine = new SpaceMarine(spaceMarine.getName(), coordinates, spaceMarine.getHealth(), spaceMarine.getCategory(),
                spaceMarine.getWeaponType(), spaceMarine.getMeleeWeapon(), chapter);
        if(spaceMarineCollection.addIfMin(localSpaceMarine)){
            userIO.printCommandText("Элемент добавлен в коллекцию\n");
        } else userIO.printCommandText("Элемент не добавлен в коллекцию\n");
    }

    @Override
    public String getDescription() {
        return "Добавляет элемент в коллекцию если значение поля Chapter меньше минимального в коллекции";
    }
}
