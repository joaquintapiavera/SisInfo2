package com.example.demo.controller;

import com.example.demo.model.Sport;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField sportNameField;
    @FXML
    private TextField sportValueField;

    @FXML
    private TextArea statusArea;

    private Sport sport;

    @FXML
    protected void onRegisterButtonClick() {
        sport = new Sport(sportNameField.getText(), Integer.parseInt(sportValueField.getText()));
        if(sport.insertSport()){
            statusArea.setText("Sport has been successfully registered");
        } else {
            statusArea.setText("Sport could not be registered");
        }

    }
}
