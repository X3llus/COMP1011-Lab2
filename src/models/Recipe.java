package models;

import java.util.Arrays;
import java.util.List;

public class Recipe {
    private String name;
    private String[] ingredients;
    private String[] steps;
    private String description;

    /**
     * Constructor for classes.Recipe object with no description
     * @param name
     * @param ingredients
     * @param steps
     */
    public Recipe(String name, String[] ingredients, String[] steps) {
        setName(name);
        setIngredients(ingredients);
        setSteps(steps);
    }

    /**
     * Constructor for classes.Recipe object with description
     * @param name Name of recipe
     * @param ingredients List of ingredients
     * @param steps List of String
     * @param description
     */
    public Recipe(String name, String[] ingredients, String[] steps, String description) {
        setName(name);
        setIngredients(ingredients);
        setSteps(steps);
        setDescription(description);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name is empty");
        }
    }

    public String[] getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(String[] ingredients) {
        if (ingredients.length > 0) {
            this.ingredients = ingredients;
        } else {
            throw new IllegalArgumentException("Ingredients is empty");
        }
    }

    public String[] getSteps() {
        return this.steps;
    }

    public void setSteps(String[] steps) {
        if (steps.length > 0) {
            this.steps = steps;
        } else {
            throw new IllegalArgumentException("Steps is empty");
        }
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (!description.isEmpty()) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Description is empty");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n" + name + "\n");

        int nameLength = name.length();
        for (int i = 0; i < nameLength + 1; i++) {
            stringBuilder.append("-");
        }

        if (description != null) {
            stringBuilder.append("\n" + description);
        }

        stringBuilder.append("\n\nIngredients:\n\n");

        List<String> ingredientsList = Arrays.asList(ingredients);
        ingredientsList.forEach((i) -> stringBuilder.append("\t" + i + "\n"));

        stringBuilder.append("\nSteps:\n\n");

        List<String> stepsList = Arrays.asList(steps);
        stepsList.forEach((i) -> stringBuilder.append("\t" + (stepsList.indexOf(i) + 1) + ". " + i + "\n"));

        return stringBuilder.toString();
    }
}
