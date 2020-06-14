package com.pack.main;

import com.pack.connectivity.ConnectionClass;
import com.pack.functions.UsefulFunctions;
import com.pack.objects.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;

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
    Label totalAmt;
    @FXML
    Label subTotal;
    @FXML
    Label taxAmt;
    @FXML
    Label userName;
    @FXML
    Label userPhoneNum;
    @FXML
    TextField discount;
    @FXML
    TextField surcharge;



    public static Connection connection = null;
    public static ResultSet rs;
    private static ObservableList<Product> obsMaterialsList = FXCollections.observableArrayList();

    public void initialize(){
        //CREATE DATABASE CONNECTION
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();

        populateObservableList(newDeck.getLength(),newDeck.getBreadth(), newDeck.getHeight());

        populateTableView();

        populateOtherDetails();
    }

    private void populateOtherDetails() {
        custName.setText(newCustomer.getName());
        custPhoneNum.setText(newCustomer.getPhoneNum());
        custAddress.setText(newCustomer.getAddress());

        //FORMAT TO TWO DECIMAL PLACES
        DecimalFormat df = new DecimalFormat("#.##");
        deckDetails.setText(df.format(newDeck.getLength()) + " ft. x " + df.format(newDeck.getBreadth()) + " ft." );

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
