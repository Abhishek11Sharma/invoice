package dacinc.dacinc.Entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String vin;
    private String registrationNumber;
    private String manufacturerBy;
    private Integer ManufacturerYear;
    private String vehicleColor;
    private String fuelType;
    private Float basePrice;
    private int tax;
    private Float totalPrice;
    private LocalDate purchaseDate;
}
