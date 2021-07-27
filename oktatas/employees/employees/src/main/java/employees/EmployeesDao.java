package employees;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
@AllArgsConstructor
public class EmployeesDao {

    private JdbcTemplate jdbcTemplate;

    public List<Employee> findAll() {
        return jdbcTemplate.query("select id, emp_name from employees",
                EmployeesDao::mapRow
        );
    }

    public Employee findById(long id) {
        return jdbcTemplate
                .queryForObject("SELECT id, emp_name FROM employees WHERE id = ?",
                        EmployeesDao::mapRow,
                        id
                );
    }

    public void createEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO employees(emp_name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getName());
            return ps;
        }, keyHolder);
        employee.setId(keyHolder.getKey().longValue());
    }

    public void updateEmployee(Employee employee) {
        jdbcTemplate.update("UPDATE employees SET emp_name = ? WHERE id = ?",
                employee.getName(), employee.getId()
        );
    }

    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM employees WHERE id = ?", id);
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM employees");
    }

    private static Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("emp_name");
        Employee employee = new Employee(id, name);
        return employee;
    }
}
