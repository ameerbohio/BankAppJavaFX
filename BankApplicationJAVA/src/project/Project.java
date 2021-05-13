/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author nbohio
 */
public class Project extends Application {

    String role = "";
    int j = 0;
    boolean found = false;

    @Override
    public void start(Stage primaryStage) {
        Button btn1 = new Button();
        Button btn2 = new Button();
        btn1.setText("Manager");
        btn2.setText("Customer");
        Text text1 = new Text();
        text1.setText("Choose Role");
        text1.setFont(new Font(20));
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                role = "manager";
                Cont(primaryStage);
            }

        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                role = "customer";
                Cont(primaryStage);
            }

        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(btn1, 0, 1);
        gridPane.add(btn2, 1, 1);

        //Creating a scene object 
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage 
        primaryStage.setTitle("Login");

        //Adding scene to the stage 
        primaryStage.setScene(scene);

        //Displaying the contents of the stage 
        primaryStage.show();
    }

    public void Cont(Stage stage) {
        Text text1 = new Text("Username");
        Button back = new Button();
        back.setText("Back");

        //creating label password 
        Text text2 = new Text("Password");

        //Creating Text Filed for email        
        TextField textField1 = new TextField();

        //Creating Text Filed for password        
        PasswordField passwordField = new PasswordField();

        //Creating Buttons 
        Button button1 = new Button("Submit");
        button1.setText("Submit");
        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (role.equals("manager")) {
                    Manager.managerlogin(textField1.getText(), passwordField.getText());
                    if(Manager.success)
                        contmanager(stage);
                    
                } else {
                    if (role.equals("customer")) {
                        if (!Customer.customers.isEmpty()) {
                            for (int i = 0; i < Customer.customers.size(); i++) {
                                if(Customer.customers.get(i).username.equals(textField1.getText()) && Customer.customers.get(i).password.equals(passwordField.getText())){ 
                                    j=i;
                                    contcustomer(stage);
                                }
                                
                            }
                        }
                    }
                }

            }

        });
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                start(stage);
            }

        });
        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();

        //Setting size for the pane  
        gridPane.setMinSize(400, 200);

        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid 
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(button1, 0, 2);
        gridPane.add(back, 4, 3);

        //Creating a scene object 
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage 
        stage.setTitle("Login");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();
    }

    public void contmanager(Stage stage) {
        Button btn1 = new Button();
        Button btn2 = new Button();
        btn1.setText("Add customer");
        btn2.setText("Delete customer");
        Text text1 = new Text();
        text1.setText("Choose Action");
        text1.setFont(new Font(20));
        Button back = new Button();
        back.setText("Logout");

        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Contadd(stage);
            }

        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Contdelete(stage);
            }

        });
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                start(stage);
            }

        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(btn1, 0, 1);
        gridPane.add(btn2, 1, 1);
        gridPane.add(back, 4, 3);

        //Creating a scene object 
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage 
        stage.setTitle("Logged in as Manager");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();
    }

    public void Contadd(Stage stage) {
        Text text1 = new Text("Add Customer username:");
        TextField textField1 = new TextField();
        Text text2 = new Text("Add Customer password:");
        TextField textField2 = new TextField();
        Button btn3 = new Button();
        Button back = new Button();
        back.setText("Back");
        Button logout = new Button();
        logout.setText("Logout");
        btn3.setText("Submit");
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                found = false;
                if(Customer.customers.isEmpty()){
                    Manager.addCustomer(textField1.getText(), textField2.getText(), 100);
                    successMsg(stage);
                }
                else {
                    for(int i = 0; i < Customer.customers.size(); i++) {
                    if (Customer.customers.get(i).username.equals(textField1.getText())) {
                        found = true;
                    }
                }
                    if(found == false){
                        Manager.addCustomer(textField1.getText(), textField2.getText(), 100);
                        successMsg(stage);
                    }
                }
            }

        });
        logout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                start(stage);
            }

        });
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                contmanager(stage);
            }

        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(textField2, 1, 1);
        gridPane.add(back, 4, 3);
        gridPane.add(logout, 5, 3);
        gridPane.add(btn3, 1, 2);
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage 
        stage.setTitle("Logged in as Manager");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();

    }

    public void Contdelete(Stage stage) {
        Text text1 = new Text("Delete Customer by username:");
        TextField textField1 = new TextField();
        Button back = new Button();
        back.setText("Back");
        Button logout = new Button();
        logout.setText("Logout");
        Button btn1 = new Button();
        btn1.setText("Submit");
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Manager.deleteCustomer(textField1.getText());
                successMsg(stage);
            }

        });
        logout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                start(stage);
            }

        });
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                contmanager(stage);
            }

        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(back, 4, 3);
        gridPane.add(logout, 5, 3);
        gridPane.add(btn1, 1, 1);
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage 
        stage.setTitle("Logged in as Manager");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();

    }

    public void contcustomer(Stage stage) {
        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();
        btn1.setText("Deposit");
        btn2.setText("Withdraw");
        btn3.setText("See balance");
        btn4.setText("Purchase");
        Text text1 = new Text();
        text1.setText("Choose Action");
        text1.setFont(new Font(15));
        Button back = new Button();
        back.setText("Logout");
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                start(stage);
            }

        });
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Contdeposit(stage);
            }

        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Contwithdraw(stage);
            }

        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Contseebalance(stage);
            }

        });
        btn4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Contpurchase(stage);
            }

        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(btn1, 0, 1);
        gridPane.add(btn2, 0, 2);
        gridPane.add(btn3, 1, 1);
        gridPane.add(btn4, 1, 2);
        gridPane.add(back, 4, 3);

        //Creating a scene object 
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage 
        stage.setTitle("Logged in as " + Customer.customers.get(j).username);

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();
    }

    public void Contdeposit(Stage stage) {
        Text text1 = new Text("Enter amount:");
        TextField textField1 = new TextField();
        Button back = new Button();
        back.setText("Back");
        Button logout = new Button();
        logout.setText("Logout");
        Button btn1 = new Button();
        btn1.setText("Submit");
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Customer.customers.get(j).balance = Customer.customers.get(j).balance + Double.parseDouble(textField1.getText());
                Customer.customers.get(j).updateAcc(Customer.customers.get(j).acc);
                successMsg2(stage);
            }

        });
        logout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                start(stage);
            }

        });
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                contcustomer(stage);
            }

        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(back, 4, 3);
        gridPane.add(logout, 5, 3);
        gridPane.add(btn1, 1, 1);


        Scene scene = new Scene(gridPane);

        //Setting title to the Stage 
        stage.setTitle("Logged in as Customer");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();

    }

    public void Contwithdraw(Stage stage) {
        Text text1 = new Text("Enter amount:");
        TextField textField1 = new TextField();
        Button back = new Button();
        back.setText("Back");
        Button logout = new Button();
        logout.setText("Logout");
        Button btn1 = new Button();
        btn1.setText("Submit");
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Customer.customers.get(j).balance = Customer.customers.get(j).balance - Double.parseDouble(textField1.getText());
                Customer.customers.get(j).updateAcc(Customer.customers.get(j).acc);
                successMsg2(stage);
            }

        });
        logout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                start(stage);
            }

        });
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                contcustomer(stage);
            }

        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(back, 4, 3);
        gridPane.add(logout, 5, 3);
        gridPane.add(btn1, 1, 1);

        Scene scene = new Scene(gridPane);
        

        //Setting title to the Stage 
        stage.setTitle("Logged in as Customer");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();

    }

    public void Contseebalance(Stage stage) {
        double balance = Customer.customers.get(j).balance;
        Text text1 = new Text("$" + balance);
        Text text2 = new Text("Balance:");
        Button back = new Button();
        back.setText("Back");
        Button logout = new Button();
        logout.setText("Logout");
        logout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                start(stage);
            }

        });
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                contcustomer(stage);
            }

        });
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text2, 0, 0);
        gridPane.add(text1, 0, 1);
        gridPane.add(back, 4, 3);
        gridPane.add(logout, 5, 3);

        Scene scene = new Scene(gridPane);

        //Setting title to the Stage 
        stage.setTitle("Logged in as Customer");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();

    }

    public void successMsg(Stage stage) {
        Text text1 = new Text();
        text1.setText("Success!");
        text1.setFont(new Font(20));
        Button btn1 = new Button();
        btn1.setText("Ok");
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                contmanager(stage);
            }

        });
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(btn1, 3, 1);

        Scene scene = new Scene(gridPane);

        //Setting title to the Stage 
        stage.setTitle("Success");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();
    }
    public void successMsg2(Stage stage) {
        Text text1 = new Text();
        text1.setText("Success!");
        text1.setFont(new Font(20));
        Button btn1 = new Button();
        btn1.setText("Ok");
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                contcustomer(stage);
            }

        });
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(btn1, 3, 1);

        Scene scene = new Scene(gridPane);

        //Setting title to the Stage 
        stage.setTitle("Success");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();
    }
    public void Contpurchase(Stage stage){
        Text text1 = new Text("Enter purchase amount:");
        TextField textField1 = new TextField();
        Button back = new Button();
        back.setText("Back");
        Button logout = new Button();
        logout.setText("Logout");
        Button btn1 = new Button();
        btn1.setText("Submit");
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(Double.parseDouble(textField1.getText())> 50){
                   
                    if(Customer.customers.get(j).acc instanceof Silver)
                        Customer.customers.get(j).balance = Customer.customers.get(j).balance - 20;
                    if(Customer.customers.get(j).acc instanceof Gold)
                        Customer.customers.get(j).balance = Customer.customers.get(j).balance - 10;
                    Customer.customers.get(j).amt = (Double.parseDouble(textField1.getText()));
                    Customer.customers.get(j).balance = Customer.customers.get(j).balance - Customer.customers.get(j).amt;
                    Customer.customers.get(j).updateAcc(Customer.customers.get(j).acc);
                    
                successMsg2(stage);
                }
            }

        });
        logout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                start(stage);
            }

        });
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                contcustomer(stage);
            }

        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(back, 4, 3);
        gridPane.add(logout, 5, 3);
        gridPane.add(btn1, 1, 1);

        Scene scene = new Scene(gridPane);
        

        //Setting title to the Stage 
        stage.setTitle("Logged in as Customer");

        //Adding scene to the stage 
        stage.setScene(scene);

        //Displaying the contents of the stage 
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
