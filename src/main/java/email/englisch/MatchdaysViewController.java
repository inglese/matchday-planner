package email.englisch;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Control;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Administers a collection of matchdays and a corresponding control.
 */
public class MatchdaysViewController {

    private List<Matchday> matchdays = new ArrayList<>();
    private TreeView<Matchday> treeView = new TreeView<>();
    private BooleanProperty aMatchdayIsSelectedProperty = new SimpleBooleanProperty();

    /**
     * Creates a MatchdaysViewController
     */
    public MatchdaysViewController() {
        setupTreeView();
    }

    private void setupTreeView() {
        TreeItem<Matchday> treeRoot = new TreeItem<>(Matchday.on(LocalDate.now()));
        treeView.setRoot(treeRoot);
        treeView.setShowRoot(false);
        treeView.setCellFactory(param -> new MatchdayCell());
        // bind aMatchdayIsSelectedProperty to treeView.getSelectionModel().selectedIndexProperty().lessThan(0));
    }

    private static class MatchdayCell extends TreeCell<Matchday> {
        @Override
        protected void updateItem(Matchday item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item.getDate().toString());
            }
        }
    }

    /**
     * Inserts the specified matchday into the matchday collection and the corresponding control.
     * <p>
     * The matchday is automatically inserted at the correct position regarding its ordering.
     *
     * @param matchday  the matchday to be inserted, not null
     */
    public void insertMatchday(final Matchday matchday) {
        Objects.requireNonNull(matchday, "matchday must not be null");
        matchdays.add(matchday);
        matchdays.sort(null);
        final int createdMatchdayIndex = matchdays.indexOf(matchday);
        treeView.getRoot().getChildren().add(createdMatchdayIndex, new TreeItem<>(matchday));
    }

    /**
     * Deletes the matchday, that is currently selected, from the matchday collection and the corresponding control.
     */
    public void deleteSelectedMatchday() {
        final TreeItem<Matchday> selectedItem = treeView.getSelectionModel().getSelectedItem();
        matchdays.remove(selectedItem.getValue());
        treeView.getRoot().getChildren().remove(selectedItem);
    }

    /**
     * Removes all administered matchdays
     */
    public void clear() {
        matchdays.clear();
        treeView.getRoot().getChildren().clear();
    }

    /**
     * Returns <tt>true</tt> if the specified matchday is administered.
     *
     * @param matchday  matchday whose presence is to be tested, not null
     * @return <tt>true</tt> if the specified matchday is administered
     */
    public boolean contains(final Matchday matchday) {
       return matchdays.contains(matchday);
    }

    /**
     * Returns a stream which consists of the administered matchdays.
     *
     * @return  the stream of the administered matchdays
     */
    public Stream<Matchday> getMatchdayStream() {
        return matchdays.stream();
    }

    /**
     * Returns the control which is used to display the matchdays.
     */
    public Control getControl() {
        return treeView;
    }

    /**
     * Whether or not a matchday is currently selected.
     */
    public BooleanProperty aMatchdayIsSelectedProperty() {
        return aMatchdayIsSelectedProperty;
    }
}
