package employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute(
                "CREATE TABLE employees (id BIGINT AUTO_INCREMENT, emp_name VARCHAR(255), PRIMARY KEY(id))"
        );

        jdbcTemplate.execute(
                "INSERT INTO employees(emp_name) VALUES ('John Doe')"
        );

        jdbcTemplate.execute(
                "INSERT INTO employees(emp_name) VALUES ('Jane Doe')"
        );

    }
}
