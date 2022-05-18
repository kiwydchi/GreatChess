package greatchess.piece;

import greatchess.board.Position;
import greatchess.board.Piece;
import greatchess.board.Board;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class King extends Piece {

    public King(Color color, Position pos) {
        super(color, pos, "K");
    }

    @Override
    public ArrayList<Position> moveFigureToAnotherPosition(Board board) {
        ArrayList<Position> correctPos = new ArrayList<>();
        char column = this.getPosition().getColumn();
        int row = this.getPosition().getRow();
        for (int i = row - 1; i <= row + 1; i++) {
            column--;
            if (new Position(column, i).positionOnBoard()) {
                if (board.getCell(new Position(column, i)).getFigure() == null) {
                    correctPos.add(new Position(column, i));
                } else if (board.getCell(new Position(column, i)).getFigure().getColor() != this.getColor()) {
                    correctPos.add(new Position(column, i));
                }
            }
            column++;
            if (new Position(column, i).positionOnBoard()) {
                if (board.getCell(new Position(column, i)).getFigure() == null) {
                    correctPos.add(new Position(column, i));
                } else if (board.getCell(new Position(column, i)).getFigure().getColor() != this.getColor()) {
                    correctPos.add(new Position(column, i));
                }
            }
            column++;
            if (new Position(column, i).positionOnBoard()) {
                if (board.getCell(new Position(column, i)).getFigure() == null) {
                    correctPos.add(new Position(column, i));
                } else if (board.getCell(new Position(column, i)).getFigure().getColor() != this.getColor()) {
                    correctPos.add(new Position(column, i));
                }
            }
            column = this.getPosition().getColumn();
        }
        return correctPos;
    }
}
