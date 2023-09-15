/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

import com.mycompany.restaurant.Order.Working.Order;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author hitma
 */
public class MainMenu extends Application {

    @Override
    public void start(Stage stage) {

        stage.setWidth(490);
        stage.setHeight(255);

        StackPane root = new StackPane();
        Setup.setStage(root, stage);

        Label lb1 = new Label("Table Status:  ");
        //lb1.setFont(Font.font("Modern No. 20", FontWeight.BOLD, 15));
        lb1.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
        lb1.setTextFill(Color.DARKGREEN);
        Label lb2 = new Label("Table Status");
        lb2.setFont(Font.font("Forte", FontWeight.MEDIUM, 18));
        lb2.setTextFill(Color.GOLDENROD);

        Label lb3 = new Label("Order Status:   ");
        // lb3.setFont(Font.font("Modern No. 20", FontWeight.BOLD, 15));
        lb3.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
        lb3.setTextFill(Color.DARKGREEN);
        Label lb4 = new Label("Order Status");
        lb4.setFont(Font.font("Forte", FontWeight.MEDIUM, 18));
        lb4.setTextFill(Color.GOLDENROD);

        HBox hb1 = new HBox(lb1, lb2);

        hb1.setPadding(new Insets(80, 0, 0, 30));
        HBox hb2 = new HBox(lb3, lb4);
        hb2.setPadding(new Insets(0, 0, 0, 30));
        VBox vb_hb = new VBox();
        vb_hb.getChildren().addAll(hb1, hb2);
        vb_hb.setPadding(new Insets(10, 0, 70, 0));

        VBox vb1 = new VBox();
        ComboBox<String> combo = new ComboBox();
        ObservableList<String> list = combo.getItems();
        
        Working work = new Working();
     
        list.addAll(work.getTableNo());
        combo.setPromptText("Select Table");
        combo.setFocusTraversable(false);
        combo.setPrefSize(170, 10);

        vb1.getChildren().add(combo);
        vb1.setPadding(new Insets(76, 0, 0, 70));

        VBox vb_hb_combo = new VBox();
        vb_hb_combo.getChildren().addAll(vb1, vb_hb);

        Button btn1 = new Button("Check Out");
        btn1.setDisable(true);
        btn1.setPrefSize(110, 10);
        btn1.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        btn1.setTextFill(Color.DARKGREEN);
        Button btn2 = new Button("Order Menu");
        btn2.setPrefSize(110, 10);
        btn2.setDisable(true);
        btn2.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        btn2.setTextFill(Color.DARKGREEN);
        btn2.setOnAction(e -> {

            try {
                if (Working.order.get(Working.selectedTable) == null) {
                    Order order = new Order(Working.selectedTable);
                    Working.order.put(Working.selectedTable, order);
                }
                OrderListMenu olm = new OrderListMenu();
                olm.start(stage);
                stage.show();

            } catch (Exception ae) {

                ae.printStackTrace();

            }

        });
        Button btn3 = new Button("Table Booking");
        btn3.setPrefSize(110, 10);
        btn3.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        btn3.setTextFill(Color.DARKGREEN);

        VBox vb2 = new VBox(btn1, btn2, btn3);
        vb2.setSpacing(20);
        // vb2.setAlignment(Pos.BOTTOM_RIGHT);
        vb2.setPadding(new Insets(70, 30, 10, 90));

        HBox hb3 = new HBox(vb_hb_combo, vb2);
        root.getChildren().add(hb3);

        combo.setOnAction(e -> {
            Working.selectedTable = combo.getValue();
            lb2.setText(work.checkTableStatus());
            if (!work.checkTableStatus().equals("Empty")) {
                btn3.setDisable(true);
            } else {
                btn3.setDisable(false);
            }

            if (work.checkTableStatus().equals("Booked")) {
                btn2.setDisable(false);
            } else {
                btn2.setDisable(true);
            }

            if (Working.order.get(Working.selectedTable) != null) {
                Order ord = Working.order.get(Working.selectedTable);
                if (ord.getItems() != null) {
                    lb4.setText("Order Received");
                    btn1.setDisable(false);
                } else {
                    lb4.setText("Order Waiting");
                    btn1.setDisable(true);
                }
            } else {
                lb4.setText("Unavailable");
                    btn1.setDisable(true);
            }
        });

        btn3.setOnAction(e -> {
            try {
                if (combo.getValue() == null) {
                    throw new ValidationException("Please select a table.");
                }
                BookingMenu book = new BookingMenu();
                book.start(stage);
            } catch (ValidationException exp) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Selection Error");
                a.setContentText(exp.getMessage());
                a.show();
            }
        });
        
        btn1.setOnAction(e -> {
            Checkout ck = new Checkout();
            ck.start(stage);
        });

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
}
