package com.seonm.sudokusolver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Map<String, Theme> themes;

    @Override
    public void start(Stage stage) throws IOException {
        scene = createScene("primary");
        PrimaryController primaryController = (PrimaryController) scene.getUserData();        
        stage.setScene(scene);
        stage.setResizable(false);
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