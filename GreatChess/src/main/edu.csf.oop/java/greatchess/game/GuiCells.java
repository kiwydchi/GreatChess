package greatchess.game;

import greatchess.board.Position;
import greatchess.functional.Field;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import greatchess.board.Cell;
import greatchess.board.Piece;

import java.util.LinkedList;

public class GuiCells extends Label {

    private final Position cellPosition;

    public GuiCells(Position cellPosition, Image image) {
        this.cellPosition = cellPosition;
        setColorToCell();
        setImage(image);
        setMinSize(80, 80);
        setMaxSize(80, 80);
    }

    public void setImage(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        this.setGraphic(imageView);
    }

    public Position getPosition() {
        return cellPosition;
    }

    public void cellsFunctional(Field field) {
        setOnDragDetected(mouseEvent -> shapeDraggingFound(mouseEvent, field));
        setOnDragOver(this::draggingFigure);
        setOnDragDropped(dragEvent -> thePositionWhenDraggingTheShape(dragEvent, field));
        setOnDragDone(dragEvent -> dragged(dragEvent, field));
        setOnMouseEntered(e -> MouseOnFigure(field));
        setOnMouseExited(e -> mouseOnExited(field));
    }

    private void MouseOnFigure(Field field) {
        Piece piece = field.getPiece(cellPosition);
        if (piece != null && piece.getColor() == field.getCurr()) {
            for (GuiCells gc : field.getCells()) {
                for (Cell cell : piece.countingCorrectPosition(field.getBoard())) {
                    if (cell.getPosition().equals(gc.getPosition())) {
                        if (cell.getFigure() != null) {
                            break;
                        }
                    }
                }
            }
        }
    }

    private void mouseOnExited(Field field) {
        Piece piece = field.getPiece(cellPosition);
        if (piece != null) {
            LinkedList<Cell> helpLink = piece.countingCorrectPosition(field.getBoard());
            for (GuiCells gc : field.getCells()) {
                for (Cell cell : helpLink) {
                    if (cell.getPosition().equals(gc.getPosition())) {
                        gc.setColorToCell();
                    }
                }
            }
        }
    }

    private void shapeDraggingFound(MouseEvent e, Field field) {
        Piece piece = field.getPiece(cellPosition);
        if (piece != null && piece.getColor() == field.getCurr()) {
            LinkedList<Cell> helpLink = piece.countingCorrectPosition(field.getBoard());
            if (helpLink.size() > 0) {
                Dragboard db = startDragAndDrop(TransferMode.MOVE);
                db.setDragView(piece.getImage());
                ClipboardContent content = new ClipboardContent();
                content.put(Piece.CHESS_PIECE, piece);
                db.setContent(content);
                for (GuiCells gc : field.getCells()) {
                    for (Cell cell : helpLink) {
                        if (cell.getPosition().equals(gc.getPosition())) {
                        }
                    }
                }
            }
        }
        e.consume();
    }

    private void draggingFigure(DragEvent e) {
        if (e.getDragboard().hasContent(Piece.CHESS_PIECE)) {
            e.acceptTransferModes(TransferMode.MOVE);
        }
        e.consume();
    }

    private void thePositionWhenDraggingTheShape(DragEvent e, Field field) {
        Dragboard db = e.getDragboard();
        if (db.hasContent(Piece.CHESS_PIECE)) {
            Piece piece = (Piece) db.getContent(Piece.CHESS_PIECE);
            piece = field.getPiece(piece.getPosition());
            LinkedList<Cell> helpLink = piece.countingCorrectPosition(field.getBoard());
            if (helpLink.size() > 0 && piece.countingCorrectPosition(field.getBoard()).contains(field.getBoard().getCell(this.cellPosition))) {
                setColorToCell();
                for (Cell cell : helpLink) {
                    if (cell.getPosition().equals(this.cellPosition)) {
                        setImage(piece.getImage());
                        for (GuiCells gc : field.getCells()) {
                            if (gc.cellPosition.equals(piece.getPosition())) {
                                gc.setGraphic(null);
                            }
                        }
                    }
                    if (cell.getPosition().equals(this.getPosition())) {
                        piece.move(field.getBoard(), cell);
                    }
                }
                field.gameTest();
            }
        }
        e.consume();
    }

    private void dragged(DragEvent e, Field field) {
        Dragboard db = e.getDragboard();
        if (db.hasContent(Piece.CHESS_PIECE)) {
            Piece piece = (Piece) db.getContent(Piece.CHESS_PIECE);
            piece.setPosition(field.getBoard().getCell(piece.getPosition()).getPosition());
            LinkedList<Cell> helpLink = piece.countingCorrectPosition(field.getBoard());
            for (GuiCells gc : field.getCells()) {
                for (Cell cell : helpLink) {
                    if (cell.getPosition().equals(gc.getPosition())) {
                        gc.setColorToCell();
                    }
                }
            }
        }
        e.consume();
    }

    private void setColorToCell() {
        if (getColor() == Color.WHITE) {
            setStyle("-fx-background-color: rgb(225, 220, 215);");
        } else {
            setStyle("-fx-background-color: rgb(95, 90, 85);");
        }
    }

    private Color getColor() {
        if ((cellPosition.getRow() + cellPosition.getColumn()) % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
}