package tech.duhacks.duhacks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.duhacks.duhacks.model.IntakeMedicine;

@Repository
public interface IntakeMedicineRepo extends JpaRepository<IntakeMedicine, Long> {
}
