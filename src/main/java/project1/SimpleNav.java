//package project1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.*;
import java.util.*;

/**
 * Displays content panes activated by a hyper-link based navigation bar
 */
public class SimpleNav extends Application {
    private LinkContent[] linkContent;

    private final StackPane content = new StackPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        linkContent = createLinkContent();

        content.setPrefWidth(600);
        HBox.setHgrow(content, Priority.ALWAYS);

        stage.setTitle("Capello Pazzo");
        stage.setScene(new Scene(createLayout()));
        stage.show();
    }

    private Pane createLayout() {
        HBox layout = new HBox(
                10,
                createNavBar(), 
                content
        );

        layout.getStylesheets().add(
                getClass().getResource("nav.css").toExternalForm()
        );

        return layout;
    }

    private VBox createNavBar() {
        VBox nav = new VBox();
        nav.setMinWidth(100);
        nav.getStyleClass().add("navbar");

        for (int i = 0; i < linkContent.length; i++) {
            Hyperlink link = createLink(
                    linkContent[i].linkText, 
                    createContentNode(linkContent[i])
            );
            nav.getChildren().add(link);
            if (i == 0) {
                link.fire();
            }
        }

        return nav;
    }

    private Node createContentNode(LinkContent linkContent) {
        Label label = new Label(linkContent.contentText);
        label.setWrapText(true);

        VBox contentNode = new VBox(
                10,
                new ImageView(linkContent.image),
                label
        );
        contentNode.getStyleClass().add("contentnode");

        return contentNode;
    }

    private Hyperlink createLink(final String linkText, final Node contentNode) {
        Hyperlink link = new Hyperlink(linkText);
        link.setOnAction(t -> content.getChildren().setAll(
                contentNode
        ));

        return link;
    }

    private static class LinkContent {
        final String linkText, contentText;
        final Image image;

        LinkContent(String linkText, String contentText, String imageLoc) {
            this.linkText = linkText;
            this.contentText = contentText;
            this.image = new Image(imageLoc);
        }
    }

    // icon license:     http://creativecommons.org/licenses/by-nc-nd/3.0/
    // icon attribution: http://www.iconarchive.com/artist/archigraphs.html
    private LinkContent[] createLinkContent() {
        return new LinkContent[] {
                new LinkContent(
                        "Lorem",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                        "http://icons.iconarchive.com/icons/archigraphs/tea-party/128/Sugar-Cubes-icon.png"
                ),
                new LinkContent(
                        "Vestibulum",
                        "Vestibulum a dui et massa laoreet vehicula.",
                        "http://icons.iconarchive.com/icons/archigraphs/tea-party/128/Tea-Cake-icon.png"
                ),
                new LinkContent(
                        "Donec",
                        "Donec sed euismod risus.",
                        "http://icons.iconarchive.com/icons/archigraphs/tea-party/128/Tea-Cup-icon.png"
                ),
                new LinkContent(
                        "Duis",
                        "Duis semper porttitor leo ac posuere.",
                        "http://icons.iconarchive.com/icons/archigraphs/tea-party/128/Tea-Pot-icon.png"
                )
        };
    }
}