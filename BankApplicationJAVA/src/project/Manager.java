/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;


/**
 *
 * @author nbohio
 */
//requires: username and password that are not null and no repeating username
//effects: adds customer to the customers arraylist and deletes from the arraylist
//
//
//
public class Manager {
    public  static String user = "admin";
    public  static String pass = "admin";
    public  static String role = "manager";
    public static boolean success;
    
    public static void addCustomer(String usern, String pass, double b) {
    Customer.customers.add(new Customer(usern, pass, b));
}
    public static void deleteCustomer(String usern){
        for(int i = 0; i< Customer.customers.size(); i++)
            if(Customer.customers.get(i).username.equals(usern)){
                Customer.customers.remove(i);
            } 
                
                
    }


    public static void managerlogin(String u, String p) {
        success = u.equals(user)&&p.equals(pass);
}
    public boolean repOK() {
        // EFFECTS: Returns true if the rep invariant holds for this    
        //          object; otherwise returns false 
         int i;
         int j;
        for(i=0; i < Customer.customers.size(); i++)
            for(j=0; j < Customer.customers.size(); j++)
                if(Customer.customers.get(i).username.equals(Customer.customers.get(j).username))
                        return false;
        return true;
    }   
    @Override
    public String toString() {
        // EFFECTS: dsiplays attributes of the manager  
        return  "username: " + user + "password: " + pass + "role: " + role;
    }
}
