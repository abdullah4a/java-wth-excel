
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
//        TODO get sheet of thresholdsSheet
        XSSFSheet thresholdsSheet = workbook.getSheet("Thresholds");
        for (var rows : flightsSheet) {
            if (rows.getRowNum() > 0 && rows.getCell(0).getDateCellValue() != null) {
//        TODO Exclude Row number 0;>0
//        TODO get Current Date currentDate
                Date currentDate = new Date();
//        TODO Store Value of 0 cell(Date) in date.
                XSSFRow row = flightsSheet.getRow(rows.getRowNum());
//                TODO Compare currentDate==date
                var date = row.getCell(0).getDateCellValue();
                if (currentDate.getDate() == date.getDate()) {
//        TODO get (%off) cell 9 and index 8 percentOff
                    var percentOff = row.getCell(8).getRawValue().startsWith(String.valueOf(0)) ? Double.parseDouble(row.getCell(8).getRawValue()) : null;
//        TODO compare percentOff>=60
                    if (percentOff != null && percentOff > 0.60 && percentOff < 0.99) {
//        TODO save into new dataArr,
                        System.out.println(percentOff);
                    }
//        TODO dataArr.getOriginCity() and Compare with thresholdsSheet
                }
            }
//                XSSFRow row = flightsSheet.getRow(rows.getRowNum());
//        XSSFRow row = flightsSheet.getRow(3);
//        double percentOffCast = row.getCell(8).getNumericCellValue() * 100;
//        System.out.println(percentOffCast >= 60);
        }
//        flights.forEach(flight -> {
//            UUID id = UUID.randomUUID();
//            flight.setId(Long.parseLong(id.toString()));
//            flight.setDate(row.getCell(0).getDateCellValue());
//            flight.setOrigin(row.getCell(1).getRawValue());
//            flight.setDestination(row.getCell(2).getRawValue());
//            flight.setDealPrice(new Double(row.getCell(3).getNumericCellValue()).longValue());
//            flight.setNormalPrice(new Double(row.getCell(4).getNumericCellValue()).longValue());
//            flight.setDepartureDate(row.getCell(5).getDateCellValue());
//            flight.setReturnDate(row.getCell(6).getDateCellValue());
//            flight.setNumberOfStops(new Double(row.getCell(7).getNumericCellValue()).longValue());
//            flight.setPercentOff(row.getCell(8).getNumericCellValue());
//            flight.setAirline(row.getCell(9).getStringCellValue());
//            flight.setDepartureAirport(row.getCell(10).getRawValue());
//            flight.setReturningAirport(row.getCell(11).getRawValue());
//            flight.setGoodDeal(Boolean.parseBoolean(row.getCell(12).getStringCellValue()));
//            flight.setLinkToFlights(row.getCell(13).getRawValue());
//            flight.setSentToPremium(row.getCell(14).getRawValue());
//            flight.setSentToFree(row.getCell(15).getRawValue());
//            flight.setRemarks(row.getCell(16).getRawValue());
//            System.out.println(flight);
//        });
//    }
//}

    }
}

