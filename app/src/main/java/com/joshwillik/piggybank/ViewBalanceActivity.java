package com.joshwillik.piggybank;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class ViewBalanceActivity extends Activity {
    public static final String BALANCE_KEY = "balance";
    public static final float BALANCE_DEFAULT = 0;

    private Account account = new Account();
    private EditText input;
    private SharedPreferences preferences;
    private DecimalFormat balanceFormatter = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        input = (EditText) findViewById(R.id.fund_change_input);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        this.loadBalance();
        this.displayBalance();
    }

    private void displayBalance(){
        String balance = "$" + this.balanceFormatter.format(this.account.getBalance());
        TextView balanceView = (TextView) findViewById(R.id.balance);
        balanceView.setText(balance);
    }

    public void makeDeposit(View v){
        this.account.deposit(this.parseInput());
        this.updateView();
    }

    public void makeWithdrawal(View v){
        this.account.withdraw(this.parseInput());
        this.updateView();
    }

    public void updateView(){
        this.displayBalance();
        this.saveBalance();
        this.input.setText("");
    }

    private float parseInput(){
        try{
            return Float.parseFloat(input.getText().toString());
        } catch( Exception e ){
            parseFailed();
            return 0;
        }
    }
    private void parseFailed() {
        Toast.makeText(this, "Cannot parse number", Toast.LENGTH_LONG);
        input.setText("");
    }

    public void saveBalance(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(BALANCE_KEY, account.getBalance());
        editor.commit();
    }

    public void loadBalance(){
        float balance = preferences.getFloat(BALANCE_KEY, BALANCE_DEFAULT);
        account.setBalance( balance );
    }
}
