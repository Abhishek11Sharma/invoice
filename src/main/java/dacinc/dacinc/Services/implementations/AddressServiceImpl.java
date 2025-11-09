package dacinc.dacinc.Services.implementations;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dacinc.dacinc.Dtos.AddressDto;
import dacinc.dacinc.Entities.Address;
import dacinc.dacinc.Repositories.AddressRepository;
import dacinc.dacinc.Services.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Sharma
 * @implNote Method to perform the action related to address
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long addAddress(AddressDto addressDto) {
        try {
            Address address = new Address();
            address = modelMapper.map(addressDto, Address.class);
            address = addressRepository.save(address);
            log.info("Address has been saved with id : {}", address.getAddressId());
            return address.getAddressId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AddressDto getAddressById(Long addressId) {
        try {
            Optional<Address> aOptional = addressRepository.findById(addressId);
            if (aOptional.isPresent()) {
                return modelMapper.map(aOptional.get(), AddressDto.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
