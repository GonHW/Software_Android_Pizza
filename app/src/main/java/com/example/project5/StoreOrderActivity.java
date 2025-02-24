package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


/**
 * Activity for displaying and managing store orders.
 * Allows users to view orders, see total prices, and remove orders from the store list.
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */
public class StoreOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    /** Instance variable Spinner type for spinner use to store order number. */
    private Spinner spinner;

    /** Instance variable ListView type for ListView to list the pizza for viewing. */
    private ListView listOfPizzaStoreOrder;

    /** Instance variable TextView type for total price to show total price for this order. */
    private TextView totalPrice;

    /** Instance variable String ArrayAdapter type for Array Adapter for list of pizzas in store order. */
    private ArrayAdapter<String> listOfPizzaStoreOrderAdapter;

    /**  Instance variable integer ArrayAdapter type forArray Adapter for list of order numbers in store order. */
    private ArrayAdapter<Integer>  orderNumberListAdapter;

    /**
     * Initializes the activity, sets up the spinner for order numbers, and displays order details.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *        this Bundle contains the data it most recently supplied. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(MainActivity.getStoreOrder().getOrderList().size() > 0){
            orderNumberListAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, orderNumberList());
            spinner = findViewById(R.id.spinner);
            spinner.setAdapter(orderNumberListAdapter);
            spinner.setSelection(0);
            spinner.setOnItemSelectedListener(this);
            displayListAndPrice(spinner.getSelectedItemPosition());
        }else{
            totalPrice = findViewById(R.id.total_output);
            totalPrice.setText("0.00");
            Toast.makeText(this, "Store Order is Empty!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Helper method that creates and returns a list of order numbers from the store's order list.
     * @return orderNumber Integer ArrayList type that containing order numbers.
     */
    private ArrayList<Integer> orderNumberList(){
        ArrayList<Integer> orderNumber = new ArrayList<>();
        for(int i = 0; MainActivity.getStoreOrder().getOrderList().size()>i; i++){
            orderNumber.add(MainActivity.getStoreOrder().getOrderList().get(i).getOrder_ID());
        }
        return orderNumber;
    }

    /**
     * Helper method that generates and returns a list of pizza descriptions for a specific order.
     * @param index The index of the selected order in the store's order list.
     * @return pizzaListString string ArrayList type containing pizza descriptions for the selected order.
     */
    private ArrayList<String> currentOrderPizzaListInfo(int index){
        ArrayList<String> pizzaListString = new ArrayList<>();
        ArrayList<Pizza> pizzaList = MainActivity.getStoreOrder().getOrderList().get(index).getPizzaList();
        for(int i = 0; pizzaList.size()>i; i++){
            pizzaListString.add(pizzaList.get(i).toString());
        }
        return pizzaListString;
    }

    /**
     * Helper method that updates the display of the pizza list and total price for the selected order.
     * @param index int type that the index of the selected order in the store's order list.
     */
    private void displayListAndPrice(int index){
        listOfPizzaStoreOrderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, currentOrderPizzaListInfo(index));
        listOfPizzaStoreOrder = findViewById(R.id.listView);
        listOfPizzaStoreOrder.setAdapter(listOfPizzaStoreOrderAdapter);
        totalPrice = findViewById(R.id.total_output);
        totalPrice.setText(MainActivity.getStoreOrder().getOrderList().get(index).computeTotal());
    }


    /**
     * Handles actions to take when an item is selected in the spinner.
     * Updates the display list and total price based on the selected order.
     * @param adapterView The AdapterView where the selection happened.
     * @param view View type that the view within the AdapterView that was clicked.
     * @param i int type that the position of the view in the adapter.
     * @param l long type that the row id of the item that was clicked.
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        displayListAndPrice(i);
    }

    /**
     * Placeholder method for handling scenarios where no item is selected in the spinner.
     * @param adapterView AdapterView that the AdapterView where the lack of selection occurred.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    /**
     * Method that removes the selected order from the store's order list and updates the display.
     * @param view View type that the view (button) that was clicked to trigger this method.
     */
    public void removeOrder(View view){
        if(MainActivity.getStoreOrder().getOrderList().size() > 0){
            int index = spinner.getSelectedItemPosition();
            MainActivity.getStoreOrder().getOrderList().remove(MainActivity.getStoreOrder().getOrderList().get(index));
            if(MainActivity.getStoreOrder().getOrderList().size() > 0){
                orderNumberListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,orderNumberList());
                spinner.setAdapter(orderNumberListAdapter);
                spinner.setSelection(0);
                displayListAndPrice(spinner.getSelectedItemPosition());
            }else{
                listOfPizzaStoreOrder.setAdapter(null);
                spinner.setAdapter(null);
                totalPrice.setText("0.00");
            }
        }else{
            Toast.makeText(this, "Store Order is Empty!", Toast.LENGTH_SHORT).show();
        }
    }
}
