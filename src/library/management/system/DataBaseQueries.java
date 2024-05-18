package library.management.system;

import java.sql.*;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DataBaseQueries {

    private static final String ADD_USER = "INSERT INTO users (user_name, email, password) VALUES (?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET user_name=?,email=?,password=? WHERE user_id=?";

    private static final String LOGGIN_USER = "SELECT * FROM users WHERE email = ? AND password = ?";

    private static final String GET_ALL_BOOKS = "SELECT * FROM books";
    private static final String GET_ALL_USERS = "SELECT * FROM users";

    private static final String UPDATE_BOOK = "UPDATE books SET book_name=?,author=?,quantity=? WHERE book_id=?";
    private static final String ADD_BOOK = "INSERT INTO books (book_name, author, quantity) VALUES (?, ?, ?)";
    private static final String DELETE_BOOK = "DELETE FROM books WHERE book_id=?";

    private Connection connect() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib", "root", "");
            return con;
        } catch (Exception e) {
            Alert msg = new Alert(AlertType.ERROR, "Error " + e);
            msg.show();
        }
        return null;
    }

    public void add_user(String user_name, String user_email, String user_password, Stage primaryStage) {

        try {
            Connection con = connect();
            PreparedStatement statement = con.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user_name.trim());
            statement.setString(2, user_email.trim());
            statement.setString(3, user_password);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int user_id = generatedKeys.getInt(1);
                    System.out.println("The ID IS :" + user_id);

                    generatedKeys.close();

                    Alert msg = new Alert(AlertType.CONFIRMATION, "Successfully Registration");
                    msg.show();

                    BorderPane main_screen = StartPage.main_lib(primaryStage, user_id, user_name, user_email, user_password);
                    Scene main_scene = new Scene(main_screen);

                    msg.setOnCloseRequest(e -> primaryStage.setScene(main_scene));
                } else {
                    Alert msg = new Alert(AlertType.ERROR, "Failed to retrieve generated user ID");
                    msg.show();
                }
            } else {
                Alert msg = new Alert(AlertType.ERROR, "Failed to insert user record");
                msg.show();
            }

        } catch (Exception e) {
            Alert msg = new Alert(AlertType.ERROR, "Error " + e);
            msg.show();
        }
    }

    public void log_in(String email, String password, Stage primaryStage) {
        try {
            Connection con = connect();
            PreparedStatement statement = con.prepareStatement(LOGGIN_USER);

            statement.setString(1, email.trim());
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String user_name = resultSet.getString("user_name");

                Alert msg = new Alert(Alert.AlertType.CONFIRMATION, "Successfully Logged In");
                msg.show();

                BorderPane main_screen = StartPage.main_lib(primaryStage, user_id, user_name, email, password);
                Scene main_scene = new Scene(main_screen);
                msg.setOnCloseRequest(e -> primaryStage.setScene(main_scene));

            } else {
                Alert msg = new Alert(Alert.AlertType.WARNING, "Incorrect Email or Password.");
                msg.show();
            }

        } catch (Exception e) {
            Alert msg = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
            msg.show();
        }
    }

    public void update_user(int user_id, String user_email, String user_name, String password) {
        try {
            Connection con = connect();
            PreparedStatement statement = con.prepareStatement(UPDATE_USER);

            statement.setString(1, user_name.trim());
            statement.setString(2, user_email.trim());
            statement.setString(3, password);
            statement.setInt(4, user_id);

            statement.execute();

            Alert msg = new Alert(Alert.AlertType.CONFIRMATION, "Successfully Updated");
            msg.show();

        } catch (Exception e) {
            Alert msg = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
            msg.show();
        }
    }

    public void get_all_books(ObservableList<Integer> bookIds, ObservableList<String> bookNames, ObservableList<String> bookAuthors, ObservableList<Integer> bookQuantities) {
        try {

            Connection con = connect();
            PreparedStatement statement = con.prepareStatement(GET_ALL_BOOKS);

            ResultSet resultSet = statement.executeQuery();

            bookIds.clear();
            bookNames.clear();
            bookAuthors.clear();
            bookQuantities.clear();

            while (resultSet.next()) {
                int id = resultSet.getInt("book_id");
                String name = resultSet.getString("book_name");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");

                bookIds.add(id);
                bookNames.add(name);
                bookAuthors.add(author);
                bookQuantities.add(quantity);

            }

        } catch (Exception e) {
            Alert msg = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
            msg.show();
        }

    }

    public void get_all_users(ObservableList<Integer> userIds, ObservableList<String> userNames, ObservableList<String> userEmails) {
        try {
            Connection con = connect();
            PreparedStatement statement = con.prepareStatement(GET_ALL_USERS);

            ResultSet resultSet = statement.executeQuery();

            userIds.clear();
            userNames.clear();
            userEmails.clear();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String name = resultSet.getString("user_name");
                String email = resultSet.getString("email");

                userIds.add(id);
                userNames.add(name);
                userEmails.add(email);

            }

        } catch (Exception e) {
            Alert msg = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
            msg.show();
        }
    }

    public void update_book(int book_id, String book_name, String author, int quantity, ObservableList<Integer> bookIds, ObservableList<String> bookNames, ObservableList<String> bookAuthors, ObservableList<Integer> bookQuantities) {
        try {
            Connection con = connect();
            PreparedStatement statement = con.prepareStatement(UPDATE_BOOK);

            statement.setString(1, book_name);
            statement.setString(2, author);
            statement.setInt(3, quantity);
            statement.setInt(4, book_id);

            statement.execute();

            this.get_all_books(bookIds, bookNames, bookAuthors, bookQuantities);

        } catch (Exception e) {
            Alert msg = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
            msg.show();
        }
    }

    public void add_book(String book_name, String author, int quantity, ObservableList<Integer> bookIds, ObservableList<String> bookNames, ObservableList<String> bookAuthors, ObservableList<Integer> bookQuantities) {
        try {
            Connection con = connect();
            PreparedStatement statement = con.prepareStatement(ADD_BOOK);

            statement.setString(1, book_name);
            statement.setString(2, author);
            statement.setInt(3, quantity);

            statement.execute();

            this.get_all_books(bookIds, bookNames, bookAuthors, bookQuantities);

        } catch (Exception e) {
            Alert msg = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
            msg.show();
        }
    }

    public void delete_book(int book_id, ObservableList<Integer> bookIds, ObservableList<String> bookNames, ObservableList<String> bookAuthors, ObservableList<Integer> bookQuantities) {

        try {

            Connection con = connect();
            PreparedStatement statement = con.prepareStatement(DELETE_BOOK);

            statement.setInt(1, book_id);

            statement.execute();

            this.get_all_books(bookIds, bookNames, bookAuthors, bookQuantities);

        } catch (Exception e) {
            Alert msg = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
            msg.show();
        }

    }

}
