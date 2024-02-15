package portfolio.joaom.gestaovagas.modules.candidate.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.joaom.gestaovagas.modules.candidate.CandidateEntity;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{

    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

    Optional<CandidateEntity> findByUsername(String username);
}
