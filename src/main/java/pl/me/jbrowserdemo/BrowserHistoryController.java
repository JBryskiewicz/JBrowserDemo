package pl.me.jbrowserdemo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.web.WebHistory;
import javafx.stage.Stage;

import java.io.IOException;

public class BrowserHistoryController {

    @FXML
    private Label historyEntry;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void browsingHistory(ObservableList<WebHistory.Entry> entries) {
        StringBuilder sb = new StringBuilder();
        for (WebHistory.Entry entry : entries) {
            sb.append("-").append(entry).append("\n" + "\n");
        }
        historyEntry.setText(sb.toString());
    }

    public void displayEntry() {
        //TODO click on entry in browsing history to visit website (create dynamic fields)
    }

    public void displayBrowser(ActionEvent event) throws IOException{

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        ObservableList<WebHistory.Entry> entries = (ObservableList<WebHistory.Entry>) stage.getUserData();
        int count = entries.size();
        String url = entries.stream()
                .map(e -> e.getUrl())
                .skip(count - 1)
                .findFirst()
                .get();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage.fxml"));
            root = loader.load();

            HomepageController homepageController = loader.getController();
            homepageController.returnToPage(url);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }
}
