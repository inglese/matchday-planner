package email.englisch;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MatchdayPlanner extends Application {

    private final ListView<Matchday> listView = new ListView<>();
    private final TreeView<Matchday> treeView = new TreeView<>();
    private final List<Matchday> matchdays = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        matchdays.addAll(Arrays.asList(
                Matchday.on(LocalDate.now().minusDays(1)),
                Matchday.on(LocalDate.now()),
                Matchday.on(LocalDate.now().plusDays(1))
        ));

        TreeItem<Matchday> treeRoot = new TreeItem<>(Matchday.on(LocalDate.now()));
        treeView.setRoot(treeRoot);
        treeView.setShowRoot(false);
        matchdays.stream().forEach(matchday -> treeRoot.getChildren().add(new TreeItem<>(matchday)));
        treeView.setCellFactory(param -> new MatchdayCell());

        final VBox buttonBox = createButtonBox(primaryStage);

        final BorderPane root = new BorderPane();
        BorderPane.setAlignment(treeView, Pos.TOP_LEFT);
        root.setCenter(treeView);
        root.setRight(buttonBox);

        final Scene scene = new Scene(root);

        primaryStage.setTitle("Spielplan");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createButtonBox(Stage primaryStage) {
/*
        Button btnLoad = new Button("_Laden");
        btnLoad.setOnAction(e -> loadMatchdays());
        Button btnSave = new Button("_Speichern");
        btnSave.setOnAction(e -> saveMatchdays());
        Button btnXlsxExport = new Button("_XLSX-Export");
        btnXlsxExport.setOnAction(e -> xlsxExport());
        final VBox buttonBox = new VBox(btnNew, btnDelete, btnLoad, btnSave, btnXlsxExport);
*/
        Button btnNew = new Button("_Neu");
        btnNew.setOnAction(e -> createMatchday(primaryStage));
        Button btnDelete = new Button("_Löschen");
        btnDelete.setOnAction(e -> deleteMatchday());
        btnDelete.disableProperty().bind(treeView.getSelectionModel().selectedIndexProperty().lessThan(0));

        return new VBox(btnNew, btnDelete);
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

    private void createMatchday(Stage primaryStage) {
        MatchdayEditController matchdayEditController = new MatchdayEditController(primaryStage, LocalDate.now());
        matchdayEditController.showMatchdayEditDialogAndWait();
        final Optional<Matchday> createdMatchdayOptional = matchdayEditController.getCreatedMatchdayOptional();
        if (createdMatchdayOptional.isPresent()) {
            final Matchday createdMatchday = createdMatchdayOptional.get();
            if (matchdays.contains(createdMatchday)) {
                final String dateString = createdMatchday.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                final Alert alert = new Alert(Alert.AlertType.ERROR, "Am " + dateString + " existiert bereits ein Spieltag!");
                alert.showAndWait();
            } else {
                matchdays.add(createdMatchday);
                matchdays.sort(null);
                final int createdMatchdayIndex = matchdays.indexOf(createdMatchday);
                treeView.getRoot().getChildren().add(createdMatchdayIndex, new TreeItem<>(createdMatchday));
            }
        }
    }

    private void deleteMatchday() {
        final TreeItem<Matchday> selectedItem = treeView.getSelectionModel().getSelectedItem();
        matchdays.remove(selectedItem.getValue());
        treeView.getRoot().getChildren().remove(selectedItem);
    }

    private void loadMatchdays() {
        try (final ObjectInputStream objectInputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("spielplan.ser")))) {
            // This cast is correct because the structure that is deserialized was serialized by saveMatchday()
            // (if the correct file is read)
            @SuppressWarnings("unchecked") List<Matchday> matchdays = (ArrayList<Matchday>) objectInputStream.readObject();
            listView.getItems().setAll(matchdays);
        } catch (final FileNotFoundException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR, "Datei nicht gefunden!");
            alert.showAndWait();
        } catch (final IOException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Lesen der Datei!");
            alert.showAndWait();
        } catch (final ClassNotFoundException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR, "Inkompatibles Datenformat!");
            alert.showAndWait();
        }
    }

    private void saveMatchdays() {
        List<Matchday> matchdays = new ArrayList<>(listView.getItems());
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("spielplan.ser")))) {
            objectOutputStream.writeObject(matchdays);
        } catch (final FileNotFoundException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR, "Datei kann nicht erzeugt werden!");
            alert.showAndWait();
        } catch (final IOException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Schreiben der Datei!");
            alert.showAndWait();
        }
    }

    private void xlsxExport() {
        MatchdayToXlsxConverter.createXlsxFrom(listView.getItems().stream());
    }
}
