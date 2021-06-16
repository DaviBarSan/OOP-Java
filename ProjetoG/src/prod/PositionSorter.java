package prod;

import java.util.Comparator;

import static prod.Board.numbOfCols;

public class PositionSorter implements Comparator<Position> {

    @Override
    public int compare(Position pos1, Position pos2) {
        return ((numbOfCols * pos1.row) + pos1.col) - (((numbOfCols * pos2.row) + pos2.col));
    }
}
