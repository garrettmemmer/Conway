package CPR;

public class Coord {
    int x = 0;
    int y = 0;
    public Coord(int x, int y) {
        if (x < 0 || y < 0 || x > 255 || y > 255)
            throw new ArrayIndexOutOfBoundsException();
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.x;
        hash = 31 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return (this.x == coord.x && this.y == coord.y);
    }
}
