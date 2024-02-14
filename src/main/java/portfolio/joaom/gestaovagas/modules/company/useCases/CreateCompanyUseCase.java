package portfolio.joaom.gestaovagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.joaom.gestaovagas.exceptions.UserAlreadyExistsException;
import portfolio.joaom.gestaovagas.modules.company.entities.CompanyEntity;
import portfolio.joaom.gestaovagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(company -> {
                    throw new UserAlreadyExistsException();
                });
        return this.companyRepository.save(companyEntity);
    }
}
