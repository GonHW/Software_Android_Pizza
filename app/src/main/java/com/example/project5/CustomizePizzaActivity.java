package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Activity class for customizing a pizza order.
 * Allows users to select toppings, sauce, and size for a custom pizza, and add it to the current order.
 *
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */
public class CustomizePizzaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    /** Instance variable for TextView for showing the price of the custom pizza. */
    private TextView price;

    /** Instance variables of ListViews for showing available and selected pizza toppings. */
    private ListView availableToppings, selectedToppings;

    /** Instance variable for Adapters for managing lists of available and selected toppings. */
    private ArrayAdapter<String> availableAdapter, selectedAdapter;

    /** Instance variable Strings for size of the pizza. */
    private String size;

    /** Constant for the default false value. */
    private static final boolean DEFAULT_VALUE_FALSE = false;

    /** Constant for the default value 1. */
    private static final int DEFAULT_VALUE_ONE = 1;

    /** Instance variable pizza Pizza object representing the current custom pizza. */
    private Pizza pizza;

    /** Instance variable sauceGroup RadioGroup object representing the sauce group */
    private RadioGroup sauceGroup;

    /** Instance variable of RadioButton object sauceTomato and sauceAlfredo for selection.  */
    private RadioButton sauceTomato, sauceAlfredo;

    private int numberOfOrders;

    /**
     * Initializes the activity, sets up ListView adapters, and retrieves intent extras.
     * @param savedInstanceState Bundle containing the state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_your_own);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pizza = PizzaMaker.createPizza("BuildYourOwn");
        availableAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, Topping.enumListToList());
        selectedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, new ArrayList<>());
        availableToppings = findViewById(R.id.toppingAvailable);
        selectedToppings = findViewById(R.id.selectedToppings);
        availableToppings.setOnItemClickListener(this);
        selectedToppings.setOnItemClickListener(this);
        availableToppings.setAdapter(availableAdapter);
        selectedToppings.setAdapter(selectedAdapter);
        sauceGroup = findViewById(R.id.sauceGroup);
        sauceTomato = findViewById(R.id.Tomato);
        sauceAlfredo = findViewById(R.id.Alfredo);
        sauceTomato.setChecked(true);
        sauceAlfredo.setChecked(false);
        pizza.sauce = Sauce.TOMATO;
        Intent intent = getIntent();
        pizza.extraSauce = intent.getBooleanExtra("EXTRASAUCE", DEFAULT_VALUE_FALSE);
        pizza.extraCheese = intent.getBooleanExtra("EXTRACHEESE", DEFAULT_VALUE_FALSE);
        size = intent.getStringExtra("SIZE");
        price = findViewById(R.id.price_print);
        numberOfOrders = intent.getIntExtra("QUANTITY", DEFAULT_VALUE_ONE);
        setSizePrice();
    }
    /**
     * Helper method to set the pizza's size and update the displayed price.
     * Adjusts the pizza size based on the selected option and recalculates the price.
     */
    private void setSizePrice(){
        if(size.equalsIgnoreCase("small")){
            pizza.size = Size.S;
            price.setText("" + pizza.price());
        }else if(size.equalsIgnoreCase("medium")){
            pizza.size = Size.M;
            price.setText("" + pizza.price());
        }else if(size.equalsIgnoreCase("large")){
            pizza.size = Size.L;
            price.setText("" + pizza.price());
        }
    }

    /**
     * Handles item clicks in both available and selected toppings ListViews.
     * Transfers toppings between lists and updates the pizza price accordingly.
     * @param adapterView The AdapterView where the click happened.
     * @param view The view within the AdapterView that was clicked.
     * @param i The position of the view in the adapter.
     * @param l The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String item = (String) adapterView.getAdapter().getItem(i);
        availableToppings = findViewById(R.id.toppingAvailable);
        selectedToppings = findViewById(R.id.selectedToppings);
        if(adapterView.getAdapter().equals(availableAdapter)){
            if(selectedAdapter.getCount() < 7){
                selectedAdapter.add(item);
                availableAdapter.remove(item);
                availableToppings.setAdapter(availableAdapter);
                selectedToppings.setAdapter(selectedAdapter);
                updateTopping();
                price.setText("" + pizza.price());
            }
            else{
                Toast.makeText(getApplicationContext(), "Cannot Choose More Than 7 Toppings!", Toast.LENGTH_SHORT).show();
            }
        }else if(adapterView.getAdapter().equals(selectedAdapter)){
            availableAdapter.add(item);
            selectedAdapter.remove(item);
            availableToppings.setAdapter(availableAdapter);
            selectedToppings.setAdapter(selectedAdapter);
            updateTopping();
            price.setText("" + pizza.price());
        }
    }

    /**
     * Updates the toppings of the pizza based on the selected items from the ListView.
     * Converts the list of string toppings to the appropriate Topping enum list.
     */
    private void updateTopping(){
        ArrayList<String> newList = new ArrayList<>();
        for(int index = 0; index < selectedAdapter.getCount(); index++){
            newList.add(selectedAdapter.getItem(index));
        }
        pizza.toppings = Topping.ListToToppingList(newList);
    }

    /**
     * Places the customized pizza order, adds it to the main order list, and navigates to the OrderingActivity.
     * @param view The view that was clicked to trigger this method.
     */
    public void placePizzaOrder(View view){
        if(selectedAdapter.getCount() < 3){
            Toast.makeText(getApplicationContext(), "You need to add 3 Toppings or more!", Toast.LENGTH_SHORT).show();
        }
        else {
            if (sauceTomato.isChecked()) {
                pizza.sauce = Sauce.TOMATO;
            } else if (sauceAlfredo.isChecked()) {
                pizza.sauce = Sauce.ALFREDO;
            }
            while(numberOfOrders-- > 0) {
                MainActivity.getCurrentOrder().addPizza(pizza);
            }
            Toast.makeText(getApplicationContext(), "Order Added!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, OrderingActivity.class);
            startActivity(intent);
        }
    }

}
