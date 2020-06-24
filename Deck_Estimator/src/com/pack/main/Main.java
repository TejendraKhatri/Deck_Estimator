package com.pack.main;

import com.pack.connectivity.ConnectionClass;
import com.pack.functions.UsefulFunctions;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //CREATE DATABASE CONNECTION
        ConnectionClass connectionClass = new ConnectionClass();
        ConnectionClass.connection = connectionClass.getConnection();

        String startPage = "sampleSplash.fxml";
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root;
        root = FXMLLoader.load(getClass().getResource(startPage));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);
        ConnectionClass.closeConnection();
        Platform.exit();
        System.exit(0);
    }
}
