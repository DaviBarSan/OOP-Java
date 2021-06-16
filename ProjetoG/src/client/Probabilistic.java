package client;

import prod.Board;
import prod.BoardScanner;
import prod.LikelihoodMatrix;
import prod.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Probabilistic implements ScannigStrategies{
    List<Position> highPriorityNeighbors = new ArrayList<>();
    int scanCounter;
    @Override
    public void scan(Board currBoard) {
        BoardScanner scanner = new BoardScanner(currBoard);
        LikelihoodMatrix probabilisticMatrix = new LikelihoodMatrix(scanner);
        while (scanner.getShipCounter() < currBoard.getNumberOfOccupedCells()) {
            Position nextScanCell;
            if (!highPriorityNeighbors.isEmpty()) {
                nextScanCell = highPriorityNeighbors.remove(0);
            } else {
                LikelihoodMatrix.recomputeMatrix();
                nextScanCell = probabilisticMatrix.computeMaxProbableCell();
            }
            scanner.setCellAsScannedOnBoard(nextScanCell);
            scanner.addScannedCell(nextScanCell);
            if (nextScanCell.containsShip()){
                scanner.incrementShipCellsCounter();
                HashMap<String,Position> validNeighbors = currBoard.getAPositionNeighborhood(nextScanCell);
                scanner.removeForbiddenNeighborsFromScan(nextScanCell,validNeighbors);
                probabilisticMatrix.addNeighborsToPriorityList(highPriorityNeighbors, validNeighbors);
            }
            this.scanCounter = scanner.getScanCounter();
        }

    }

    @Override
    public int getCounter() {
        return scanCounter;
    }
}
