package pl.me.jbrowserdemo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    private static final String HOMEPAGE = "www.google.com";

    @FXML
    private WebView webView;
    @FXML
    private TextField urlField;

    private WebEngine engine;
    private WebHistory webHistory;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = webView.getEngine();
        urlField.setText(HOMEPAGE);
        loadPage();
    }

    public void loadPage() {
        engine.load("http://" + urlField.getText());
    }

    public void returnToPage(String url) {
        engine.load(url);
        urlField.setText(url);
    }

    public void reloadPage() {
        engine.reload();
    }

    public void displayHistory(ActionEvent event) throws IOException {

        ObservableList<WebHistory.Entry> entries = engine.getHistory().getEntries();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        webView.getEngine().load(null);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("browser_history.fxml"));
        root = loader.load();

        //example of how to pass required data using UserData for other controller
        stage.setUserData(entries);

        //example of how to pass required data only to one specific method in other controller
        BrowserHistoryController historyController = loader.getController();
        historyController.browsingHistory(entries);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void previousPage() {
        try {
            webHistory = engine.getHistory();
            ObservableList<WebHistory.Entry> entries = webHistory.getEntries();
            webHistory.go(-1);
            urlField.setText(entries.get(webHistory.getCurrentIndex()).getUrl());
        } catch (RuntimeException e) {

        }
    }

    public void nextPage() {
        try {
            webHistory = engine.getHistory();
            ObservableList<WebHistory.Entry> entries = webHistory.getEntries();
            webHistory.go(1);
            urlField.setText(entries.get(webHistory.getCurrentIndex()).getUrl());
        } catch (RuntimeException e) {

        }
    }
}
