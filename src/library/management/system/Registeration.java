package library.management.system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Registeration {

    static public GridPane registeration_screen(Stage primaryStage) {

        GridPane registration_root = new GridPane();
        registration_root.setStyle("-fx-background-color: #1E1E1E");

        Text text1 = new Text("We are very excited to meet ");
        Text text2 = new Text("You");

        text1.setStyle("-fx-fill: #ffffff;");
        text2.setStyle("-fx-fill: #8758FF;");

        TextFlow headerFlow = new TextFlow(text1, text2);

        Text text3 = new Text("Do you already have an account? ");
        Text text4 = new Text("SignIn");
        //
        text4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                GridPane login_root = Login.main_screen(primaryStage);
                Scene login_scene = new Scene(login_root, 700, 400);
                primaryStage.setTitle("Login Page");
                primaryStage.setScene(login_scene);
            }
        });

        text4.setFocusTraversable(true);

        text3.setStyle("-fx-fill: #ffffff;");
        text4.setStyle("-fx-fill: #8758FF; -fx-font-weight: bold;");

        TextFlow footerFlow = new TextFlow(text3, text4);

        Label header = new Label();
        header.setGraphic(headerFlow);
        header.setFont(new Font(26));
        header.setStyle("-fx-font-weight: bold;");
        GridPane.setMargin(header, new Insets(0, 0, 20, 0));

        Label footer = new Label();
        footer.setGraphic(footerFlow);

        Label full_name = new Label("Full Name: ");
        full_name.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        TextField name_controller = new TextField();

        Label email = new Label("Email: ");
        email.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        TextField email_controller = new TextField();

        Label password = new Label("Password: ");
        password.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        TextField password_controller = new PasswordField();

        Button register_btn = new Button("SignUp");
        register_btn.setPrefSize(120, 20);
        register_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white; -fx-font-weight: bold;");
        GridPane.setMargin(register_btn, new Insets(15, 0, 8, 0));

        //fields styles
        name_controller.setPrefSize(400, 30);
        email_controller.setPrefSize(400, 30);
        password_controller.setPrefSize(400, 30);

        registration_root.setAlignment(Pos.CENTER);
        registration_root.setVgap(15);
        registration_root.setHgap(30);

        registration_root.add(header, 0, 0, 2, 1);
        registration_root.add(full_name, 0, 1);
        registration_root.add(name_controller, 1, 1);
        registration_root.add(email, 0, 2);
        registration_root.add(email_controller, 1, 2);
        registration_root.add(password, 0, 3);
        registration_root.add(password_controller, 1, 3);
        registration_root.add(register_btn, 0, 4, 2, 1);
        registration_root.add(footer, 0, 5, 2, 1);

        GridPane.setHalignment(register_btn, HPos.CENTER);
        GridPane.setValignment(register_btn, VPos.CENTER);
        GridPane.setHalignment(header, HPos.CENTER);
        GridPane.setValignment(header, VPos.CENTER);
        GridPane.setHalignment(footer, HPos.CENTER);
        GridPane.setValignment(footer, VPos.CENTER);

        register_btn.setOnAction(new EventHandler<ActionEvent>() {//
            public void handle(ActionEvent event) {
                if (name_controller.getText().isEmpty() || email_controller.getText().isEmpty() || password_controller.getText().isEmpty()) {
                    Alert msg = new Alert(Alert.AlertType.WARNING, "Please fill all the Fields");
                    msg.show();
                    return;
                } else {
                    System.out.println("Pressed!");
                    new DataBaseQueries().add_user(name_controller.getText(), email_controller.getText(), password_controller.getText(),primaryStage);
                }

            }
        });

        return registration_root;
    }

;

}
