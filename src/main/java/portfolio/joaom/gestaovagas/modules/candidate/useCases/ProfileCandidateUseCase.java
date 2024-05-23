package portfolio.joaom.gestaovagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.joaom.gestaovagas.exceptions.UserNotFoundException;
import portfolio.joaom.gestaovagas.modules.candidate.CandidateRepository;
import portfolio.joaom.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);

        return ProfileCandidateResponseDTO.builder()
                .id(candidate.getId())
                .username(candidate.getUsername())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .description(candidate.getDescription())
                .build();
    }
}
