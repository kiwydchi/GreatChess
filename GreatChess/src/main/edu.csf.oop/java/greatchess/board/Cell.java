package greatchess.board;

public class Cell {

    private final Position position;
    private Piece piece;

    public Cell(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getFigure() {
        return piece;
    }

    public void setFigure(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return position.toString() + ": " + (piece != null ? (piece.getName() + "; ") : "null; ");
    }
}
