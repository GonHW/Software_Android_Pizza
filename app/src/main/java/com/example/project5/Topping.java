package com.example.project5;

import java.util.ArrayList;

/**
 * Represents Topping for Pizza as an enumeration.
 * Each enum value is associated with a specific string name code.
 *
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */

public enum Topping {

    /** Information for SAUSAGE(SAUSAGE), PEPPERONI(PEPPERONI), GREEN_PEPPER(GREEN PEPPER),
     *  ONION(ONION), MUSHROOM(MUSHROOM), HAM(HAM), BLACK_OLIVE(BLACK OLIVE), BEEF(BEEF),
     *  SHRIMP(SHRIMP), SQUID(SQUID), PINEAPPLE(PINEAPPLE), CHICKEN(CHICKEN), and CRAB_MEATS(CRAB MEATS) as toppings for the Pizza.
     *  */
    SAUSAGE("SAUSAGE"),
    PEPPERONI("PEPPERONI"),
    GREEN_PEPPER("GREEN PEPPER"),
    ONION("ONION"),
    MUSHROOM("MUSHROOM"),
    HAM("HAM"),
    BLACK_OLIVE("BLACK OLIVE"),
    BEEF("BEEF"),
    SHRIMP("SHRIMP"),
    SQUID("SQUID"),
    PINEAPPLE("PINEAPPLE"),
    CHICKEN("CHICKEN"),
    CRAB_MEATS("CRAB MEATS");

    /** The full string name of the toppings. **/
    private final String name;

    /**
     * Size enum class constructor.
     * @param name String variable for topping name.
     */
    Topping(String name){
        this.name = name;
    }

    /**
     * Helper method to convert Topping constants list to ArrayList.
     * @return ObservableList of Topping constants list.
     */
    public static ArrayList<String> enumListToList(){
        ArrayList<String> toppings = new ArrayList<>();
        for(int index = 0; index < Topping.values().length; index++){
            toppings.add(Topping.values()[index].toString());
        }
        return toppings;
    }
    /**
     * Helper method to find constant Topping.
     * @param topping Topping String,
     * @return Topping constant
     */
    public static Topping find(String topping){
        for(int index = 0; index < Topping.values().length; index++){
            if(topping.equalsIgnoreCase(Topping.values()[index].toString())){
                return Topping.values()[index];
            }
        }
        return null;
    }
    /**
     * Helper method to convert string arraylist to ToppingList.
     * @param list string arraylist for toppings.
     * @return Topping arraylist.
     */
    public static ArrayList<Topping> ListToToppingList(ArrayList<String> list){
        ArrayList<Topping> temp = new ArrayList<>();
        while(list.size() > 0){
            temp.add(find(list.remove(0)));
        }
        return temp;
    }


    /**
     * Return the Topping information as a String.
     * @return name String type of the topping.
     */
    @Override
    public String toString(){
        return String.format("%s", this.name);
    }

}