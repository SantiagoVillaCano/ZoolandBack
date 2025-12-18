package apzo.ApiZoo.repository;

import apzo.ApiZoo.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {}