package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Writer writer = new Writer();

    VBox vBox = new VBox();   // панель для текстового окна и панели с кнопками
    vBox.setSpacing(20);
    TextArea textArea = new TextArea();
    textArea.setWrapText(true);
    textArea.setEditable(false);
    textArea.setPrefColumnCount(10);
    textArea.setPrefRowCount(10);

    HBox buttonBox = new HBox();    // панель с кнопками
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.setSpacing(20);

    Button startButton = new Button("Начать");
    startButton.setOnAction(actionEvent -> {
      writer.startThread("First thread", 1, true);
      writer.startThread("Second thread", 2, false);
      startButton.setDisable(true);
    });

    Button clearButton = new Button("Очистить");
    clearButton.setOnAction(actionEvent -> {
      writer.setRes("");
      textArea.setText("");
    });

    Button viewButton = new Button("Вывести");
    viewButton.setOnAction(actionEvent -> textArea.setText(writer.getRes()));

    buttonBox.getChildren().addAll(startButton, clearButton, viewButton);
    vBox.getChildren().addAll(textArea, buttonBox);

    stage.setScene(new Scene(vBox, 600, 200));
    stage.show();
  }
}

