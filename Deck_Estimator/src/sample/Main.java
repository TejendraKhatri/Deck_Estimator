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
        String stageTitle = "User Details";
        Parent root;
        File myObj  = new File("userDetails.txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
            startPage = "MenuOptions.fxml";
            stageTitle = "Menu Options";
        }
        root = FXMLLoader.load(getClass().getResource(startPage));
        primaryStage.setTitle(stageTitle);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);

        //To delete the file for now
<<<<<<< HEAD
        //Delete this later
        ////////////////////////////////
//        File myObj2 = new File("customerDetails.txt");
//        File myObj1 = new File("userDetails.txt");
//        if (myObj2.delete()) {
//            System.out.println("Deleted the file: " + myObj2.getName());
//        }
//        if (myObj1.delete()) {
//            System.out.println("Deleted the file: " + myObj1.getName());
//        }
//        else {
//            System.out.println("Failed to delete the file.");
//        }
        ///////////////////////////////
=======
        //Delete this later or not
        //////////////////////////////////
        File myObj2 = new File("customerDetails.txt");
        File myObj1 = new File("userDetails.txt");
        if (myObj2.delete()) {
            System.out.println("Deleted the file: " + myObj2.getName());
        }
        if (myObj1.delete()) {
            System.out.println("Deleted the file: " + myObj1.getName());
        }
        else {
            System.out.println("Failed to delete the file.");
        }
        /////////////////////////////////
>>>>>>> 45af71da0f247c15bf3e3744d8e4f2ce6efa61cd
    }
}
