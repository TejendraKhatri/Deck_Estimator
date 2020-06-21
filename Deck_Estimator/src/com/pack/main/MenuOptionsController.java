package com.pack.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void initialize(){
        openQuoteBtn.setDisable(true);
        deleteQuoteBtn.setDisable(true);
        changePriceBtn.setDisable(false);
        updateQuoteBtn.setDisable(true);
    }

    @FXML
    private void newQuoteBtnAction(ActionEvent event){
        if(event.getSource() == newQuoteBtn){
            try {
                Stage stage = new Stage();
                Parent root;
              //  stage = (Stage) newQuoteBtn.getScene().getWindow();
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

    }

    @FXML
    private void openQuoteBtnAction(ActionEvent event){

    }

    @FXML
    private void deleteQuoteBtnAction(ActionEvent event){

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




}
