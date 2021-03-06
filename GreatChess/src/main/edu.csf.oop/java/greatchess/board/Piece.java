package greatchess.board;

import javafx.scene.image.Image;
import javafx.scene.input.DataFormat;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class Piece implements Serializable {

    public transient static final DataFormat CHESS_PIECE = new DataFormat("chess.piece");
    private final transient static Map<String, Image> imageCache = new HashMap<>();
    private Position Position;
    private final String name;
    private final String imageFileName;
    private final transient Color color;

    protected Piece(Color color, Position position, String name) {
        this.color = color;
        this.Position = position;
        this.name = name;
        imageFileName = "File:images/" + (color.equals(Color.BLACK)
                ? "B"
                : "W") + name + ".png";
        if (!imageCache.containsKey(imageFileName)) {
            imageCache.put(imageFileName, new Image(imageFileName));
        }
    }

    public Image getImage() {
        return imageCache.get(imageFileName);
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return Position;
    }

    public String getName() {
        return name;
    }

    public void setPosition(Position position) {
        this.Position = position;
    }

    public void move(Board board, Cell cell) {
        board.getCell(getPosition()).setFigure(null);
        this.setPosition(cell.getPosition());
        cell.setFigure(this);
        board.refreshGraph();
    }

    public LinkedList<Cell> countingCorrectPosition(Board board) {
        for (Cell current : board.getGraph()) {
            if (current.getPosition() == this.getPosition()) {
                return board.getGraph().allEdges(current);
            }
        }
        return new LinkedList<>();
    }

    public abstract ArrayList<Position> moveFigureToAnotherPosition(Board board);
}
