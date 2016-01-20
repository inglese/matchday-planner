package email.englisch;

import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
        createXlsxFile(matchdayStream);
    }

    private static void createXlsxFile(final Stream<Matchday> matchdayStream) {
        final Workbook wb = new XSSFWorkbook();
        final Sheet sheet = wb.createSheet("Spielplan");

        final List<Matchday> matchdays = matchdayStream.collect(Collectors.toList());
        final CreationHelper creationHelper = wb.getCreationHelper();
        final CellStyle dateCellStyle = wb.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("ddd, dd.mm.yy"));
        int rowno = 0;
        for (Matchday matchday : matchdays) {
            final LocalDate matchdayDate = matchday.getDate();
            final Calendar calendar = Calendar.getInstance();
            calendar.set(
                    matchdayDate.getYear(),
                    matchdayDate.getMonthValue()-1,
                    matchdayDate.getDayOfMonth());
            final Row row = sheet.createRow(rowno);
            rowno++;
            final Cell cell = row.createCell(0);
            cell.setCellValue(calendar);
            cell.setCellStyle(dateCellStyle);
        }

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
