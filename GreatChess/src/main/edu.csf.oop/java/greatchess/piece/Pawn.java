package greatchess.piece;

import greatchess.board.Position;
import greatchess.board.Piece;
import greatchess.board.Board;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Color color, Position pos) {
        super(color, pos, "P");
    }

    @Override
    public ArrayList<Position> moveFigureToAnotherPosition(Board board) {
        ArrayList<Position> correctPos = new ArrayList<>();
        char column = this.getPosition().getColumn();
        int row = this.getPosition().getRow();
        row = this.getColor() == Color.WHITE ? row + 1 : row - 1;
        if (new Position(column, row).positionOnBoard() && board.getCell(new Position(column, row)).getFigure() == null) {
            correctPos.add(new Position(column, row));
        }
        column++;
        if (new Position(column, row).positionOnBoard() && !(Math.abs(this.getPosition().getRow() - row) > 1)) {
            correctPos.add(new Position(column, row));
        }
        column -= 2;
        if (new Position(column, row).positionOnBoard() && !(Math.abs(this.getPosition().getRow() - row) > 1)) {
            correctPos.add(new Position(column, row));
        }
        return correctPos;
    }
}