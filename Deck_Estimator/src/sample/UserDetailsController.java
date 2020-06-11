package sample;

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

import java.io.IOException;

public class UserDetailsController {
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
            User newuser = new User(userNameLabel.getText(), userPhoneLabel.getText());
            System.out.println(newuser.getName());
            try {
                Stage stage;
                Parent root;
                stage = (Stage) btnContinue.getScene().getWindow();
                FXMLLoader myLoader =
                        new FXMLLoader(getClass().getResource( "CustomerDetails.fxml" ));
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

}
