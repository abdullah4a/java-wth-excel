import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.util.Diff;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExelToJava {
    public static void main(String[] args) throws IOException {
        readExcelFile();
    }

    private static void readExcelFile() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("src/main/java/static/Daily Flight Price Tracking_1.xlsx"));
        var list = compareLists(workbook);
    }

    private static List<Thresholds> getThresholds(XSSFWorkbook workbook) {
        XSSFSheet thresholdsSheet = workbook.getSheet("Thresholds");
        List<Thresholds> thresholds = new ArrayList<>();
        try {
//        Loop for Thresholds; get thresholds and match them with flights list
            for (var thresholdsRows : thresholdsSheet) {
                XSSFRow row = thresholdsSheet.getRow(thresholdsRows.getRowNum());
                if (row.getRowNum() > 0 && row.getCell(0).getStringCellValue() != null) {
                    var threshold = new Thresholds();
                    threshold.setDestination(row.getCell(0).getStringCellValue());
                    threshold.setDealPrice(new Double(row.getCell(1).getNumericCellValue()).longValue());
                    threshold.setNormalPrice(new Double(row.getCell(2).getNumericCellValue()).longValue());
                    threshold.setPercentOff(row.getCell(3).getNumericCellValue());
                    thresholds.add(threshold);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return thresholds;
    }

    private static List<src.main.java.Flights> getFlights(XSSFWorkbook workbook) {
        XSSFSheet flightsSheet = workbook.getSheet("Flights 2022");
        List<src.main.java.Flights> flights = new ArrayList<>();
        //        Loop For Flights; get flights and save in the list if they fulfill Requirements
        for (var rows : flightsSheet) {
            if (rows.getRowNum() > 0 && rows.getCell(0).getDateCellValue() != null) {
                Date currentDate = new Date();
                XSSFRow row = flightsSheet.getRow(rows.getRowNum());
                var date = row.getCell(0).getDateCellValue();
                boolean getSentRecords = StringUtils.equals(row.getCell(14).getStringCellValue(), "Yes");
                var difference = currentDate.getTime() - date.getDate();
                if (difference > 20 && getSentRecords) {
                    if (currentDate.getMonth() == date.getMonth() && currentDate.getDate() == date.getDate()) {
                        var percentOff = row.getCell(8).getRawValue().startsWith(String.valueOf(0)) ? Double.parseDouble(row.getCell(8).getRawValue()) : null;
                        if (percentOff != null && percentOff > 0.60 && percentOff < 0.99) {
                            var flight = new src.main.java.Flights();
                            flight.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
                            flight.setDate(row.getCell(0).getDateCellValue());
                            flight.setOrigin(row.getCell(1).getStringCellValue());
                            flight.setDestination(row.getCell(2).getStringCellValue());
                            flight.setDealPrice(new Double(row.getCell(3).getNumericCellValue()).longValue());
                            flight.setNormalPrice(new Double(row.getCell(4).getNumericCellValue()).longValue());
                            flight.setDepartureDate(row.getCell(5).getDateCellValue());
                            flight.setReturnDate(row.getCell(6).getDateCellValue());
                            flight.setNumberOfStops(new Double(row.getCell(7).getNumericCellValue()).longValue());
                            flight.setPercentOff(row.getCell(8).getNumericCellValue());
                            flight.setAirline(row.getCell(9).getStringCellValue());
                            flight.setDepartureAirport(row.getCell(10).getStringCellValue());
                            flight.setReturningAirport(row.getCell(11).getStringCellValue());
                            flight.setGoodDeal(Boolean.parseBoolean(row.getCell(12).getStringCellValue()));
                            flight.setLinkToFlights(row.getCell(13).getStringCellValue());
                            flight.setSentToPremium(row.getCell(14).getStringCellValue());
                            flight.setSentToFree(row.getCell(15).getStringCellValue());
                            flight.setRemarks(row.getCell(16).getStringCellValue());
                            System.out.println(flight.getDealPrice());
                            flights.add(flight);
                        }
                    } else {
                        System.out.println("No Record of that date Found.\nPlease Update workbook.");
                        break;
                    }
                }
            }
        }
        return flights;
    }

    private static List<src.main.java.Flights> compareLists(XSSFWorkbook workbook) {
        List<src.main.java.Flights> flights = getFlights(workbook);
        var newFlights = new ArrayList<src.main.java.Flights>();
        List<Thresholds> thresholds = getThresholds(workbook);
        for (var flight : flights) {
            for (var threshold : thresholds) {
                if (Objects.equals(threshold.getDestination(), flight.getDestination())) {
                    if (flight.getDealPrice() < threshold.getDealPrice()) {
                        newFlights.add(flight);
                        break;
                    }
                }
            }
        }
        return newFlights;
    }
}

class SendMail {

    public static void main(String[] args) {
        String to = "testmail4mef@gmail.com";
        String from = "nullnameinmail@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nullnameinmail@gmail.com", "gldnwebuvpvkvtdh");
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("This is the Subject Line!");
            message.setText("This is actual message");
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
