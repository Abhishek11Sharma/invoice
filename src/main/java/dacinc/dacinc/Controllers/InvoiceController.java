package dacinc.dacinc.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dacinc.dacinc.Dtos.InvoiceDto;
import dacinc.dacinc.Services.implementations.InvoiceServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceServiceImpl invoiceServiceImpl;

    @PostMapping("invoice")
    public ResponseEntity<?> generateInvoice(@Valid @RequestBody InvoiceDto invoiceDto) {
        return invoiceServiceImpl.generateInvoice(invoiceDto);
    }
}
