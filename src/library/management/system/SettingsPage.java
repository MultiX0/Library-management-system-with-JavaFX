package library.management.system;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;

public class SettingsPage extends Application {
    public void start(Stage primaryStage){
        GridPane root = settings_page(primaryStage);
        Scene s = new Scene(root,700,700);
        primaryStage.setScene(s);
        
        primaryStage.show();
    }
    static public GridPane settings_page(Stage primaryStage){
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #2E2E2E");
        
        
        return root;
    } 
    
    public static void main(String []args){
        launch(args);
    }
}
