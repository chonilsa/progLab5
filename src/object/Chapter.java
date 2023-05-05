package object;

public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000

    public Chapter(String name, long marinesCount) {
        this.name = name;
        this.marinesCount = marinesCount;
    }

    public Chapter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMarinesCount() {
        return marinesCount;
    }

    public void setMarinesCount(long marinesCount) {
        this.marinesCount = marinesCount;
    }

    @Override
    public String toString() {
        return "(name = " + name + ", marinesCount = " + marinesCount + ")";
    }
}