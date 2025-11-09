package dacinc.dacinc.Services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dacinc.dacinc.Dtos.VehicleDto;
import dacinc.dacinc.Entities.Vehicle;
import dacinc.dacinc.Repositories.VehicleRepository;
import dacinc.dacinc.Services.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Sharma
 * @implNote Method to perform the operations related to Vehicle
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final ModelMapper modelMapper;
    private final VehicleRepository vehicleRepository;

    @Override
    public Long addVehicle(VehicleDto vehicleDto) {
        try {
            Vehicle vehicle = new Vehicle();
            vehicle = modelMapper.map(vehicleDto, Vehicle.class);
            vehicle = vehicleRepository.save(vehicle);
            return vehicle.getVehicleId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<VehicleDto> getAllVehicle() {
        List<VehicleDto> vehicleDtos = new ArrayList<>();
        try {
            List<Vehicle> vehicles = vehicleRepository.findAll();
            vehicleDtos = vehicles.stream().map(vehicle -> modelMapper.map(vehicle, VehicleDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicleDtos;
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        try {
            Optional<Vehicle> vOptional = vehicleRepository.findById(vehicleId);
            if (vOptional.isPresent()) {
                return vOptional.get();
            }
        } catch (Exception e) {
        }
        return null;
    }

}
