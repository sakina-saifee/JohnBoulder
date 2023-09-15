/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class BookingMenu extends Application {

    @Override
    public void start(Stage stage) {
        stage.setWidth(550);
        stage.setHeight(289);

        StackPane sp = new StackPane();

        Setup.setStage(sp, stage);

        Label lb1 = new Label("Selected Table: ");
        lb1.setFont(Font.font("Forte", FontWeight.MEDIUM, 35));
        lb1.setPadding(new Insets(10, 0, 0, 30));
        lb1.setTextFill(Color.GOLDENROD);

        Label lb2 = new Label(Working.selectedTable);
        lb2.setFont(Font.font("Forte", FontWeight.EXTRA_BOLD, 23));
        lb2.setPadding(new Insets(8, 0, 0, 60));
        lb2.setTextFill(Color.DARKGREEN);

        VBox vb0 = new VBox();
        vb0.getChildren().addAll(lb1, lb2);

        Label lb3 = new Label("RESERVATION     ");
        lb3.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
        lb3.setTextFill(Color.DARKGREEN);

        Label lb4 = new Label("TIMINGS: ");
        lb4.setPadding(new Insets(8, 0, 0, 15));
        lb4.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
        lb4.setTextFill(Color.DARKGREEN);

        VBox vb1 = new VBox();
        vb1.getChildren().addAll(lb3, lb4);

        TextField tf1 = new TextField();
        tf1.setPromptText("HH:MM");
        tf1.setFocusTraversable(true);
        tf1.setMaxWidth(100);
        tf1.setDisable(true);

        HBox hb1 = new HBox();
        hb1.getChildren().addAll(vb1, tf1);
        hb1.setPadding(new Insets(40, 0, 0, 26));
        VBox vb2 = new VBox();
        vb2.getChildren().addAll(vb0, hb1);

        RadioButton rbtn1 = new RadioButton("Book Now! ");
        rbtn1.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
        rbtn1.setPadding(new Insets(0, 0, 10, 0));
        rbtn1.setTextFill(Color.DARKGREEN);

        RadioButton rbtn2 = new RadioButton("Reserve Table ");
        rbtn2.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
        rbtn2.setTextFill(Color.DARKGREEN);
        
       

        VBox vb3 = new VBox();
        vb3.getChildren().addAll(rbtn1, rbtn2);
        vb3.setPadding(new Insets(100, 0, 0, 60));

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(vb2, vb3);

        //ImageView iview = new ImageView(new Image("com.mycompany.restaurant/table.jpg"));
        Button booktablebtn = new Button("BOOK TABLE");
        booktablebtn.setTranslateX(40);
        booktablebtn.setTranslateY(30);
        booktablebtn.setMinWidth(100);
        booktablebtn.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        booktablebtn.setTextFill(Color.DARKGREEN);
        
        booktablebtn.setDisable(true);

        Button gobackbtn = new Button("GO BACK");
        gobackbtn.setTranslateX(70);
        gobackbtn.setTranslateY(30);
        gobackbtn.setMinWidth(100);
        gobackbtn.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        gobackbtn.setTextFill(Color.DARKGREEN);
        gobackbtn.setOnAction(e -> {

            try {

                MainMenu mm = new MainMenu();
                mm.start(stage);
                stage.show();

            } catch (Exception ax) {
                ax.printStackTrace();
            }

        });

        HBox hbbtn = new HBox();
        hbbtn.getChildren().addAll(booktablebtn, gobackbtn);

        ToggleGroup tg = new ToggleGroup();
        tg.getToggles().addAll(rbtn1, rbtn2);

        VBox vb4 = new VBox();
        vb4.getChildren().addAll(hb2, hbbtn);

        sp.getChildren().addAll(vb4);

        booktablebtn.setOnAction(e -> {
            Working work = new Working();
            RadioButton rb = (RadioButton) tg.getSelectedToggle();
            if (rb.getText().equals("Reserve Table ")) {
                try {
                    work.emptyOrNullString(tf1.getText());
                    work.timeFormat(tf1.getText());
                    work.bookTable(tf1.getText());
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Table Reserved!");
                    a.show();
                    MainMenu mm = new MainMenu();
                    mm.start(stage);
                } catch (ValidationException exp) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setHeaderText("Input Error");
                    a.setContentText(exp.getMessage());
                    a.show();
                } catch (Exception exp) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setHeaderText("Unknown Error");
                    a.setContentText(exp.getMessage());
                    a.show();
                }
            } else {
                work.bookTable();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Table Booked!");
                a.show();
                MainMenu mm = new MainMenu();
                mm.start(stage);
            }
        });

        tg.selectedToggleProperty().addListener(e -> {
            booktablebtn.setDisable(false);
            RadioButton rb = (RadioButton) tg.getSelectedToggle();
            if (rb.getText().equals("Reserve Table ")) {
                tf1.setDisable(false);
            } else {
                tf1.setDisable(true);
            }
        });

        Scene scene = new Scene(sp);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

    }

}
