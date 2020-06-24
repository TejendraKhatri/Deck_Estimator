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

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import static com.pack.functions.UsefulFunctions.populateObservableList;
import static com.pack.main.CustomerDetailsController.newCustomer;
import static com.pack.main.DeckDetailsController.newDeck;
import static com.pack.main.DeckDetailsController.newStairs;
import static com.pack.main.MenuOptionsController.discount;
import static com.pack.main.MenuOptionsController.surcharge;

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
    private final DecimalFormat df = new DecimalFormat("#.##");

    public void initialize(){
        if(discount!= 0 ){
            discountField.setDisable(false);
            discountField.setText(String.valueOf(discount));
        }
        else if(surcharge!= 0 ){
            surchargeField.setDisable(false);
            surchargeField.setText(String.valueOf(surcharge));
        }

        discountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                surchargeField.setDisable(!newValue.equals(""));
                if (!newValue.matches("\\d{0,10}([\\.]\\d{0,2})?")) {
                    discountField.setText(oldValue);
                }
                updateTotalBalance();
            }
        });

        surchargeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                discountField.setDisable(!newValue.equals(""));
                if (!newValue.matches("\\d{0,10}([\\.]\\d{0,2})?")) {
                    surchargeField.setText(oldValue);
                }
                updateTotalBalance();
            }
        });

        updateWindow();
    }

    private void updateWindow(){
        discount = 0;
        surcharge = 0;
        populateObservableList(newDeck.getLength(),newDeck.getBreadth(), newDeck.getHeight());
        populateTableView();
        populateOtherDetails();
        updateTotalBalance();
    }
    
    @FXML
    private void handleMenuAction(ActionEvent event){
        if(event.getSource() == redoMenu){
            newDeck = null;
            newStairs = null;
            UsefulFunctions.materialList.clear();
            obsMaterialsList.clear();
            try {
                Stage stage;
                Parent root;
                stage = (Stage) matTable.getScene().getWindow();
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
        else if(event.getSource() == exitMenu){
            Stage stage = (Stage) subTotalLabel.getScene().getWindow();
            stage.close();
        }
        else if(event.getSource() == saveMenu){
            saveEstimate();
        }
        else if(event.getSource() == aboutMenu){
            try {
                Stage stage = new Stage();
                Parent root;
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

    public void saveEstimate(){
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                FileWriter myWriter = new FileWriter(fileToSave);
                myWriter.write(newCustomer.toString() + "\n");
                myWriter.write(newDeck.toString() + "\n");
                if(newStairs != null) {
                    myWriter.write(newStairs.toString() + "\n");
                }else myWriter.write("END\n");
                if (!(discountField.getText() == null || discountField.getText().trim().isEmpty())) {
                    myWriter.write("DISCOUNT\n");
                    myWriter.write(discountField.getText());
                }
                else if(!(surchargeField.getText() == null || surchargeField.getText().trim().isEmpty())){
                    myWriter.write("SURCHARGE\n");
                    myWriter.write(surchargeField.getText());
                }
                else myWriter.write("END");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
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
        deckDetails.setText(df.format((newDeck.getLength() + (float)newDeck.getLnthInch()/12))
                + " ft. x " + df.format((newDeck.getBreadth() + (float)newDeck.getBreadthInch()/12)) + " ft.");
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
