package object;

import jdk.jfr.Category;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SpaceMarine implements Comparable {

    private static long uniqueId = 1;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float health; //Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле может быть null
    private Chapter chapter; //Поле может быть null
    private static String pattern = "yyyy-MM-dd HH:mm:ss.SSS";

    public SpaceMarine(String name, Coordinates coordinates, float health, AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = uniqueId++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public SpaceMarine() {
    }

    public SpaceMarine(long id, String name, Coordinates coordinates, Date date, float health, AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = date;
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCreationDate() {
        return Long.toString(creationDate.getTime());
    }

    public String getFormattedCreationDate() {
        return new SimpleDateFormat(getPattern()).format(creationDate);
    }

    public float getHealth() {
        return health;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinateX(Integer x) {
        this.getCoordinates().setX(x);
    }

    public void setCoordinateY(float y) {
        this.getCoordinates().setY(y);
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public void setChapterName(String name) {
        this.chapter.setName(name);
    }

    public void setChapterMarinesCount(long marinesCount) {
        this.chapter.setMarinesCount(marinesCount);
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public static String getPattern() {
        return pattern;
    }

    public static long getUniqueId() {
        return uniqueId;
    }

    public int getCategoryOrdinal() {
        return this.category.ordinal();
    }

    @Override
    public String toString() {
        return "SpaceMarine{" +
                " id = " + this.getId() + "," +
                " name = " + getName() + "," +
                " coordinates = " + getCoordinates().toString() + "," +
                " creationDate = " + getFormattedCreationDate() + "," +
                " health = " + getHealth() + "," +
                " category = " + getCategory().toString() + "," +
                " weaponType = " + getWeaponType().toString() + "," +
                " meleeWeapon = " + getMeleeWeapon().toString() + "," +
                " chapter = " + getChapter().toString() +
                " }";
    }


    @Override
    public int compareTo(Object anotherSpaceMarine) {
        if (this.getId() == ((SpaceMarine) anotherSpaceMarine).getId()) {
            return 0;
        } else if (this.getId() > ((SpaceMarine) anotherSpaceMarine).getId()) {
            return 1;
        } else {
            return -1;
        }
    }


}
