package greatchess.functional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import greatchess.serialization.Load;
import greatchess.serialization.Save;
import javafx.application.Application;

import greatchess.board.Board;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Match extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Match.class);
    private Field field;
    private HBox gameStatus;
    private static Match match;
    private BorderPane pane;

    @Override
    public void start(Stage primaryStage) {
        match = this;
        pane = new BorderPane();
        field = new Field();
        createMenu();
        field.getGridPane().setAlignment(Pos.CENTER);
        pane.setCenter(field.getGridPane());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(pane, 800, 820));
        primaryStage.setTitle("Великие шахматы");
        primaryStage.show();
    }

    public void createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Меню");
        MenuItem newItem = new MenuItem("Новая игра");
        newItem.setOnAction(actionEvent -> {
            field.resetBoard();
        });
        MenuItem saveFileItem = new MenuItem("Сохранить");
        saveFileItem.setOnAction(actionEvent -> {
            FileChooser fileOpener = new FileChooser();
            fileOpener.setTitle("Choose file");
            fileOpener.setInitialDirectory(new File("src/main/resources/jsons"));
            fileOpener.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
            serialization(fileOpener.showSaveDialog(new Stage()));
        });
        MenuItem loadFileItem = new MenuItem("Загрузить");
        loadFileItem.setOnAction(actionEvent -> {
            FileChooser fileOpener = new FileChooser();
            fileOpener.setTitle("Choose file");
            fileOpener.setInitialDirectory(new File("src/main/resources/jsons"));
            fileOpener.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
            deserialization(fileOpener.showOpenDialog(new Stage()));
        });
        fileMenu.getItems().addAll(newItem, saveFileItem, loadFileItem);
        menuBar.getMenus().addAll(fileMenu);
        pane.setTop(menuBar);
    }

    public static void finishGame(Color color) {
        Stage stage = new Stage();
        Label text = new Label();
        text.setFont(new Font("Arial", 30));
        text.setTextFill(Color.RED);
        text.setText((color == Color.WHITE ? "ЧЁРНЫЕ " : "БЕЛЫЕ ") + "ВЫИГРАЛИ");
        text.setAlignment(Pos.CENTER);
        match.gameStatus = new HBox(text);
        match.gameStatus.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(match.gameStatus, 400, 200));
        stage.show();
    }

    public void serialization(File file) {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Board.class, new Save());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(simpleModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, field.getBoard());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialization(File file) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        mapper.configure(
                DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        simpleModule.addDeserializer(Board.class, new Load());
        mapper.registerModule(simpleModule);
        simpleModule.addSerializer(Board.class, new Save());
        mapper.registerModule(simpleModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            field.setBoard(mapper.readValue(file, Board.class));
            logger.info("Загрузка завершена успешно.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
