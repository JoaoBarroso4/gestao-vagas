package portfolio.joaom.gestaovagas.modules.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.joaom.gestaovagas.modules.candidate.entity.ApplyJobEntity;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

}
