package greatchess.board;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {

    private final char column;
    private final int row;

    public Position(char column, int row) {
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean positionOnBoard() {
        return this.getRow() <= 10 && this.getRow() >= 1 && this.getColumn() >= 'A' && this.getColumn() <= 'J';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position cellPosition = (Position) o;
        return column == cellPosition.column && row == cellPosition.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return (this.column) + Integer.toString(this.row);
    }
}
