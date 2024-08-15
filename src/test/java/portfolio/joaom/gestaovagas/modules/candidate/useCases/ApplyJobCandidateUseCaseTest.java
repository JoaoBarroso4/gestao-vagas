package portfolio.joaom.gestaovagas.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import portfolio.joaom.gestaovagas.exceptions.JobNotFoundException;
import portfolio.joaom.gestaovagas.exceptions.UserNotFoundException;
import portfolio.joaom.gestaovagas.modules.candidate.entity.CandidateEntity;
import portfolio.joaom.gestaovagas.modules.candidate.repository.CandidateRepository;
import portfolio.joaom.gestaovagas.modules.candidate.entity.ApplyJobEntity;
import portfolio.joaom.gestaovagas.modules.candidate.repository.ApplyJobRepository;
import portfolio.joaom.gestaovagas.modules.company.entities.JobEntity;
import portfolio.joaom.gestaovagas.modules.company.repositories.JobRepository;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks // injects the class
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock // injects the class dependencies
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

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

    @Test
    @DisplayName("should be able to apply for a job")
    public void should_be_able_to_apply_for_a_job() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder()
                .jobId(idJob)
                .candidateId(idCandidate)
                .build();

        var applyJobCreated = ApplyJobEntity.builder()
                .id(UUID.randomUUID())
                .build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
