package com.example.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * RecyclerView adapter class for displaying pizza information.
 * Manages the display and interaction of pizza items within a RecyclerView.
 * @author Hench Wu (hhw14), Deep Patel(dp1136)
 */
public class PizzaInfoRecyclerView extends RecyclerView.Adapter<PizzaInfoRecyclerView.PizzaItemsHolder> {

    /** Instance variable for context. */
    private Context context;

    /** Instance variable for pizzaList. */
    private ArrayList<Pizza> pizzaList;

    /** Constant for the max number of pizza in current order. */
    private static final int MAX_PIZZA_ORDER = 30;

    /** Constant for the max number of pizza per order. */
    private static final int MAX_PIZZA_PER_ORDER = 10;

    /** Constant for the minimum number of pizza per order. */
    private static final int MIN_PIZZA_PER_ORDER = 1;

    /**
     * int array for holding images id in the following order: "BuildYourOwn", "Deluxe", "Supreme", "Meatzza",
     * "Seafood","Pepperoni", "Chicken Alfredo",
     * "Hawaiian", "Vegetable", "Veggie Alfredo", "The Pig";
     */
    private int[] pictureForPizza = {R.drawable.byo, R.drawable.deluxe, R.drawable.supreme, R.drawable.meatza,
            R.drawable.seafood, R.drawable.pepperoni, R.drawable.chicken_alfredo_pizza,
            R.drawable.hawaiian_pizza, R.drawable.vegetable_pizza, R.drawable.veggie_alfredo, R.drawable.the_pig };

    /**
     * Parameterized constructor that contains context and the pizzaList information.
     * @param context Context type.
     * @param pizzaList Pizza type that contain the list of pizza type.
     */
    public PizzaInfoRecyclerView(Context context, ArrayList<Pizza> pizzaList){
        this.context = context;
        this.pizzaList = pizzaList;
    }

