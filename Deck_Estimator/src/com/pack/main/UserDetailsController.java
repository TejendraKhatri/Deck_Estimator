package com.pack.main;

import com.pack.functions.UsefulFunctions;
import com.pack.objects.User;
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

import java.io.FileWriter;
import java.io.IOException;

public class UserDetailsController {
    @FXML
    TextField userName;
    @FXML
    TextField userPhone;
    @FXML
    Button btnContinue;

    public void initialize(){
        btnContinue.setDisable(true);
        userName.requestFocus();
        userName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!(userPhone.getText().equals("") || userName.getText().equals("")) ){
                    btnContinue.setDisable(false);
                }
                else btnContinue.setDisable(true);
            }
        });
        userPhone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}?")) {
                    userPhone.setText(oldValue);
                }
                if (!(userPhone.getText().equals("") || userName.getText().equals(""))){
                    btnContinue.setDisable(false);
                }
                else btnContinue.setDisable(true);
            }
        });
    }

    @FXML
    private void btnClickedAction(ActionEvent event){
        if(event.getSource() == btnContinue){
            User newuser = new User(userName.getText(), userPhone.getText());
            UsefulFunctions.addUserToDatabase(newuser.getName(),newuser.getPhoneNum());
            try {
                FileWriter myWriter = new FileWriter("userDetails.txt");
                myWriter.write(newuser.getName() + "\n" + newuser.getPhoneNum());
                myWriter.close();
                Stage stage;
                Parent root;
                stage = (Stage) btnContinue.getScene().getWindow();
                FXMLLoader myLoader =
                        new FXMLLoader(getClass().getResource("MenuOptions.fxml"));
                root = myLoader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Menu Options");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    }

}
