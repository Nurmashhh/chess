package com.nurmash.piece;

import com.nurmash.Color;
import com.nurmash.Coordinates;
import com.nurmash.CoordinatesShift;

import java.util.Set;

public class Rook extends LongRangePiece implements IRook {
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return getRookMoves();
    }
}
