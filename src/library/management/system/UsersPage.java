package library.management.system;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UsersPage extends Application {

    @Override
    public void start(Stage primaryStage) {

        BorderPane main = get_users();
        main.setStyle("-fx-background-color:#1e1e1e");

        Scene scene = new Scene(main, 700, 400);

        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(950);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }

    static public BorderPane get_users() {

        ObservableList<Integer> userIds = FXCollections.observableArrayList();
        ObservableList<String> userNames = FXCollections.observableArrayList();
        ObservableList<String> userEmails = FXCollections.observableArrayList();

        ListView<Integer> idListView = new ListView<>(userIds);
        ListView<String> nameListView = new ListView<>(userNames);
        ListView<String> emailListView = new ListView<>(userEmails);

        Label id_label = new Label("User ID: ");
        id_label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Label name_label = new Label("User Name: ");
        name_label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Label email_label = new Label("User Email: ");
        email_label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Button get_allUsers_btn = new Button("Get All Users");
        get_allUsers_btn.setPrefSize(120, 20);
        get_allUsers_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");

        GridPane.setMargin(get_allUsers_btn, new Insets(15, 0, 10, 0));

        get_allUsers_btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                new DataBaseQueries().get_all_users(userIds, userNames, userEmails);
            }

        });

        Label content = new Label("In Case You Want to get user data by his id");
        content.setStyle("-fx-text-fill:#ffff;-fx-font-weight: bold;");
        content.setFont(new Font("Arial", 16));

        Label user_id_label = new Label("User Id: ");
        user_id_label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
        GridPane.setMargin(user_id_label, new Insets(0, 20, 5, 20));

        TextField user_id_controller = new TextField();
        GridPane.setMargin(user_id_controller, new Insets(0, 20, 10, 20));

        Button get_user_byId_btn = new Button("Get User");
        get_user_byId_btn.setPrefSize(120, 20);
        get_user_byId_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");

        get_user_byId_btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (user_id_controller.getText().isEmpty()) {
                    Alert msg = new Alert(AlertType.WARNING, "Please fill the field");
                    msg.show();
                } else {
                    new DataBaseQueries().get_user_by_id(user_id_controller.getText(), userIds, userNames, userEmails);

                }
            }

        });

        GridPane users = new GridPane();
        users.setAlignment(Pos.CENTER);
        users.add(id_label, 0, 0);
        users.add(idListView, 0, 1);
        users.add(name_label, 1, 0);
        users.add(nameListView, 1, 1);
        users.add(email_label, 2, 0);
        users.add(emailListView, 2, 1);
        users.add(get_allUsers_btn, 0, 2, 4, 1);
        users.add(content, 0, 3, 4, 1);
        users.add(user_id_label, 0, 4, 4, 1);
        users.add(user_id_controller, 0, 5, 4, 1);
        users.add(get_user_byId_btn, 0, 6, 4, 1);

        GridPane.setValignment(get_allUsers_btn, VPos.CENTER);
        GridPane.setHalignment(get_allUsers_btn, HPos.CENTER);

        GridPane.setValignment(get_user_byId_btn, VPos.CENTER);
        GridPane.setHalignment(get_user_byId_btn, HPos.CENTER);

        GridPane.setValignment(content, VPos.CENTER);
        GridPane.setHalignment(content, HPos.CENTER);

        GridPane.setMargin(content, new Insets(15, 0, 15, 0));

        BorderPane root = new BorderPane();
        root.setCenter(users);
        root.setPadding(new Insets(10, 15, 10, 15));

        return root;

    }

    static public void main(String[] args) {
        launch(args);
    }
}
