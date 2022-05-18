package greatchess.piece;

import greatchess.board.Position;
import greatchess.board.Piece;
import greatchess.board.Board;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(Color color, Position pos) {
        super(color, pos, "Q");
    }

    @Override
    public ArrayList<Position> moveFigureToAnotherPosition(Board board) {
        ArrayList<Position> correctPos = new ArrayList<>();
        char column = this.getPosition().getColumn();
        column--;
        int row = this.getPosition().getRow();
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
        column = this.getPosition().getColumn();
        row = this.getPosition().getRow();
        row++;
        while (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getFigure() != null) {
                if (!(board.getCell(new Position(column, row)).getFigure() instanceof King)
                        || board.getCell(new Position(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    row++;
                    if (new Position(column, row).positionOnBoard()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            row++;
        }
        row = this.getPosition().getRow();
        row--;
        while (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getFigure() != null) {
                if (!(board.getCell(new Position(column, row)).getFigure() instanceof King)
                        || board.getCell(new Position(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    row--;
                    if (new Position(column, row).positionOnBoard()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            row--;
        }
        row = this.getPosition().getRow();
        column++;
        while (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getFigure() != null) {
                if (!(board.getCell(new Position(column, row)).getFigure() instanceof King)
                        || board.getCell(new Position(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    column++;
                    if (new Position(column, row).positionOnBoard()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            column++;
        }
        column = this.getPosition().getColumn();
        column--;
        while (new Position(column, row).positionOnBoard()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getFigure() != null) {
                if (!(board.getCell(new Position(column, row)).getFigure() instanceof King)
                        || board.getCell(new Position(column, row)).getFigure().getColor() == this.getColor()) {
                    break;
                } else {
                    column--;
                    if (new Position(column, row).positionOnBoard()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            column--;
        }
        return correctPos;
    }
}
