package dacinc.dacinc.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InvoiceDto {
    @NotNull
    private Long dealerId;
    @NotNull
    private Long vehicleId;
    @NotNull
    private String customerName;
}
