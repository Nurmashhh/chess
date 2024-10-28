package com.nurmash;

import com.nurmash.board.Board;
import com.nurmash.board.BoardConsoleRenderer;
import com.nurmash.board.BoardFactory;

public class Main {
    public static void main(String[] args) {
//        Board board = new Board();
//        board.setUpDefaultPiecesPositions();

        Board board = (new BoardFactory()).fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1a1");

        BoardConsoleRenderer boardConsoleRenderer = new BoardConsoleRenderer();
//        boardConsoleRenderer.render(board);
//        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
//        renderer.render(board);
//
//        Piece piece = board.getPiece(new Coordinates(File.G, 8));
//        Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
//
//        int a = 123;

        Game game = new Game(board);
        game.gameLoop();


    }
}