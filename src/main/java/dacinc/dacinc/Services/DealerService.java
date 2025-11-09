package dacinc.dacinc.Services;

import java.util.List;

import dacinc.dacinc.Dtos.DealerDto;

public interface DealerService {
    public Long addDealer(DealerDto dealerDto);

    public List<DealerDto> getAllDealer();

    public DealerDto getDealerById(Long dealerId);
}
