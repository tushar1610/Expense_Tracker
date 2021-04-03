package com.example.android.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    ArrayList<Expense> expenseArrayList;
    Context context;

    public ExpenseAdapter(Context context, ArrayList<Expense> expenseArrayList){
        this.context = context;
        this.expenseArrayList = expenseArrayList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_row, parent, false );
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        holder.name.setText(expenseArrayList.get(position).getNameExpense());
        holder.amount.setText(expenseArrayList.get(position).getAmountExpense());
        holder.note.setText(expenseArrayList.get(position).getNoteExpense());
    }

    @Override
    public int getItemCount() {
        return expenseArrayList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{

        TextView name, amount, note;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_tv);
            amount = itemView.findViewById(R.id.amount_tv);
            note = itemView.findViewById(R.id.note_tv);
        }
    }
}
