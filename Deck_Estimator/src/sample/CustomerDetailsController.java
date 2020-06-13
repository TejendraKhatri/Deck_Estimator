package sample;

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

import java.io.FileWriter;
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

    public void initialize(){
        btnContinue.setDisable(true);
        customerName.requestFocus();
        customerName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!(customerPhone.getText().equals("") || customerName.getText().equals("")
                      || customerAddress.getText().equals("")) ){
                    btnContinue.setDisable(false);
                }
                else btnContinue.setDisable(true);
            }
        });
        customerAddress.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!(customerPhone.getText().equals("") || customerName.getText().equals("")
                        || customerAddress.getText().equals("")) ){
                    btnContinue.setDisable(false);
                }
                else btnContinue.setDisable(true);
            }
        });
        customerPhone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}?")) {
                    customerPhone.setText(oldValue);
                }
                if (!(customerPhone.getText().equals("") || customerName.getText().equals("")
                        || customerAddress.getText().equals(""))){
                    btnContinue.setDisable(false);
                }
                else btnContinue.setDisable(true);
            }
        });
    }

    @FXML
    private void btnClickedAction(ActionEvent event){
        if(event.getSource() == btnContinue){
            Customer newCust = new Customer(customerName.getText(), customerPhone.getText(),customerAddress.getText());
            try {
                FileWriter myWriter = new FileWriter("customerDetails.txt");
                myWriter.write(newCust.getName() + "\n" + newCust.getPhoneNum() + "\n" + newCust.getAddress());
                myWriter.close();
                Stage stage;
                Parent root;
                stage = (Stage) btnContinue.getScene().getWindow();
                FXMLLoader myLoader =
                        new FXMLLoader(getClass().getResource( "DeckDetails.fxml" ));
                root = myLoader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Deck Details");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
