package com.dnj.fooding.support;

import com.dnj.fooding.App;
import com.dnj.fooding.controller.LogindialogController;
import com.dnj.fooding.dao.LoginDao;
import com.dnj.fooding.exeptions.UserNotFoundException;
import com.dnj.fooding.model.User;
import com.dnj.fooding.service.LoginService;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

public class UsbDetection1 {

   public static void main(String args[]) {
       starDetection();
   }
    public static void starDetection() {
        
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
    
    
    
    public static void detect(File[] usb,String[] drive_name,boolean[] usb_detected)
    { 
        boolean isEjected=false;
        while(true)
    {
        //the following loop is iterated to find the usb inserted
        for ( int i = 0; i < drive_name.length; ++i )
        {
            boolean if_detected;
            if_detected = usb[i].canRead();

            if (if_detected != usb_detected[i] )
                {
                    if ( if_detected){
                    System.out.println("USB "+drive_name[i]+" detected ");
 
                    usb_detected[i] = if_detected;
                    
                    while(true){
                       if_detected = usb[i].canRead();
                       if(!if_detected){
                           System.out.println("USB "+drive_name[i]+" ejected ");
                           isEjected=true;
 break;
                       }
                    }
                   
                    }
                }
 
        }
        
  if(isEjected){
                        break;
                    }
    }
     starDetection();   
    }
    
    
}