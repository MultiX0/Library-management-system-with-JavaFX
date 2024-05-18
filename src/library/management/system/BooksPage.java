package library.management.system;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BooksPage extends Application {

    @Override
    public void start(Stage primaryStage) {

        BorderPane main = get_books();
        main.setStyle("-fx-background-color: #1E1E1E");

        Scene scene = new Scene(main, 700, 400);

        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(950);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }

    static public BorderPane get_books() {

        ObservableList<Integer> bookIds = FXCollections.observableArrayList();
        ObservableList<String> bookNames = FXCollections.observableArrayList();
        ObservableList<String> bookAuthors = FXCollections.observableArrayList();
        ObservableList<Integer> bookQuantities = FXCollections.observableArrayList();

        ListView<Integer> idListView = new ListView<>(bookIds);
        idListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ListView<String> nameListView = new ListView<>(bookNames);
        ListView<String> authorListView = new ListView<>(bookAuthors);
        ListView<Integer> quantityListView = new ListView<>(bookQuantities);

        Label id_label = new Label("Book ID: ");
        id_label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Label name_label = new Label("Book Name: ");
        name_label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Label author_label = new Label("Book Author: ");
        author_label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Label quantity_label = new Label("Book Quantity: ");
        quantity_label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Label content = new Label("In Case You Want to Add Or Update A book fill the fields");
        content.setStyle("-fx-text-fill:#ffff;-fx-font-weight: bold;");
        content.setFont(new Font(16));

        TextField name_field = new TextField();
        GridPane.setMargin(name_field, new Insets(0, 0, 10, 0));
        TextField author_field = new TextField();
        GridPane.setMargin(author_field, new Insets(0, 0, 10, 0));

        TextField quantity_field = new TextField();
        GridPane.setMargin(quantity_field, new Insets(0, 0, 10, 0));

        Label enter_name = new Label("Enter Name");
        enter_name.setStyle("-fx-text-fill:#ffff;-fx-font-weight: bold;");
        enter_name.setFont(new Font(11));
        Label enter_author = new Label("Enter Author");
        enter_author.setStyle("-fx-text-fill:#ffff;-fx-font-weight: bold;");
        enter_author.setFont(new Font(11));
        Label enter_quantity = new Label("Enter Quantity");
        enter_quantity.setStyle("-fx-text-fill:#ffff;-fx-font-weight: bold;");
        enter_quantity.setFont(new Font(11));

        Button get_allBooks_btn = new Button("Get All Books");
        get_allBooks_btn.setPrefSize(120, 20);
        get_allBooks_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");
        GridPane.setMargin(get_allBooks_btn, new Insets(10, 0, 0, 0));

        get_allBooks_btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                new DataBaseQueries().get_all_books(bookIds, bookNames, bookAuthors, bookQuantities);
            }

        });

        Button add_newBook_btn = new Button("ADD Book");
        add_newBook_btn.setPrefSize(120, 20);
        add_newBook_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");
        add_newBook_btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (name_field.getText().isEmpty() || author_field.getText().isEmpty() || quantity_field.getText().isEmpty()) {
                    Alert msg = new Alert(Alert.AlertType.WARNING, "Please Enter All The Fields");
                    msg.show();
                    return;
                }

                new DataBaseQueries().add_book(name_field.getText().trim(), author_field.getText().trim(), Integer.parseInt(quantity_field.getText()),
                        bookIds, bookNames, bookAuthors, bookQuantities);

                name_field.setText("");
                author_field.setText("");
                quantity_field.setText("");

            }
        });

        Button update_book_btn = new Button("Update Book");
        update_book_btn.setPrefSize(120, 20);
        update_book_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");
        update_book_btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (idListView.getSelectionModel().getSelectedItem() == null) {
                    Alert msg = new Alert(Alert.AlertType.WARNING, "Please Select The Book ID That You Want to Update From The List");
                    msg.show();
                    return;
                }
                if (name_field.getText().isEmpty() || author_field.getText().isEmpty() || quantity_field.getText().isEmpty()) {
                    Alert msg = new Alert(Alert.AlertType.WARNING, "Please Enter All The Fields");
                    msg.show();
                    return;
                }

                new DataBaseQueries().update_book(idListView.getSelectionModel().getSelectedItem(), name_field.getText().trim(), author_field.getText().trim(), Integer.parseInt(quantity_field.getText()),
                        bookIds, bookNames, bookAuthors, bookQuantities);

                name_field.setText("");
                author_field.setText("");
                quantity_field.setText("");

            }
        });

        Button delete_book_btn = new Button("Delete Book");
        delete_book_btn.setPrefSize(120, 20);
        delete_book_btn.setStyle("-fx-background-color: #8758FF; -fx-text-fill: white;");

        delete_book_btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (idListView.getSelectionModel().getSelectedItem() == null) {
                    Alert msg = new Alert(Alert.AlertType.WARNING, "Please Select The Book ID That You Want to Deleted From The List");
                    msg.show();
                    return;
                }

                new DataBaseQueries().delete_book(idListView.getSelectionModel().getSelectedItem(),
                        bookIds, bookNames, bookAuthors, bookQuantities);

            }
        });

        GridPane fields = new GridPane();
        fields.setVgap(5);
        fields.setHgap(20);
        fields.setAlignment(Pos.CENTER);
        fields.add(enter_name, 0, 0);
        fields.add(name_field, 0, 1);
        fields.add(enter_author, 1, 0);
        fields.add(author_field, 1, 1);
        fields.add(enter_quantity, 2, 0);
        fields.add(quantity_field, 2, 1);

        fields.add(add_newBook_btn, 0, 2);
        fields.add(update_book_btn, 1, 2);
        fields.add(delete_book_btn, 2, 2);
        fields.add(get_allBooks_btn, 0, 3, 3, 1);

        GridPane.setValignment(get_allBooks_btn, VPos.CENTER);
        GridPane.setHalignment(get_allBooks_btn, HPos.CENTER);

        GridPane.setValignment(add_newBook_btn, VPos.CENTER);
        GridPane.setHalignment(add_newBook_btn, HPos.CENTER);

        GridPane.setValignment(update_book_btn, VPos.CENTER);
        GridPane.setHalignment(update_book_btn, HPos.CENTER);

        GridPane.setValignment(delete_book_btn, VPos.CENTER);
        GridPane.setHalignment(delete_book_btn, HPos.CENTER);

        fields.setPadding(new Insets(15, 0, 15, 0));

        GridPane books = new GridPane();
        books.setAlignment(Pos.CENTER);
        books.add(id_label, 0, 0);
        books.add(idListView, 0, 1);
        books.add(name_label, 1, 0);
        books.add(nameListView, 1, 1);
        books.add(author_label, 2, 0);
        books.add(authorListView, 2, 1);
        books.add(quantity_label, 3, 0);
        books.add(quantityListView, 3, 1);
        books.add(content, 0, 2, 4, 1);
        books.add(fields, 0, 3, 4, 1);

        GridPane.setMargin(content, new Insets(15, 0, 10, 0));

        GridPane.setValignment(content, VPos.CENTER);
        GridPane.setHalignment(content, HPos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(books);
        root.setPadding(new Insets(10, 15, 10, 15));

        return root;

    }

    static public void main(String[] args) {
        launch(args);
    }
}
