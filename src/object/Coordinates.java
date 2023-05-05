package object;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private float y;

    public Coordinates() {
    }

    public Coordinates(float y) {
        this.y = y;
    }

    public Coordinates(Integer x) {
        this.x = x;
    }

    public Coordinates(Integer x, float y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "coordinates (x, y) = (" + getX() + ", " + getY() + ")";
    }
}
