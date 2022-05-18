package greatchess.game;

import greatchess.board.Position;
import greatchess.functional.Field;

public class GUI {

    public GuiCells setGraphicCellAnimation(Field field, char col, int row) {
        GuiCells cell = new GuiCells(new Position(col, row), field.getBoard().getCell(new Position(col, row)).getFigure() == null
                ? null
                :  field.getBoard().getCell(new Position(col, row)).getFigure().getImage());
        cell.cellsFunctional(field);
        return cell;
    }
}
