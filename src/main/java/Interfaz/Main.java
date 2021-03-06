package Interfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Elecciones Paso 2019");
        primaryStage.setScene(new Scene(root, 900, 400));
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(900);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
