package tech.duhacks.duhacks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.duhacks.duhacks.model.HealthProduct;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HealthProductRepo extends JpaRepository<HealthProduct, Long> {

    List<HealthProduct> findAllByUserIdAndQuantityGreaterThanAndExpiryDateAfter(Long userId, Integer quantity, LocalDate currentDate);
}
