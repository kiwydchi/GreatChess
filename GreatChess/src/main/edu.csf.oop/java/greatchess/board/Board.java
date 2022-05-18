package greatchess.board;

import greatchess.functional.Field;
import greatchess.piece.King;
import greatchess.piece.Pawn;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

public class Board {

    private static final int SIZE_BOARD = 10;
    private final Graph<Cell> graph;
    private static final Logger logger = LoggerFactory.getLogger(Board.class);
    private Field field;

    public Board() {
        graph = new Graph<>();
        for (int row = 1; row <= SIZE_BOARD; row++) {
            for (char col = 'A'; col <= 'J'; col++) {
                graph.addVertex(new Cell(new Position(col, row), null));
            }
        }
        logger.info("Доска проинициализированна успешно.");
    }

    public void refreshGraph() {
        try {
            for (Cell cell : graph) {
                if (cell.getFigure() == null) {
                    if (graph.allEdges(cell).size() > 0) {
                        graph.removeEdges(cell);
                    }
                } else {
                    if (graph.allEdges(cell).size() > 0) {
                        graph.removeEdges(cell);
                    }
                    for (Position cellPosition : cell.getFigure().moveFigureToAnotherPosition(this)) {
                        if (getCell(cellPosition).getFigure() != null) {
                            if (getCell(cellPosition).getFigure().getColor() != cell.getFigure().getColor()) {
                                graph.createEdgeFromV1ToV2(cell, getCell(cellPosition));
                            }
                        } else {
                            if (cell.getFigure() instanceof Pawn) {
                                if (cellPosition.getColumn() == cell.getFigure().getPosition().getColumn()) {
                                    graph.createEdgeFromV1ToV2(cell, getCell(cellPosition));
                                }
                            } else {
                                graph.createEdgeFromV1ToV2(cell, getCell(cellPosition));
                            }
                        }
                    }
                }
            }
            refreshGraphForKing(getKingCell(Color.BLACK));
            ArrayList<Piece> attackingPieces = kingAttackingFigures(getKingCell(Color.WHITE));
            if (attackingPieces.size() > 1) {
                for (Cell cell : graph) {
                    if (cell.getFigure().getColor() != Color.WHITE && !(cell.getFigure() instanceof King)) {
                        graph.removeEdges(cell);
                    }
                }
            }
            attackingPieces = kingAttackingFigures(getKingCell(Color.BLACK));
            if (attackingPieces.size() > 1) {
                for (Cell cell : graph) {
                    if (cell.getFigure().getColor() != Color.BLACK && !(cell.getFigure() instanceof King)) {
                        graph.removeEdges(cell);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cell canPawnTakeChange() {
        for (Cell cell : graph) {
            if (cell.getFigure() != null && cell.getFigure() instanceof Pawn) {
                if (cell.getPosition().getRow() == 1 && cell.getFigure().getColor() == Color.BLACK) {
                    return cell;
                }
                if (cell.getPosition().getRow() == 10 && cell.getFigure().getColor() == Color.WHITE) {
                    return cell;
                }
            }
        }
        return null;
    }

    public ArrayList<Piece> kingAttackingFigures(Cell kingCell) {
        ArrayList<Piece> attackingPieces = new ArrayList<>();
        for (Cell cell : graph) {
            if (graph.allEdges(cell).contains(kingCell)) {
                attackingPieces.add(cell.getFigure());
            }
        }
        return attackingPieces;
    }

    public void refreshGraphForKing(Cell current) {
        try {
            for (Cell cell : graph) {
                if (cell.getFigure() != null && cell.getFigure().getColor() != current.getFigure().getColor()) {
                    if (cell.getFigure() instanceof King) {
                        for (Position cellPosition : cell.getFigure().moveFigureToAnotherPosition(this)) {
                            if (current.getFigure().countingCorrectPosition(this).contains(this.getCell(cellPosition))) {
                                graph.removeEdge(current, this.getCell(cellPosition));
                            }
                        }
                    }
                    for (Position cellPosition : cell.getFigure().moveFigureToAnotherPosition(this)) {
                        if (pawnConditionToKing(cell, this.getCell(cellPosition), current)) {
                            if (current.getFigure().countingCorrectPosition(this).contains(this.getCell(cellPosition))) {
                                logger.info(cellPosition.toString());
                                graph.removeEdge(current, this.getCell(cellPosition));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private boolean pawnConditionToKing(Cell firstCell, Cell secondCell, Cell targetCell) {
        if (firstCell.getFigure() instanceof Pawn) {
            for (Cell help : targetCell.getFigure().countingCorrectPosition(this)) {
                if (!help.getPosition().equals(secondCell.getPosition())) {
                    continue;
                }
                char column = firstCell.getPosition().getColumn();
                column++;
                char column2 = firstCell.getPosition().getColumn();
                column2--;
                int row = firstCell.getFigure().getColor() == Color.WHITE ? firstCell.getPosition().getRow() + 1 : firstCell.getPosition().getRow() - 1;
                if (secondCell.getPosition().getRow() == row && (secondCell.getPosition().getColumn() == column2
                        || secondCell.getPosition().getColumn() == column)) {
                    return true;
                }
                return firstCell.getPosition().getColumn() != secondCell.getPosition().getColumn();
            }
        }
        return true;
    }

    public Cell getKingCell(Color color) {
        for (Cell cell : graph) {
            if (cell.getFigure() != null) {
                if (cell.getFigure() instanceof King && cell.getFigure().getColor() == color) {
                    return cell;
                }
            }
        }
        return null;
    }

    public Cell getCell(Position cellPosition) {
        for (Cell cell : graph) {
            if (cell.getPosition().equals(cellPosition)) {
                return cell;
            }
        }
        return null;
    }

    public int getSizeBoard() {
        return SIZE_BOARD;
    }

    public Graph<Cell> getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return "Board{" +
                "graph=" + graph.toString() +
                '}';
    }
}
