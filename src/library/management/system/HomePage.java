package library.management.system;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import static library.management.system.StartPage.main_lib;

public class HomePage extends Application {

    public void start(Stage primaryStage) {
        BorderPane main = main_lib(primaryStage, 1, "test", "main_lib", "main_lib");//

        Scene scene = new Scene(main, 700, 400);

        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(950);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }

    static public GridPane get_home(Stage primaryStage, int user_id, String name, String user_email, String user_password, Text text) {
        GridPane home = new GridPane();
        home.setAlignment(Pos.CENTER);
        home.setVgap(15);
        home.setHgap(40);

        Label content_header = new Label("Your Information");
        content_header.setFont(new Font(24));
        content_header.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label id = new Label("User ID: ");
        id.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        String userIDString = String.valueOf(user_id);
        Label id_num = new Label(userIDString);
        id_num.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Label full_name = new Label("Full Name: ");
        full_name.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        TextField name_controller = new TextField();

        name_controller.setText(name);

        Label email = new Label("Email: ");
        email.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        TextField email_controller = new TextField();

        email_controller.setText(user_email);

        Label password = new Label("Password: ");
        password.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        TextField password_controller = new PasswordField();

        password_controller.setText(user_password);

        Button update_btn = new Button("Update");
        update_btn.setPrefSize(120, 20);
        update_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white; -fx-font-weight: bold;");

        //fields style
        name_controller.setPrefSize(400, 30);
        email_controller.setPrefSize(400, 30);
        password_controller.setPrefSize(400, 30);

        home.add(content_header, 0, 0, 2, 1);
        home.add(id, 0, 1);
        home.add(id_num, 1, 1);
        home.add(full_name, 0, 2);
        home.add(name_controller, 1, 2);
        home.add(email, 0, 3);
        home.add(email_controller, 1, 3);
        home.add(password, 0, 4);
        home.add(password_controller, 1, 4);
        home.add(update_btn, 0, 5, 2, 1);

        GridPane.setValignment(content_header, VPos.CENTER);
        GridPane.setHalignment(content_header, HPos.CENTER);
        GridPane.setValignment(update_btn, VPos.CENTER);
        GridPane.setHalignment(update_btn, HPos.CENTER);

        update_btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (name_controller.getText().isEmpty() || email_controller.getText().isEmpty() || password_controller.getText().isEmpty()) {
                    Alert msg = new Alert(Alert.AlertType.WARNING, "Please fill all the Fields");
                    msg.show();
                    return;
                }
                text.setText(name_controller.getText());
                new DataBaseQueries().update_user(user_id, email_controller.getText(), name_controller.getText(), password_controller.getText());
                System.out.println("Pressed!");

            }
        });
//
        return home;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
