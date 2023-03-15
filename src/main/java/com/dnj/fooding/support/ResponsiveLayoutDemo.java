/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dnj.fooding.support;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Animesh Samanta
 */
public class ResponsiveLayoutDemo extends Application {

  private Label textLabel;

  private ImageView imageView;

  private HBox hBox;

  private StackPane mainPane;

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.textLabel = new Label("Lorem ipsum dolor sit amet...");
    textLabel.setWrapText(true);

    this.imageView = new ImageView("http://goo.gl/1tEZxQ");
    imageView.setFitWidth(240);
    imageView.setPreserveRatio(true);

    hBox = new HBox();
    hBox.setSpacing(8);

    hBox.getChildren().addAll(imageView, textLabel);

    mainPane = new StackPane(hBox);
    mainPane.setPadding(new Insets(24));

    primaryStage.setScene(new Scene(mainPane));
    primaryStage.show();
  }

  public static void main(String... args) {
    launch(args);
  }
}
