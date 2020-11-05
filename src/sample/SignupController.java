package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField singUpFirstName;

    @FXML
    private JFXTextField signUpLastName;

    @FXML
    private JFXTextField signUpUserName;

    @FXML
    private JFXCheckBox signUpCheckBoxMale;

    @FXML
    private JFXCheckBox signUpCheckBoxFemale;

    @FXML
    private JFXTextField signUpLocation;

    @FXML
    private JFXPasswordField signUpPassword;

    @FXML
    private JFXButton signUpButton;

    @FXML
    void initialize() {
        signUpButton.setOnAction(actionEvent -> {
            createUser();
            signUpButton.getScene().getWindow().hide();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/sample/view/login.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


            /*
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

             */

        });

    }
    public void createUser(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String name = singUpFirstName.getText();
        String lastName = signUpLastName.getText();
        String userName = signUpUserName.getText();
        String password = signUpPassword.getText();
        String location = signUpLocation.getText();
        String gender = "";
        if(signUpCheckBoxMale.isSelected()){gender = "Male";}
        else gender="Female";
        User user = new User(name, lastName, userName, password, location, gender);
        databaseHandler.signUpUser(user);

    }
}
