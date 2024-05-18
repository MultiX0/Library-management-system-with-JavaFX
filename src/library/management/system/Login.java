package library.management.system;

import javafx.event.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Login {

    static public GridPane main_screen(Stage primaryStage) {

        GridPane root = new GridPane();
        root.setStyle("-fx-background-color:#1E1E1E");

        Text text1 = new Text("Welcome To Our ");
        Text text2 = new Text("Library");

        text2.setStyle("-fx-fill: #8758FF;");
        text1.setStyle("-fx-fill: #ffffff;");

        Text text3 = new Text("You Dont have an account? ");
        Text text4 = new Text("SignUp");

        text4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                GridPane register_root = Registeration.registeration_screen(primaryStage);
                Scene register_scene = new Scene(register_root, 700, 400);
                primaryStage.setTitle("Registeration Page");
                primaryStage.setScene(register_scene);
            }
        });

        text4.setFocusTraversable(true);

        text4.setStyle("-fx-fill: #8758FF;-fx-font-weight: bold;");
        text3.setStyle("-fx-fill: #ffffff;");

        TextFlow footer_flow = new TextFlow(text3, text4);

        TextFlow header_flow = new TextFlow(text1, text2);

        Label header = new Label();
        Label footer = new Label();
        footer.setGraphic(footer_flow);

        header.setFont(new Font(26));
        header.setGraphic(header_flow);

        header.setStyle("-fx-font-weight: bold;");

        header.setFont(new Font(26));
        GridPane.setMargin(header, new Insets(0, 0, 20, 0));

        Label email = new Label("Email: ");
        Label password = new Label("Password: ");

        email.setStyle("-fx-text-fill:#ffff;-fx-font-weight: bold;");
        password.setStyle("-fx-text-fill:#ffff;-fx-font-weight: bold;");
        //
        TextField email_controller = new TextField();
        TextField password_controller = new PasswordField();

        Button login_btn = new Button("Login");
        login_btn.setPrefSize(120, 20);
        login_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white; -fx-font-weight: bold;");

        //fields styles
        email_controller.setPrefSize(400, 30);
        password_controller.setPrefSize(400, 30);

        login_btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
//                BorderPane main_page = StartPage.main_lib(primaryStage, 1, "", "", "");
//                Scene main_scene = new Scene(main_page, 800, 400);
//                primaryStage.setTitle("Main Page");
//                primaryStage.setScene(main_scene);

             if (email_controller.getText().isEmpty() || password_controller.getText().isEmpty()) {
                    Alert msg = new Alert(Alert.AlertType.WARNING, "Please fill all the Fields");
                    msg.show();
                    return;
                }

            new DataBaseQueries().log_in(email_controller.getText(), password_controller.getText(),primaryStage);
            }
        });

        // here to reminde my self              top right bottom left (like a clock arrows)
        GridPane.setMargin(login_btn, new Insets(15, 0, 8, 0));

        root.setAlignment(Pos.CENTER);

        root.add(header, 0, 0, 2, 1);
        root.add(email, 0, 1);
        root.add(email_controller, 1, 1);
        root.add(password, 0, 2);
        root.add(password_controller, 1, 2);

        root.add(login_btn, 0, 3, 2, 1);

        root.add(footer, 0, 4, 2, 1);

        GridPane.setHalignment(login_btn, HPos.CENTER);
        GridPane.setValignment(login_btn, VPos.CENTER);

        GridPane.setHalignment(header, HPos.CENTER);
        GridPane.setValignment(header, VPos.CENTER);

        GridPane.setHalignment(footer, HPos.CENTER);
        GridPane.setValignment(footer, VPos.CENTER);

        root.setVgap(15);
        root.setHgap(30);

        return root;
    }

;

}
