package com.pack.main;

import com.pack.connectivity.ConnectionClass;
import com.pack.functions.Constants;
import com.pack.functions.UsefulFunctions;
import com.pack.objects.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Scanner;

import static com.pack.functions.UsefulFunctions.populateObservableList;
import static com.pack.main.CustomerDetailsController.newCustomer;
import static com.pack.main.DeckDetailsController.newDeck;

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



    public static Connection connection = null;
    public static ResultSet rs;
    private static ObservableList<Product> obsMaterialsList = FXCollections.observableArrayList();
    DecimalFormat df = new DecimalFormat("#.##");

    public void initialize(){
        //CREATE DATABASE CONNECTION
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();

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
        deckDetails.setText(df.format(newDeck.getLength()) + " ft. x " + df.format(newDeck.getBreadth()) + " ft." );
        try {
            File file =
                    new File("userDetails.txt");
            Scanner sc = new Scanner(file);
            userName.setText(sc.nextLine());
            userPhoneNum.setText(sc.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
