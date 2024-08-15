package portfolio.joaom.gestaovagas.modules.candidate.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import portfolio.joaom.gestaovagas.modules.candidate.entity.CandidateEntity;
import portfolio.joaom.gestaovagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import portfolio.joaom.gestaovagas.modules.candidate.useCases.CreateCandidateUseCase;
import portfolio.joaom.gestaovagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import portfolio.joaom.gestaovagas.modules.candidate.useCases.ProfileCandidateUseCase;
import portfolio.joaom.gestaovagas.modules.company.entities.JobEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var candidateId = (String) request.getAttribute("candidateId");
        try {
            var result = this.profileCandidateUseCase.execute(UUID.fromString(candidateId));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidate", description = "Candidate information")
    @Operation(summary = "List all available jobs to candidate by filter", description = "This function lists all " +
            "available jobs to candidate accordingly to a given description.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = JobEntity.class))
                    )
            }),
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findJobsByFilter(@RequestParam String filter) {
        return this.listAllJobsByFilterUseCase.execute(filter);
    }

    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Apply to a job",
            description = "This function allows a candidate to apply to a job.")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID jobId) {
        var candidateId = request.getAttribute("candidateId");

        try {
            var result = this.applyJobCandidateUseCase.execute(UUID.fromString(candidateId.toString()), jobId);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
