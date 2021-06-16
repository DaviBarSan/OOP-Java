package client;

import prod.Board;
import prod.BoardScanner;
import prod.Position;

import java.util.HashMap;

public class Smart implements ScannigStrategies{
    int scanCounter;
    @Override
    public void scan(Board currBoard) {
        BoardScanner newScanner = new BoardScanner(currBoard);
        while (newScanner.getShipCounter() < currBoard.getNumberOfOccupedCells()){
            Position currPos = newScanner.getToScanCells().remove(0);
            newScanner.setCellAsScannedOnBoard(currPos);
            newScanner.addScannedCell(currPos);
            if (currPos.containsShip()) {
                newScanner.incrementShipCellsCounter();
                HashMap<String, Position> validNeighbors = currBoard.getAPositionNeighborhood(currPos);
                newScanner.removeForbiddenNeighborsFromScan(currPos, validNeighbors);
                newScanner.movePriorityCells(validNeighbors);
                }
            }
                this.scanCounter = newScanner.getScanCounter();
            }
    @Override
    public int getCounter() {
        return scanCounter;
    }
}
