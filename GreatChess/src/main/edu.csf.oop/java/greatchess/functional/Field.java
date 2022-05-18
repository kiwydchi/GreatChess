package greatchess.functional;

import greatchess.board.Position;
import greatchess.board.Piece;
import greatchess.piece.*;
import greatchess.game.GuiCells;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import greatchess.board.Board;
import greatchess.board.Cell;
import greatchess.game.GUI;

import java.util.ArrayList;

public class Field {

    private final GridPane gridPane;
    private Board board;
    private static final Logger logger = LoggerFactory.getLogger(Field.class);
    private final ArrayList<GuiCells> cells;
    private Color curr = Color.WHITE;
    GUI GUI = new GUI();
    Match match = new Match();

    public Match getMatch() {
        return match;
    }

    public static Logger getLogger() {
        return logger;
    }

    public greatchess.game.GUI getGUI() {
        return GUI;
    }

    public void setCurr(Color curr) {
        this.curr = curr;
    }

    public void setGUI(greatchess.game.GUI GUI) {
        this.GUI = GUI;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Field() {
        board = new Board();
        gridPane = new GridPane();
        cells = new ArrayList<>();
        firstPieceInit();
        for (int row = 1; row <= board.getSizeBoard(); row++) {
            for (char col = 'A'; col <= 'J'; col++) {
                GuiCells cell = GUI.setGraphicCellAnimation(this, col, row);
                cells.add(cell);
                gridPane.add(cell, col, row);
            }
        }
        logger.info("Поле проинициализированно успешно.");
    }

    public void gameTest() {
        pawnReplace();
        curr = curr == Color.BLACK ? Color.WHITE : Color.BLACK;
        if (board.getKingCell(curr).getFigure().countingCorrectPosition(this.getBoard()).size() == 0) {
            for (Cell cell : board.getGraph()) {
                if (cell.getFigure() != null && cell.getFigure().countingCorrectPosition(board).size() > 0 && cell.getFigure().getColor() == curr) {
                    return;
                }
            }
            Match.finishGame(curr);
        }
    }

    public void pawnReplace() {
        Cell cell = board.canPawnTakeChange();
        if (cell != null) {
            this.getBoard().getCell(cell.getPosition()).setFigure(new Bishop(cell.getFigure().getColor(), cell.getPosition()));
            for (GuiCells gc : this.getCells()) {
                if (gc.getPosition().equals(cell.getPosition())) {
                    gc.setImage(this.getBoard().getCell(cell.getPosition()).getFigure().getImage());
                }
            }
        }
    }

    public void setBoard(Board board) {
        this.board = board;
        board.refreshGraph();
        cells.clear();
        for (int row = 1; row <= board.getSizeBoard(); row++) {
            for (char col = 'A'; col <= 'J'; col++) {
                GuiCells cell = GUI.setGraphicCellAnimation(this, col, row);
                cells.add(cell);
                gridPane.add(cell, col, row);
            }
        }
    }

    public void resetBoard() {
        cells.clear();
        for (Cell cell : this.board.getGraph()) {
            cell.setFigure(null);
        }
        firstPieceInit();
        for (int row = 1; row <= board.getSizeBoard(); row++) {
            for (char col = 'A'; col <= 'H'; col++) {
                GuiCells cell = GUI.setGraphicCellAnimation(this, col, row);
                cells.add(cell);
                gridPane.add(cell, col, row);
            }
        }
        logger.info("Game was restarted successfully");
    }

    public Color getCurr() {
        return curr;
    }

    public void setColor(Color color) {
        curr = color;
    }

    private void firstPieceInit() {
        for (Cell cell : board.getGraph()) {
            if (cell.getPosition().equals(new Position('A', 10)) || cell.getPosition().equals(new Position('J', 10)) ||
                    cell.getPosition().equals(new Position('A', 1)) || cell.getPosition().equals(new Position('J', 1))) {
                cell.setFigure(new Rook((cell.getPosition().equals(new Position('A', 10)) || cell.getPosition().equals(new Position('J', 10)))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPosition()));
            }
            if (cell.getPosition().equals(new Position('B', 10)) || cell.getPosition().equals(new Position('I', 10)) ||
                    cell.getPosition().equals(new Position('B', 1)) || cell.getPosition().equals(new Position('I', 1))) {
                cell.setFigure(new Knight((cell.getPosition().equals(new Position('B', 10)) || cell.getPosition().equals(new Position('I', 10)))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPosition()));
                if (cell.getPosition().getRow() == 1) {
                    char ch = cell.getPosition().getColumn();
                    ch++;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 3)));
                    ch -= 2;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 3)));
                } else {
                    char ch = cell.getPosition().getColumn();
                    ch++;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 6)));
                    ch -= 2;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 3)));
                }
            }
            if (cell.getPosition().equals(new Position('C', 10)) || cell.getPosition().equals(new Position('H', 10)) ||
                    cell.getPosition().equals(new Position('C', 1)) || cell.getPosition().equals(new Position('H', 1))) {
                cell.setFigure(new Bishop((cell.getPosition().equals(new Position('C', 10)) || cell.getPosition().equals(new Position('H', 10)))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPosition()));
            }
            if (cell.getPosition().equals(new Position('D', 10)) || cell.getPosition().equals(new Position('G', 1))) {
                cell.setFigure(new Queen(cell.getPosition().equals(new Position('D', 10))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPosition()));
            }
            if (cell.getPosition().equals(new Position('E', 10)) || cell.getPosition().equals(new Position('F', 1))) {
                cell.setFigure(new King(cell.getPosition().equals(new Position('E', 10))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPosition()));
            }
            if (cell.getPosition().equals(new Position('F', 10)) || cell.getPosition().equals(new Position('E', 1))) {
                cell.setFigure(new Giraffe(cell.getPosition().equals(new Position('F', 10))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPosition()));
            }
            if (cell.getPosition().equals(new Position('G', 10)) || cell.getPosition().equals(new Position('D', 1))) {
                cell.setFigure(new Vizier(cell.getPosition().equals(new Position('G', 10))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPosition()));
            }
            if (cell.getPosition().equals(new Position('E', 9)) || cell.getPosition().equals(new Position('E', 2)) ||
                    cell.getPosition().equals(new Position('F', 9)) || cell.getPosition().equals(new Position('F', 2))) {
                cell.setFigure(new WarMachine((cell.getPosition().equals(new Position('E', 9)) || cell.getPosition().equals(new Position('F', 9)))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPosition()));
            }
            if (((cell.getPosition().getRow() == 9 || cell.getPosition().getRow() == 2) && !(cell.getPosition().equals(new Position('E', 2)) || cell.getPosition().equals(new Position('E', 9)) ||
                    cell.getPosition().equals(new Position('F', 2)) || cell.getPosition().equals(new Position('F', 9)))) ||
                    (cell.getPosition().equals(new Position('E', 3)) || cell.getPosition().equals(new Position('F', 3))) || cell.getPosition().equals(new Position('E', 8))
                    || cell.getPosition().equals(new Position('F', 8))) {
                cell.setFigure(new Pawn(cell.getPosition().getRow() == 9 || cell.getPosition().getRow() == 8
                        ? Color.BLACK
                        : Color.WHITE, cell.getPosition()));

                char ch = cell.getPosition().getColumn();
                if (cell.getPosition().getRow() == 7) {
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 7 - 1)));
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 7 - 2)));
                } else {
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 2 + 1)));
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 2 + 2)));
                }
            }
            try {
                logger.info("Фигура " + cell.getFigure().getName() + " была размещена на клетке: " +  cell.getPosition().getColumn() + cell.getPosition().getRow());
            } catch (Exception e) {
                logger.info("Клетка " + cell.getPosition() +  " пуста");
            }
        }
    }

    public ArrayList<GuiCells> getCells() {
        return cells;
    }

    public Board getBoard() {
        return board;
    }

    public Piece getPiece(Position cellPosition) {
        return this.board.getCell(cellPosition).getFigure();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    @Override
    public String toString() {
        return "Field{" +
                ", board=" + board +
                '}';
    }
}
