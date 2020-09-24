package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorPopupController {

    CreateRecipeController controller;

    @FXML
    private Label lbl_error;

    @FXML
    private Button btn_okay;

    public void initialize() {
        btn_okay.setOnAction(actionEvent -> {
            onBtnOkayClick(actionEvent);
        });
    }

    public void initError(IllegalArgumentException errMessage) {
        lbl_error.setText(errMessage.getMessage());
    }

    public void setParent(CreateRecipeController controller) {
        this.controller = controller;
    }

    void onBtnOkayClick(ActionEvent event) {
        controller.enable();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
    }
}
