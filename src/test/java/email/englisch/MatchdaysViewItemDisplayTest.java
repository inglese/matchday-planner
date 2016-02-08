package email.englisch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MatchdaysViewItemDisplayTest extends Application{

    private final TreeView<MatchdaysViewItem> treeView = new TreeView<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupTreeView();

        StackPane root = new StackPane(treeView);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("MatchdaysViewDisplayTest");
        primaryStage.show();
    }

    private void setupTreeView() {
        final TreeItem<MatchdaysViewItem> treeRoot = new TreeItem<>(new MatchdayViewItem(Matchday.on(LocalDate.now())));
        treeView.setRoot(treeRoot);
        createTreeViewTestItems();
        treeView.setShowRoot(false);
        treeView.setCellFactory(param -> new MatchdaysViewCell());
    }

    private void createTreeViewTestItems() {
        final TreeItem<MatchdaysViewItem> treeRoot = treeView.getRoot();
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
        final List<Match> testMatches = createTestMatches();
        createTestDates().forEach(date -> testMatchdays.add(Matchday.on(date)));
        testMatchdays.forEach(testMatchday -> appendMatchesToMatchday(testMatchday, testMatches));
        return testMatchdays;
    }

    private void appendMatchesToMatchday(final Matchday matchday, final List<Match> matches) {
        matches.forEach(matchday::insert);
    }

    private List<LocalDate> createTestDates() {
        final List<LocalDate> testDates = new ArrayList<>();
        testDates.add(LocalDate.now().minusDays(1));
        testDates.add(LocalDate.now());
        testDates.add(LocalDate.now().plusDays(1));
        return testDates;
    }

    private List<Match> createTestMatches() {
        final List<Match> testMatches = new ArrayList<>();
        createTestTimes().forEach(testTime -> testMatches.add(Match.at(testTime)));
        return testMatches;
    }

    private List<LocalTime> createTestTimes() {
        final List<LocalTime> testTimes = new ArrayList<>();
        testTimes.add(LocalTime.of(10, 0));
        testTimes.add(LocalTime.of(12, 0));
        testTimes.add(LocalTime.of(14, 0));
        return testTimes;
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
