package tech.duhacks.duhacks.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import tech.duhacks.duhacks.dto.HealthProductDto;
import tech.duhacks.duhacks.exception.AuthException;
import tech.duhacks.duhacks.model.HealthProduct;
import tech.duhacks.duhacks.model.MedicationSchedule;
import tech.duhacks.duhacks.repository.HealthProductRepo;
import tech.duhacks.duhacks.repository.UserRepo;
import tech.duhacks.duhacks.schedular.ExpiryEmail;

import java.util.MissingResourceException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HealthProductService {

    private final HealthProductRepo healthProductRepo;
    private final HealthProductMapper healthProductMapper;
    private final ExpiryEmail expiryEmail;
    private final UserRepo userRepo;

    public HealthProductDto add(HealthProductDto hrd){
        var user = userRepo.findById(hrd.userId()).orElseThrow(() ->  new EntityNotFoundException("User Not Found") );

        var healthProduct  = HealthProduct.builder()
                .name(hrd.name())
                .amount(hrd.amount())
                .quantity(hrd.quantity())
                .expiryDate(hrd.expiryDate())
                .user(user)
                .build();

        Set<MedicationSchedule> medicationSchedules = hrd.times().stream().map(time -> {
            MedicationSchedule schedule = new MedicationSchedule();
            schedule.setTime(time);
            schedule.setHealthProduct(healthProduct); // This will link the schedule to the health product
            return schedule;
        }).collect(Collectors.toSet());

        healthProduct.setMedicationSchedules(medicationSchedules); // Associate the schedules with the health product
        var hrqSave =  healthProductMapper.getHealthProductDto(healthProductRepo.save(healthProduct));

        expiryEmail.addMedicine(healthProduct);

        return  hrqSave;
    }

    public boolean deleteProduct(Long id){
        healthProductRepo.deleteById(id);
        healthProductRepo.findById(id).orElseThrow(() -> new AuthException("Failed to delete Product"));
        expiryEmail.removeMedicine(id);
        return true;
    }

}
