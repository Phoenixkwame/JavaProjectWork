package network2c.hostelallocationsystemfinal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class UserAdminDashboard implements Initializable {
    @FXML
    private Button button_logout;

    @FXML
    private Label label_welcome;

    @FXML
    private Label expressQuote2;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnMessages;

    @FXML
    private Button btnChat;

    @FXML
    private Button btnSettings;

    @FXML
    private Pane pnlStatus;

    @FXML
    private Label labelStatusMini;

    @FXML
    private Label labelStatus;

    @FXML
    private Label btnClose;

    @FXML
    private GridPane panelChat;

    @FXML
    private GridPane panelDashboard;

    @FXML
    private GridPane panelMessages;

    @FXML
    private GridPane panelSettings;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

      button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            DBUtils.changeScene(event, "loginPage.fxml", "Log in", null);
            }

        });

    }
    public void onClickBeforeApplyPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("beforeApply.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void onClickToHostelName(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hostelNames.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void setUserInformation(String username){
        label_welcome.setText("Welcome" + " " + "Admin" + username + "!");
        expressQuote2.setText( "Thank You! Enjoy you Stay " + username);
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnDashboard) {
            labelStatusMini.setText("/home/dashboard");
            labelStatus.setText("Dashboard");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 43, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            panelDashboard.toFront();
        } else {

        }



        if (event.getSource() == btnChat) {
            labelStatusMini.setText("/home/chat");
            labelStatus.setText("Send Messages");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43, 99, 63), CornerRadii.EMPTY, Insets.EMPTY)));
            panelChat.toFront();

        } else if (event.getSource() == btnSettings) {
            labelStatusMini.setText("/home/settings");
            labelStatus.setText("Settings");
            pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(99, 43, 63), CornerRadii.EMPTY, Insets.EMPTY)));
            panelSettings.toFront();

        }

    }

    public void handleClose(javafx.scene.input.MouseEvent event) {
        if (event.getSource() ==btnClose)
        System.exit(0);
    }

}