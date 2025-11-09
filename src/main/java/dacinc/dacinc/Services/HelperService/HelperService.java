package dacinc.dacinc.Services.HelperService;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dacinc.dacinc.Dtos.DealerDto;
import dacinc.dacinc.Entities.Vehicle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HelperService {

    /**
     * @implNote This method will generate the QR code based with transaction id and store on file path and the path will be  used to embed in pdf. 
     */
    public void generateQRCode(String transactionId, String filePath) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(transactionId, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public void createInvoicePdf(String filePath, DealerDto dealer, Vehicle
    // vehicle,
    // String customerName, String invoiceNumber, String timestamp, String qrPath) {

    // try {
    // Document document = new Document();
    // PdfWriter pdfWriter = PdfWriter.getInstance(document, new
    // FileOutputStream(filePath));
    // document.open();

    // PdfContentByte canvas = pdfWriter.getDirectContentUnder();
    // Rectangle rect = new Rectangle(document.getPageSize());
    // rect.setBackgroundColor(new BaseColor(245, 220, 245));
    // canvas.rectangle(rect);

    // Font bold = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    // document.add(new Paragraph("Vehicle Sales Invoice", bold));
    // document.add(new Paragraph("Invoice No: " + invoiceNumber));
    // document.add(new Paragraph("Date: " + timestamp));
    // document.add(Chunk.NEWLINE);

    // document.add(new Paragraph("Dealer Details:", bold));
    // document.add(new Paragraph(dealer.getFirstName() + " " +
    // dealer.getLastName()));
    // document.add(new Paragraph("Email: " + dealer.getEmailId()));
    // document.add(new Paragraph("Mobile: " + dealer.getMobile()));

    // document.add(new Paragraph("Address 1 :" +
    // dealer.getAddressDto().getAddress1()));
    // document.add(new Paragraph("Address 2 :" +
    // dealer.getAddressDto().getAddress2()));
    // document.add(new Paragraph("City :" + dealer.getAddressDto().getCityName()));
    // document.add(new Paragraph("State :" +
    // dealer.getAddressDto().getStateName()));
    // document.add(new Paragraph("Pincode :" +
    // dealer.getAddressDto().getPincode()));

    // document.add(Chunk.NEWLINE);

    // document.add(new Paragraph("Customer Name: " + customerName, bold));
    // document.add(Chunk.NEWLINE);

    // document.add(new Paragraph("Vehicle Details:", bold));
    // document.add(new Paragraph("VIN: " + vehicle.getVin()));
    // document.add(new Paragraph("Manufacturer: " + vehicle.getManufacturerBy()));
    // document.add(new Paragraph("Year: " + vehicle.getManufacturerYear()));
    // document.add(new Paragraph("Color: " + vehicle.getVehicleColor()));
    // document.add(new Paragraph("Fuel: " + vehicle.getFuelType()));
    // document.add(new Paragraph("Base Price: " + vehicle.getBasePrice()));
    // document.add(new Paragraph("Tax: " + vehicle.getTax()+"%"));
    // float totalPrice = vehicle.getBasePrice() + (vehicle.getBasePrice() *
    // vehicle.getTax() / 100);

    // document.add(new Paragraph("Total Price With Tax: ₹" + totalPrice));
    // document.add(Chunk.NEWLINE);

    // Image qrImage = Image.getInstance(qrPath);
    // qrImage.scaleAbsolute(100, 100);
    // qrImage.setAlignment(Element.ALIGN_RIGHT);
    // document.add(qrImage);

    // document.close();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    /**
     * This method will generate the pdf will dealer and vehicle details.
     */
    public void createInvoicePdf(String filePath, DealerDto dealer, Vehicle vehicle,
            String customerName, String invoiceNumber, String timestamp, String qrPath) {

        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            PdfContentByte canvas = writer.getDirectContentUnder();
            Rectangle rect = new Rectangle(document.getPageSize());
            rect.setBackgroundColor(new BaseColor(245, 225, 250));
            canvas.rectangle(rect);

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
            Font keyFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font valueFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            Paragraph title = new Paragraph("Vehicle Sales Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f);
            document.add(title);

            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setSpacingAfter(10f);

            headerTable.addCell(createCell("Invoice No:", keyFont, BaseColor.LIGHT_GRAY));
            headerTable.addCell(createCell(invoiceNumber, valueFont));
            headerTable.addCell(createCell("Date:", keyFont, BaseColor.LIGHT_GRAY));
            headerTable.addCell(createCell(timestamp, valueFont));

            document.add(headerTable);
            document.add(Chunk.NEWLINE);

            Paragraph dealerHeading = new Paragraph("Dealer Details", sectionFont);
            dealerHeading.setSpacingAfter(10f);
            document.add(dealerHeading);

            PdfPTable dealerTable = new PdfPTable(2);
            dealerTable.setWidthPercentage(100);
            dealerTable.setSpacingAfter(15f);
            dealerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            dealerTable.addCell(createCell("Name:", keyFont));
            dealerTable.addCell(createCell(dealer.getFirstName() + " " + dealer.getLastName(), valueFont));
            dealerTable.addCell(createCell("Email:", keyFont));
            dealerTable.addCell(createCell(dealer.getEmailId(), valueFont));
            dealerTable.addCell(createCell("Mobile:", keyFont));
            dealerTable.addCell(createCell(dealer.getMobile(), valueFont));
            dealerTable.addCell(createCell("Address:", keyFont));
            dealerTable.addCell(createCell(dealer.getAddressDto().getAddress1() + ", " +
                    dealer.getAddressDto().getCityName() + ", " +
                    dealer.getAddressDto().getStateName() + " - " +
                    dealer.getAddressDto().getPincode(), valueFont));

            document.add(dealerTable);

            Paragraph custHeading = new Paragraph("Customer Details", sectionFont);
            custHeading.setSpacingAfter(5f);
            document.add(custHeading);
            document.add(new Paragraph("Name: " + customerName, valueFont));
            document.add(Chunk.NEWLINE);

            Paragraph vehicleHeading = new Paragraph("Vehicle Details", sectionFont);
            vehicleHeading.setSpacingAfter(10f);
            document.add(vehicleHeading);

            PdfPTable vehicleTable = new PdfPTable(2);
            vehicleTable.setWidthPercentage(100);
            vehicleTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            vehicleTable.setSpacingAfter(15f);

            vehicleTable.addCell(createCell("VIN:", keyFont));
            vehicleTable.addCell(createCell(vehicle.getVin(), valueFont));
            vehicleTable.addCell(createCell("Manufacturer:", keyFont));
            vehicleTable.addCell(createCell(vehicle.getManufacturerBy(), valueFont));
            vehicleTable.addCell(createCell("Year:", keyFont));
            vehicleTable.addCell(createCell(String.valueOf(vehicle.getManufacturerYear()), valueFont));
            vehicleTable.addCell(createCell("Color:", keyFont));
            vehicleTable.addCell(createCell(vehicle.getVehicleColor(), valueFont));
            vehicleTable.addCell(createCell("Fuel Type:", keyFont));
            vehicleTable.addCell(createCell(vehicle.getFuelType(), valueFont));

            document.add(vehicleTable);

            Paragraph pricingHeading = new Paragraph("Pricing Summary", sectionFont);
            pricingHeading.setSpacingAfter(10f);
            document.add(pricingHeading);

            float totalPrice = vehicle.getBasePrice() + (vehicle.getBasePrice() * vehicle.getTax() / 100);
            PdfPTable priceTable = new PdfPTable(2);
            priceTable.setWidthPercentage(100);
            priceTable.setSpacingAfter(20f);
            priceTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            priceTable.addCell(createCell("Base Price:", keyFont));
            priceTable.addCell(createCell("₹ " + vehicle.getBasePrice(), valueFont));
            priceTable.addCell(createCell("Tax (" + vehicle.getTax() + "%):", keyFont));
            priceTable.addCell(createCell("₹ " + (vehicle.getBasePrice() * vehicle.getTax() / 100), valueFont));
            priceTable.addCell(createCell("Total Price:", keyFont, new BaseColor(230, 255, 230)));
            priceTable.addCell(createCell("₹ " + totalPrice, valueFont, new BaseColor(230, 255, 230)));

            document.add(priceTable);

            if (qrPath != null) {
                Image qrImage = Image.getInstance(qrPath);
                qrImage.scaleAbsolute(100, 100);
                qrImage.setAlignment(Element.ALIGN_RIGHT);
                document.add(qrImage);
            }

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PdfPCell createCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(5f);
        return cell;
    }

    private PdfPCell createCell(String text, Font font, BaseColor bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(bgColor);
        cell.setPadding(5f);
        return cell;
    }

}
