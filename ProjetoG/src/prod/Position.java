package prod;

import java.util.Objects;

/**
 * Basic unity to construct a board
 *
 * @inv row != null; col != null;
 */
public class Position{
    int row;
    int col;
    boolean containShip;
    boolean scanned;

    /**
     * Instatiate an Position unity, which can be associated a containShip data and scan status data
     *
     * @param row integer reference to row position in matrix;
     * @param col integer reference to column position in matrix
     * @pre row >= 0 && col >= 0;
     * @post instatiate an Position object, without Ship and not scanned yet
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
        containShip = false;
        scanned = false;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col && containShip == position.containShip;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * Set as true the attribute data to ship in this position reference
     *
     * @return to Client a boolean that indicates if this position contains Ship
     */
    public boolean containsShip() {
        return containShip;
    }

    /**
     *
     */
    public void setAsScanned() {
        scanned = true;
    }

    public boolean isScanned() {
        return scanned;
    }
}
