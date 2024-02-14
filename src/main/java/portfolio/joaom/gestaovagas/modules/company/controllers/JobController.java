package portfolio.joaom.gestaovagas.modules.company.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.joaom.gestaovagas.modules.company.entities.JobEntity;
import portfolio.joaom.gestaovagas.modules.company.useCases.CreateJobUseCase;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    CreateJobUseCase createJobUseCase;

    @RequestMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody JobEntity jobEntity) {
        try {
            var response = this.createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
