/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.seonm.sudokusolver;

import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Mark Seon
 */
public class PrimaryController implements Initializable {

    @FXML
    GridPane parentGrid;
    @FXML
    Spinner themeSpinner;
    Map<String, Theme> themes;
    final int GRID_SIZE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void initThemes(Map<String, Theme> themes) {
        this.themes = themes;
        
        ObservableList <String> themeNames = FXCollections.observableArrayList(themes.keySet());
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(themeNames);
        valueFactory.setValue("Default");
        valueFactory.setWrapAround(true);
        themeSpinner.setValueFactory(valueFactory);
            
        themeSpinner.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                Theme theme = themes.get(themeSpinner.getValue());
                themeSpinner.getScene().getRoot().setStyle(theme.getCSS());
            }           
        });
    }

    public PrimaryController() {
        GRID_SIZE = 9;
    }

    private Node getGridCell(GridPane grid, int row, int col) {
        final int ROW_COUNT = GRID_SIZE / 3;
        return grid.getChildren().get((row * ROW_COUNT) + col);
    }

    private int getCellValue(int row, int col) {
        String cellValue = getCell(row, col).getText();
        return cellValue.isEmpty() ? 0 : Integer.parseInt(cellValue);
    }
    
    private void setCellValue(int value, int row, int col){
        getCell(row, col).setText(String.valueOf(value));
    }
    
    private TextField getCell(int row, int col) {
        int superRow = row / 3;
        int superCol = col / 3;
        int subRow = row % 3;
        int subCol = col % 3;
        GridPane subGrid = (GridPane) getGridCell(parentGrid, superRow, superCol);
        TextField cell = (TextField) getGridCell(subGrid, subRow, subCol);
        
        return cell;
    }

    @FXML
    private void clearGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                getCell(row, col).setText("");
            }
        }
    }

    @FXML
    private void validateCell(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        validateTextField(textField);

    }

    private void validateTextField(TextField textField) {
        final int MAX_LENGTH = 1;

        String text = textField.getText();
        if (!text.isEmpty()) {
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
    private void selectCell(MouseEvent event) {
        TextField textField = (TextField) event.getSource();
        textField.positionCaret(0);
        textField.requestFocus();
    }

    @FXML
    private void deselectCell(MouseEvent event) {
        TextField textField = (TextField) event.getSource();
        validateTextField(textField);

        Node parent = textField.getParent();
        parent.requestFocus();
    }

    private int[][] readGrid() {
        int[][] grid = new int[GRID_SIZE][GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                grid[row][col] = getCellValue(row, col);
            }
        }

        return grid;
    }
    
    private void setGrid(int[][] grid) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                setCellValue(grid[row][col], row, col);
            }
        }
    }
    
    

//    public void test() {
//        int[][] grid = readGrid();
//        for (int[] line : grid) {
//            System.out.println(Arrays.toString(line));
//        }
//        System.out.println();
//    }
    
    private void printGrid(int[][] grid) {
        for (int[] line : grid) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("");
    }
    
    @FXML
    private void solve() {
        SudokuSolver sudokuSolver = new SudokuSolver();
        int[][] currentGrid = readGrid();
        int[][] solvedGrid = sudokuSolver.solveGrid(currentGrid);
//        printGrid(currentGrid);
//        printGrid(solvedGrid);

        if(!Arrays.deepEquals(currentGrid, solvedGrid)){
            setGrid(solvedGrid);
        } else {
            // grid not solveable - notify user
        }
    }
    
    @FXML
    private void saveGrid() {
        App.saveGrid(readGrid());
       
    }
    
    @FXML
    private void loadGrid() {
        setGrid(App.loadGrid());
    }
    
    
}
