package com.pack.main;

import com.pack.functions.UsefulFunctions;
import com.pack.objects.Customer;
import com.pack.objects.Deck;
import com.pack.objects.Stairs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.pack.main.DeckDetailsController.newDeck;
import static com.pack.main.DeckDetailsController.newStairs;
import static com.pack.main.ResultPageController.obsMaterialsList;

public class MenuOptionsController {
    @FXML
    Button newQuoteBtn;
    @FXML
    Button updateQuoteBtn;
    @FXML
    Button openQuoteBtn;
    @FXML
    Button deleteQuoteBtn;
    @FXML
    Button changePriceBtn;

    @FXML
    private void newQuoteBtnAction(ActionEvent event){
        if(event.getSource() == newQuoteBtn){
            newDeck = null;
            newStairs = null;
            UsefulFunctions.materialList.clear();
            obsMaterialsList.clear();
            try {
                Stage stage = new Stage();
                Parent root;
                FXMLLoader myLoader =
                        new FXMLLoader(getClass().getResource("CustomerDetails.fxml"));
                root = myLoader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Customer Details");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void updateQuoteBtnAction(ActionEvent event){
        if(!getDataFromTxt()) return;
        try{
            Stage stage = new Stage();
            Parent root;
            FXMLLoader myLoader =
                    new FXMLLoader(getClass().getResource("UpdateQuote.fxml"));
            root = myLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Update Quote");
            stage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void openQuoteBtnAction(ActionEvent event){
            if(!getDataFromTxt()) return;
            try{
                UsefulFunctions.materialList.clear();
                obsMaterialsList.clear();
                Stage stage = new Stage();
                Parent root;
                FXMLLoader myLoader =
                        new FXMLLoader(getClass().getResource("ResultPage.fxml"));
                root = myLoader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Estimate Details");
                stage.show();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

    @FXML
    private void deleteQuoteBtnAction(ActionEvent event){
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to delete");
        int userSelection = fileChooser.showOpenDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                fileChooser.getSelectedFile().delete();
                System.out.println("Successful deletion");
            }
        }
    }

    @FXML
    private void priceChangeBtnAction(ActionEvent event){
        if(event.getSource() == changePriceBtn){
            try {
                Stage stage;
                Parent root;
                stage = (Stage) changePriceBtn.getScene().getWindow();
                FXMLLoader myLoader =
                        new FXMLLoader(getClass().getResource("ChangePrice.fxml"));
                root = myLoader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Change Price");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean getDataFromTxt() {
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a quote to open");
        int userSelection = fileChooser.showOpenDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToRead = fileChooser.getSelectedFile();
            try {
                Scanner reader = new Scanner(fileToRead);
                String custName = reader.nextLine();
                String custPhn = reader.nextLine();
                String custAdd = reader.nextLine();
                int len = reader.nextInt();
                int width = reader.nextInt();
                int lenIn = reader.nextInt();
                int widIn = reader.nextInt();
                int hght = reader.nextInt();
                String dump = reader.nextLine();
                String temp = reader.nextLine();
                reader.close();
                CustomerDetailsController.newCustomer = new Customer(custName, custPhn, custAdd);
                newDeck = new Deck(len, lenIn, width, widIn, hght);
                if (temp.equals("END")) {
                    newStairs = null;
                } else {
                    newStairs = new Stairs(hght, Float.valueOf(temp));
                }
                return true;
            } catch (InputMismatchException e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Incorrect file format");
                a.showAndWait();
            } catch (IOException e) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Incorrect file format");
                a.showAndWait();
                e.printStackTrace();
            }
        }
        return false;
    }
}
