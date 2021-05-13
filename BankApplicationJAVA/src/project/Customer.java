/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;

/**
 *
 * @author nbohio
 */
public class Customer extends Manager{
    
    public static ArrayList<Customer> customers = new ArrayList();
    public String username = "";
    public String password = "";
    public double balance = 0;
    public double amt = 0;
    public static String role = "customer";
    public Account acc = new Silver();
    public int fee = 0;


    public Customer(String u, String p, double b) {
        this.balance = b;
        this.username = u;
        this.password = p;
        this.acc = new Silver();
    }
    
    public void setAcc(Account acc){
        this.acc = acc;
    }
    public Account getAcc(Account acc){
        return acc;
    }
    public void updateAcc(Account acc) {
        if(balance < 10000)
            setAcc(new Silver());
        if(balance <20000 && balance >= 10000)
            setAcc(new Gold());
        if(balance>=20000)
            setAcc(new Platinum());
    }
    public void findFee(Account acc){
    }
    public double getBalance() {
        return balance;
    }

    
    
    
}
