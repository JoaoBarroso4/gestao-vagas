package portfolio.joaom.gestaovagas.modules.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.joaom.gestaovagas.modules.company.entities.CompanyEntity;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);

    Optional<CompanyEntity> findByUsername(String username);
}
