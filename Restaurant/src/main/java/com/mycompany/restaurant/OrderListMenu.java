/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

import com.mycompany.restaurant.Order.Working.Item;
import com.mycompany.restaurant.Order.Working.Order;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
public class OrderListMenu extends Application {

    @Override
    public void start(Stage stage) {

        StackPane sp = new StackPane();

        Setup.setStage(sp, stage);
        stage.setWidth(490);
        stage.setHeight(425);

        Label lb1 = new Label("Selected Table");
        lb1.setFont(Font.font("Forte", FontWeight.MEDIUM, 25));
        lb1.setPadding(new Insets(50, 0, 0, 30));
        lb1.setTextFill(Color.DARKGREEN);

        Label lb2 = new Label(Working.selectedTable);
        lb2.setFont(Font.font("Modern No. 20", FontWeight.BOLD, 18));
        lb2.setPadding(new Insets(8, 0, 0, 60));
        lb2.setTextFill(Color.GOLDENROD);

        VBox vb0 = new VBox();
        vb0.getChildren().addAll(lb1, lb2);

        Button addbtn = new Button("ADD ITEM");
        addbtn.setTranslateX(60);
        addbtn.setTranslateY(160);
        addbtn.setMinWidth(100);
        addbtn.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        addbtn.setTextFill(Color.DARKGREEN);

//        Button delbtn = new Button("DELETE ITEM");
//        delbtn.setTranslateX(60);
//        delbtn.setTranslateY(180);
//        delbtn.setMinWidth(100);
//        delbtn.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
//        delbtn.setTextFill(Color.DARKGREEN);

        VBox vb1 = new VBox();
        vb1.getChildren().addAll(addbtn);

        ListView listview = new ListView();
        listview.setPrefWidth(300);
        listview.setPrefHeight(200);
        listview.setTranslateX(20);
        listview.setTranslateY(20);

        Order order = Working.order.get(Working.selectedTable);
        if (order.getItems() != null) {
            for (Item i : order.getItems()) {
                for (String key : i.quantity.keySet()) {
                    listview.getItems().add(i.name + " (" + key + ")");
                }
            }
        }

        VBox vb2 = new VBox();
        vb2.getChildren().addAll(vb0, listview);

        HBox hb0 = new HBox();
        hb0.getChildren().addAll(vb2, vb1);

        Button applybtn = new Button("APPLY");
        applybtn.setTranslateX(40);
        applybtn.setTranslateY(30);
        applybtn.setMinWidth(100);
        applybtn.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        applybtn.setTextFill(Color.DARKGREEN);

        Button gobackbtn = new Button("GO BACK");
        gobackbtn.setTranslateX(70);
        gobackbtn.setTranslateY(30);
        gobackbtn.setMinWidth(100);
        gobackbtn.setFont(Font.font("72 Black", 12));
        gobackbtn.setTextFill(Color.DARKGREEN);
        gobackbtn.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        gobackbtn.setTextFill(Color.DARKGREEN);

        HBox hb1 = new HBox();
        hb1.getChildren().addAll(gobackbtn);
        hb1.setPadding(new Insets(10, 0, 0, 10));

        VBox vb3 = new VBox();
        vb3.getChildren().addAll(vb2, hb1);

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(vb3, vb1);

        sp.getChildren().addAll(hb2);
        Scene scene = new Scene(sp);

        gobackbtn.setOnAction(e -> {

            try {

                MainMenu mm = new MainMenu();
                mm.start(stage);

            } catch (Exception ai) {

                ai.printStackTrace();
            }

        });

        addbtn.setOnAction(e -> {
            AddItemsMenu aim = new AddItemsMenu();
            try {
                aim.start(stage);
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });

        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();

    }
}
