package portfolio.joaom.gestaovagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.joaom.gestaovagas.exceptions.UserAlreadyExistsException;
import portfolio.joaom.gestaovagas.modules.candidate.CandidateEntity;
import portfolio.joaom.gestaovagas.modules.candidate.controllers.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(candidate -> {
                    throw new UserAlreadyExistsException();
                });
        return this.candidateRepository.save(candidateEntity);
    }

}
