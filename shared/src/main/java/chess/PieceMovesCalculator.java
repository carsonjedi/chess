package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PieceMovesCalculator {
    private static ChessBoard board;
    private static ChessPosition myPosition;

    public static Collection<ChessMove> bishop_moves_list(ChessBoard board, ChessPosition myPosition){
        PieceMovesCalculator.board = board;
        PieceMovesCalculator.myPosition = myPosition;
        return List.of();
    }
    public static Collection<ChessMove> king_moves_list(ChessBoard board, ChessPosition myPosition){
        PieceMovesCalculator.board = board;
        PieceMovesCalculator.myPosition = myPosition;
        int col = myPosition.getColumn();
        int row = myPosition.getRow();
        Collection<ChessMove> move_list = new ArrayList<>();
        int col_iter = -1;
        while (col_iter < 2)
        {
            int row_iter = -1;
            while (row_iter < 2)
            {
                if(row + row_iter >= 1 && row + row_iter <=8 && col + col_iter >=1 && col + col_iter <=8) {
                    ChessPosition new_position = new ChessPosition(row + row_iter, col + col_iter);
                    if (board.getPiece(new_position) == null) {
                        ChessMove move = new ChessMove(myPosition, new_position, null);
                        move_list.add(move);
                    } else if (board.getPiece(new_position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        ChessMove move = new ChessMove(myPosition, new_position, null);
                        move_list.add(move);
                    }
                }
                row_iter = row_iter + 1;
            }
            col_iter +=1;
        }
        return move_list;
    }
    public static Collection<ChessMove> knight_moves_list(ChessBoard board, ChessPosition myPosition){
        PieceMovesCalculator.board = board;
        PieceMovesCalculator.myPosition = myPosition;
        int col = myPosition.getColumn();
        int row = myPosition.getRow();
        Collection<ChessMove> move_list = new ArrayList<>();
        if(myPosition.getColumn() > 2)
        {
            if(myPosition.getRow() < 8)
            {
                ChessPosition new_position = new ChessPosition(row + 1, col - 2);
                if (board.getPiece(new_position) == null) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
                else if (board.getPiece(new_position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
            }
        }
        if(myPosition.getColumn() < 7)
        {
            if(myPosition.getRow() < 8)
            {
                ChessPosition new_position = new ChessPosition(row + 1, col + 2);
                if (board.getPiece(new_position) == null) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
                else if (board.getPiece(new_position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
            }
        }
        if(myPosition.getColumn() > 2)
        {
            if(myPosition.getRow() > 1)
            {
                ChessPosition new_position = new ChessPosition(row -1, col -2);
                if (board.getPiece(new_position) == null) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
                else if (board.getPiece(new_position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
            }
        }
        if(myPosition.getColumn() > 1)
        {
            if(myPosition.getRow() > 2)
            {
                ChessPosition new_position = new ChessPosition(row - 2, col - 1);
                if (board.getPiece(new_position) == null) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
                else if (board.getPiece(new_position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
            }
        }
        if(myPosition.getColumn() > 1)
        {
            if(myPosition.getRow() < 7)
            {
                ChessPosition new_position = new ChessPosition(row + 2, col - 1);
                if (board.getPiece(new_position) == null) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
                else if (board.getPiece(new_position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
            }
        }
        if(myPosition.getColumn() < 8)
        {
            if(myPosition.getRow() > 2)
            {
                ChessPosition new_position = new ChessPosition(row - 2, col + 1);
                if (board.getPiece(new_position) == null) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
                else if (board.getPiece(new_position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
            }
        }
        if(myPosition.getColumn() < 8)
        {
            if(myPosition.getRow() < 7)
            {
                ChessPosition new_position = new ChessPosition(row + 2, col + 1);
                if (board.getPiece(new_position) == null) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
                else if (board.getPiece(new_position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
            }
        }
        if(myPosition.getColumn() < 7)
        {
            if(myPosition.getRow() > 1)
            {
                ChessPosition new_position = new ChessPosition(row - 1, col + 2);
                if (board.getPiece(new_position) == null) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
                else if (board.getPiece(new_position).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                    ChessMove move = new ChessMove(myPosition, new_position, null);
                    move_list.add(move);
                }
            }
        }
        return move_list;
    }
    public static Collection<ChessMove> pawn_moves_list(ChessBoard board, ChessPosition myPosition){
        PieceMovesCalculator.board = board;
        PieceMovesCalculator.myPosition = myPosition;

        return List.of();
    }
    public static Collection<ChessMove> queen_moves_list(ChessBoard board, ChessPosition myPosition){
        PieceMovesCalculator.board = board;
        PieceMovesCalculator.myPosition = myPosition;

        return List.of();
    }
    public static Collection<ChessMove> rook_moves_list(ChessBoard board, ChessPosition myPosition){
        PieceMovesCalculator.board = board;
        PieceMovesCalculator.myPosition = myPosition;
        return List.of();
    }
}
