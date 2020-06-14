package com.pack.main;

import Connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    static Connection connection = null;
    static ResultSet rs;

    public void initialize(){
        //CREATE DATABASE CONNECTION
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();


        //TESTING PURPOSE
        try{
            rs = connection.createStatement().executeQuery("SELECT * from deckmodels.material");
            while (rs.next()) {
                System.out.println(rs.getInt("Product_ID") + "  " + rs.getString("Material_Name") + " " + rs.getFloat("Unit_Price"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////

        custName.setText(newCustomer.getName());
        custPhoneNum.setText(newCustomer.getPhoneNum());
        custAddress.setText(newCustomer.getAddress());

        //FORMAT TO TWO DECIMAL PLACES
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
