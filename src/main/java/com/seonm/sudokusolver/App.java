package com.seonm.sudokusolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    private Map<String, Theme> themes;

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        scene = createScene("primary");
        PrimaryController primaryController = (PrimaryController) scene.getUserData();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("icon.png")));
        stage.setTitle("Sudoku Solver");
        stage.show();

        themes = new HashMap<>();
        initThemes();

        primaryController.initThemes(themes);
    }

    private void initThemes() {
        // Default theme
        Theme defaultTheme = new Theme("#8ecccc", "#50717b", "#3a4042", "#212121");
        themes.put("Default", defaultTheme);

        // Indigo theme
        Theme indigo = new Theme("#dbd8e3", "#5c5470", "#352f44", "#2a2438");
        themes.put("Indigo", indigo);

        // Ocean theme
        Theme ocean = new Theme("#bbe4e9", "#79c2d0", "#53a8b6", "#5585b5");
        themes.put("Ocean", ocean);

        // Violet
        Theme violet = new Theme("#d5def5", "#8594e4", "#6643b5", "#430f58");
        themes.put("Violet", violet);

        // Sunrise
        Theme sunrise = new Theme("#ef5a5a", "#ffa952", " #ffe79a", "#feffdf");
        themes.put("Sunrise", sunrise);

        // Evergreen
        Theme Spruce = new Theme("#b8e1dd", "#3a9188", "#044a42", "#062925");
        themes.put("Spruce", Spruce);

        // Aloe
        Theme aloe = new Theme("#d3f6d1", "#a7d7c5", "#74b49b", "#5c8d89");
        themes.put("Aloe", aloe);

        // Mint (inverse of Aloe theme)
        Theme mint = new Theme("#5c8d89", "#74b49b", "#a7d7c5", "#d3f6d1");
        themes.put("Mint", mint);
    }

    private static Scene createScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Scene newScene = new Scene(fxmlLoader.load());
        newScene.setUserData(fxmlLoader.getController());

        return newScene;
    }

    public static void saveGrid(int[][] grid) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save grid");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setInitialFileName("Grid.txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));

        File gridFile = fileChooser.showSaveDialog(stage);
        if (gridFile != null) {
            try (FileWriter writer = new FileWriter(gridFile)) {
                for (int[] row : grid) {
                    for (int cell : row) {
                        writer.write(String.valueOf(cell));
                    }

                    writer.write("\n");
                }
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static int[][] loadGrid() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load grid");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        final int GRID_SIZE = 9;
        int[][] grid = new int[GRID_SIZE][GRID_SIZE];
        
        File gridFile = fileChooser.showOpenDialog(stage);
        if (gridFile != null) {
            Charset charset = Charset.forName("UTF-8");
            Path path = gridFile.toPath();
            try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
                String row = reader.readLine();
                int rowIndex = 0;
                
                while(row != null && rowIndex < GRID_SIZE) {
                    char[] rowArray = row.toCharArray();
                    
                    for (int i = 0; i < rowArray.length && i < GRID_SIZE; i++) {
                        grid[rowIndex][i] = Character.getNumericValue(rowArray[i]);
                    }
                    
                    rowIndex++;
                    row = reader.readLine();
                }

            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }
        
        return grid;
    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//    
//    private static FXMLLoader createLoader(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader;
//    }
//    
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }
    public static void main(String[] args) {
        launch();
    }

}
