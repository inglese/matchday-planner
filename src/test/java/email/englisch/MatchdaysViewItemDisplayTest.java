package email.englisch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchdaysViewItemDisplayTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        final TreeView<MatchdaysViewItem> treeView = createTreeView();

        StackPane root = new StackPane(treeView);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("MatchdaysViewDisplayTest");
        primaryStage.show();
    }

    private TreeView<MatchdaysViewItem> createTreeView() {
        final TreeView<MatchdaysViewItem> treeView = new TreeView<>();
        final TreeItem<MatchdaysViewItem> treeRoot = new TreeItem<>(new MatchdayViewItem(Matchday.on(LocalDate.now())));
        treeView.setRoot(treeRoot);
        createTreeViewTestItems(treeRoot);
        treeView.setShowRoot(false);
        treeView.setCellFactory(param -> new MatchdaysViewCell());
        return treeView;
    }

    private void createTreeViewTestItems(final TreeItem<MatchdaysViewItem> treeRoot) {
        createTestMatchdaysViewItems().forEach(
                matchdaysViewItem -> treeRoot.getChildren().add(new TreeItem<>(matchdaysViewItem))
        );
    }

    private List<MatchdaysViewItem> createTestMatchdaysViewItems() {
        final List<MatchdaysViewItem> testMatchdaysViewItem = new ArrayList<>();
        createTestMatchdays().forEach(matchday -> testMatchdaysViewItem.add(new MatchdayViewItem(matchday)));
        return testMatchdaysViewItem;
    }

    private List<Matchday> createTestMatchdays() {
        final List<Matchday> testMatchdays = new ArrayList<>();
        createTestDates().forEach(date -> testMatchdays.add(Matchday.on(date)));
        return testMatchdays;
    }

    private List<LocalDate> createTestDates() {
        final List<LocalDate> testDates = new ArrayList<>();
        testDates.add(LocalDate.now().minusDays(1));
        testDates.add(LocalDate.now());
        testDates.add(LocalDate.now().plusDays(1));
        return testDates;
    }

    private static class MatchdaysViewCell extends TreeCell<MatchdaysViewItem> {
        @Override
        protected void updateItem(MatchdaysViewItem item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(item.getGraphic());
            }
        }
    }
}
