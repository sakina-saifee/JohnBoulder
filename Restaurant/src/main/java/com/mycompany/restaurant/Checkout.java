package com.mycompany.restaurant;

import com.mycompany.restaurant.Order.Working.Item;
import com.mycompany.restaurant.Order.Working.Order;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Checkout extends Application {

    @Override
    public void start(Stage stage) {
        StackPane sp = new StackPane();
        Setup.setStage(sp, stage);
        stage.setWidth(450);
        stage.setHeight(370);
        stage.setResizable(false);
        Button gobackbtn = new Button("GO BACK");
        gobackbtn.setTranslateX(50);
        gobackbtn.setTranslateY(10);
        gobackbtn.setMinWidth(100);
        gobackbtn.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        gobackbtn.setTextFill(Color.DARKGREEN);

        Label lb1 = new Label("TOTAL BILL");
        lb1.setFont(Font.font("Forte", FontWeight.MEDIUM, 18));
        lb1.setTextFill(Color.GOLDENROD);

        Label lb2 = new Label("     $BILL  ");
        lb2.setTranslateX(20);

        lb2.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
        lb2.setTextFill(Color.DARKGREEN);

        VBox vb0 = new VBox();
        vb0.getChildren().addAll(lb1, lb2);
        vb0.setPadding(new Insets(200, 0, 0, 50));

        Button checkoutbtn = new Button("CHECKOUT");
        checkoutbtn.setTranslateX(55);
        checkoutbtn.setTranslateY(20);
        checkoutbtn.setMinWidth(100);
        checkoutbtn.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        checkoutbtn.setTextFill(Color.DARKGREEN);

        VBox vb1 = new VBox();
        vb1.getChildren().addAll(gobackbtn, vb0, checkoutbtn);
        ListView listview = new ListView();
        Order order = Working.order.get(Working.selectedTable);
        if (order.getItems() != null) {
            //items : order.getItems()
            for (Item i : order.getItems()) {
                for (String key : i.quantity.keySet()) {
                    listview.getItems().add(i.name + " (" + key + ")  Cost: " + i.quantity.get(key));
                }
            }
            lb2.setText("RS." + order.getTotal() + "  ");
        }

        HBox hb1 = new HBox();
        hb1.getChildren().addAll(listview, vb1);
        sp.getChildren().addAll(hb1);

        gobackbtn.setOnAction(e -> {
            MainMenu mm = new MainMenu();
            mm.start(stage);
        });

        checkoutbtn.setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("ORDER COMPLETE!");
            a.showAndWait();
            Working.orderComplete();
            MainMenu mm = new MainMenu();
            mm.start(stage);
        });

        var scene = new Scene(sp);
        stage.setScene(scene);
        stage.show();
    }

}
