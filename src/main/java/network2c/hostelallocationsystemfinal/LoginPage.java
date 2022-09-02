package network2c.hostelallocationsystemfinal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Boolean.FALSE;

public class LoginPage implements Initializable {


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
    private Label labelForgotPass;

    @FXML
    Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event, usernameTextField.getText(),passwordPasswordField.getText());
            }
        });


    }



    public void LoginButtonOnAction(ActionEvent e) throws IOException {

        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel2.setText("Please enter details  ");
        }


    }
    public void validateLogin() {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM pocbase.user_login WHERE UserName = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";
        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    //loginMessageLabel2.setText("Login Successful");
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
    public void labelMouseMoved(javafx.scene.input.MouseEvent event){
        labelForgotPass.setCursor(javafx.scene.Cursor.HAND);
    }
   public void labelMouseMovedpressed(javafx.scene.input.MouseEvent event){
        LoginForgotPasswordPage loginForgotPasswordPage = new LoginForgotPasswordPage();
        this.setVisible(true);
   }

    private void setVisible(boolean b) {
    }


    public void createAccountForm() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(RegisterPage.class.getResource("userLoginDashboard.fxml"));
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