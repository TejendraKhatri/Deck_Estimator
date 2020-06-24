package com.pack.main;

import com.pack.functions.UsefulFunctions;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SplashController {
    @FXML
    private StackPane rootPane;
    static Stage stage = new Stage();

    public class SampleSplashScreen extends Thread {
        public void run(){
            try {
                Thread.sleep(4000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String startPage = "MenuOptions.fxml";
                        String stageTitle = "Menu Options";
                        Parent root = null;
                        if (UsefulFunctions.userExists()) {
                        System.out.println("User Exists");
                        } else {
                        System.out.println("User not exists");
                        startPage = "UserDetails.fxml";
                        stageTitle = "User Details";
                        }
                        try {
                            root = FXMLLoader.load(getClass().getResource(startPage));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setTitle(stageTitle);
                        stage.setScene(scene);
                        stage.show();
                        rootPane.getScene().getWindow().hide();
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void initialize() {
        new SampleSplashScreen().start();
    }

}
