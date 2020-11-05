package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AddItemFormController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public JFXTextField bookNameTextField;

    @FXML
    private JFXButton saveNoteButton;

    @FXML
    public JFXTextArea descriptionTextField;

    ArrayList<String> nameBook = new ArrayList<>();
    ArrayList<String> nameDesc = new ArrayList<>();

    @FXML
    private JFXListView<Label> list;

    @FXML
    void displaySelected(MouseEvent event) {
        //String movie = String.valueOf(list.getSelectionModel().getSelectedItems());
        int row = list.getSelectionModel().getSelectedIndex();
        descriptionTextField.setText(nameDesc.get(row));
        bookNameTextField.setText(nameBook.get(row));
    }
    @FXML
    void initialize() {
        saveNoteButton.setOnAction(actionEvent -> {

            addNotes();

        });
    }
    public void addNotes(){
        Notes notes = new Notes();
        String noteText = bookNameTextField.getText().trim();
        String noteDesc = descriptionTextField.getText().trim();
        if(nameBook.contains(noteText)){
            int h = nameBook.indexOf(noteText);
            nameDesc.set(h, noteDesc);
        }else {
            Label label = new Label(noteText);
            list.getItems().add(label);
            nameBook.add(noteText);
            nameDesc.add(noteDesc);
            notes.setNote(noteText);
        }
        bookNameTextField.setText("");
        descriptionTextField.setText("");
    }
}
