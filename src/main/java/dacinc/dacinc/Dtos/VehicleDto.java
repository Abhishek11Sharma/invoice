package dacinc.dacinc.Dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class VehicleDto {
    private Long vehicleId;

    @NotBlank(message = "VIN is required")
    private String vin;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotBlank(message = "Manufacturer name is required")
    private String manufacturerBy;

    @NotNull(message = "Manufacturing year is required")
    @Min(value = 1886, message = "Manufacturing year must be valid") // first car invented in 1886 😄
    private Integer manufacturerYear;

    @NotBlank(message = "Vehicle color is required")
    private String vehicleColor;

    @NotBlank(message = "Fuel type is required")
    private String fuelType;

    @NotNull(message = "Base price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Base price must be greater than 0")
    private Float basePrice;

    @NotNull(message = "Tax is required")
    @Min(value = 0, message = "Tax cannot be negative")
    private Integer tax;

    @NotNull(message = "Total price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than 0")
    private Float totalPrice;

    @NotNull(message = "Purchase date is required")
    @PastOrPresent(message = "Purchase date cannot be in the future")
    private LocalDate purchaseDate;
}
