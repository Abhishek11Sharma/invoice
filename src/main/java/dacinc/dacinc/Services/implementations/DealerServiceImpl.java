package dacinc.dacinc.Services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dacinc.dacinc.Dtos.AddressDto;
import dacinc.dacinc.Dtos.DealerDto;
import dacinc.dacinc.Entities.Dealer;
import dacinc.dacinc.Repositories.DealerRepository;
import dacinc.dacinc.Services.DealerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Sharma
 * @implNote Method to perform the operations related to Dealer
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DealerServiceImpl implements DealerService {
    private final ModelMapper modelMapper;
    private final DealerRepository dealerRepository;
    private final AddressServiceImpl addressServiceImpl;

    @Transactional
    @Override
    public Long addDealer(DealerDto dealerDto) {
        try {
            Dealer dealer = new Dealer();
            long addressId = addressServiceImpl.addAddress(dealerDto.getAddressDto());
            log.info("Address Id new dealer : {}", addressId);

            dealer.setAddressId(addressId);
            dealer.setEmailId(dealerDto.getEmailId());
            dealer.setFirstName(dealerDto.getFirstName());
            dealer.setLastName(dealerDto.getLastName());
            dealer.setMobile(dealerDto.getMobile());

            dealer = dealerRepository.save(dealer);
            log.info("Dealer has been saved");
            return dealer.getDealerId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DealerDto> getAllDealer() {
        List<DealerDto> dealerDtos = new ArrayList<>();
        try {
            List<Dealer> dealers = dealerRepository.findAll();
            if (!dealers.isEmpty()) {

                for (Dealer dealer : dealers) {
                    AddressDto addressDto = addressServiceImpl.getAddressById(dealer.getAddressId());
                    DealerDto dealerDto = modelMapper.map(dealer, DealerDto.class);
                    dealerDto.setAddressDto(addressDto);
                    dealerDtos.add(dealerDto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dealerDtos;
    }

    @Override
    public DealerDto getDealerById(Long dealerId) {
        try {
            Optional<Dealer> gOptional = dealerRepository.findById(dealerId);
            if (gOptional.isPresent()) {
                Dealer dealer = gOptional.get();
                DealerDto dealerDto = modelMapper.map(dealer, DealerDto.class);

                AddressDto addressDto = addressServiceImpl.getAddressById(dealer.getAddressId());
                dealerDto.setAddressDto(addressDto);

                return dealerDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
