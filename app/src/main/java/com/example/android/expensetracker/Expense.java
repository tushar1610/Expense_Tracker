package com.example.android.expensetracker;

public class Expense {

    String nameExpense, amountExpense, noteExpense;

    public Expense(){}

    public Expense(String nameExpense, String amountExpense, String noteExpense) {
        this.nameExpense = nameExpense;
        this.amountExpense = amountExpense;
        this.noteExpense = noteExpense;
    }

    public String getNameExpense() {
        return nameExpense;
    }

    public void setNameExpense(String nameExpense) {
        this.nameExpense = nameExpense;
    }

    public String getAmountExpense() {
        return amountExpense;
    }

    public void setAmountExpense(String amountExpense) {
        this.amountExpense = amountExpense;
    }

    public String getNoteExpense() {
        return noteExpense;
    }

    public void setNoteExpense(String noteExpense) {
        this.noteExpense = noteExpense;
    }
}
