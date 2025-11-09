package dacinc.dacinc.Entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldVehicle {

    @Id
    private UUID transactionId;
    private Long dealerId;
    private Long vehicleId;
}
