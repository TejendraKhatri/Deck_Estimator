package com.pack.main;

import com.pack.objects.Deck;
import com.pack.objects.Stairs;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DeckDetailsController {
    @FXML
    ComboBox<Integer> lengthFt;
    @FXML
    ComboBox<Integer> lengthInch;
    @FXML
    ComboBox<Integer> breadthFt;
    @FXML
    ComboBox<Integer> breadthInch;
    @FXML
    ComboBox<Integer> heightFt;
    @FXML
    Button nextBtn;
    @FXML
    CheckBox stairsChk;
    @FXML
    TextField totalRunTextBox;

    public static Deck newDeck;
    public static Stairs newStairs;

    public void initialize(){
        nextBtn.setDisable(true);
        totalRunTextBox.setDisable(true);

        for(int i = 1; i <= 100 ; i++){
            lengthFt.getItems().add(i);
            breadthFt.getItems().add(i);
            if(i<=12){
                lengthInch.getItems().add(i-1);
                breadthInch.getItems().add(i-1);
                heightFt.getItems().add(i-1);
            }
        }

        lengthFt.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                if(lengthFt.getValue() != null && lengthInch.getValue() != null &&
                        breadthFt.getValue() != null && breadthInch.getValue() != null
                        && heightFt.getValue() != null){
                    nextBtn.setDisable(false);
                }
            }
        });

        lengthInch.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                if(lengthFt.getValue() != null && lengthInch.getValue() != null &&
                        breadthFt.getValue() != null && breadthInch.getValue() != null
                        && heightFt.getValue() != null){
                    nextBtn.setDisable(false);
                }
            }
        });

        breadthFt.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                if(lengthFt.getValue() != null && lengthInch.getValue() != null &&
                        breadthFt.getValue() != null && breadthInch.getValue() != null
                        && heightFt.getValue() != null){
                    nextBtn.setDisable(false);
                }
            }
        });

        breadthInch.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                if(lengthFt.getValue() != null && lengthInch.getValue() != null &&
                        breadthFt.getValue() != null && breadthInch.getValue() != null
                        && heightFt.getValue() != null){
                    nextBtn.setDisable(false);
                }
            }
        });

        heightFt.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                if(lengthFt.getValue() != null && lengthInch.getValue() != null &&
                        breadthFt.getValue() != null && breadthInch.getValue() != null
                        && heightFt.getValue() != null){
                    nextBtn.setDisable(false);
                }
            }
        });

        stairsChk.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                totalRunTextBox.setDisable(!stairsChk.isSelected());
                nextBtn.setDisable(!stairsChk.isSelected() || totalRunTextBox.getText().isEmpty());
            }
        });

        totalRunTextBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d{0,10}?")) {
                    totalRunTextBox.setText(s);
                }
                nextBtn.setDisable(totalRunTextBox.getText().isEmpty());
            }
        });

    }

    @FXML
    private void btnHandleAction(ActionEvent event){
        if(event.getSource() == nextBtn){
            newDeck = new Deck(lengthFt.getValue(),lengthInch.getValue(),breadthFt.getValue(),breadthInch.getValue(),heightFt.getValue());
            if(stairsChk.isSelected() && !(totalRunTextBox.getText().isEmpty())){
                newStairs = new Stairs(heightFt.getValue(),Float.valueOf(totalRunTextBox.getText()));
            }
            try {
                Stage stage;
                Parent root;
                stage = (Stage) nextBtn.getScene().getWindow();
                FXMLLoader myLoader =
                        new FXMLLoader(getClass().getResource("ResultPage.fxml"));
                root = myLoader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Deck Calculator");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
