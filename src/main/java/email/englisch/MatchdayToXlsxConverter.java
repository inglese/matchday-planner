package email.englisch;

import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Provides functionality for creating an XLSX-file from a matchday stream.
 */
public class MatchdayToXlsxConverter {

    private MatchdayToXlsxConverter() {
        throw new AssertionError("default constructor accidentally invoked from within class MatchdayToXlsxConverter");
    }

    /**
     * Creates an XLSX converter and converts the matchdays of the specified stream
     *
     * @param matchdayStream  the matchdays that should be converted, not null
     */
    public static void createXlsxFrom(final Stream<Matchday> matchdayStream) {
        Objects.requireNonNull(matchdayStream, "matchdayStream must not be null");
        createXlsxFile();
    }

    private static void createXlsxFile() {
        final Workbook wb = new XSSFWorkbook();
        final Sheet sheet = wb.createSheet("Spielplan");
        try (final FileOutputStream fileOut = new FileOutputStream("spielplan.xlsx")) {
            wb.write(fileOut);
            fileOut.close();
        }
        catch (final FileNotFoundException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR, "XLSX-Datei kann nicht erzeugt werden!");
            alert.showAndWait();
        }
        catch (final IOException e) {
            final Alert alert = new Alert(Alert.AlertType.ERROR, "Fehler beim Schreiben in die XLSX-Datei!");
            alert.showAndWait();

        }
    }
}
