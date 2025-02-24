package com.example.project5;


/**
 * Factory class for creating various types of Pizza objects.
 * Supports creating predefined types like Deluxe, Supreme, Meatzza, Seafood, Pepperoni, and BuildYourOwn.
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */
public class PizzaMaker {

    /**
     * Creates and returns a Pizza object based on the specified pizza type.
     * @param pizzaType The type of pizza to create, as a String.
     * Returns object Pizza if matched, otherwise null if the specified type is not recognized.
     */
    public static Pizza createPizza(String pizzaType) {
        if(pizzaType.equalsIgnoreCase("Deluxe")){
            return new Deluxe();
        }
        else if(pizzaType.equalsIgnoreCase("Supreme")){
            return new Supreme();
        }
        else if(pizzaType.equalsIgnoreCase("Meatzza")){
            return new Meatzza();
        }
        else if(pizzaType.equalsIgnoreCase("Seafood")){
            return new Seafood();
        }
        else if(pizzaType.equalsIgnoreCase("Pepperoni")) {
            return new Pepperoni();
        }

        else if(pizzaType.equalsIgnoreCase("Chicken Alfredo")){
            return new ChickenAlfredo();
        }
        else if(pizzaType.equalsIgnoreCase("Hawaiian")){
            return new Hawaiian();
        }
        else if(pizzaType.equalsIgnoreCase("Vegetable")){
            return new Vegetable();
        }
        else if(pizzaType.equalsIgnoreCase("Veggie Alfredo")) {
            return new VeggieAlfredo();
        }
        if(pizzaType.equalsIgnoreCase("The Pig")){
            return new ThePig();
        }
        else if(pizzaType.equalsIgnoreCase("BuildYourOwn")) {
            return new BuildYourOwn();
        }
        return null;

    }
}
