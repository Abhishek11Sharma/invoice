package dacinc.dacinc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dacinc.dacinc.Entities.Dealer;

public interface DealerRepository extends JpaRepository<Dealer,Long>{
    
}
