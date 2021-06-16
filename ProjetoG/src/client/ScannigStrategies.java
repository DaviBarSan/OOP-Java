package client;

import prod.Board;

public interface ScannigStrategies {
    public abstract void scan(Board currBoard);
    public abstract int getCounter();
}
