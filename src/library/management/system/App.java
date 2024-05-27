package library.management.system;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                event.consume();

                Alert msg = new Alert(AlertType.CONFIRMATION, "See you again!");
                msg.show();
                msg.setOnCloseRequest(new EventHandler<DialogEvent>(){
                public void handle(DialogEvent e){
                    primaryStage.close();
                }
                });
            }
        });

    }    //

    public static void main(String[] args) {
        launch(args);
    }
}
