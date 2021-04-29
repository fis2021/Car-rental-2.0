module rc.project {
    requires javafx.controls;
    requires javafx.fxml;

    opens rc.project to javafx.fxml;
    exports rc.project;

}