package com.joshwillik.piggybank;

/**
 * Account class.
 * Stores account balance
 */
public class Account {
    private float balance;

    public float deposit( float num ){
        this.setBalance( balance + num );
        return balance;
    }

    public float withdraw( float num ) {
        this.setBalance( balance - num );
        return balance;
    }

    public float getBalance(){
        return balance;
    }
    public void setBalance( float balance ){
        this.balance = balance;
    }
}
