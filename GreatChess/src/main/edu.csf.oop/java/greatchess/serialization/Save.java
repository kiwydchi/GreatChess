package greatchess.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import greatchess.board.Board;
import greatchess.board.Cell;
import javafx.scene.paint.Color;

import java.io.IOException;

public class Save extends JsonSerializer<Board> {

    @Override
    public void serialize(Board board, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        for (Cell cell : board.getGraph()) {
            jsonGenerator.writeStringField(cell.getPosition().toString(), (cell.getFigure() != null ? cell.getFigure().getName() : "null"));
            String color;
            if (cell.getFigure() != null) {
                color = cell.getFigure().getColor() == Color.BLACK ? "Black" : "White";
            } else {
                color = "null";
            }
            jsonGenerator.writeStringField(cell.getPosition().toString() + (cell.getFigure() != null ? cell.getFigure().getName() : "null"), color);
        }
        jsonGenerator.writeEndObject();
    }
}