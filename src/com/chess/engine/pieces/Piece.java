package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final int piecePosition;
    // WHETHER PIECE IS WHITE OR BLACK
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    private final int cachedHashCode;

    Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        // TODO: more work here!
        this.isFirstMove = false;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Piece)) {
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType()
                && pieceAlliance == otherPiece.getPieceAlliance() && isFirstMove == otherPiece.isFirstMove();
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public abstract Piece movePiece(Move move);

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public int getPiecePosition() {
        return piecePosition;
    }

    public enum PieceType {

        PAWN("P"){
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT("N"){
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B"){
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R"){
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q"){
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K"){
            @Override
            public boolean isKing() {
                return true;
            }
        };

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

    }

}
