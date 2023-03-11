module com.dnj.fooding {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
   
    
    
    opens com.dnj.fooding to javafx.fxml;
    opens com.dnj.fooding.model to javafx.fxml;
    opens com.dnj.fooding.controller to javafx.fxml;
    opens com.dnj.fooding.dao to javafx.fxml;
    opens com.dnj.fooding.service to javafx.fxml;
    opens com.dnj.fooding.support to javafx.fxml;
    exports com.dnj.fooding;
    exports com.dnj.fooding.model;
    exports com.dnj.fooding.controller;
    exports com.dnj.fooding.dao;
    exports com.dnj.fooding.service;
    exports com.dnj.fooding.support;
    
    
}
