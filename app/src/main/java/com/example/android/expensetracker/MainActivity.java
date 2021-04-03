package com.example.android.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DialogAdapter.DialogListenerInterface, CalculateDialogAdapter.CalculateInterface {

    RecyclerView rvExpenses;
    FloatingActionButton add_button;
    ArrayList<Expense> expenses;
    ExpenseAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_file, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvExpenses = findViewById(R.id.rv_expense);
        add_button = findViewById(R.id.add);

        loadData();
        buildRecyclerView();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    private void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences("Expense", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("arrayList", null);
        Type type = new TypeToken<ArrayList<Expense>>(){}.getType();
        expenses = gson.fromJson(json, type);

        if (expenses == null){
            expenses = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        adapter = new ExpenseAdapter(MainActivity.this, expenses);
        rvExpenses.setAdapter(adapter);
        rvExpenses.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void openDialog() {

        DialogAdapter dialogAdapter = new DialogAdapter();
        dialogAdapter.show(getSupportFragmentManager(), "Add Expense Dialog");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sum_total:
                sumTotal();
                return true;
            case R.id.reset:
                SharedPreferences sharedPreferences = getSharedPreferences("Expense", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                expenses.clear();
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sumTotal() {
        CalculateDialogAdapter calculateDialogAdapter = new CalculateDialogAdapter();
        calculateDialogAdapter.show(getSupportFragmentManager(), "Calculate Amount Per Head");
    }

    @Override
    public void applyText(String name, String amount, String note) {

        expenses.add(new Expense(name, amount, note));
        adapter.notifyItemInserted(expenses.size());

        //Apply SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("Expense", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(expenses);
        editor.putString("arrayList", json);
        editor.apply();
    }

    @Override
    public void applyCalculation(String numberOfFriends) {
        int nof = Integer.parseInt(numberOfFriends);
        int sum = 0;
        for (int i = 0; i<expenses.size(); i++){
            sum = sum + Integer.parseInt(expenses.get(i).amountExpense);
        }
        showAmountPerHead(sum/nof);
    }

    private void showAmountPerHead(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Amount per head = Rs." + i);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}