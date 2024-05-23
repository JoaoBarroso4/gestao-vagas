package portfolio.joaom.gestaovagas.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import portfolio.joaom.gestaovagas.exceptions.JobNotFoundException;
import portfolio.joaom.gestaovagas.exceptions.UserNotFoundException;
import portfolio.joaom.gestaovagas.modules.candidate.CandidateEntity;
import portfolio.joaom.gestaovagas.modules.candidate.CandidateRepository;
import portfolio.joaom.gestaovagas.modules.company.repositories.JobRepository;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks // injects the class
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock // injects the class dependencies
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("should not be able to apply for a job when candidate does not exist")
    public void should_not_be_able_to_apply_for_a_job_when_candidate_does_not_exist() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("should not be able to apply for a job when job does not exist")
    public void should_not_be_able_to_apply_for_a_job_when_job_does_not_exist() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
}
