package prod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    static int numbOfRows;
    static int MAXROWINDEX;
    static int numbOfCols;
    static int MAXCOLINDEX;
    static List<Position> currBoard;
    int numberOfShips;
    int numberOfOccupedCells;
    List<Position> shipsCoordsOnBoard;

    /**
     * @param numbOfRows    integer indicating the number of rows in instantiated board
     * @param numbOfCols    integer indicating the number of columns in instantiated board
     * @param numberOfShips integer indicating the number of ships present in instantiated board
     * @inv
     */
    public Board(int numbOfRows, int numbOfCols, int numberOfShips) {
        Board.numbOfRows = numbOfRows;
        MAXROWINDEX = numbOfRows - 1;
        Board.numbOfCols = numbOfCols;
        MAXCOLINDEX = numbOfCols - 1;
        this.numberOfShips = numberOfShips;
        currBoard = boardGenerator(numbOfRows, numbOfCols);
        shipsCoordsOnBoard = new ArrayList<>();
        numberOfOccupedCells = 0;
    }

    /**
     * Static method that generates an List of Positions, as matrix, from rows and column size;
     *
     * @param nRows
     * @param nCols
     * @return List of Positions with all the board's cells.
     */
    private static List<Position> boardGenerator(int nRows, int nCols) {
        List<Position> currBoard = new ArrayList<>();
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                currBoard.add(new Position(r, c));
            }
        }
        return currBoard;
    }

    /**
     * Getter for this.currBoard;
     *
     * @return List with all Positions of board;
     */
    public static List<Position> getCurrBoard() {
        return currBoard;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        outerloop:
        for (int i = 0; i < numbOfRows; i++) {
            for (int j = 0; j < numbOfCols; j++) {
                Position currPos = getPosInListFromCoordinates(i, j);
                if (currPos.containShip && !(currPos.scanned)) board.append('O');
                else if (currPos.containShip && currPos.scanned) board.append('+');
                else if (currPos.scanned && !(currPos.containShip)) board.append('X');
                else board.append('.');
            }
            if (i + 1 == numbOfRows) break;
            board.append('\n');
        }
        String boardToString = board.toString();
        return boardToString;
    }

    /**
     * A Ship currShip provides coordinates to set a cell.containsShip as true;
     *
     * @param currShip Ship object that provides coordinates data to set it into board;
     * @throws IndexOutOfBoundsException if !isValidCoord(posOccupedByShip);
     * @pre isValidCoord(posOccupedByShip) = true
     * @post shipsOnBoard.size > 0;
     * @post numberOfOccupedCells > 0;
     */
    //ship must fit in board
    public void insertShipsOnBoard(Ship currShip) {
        for (Position posOccupedByShip : currShip.coords) {
            if (!isValidCoord(posOccupedByShip.row, posOccupedByShip.col))
                throw new IndexOutOfBoundsException("Out of board bounds!");
            Position currPos = getPosInListFromCoordinates(posOccupedByShip.row, posOccupedByShip.col);
            currPos.containShip = true;
            numberOfOccupedCells++;
            shipsCoordsOnBoard.add(currPos);
        }
    }

    /**
     * @return an integer indicating the number of ships on board at current moment;
     */
    public int getNumberOfShips() {
        return numberOfShips;
    }

    /**
     * By using row and col coordinates, get its position index in list this.currBoard;
     *
     * @param col integer = column coordinate;
     * @param row integer = row coordinate;
     * @return integer that represents index for Position p in this.currBoard;
     */
    //testar
    public static Position getPosInListFromCoordinates(int row, int col) {
        if (!isValidCoord(row, col)) return null;
        return currBoard.get((numbOfCols * row) + col);
    }

    /**
     * Getter to numb of cells occuped by ships
     *
     * @return integer
     */
    public int getNumberOfOccupedCells() {
        return numberOfOccupedCells;
    }

    public List<Position> getShipsCoordsOnBoard() {
        return shipsCoordsOnBoard;
    }

    /**
     * Gets an HashMap with neighbor position, from an main Position pos;
     *
     * @param pos main position that want get the neighbors;
     * @return HashMap to easly access a value = a neighbor Position object and its data, using a key = String orientation;
     */
    public HashMap<String, Position> getAPositionNeighborhood(Position pos) {
        String[] windRose = {"NW", "SW", "S", "NE", "E", "SE","X","N","W"};
        Integer[] posCoord = {pos.row - 1, pos.col - 1,
                pos.row + 1, pos.col - 1,
                pos.row + 1, pos.col,
                pos.row - 1, pos.col + 1,
                pos.row, pos.col + 1,
                pos.row + 1, pos.col + 1,
                pos.row, pos.col,
                pos.row-1, pos.col,
                pos.row, pos.col-1};
        HashMap<String, Position> hmToIndex = new HashMap<>();
        for (int i = 0, j = 0; i < windRose.length; i++, j = j + 2)
            try {
                Position currPosition = getPosInListFromCoordinates(posCoord[j], posCoord[j + 1]);
                hmToIndex.put(windRose[i], currPosition);
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        return hmToIndex;
    }
    public static boolean isValidCoord(int row, int col) {
        return (row <= MAXROWINDEX && row >= 0) && (col <= MAXCOLINDEX && col >= 0);
    }

    public static int getNumberOfCols() {
        return numbOfCols;
    }
}
