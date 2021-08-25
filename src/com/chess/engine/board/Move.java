package com.chess.engine.board;

import com.chess.engine.board.Board.Builder;
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

    public abstract Board execute();

    public Piece getPieceMoved() {
        return pieceMoved;
    }

    public static final class MajorMove extends Move {
        public MajorMove(final Board board,
                  final Piece movedPiece,
                  final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
                // TODO: hashcode and equals for pieces
                if (!this.pieceMoved.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }

            builder.setPiece(this.pieceMoved.movePiece(this));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
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

        @Override
        public Board execute() {
            return null;
        }
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }



}
