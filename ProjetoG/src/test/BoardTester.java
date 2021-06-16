package test;

import org.junit.jupiter.api.Test;
import prod.Board;
import prod.Position;
import prod.Ship;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTester {

    @Test
    public void CreateANewBoard(){
        Board testBoard = new Board(3,4,2);
        assertNotNull(testBoard);
    }

    @Test
    public void boardToString(){
        Board skateBoard = new Board(3, 4, 2);
        assertEquals("....\n....\n....", skateBoard.toString());
        System.out.println(skateBoard);
    }

    @Test
    public void insertingShips(){
        Board surfboard = new Board(5,6,2);
        Ship ship1 = new Ship(1, 1, 4, 'E');
        Ship ship2 = new Ship(3,3,2,'S');
        surfboard.insertShipsOnBoard(ship1);
        surfboard.insertShipsOnBoard(ship2);
        assertEquals("......\n.OOOO.\n......\n...O..\n...O..", surfboard.toString());
        System.out.println(surfboard);
    }

    @Test
    public void insertInvalidPosition(){
        Board skinBoard = new Board(3, 3, 1);
        Ship currShip = new Ship(2,2,2,'S');
        assertThrows(IndexOutOfBoundsException.class, () -> skinBoard.insertShipsOnBoard(currShip));
    }

    @Test
    public void testNeighborPosition(){
        Board boardzin = new Board(4,4,1);
        Ship shiper = new Ship(2,1,2,'S');
        boardzin.insertShipsOnBoard(shiper);
        Position eastSide = boardzin.getAPositionNeighborhood(shiper.getCoords().get(1)).get("E");
        assertEquals(Board.getPosInListFromCoordinates(3,2), eastSide);
    }

}
