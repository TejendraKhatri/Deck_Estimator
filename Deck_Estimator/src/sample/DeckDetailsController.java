package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

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

    public void initialize(){
        nextBtn.setDisable(true);
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

    }

    @FXML
    private void btnHandleAction(ActionEvent event){
        if(event.getSource() == nextBtn){

        }
    }



}
