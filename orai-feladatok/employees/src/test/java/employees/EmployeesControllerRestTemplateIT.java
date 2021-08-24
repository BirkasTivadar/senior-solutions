package employees;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    EmployeesService service;

    @BeforeEach
    void init() {
        service.deleteAllEmployees();
    }

    @RepeatedTest(2)
    void testEmployees() {
        EmployeeDTO employeeDTO =
                template.postForObject("/api/employees", new CreateEmployeeCommand("John Doe"), EmployeeDTO.class);

        assertEquals("John Doe", employeeDTO.getName());

        template.postForObject("/api/employees", new CreateEmployeeCommand("Jane Doe"), EmployeeDTO.class);

        List<EmployeeDTO> employeeDTOList = template.exchange("/api/employees",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<EmployeeDTO>>() {
                        })
                .getBody();

        assertThat(employeeDTOList)
                .extracting(EmployeeDTO::getName)
                .containsExactly("John Doe", "Jane Doe");
    }
}
