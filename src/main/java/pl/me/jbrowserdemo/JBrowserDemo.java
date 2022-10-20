package pl.me.jbrowserdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JBrowserDemo extends Application {

    private static final int WIDTH = 1800;
    private static final int HEIGHT = 900;
    private static final String BROWSER_NAME = "JBrowser Demo";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JBrowserDemo.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(BROWSER_NAME);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}