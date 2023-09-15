package com.mycompany.restaurant;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author hitma
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setWidth(420);
        stage.setHeight(230);

        StackPane root = new StackPane();
        Setup.setStage(root, stage);

        HBox hb1 = new HBox();
        Label lb1 = new Label("Employee ID:   ");
        lb1.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
        lb1.setTextFill(Color.DARKGREEN);
        TextField tf1 = new TextField();
        tf1.setPromptText("Enter your ID");
        tf1.setFocusTraversable(false);
        hb1.getChildren().addAll(lb1, tf1);

        HBox hb2 = new HBox();
        Label lb2 = new Label("Password:        ");
        lb2.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 14));
        lb2.setTextFill(Color.DARKGREEN);
        PasswordField pf1 = new PasswordField();
        pf1.setPromptText("Enter your password");
        pf1.setFocusTraversable(false);
        hb2.getChildren().addAll(lb2, pf1);

        //For login button
        Button btn1 = new Button("LOGIN");
        btn1.setMinWidth(100);
        btn1.setTranslateX(100);
        btn1.setTranslateY(10);
        btn1.setFont(Font.font("72 Black", FontWeight.EXTRA_BOLD, 12));
        btn1.setTextFill(Color.DARKGREEN);

        VBox vb1 = new VBox(hb1, hb2, btn1);
        vb1.setSpacing(10);
        vb1.setPadding(new Insets(58, 58, 58, 80));

        root.getChildren().add(vb1);

        btn1.setOnAction(e -> {
            try {
                String id = tf1.getText();
                String pass = pf1.getText();
                Working work = new Working();
                work.emptyOrNullString(id, pass);
                if (work.getIdPassword(id, pass) == true) {
                    MainMenu mm = new MainMenu();
                    mm.start(stage);
                } else {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Inccorect ID or Password");
                    a.show();
                }
            } catch (ValidationException exp) {
                Alert a = new Alert(AlertType.ERROR);
                a.setHeaderText("Input Error");
                a.setContentText(exp.getMessage());
                a.show();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Working.addTables();
        launch();
    }

}
