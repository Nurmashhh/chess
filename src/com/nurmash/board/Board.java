package com.nurmash.board;

import com.nurmash.Color;
import com.nurmash.Coordinates;
import com.nurmash.File;
import com.nurmash.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Board {
    public HashMap<Coordinates, Piece> pieces = new HashMap<>();
    public final String startingFen;
    public List<Move> moves = new ArrayList<>();

    public Board(String startingFen) {
        this.startingFen = startingFen;
    }

    public void setPiece(Coordinates coordinates, Piece piece) {
        piece.coordinates = coordinates;
        pieces.put(coordinates, piece);
    }

    public void removePiece(Coordinates coordinates) {
        pieces.remove(coordinates);
    }

    public void movePiece(Coordinates from, Coordinates to) {
        Piece piece = getPiece(from);

        removePiece(from);
        setPiece(to, piece);
        moves.add(new Move(from, to));
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !pieces.containsKey(coordinates);
    }

    public Piece getPiece(Coordinates coordinates) {
        return pieces.get(coordinates);
    }

    public void setUpDefaultPiecesPositions() {
        //set pawns
        for (File file : File.values()) {
            setPiece(new Coordinates(file, 2), new Pawn(Color.WHITE, new Coordinates(file, 2)));
            setPiece(new Coordinates(file, 7), new Pawn(Color.BLACK, new Coordinates(file, 7)));
        }

        // set rooks
        setPiece(new Coordinates(File.A, 1), new Rook(Color.WHITE, new Coordinates(File.A, 1)));
        setPiece(new Coordinates(File.H, 1), new Rook(Color.WHITE, new Coordinates(File.H, 1)));
        setPiece(new Coordinates(File.A, 8), new Rook(Color.BLACK, new Coordinates(File.A, 8)));
        setPiece(new Coordinates(File.H, 8), new Rook(Color.BLACK, new Coordinates(File.H, 8)));

        //set knights
        setPiece(new Coordinates(File.B, 1), new Knight(Color.WHITE, new Coordinates(File.B, 1)));
        setPiece(new Coordinates(File.G, 1), new Knight(Color.WHITE, new Coordinates(File.G, 1)));
        setPiece(new Coordinates(File.B, 8), new Knight(Color.BLACK, new Coordinates(File.B, 8)));
        setPiece(new Coordinates(File.G, 8), new Knight(Color.BLACK, new Coordinates(File.G, 8)));

        //set Bishops
        setPiece(new Coordinates(File.C, 1), new Bishop(Color.WHITE, new Coordinates(File.C, 1)));
        setPiece(new Coordinates(File.F, 1), new Bishop(Color.WHITE, new Coordinates(File.F, 1)));
        setPiece(new Coordinates(File.C, 8), new Bishop(Color.BLACK, new Coordinates(File.C, 8)));
        setPiece(new Coordinates(File.F, 8), new Bishop(Color.BLACK, new Coordinates(File.F, 8)));

        //set Queens
        setPiece(new Coordinates(File.D, 1), new Queen(Color.WHITE, new Coordinates(File.A, 1)));
        setPiece(new Coordinates(File.D, 8), new Queen(Color.BLACK, new Coordinates(File.A, 8)));

        //set Kings
        setPiece(new Coordinates(File.E, 1), new King(Color.WHITE, new Coordinates(File.A, 1)));
        setPiece(new Coordinates(File.E, 8), new King(Color.BLACK, new Coordinates(File.A, 8)));
    }

    public static boolean isSquareDark(Coordinates coordinates) {
        return (((coordinates.file.ordinal() + 1) + coordinates.rank) % 2) == 0;
    }

    public boolean isSquareAttackedByColor(Coordinates coordinates, Color color) {
        List<Piece> pieces = getPiecesByColor(color);

        for (Piece piece : pieces) {
            Set<Coordinates> attackedSquares = piece.getAttackedSquares(this);

            if (attackedSquares.contains(coordinates)) {
                return true;
            }
        }

        return false;
    }

    public List<Piece> getPiecesByColor(Color color) {
        List<Piece> result = new ArrayList<>();

        for (Piece piece : pieces.values()) {
            if (piece.color == color) {
                result.add(piece);
            }
        }

        return result;
    }

    public void makeMove(Move move) {
        Piece piece = getPiece(move.from);

        removePiece(move.from);
        setPiece(move.to, piece);

        moves.add(move);
    }
}