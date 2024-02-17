package portfolio.joaom.gestaovagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.joaom.gestaovagas.modules.company.entities.JobEntity;
import portfolio.joaom.gestaovagas.modules.company.repositories.JobRepository;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
