package sample;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton loginSignupButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        String loginText = loginUsername.getText().trim();
        String loginPwd = loginPassword.getText().trim();

        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPwd);

        loginButton.setOnAction(actionEvent -> {
            ResultSet userRow = databaseHandler.getUser(user);
            int count = 0;

            try {
                while (userRow.next()) {
                    count++;
                } if (count == 1) {
                    System.out.println("Login Successful!");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        });

        loginSignupButton.setOnAction(actionEvent -> {
            loginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

    }
}
