package com.pack.main;

import com.pack.connectivity.ConnectionClass;
import com.pack.functions.UsefulFunctions;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //CREATE DATABASE CONNECTION
        ConnectionClass connectionClass = new ConnectionClass();
        ConnectionClass.connection = connectionClass.getConnection();
        String startPage = "MenuOptions.fxml";
        String stageTitle = "Menu Options";
        Parent root;
        if (UsefulFunctions.userExists()) {
            System.out.println("User Exists");
        } else {
            System.out.println("User not exists");
            startPage = "UserDetails.fxml";
            stageTitle = "User Details";
        }
        root = FXMLLoader.load(getClass().getResource(startPage));
        primaryStage.setTitle(stageTitle);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);
        ConnectionClass.closeConnection();
        Platform.exit();
    }
}
