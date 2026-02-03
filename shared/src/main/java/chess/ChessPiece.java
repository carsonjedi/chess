package chess;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import chess.PieceMovesCalculator;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        if(type == PieceType.PAWN){
            return PieceMovesCalculator.pawn_moves_list(board, myPosition);
        }
        if(type == PieceType.KING){
            return PieceMovesCalculator.king_moves_list(board, myPosition);
        }
        if(type == PieceType.BISHOP){
            return PieceMovesCalculator.bishop_moves_list(board, myPosition);
        }
        if(type == PieceType.ROOK){
            return PieceMovesCalculator.rook_moves_list(board, myPosition);
        }
        if(type == PieceType.QUEEN){
            return PieceMovesCalculator.queen_moves_list(board, myPosition);
        }
        if(type == PieceType.KNIGHT){
            return PieceMovesCalculator.knight_moves_list(board, myPosition);
        }
        return List.of();
    }
}
