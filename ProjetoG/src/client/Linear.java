package client;

import prod.Board;
import prod.BoardScanner;
import prod.Position;

public class Linear implements ScannigStrategies{
    int scanCounter;
    @Override
    public void scan(Board board) {
        BoardScanner currScanner = new BoardScanner(board);
        int shipCellsFound = 0;
        while (currScanner.getShipCounter() < board.getNumberOfOccupedCells()){
            Position currPos = currScanner.getToScanCells().remove(0);
            currScanner.setCellAsScannedOnBoard(currPos);
            currScanner.addScannedCell(currPos);
            if (currPos.containsShip()){
                  currScanner.incrementShipCellsCounter();
            }
            this.scanCounter = currScanner.getScanCounter();

        }
        //for (Position scanCell: currScanner.getToScanCells()) {
        //    currScanner.setCellAsScanned(scanCell);
        //    currScanner.addScannedCell(scanCell);
        //    if (scanCell.containsShip()) {
        //        currScanner.incrementShipCellsCounter();
        //    }
        //    if (currScanner.getShipCounter() == board.getNumberOfOccupedCells()) break;
        //    }
        //    this.scanCounter = currScanner.getScanCounter();
    }

    /**
     * Number of cells already scanned in board;
     * @return integer counter to scanned cells;
     */
    @Override
    public int getCounter() {return scanCounter;
    }
}
