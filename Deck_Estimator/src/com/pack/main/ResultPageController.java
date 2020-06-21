package com.pack.main;

import com.pack.connectivity.ConnectionClass;
import com.pack.functions.Constants;
import com.pack.functions.UsefulFunctions;
import com.pack.objects.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import static com.pack.functions.UsefulFunctions.populateObservableList;
import static com.pack.main.CustomerDetailsController.newCustomer;
import static com.pack.main.DeckDetailsController.newDeck;
import static com.pack.main.DeckDetailsController.newStairs;

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
    @FXML
    Label totalAmtLabel;
    @FXML
    Label subTotalLabel;
    @FXML
    Label taxAmtLabel;
    @FXML
    Label userName;
    @FXML
    Label userPhoneNum;
    @FXML
    TextField discountField;
    @FXML
    TextField surchargeField;
    @FXML
    MenuItem redoMenu;
    @FXML
    MenuItem saveMenu;
    @FXML
    MenuItem exitMenu;
    @FXML
    MenuItem aboutMenu;

    public static ResultSet rs;
    static ObservableList<Product> obsMaterialsList = FXCollections.observableArrayList();
    private DecimalFormat df = new DecimalFormat("#.##");

    public void initialize(){

        discountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    surchargeField.setDisable(false);
                }
                else surchargeField.setDisable(true);
                if (!newValue.matches("\\d{0,10}([\\.]\\d{0,10})?")) {
                    discountField.setText(oldValue);
                }
                updateTotalBalance();
            }
        });

        surchargeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    discountField.setDisable(false);
                }
                else discountField.setDisable(true);
                if (!newValue.matches("\\d{0,10}([\\.]\\d{0,10})?")) {
                    surchargeField.setText(oldValue);
                }
                updateTotalBalance();
            }
        });


        populateObservableList(newDeck.getLength(),newDeck.getBreadth(), newDeck.getHeight());

        populateTableView();

        populateOtherDetails();

        updateTotalBalance();
    }

    @FXML
    private void handleBtnAction(ActionEvent event){
//        if(event.getSource() == newQuoteBtn){
//            newStairs = null;
//            newDeck = null;
//            try {
//                Stage stage;
//                Parent root;
//                stage = (Stage) newQuoteBtn.getScene().getWindow();
//                FXMLLoader myLoader =
//                        new FXMLLoader(getClass().getResource("CustomerDetails.fxml"));
//                root = myLoader.load();
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.setTitle("Customer Details");
//                stage.show();
//                UsefulFunctions.materialList.clear();
//                obsMaterialsList.clear();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @FXML
    private void handleMenuAction(ActionEvent event){
        if(event.getSource() == exitMenu){
            Stage stage = (Stage) subTotalLabel.getScene().getWindow();
            stage.close();
        }
        else if(event.getSource() == aboutMenu){
            try {
                Stage stage = new Stage();
                Parent root;
               // stage = (Stage) newQuoteBtn.getScene().getWindow();
                FXMLLoader myLoader =
                        new FXMLLoader(getClass().getResource("AboutPage.fxml"));
                root = myLoader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("About Deck Estimator");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateTotalBalance(){
        //calculate subTotal
        double subtotalAmt = 0;
        for(Product x: obsMaterialsList){
            subtotalAmt += x.getSubTotal();
        }
        subTotalLabel.setText(df.format(subtotalAmt));
        //calculate Tax
        double taxAmt = subtotalAmt * Constants.TAXRATE / 100;
        taxAmtLabel.setText(df.format(taxAmt));

        double discountAmt = 0;
        double surchargeAmt = 0;
        if(!(discountField.getText().isEmpty())){
            discountAmt = Double.valueOf(discountField.getText());
        }
        if(!(surchargeField.getText().isEmpty())){
            surchargeAmt = Double.valueOf(surchargeField.getText());
        }

        double totalAmt = subtotalAmt + taxAmt - discountAmt + surchargeAmt;
        totalAmtLabel.setText(df.format(totalAmt));
    }

    private void populateOtherDetails() {
        custName.setText(newCustomer.getName());
        custPhoneNum.setText(newCustomer.getPhoneNum());
        custAddress.setText(newCustomer.getAddress());

        //FORMAT TO TWO DECIMAL PLACES
        deckDetails.setText(df.format(newDeck.getLength()) + " ft. x " + df.format(newDeck.getBreadth()) + " ft.");
        try {
            rs = ConnectionClass.connection.createStatement().executeQuery("SELECT * FROM user");
            rs.next();
            userName.setText(rs.getString("User_Name"));
            userPhoneNum.setText(rs.getString("User_PhoneNumber"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void populateTableView(){
        obsMaterialsList.addAll(UsefulFunctions.materialList);

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        colSubtotal.setCellFactory(tc->new TableCell<Product,Double>(){
            protected void updateItem(Double subTotal,boolean empty){
                super.updateItem(subTotal, empty);
                if(empty) {
                    setText(null);
                } else {
                    setText(String.format(" $%.2f", subTotal.floatValue()));
                }
            }
        });
        matTable.setItems(obsMaterialsList);
    }

}
