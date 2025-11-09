package dacinc.dacinc.Services.implementations;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dacinc.dacinc.Entities.SoldVehicle;
import dacinc.dacinc.Repositories.SoldVehicleRepository;
import dacinc.dacinc.Services.SoldVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Sharma
 * @implNote Method to add the sold vehicle and generate the transaction id that will help in QR code
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SoldVehicleImpl implements SoldVehicleService {

    private final SoldVehicleRepository soldVehicleRepository;

    @Override
    public UUID soldVehicle(Long dealerId, Long vehicleId) {
        try {
            SoldVehicle soldVehicle = new SoldVehicle(UUID.randomUUID(), dealerId, vehicleId);
            soldVehicle = soldVehicleRepository.save(soldVehicle);
            log.info("Vehicle sold transaction has been saved with id : {}",soldVehicle);
            return soldVehicle.getTransactionId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
