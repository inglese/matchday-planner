package email.englisch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.Arrays;

public class TreeViewSpike extends Application {

    @Override
    public void start(Stage primaryStage) {
        final TreeView<Item> treeView = new TreeView<>();
        TreeItem<Item> treeRoot = new TreeItem<>(new ChildItem("treeRoot"));
        treeView.setRoot(treeRoot);
        treeView.setShowRoot(false);
        treeView.setCellFactory(
                new Callback<TreeView<Item>, TreeCell<Item>>() {
                    @Override
                    public TreeCell<Item> call(TreeView<Item> param) {
                        return new TreeCell<Item>() {
                            @Override
                            protected void updateItem(Item item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item == null) {
                                    setText("");
                                    setGraphic(null);
                                } else {
                                    //setText(item.treeItemRepresentation());
                                    setGraphic(new Label(item.treeItemRepresentation()));
                                }
                            }
                        };
                    }
                }
        );
        TreeItem<Item> parentItem = new TreeItem<>(new ParentItem(LocalDate.now()));
        parentItem.setExpanded(true);
        treeRoot.getChildren().add(parentItem);
        parentItem.getChildren().addAll(Arrays.asList(new TreeItem<>(new ChildItem("First Child")),
                new TreeItem<>(new ChildItem("Second Child")),
                new TreeItem<>(new ChildItem("Third Child"))));


        final StackPane stackPane = new StackPane();
        stackPane.getChildren().add(treeView);

        final Scene scene = new Scene(stackPane);

        primaryStage.setTitle("TreeViewSpike");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static abstract class Item {
        abstract String treeItemRepresentation();
    }

    private static class ParentItem extends Item {
        private LocalDate date;

        ParentItem(LocalDate date) {
            this.date = date;
        }

        @Override
        String treeItemRepresentation() {
            return date.toString();
        }
    }

    private static class ChildItem extends Item {
        private String text;

        public ChildItem(String text) {
            this.text = text;
        }

        @Override
        String treeItemRepresentation() {
            return text;
        }
    }
}
