package com.dnj.fooding;

import com.dnj.fooding.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static User currentUser;

    @Override
    public void start(Stage stage) throws IOException {
        //DataBaseConnection con=new DataBaseConnection();
        
        
        this.stage=stage;
        scene = new Scene(loadFXML("logindialog"), 640, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
       
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
public static Stage getStage(){
    return stage;
}
public static Parent setNewStage(String view) {
    Parent p=null;
       try{
        p=loadFXML(view); 
        Stage stage=new Stage();
        scene = new Scene(p, 640, 480);
        stage.setScene(scene);
        stage.setResizable(true);
        //stage.setFullScreen(true);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(true);
        stage.show();
        
        App.stage.close();
        App.stage=stage;
       }
       catch(Exception e){
           e.printStackTrace();
       }
       return p; 
    }
 public static Pane loadFXMLPanel(String fxml) {
        try {
            URL fileURL=App.class.getResource(fxml+".fxml");
            Pane pane = new FXMLLoader().load(fileURL);
            return pane;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}