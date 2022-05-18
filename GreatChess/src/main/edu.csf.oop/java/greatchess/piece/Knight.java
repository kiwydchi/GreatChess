package greatchess.piece;

import greatchess.board.Position;
import greatchess.board.Piece;
import greatchess.board.Board;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Knight extends Piece {

    public Knight(Color color, Position pos) {
        super(color, pos, "Kn");
    }

    @Override
    public ArrayList<Position> moveFigureToAnotherPosition(Board board) {
        ArrayList<Position> correctPos = new ArrayList<>();
        char column = this.getPosition().getColumn();
        int row = this.getPosition().getRow();
        row += 1;
        column += 2;
        if (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
        }
        row -= 2;
        if (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
        }
        row += 2;
        column -= 4;
        if (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
        }
        row -= 2;
        if (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
        }
        column++;
        row += 3;
        if (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
        }
        row -= 4;
        if (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
        }
        column += 2;
        if (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
        }
        row += 4;
        if (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
        }
        return correctPos;
    }
}
