package dacinc.dacinc.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @NotBlank(message = "Address line 1 is required")
    private String address1;

    private String address2="";

    @NotBlank(message = "City name is required")
    private String cityName;

    @NotBlank(message = "State name is required")
    private String stateName;

    @NotBlank(message = "Pincode is required")
    @Size(min = 6, max = 6, message = "Pincode must be exactly 6 digits")
    @Pattern(regexp = "\\d{6}", message = "Pincode must contain only numbers")
    private String pincode;
}
