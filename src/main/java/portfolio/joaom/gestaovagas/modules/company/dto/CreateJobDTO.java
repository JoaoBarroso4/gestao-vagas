package portfolio.joaom.gestaovagas.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {

    private String title;
    private String description;
    private String level;
    private String benefits;
}
