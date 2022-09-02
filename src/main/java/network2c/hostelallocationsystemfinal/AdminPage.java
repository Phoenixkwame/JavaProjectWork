package network2c.hostelallocationsystemfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class AdminPage {


    public void onClickBackToLoginOptionPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginOption.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button cancelButton;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordPasswordField;
    @FXML
    private Label loginMessageLabel2;

    @FXML
    Button loginButton;

    public void LoginButtonOnAction(ActionEvent e) throws IOException {


        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel2.setText("Please enter details  ");
        }


    }


    public void cancelButtonAction(ActionEvent e) {
        javafx.stage.Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM pocbase.admin_account WHERE UserName = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel2.setText("Login Successful");
                    createAccountForm();

                } else {

                    loginMessageLabel2.setText("Invalid login, Please try again");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }
    }
        public void createAccountForm() {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(RegisterPage.class.getResource("userAdminDashboard.fxml"));
                Stage registerStage = new Stage();
                registerStage.initStyle(StageStyle.UNDECORATED);
                Scene scene = new Scene(fxmlLoader.load(), 874, 470);
                registerStage.setScene(scene);
                registerStage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }


}
