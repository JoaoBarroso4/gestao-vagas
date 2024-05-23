package portfolio.joaom.gestaovagas.modules.candidate.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import portfolio.joaom.gestaovagas.modules.candidate.CandidateEntity;
import portfolio.joaom.gestaovagas.modules.company.entities.JobEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "apply_jobs")
public class ApplyJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false) // this is a read-only column
    private CandidateEntity candidateEntity;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false) // this is a read-only column
    private JobEntity jobEntity;

    @Column(name = "candidate_id")
    private UUID candidateId;

    @Column(name = "job_id")
    private UUID jobId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
