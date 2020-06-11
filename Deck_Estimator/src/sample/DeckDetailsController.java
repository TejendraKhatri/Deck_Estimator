package sample;

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

    }

    @FXML
    private void btnHandleAction(ActionEvent event){
        if(event.getSource() == nextBtn){

        }
    }



}
