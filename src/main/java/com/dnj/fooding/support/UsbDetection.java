package com.dnj.fooding.support;

import com.dnj.fooding.App;
import com.dnj.fooding.controller.LogindialogController;
import com.dnj.fooding.dao.LoginDao;
import com.dnj.fooding.exeptions.UserNotFoundException;
import com.dnj.fooding.model.User;
import com.dnj.fooding.service.LoginService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class UsbDetection implements Runnable {

   private static int logInFlag=0;
    public static void startDetection() {
        
      //here the usb drive names are named as E,F,G,H in the system
      String[] drive_name = new String[]{ "E", "F", "G", "H", "I" ,"J","K", "L","M", "N","D"};
      //here we initialize an array for the usb that is to be inserted
      File[] usb = new File[drive_name.length];
      //if the usb is detected then it is assigned True else False
      boolean[] usb_detected = new boolean[drive_name.length];
 
      // in below loop we append :/ to the drive names because E:/ D:/  
      //and then the corresponding drive is searched with the help of canRead() method
       
 
    for ( int i = 0; i < drive_name.length; ++i )
    {
         usb[i] = new File(drive_name[i]+":/");
         //This method determines whether the program can read the file denoted by the mentioned path name
         usb_detected[i] = usb[i].canRead();
    }
 
System.out.println("Insert USB");

detect(usb,drive_name,usb_detected);


  }
    
    
    
   public static void detect(File[] usb,String[] drive_name,boolean[] usb_detected) {
    boolean isEjected = false;
    while(App.running) {
        //the following loop is iterated to find the usb inserted
        for (int i = 0; i < drive_name.length; ++i) {
            boolean if_detected;
            if_detected = usb[i].canRead();

            if (if_detected != usb_detected[i]) {
                if (if_detected && (App.currentUser == null || App.isLoggedin == false)) {
                    System.out.println("USB "+drive_name[i]+" detected ");

                    usb_detected[i] = if_detected;
                    logInFlag++;
                    readCredFromUSB(usb[i]);
                    while(App.running) {
                        if_detected = usb[i].canRead();
                        if (!if_detected) {
                            System.out.println("USB "+drive_name[i]+" ejected ");
                            isEjected = true;
                            break;
                        }
                    }
                }
            }
        }
        if (isEjected) {
            break;
        }
    }

    // Call Platform.runLater() method outside the while loop
    if (isEjected) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (App.logInBy.equals("USB_KEY")) {
                    Alert a=new Alert(Alert.AlertType.ERROR);
                    a.setContentText("USB ejected, Logging out....");
                    a.setTitle("Security");
                    a.setHeaderText("Log out");
                    a.show();
                    logInFlag=0;
                    LogindialogController.logOutUsbMode();
                    a.close();
                }
            }
        });
    }
    if(App.running){
    startDetection();  
    }
}
public static void readCredFromUSB(final File folder){
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            readCredFromUSB(fileEntry);
        } else {
            FileInputStream file = null;
            try {
                System.out.println(fileEntry.getName());
                if(fileEntry.getName().equalsIgnoreCase("restro365key.ini")){
                Properties properties = new Properties();
                file = new FileInputStream(fileEntry.getAbsolutePath());
                properties.load(file);
                file.close();
                String userid = AESEncryption.decrypt(properties.getProperty("userid"));
                String password = AESEncryption.decrypt(properties.getProperty("passkey"));
                
            LogindialogController.passwordforloginstatic.setText(password);
            LogindialogController.useridforloginstatic.setText(userid);
            loginFinal();
           
            break;
                }
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(UsbDetection.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex) {
                Logger.getLogger(UsbDetection.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
        }
    }
}

    private static void loginFinal(){
        Platform.runLater(new Runnable() {
    @Override
    public void run() {
        Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("USB Login");
                a.setContentText("Logging in via USB Key.");
                a.show();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(UsbDetection.class.getName()).log(Level.SEVERE, null, ex);
        }
        LogindialogController.usbLogin();
        a.close();
        
    }
});
    }

    @Override
    public void run() {
        try{
       UsbDetection.startDetection();
     
            
        }catch(Exception e){
            
        }
    }
    
}