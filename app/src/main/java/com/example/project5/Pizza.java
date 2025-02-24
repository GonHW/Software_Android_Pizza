package com.example.project5;

import java.util.ArrayList;


/**
 * Abstract representation of pizza, providing common structure and behavior for different types of pizza.
 * Cannot be instantiated directly and must be subclassed to define specific pizza behaviors.
 *
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */
public abstract class Pizza {

    /** Instance variable of an arraylist for holding the toppings (from enum class) for the pizza. */
    protected ArrayList<Topping> toppings; //Topping is a enum class

    /** Instance variable for the Size (from the enum class) for the pizza. */
    protected Size size; //Size is a enum class

    /** Instance variable for the Sauce (from the enum class) for the pizza. */
    protected Sauce sauce; //Sauce is a enum class

    /** Instance variable for the boolean type extra Sauce for the pizza. */
    protected boolean extraSauce;

    /** Instance variable for the boolean type extra Cheese for the pizza. */
    protected boolean extraCheese;

    /** Abstract method of price, implementation is deferred to subclasses. */
    public abstract double price(); //polymorphism

    /** Abstract method to get pizza type, implementation is deferred to subclasses. */
    public abstract String getPizzaType();

    /** Define Constant name for Topping not found equals -1.*/
    private static final int NOT_FOUND = -1;

    /** Define Constant name for ZERO for topping and for initial value of price.*/
    protected static final int ZERO = 0;

    /** Define Constant name MAX number of toppings.*/
    private static final int MAX_TOPPINGS = 7;

    /** Define Constant name extra Sauce for Price.*/
    protected static final double PRICE_EXTRA_SAUCE = 1.00;

    /** Define Constant name extra Cheese for Price.*/
    protected static final double PRICE_EXTRA_CHEESE = 1.00;

    /**
     * Default Constructor for Pizza Class and initialize ArrayList for toppings.
     */
    public Pizza(){
        toppings = new ArrayList<>();
        this.size = null;
        this.sauce = null;
        this.extraSauce = false;
        this.extraCheese = false;
    }

    /**
     * Overload Constructor for Pizza Class and initialize ArrayList for toppings.
     */
    public Pizza(ArrayList<Topping> toppings, Size size, Sauce sauce,boolean extraSauce, boolean extraCheese){
        this.toppings = toppings;
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        if(toppings == null){
            this.toppings = new ArrayList<>();
        }
    }


    /**
     * Helper method for searching for the target Topping in the Arraylist.
     * @param targetTopping Topping type for ENUM to find.
     * @return an integer value of the index of the Topping in the arraylist if found, else NOT_FOUND.
     */
    private int findTopping(Topping targetTopping){
        int index = NOT_FOUND;
        for(int i = 0; i < toppings.size(); i++){
            if(targetTopping == toppings.get(i)){
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Method to add a topping for the pizza, and it can't exceed 7 toppings.
     * @param topping Topping type to be added.
     * @return true boolean for added, else false.
     */
    public boolean addTopping(Topping topping){
        if(toppings.size() >= MAX_TOPPINGS){
            return false;
        }
        if(findTopping(topping) == NOT_FOUND){
            toppings.add(topping);
            return true;
        }
        return false;
    }

    /**
     * Method to remove a topping on the pizza.
     * @param topping Topping type to be removed.
     * @return true boolean for removed, else false.
     */
    public boolean removeTopping(Topping topping){
        if(toppings.size() <= ZERO){
            return false;
        }
        int index = findTopping(topping);
        if(index != NOT_FOUND){
            toppings.remove(index);
            return true;
        }
        return false;
    }



    /**
     * Override toString Method that generates a string for list of toppings.
     * This overrides the toString method from the parent object class.
     * @return Formatted string containing topping details.
     */
    @Override
    public String toString() {
        String textual = "(Sauce:" + sauce;
        if(extraSauce){
            textual += ", Extra Sauce";
        }
        if(extraCheese){
            textual += ", Extra Cheese";
        }
        textual += "), ";
        for(Topping topping : toppings){
            textual += topping + ", ";
        }
        return textual + this.size;
    }


}