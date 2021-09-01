package map;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "projectMaps")
public class ProjectMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
//    @MapKey(name = "cardNumber")
    @MapKeyColumn(name = "employee_in_project")
    private Map<String, EmployeeMap> employeeMaps = new HashMap<>();

    public ProjectMap() {
    }

    public ProjectMap(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, EmployeeMap> getEmployeeMaps() {
        return employeeMaps;
    }

    public void setEmployeeMaps(Map<String, EmployeeMap> employees) {
        this.employeeMaps = employees;
    }
}
