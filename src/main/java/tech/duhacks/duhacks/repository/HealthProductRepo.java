package tech.duhacks.duhacks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.duhacks.duhacks.model.HealthProduct;

@Repository
public interface HealthProductRepo extends JpaRepository<HealthProduct, Long> {


}
