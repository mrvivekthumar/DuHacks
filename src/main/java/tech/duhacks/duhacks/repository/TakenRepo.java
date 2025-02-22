package tech.duhacks.duhacks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.duhacks.duhacks.model.Taken;

@Repository
public interface TakenRepo extends JpaRepository<Taken, Long> {
}
