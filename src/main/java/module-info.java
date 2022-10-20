module pl.me.jbrowserdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens pl.me.jbrowserdemo to javafx.fxml;
    exports pl.me.jbrowserdemo;
}