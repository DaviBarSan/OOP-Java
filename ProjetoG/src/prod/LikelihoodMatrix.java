package prod;

import java.util.*;

public class LikelihoodMatrix {
    static TreeMap<Position,Integer> likelihoodMatrix;
    BoardScanner currentBoardScanner;
    Board currBoard;

    public LikelihoodMatrix(BoardScanner currentBoardScanner) {
        likelihoodMatrix = matrixGenerator();
        this.currentBoardScanner = currentBoardScanner;
        currBoard = BoardScanner.boardToScan;

    }

    private TreeMap<Position,Integer> matrixGenerator(){
        TreeMap<Position,Integer> newMatrix = new TreeMap<>(new PositionSorter());
        for (Position currPos: Board.getCurrBoard()) {
            newMatrix.put(currPos,0);
        }
        return newMatrix;
    }

    private static void resetMatrixScores(){
        int maxRowIdx = Board.MAXROWINDEX;
        int maxColIdx = Board.MAXCOLINDEX;
        for (int row = 0; row <= maxRowIdx; row++)
            for (int col = 0; col <= maxColIdx; col++){
                Position curCell = Board.getPosInListFromCoordinates(row,col);
                if (curCell.scanned){
                    likelihoodMatrix.put(curCell, -1);
                }
                else likelihoodMatrix.put(curCell,0);
            }
    }
    public static void calculateMatrixScoreForShipSize(int shipSize){
        int maxRowIdx = Board.MAXROWINDEX;
        int maxColIdx = Board.MAXCOLINDEX;
        for (int row = 0; row <= maxRowIdx; row++)
            for (int col = 0; col <= maxColIdx; col++){
                Position shipFirstCell = Board.getPosInListFromCoordinates(row,col);
                if (!shipFirstCell.scanned){
                    List<Position> wholeShipVertically = shipFitsVertically(shipFirstCell,shipSize);
                    List<Position> wholeShipHorizontally = shipFitsHorizontally(shipFirstCell, shipSize);
                    if (wholeShipVertically.size() == shipSize) incrementWholeShipInMatrix(wholeShipVertically);
                    if (wholeShipHorizontally.size() == shipSize) incrementWholeShipInMatrix(wholeShipHorizontally);
                }
            }
    }

    private static List<Position> shipFitsVertically(Position firstShipCell, int shipSize){
        List<Position> wholeShipHorizontaly = new ArrayList<>();
        for (int row = firstShipCell.row; row < firstShipCell.row + shipSize; row++){
            Position currCell = Board.getPosInListFromCoordinates(row, firstShipCell.col);
            if (currCell != null && !currCell.scanned)wholeShipHorizontaly.add(currCell);
        }
        return wholeShipHorizontaly;
    }
    private static List<Position> shipFitsHorizontally(Position firstShipCell, int shipSize){
        List<Position> wholeShipVertically = new ArrayList<>();
        for (int col = firstShipCell.col; col < firstShipCell.col + shipSize; col++){
            Position currCell = Board.getPosInListFromCoordinates(firstShipCell.row, col);
            if (currCell!=null && !currCell.scanned)wholeShipVertically.add(currCell);
        }
        return wholeShipVertically;
    }
    private static void incrementWholeShipInMatrix(List<Position> wholeShip){
        for (Position curValidCell : wholeShip){
            int finalScore = likelihoodMatrix.get(curValidCell) + 1;
            likelihoodMatrix.put(curValidCell,finalScore);
        }
    }

    public Position computeMaxProbableCell(){
        Position probableCell = likelihoodMatrix.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();
        return probableCell;
    }

    public static void recomputeMatrix(){
        resetMatrixScores();
        calculateMatrixScoreForShipSize(1);
        calculateMatrixScoreForShipSize(2);
        calculateMatrixScoreForShipSize(3);
        calculateMatrixScoreForShipSize(4);
        calculateMatrixScoreForShipSize(5);
    }

    public void addNeighborsToPriorityList(List<Position> priorityList, HashMap<String,Position> validNeighbors){
        priorityList.removeIf(position -> position.isScanned() == true);
        List<Position> tmp = new ArrayList<>();
        tmp.add(validNeighbors.get("N"));
        tmp.add(validNeighbors.get("E"));
        tmp.add(validNeighbors.get("S"));
        tmp.add(validNeighbors.get("W"));
        for (Position currPos : tmp) {
            if (currPos != null && !currPos.scanned) priorityList.add(currPos);
        }
    }




}
