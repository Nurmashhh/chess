package com.nurmash;

import com.nurmash.board.Board;
import com.nurmash.board.BoardConsoleRenderer;
import com.nurmash.board.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final Board board;

    private BoardConsoleRenderer renderer = new BoardConsoleRenderer();
    private final List<GameStateChecker> checkers = List.of(new StalemateGameStateChecker(), new CheckmateGameStateChecker());

    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop() {
        Color colorToMove = Color.WHITE;

        GameState state = determineGameState(board, colorToMove);

        while (state == GameState.ONGOING) {
            // render
            renderer.render(board);

            if (colorToMove == Color.WHITE) {
                System.out.println("White to move");
            } else {
                System.out.println("Black to move");
            }

            // input
            Move move = InputCoordinates.inputMove(board, colorToMove, renderer);

            // move
            board.makeMove(move);

            // pass move
            colorToMove = colorToMove.opposite();

            state = determineGameState(board, colorToMove);
        }

        renderer.render(board);

        System.out.println("Game ended with state = " + state);
    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateChecker checker : checkers) {
            GameState state = checker.check(board, color);

            if (state != GameState.ONGOING) {
                return state;
            }
        }

        return GameState.ONGOING;
    }
}
