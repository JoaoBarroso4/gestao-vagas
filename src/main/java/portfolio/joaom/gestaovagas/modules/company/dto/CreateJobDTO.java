package portfolio.joaom.gestaovagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {

    @Schema(example = "Software Engineer", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(example = "We are looking for a software engineer to join our team.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "Junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;

    @Schema(example = "Health insurance, dental insurance, and food allowance.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
}
