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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatchdayPlanner extends Application {

    private final MatchdaysViewController matchdaysViewController = new MatchdaysViewController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        createExemplaryMatchdays();
        primaryStage.setScene(createGUI(primaryStage));
        primaryStage.setTitle("Spielplan");
        primaryStage.show();
    }

    private Scene createGUI(Stage primaryStage) {
        final BorderPane root = new BorderPane();
        BorderPane.setAlignment(matchdaysViewController.getControl(), Pos.TOP_LEFT);
        root.setCenter(matchdaysViewController.getControl());
        root.setRight(createButtons(primaryStage));
        return new Scene(root);
    }

    private VBox createButtons(Stage primaryStage) {
        Button btnNew = createButtonNew(primaryStage);
        Button btnDelete = createButtonDelete();
        Button btnLoad = createButtonLoad();
        Button btnSave = createButtonSave();
        Button btnXlsxExport = createButtonXlsxExport();

        return new VBox(btnNew, btnDelete, btnLoad, btnSave, btnXlsxExport);
    }

    private void createExemplaryMatchdays() {
        matchdaysViewController.insertMatchday(Matchday.on(LocalDate.now().minusDays(1)));
        matchdaysViewController.insertMatchday(Matchday.on(LocalDate.now()));
        matchdaysViewController.insertMatchday(Matchday.on(LocalDate.now().plusDays(1)));
    }

    private Button createButtonNew(Stage primaryStage) {
        Button btnNew = new Button("_Neu");
        btnNew.setOnAction(e -> showMatchdayCreationDialog(primaryStage));
        return btnNew;
    }

    private Button createButtonDelete() {
        Button btnDelete = new Button("_Löschen");
        btnDelete.setOnAction(e -> matchdaysViewController.deleteSelectedMatchday());
        btnDelete.disableProperty().bind(matchdaysViewController.aMatchdayIsSelectedProperty());
        return btnDelete;
    }

    private Button createButtonLoad() {
        Button btnLoad = new Button("_Laden");
        btnLoad.setOnAction(e -> loadMatchdays());
        return btnLoad;
    }

    private Button createButtonSave() {
        Button btnSave = new Button("_Speichern");
        btnSave.setOnAction(e -> saveMatchdays());
        return btnSave;
    }

    private Button createButtonXlsxExport() {
        Button btnXlsxExport = new Button("_XLSX-Export");
        btnXlsxExport.setOnAction(e -> xlsxExport());
        return btnXlsxExport;
    }

    private void showMatchdayCreationDialog(Stage primaryStage) {
        MatchdayEditController matchdayEditController = new MatchdayEditController(primaryStage, LocalDate.now());
        matchdayEditController.showMatchdayEditDialogAndWait();
        final Optional<Matchday> createdMatchdayOptional = matchdayEditController.getCreatedMatchdayOptional();
        if (createdMatchdayOptional.isPresent()) {
            final Matchday createdMatchday = createdMatchdayOptional.get();
            if (matchdaysViewController.contains(createdMatchday)) {
                final String dateString = createdMatchday.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                showError("Am " + dateString + " existiert bereits ein Spieltag!");
            } else {
                matchdaysViewController.insertMatchday(createdMatchday);
            }
        }
    }

    private void showError(String errorMessage) {
        final Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
        alert.showAndWait();
    }

    private void loadMatchdays() {
        try (final ObjectInputStream objectInputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("spielplan.ser")))) {
            // This cast is correct because the structure that is deserialized was serialized by saveMatchday()
            // (if the correct file is read)
            @SuppressWarnings("unchecked") final List<Matchday> loadedMatchdays =
                    (ArrayList<Matchday>) objectInputStream.readObject();

            matchdaysViewController.clear();
            loadedMatchdays.forEach(matchdaysViewController::insertMatchday);
        } catch (final FileNotFoundException e) {
            showError("Datei nicht gefunden!");
        } catch (final IOException e) {
            showError("Fehler beim Lesen der Datei!");
        } catch (final ClassNotFoundException e) {
            showError("Inkompatibles Datenformat!");
        }
    }

    private void saveMatchdays() {
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("spielplan.ser")))) {
            final ArrayList<Matchday> matchdaysToSave =
                    matchdaysViewController.getMatchdayStream().collect(Collectors.toCollection(ArrayList::new));
            objectOutputStream.writeObject(matchdaysToSave);
        } catch (final FileNotFoundException e) {
            showError("Datei kann nicht erzeugt werden!");
        } catch (final IOException e) {
            showError("Fehler beim Schreiben der Datei!");
        }
    }

    private void xlsxExport() {
        MatchdayToXlsxConverter.createXlsxFrom(matchdaysViewController.getMatchdayStream());
    }
}
