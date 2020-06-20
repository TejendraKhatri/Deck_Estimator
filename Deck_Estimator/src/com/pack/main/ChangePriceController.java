package com.pack.main;

import com.pack.connectivity.ConnectionClass;
import com.pack.objects.Material;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChangePriceController implements Initializable{
    @FXML
    Button goBackBtn;
    @FXML
    Button editBtn;
    @FXML
    Button updateBtn;
    @FXML
    TextField newAmtField;
    @FXML
    TableView matTableView;
    @FXML
    TableColumn nameCol;
    @FXML
    TableColumn priceCol;
    @FXML
    TableColumn idCol;
    @FXML
    Pane updatePane;
    @FXML
    Label matNameLabel;

    ArrayList<Material> materialList = new ArrayList<>();
    ObservableList<Material> obList = FXCollections.observableArrayList();
    ResultSet rs;
    PreparedStatement st;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePane.setVisible(false);
        updateBtn.setDisable(true);
        editBtn.setDisable(true);

        newAmtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d{0,4}([\\.]\\d{0,2})?")) {
                    newAmtField.setText(s);
                }
                if(t1.isEmpty()){
                    updateBtn.setDisable(true);
                }
                else updateBtn.setDisable(false);
            }
        });

//        matTableView.add

        populateTableView();
    }

    @FXML
    private void btnHandleAction(ActionEvent e){
        if(e.getSource() == goBackBtn){
            optionsPage();
        }
        else if(e.getSource() == editBtn){
            updatePane.setVisible(true);
            matNameLabel.setText( " Aile lai temporary naam");
        }
        else if(e.getSource() == updateBtn){
            updatePane.setVisible(false);
            System.out.println("updated");
        }
    }

    private void populateTableView(){
        try{
            rs = ConnectionClass.connection.createStatement().executeQuery("SELECT * FROM material");
            while(rs.next()){
                materialList.add(new Material(rs.getInt("Product_ID"),rs.getString("Material_Name"),
                        rs.getDouble("Unit_Price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        obList.addAll(materialList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        matTableView.setItems(obList);
    }

    private void optionsPage(){
        try {
            Stage stage;
            Parent root;
            stage = (Stage) goBackBtn.getScene().getWindow();
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
