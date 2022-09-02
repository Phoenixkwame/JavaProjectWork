package network2c.hostelallocationsystemfinal;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Handler;


public class  RegisterPage implements Initializable {
    @FXML
    private Button registerButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField dateTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField contactTextField;

    @FXML
    private TextField statTextField;

    @FXML
    private TextField sexTextField;





        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){



    }


    public void registerButtonOnAction(ActionEvent e) {
        if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
            confirmPasswordLabel.setText("");
        } else {
            confirmPasswordLabel.setText("Password does not match");
        }
    }

    public void onClickBackToLoginOptionPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("access-page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void closeButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser() {
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String address = addressTextField.getText();
        String dateOfBirth = dateTextField.getText();
        String email = emailTextField.getText();
        String country = countryTextField.getText();
        String contact = contactTextField.getText();
        String stat = statTextField.getText();
        String sex = sexTextField.getText();
        String password = setPasswordField.getText();


        String insertFields = "INSERT INTO pocbase.user_register (firstname, lastname,username, address, dateOfBirth, email,country, contact, stat, sex, password) VALUES('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + address + "','" + dateOfBirth + "','" + email + "','" + country + "','" + contact + "','" + stat + "','" + sex + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;







        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            registrationMessageLabel.setText("Your registration is successfully!");

            firstnameTextField.setText("");
            lastnameTextField.setText("");
            usernameTextField.setText("");
            addressTextField.setText("");
            dateTextField.setText("");
            emailTextField.setText("");
            countryTextField.setText("");
            contactTextField.setText("");
            statTextField.setText("");
            sexTextField.setText("");
            setPasswordField.setText("");
            confirmPasswordField.setText("");
            createAccountForm();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }




    }
    public void createAccountForm() {
        try {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Your registration is successfully! Go back to login");
            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    }
