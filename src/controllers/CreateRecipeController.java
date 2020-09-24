package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import models.Recipe;

public class CreateRecipeController {

    @FXML
    private GridPane grd_root;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_description;

    @FXML
    private TextArea txt_ingredients;

    @FXML
    private TextArea txt_steps;

    @FXML
    private Button btn_create;

    public void initialize() {
        btn_create.setOnAction(actionEvent -> {
            try {
                onBtnCreateClick(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    void onBtnCreateClick(ActionEvent event) throws IOException {
        try {
            String name = txt_name.getText();
            String description = txt_description.getText();

            String[] ingredients = makeList(txt_ingredients);
            String[] steps = makeList(txt_steps);

            ArrayList<String> errors = new ArrayList<String>();

            if (name.isEmpty())
                errors.add("name is empty");

            if (ingredients.length <= 0)
                errors.add("no ingredients");

            if (steps.length <= 0)
                errors.add("no steps");

            if (errors.size() > 0) {
                String errorMessage = buildError(errors);
                throw new IllegalArgumentException(errorMessage);
            }

            System.out.println(errors.size());

            Recipe recipe;

            if (description.isEmpty()) {
                recipe = new Recipe(name, ingredients, steps);
            } else {
                recipe = new Recipe(name, ingredients, steps, description);
            }

            openRecipe(recipe, event);

        } catch (IllegalArgumentException e) {
            grd_root.setDisable(true);
            openErrorDialog(e);
        }
    }

    private String[] makeList(TextArea textArea) {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(textArea.getText().split(",")));
        list = new ArrayList<String>(list.stream().map(String::trim).collect(Collectors.toList()));
        return list.toArray(String[]::new);
    }

    String buildError(ArrayList<String> errors) {

        StringBuilder errorMessage = new StringBuilder("");

        errors.forEach(e -> {
            if (errors.indexOf(e) == 0)
                errorMessage.append(e);
            else if (errors.indexOf(e) == errors.size() - 1)
                errorMessage.append(", and " + e);
            else
                errorMessage.append(", " + e);
        });

        return errorMessage.toString();
    }

    void openErrorDialog(IllegalArgumentException e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/errorPopup.fxml"));

        Parent activityParent = loader.load();
        Scene activityScene = new Scene(activityParent);

        ErrorPopupController controller = loader.getController();
        controller.initError(e);
        controller.setParent(this);

        Stage window = new Stage();
        window.setScene(activityScene);
        window.show();
    }

    void openRecipe(Recipe recipe, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/recipe.fxml"));

        Parent activityParent = loader.load();
        Scene activityScene = new Scene(activityParent);

        RecipeController controller = loader.getController();
        controller.initRecipe(recipe);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(activityScene);
        window.show();
    }

    void enable() {
        grd_root.setDisable(false);
    }
}
