package library.management.system;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class AboutPage {

    static public GridPane get_about() {
        
        String description = "Welcome to our Library Management System (LMS)! Our platform is designed to streamline and enhance your library experience.\n"
                + "With our user-friendly interface, you can easily browse through our vast collection of books, manage your borrowing history, and discover new titles that match your interests.\n"
                + "Our LMS empowers you to efficiently search for books by title, author, genre, or keyword, making it simple to find exactly what you're looking for.\n"
                + "Once you've found the perfect book, you can conveniently reserve or borrow it with just a few clicks.\n"
                + "But our system offers more than just book management.\n"
                + "You can also create and manage your user profile, keep track of due dates and return deadlines, and receive notifications to ensure you never miss a beat.\n"
                + "Whether you're a student, a researcher, or simply an avid reader, our Library Management System is here to simplify your library experience and help you make the most of our extensive collection.\n"
                + "Welcome aboard, and happy reading!";
        
        GridPane about = new GridPane();
        about.setAlignment(Pos.CENTER);
        about.setVgap(8);
        about.setPadding(new Insets(8,8,8,15));
        
        
        Label header = new Label("About US");
        header.setFont(new Font(24));
        header.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        
        
        Label content = new Label(description);
        content.setFont(new Font(13));
        content.setStyle("-fx-text-fill: #fffffe;");
        
        about.add(header, 0, 0,2,1);
        about.add(content, 0, 1,2,1);
        
        
        
        GridPane.setValignment(header, VPos.CENTER);
        GridPane.setHalignment(header, HPos.CENTER);
        GridPane.setValignment(content, VPos.CENTER);
        GridPane.setHalignment(content, HPos.CENTER);

        return about;
    }
}
