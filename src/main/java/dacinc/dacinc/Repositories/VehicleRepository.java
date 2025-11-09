package dacinc.dacinc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dacinc.dacinc.Entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Long>{
    
}
