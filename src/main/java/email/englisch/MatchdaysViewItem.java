package email.englisch;

import javafx.scene.Node;

/**
 * The {@code MatchdaysViewItem} interface must be implemented by any class whose instances
 * should be displayed in {@code MatchdayView}
 */
public interface MatchdaysViewItem {
    /**
     * Returns a graphical representation of the class that implements this interface
     *
     * @return Node to be used as graphical representation in the {@code MatchdayView}, not null
     */
    Node getGraphic();
}
