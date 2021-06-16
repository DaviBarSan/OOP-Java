package test;

import client.Probabilistic;
import client.ScannigStrategies;
import client.Smart;
import org.junit.jupiter.api.Test;
import prod.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LikelihoodMatrixTester {

    //@Test
    //public void TestInsertingOneShipCells(){
    //    Board boardera = new Board(4,4,0);
    //    BoardScanner scanner = new BoardScanner(boardera);
    //    LikelihoodMatrix currMatrix = new LikelihoodMatrix(scanner);
    //    assertDoesNotThrow(() -> currMatrix.insertOneCellShipProbability());
    //}

    //@Test
    //public void TestInsertingOneShipCellsInAlreadyScannedBoard(){
    //    Board boardera = new Board(4,4,1);
    //    Ship ship1 = new Ship(1,2,2,'E');
    //    boardera.insertShipsOnBoard(ship1);
    //    BoardScanner scanner = new BoardScanner(boardera);
    //    ScannigStrategies scannigStrategies = new Smart();
    //    scannigStrategies.scan(boardera);
    //    LikelihoodMatrix currMatrix = new LikelihoodMatrix(scanner);
    //    assertDoesNotThrow(() -> currMatrix.insertOneCellShipProbability());
    //}

    @Test
    public void TestInsertingOneShipCellsInAlreadyScannedBoard1(){
        Board boardera = new Board(4,4,1);
        Ship ship1 = new Ship(1,2,2,'E');
        boardera.insertShipsOnBoard(ship1);
        BoardScanner scanner = new BoardScanner(boardera);
        ScannigStrategies scannigStrategies = new Smart();
        scannigStrategies.scan(boardera);
        LikelihoodMatrix currMatrix = new LikelihoodMatrix(scanner);
        LikelihoodMatrix.calculateMatrixScoreForShipSize(2);
        LikelihoodMatrix.calculateMatrixScoreForShipSize(3);

    }

    @Test
    public void calculateMatrix(){
        Board boardera = new Board(4,4,1);
        BoardScanner scanner = new BoardScanner(boardera);
        LikelihoodMatrix currMatrix = new LikelihoodMatrix(scanner);
        LikelihoodMatrix.recomputeMatrix();
        Position mostProbable = currMatrix.computeMaxProbableCell();
    }

    @Test
    public void testProbabilityScan(){
        Board boardera = new Board(4,4,1);
        Ship ship1 = new Ship(1,2,2,'E');
        boardera.insertShipsOnBoard(ship1);
        ScannigStrategies scannigStrategies = new Probabilistic();
        scannigStrategies.scan(boardera);
    }

    @Test
    public void testARealInput(){
        Board boardInput5 = new Board(10,10,5);
        Ship ship1 = new Ship(0,0,1,'E');
        Ship ship2 = new Ship(1,2,2,'E');
        Ship ship3 = new Ship(3,3,3,'S');
        Ship ship4 = new Ship(4,5,4,'S');
        Ship ship5 = new Ship(9,4,5,'E');
        boardInput5.insertShipsOnBoard(ship1);
        boardInput5.insertShipsOnBoard(ship2);
        boardInput5.insertShipsOnBoard(ship3);
        boardInput5.insertShipsOnBoard(ship4);
        boardInput5.insertShipsOnBoard(ship5);
        ScannigStrategies scanner = new Probabilistic();
        scanner.scan(boardInput5);
        assertEquals("+XXXX.XX.X\nXX++XXX.X.\nXXXXXX.X.X\n.XX+XXXXXX\nXXX+X+X.X.\nX.X+X+XX.X\n" +
                ".XXXX+XXXX\nX.X.X+X.X.\n.X.XXXXXXX\nX.XX+++++X", boardInput5.toString());
        assertEquals(58, scanner.getCounter());
    }

    @Test
    public void fullSample02(){
        Board boardSmp2 = new Board(5,6,2);
        boardSmp2.insertShipsOnBoard(new Ship(1,1,4,'E'));
        boardSmp2.insertShipsOnBoard(new Ship(3,3,2,'S'));
        ScannigStrategies scannigStrategies = new Probabilistic();
        scannigStrategies.scan(boardSmp2);
        System.out.println(boardSmp2.toString());
        System.out.println(scannigStrategies.getCounter());

    }
    @Test
    public void fullSample03(){
        Board boardSmp2 = new Board(3,10,5);
        boardSmp2.insertShipsOnBoard(new Ship(0,0,2,'E'));
        boardSmp2.insertShipsOnBoard(new Ship(2,0,1,'E'));
        boardSmp2.insertShipsOnBoard(new Ship(2,5,5,'E'));
        boardSmp2.insertShipsOnBoard(new Ship(0,6,4,'E'));
        boardSmp2.insertShipsOnBoard(new Ship(1,3,1,'E'));
        ScannigStrategies scannigStrategies = new Probabilistic();
        scannigStrategies.scan(boardSmp2);
        System.out.println(boardSmp2.toString());
        System.out.println(scannigStrategies.getCounter());
    }
    @Test
    public void testFilteringNotScannedCells(){
        Position a = new Position(1,2);
        a.setAsScanned();
        Position b = new Position(3,2);
        b.setAsScanned();
        Position c = new Position(5,2);
        Position d = new Position(1,3);
        Position e = new Position(1,4);
        Position f = new Position(1,5);
        f.setAsScanned();
        List<Position> filterList = new ArrayList<>(List.of(a,b,c,d,e,f));
        List<Position> filtredList = filterList.stream().filter(pos -> pos.isScanned() == false).collect(Collectors.toList());
        assertEquals("[(5, 2), (1, 3), (1, 4)]", filtredList.toString());
        filterList.removeIf(position -> position.isScanned() == true);
        assertEquals("[(5, 2), (1, 3), (1, 4)]", filterList.toString());

    }
}
