package com.example.project5;

import java.text.DecimalFormat;
import java.util.Collections;

/**
 * A child class of pizza that represents a Pepperroni Pizza, with price of pizza structure.
 * This pizza type enforces the size of the pizza for the price requirement to show the cost of the pizza.
 *
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */

public class Pepperoni extends Pizza {

    /** Define Constant name for small pizza size price.*/
    private static final double SMALL_PRICE = 10.99;

    /** Define Constant name for medium pizza size price.*/
    private static final double MEDIUM_PRICE = SMALL_PRICE + 2.00;

    /** Define Constant name for large pizza size price.*/
    private static final double LARGE_PRICE = SMALL_PRICE + 4.00;

    /** Define Constant name for the type of pizza. */
    private static final String PIZZA_TYPE = "Pepperoni";

    /**
     * Default Constructor for Pepperoni Pizza Class and initialize ArrayList for toppings.
     */
    public Pepperoni(){
        Collections.addAll(toppings, Topping.PEPPERONI);
        super.sauce = Sauce.TOMATO;
    }

    /**
     * Overload Constructor for Pepperoni Pizza Class and initialize ArrayList for toppings.
     */
    public Pepperoni(Size size, boolean extraSauce, boolean extraCheese){
        super(null, size, Sauce.TOMATO, extraSauce, extraCheese);
        Collections.addAll(toppings, Topping.PEPPERONI);
    }

    /**
     * Override method for the price of the pizza based on the size.
     * @return price Double type price of the pizza.
     */
    @Override
    public double price() {
        double price = ZERO;
        if(super.size == Size.S){
            price = SMALL_PRICE;
        }
        else if(super.size == Size.M) {
            price = MEDIUM_PRICE;
        }
        else if(super.size == Size.L){
            price = LARGE_PRICE;
        }
        if(super.extraSauce){
            price += PRICE_EXTRA_SAUCE;
        }
        if(super.extraCheese){
            price += PRICE_EXTRA_CHEESE;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        String sPrice = decimalFormat.format(price);
        return Double.parseDouble(sPrice);
    }

    /**
     * Getter method to retrieves the type of pizza, which is "Pepperoni".
     * @return PIZZA_TYPE String type representing the pizza type.
     */
    @Override
    public String getPizzaType(){
        return PIZZA_TYPE;
    }

    /**
     * Override toString Method that generates a string for list of toppings, size.
     * This overrides the toString method from the parent object class.
     * @return Formatted string containing topping details.
     */
    @Override
    public String toString() {
        return PIZZA_TYPE + " " + super.toString() + " $" + (new DecimalFormat("#,###.00")).format(price());
    }
}