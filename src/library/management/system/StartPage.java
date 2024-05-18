package library.management.system;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class StartPage {

    static public BorderPane main_lib(Stage primaryStage, int user_id,String name, String email, String password) {//
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15, 15, 15, 15));
        root.setStyle("-fx-background-color: #1E1E1E");

        Button home_btn = new Button("Home Page");
        home_btn.setPrefSize(90, 20);
        home_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");
        Button books_btn = new Button("Books Page");
        books_btn.setPrefSize(90, 20);
        books_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");
        Button users_btn = new Button("Users Page");
        users_btn.setPrefSize(90, 20);
        users_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");
        Button aboutUs_btn = new Button("About Us");
        aboutUs_btn.setPrefSize(90, 20);
        aboutUs_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");
        Button logOut_btn = new Button("Log Out");
        logOut_btn.setPrefSize(90, 20);
        logOut_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");

        logOut_btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                GridPane login_root = Login.main_screen(primaryStage);
                Scene login_scene = new Scene(login_root);
                primaryStage.setTitle("Login Page");
                primaryStage.setScene(login_scene);
            }
        });//

        VBox menu = new VBox();
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setStyle("-fx-background-color: #2E2E2E; -fx-background-radius: 10px;");

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        menu.getChildren().addAll(home_btn, books_btn, users_btn, aboutUs_btn, spacer, logOut_btn);
        menu.setSpacing(10);

        Text text1 = new Text("Welcome Dear ");
        Text text2 = new Text(name);

        text1.setStyle("-fx-fill: #ffffff;");
        text2.setStyle("-fx-fill: #8758FF;");

        TextFlow header_flow = new TextFlow(text1, text2);

        Label header = new Label();
        header.setGraphic(header_flow);
        header.setStyle("-fx-font-weight: bold;");
        header.setFont(new Font(18));
        
        
        GridPane home = HomePage.get_home(primaryStage,user_id,name,email,password,text2);
        root.setCenter(home);////
////
        
        

        BorderPane.setAlignment(header, Pos.CENTER);
        BorderPane.setMargin(header, new Insets(15, 0, 0, 0));
        root.setTop(header);
        root.setLeft(menu);
        
        
        //buttons actions
        
        home_btn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                root.setCenter(home);
            }
        });
        
        books_btn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                BorderPane books_page = BooksPage.get_books();
                root.setCenter(books_page);
            }//
        });
        
        users_btn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                BorderPane users_page = UsersPage.get_users();
                root.setCenter(users_page);
            }
        });//
        
        aboutUs_btn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                GridPane about = AboutPage.get_about();
                root.setCenter(about);
            }
        });

        return root;

    }
}
