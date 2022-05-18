package greatchess.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import greatchess.board.Board;
import greatchess.board.Cell;
import greatchess.piece.*;
import javafx.scene.paint.Color;

import java.io.IOException;

public class Load extends JsonDeserializer<Board> {

    @Override
    public Board deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Board board = new Board();
        TreeNode treeNode = jsonParser.readValueAsTree();
        for (Cell cell : board.getGraph()) {
            TextNode str = (TextNode) treeNode.get(cell.getPosition().toString());
            if (!str.textValue().equals("null")) {
                Color color = null;
                TextNode stringColor = (TextNode) treeNode.get(cell.getPosition().toString() + str.textValue());
                if (stringColor.textValue().equals("Black")) {
                    color = Color.BLACK;
                } else if (stringColor.textValue().equals("White")){
                    color = Color.WHITE;
                }
                if (str.textValue().equals("P")) {
                    cell.setFigure(new Pawn(color, cell.getPosition()));
                }
                if (str.textValue().equals("K")) {
                    cell.setFigure(new King(color, cell.getPosition()));
                }
                if (str.textValue().equals("Kn")) {
                    cell.setFigure(new Knight(color, cell.getPosition()));
                }
                if (str.textValue().equals("B")) {
                    cell.setFigure(new Bishop(color, cell.getPosition()));
                }
                if (str.textValue().equals("Q")) {
                    cell.setFigure(new Queen(color, cell.getPosition()));
                }
                if (str.textValue().equals("R")) {
                    cell.setFigure(new Rook(color, cell.getPosition()));
                }
                if (str.textValue().equals("V")) {
                    cell.setFigure(new Vizier(color, cell.getPosition()));
                }
                if (str.textValue().equals("WM")) {
                    cell.setFigure(new WarMachine(color, cell.getPosition()));
                }
                if (str.textValue().equals("G")) {
                    cell.setFigure(new Giraffe(color, cell.getPosition()));
                }
            }
        }
        return board;
    }
}
