package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {

    final Board board;
    final Piece pieceMoved;
    final int destinationCoordinate;

    private Move(final Board board,
                final Piece pieceMoved,
                final int destinationCoordinate) {
        this.board = board;
        this.pieceMoved = pieceMoved;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static final class MajorMove extends Move {
        public MajorMove(final Board board,
                  final Piece movedPiece,
                  final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move {
        final Piece attackedPiece;

        public AttackMove(final Board board,
                          final Piece pieceMoved,
                          final int destinationCoordinate,
                          final Piece attackedPiece) {
            super(board, pieceMoved, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }



}
