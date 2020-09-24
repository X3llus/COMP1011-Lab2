package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Recipe;

import java.io.IOException;

public class RecipeController {

    @FXML
    private Label lbl_recipe;

    @FXML
    private Button btn_back;

    public void initialize() {
        btn_back.setOnAction(actionEvent -> {
            try {
                back(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void initRecipe(Recipe recipe) {
        lbl_recipe.setText(recipe.toString());
    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/createRecipe.fxml"));

        Parent activityParent = loader.load();
        Scene activityScene = new Scene(activityParent);

        CreateRecipeController controller = loader.getController();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(activityScene);
        window.show();
    }
}
