package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return team == chessGame.team && Objects.equals(board, chessGame.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, board);
    }

    private TeamColor team;
    private ChessBoard board;

    public ChessGame() {
        this.board = new ChessBoard();
        this.team = TeamColor.WHITE;
        this.board.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return team;
    }

    /**
     * Set's which teams turn it is
     *
     * @param color the team whose turn it is
     */
    public void setTeamTurn(TeamColor color) {
        if (color == TeamColor.WHITE)
        {
            team = TeamColor.WHITE;
        }
        else if (color == TeamColor.BLACK)
        {
            team = TeamColor.BLACK;
        }
        else
        {
            team = TeamColor.WHITE;
        }
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = board.getPiece(startPosition);
        if (piece == null)
        {
            return null;
        }
        Collection<ChessMove> move_list = piece.pieceMoves(board, startPosition);
        Collection<ChessMove> valid_list = new ArrayList<>();
        for (ChessMove move : move_list){
                ChessPiece end_piece = board.getPiece(move.getEndPosition());
                board.removePiece(startPosition);
                board.addPiece(move.getEndPosition(), piece);
                TeamColor color = piece.getTeamColor();
                if (!isInCheck(color))
                {
                    valid_list.add(move);
                }
                board.removePiece(move.getEndPosition());
                board.addPiece(startPosition, piece);
                board.addPiece(move.getEndPosition(), end_piece);
        }
        return valid_list;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition startPosition = move.getStartPosition();
        ChessPosition endPosition = move.getEndPosition();
        if (board.getPiece(startPosition) == null)
        {
            throw new InvalidMoveException();
        }
        ChessPiece move_piece = board.getPiece(startPosition);
        if (move_piece.getTeamColor() != team)
        {
            throw new InvalidMoveException();
        }
        Collection<ChessMove> valid_list = validMoves(startPosition);
        for (ChessMove valid_move : valid_list){
            if (valid_move.equals(move))
            {
                board.removePiece(startPosition);
                if (move.getPromotionPiece() != null)
                {
                    move_piece = new ChessPiece(team, move.getPromotionPiece());
                }
                board.addPiece(endPosition, move_piece);
                if (team == TeamColor.WHITE)
                {
                    setTeamTurn(TeamColor.BLACK);
                }
                else if (team == TeamColor.BLACK)
                {
                    setTeamTurn(TeamColor.WHITE);
                }
                return;
            }
        }
        throw new InvalidMoveException();
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        int col = 1;
        while (col <= 8){
            int row = 1;
            while (row <= 8){
                ChessPosition test_position = new ChessPosition(row, col);
                if (board.getPiece(test_position) != null)
                {
                    ChessPiece test_piece = board.getPiece(test_position);
                    if (test_piece.getTeamColor() != teamColor)
                    {
                        Collection<ChessMove> valid_list = test_piece.pieceMoves(board, test_position);
                        for (ChessMove move : valid_list)
                        {
                            ChessPosition end_position = move.getEndPosition();
                            if (board.getPiece(end_position) != null)
                            {
                                ChessPiece end_piece = board.getPiece(end_position);
                                if (end_piece.getPieceType()== ChessPiece.PieceType.KING)
                                {
                                    return true;
                                }
                            }
                        }
                    }
                }
                row +=1;
            }
            col+=1;
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if (!isInCheck(teamColor))
        {
            return false;
        }
        int col = 1;
        while (col <= 8){
            int row = 1;
            while (row <= 8){
                ChessPosition test_position = new ChessPosition(row, col);
                if (board.getPiece(test_position) != null)
                {
                    ChessPiece test_piece = board.getPiece(test_position);
                    if (test_piece.getTeamColor() == teamColor)
                    {
                        Collection<ChessMove> valid_list = validMoves(test_position);
                        if (!valid_list.isEmpty() )
                        {
                            return false;
                        }
                    }
                }
                row +=1;
            }
            col+=1;
        }
        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (isInCheck(teamColor))
        {
            return false;
        }
        int col = 1;
        while (col <= 8){
            int row = 1;
            while (row <= 8){
                ChessPosition test_position = new ChessPosition(row, col);
                if (board.getPiece(test_position) != null)
                {
                    ChessPiece test_piece = board.getPiece(test_position);
                    if (test_piece.getTeamColor() == teamColor)
                    {
                        Collection<ChessMove> valid_list = validMoves(test_position);
                        if (!valid_list.isEmpty() )
                        {
                            return false;
                        }
                    }
                }
                row +=1;
            }
            col+=1;
        }
        return true;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }
}
