package dacinc.dacinc.Services;

import dacinc.dacinc.Dtos.AddressDto;

public interface AddressService {
    public Long addAddress(AddressDto addressDto);

    public AddressDto getAddressById(Long addressId);
}
