package com.example.project5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


/**
 * Activity class for managing the current pizza order.
 * Displays the current order details, allows the user to remove individual pizzas or cancel the entire order,
 * and provides an option to place the order.
 *
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */
public class CurrentOrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    /** Instance variable of ListView type for list of pizza. */
    private ListView listPizza;
    /** Instance variable of ArrayAdapter<String> typeArray Adapter list of pizza.
     */
    private ArrayAdapter<String> pizzaListAdapter;
    /** Instance variable for TextView type for display fields current order number, sub total, sales Taxes, and Total cost of order. */
    private TextView currentOrderID , subtotal, salesTaxes, orderTotal;

    /** Constant for the max number of current order in store orders. */
    private static final int MAX_CURRENT_ORDER = 30;

    /**
     * Initializes the activity, sets up the ListView adapter, and displays the current order details.
     * @param savedInstanceState Bundle containing the state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pizzaListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1 ,currentOrderPizzaListInfo());
        listPizza = findViewById(R.id.listView);
        listPizza.setOnItemClickListener(this);
        listPizza.setAdapter(pizzaListAdapter);
        currentOrderID = findViewById(R.id.orderNumber);
        subtotal = findViewById(R.id.subtotal_output);
        salesTaxes = findViewById(R.id.tax_output);
        orderTotal = findViewById(R.id.total_output);
        currentOrderID.setText("" + MainActivity.getCurrentOrder().getOrder_ID());
        subtotal.setText(MainActivity.getCurrentOrder().computeSubTotal());
        salesTaxes.setText(MainActivity.getCurrentOrder().computeTax());
        orderTotal.setText(MainActivity.getCurrentOrder().computeTotal());
    }

    /**
     * Helper method to generate a list of pizzas in the current order as strings.
     * Converts each pizza in the current order to a string representation.
     * @return orderNumber ArrayList<String> type of pizza descriptions.
     */
    private ArrayList<String> currentOrderPizzaListInfo(){
        ArrayList<String> orderNumber = new ArrayList<>();
        ArrayList<Pizza> pizzaList = MainActivity.getCurrentOrder().getPizzaList();
        for(int i = 0; pizzaList.size()>i; i++){
            orderNumber.add(pizzaList.get(i).toString());
        }
        return orderNumber;
    }

    /**
     * Helper method to update the display of prices (subtotal, taxes, and total) in the current order.
     * Recalculates and updates the displayed prices based on the current order.
     */
    private void updatePrice(){
        subtotal.setText(MainActivity.getCurrentOrder().computeSubTotal());
        salesTaxes.setText(MainActivity.getCurrentOrder().computeTax());
        orderTotal.setText(MainActivity.getCurrentOrder().computeTotal());
    }

    /**
     * OnClick Method to handle the cancellation of the entire order.
     * Displays an AlertDialog for confirmation and clears the order if confirmed.
     * @param view View type for the view that was clicked to trigger this method.
     */
    public void cancel_all(View view){
        if(pizzaListAdapter.getCount() > 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setMessage("Do you want to remove all the pizzas?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.getCurrentOrder().getPizzaList().removeAll(MainActivity.getCurrentOrder().getPizzaList());
                    pizzaListAdapter.clear();
                    listPizza = findViewById(R.id.listView);
                    listPizza.setAdapter(pizzaListAdapter);
                    updatePrice();
                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(view.getContext(), "Pizzas remain!", Toast.LENGTH_SHORT).show();
                }
            });
            alert.show();
        }else{
            Toast.makeText(view.getContext(), "Order is Empty!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * OnClick Method to handle placing the current order.
     * Displays an AlertDialog for confirmation and adds the order to the store order if confirmed.
     * @param view View type for the view that was clicked to trigger this method.
     */
    public void place_order(View view){
        if(pizzaListAdapter.getCount() > 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setMessage("Do you want to place the order?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    int currentOrderInStore = MainActivity.getStoreOrder().getOrderList().size();
                    if(currentOrderInStore >= MAX_CURRENT_ORDER){
                        Toast.makeText(view.getContext(), "Store Order can't hold more than " + MAX_CURRENT_ORDER + " current orders! Current Order Not Added!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    MainActivity.getStoreOrder().addOrder(MainActivity.getCurrentOrder());
                    MainActivity.makeNewCurrentOrder();
                    currentOrderID.setText(MainActivity.getCurrentOrder().getOrder_ID()+"");
                    updatePrice();
                    listPizza.setAdapter(null);
                    pizzaListAdapter.clear();
                    Toast.makeText(view.getContext(), "Current Order Placed!", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(view.getContext(), "Current Order not Placed!", Toast.LENGTH_SHORT).show();
                }
            });
            alert.show();
        }
        else{
            Toast.makeText(view.getContext(), "Order is Empty!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * OnItemClickListener Method for handling item selection in the ListView.
     * Displays an AlertDialog to confirm the removal of a selected pizza from the order.
     * @param adapterView AdapterView<?> type for the AdapterView where the selection occurred.
     * @param view View type for the view within the AdapterView that was clicked.
     * @param i int type for the position of the view in the adapter.
     * @param l int type for the row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String pizzaTarget = (String) adapterView.getAdapter().getItem(i);
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        alert.setMessage("Do you want to remove this pizza?");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int index = currentOrderPizzaListInfo().indexOf(pizzaTarget);
                MainActivity.getCurrentOrder().removePizza(MainActivity.getCurrentOrder().getPizzaList().get(index));
                pizzaListAdapter.remove(pizzaTarget);
                listPizza = findViewById(R.id.listView);
                listPizza.setAdapter(pizzaListAdapter);
                updatePrice();
                Toast.makeText(view.getContext(), "Pizza removed!", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(view.getContext(), "Pizza not Cancelled!", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }
}
