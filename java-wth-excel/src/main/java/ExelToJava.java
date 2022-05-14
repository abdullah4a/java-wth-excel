
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ExelToJava {
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        readExcelFile();
    }

    private static void readExcelFile() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("src/main/java/static/Daily Flight Price Tracking_1.xlsx"));
        XSSFSheet flightsSheet = workbook.getSheet("Flights 2022");
        List<src.main.java.flights> flights = new ArrayList<>();
        XSSFSheet thresholdsSheet = workbook.getSheet("Thresholds");
        for (var rows : flightsSheet) {
            if (rows.getRowNum() > 0 && rows.getCell(0).getDateCellValue() != null) {
                Date currentDate = new Date();
                XSSFRow row = flightsSheet.getRow(rows.getRowNum());
                var date = row.getCell(0).getDateCellValue();
                if (currentDate.getDate() == date.getDate()) {
                    var percentOff = row.getCell(8).getRawValue().startsWith(String.valueOf(0))
                            ? Double.parseDouble(row.getCell(8).getRawValue()) : null;
                    if (percentOff != null && percentOff > 0.60 && percentOff < 0.99) {
                        System.out.println(percentOff);
                    }
                }
            }
        }
    }
}

