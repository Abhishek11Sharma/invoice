package dacinc.dacinc.Services;

import java.util.List;

import dacinc.dacinc.Dtos.VehicleDto;
import dacinc.dacinc.Entities.Vehicle;

public interface VehicleService {
    public Long addVehicle(VehicleDto vehicleDto);

    public List<VehicleDto> getAllVehicle();

    public Vehicle getVehicleById(Long vehicleId);
}
