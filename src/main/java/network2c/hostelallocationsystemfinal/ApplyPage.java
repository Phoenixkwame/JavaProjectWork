package network2c.hostelallocationsystemfinal;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

public class ApplyPage implements Initializable {
    List<String> lstFile;
    List<String> lstFiles;


    @FXML
    private Button btnApplyNow;
    @FXML
    private Button file_uploadButton;

    @FXML
    private Button file_uploadButton1;

    @FXML
    private AnchorPane panelC;

    @FXML
    private Label label_fileChooser;

    @FXML
    private Label label_fileChooser1;


    @FXML
    private ComboBox<String> hostelName;

    @FXML
    private ComboBox<String> genderType;

    @FXML
    private ComboBox<String> roomType;

    @FXML
    private Text loadingText;

    @FXML
    private ProgressBar progressIndicator;

    @FXML
    private Button btnClose;

    ProgressPage.LoadingScreen loadingScreen;


    final ObservableList options = FXCollections.observableArrayList();


    @FXML
    void ClickToSendApplication (ActionEvent event) throws IOException {
        if (!genderType.getItems().isEmpty() && !hostelName.getItems().isEmpty() && !roomType.getItems().isEmpty() && !label_fileChooser.getText().isBlank() && !label_fileChooser1.getText().isBlank()) {
            System.out.println("Apply button Clicked");
                registerUser();


                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("progressPage.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();


        } else {
            System.out.println("Error enter details");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Select details to continue ");
            alert1.show();

        }


    }

    @FXML
    void fileUpload(ActionEvent event) {
        FileChooser file = new FileChooser();
        file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Files", lstFile));
        File f = file.showOpenDialog(null);

        if (f != null) {
            label_fileChooser.setText("Selected File::" + f.getAbsolutePath());
        }
    }
    @FXML
        void fileUpload1(ActionEvent event){
            FileChooser file1 = new FileChooser();
            file1.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", lstFiles));
            File fs = file1.showOpenDialog(null);

            if (fs != null) {
                label_fileChooser1.setText("Selected File::" + fs.getAbsolutePath());
            }


        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lstFile = new ArrayList<>();
        lstFile.add("*.doc");
        lstFile.add("*.docx");
        lstFile.add("*.DOC");
        lstFile.add("*.DOCX");

        lstFiles = new ArrayList<>();
        lstFiles.add("*.jpg");
        lstFiles.add("*.JPG");

        roomType.getItems().addAll("2 in a room", "3 in a room", "4 in a room", "6 in a room");
        hostelName.getItems().addAll("GET FUND HOSTEL", "SIRJOE HOSTEL", "ADEHYIE HOSTEL", "LORDS HOSTEL", "PLANTAIN HOSTEL", "UNISEX HOSTEL", "UNIVERSAL HOSTEL(ANIMAL KINGDOM)", "CENTRAL HOSTEL", "SUPREME HOSTEL");
        genderType.getItems().addAll("MALE", "FEMALE");


        loadingScreen = new ProgressPage.LoadingScreen(progressIndicator, loadingText);

    }
        public void registerUser() {
            DBConnection connectNow = new DBConnection();
            Connection connectDB = connectNow.getConnection();

            ComboBox comboBox = new ComboBox(options);

            String hostelname = hostelName.getSelectionModel().getSelectedItem();
            String gender = genderType.getSelectionModel().getSelectedItem();
            String roomtype = roomType.getSelectionModel().getSelectedItem();
            String file = file_uploadButton.getText();
            String passportpic = file_uploadButton.getText();

            String insertFields = "INSERT INTO pocbase.user_apply (hostelname, gender,roomtype, file, passportpic) VALUES('";
            String insertValues = hostelname + "','" + gender + "','" + roomtype + "','" + file + "','" + passportpic + "')";
            String insertToApply = insertFields + insertValues;

            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToApply);
                createAccountForm();


            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }


        }
    public void onClickBackBeforeApply(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("beforeApply.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

        public void createAccountForm() {
            try {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Your application is successfully! click send and Go back to dashboard");
                alert.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }

    }

