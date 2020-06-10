package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextField userNameLabel;
    @FXML
    TextField userPhoneLabel;
    @FXML
    Button btnContinue;

    public void initialize(){
        btnContinue.setDisable(true);
        userNameLabel.requestFocus();
        userNameLabel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!(userPhoneLabel.getText().equals("") || userNameLabel.getText().equals("")) ){
                    btnContinue.setDisable(false);
                }
                else btnContinue.setDisable(true);
            }
        });
        userPhoneLabel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}?")) {
                    userPhoneLabel.setText(oldValue);
                }
                if (!(userPhoneLabel.getText().equals("") || userNameLabel.getText().equals(""))){
                    btnContinue.setDisable(false);
                }
                else btnContinue.setDisable(true);
            }
        });
    }

    @FXML
    private void btnClickedAction(ActionEvent event){
        if(event.getSource() == btnContinue){
            System.out.println("hey there delilah");
        }
    }

}
