package com.pack.main;

import com.pack.functions.UsefulFunctions;
import com.pack.objects.Customer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerDetailsController {
    @FXML
    TextField customerName;
    @FXML
    TextField customerPhone;
    @FXML
    Button btnContinue;
    @FXML
    TextField customerAddress;
    @FXML
    Button backBtn;

    public static Customer newCustomer;

    public void initialize(){
        btnContinue.setDisable(true);
        customerName.requestFocus();
        customerName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnContinue.setDisable(customerPhone.getText().equals("") || customerName.getText().equals("")
                        || customerAddress.getText().equals(""));
            }
        });
        customerAddress.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnContinue.setDisable(customerPhone.getText().equals("") || customerName.getText().equals("")
                        || customerAddress.getText().equals(""));
            }
        });
        customerPhone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}?")) {
                    customerPhone.setText(oldValue);
                }
                btnContinue.setDisable(customerPhone.getText().equals("") || customerName.getText().equals("")
                        || customerAddress.getText().equals(""));
            }
        });
    }

    @FXML
    private void btnClickedAction(ActionEvent event){
        if(event.getSource() == btnContinue){
            newCustomer = new Customer(customerName.getText(), customerPhone.getText(),customerAddress.getText());
            UsefulFunctions.addCustomerToDatabase(newCustomer.getName(),newCustomer.getPhoneNum(),newCustomer.getAddress());
            try {
                Stage stage;
                Parent root;
                stage = (Stage) btnContinue.getScene().getWindow();
                FXMLLoader myLoader =
                        new FXMLLoader(getClass().getResource("DeckDetails.fxml"));
                root = myLoader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Deck Details");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(event.getSource() == backBtn){
            Stage stage;
            stage = (Stage) btnContinue.getScene().getWindow();
            stage.close();
        }
    }
}
