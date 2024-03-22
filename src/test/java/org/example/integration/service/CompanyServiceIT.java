package org.example.integration.service;

import lombok.RequiredArgsConstructor;
import org.example.config.DatabaseProperties;
import org.example.dto.CompanyReadDto;
import org.example.integration.annotation.IT;
import org.example.service.CompanyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = Main.class, initializers = ConfigDataApplicationContextInitializer.class)
//@SpringBootTest
//@ActiveProfiles("test")
@IT
@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyServiceIT {

    public static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {

        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID, null);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}
