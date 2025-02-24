package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Main activity class for the RU PIZZA Order App.
 * This class acts as the entry point of the app, managing navigation to different ordering activities.
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */
public class MainActivity extends AppCompatActivity {

    /** Define globally available StoreOrder. */
    private static StoreOrder storeOrder = new StoreOrder();
    /** Define globally available CurrentOrder. */
    private static CurrentOrder currentOrder = new CurrentOrder(storeOrder.getNextOrderNumber());

    /** Helper method that makes a new Order.*/
    public static void makeNewCurrentOrder(){
        currentOrder = new CurrentOrder(storeOrder.getNextOrderNumber());
    }

    /** Getter method for StoreOrder.*/
    public static StoreOrder getStoreOrder(){return storeOrder;}

    /** Getter method for CurrentOrder.*/
    public static CurrentOrder getCurrentOrder(){return currentOrder;}

    /**
     * The Initialization method for this activity. It will also initialize the storeOrder.
     * @param savedInstanceState Bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Event handler for the Store Order button, navigates to the StoreOrderActivity.
     * @param view The view (button) that triggered this method.
     */
    public void onStoreButton(View view) {
        Intent storeOrder = new Intent(this, StoreOrderActivity.class);
        startActivity(storeOrder);
    }

    /**
     * Event handler for the Ordering button, navigates to the OrderingActivity.
     * @param view The view (button) that triggered this method.
     */
    public void onOrderingButton(View view) {
        Intent pizzaOrder = new Intent(this, OrderingActivity.class);
        startActivity(pizzaOrder);
    }

    /**
     * Event handler for the Current Order button, navigates to the CurrentOrderActivity.
     * @param view The view (button) that triggered this method.
     */
    public void onCurrentButton(View view) {
        Intent currentOrder = new Intent(this, CurrentOrderActivity.class);
        startActivity(currentOrder);
    }
}