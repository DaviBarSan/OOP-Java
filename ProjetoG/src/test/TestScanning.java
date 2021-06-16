package test;

import client.Linear;
import client.ScannigStrategies;
import client.Smart;
import org.junit.jupiter.api.Test;
import prod.Board;
import prod.BoardScanner;
import prod.Position;
import prod.Ship;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestScanning {
    @Test
    public void TestLinearScanning(){
        Board surfboard = new Board(5,6,2);
        Ship ship1 = new Ship(1, 1, 4, 'E');
        Ship ship2 = new Ship(3,3,2,'S');
        surfboard.insertShipsOnBoard(ship1);
        surfboard.insertShipsOnBoard(ship2);
        ScannigStrategies scanner = new Linear();
        scanner.scan(surfboard);
        assertEquals("XXXXXX\nX++++X\nXXXXXX\nXXX+XX\nXXX+..", surfboard.toString());
        System.out.println(surfboard);
        assertEquals(28, scanner.getCounter());
    }

    @Test
    public void TestScannerProprietiesSizeNotNull(){
        Board surfboard = new Board(5,6,2);
        Ship ship1 = new Ship(1, 1, 4, 'E');
        Ship ship2 = new Ship(3,3,2,'S');
        surfboard.insertShipsOnBoard(ship1);
        surfboard.insertShipsOnBoard(ship2);
        ScannigStrategies scanner = new Linear();
        assertEquals(0,scanner.getCounter());
    }

    @Test
    public void TestScannerBoardReferenceToRealBoard(){
        Board surfboard = new Board(5,6,2);
        BoardScanner newScanner = new BoardScanner(surfboard);
        assertEquals(Board.getCurrBoard().get(5), newScanner.getToScanCells().get(5));
    }

    @Test
    public void TestRemovingForbiddenNeighbors1(){
        Board snowboard = new Board(3,3,1);
        BoardScanner scanner = new BoardScanner(snowboard);
        //must fit in board;
        Ship shipado = new Ship(1,2,1,'E');
        snowboard.insertShipsOnBoard(shipado);
        for (Position posInShipado: snowboard.getShipsCoordsOnBoard()) {
            HashMap<String,Position> currNeighbor = snowboard.getAPositionNeighborhood(posInShipado);
            scanner.removeForbiddenNeighborsFromScan(posInShipado, currNeighbor);
            currNeighbor.clear();
        }
        assertEquals(".X.\n..O\n.X.", snowboard.toString());
    }

    @Test
    public void TestRemovingForbiddenNeighbors2(){
        Board snowboard = new Board(3,3,1);
        BoardScanner scanner = new BoardScanner(snowboard);
        //must fit in board;
        Ship shipado = new Ship(1,0,2,'E');
        snowboard.insertShipsOnBoard(shipado);
        for (Position posInShipado: snowboard.getShipsCoordsOnBoard()) {
            HashMap<String,Position> currNeighbor = snowboard.getAPositionNeighborhood(posInShipado);
            scanner.removeForbiddenNeighborsFromScan(posInShipado, currNeighbor);
            currNeighbor.clear();
        }
        assertEquals("XXX\nOO.\nXXX", snowboard.toString());
    }

    @Test
    public void TestSmartStrategy(){
        Board smartBoard = new Board(5,6,2);
        Ship ship1 = new Ship(1,1,4,'E');
        smartBoard.insertShipsOnBoard(ship1);
        Ship ship2 = new Ship(3,3,2, 'S');
        smartBoard.insertShipsOnBoard(ship2);
        ScannigStrategies strategyScanner = new Smart();
        strategyScanner.scan(smartBoard);
        assertEquals("XXXXXX\nX++++X\nXXXXXX\nXXX+X.\n..X+X.", smartBoard.toString());
        assertEquals(18, strategyScanner.getCounter());
    }

    @Test
    public void testFindingNextShipCells(){
        Board blackBoard = new Board(6,3,1);
        Ship longShip = new Ship(1,2,4,'S');
        blackBoard.insertShipsOnBoard(longShip);
        ScannigStrategies scannigStrategies = new Smart();
        scannigStrategies.scan(blackBoard);
        assertEquals("XXX\nXX+\n.X+\n.X+\n.X+\n.X.", blackBoard.toString());
    }

    @Test
    public void testSmartStrategy2(){
        Board motherboard = new Board(3, 10 ,5);
        Ship ship1 = new Ship(0, 0, 2, 'E');
        Ship ship2 = new Ship(2, 0, 1, 'E');
        Ship ship3 = new Ship(2, 5, 5, 'E');
        Ship ship4 = new Ship(0, 6, 4, 'E');
        Ship ship5 = new Ship(1, 3, 1, 'E');
        ScannigStrategies scanner = new Smart();
        motherboard.insertShipsOnBoard(ship1);
        motherboard.insertShipsOnBoard(ship2);
        motherboard.insertShipsOnBoard(ship3);
        motherboard.insertShipsOnBoard(ship4);
        motherboard.insertShipsOnBoard(ship5);
        scanner.scan(motherboard);
        assertEquals("++XXXX++++\n" +"XXX+XXXXXX\n" +"+XXXX+++++", motherboard.toString());
        assertEquals(20, scanner.getCounter());
    }

    @Test
    public void testFinalSmartStrategyInput(){
        Board motherboard = new Board(10, 10 ,5);
        Ship ship1 = new Ship(0, 0, 1, 'E');
        Ship ship2 = new Ship(1, 2, 2, 'E');
        Ship ship3 = new Ship(3, 3, 3, 'S');
        Ship ship4 = new Ship(4, 5, 4, 'S');
        Ship ship5 = new Ship(9, 4, 5, 'E');
        ScannigStrategies scanner = new Smart();
        motherboard.insertShipsOnBoard(ship1);
        motherboard.insertShipsOnBoard(ship2);
        motherboard.insertShipsOnBoard(ship3);
        motherboard.insertShipsOnBoard(ship4);
        motherboard.insertShipsOnBoard(ship5);
        scanner.scan(motherboard);
        assertEquals("+XXXXXXXXX\n" +"XX++XXXXXX\n" +"XXXXXXXXXX\n"+"XXX+XXXXXX\n"+"XXX+X+XXXX\n"+"XXX+X+XXXX\n"+"XXXXX+XXXX\n"+"XXXXX+XXXX\n"+"XXXXXXXXXX\n"+"XXXX+++++.", motherboard.toString());
        assertEquals(82, scanner.getCounter());
    }

    @Test
    public void testRotate(){
        Board board = new Board(3,3,0);
        BoardScanner scanner = new BoardScanner(board);
        List<Position> toScanList = scanner.getToScanCells();
        Position sCell = toScanList.remove(4);
        toScanList.add(sCell);
        Collections.rotate(toScanList.subList(1, toScanList.size()), 1);
        System.out.println(toScanList);
    }
    @Test
    public void testRotate1() {
        Board board = new Board(3, 3, 0);
        BoardScanner scanner = new BoardScanner(board);
        List<Position> toScanList = scanner.getToScanCells();
        Position sCell = toScanList.remove(4);
        toScanList.add(sCell);
        Collections.rotate(toScanList.subList(0, toScanList.size()), 1);
        System.out.println(toScanList);
    }
}
