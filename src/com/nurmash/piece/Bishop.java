package com.nurmash.piece;

import com.nurmash.Color;
import com.nurmash.Coordinates;
import com.nurmash.CoordinatesShift;

import java.util.Set;

public class Bishop extends LongRangePiece implements IBishop {
    public Bishop(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        return getBishopMoves();
    }
}
