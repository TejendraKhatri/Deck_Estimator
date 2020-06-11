package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String startPage = "UserDetails.fxml";
        Parent root;
//        File myObj  = new File("userDetails.txt");
//        if (myObj.createNewFile()) {
//            System.out.println("File created: " + myObj.getName());
//        } else {
//            System.out.println("File already exists.");
//            startPage = "CustomerDetails.fxml";
//        }
        root = FXMLLoader.load(getClass().getResource(startPage));
        primaryStage.setTitle("User Details");
        primaryStage.setScene(new Scene(root, 500, 230));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
