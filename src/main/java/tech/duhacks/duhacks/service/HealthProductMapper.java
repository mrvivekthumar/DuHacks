package tech.duhacks.duhacks.service;

import org.springframework.stereotype.Service;
import tech.duhacks.duhacks.dto.HealthProductDto;
import tech.duhacks.duhacks.model.HealthProduct;
import tech.duhacks.duhacks.model.MedicationSchedule;

import java.util.stream.Collectors;

@Service
public class HealthProductMapper {

    public HealthProductDto getHealthProductDto(HealthProduct hp){
        return new HealthProductDto(
                hp.getId(),
                hp.getName(),
                hp.getQuantity(),
                hp.getExpiryDate(),
                hp.getAmount(),
                hp.getUser().getId(),
                hp.getMedicationSchedules().stream().map(MedicationSchedule::getTime).collect(Collectors.toSet())
        );
    }
}
