package portfolio.joaom.gestaovagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.joaom.gestaovagas.exceptions.JobNotFoundException;
import portfolio.joaom.gestaovagas.exceptions.UserNotFoundException;
import portfolio.joaom.gestaovagas.modules.candidate.CandidateRepository;
import portfolio.joaom.gestaovagas.modules.candidate.repository.ApplyJobRepository;
import portfolio.joaom.gestaovagas.modules.company.repositories.JobRepository;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public void execute(UUID idCandidate, UUID idJob) {
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);

        this.jobRepository.findById(idJob)
                .orElseThrow(JobNotFoundException::new);
    }
}
