/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.seonm.sudokusolver;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Mark Seon
 */
public class PrimaryController implements Initializable {
    @FXML
    GridPane parentGrid;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        GridPane subGrid = (GridPane) getGridCell(parentGrid, 0, 0);
//        TextField cell = (TextField) getGridCell(subGrid, 0, 0);
//        String text = cell.getText();
//        System.out.println(text);
        
    }    
    
    private Node getGridCell(GridPane gridPane, int row, int column) {
        final int ROW_COUNT = 3;
        return gridPane.getChildren().get((row * ROW_COUNT) + column);
    }
    
    @FXML
    private void validateCell(KeyEvent event) {
        final int MAX_LENGTH = 1;
        TextField textField = (TextField) event.getSource();
        String text = textField.getText();
        if(!text.isEmpty()) {
            if (text.length() > 1) {
                text = text.substring(0, MAX_LENGTH);
                textField.setText(text);
            }
            char textChar = text.charAt(0);
            if (!Character.isDigit(textChar) || Character.getNumericValue(textChar) == 0) {
                textField.setText("");
            }
            
            // Reset caret position each time a number is entered
            textField.positionCaret(0);
        }
    }
    
    @FXML
    private void zeroCaret(MouseEvent event) {
        TextField textField = (TextField) event.getSource();
        textField.positionCaret(0);
    }
}
