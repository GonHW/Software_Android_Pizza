package com.example.project5;


import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Activity class for presenting a list of pizza options to order.
 * This class manages the display of different types of pizzas using a RecyclerView,
 * allowing users to select and order their preferred pizzas.
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */

public class OrderingActivity extends AppCompatActivity {

    /** Instance variable ArrayList of pizza for pizzaListInfo. */
    private ArrayList<Pizza> pizzaListInfo = new ArrayList<>();

    /**
     * Initializes the OrderingActivity, setting up the RecyclerView with a list of pizza options.
     * The method populates the RecyclerView with different pizza types, enabling users to view
     * and select from various pizza options.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *  this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        Collections.addAll(pizzaListInfo, PizzaMaker.createPizza("BuildYourOwn"), PizzaMaker.createPizza("Deluxe"),
                PizzaMaker.createPizza("Supreme"), PizzaMaker.createPizza("Meatzza"), PizzaMaker.createPizza("Seafood"),
                PizzaMaker.createPizza("Pepperoni"), PizzaMaker.createPizza("Chicken Alfredo"), PizzaMaker.createPizza("Hawaiian"),
                PizzaMaker.createPizza("Vegetable"), PizzaMaker.createPizza("Veggie Alfredo"), PizzaMaker.createPizza("The Pig"));
        PizzaInfoRecyclerView adapter = new PizzaInfoRecyclerView(this, pizzaListInfo);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
