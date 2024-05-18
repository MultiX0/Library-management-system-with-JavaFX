package library.management.system;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane main = Login.main_screen(primaryStage);//

        Scene scene = new Scene(main, 700, 400);

        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(950);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }    //
    
        public static void main(String[] args) {
        launch(args);
    }
}
