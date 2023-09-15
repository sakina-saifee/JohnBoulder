package com.mycompany.restaurant;

import com.mycompany.restaurant.Order.Working.Item;
import com.mycompany.restaurant.Order.Working.ItemList;
import com.mycompany.restaurant.Order.Working.Order;
import java.util.HashMap;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * JavaFX AddItemsMenu
 */
public class AddItemsMenu extends Application {

    static ItemList il = new ItemList();

    @Override
    public void start(Stage stage) {
        StackPane sp = new StackPane();
        Setup.setStage(sp, stage);
        stage.setWidth(850);
        stage.setHeight(460);

        ScrollPane scroll = new ScrollPane(sp);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Label lb0 = new Label("Order Menu: ");
        lb0.setFont(Font.font("Forte", FontWeight.MEDIUM, 35));
        lb0.setPadding(new Insets(30, 0, 0, 40));
        lb0.setTextFill(Color.GOLDENROD);
        HBox head = new HBox();
        head.getChildren().addAll(lb0);

        //Dynamically adding items using itemlist and item class
        int list = il.items.size();

        CheckBox[] itemName = new CheckBox[list];
        TextField[] qty = new TextField[list];
        ToggleGroup[] radioGroup = new ToggleGroup[list];
        HBox[] radioBox = new HBox[list];
        HBox[] elementBox = new HBox[list];

        int i = 0;
        
        //for (Wo datatype jo collection say arahi : Wo collection)
        for (Item it : il.items) {
            itemName[i] = new CheckBox(it.name);
            itemName[i].setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
            itemName[i].setTextFill(Color.DARKGREEN);
            itemName[i].setPrefWidth(100);

            qty[i] = new TextField();
            qty[i].setPromptText("Qty");
            qty[i].setPrefWidth(40);
            qty[i].setPrefHeight(-10);
//            UnaryOperator<Change> integerFilter = change -> {
//                String newText = change.getControlNewText();
//                if (newText.matches("-?([1-9][0-9]*)?")) {
//                    return change;
//                }
//                return null;
//            };
//            qty[i].setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
            
            radioBox[i] = new HBox();

            radioGroup[i] = new ToggleGroup();
            HashMap<String, Double> quantity = il.items.get(i).quantity;
            RadioButton[] qBox = new RadioButton[quantity.size()];
            int j = 0;
            for (String key : quantity.keySet()) {
                qBox[j] = new RadioButton(key);
                qBox[j].setTooltip(new Tooltip("(Rs." + quantity.get(key).toString() + ")"));
                qBox[j].setTranslateX(j * 40);
                qBox[j].setPrefWidth(150);
                qBox[j].setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
                qBox[j].setTextFill(Color.DARKGREEN);
                qBox[j].setToggleGroup(radioGroup[i]);

                qBox[0].setSelected(true);

                radioBox[i].getChildren().add(qBox[j]);

                j++;
            }
            radioBox[i].setTranslateX(50);

            elementBox[i] = new HBox();
            elementBox[i].getChildren().addAll(itemName[i], qty[i], radioBox[i]);
            elementBox[i].setPadding(new Insets(30, 0, 0, 40));

            i++;
        }

        Label lb1 = new Label("COST LABEL: ");
        lb1.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
        lb1.setTranslateY(5);
        lb1.setTextFill(Color.DARKGREEN);
        Label lb2 = new Label("COST");
        lb2.setFont(Font.font("Forte", FontWeight.MEDIUM, 21));
        lb2.setTextFill(Color.GOLDENROD);

        HBox hb3 = new HBox();
        hb3.getChildren().addAll(lb1, lb2);
        hb3.setPadding(new Insets(70, 0, 5, 170));
        hb3.setTranslateX(70);

        Button addbtn = new Button("ADD ITEM");
        addbtn.setTranslateX(60);
        addbtn.setTranslateY(70);
        addbtn.setMinWidth(100);
        addbtn.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        addbtn.setTextFill(Color.DARKGREEN);
        

        final CheckBox[] cb = itemName;
        addbtn.setOnAction(e -> {
            for (int k = 0; k < cb.length; k++) {
                if (cb[k].isSelected()) {
                    int qt = Integer.parseInt(qty[k].getText());
                    RadioButton rb = (RadioButton) radioGroup[k].getSelectedToggle();
                    for (int q = 0; q < qt; q++) {
                        updateOrderList(cb[k].getText(), rb.getText());
                    }
                }
            }
        });

        Button gobackbtn = new Button("GO BACK");
        gobackbtn.setTranslateX(-230);
        gobackbtn.setTranslateY(70);
        gobackbtn.setMinWidth(100);
        gobackbtn.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        gobackbtn.setTextFill(Color.DARKGREEN);

        gobackbtn.setOnAction(e -> {
            OrderListMenu olm = new OrderListMenu();
            olm.start(stage);
        });

        HBox hb4 = new HBox();
        hb4.getChildren().addAll(addbtn, hb3, gobackbtn);

        VBox vb0 = new VBox();
        vb0.getChildren().add(head);
        for (i = 0; i < elementBox.length; i++) {
            vb0.getChildren().add(elementBox[i]);
        }
        vb0.getChildren().add(hb4);

        sp.getChildren().addAll(vb0);
        Scene scene = new Scene(scroll);
        stage.setScene(scene);
        stage.show();
    }

    private static void updateOrderList(String itemName, String quantName) {
        double cost = 0;
        //items : il.items
        //large
        //small
        //regular
        for (Item items : il.items) {
            if (items.name.equals(itemName)) {
                cost = items.quantity.get(quantName);
                break;
            }
        }
        HashMap<String, Double> details = new HashMap<>();
        details.put(quantName, cost);
        Item i = new Item(itemName, details);
        Working.order.get(Working.selectedTable).addItem(i);
//        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
//        String fullCart = "Present Cart: \n";
//        Order order = Working.order.get(Working.selectedTable);
//        for (Item item : order.getItems()) {
//            for (String key : item.quantity.keySet()) {
//                fullCart += item.name + " (" + key + ")";
//            }
//        }

//        System.out.println(fullCart);
    }
}
