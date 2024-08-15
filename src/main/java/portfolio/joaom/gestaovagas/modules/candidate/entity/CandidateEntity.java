package portfolio.joaom.gestaovagas.modules.candidate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data // Lombok annotation to generate getters and setters for all fields
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @NotBlank(message = "Username should not be blank")
    @Pattern(regexp = "\\S+", message = "Username should not contain whitespaces")
    @Column(unique=true)
    private String username;

    @Email(message = "Email should be valid")
    @Column(unique=true)
    private String email;

    @Length(min = 6, max = 100, message = "Password should have at least 6 characters and at most 100 characters")
    private String password;

    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
