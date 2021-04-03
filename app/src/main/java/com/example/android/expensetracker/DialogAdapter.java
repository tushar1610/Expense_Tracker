package com.example.android.expensetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogAdapter extends AppCompatDialogFragment {

    EditText name_text, amount_text, note_text;
    private DialogListenerInterface listenerInterface;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view)
                .setTitle("Details Of Expense")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nameText = name_text.getText().toString().trim();
                        String amountText = amount_text.getText().toString().trim();
                        String noteText = note_text.getText().toString().trim();
                        listenerInterface.applyText(nameText, amountText, noteText);
                    }
                });

        name_text = view.findViewById(R.id.name_friend);
        amount_text = view.findViewById(R.id.expense);
        note_text = view.findViewById(R.id.note);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            listenerInterface = (DialogListenerInterface) context;
        } catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public interface DialogListenerInterface{
        void applyText(String name, String amount, String note);
    }
}
