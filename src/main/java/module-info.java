module com.dnj.fooding {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires javolution;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires pusher.http.java;
    requires pusher.java.client;
   requires javafx.webEmpty;
   requires javafx.web;
    
   
    opens com.dnj.fooding to javafx.fxml;
    opens com.dnj.fooding.model to javafx.fxml,org.hibernate.orm.core;
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
