/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author hitma
 */
public class Setup extends Pane {

    public static void setStage(Pane root, Stage stage) {
        try {
            String resourcePath = new File(".").getCanonicalPath();
            String absPath = resourcePath + "\\src\\main\\java\\com\\mycompany\\restaurant\\";
            
            Image image = new Image(new FileInputStream(absPath + "img3.png"));
            Image logo = new Image(new FileInputStream(absPath + "logo.PNG"));

            ImageView iview = new ImageView();
            iview.setImage(image);
            iview.setOpacity(0.15);
            iview.setFitWidth(stage.getWidth());
            iview.setFitHeight(stage.getHeight());

            root.getChildren().add(iview);
            stage.getIcons().add(logo);

            stage.setTitle("John Boulder's Restaurant");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