    /**
     * This method creates a new ViewHolder for each item, defining its UI structure when RecyclerView needs a new ViewHolder
     * Inflates the layout for each pizza item in the RecyclerView.
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The view type of the new View.
     * @return A new instance of PizzaItemsHolder containing the inflated view.
     */
    @NonNull
    @Override
    public PizzaInfoRecyclerView.PizzaItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.pizzatype_recycler, parent, false);
        return new PizzaInfoRecyclerView.PizzaItemsHolder(view);
    }

    /**
     * Binds data to the views in the PizzaItemsHolder at the given position in the pizza list.
     * This method sets up the pizza image, type, toppings, and sauce, and updates the UI accordingly
     * and will be called by RecyclerView.
     * @param holder The ViewHolder which should be updated to represent the contents of the pizza item.
     * @param position The position of the pizza item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PizzaInfoRecyclerView.PizzaItemsHolder holder, int position) {

        checkPriceBasedOnCondition(holder, position);
        String pizzaType = pizzaList.get(position).getPizzaType();
        holder.imagesOfPizza.setImageResource(pictureForPizza[position]);
        String textForBuildYouOwn = "Add at least 3 toppings, up to 7. Each topping cost $1.49. First 3 toppings are free. ";
        if(pizzaType.equalsIgnoreCase("BuildYourOwn")){
            holder.pizzaType.setText("Build Your Own");
            holder.pizzaToppingList.setText(textForBuildYouOwn);
            holder.sauceType.setText("");
            holder.addingOrderButton.setText("Build Your Own");
        }
        else{
            holder.pizzaType.setText(pizzaType);
            holder.pizzaToppingList.setText(pizzaList.get(position).toppings.toString().replaceAll("[\\[\\]]", ""));
            holder.sauceType.setText(pizzaList.get(position).sauce.toString());
            holder.addingOrderButton.setText("Add Pizza Order");
        }
    }


    /**
     * Helper method that updates the pizza's extra sauce and cheese options based on the user's selection and recalculates the prices for each size.
     * The method directly modifies the pizza item at the given position in the pizzaList based on the state of checkboxes in the holder.
     * It sets the extraSauce and extraCheese properties of the pizza item, then updates the displayed prices for small, medium, and large sizes.
     * @param holder The ViewHolder containing the UI elements (checkboxes) for the current pizza item.
     * @param position The position of the pizza item in the pizzaList to be updated.
     */
    private void checkPriceBasedOnCondition(@NonNull PizzaInfoRecyclerView.PizzaItemsHolder holder, int position){
        if(holder.checkExtraSauce.isChecked()){
            pizzaList.get(position).extraSauce = true;
        }
        else{
            pizzaList.get(position).extraSauce = false;
        }
        if(holder.checkExtraCheese.isChecked()){
            pizzaList.get(position).extraCheese = true;
        }
        else{
            pizzaList.get(position).extraCheese = false;
        }
        pizzaList.get(position).size = Size.S;
        holder.smallPrice.setText(Size.S.toString() + " $" + pizzaList.get(position).price());
        pizzaList.get(position).size = Size.M;
        holder.mediumPrice.setText(Size.M.toString() + " $" + pizzaList.get(position).price());
        pizzaList.get(position).size = Size.L;
        holder.largePrice.setText(Size.L.toString() + " $" + pizzaList.get(position).price());
        pizzaList.get(position).size = null;

    }
    /**
     * Getter method that returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }
    /**
     * Grabbing the view from our pizza_recycler_view_row layout file.
     * ViewHolder class for pizza items, initializing UI components from the layout file.
     */
    public static class PizzaItemsHolder extends RecyclerView.ViewHolder{
        /** Instance variable for ImageView for pizza pictures. */
        private ImageView imagesOfPizza;

        /** Instance variable for TextView for quantity of pizza, sauce type, pizza type, and list of pizza toppings */
        private TextView sauceType, pizzaType, pizzaToppingList, quantity;

        /** Instance variable for RadioGroup for size group. */
        private RadioGroup sizeGroup;

        /** Instance variable for CheckBox for extra sauce and extra cheese. */
        private CheckBox checkExtraSauce, checkExtraCheese;

        /** Instance variable for RadioButton for pizza size selection. */
        private RadioButton smallPrice, mediumPrice, largePrice;

        /** Instance variable Buttons for adding order, increasing, and decreasing. */
        private Button addingOrderButton, incrementButton, decrementButton;

        /**
         * Constructor for ViewHolder, sets up UI components and click listeners.
         * @param itemView The view of the RecyclerView item.
         */
        public PizzaItemsHolder(@NonNull View itemView) {
            super(itemView);
            imagesOfPizza = itemView.findViewById(R.id.pizza_image);
            checkExtraSauce = itemView.findViewById(R.id.ExtraSauce);
            checkExtraCheese = itemView.findViewById(R.id.ExtraCheese);
            pizzaType = itemView.findViewById(R.id.pizzaType);
            sauceType = itemView.findViewById(R.id.saucetype);
            pizzaToppingList = itemView.findViewById(R.id.pizzaToppingList);
            smallPrice = itemView.findViewById(R.id.small_price);
            mediumPrice = itemView.findViewById(R.id.medium_price);
            largePrice = itemView.findViewById(R.id.large_price);
            addingOrderButton = itemView.findViewById(R.id.AddOrderButton);
            incrementButton = itemView.findViewById(R.id.buttonIncrease);
            decrementButton = itemView.findViewById(R.id.buttonDecrease);
            quantity = itemView.findViewById(R.id.quantity);
            decrementButton.setEnabled(false);
            plus_minus_button();
            setButtonTextOnClick(itemView);
        }

        /**
         * Sets up click listeners for increment and decrement buttons to adjust the quantity value.
         * Increases the quantity by one on each click of the increment button,
         * and decreases it by one on each click of the decrement button,
         * as long as the current quantity is greater than one.
         */
        private void plus_minus_button (){
            incrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentQuantity = Integer.parseInt(quantity.getText().toString());
                    if(currentQuantity < MAX_PIZZA_PER_ORDER){
                        quantity.setText(String.valueOf(++currentQuantity));
                    }
                    if(currentQuantity >= MAX_PIZZA_PER_ORDER){
                        incrementButton.setEnabled(false);
                        Toast.makeText(itemView.getContext(), MAX_PIZZA_PER_ORDER + " is max per order!", Toast.LENGTH_SHORT).show();
                    }
                    if(!decrementButton.isEnabled() && currentQuantity > MIN_PIZZA_PER_ORDER){
                        decrementButton.setEnabled(true);
                    }
                }
            });
            decrementButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentQuantity = Integer.parseInt(quantity.getText().toString());
                    if (currentQuantity > MIN_PIZZA_PER_ORDER) {
                        quantity.setText(String.valueOf(--currentQuantity));
                    }
                    if(currentQuantity <= MIN_PIZZA_PER_ORDER){
                        decrementButton.setEnabled(false);
                    }
                    if(!incrementButton.isEnabled() && currentQuantity < MAX_PIZZA_PER_ORDER){
                        incrementButton.setEnabled(true);
                    }
                }
            });
        }


        /**
         * sets click listener for the button, handling pizza order addition and customization.
         * @param itemView The view of the RecyclerView item.
         */
        private void setButtonTextOnClick(@NonNull View itemView){
            addingOrderButton.setOnClickListener(new View.OnClickListener() {
                /**
                 * OnClick Method that handles click events on the pizza order button, either adding the pizza to the order or initiating pizza customization.
                 * Displays an AlertDialog for confirmation on adding to order, or navigates to the topping selection activity.
                 * @param view The view that was clicked.
                 */
                @Override
                public void onClick(View view) {
                    addingOrderButton = itemView.findViewById(R.id.AddOrderButton);
                    if(addingOrderButton.getText().toString().trim().equalsIgnoreCase("Add Pizza Order")){
                        AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                        alert.setMessage("Do you want to add this pizza to order?");
                        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                setChooseSpecialtyPizza();
                                clearSizeGroup();
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(itemView.getContext(), "Order not added!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog dialog = alert.create();
                        dialog.show();
                    }else if(addingOrderButton.getText().equals("Build Your Own")){
                        setChooseBYOPizza();
                        clearSizeGroup();
                    }
                }

                /**
                 * Helper method to reset the pizza size group and checkbox states.
                 * Clears the size selection and resets extra sauce and cheese checkboxes.
                 */
                private void clearSizeGroup(){
                    if(smallPrice.isChecked()||mediumPrice.isChecked()||largePrice.isChecked()) {
                        sizeGroup = itemView.findViewById(R.id.SizeGroup);
                        sizeGroup.clearCheck();
                        checkExtraSauce.setChecked(false);
                        checkExtraCheese.setChecked(false);
                        quantity.setText("1");
                        incrementButton.setEnabled(true);
                        decrementButton.setEnabled(false);
                    }
                }

                /**
                 * Helper method to create a specialty pizza order with the selected size and extra options.
                 * Sets up and handles the selection process for a specialty pizza based on the user's size and extra options choice.
                 */
                private void setChooseSpecialtyPizza(){
                    if (smallPrice.isChecked()) {
                        createSpecialty((String) pizzaType.getText(), checkExtraSauce.isChecked(), checkExtraCheese.isChecked(), Size.S);
                    } else if (mediumPrice.isChecked()) {
                        createSpecialty((String) pizzaType.getText(), checkExtraSauce.isChecked(), checkExtraCheese.isChecked(), Size.M);
                    } else if (largePrice.isChecked()){
                        createSpecialty((String) pizzaType.getText(),  checkExtraSauce.isChecked(), checkExtraCheese.isChecked(), Size.L);
                    }else{
                        Toast.makeText(itemView.getContext(), "Choose size!!!", Toast.LENGTH_LONG).show();
                    }
                }

                /**
                 * Helper method to navigate to the pizza customization activity with the necessary intent extras.
                 * Prepares and starts the CustomizePizzaActivity with the selected pizza type and extra options.
                 */
                private void setChooseBYOPizza(){
                    int numberOfOrders = Integer.parseInt(("" + quantity.getText()));
                    int pizzaListSize = MainActivity.getCurrentOrder().getPizzaList().size();
                    if(numberOfOrders + pizzaListSize > MAX_PIZZA_ORDER){
                        Toast.makeText(itemView.getContext(), "Current Order can't exceed " + MAX_PIZZA_ORDER + " Pizza orders! Pizzas not Added!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent selectTopping = new Intent(itemView.getContext(), CustomizePizzaActivity.class);
                    selectTopping.putExtra("PIZZATYPE", pizzaType.getText());
                    selectTopping.putExtra("SAUCE", sauceType.getText());
                    selectTopping.putExtra("EXTRASAUCE", checkExtraSauce.isChecked());
                    selectTopping.putExtra("EXTRACHEESE", checkExtraCheese.isChecked());
                    selectTopping.putExtra("QUANTITY", numberOfOrders);
                    if(smallPrice.isChecked()){
                        selectTopping.putExtra("SIZE", "small");
                        itemView.getContext().startActivity(selectTopping);
                    }else if(mediumPrice.isChecked()){
                        selectTopping.putExtra("SIZE", "medium");
                        itemView.getContext().startActivity(selectTopping);
                    }else if(largePrice.isChecked()){
                        selectTopping.putExtra("SIZE", "large");
                        itemView.getContext().startActivity(selectTopping);
                    }else{
                        Toast.makeText(itemView.getContext(), "Choose size!!!", Toast.LENGTH_SHORT).show();
                    }
                }

                /**
                 * Helper method to generate a specialty pizza and add it to the current order.
                 * Creates a specialty pizza based on the specified type, size, and extra options and adds it to the current order.
                 * @param pizzaType The type of pizza to create.
                 * @param extraSauce Boolean indicating if extra sauce is selected.
                 * @param extraCheese Boolean indicating if extra cheese is selected.
                 * @param size The size of the pizza.
                 */
                private void createSpecialty(String pizzaType, boolean extraSauce, boolean extraCheese, Size size){
                    Pizza pizza = PizzaMaker.createPizza(pizzaType);
                    pizza.extraSauce = extraSauce;
                    pizza.extraCheese = extraCheese;
                    pizza.size = size;
                    int numberOfOrders = Integer.parseInt(("" + quantity.getText()));
                    int pizzaListSize = MainActivity.getCurrentOrder().getPizzaList().size();
                    if(numberOfOrders+pizzaListSize > MAX_PIZZA_ORDER){
                        Toast.makeText(itemView.getContext(), "Current Order can't exceed " + MAX_PIZZA_ORDER + " Pizza orders! Pizzas not Added!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    while(numberOfOrders-- > 0) {
                        MainActivity.getCurrentOrder().addPizza(pizza);
                    }
                    Toast.makeText(itemView.getContext(), "Order added!", Toast.LENGTH_SHORT).show();
                }

            });
        }
    }
}
