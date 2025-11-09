package dacinc.dacinc.Services.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dacinc.dacinc.Dtos.DealerDto;
import dacinc.dacinc.Dtos.InvoiceDto;
import dacinc.dacinc.Entities.Vehicle;
import dacinc.dacinc.Services.InvoiceService;
import dacinc.dacinc.Services.HelperService.HelperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Sharma
 * @implNote Method for invoice generation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final SoldVehicleImpl soldVehicleImpl;
    private final DealerServiceImpl dealerServiceImpl;
    private final VehicleServiceImpl vehicleServiceImpl;
    private final HelperService helperService;

    @Override
    public ResponseEntity<?> generateInvoice(InvoiceDto invoiceDto) {
        long fileId = System.currentTimeMillis();
        String pdfFilePath = "invoice_" + fileId + ".pdf";
        String qrPath = "invoice_qr_" + fileId + ".png";
        try {
            UUID transactionId = soldVehicleImpl.soldVehicle(invoiceDto.getDealerId(), invoiceDto.getVehicleId());
            if (transactionId != null) {
                DealerDto dealerDto = dealerServiceImpl.getDealerById(invoiceDto.getDealerId());
                Vehicle vehicle = vehicleServiceImpl.getVehicleById(invoiceDto.getVehicleId());

                String invoiceNumber = "INVOICE-" + System.currentTimeMillis();
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                helperService.generateQRCode(transactionId.toString(), qrPath);

                helperService.createInvoicePdf(pdfFilePath, dealerDto, vehicle, invoiceDto.getCustomerName(),
                        invoiceNumber,
                        timestamp, qrPath);
            }
            byte[] pdfBytes = Files.readAllBytes(Paths.get(pdfFilePath));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFilePath)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error generating invoice: " + e.getMessage());
        } finally {
            try {
                if (pdfFilePath != null)
                    Files.deleteIfExists(Path.of(pdfFilePath));
                if (qrPath != null)
                    Files.deleteIfExists(Path.of(qrPath));
            } catch (IOException ioEx) {
                System.err.println("Warning: failed to delete temp files - " + ioEx.getMessage());
            }
        }
    }

}
