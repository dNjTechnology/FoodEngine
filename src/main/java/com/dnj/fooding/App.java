package com.dnj.fooding;

import com.dnj.fooding.model.User;
import com.dnj.fooding.support.HibernateUtil;
import com.dnj.fooding.support.RealTimeSystem;
import com.dnj.fooding.support.UsbDetection;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static User currentUser;
    public static Boolean running;
    public static String logInBy;
    public static Boolean isLoggedin;
    public static Boolean isTableInspectOn;
    public static Stage subActiveStage;
    @Override
    public void start(Stage stage) throws IOException {
        //DataBaseConnection con=new DataBaseConnection();
        HibernateUtil.getSessionFactory();
        running=true;
         isTableInspectOn=false;
        Thread t = new Thread(new UsbDetection());
        t.start();
        
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
        stage.setResizable(false);
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
 public static Object loadFXMLPanel(String fxml) {
        try {
            URL fileURL=App.class.getResource(fxml+".fxml");
            if(new FXMLLoader().load(fileURL) instanceof ScrollPane){
               ScrollPane pane = new FXMLLoader().load(fileURL); 
               return pane;
            }
            
            Pane pane = new FXMLLoader().load(fileURL);
            return pane;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}