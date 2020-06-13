package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.text.DecimalFormat;

import static com.pack.functions.UsefulFunctions.populateObservableList;
import static sample.CustomerDetailsController.newCustomer;
import static sample.DeckDetailsController.newDeck;

public class ResultPageController {
    @FXML
    Label custName;
    @FXML
    Label custPhoneNum;
    @FXML
    Label custAddress;
    @FXML
    Label deckDetails;
    @FXML
    TableView matTable;
    @FXML
    TableColumn colName;
    @FXML
    TableColumn colQty;
    @FXML
    TableColumn colUnitPrice;
    @FXML
    TableColumn colSubtotal;


    public void initialize(){
        System.out.println(newDeck.toString());
        custName.setText(newCustomer.getName());
        custPhoneNum.setText(newCustomer.getPhoneNum());
        custAddress.setText(newCustomer.getAddress());
        DecimalFormat df = new DecimalFormat("#.##");
        deckDetails.setText(df.format(newDeck.getLength()) + " ft. x " + df.format(newDeck.getBreadth()) + " .ft" );
        populateObservableList();
        populateTableView();
        populateOtherDetails();
    }

    private void populateOtherDetails() {
    }

    private void populateTableView(){

    }

}
