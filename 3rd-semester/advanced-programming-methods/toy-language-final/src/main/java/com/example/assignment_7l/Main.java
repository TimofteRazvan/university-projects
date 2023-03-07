package com.example.assignment_7l;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader programSelectorLoader = new FXMLLoader();
        // SETTER FOR PROGRAM CHOOSER
        programSelectorLoader.setLocation(Main.class.getResource("ProgramChooserController.fxml"));
        Parent programSelectorRoot = programSelectorLoader.load();
        Scene programSelectorScene = new Scene(programSelectorRoot, 800, 350);
        // SETTER FOR PROGRAM CHOOSER CONTROLLER
        ProgramChooserController programChooserController = programSelectorLoader.getController();
        primaryStage.setTitle("PROGRAM SELECTOR");
        primaryStage.setScene(programSelectorScene);
        primaryStage.show();

        FXMLLoader programExecutorLoader = new FXMLLoader();
        // SETTER FOR PROGRAM EXECUTOR
        programExecutorLoader.setLocation(Main.class.getResource("ProgramExecutorController.fxml"));
        Parent programExecutorRoot = programExecutorLoader.load();
        Scene programExecutorScene = new Scene(programExecutorRoot, 700, 500);
        // SETTER FOR PROGRAM EXECUTOR CONTROLLER
        ProgramExecutorController programExecutorController = programExecutorLoader.getController();
        programChooserController.setProgramExecutorController(programExecutorController);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("TOY LANGUAGE INTERPRETER");
        secondaryStage.setScene(programExecutorScene);
        secondaryStage.show();
    }
}
