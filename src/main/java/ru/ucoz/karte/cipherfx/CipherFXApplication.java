package ru.ucoz.karte.cipherfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class CipherFXApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CipherFXApplication.class.getResource("cipherfx-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 520);
        stage.getIcons().add(new Image("file:icon.png"));
        stage.setResizable(true);
        stage.setTitle("CipherFX");
        stage.setScene(scene);
        stage.show();
        stage.maxHeightProperty().bind(stage.heightProperty());
        stage.minHeightProperty().bind(stage.heightProperty());

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            CipherFXController.controller.inputTextArea.setPrefWidth(scene.getWidth()-42);
            CipherFXController.controller.keyTextArea.setPrefWidth(scene.getWidth()-42);
            CipherFXController.controller.outputTextArea.setPrefWidth(scene.getWidth()-42);
            CipherFXController.controller.openInputTextFileButton.setLayoutX(
                    scene.getWidth()-
                    CipherFXController.controller.openInputTextFileButton.getWidth()-22
            );
            CipherFXController.controller.openKeyTextFileButton.setLayoutX(
                    scene.getWidth()-
                    CipherFXController.controller.openKeyTextFileButton.getWidth()-22
            );
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            //CipherFXController.controller.smallPane.setLayoutY(scene.getHeight()-75);
        });
    }

    public static void main(String[] args) {
        launch();
    }

}