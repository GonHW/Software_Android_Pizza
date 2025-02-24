package com.example.project5;


import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The current order class that handles the same order.
 * Will be combined with Controller class to run the program.
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */
public class CurrentOrder {
    /** Instance variable for order ID. */
    private int order_ID;

    /** Instance Variable for arraylist of the pizza. */
    private ArrayList<Pizza> pizzaList;

    /** Define Constant name for ZERO.*/
    private static final int ZERO = 0;

    /** Define Constant name for sales tax rate in NJ.*/
    private static final double SALE_TAX_RATE = 0.06625;

    /**
     * Constructor for generating current Order.
     * @param order_ID int data type order ID of the current order.
     */
    public CurrentOrder(int order_ID) {
        this.order_ID = order_ID;
        pizzaList = new ArrayList<Pizza>();
    }

    /**
     * Adding method that attempt to add the pizza in the ArrayList.
     * @param pizza Pizza object type that is the pizza that will be added.
     * @return boolean type that return true if successful add, otherwise false.
     */
    public boolean addPizza(Pizza pizza){
        return pizzaList.add(pizza);
    }

    /**
     * Removing method that attempt to remove the pizza in the ArrayList.
     * @param pizza Pizza object type that is a target pizza.
     * @return boolean type that return true if successful remove, otherwise false.
     */
    public boolean removePizza(Pizza pizza){
        return pizzaList.remove(pizza);
    }

    /**
     * A computing method that calculate the subtotal value of this order.
     * The purpose of this method is to return the double value in string for displaying the total in GUI.
     * @return subTotal String type in a DecimalFormat "#,###.00 with only 2 decimal.
     */
    public String computeSubTotal(){
        double subTotal = ZERO;
        for(int i = 0; i < pizzaList.size(); i++){
            subTotal += pizzaList.get(i).price();
        }
        return (new DecimalFormat("#,##0.00")).format(subTotal);
    }

    /**
     * A computing method that calculate the tax of the subtotal value of this order.
     * The purpose of this method is to return the double value in string for displaying the total in GUI.
     * @return tax String type in a DecimalFormat "#,###.00 with only 2 decimal.
     */
    public String computeTax(){
        double tax = Double.parseDouble(computeSubTotal()) * SALE_TAX_RATE;
        return (new DecimalFormat("#,##0.00")).format(tax);
    }

    /**
     * A computing method that calculate the final total value of this order.
     * The purpose of this method is to return the double value in string for displaying the total in GUI.
     * @return total String type in a DecimalFormat "#,###.00 with only 2 decimal.
     */
    public String computeTotal(){
        double total = Double.parseDouble(computeSubTotal()) + Double.parseDouble(computeTax());
        return (new DecimalFormat("#,##0.00")).format(total);
    }

    /**
     * A getter method for getting the arraylist of the list of pizza in current order.
     * @return pizzaList ArrayList<Pizza> type with object Pizza.
     */
    public ArrayList<Pizza> getPizzaList(){
        return pizzaList;
    }

    /**
     * A getter's method to get order ID.
     * @return order_ID int type for this order.
     */
    public int getOrder_ID(){
        return order_ID;
    }


    /**
     * A toString method to generate a String representation the list of pizza in current order for write into a file.
     * @return text String type for the entire current order.
     */
    @Override
    public String toString() {
        String text = "";
        for(int i = 0; i < pizzaList.size(); i++){
            text += pizzaList.get(i).toString() + "\n";
        }
        return text;
    }
}
