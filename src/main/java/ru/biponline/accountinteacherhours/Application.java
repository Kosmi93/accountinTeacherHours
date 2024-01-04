package ru.biponline.accountinteacherhours;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Stage mainStage;

    public static void main(String[] args) {
        launch();
    }
    public static Stage getStage() {
        return mainStage;
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Application.class.getResource("start-view.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        stage.setTitle("Нагрузка");
        stage.setScene(scene);
        stage.show();
        mainStage = stage;
    }


}