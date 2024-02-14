package portfolio.joaom.gestaovagas.modules.company.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "job")
@Data
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private String level;

    private String benefits;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;
    @Column(name = "company_id")
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
