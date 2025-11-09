package dacinc.dacinc.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dacinc.dacinc.Dtos.ApiResponseDto;
import dacinc.dacinc.Dtos.VehicleDto;
import dacinc.dacinc.Services.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping("vehicle")
    public ResponseEntity<?> addVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        Long vehicleId = vehicleService.addVehicle(vehicleDto);
        if (vehicleId == null) {
            return ResponseEntity.status(500).body(
                    new ApiResponseDto("Vehicle was not saved due to some exception. Please try later", false, null));
        } else {
            return ResponseEntity.status(201)
                    .body(new ApiResponseDto("Vehicle has been added successfully", true, null));
        }
    }

    @GetMapping("vehicle")
    public ResponseEntity<ApiResponseDto> getAllVehilce() {
        List<VehicleDto> vehicleDtos = vehicleService.getAllVehicle();
        return ResponseEntity.status(200).body(new ApiResponseDto("Available vehicle information", true, vehicleDtos));
    }
}
