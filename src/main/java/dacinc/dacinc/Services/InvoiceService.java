package dacinc.dacinc.Services;

import org.springframework.http.ResponseEntity;

import dacinc.dacinc.Dtos.InvoiceDto;

public interface InvoiceService {
    public ResponseEntity<?> generateInvoice(InvoiceDto invoiceDto);
}
