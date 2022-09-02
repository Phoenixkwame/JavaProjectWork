package network2c.hostelallocationsystemfinal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProgressPage implements Initializable {
    @FXML
    private Text loadingText;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Button btnClose;

    LoadingScreen loadingScreen;

    public void onClickBackDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userLoginDashboard.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadingScreen = new LoadingScreen(progressIndicator, loadingText);
    }

    @FXML
    void cancel(ActionEvent event) {
        Thread thread = new Thread(loadingScreen);
        thread.setDaemon(true);
        thread.start();
    }



    public static class LoadingScreen implements Runnable {
        ProgressIndicator progressIndicator;
        Text loadingText;


        public LoadingScreen(ProgressIndicator progressIndicator, Text loadingText) {
            this.progressIndicator = progressIndicator;
            this.loadingText = loadingText;
        }

        public LoadingScreen(ProgressIndicator progressIndicator) {
            this.progressIndicator = progressIndicator;
        }

        @Override
        public void run() {
            while (progressIndicator.getProgress() <= 1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        progressIndicator.setProgress(progressIndicator.getProgress() + 0.1);
                    }
                });
                synchronized (this) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            loadingText.setText("Sending Complete");
        }
    }
}