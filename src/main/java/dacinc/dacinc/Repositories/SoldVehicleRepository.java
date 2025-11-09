package dacinc.dacinc.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dacinc.dacinc.Entities.SoldVehicle;

public interface SoldVehicleRepository extends JpaRepository<SoldVehicle,UUID>{
    
}
