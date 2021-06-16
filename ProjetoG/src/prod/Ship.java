package prod;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    Position mainCoordinate;
    List<Position> coords;


    char orientation;
    int size;

    public Ship(int xCoord, int yCoord, int size, char orientation) {
        this.mainCoordinate = new Position(xCoord, yCoord);
        this.orientation = orientation;
        this.size = size;
        this.coords = generateThisShipCoordinates(xCoord, yCoord, size, orientation);
    }

    private static List<Position> generateThisShipCoordinates(int xCoord, int yCoord, int size, char orientation) {
        List<Position> tmp = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (orientation == 'E') tmp.add(new Position(xCoord, yCoord + i));
            else tmp.add(new Position(xCoord + i, yCoord));
        }
        return tmp;
    }

    public List<Position> getCoords() {
        return coords;
    }
}
