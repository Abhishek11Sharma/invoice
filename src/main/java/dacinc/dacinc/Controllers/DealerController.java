package dacinc.dacinc.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dacinc.dacinc.Dtos.ApiResponseDto;
import dacinc.dacinc.Dtos.DealerDto;
import dacinc.dacinc.Services.DealerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DealerController {

    private final DealerService dealerService;

    @PostMapping("dealer")
    public ResponseEntity<?> addDealer(@Valid @RequestBody DealerDto dealerDto) {
        Long dealerId = dealerService.addDealer(dealerDto);
        if (dealerId == null) {
            return ResponseEntity.status(500).body(
                    new ApiResponseDto("Dealer was not saved due to some exception. Please try later", false, null));
        } else {
            return ResponseEntity.status(201)
                    .body(new ApiResponseDto("Dealer has been added successfully", true, null));
        }
    }

    @GetMapping("dealer")
    public ResponseEntity<ApiResponseDto> getAllDealer() {
        List<DealerDto> dealerDtos = dealerService.getAllDealer();
        return ResponseEntity.status(200).body(new ApiResponseDto("Available dealer information", true, dealerDtos));
    }
}
