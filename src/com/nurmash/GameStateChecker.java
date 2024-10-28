package com.nurmash;

import com.nurmash.board.Board;

public abstract class GameStateChecker {
    public abstract GameState check(Board board, Color color);


}
