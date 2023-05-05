package file;

import exception.ValidValuesRangeException;
import io.UserIO;
import object.*;

/**
 * The type Space marine fields reader.
 */
public class SpaceMarineFieldsReader {
    private UserIO userIO;

    /**
     * Instantiates a new Space marine fields reader.
     *
     * @param userIO the user io
     */
    public SpaceMarineFieldsReader(UserIO userIO) {
        this.userIO = userIO;
    }

    /**
     * Read id long.
     *
     * @return the long
     */
    public long readId(){
        long id;

        while (true){
            try {
                userIO.printCommandText("id (not null & not negative): ");
                id = Long.parseLong(userIO.readLine().trim());
                if (userIO.readLine().trim().equals("") || id <= 0) {
                    throw new ValidValuesRangeException();
                } else return id;
            } catch (ValidValuesRangeException ex){
                System.err.println("\nЗначение поля не может быть null или negative");
            }
        }
    }

    /**
     * Read name string.
     *
     * @return the string
     */
    public String readName() {
        String str;

        while (true) {
            userIO.printCommandText("name (not null): ");
            str = userIO.readLine().trim();
            if (str.equals("")) userIO.printCommandError("\nЗначение поля не может быть null или пустой строкой \n");
            else return str;
        }
    }

    /**
     * Read coordinates coordinates.
     *
     * @return the coordinates
     */
    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }

    /**
     * Read coordinate x integer.
     *
     * @return the integer
     */
    public Integer readCoordinateX() {
        Integer x;
        while (true) {
            try {
                userIO.printCommandText("coordinate_x (Integer & not null): ");
                x = Integer.valueOf(userIO.readLine().trim());
                return x;
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Integer и не null");
            } catch (ValidValuesRangeException ex) {
                System.out.println("Координата x не может быть null");
            }
        }
    }

    /**
     * Read coordinate y float.
     *
     * @return the float
     */
    public float readCoordinateY() {
        float y;
        while (true) {
            try {
                userIO.printCommandText("coordinate_y (float & can be null): ");
                String str = userIO.readLine().trim();
                if (str.equals("")) y = 0;
                else y = Float.parseFloat(str);
                return y;
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа float");
            }
        }
    }

    /**
     * Read health float.
     *
     * @return the float
     */
    public float readHealth() {
        float health;
        while (true) {
            try {
                userIO.printCommandText("health (float & health > 0): ");
                String str = userIO.readLine().trim();
                if (str.equals("")) throw new ValidValuesRangeException();
                else {
                    health = Float.parseFloat(str);
                    if (health <= 0) throw new ValidValuesRangeException();
                }
                return health;
            } catch (ValidValuesRangeException ex) {
                System.err.println("Значение health должно быть больше 0");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа float");
            }
        }
    }

    /**
     * Read category astartes category.
     *
     * @return the astartes category
     */
    public AstartesCategory readCategory() {
        AstartesCategory category;
        while (true) {
            try {
                userIO.printCommandText("Допустимые значения category :");
                for (AstartesCategory val : AstartesCategory.values()) {
                    userIO.printCommandText(val.name());
                }
                userIO.printCommandText("category: ");
                category = AstartesCategory.valueOf(userIO.readLine().toUpperCase().trim());
                return category;
            } catch (IllegalArgumentException ex) {
                System.err.println("Значение введенной константы не представлено в перечислении AstartesCategory");
            }
        }
    }

    /**
     * Read weapon type weapon.
     *
     * @return the weapon
     */
    public Weapon readWeaponType() {
        Weapon weaponType;
        while (true) {
            try {
                userIO.printCommandText("Допустимые значения weaponType :");
                for (Weapon val : Weapon.values()) {
                    userIO.printCommandText(val.name());
                }
                userIO.printCommandText("weaponType: ");
                weaponType = Weapon.valueOf(userIO.readLine().toUpperCase().trim());
                return weaponType;
            } catch (IllegalArgumentException ex) {
                System.err.println("Значение введенной константы не представлено в перечислении Weapon");
            }
        }
    }

    /**
     * Read melee weapon melee weapon.
     *
     * @return the melee weapon
     */
    public MeleeWeapon readMeleeWeapon() {
        MeleeWeapon meleeWeapon;
        while (true) {
            try {
                userIO.printCommandText("Допустимые значения meleeWeapon :");
                for (MeleeWeapon val : MeleeWeapon.values()) {
                    userIO.printCommandText(val.name());
                }
                userIO.printCommandText("meleeWeapon: ");
                String str = userIO.readLine();
                if (str.equals("")) throw new ValidValuesRangeException();
                else {
                    meleeWeapon = MeleeWeapon.valueOf(str.toUpperCase().trim());
                    return meleeWeapon;
                }
            } catch (IllegalArgumentException ex) {
                System.err.println("Значение введенной константы не представлено в перечислении MeleeWeapon");
            }
        }
    }

    /**
     * Read marines count long.
     *
     * @return the long
     */
    public long readMarinesCount() {
        long marinesCount;
        while (true) {
            try {
                userIO.printCommandText("marinesCount (long & marinesCount > 0 & marinesCount < 1000): ");
                String str = userIO.readLine().trim();
                if (str.equals("")) marinesCount = 0;
                else {
                    marinesCount = Long.parseLong(str);
                    if (marinesCount <= 0 || marinesCount > 1000) throw new ValidValuesRangeException();
                }
                return marinesCount;
            } catch (ValidValuesRangeException ex) {
                System.err.println("Значение age должно быть больше 0, но меньше 1000");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа long");
            }
        }
    }

    /**
     * Read chapter name string.
     *
     * @return the string
     */
    public String readChapterName(){
        String chapterName;
        while (true) {
            userIO.printCommandText("chapter name (not null): ");
            chapterName = userIO.readLine().trim();
            if (chapterName.equals("")) userIO.printCommandError("\nЗначение поля не может быть null или пустой строкой \n");
            else return chapterName;
        }
    }
}
