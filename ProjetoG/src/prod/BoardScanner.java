package prod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class BoardScanner {
    static List<Position> toScanCells;
    static Board boardToScan;
    public static List<Position> scannedCells;
    static int scanCounter;
    static int shipCounter;

    public BoardScanner(Board boardToScan) {
        scannedCells = new ArrayList<>();
        BoardScanner.boardToScan = boardToScan;
        toScanCells = toScanCellsList(boardToScan);
        BoardScanner.scanCounter = scannedCells.size();
        BoardScanner.shipCounter = 0;
    }

    /**
     * Creates an ArrayList with references to Positions on board
     * @param board
     * @return an ArrayList<Position> whi references;
     */
    public static List<Position> toScanCellsList(Board board) {
        List<Position> toScanList = new ArrayList<>();
        for (Position boardCell : Board.getCurrBoard()) {
            toScanList.add(boardCell);
        }
        return toScanList;
    }

    /**
     * Set directly on scanned board the current cell as scanned;
     * @pre  currScanCell must be valid cell on boardToScan;
     * @param curScanCell current scanning cell;
     */
    public void setCellAsScannedOnBoard(Position curScanCell) {
        curScanCell.scanned = true;
        toScanCells.remove(curScanCell);
    }

    /**
     * From a main position, remove NE, NW, SW and SE neighbors cells. It precisely remove only valid cells.
     * @param position main Position reference to remove neighbors
     * @param validNeighbors HashMap<String,Position> with Key = String, that refers to orientation to neighbor
     *          and Value as Position object reference;
     */
    public void removeForbiddenNeighborsFromScan(Position position, HashMap<String,Position> validNeighbors) {
        List<Position> neighborhood = new ArrayList<>();
        neighborhood.add(validNeighbors.get("NW"));
        neighborhood.add(validNeighbors.get("NE"));
        neighborhood.add(validNeighbors.get("SW"));
        neighborhood.add(validNeighbors.get("SE"));
        for (Position forbiddenNeighbor: neighborhood) {
            if (forbiddenNeighbor != null){
                setCellAsScannedOnBoard(forbiddenNeighbor);
            }
        }
    }

    /**
     * Move the SouthCell to priority in toScanCells list. Its new position depends on EastCell validation;
     * @param validNeighbors HashMap<String,Position> to easly access the East and South cells;
     */
    public void movePriorityCells(HashMap<String, Position> validNeighbors) {
        Position eastSide = validNeighbors.get("E");
        Position southSide = validNeighbors.get("S");
        if (southSide == null || southSide.scanned) return;
        if (eastSide != null || !eastSide.scanned){
            toScanCells.remove(southSide);
            toScanCells.add(southSide);
            Collections.rotate(toScanCells.subList(1,toScanCells.size()), 1);
            return;
        }
        toScanCells.remove(southSide);
        toScanCells.add(southSide);
        Collections.rotate(toScanCells.subList(0,toScanCells.size()), 1);

    }

    /**
     * Adds a scanned cell to scannedCells List
     * @param scannedCell Position object currently scanned;
     */
    public void addScannedCell(Position scannedCell) {
        scannedCells.add(scannedCell);
        scanCounter = scannedCells.size();
    }

    /**
     * @return integer with number of cells already scanned
     */
    public int getScanCounter() {
        return scannedCells.size();
    }

    /**
     *
     * @return List of remain Position objects to scan in board
     */
    public List<Position> getToScanCells() {
        return toScanCells;
    }

    /**
     * increment shipCells counter;
     */
    public void incrementShipCellsCounter(){
        shipCounter++;
    }

    /**
     * @return integer with number of shipCells already found;
     */
    public int getShipCounter() {
        return shipCounter;
    }
}
