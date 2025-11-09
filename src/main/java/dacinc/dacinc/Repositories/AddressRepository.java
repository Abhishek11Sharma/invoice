package dacinc.dacinc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dacinc.dacinc.Entities.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {
    
}
