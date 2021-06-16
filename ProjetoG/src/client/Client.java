package client;

import prod.Board;
import prod.Ship;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String strategy = sc.next();
        ScannigStrategies scannigStrategy = null;
        if (strategy.equals("linear"))  scannigStrategy = new Linear();
        if (strategy.equals("smart")) scannigStrategy = new Smart();
        if (strategy.equals("prob")) scannigStrategy = new Probabilistic();
        Board board = new Board(sc.nextInt(), sc.nextInt(), sc.nextInt());
        insertShipsOnBoard(sc, board);
        scannigStrategy.scan(board);
        System.out.println(scannigStrategy.getCounter());
        System.out.println(board);
    }

    private static void insertShipsOnBoard(Scanner sc, Board currBoard){
        for(int i = 0; i < currBoard.getNumberOfShips(); i++){
            int row = sc.nextInt();
            int col = sc.nextInt();
            int size = sc.nextInt();
            char orientation = sc.next().charAt(0);
            currBoard.insertShipsOnBoard(new Ship(row, col, size, orientation));
        }
    }

}
