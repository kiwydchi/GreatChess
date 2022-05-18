package greatchess.piece;

import greatchess.board.Position;
import greatchess.board.Piece;
import greatchess.board.Board;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Color color, Position pos) {
        super(color, pos, "B");
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
        column = this.getPosition().getColumn();
        column--;
        row = this.getPosition().getRow();
        row++;
        while (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getFigure() != null) {
                if (!(board.getCell(new Position(column, row)).getFigure() instanceof King)
                        || board.getCell(new Position(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    column--;
                    row++;
                    if (new Position(column, row).positionOnBoard()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            column--;
            row++;
        }
        column = this.getPosition().getColumn();
        column++;
        row = this.getPosition().getRow();
        row--;
        while (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getFigure() != null) {
                if (!(board.getCell(new Position(column, row)).getFigure() instanceof King)
                        || board.getCell(new Position(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    column++;
                    row--;
                    if (new Position(column, row).positionOnBoard()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            column++;
            row--;
        }
        column = this.getPosition().getColumn();
        column++;
        row = this.getPosition().getRow();
        row++;
        while (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getFigure() != null) {
                if (!(board.getCell(new Position(column, row)).getFigure() instanceof King)
                        || board.getCell(new Position(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    column++;
                    row++;
                    if (new Position(column, row).positionOnBoard()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            column++;
            row++;
        }
        column = this.getPosition().getColumn();
        column--;
        row = this.getPosition().getRow();
        row--;
        while (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getFigure() != null) {
                if (!(board.getCell(new Position(column, row)).getFigure() instanceof King)
                        || board.getCell(new Position(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    column--;
                    row--;
                    if (new Position(column, row).positionOnBoard()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            column--;
            row--;
        }
        return correctPos;
    }

}
