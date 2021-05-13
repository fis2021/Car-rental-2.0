module rc.project {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
//    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires mysql.connector.java;
//    requires mysql.java.connector;
//    requires org.hibernate.orm.core;

    opens rc.project to javafx.fxml;
    exports rc.project;
    //exports rc.project.service;
    //opens rc.project.service to javafx.fxml;
    //exports rc.project.controllers;
    //opens rc.project.controllers to javafx.fxml;

}