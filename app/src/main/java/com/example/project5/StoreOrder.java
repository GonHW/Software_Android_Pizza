package com.example.project5;

import java.util.ArrayList;


/**
 * The store order class that handles all order for the store.
 * Will be combined with Controller class to run the program.
 *
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */
public class StoreOrder {

    /** Instance variable ArrayList that holds a list of current order. */
    private ArrayList<CurrentOrder> orderList;

    /** Static integer to track the next order number. */
    private static int nextOrderNumber = 1;

    /**
     * Default constructor of the StoreOrder Class and initialize ArrayList for CurrentOrder.
     */
    public StoreOrder(){
        orderList = new ArrayList<>();
    }

    /**
     * A getter method for getting the arraylist of the orderList in the store.
     * @return orderList ArrayList of different current order in this store order.
     */
    public ArrayList<CurrentOrder> getOrderList(){
        return orderList;
    }

    /**
     * Adding method that attempt to add the CurrentOrder in the ArrayList.
     * @param newOrder CurrentOrder object type that is the pizza that will be added.
     * @return boolean type that return true if successful add, otherwise false.
     */
    public boolean addOrder(CurrentOrder newOrder){
        nextOrderNumber++;

        return orderList.add(newOrder);
    }


    /**
     * Removing method that attempt to remove the CurrentOrder in the ArrayList.
     * @param target CurrentOrder object type that is a target pizza.
     * @return boolean type that return true if successful remove, otherwise false.
     */
    public boolean removeOrder(CurrentOrder target){
        return orderList.remove(target);
    }

    /**
     * Getter method for returning specific order by the given order number or ID.
     * The order ID or number is created inorder. So we can use Binarysearch to find the order.
     * @param id int ID of the order in the order list.
     * @return a CurrentOrder in the orderList based on the ID. If not find, return null.
     */
    public CurrentOrder getOrderByOrderID(int id){
        int index = binarySearch(orderList, id);
        if(index != -1) {
            return orderList.get(index);
        }
        return null;
    }

    /**
     * Getter method for returning next available order number
     * @return an int that represents a new order number.
     */
    public int getNextOrderNumber(){return nextOrderNumber;}

    /**
     * Helper method to that performs a binary search on a inorder ArrayList of CurrentOrder objects.
     * @param orders The sorted ArrayList of CurrentOrder objects to be searched.
     * @param target The order ID of the CurrentOrder to find.
     * @return The index of the target order if found; otherwise, returns -1.
     */
    private static int binarySearch(ArrayList<CurrentOrder> orders, int target) {
        int low = 0;
        int high = orders.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            CurrentOrder midOrder = orders.get(mid);
            if (midOrder.getOrder_ID() == target) {
                return mid;
            } else if (midOrder.getOrder_ID() < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}


