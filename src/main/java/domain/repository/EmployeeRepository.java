package domain.repository;

import domain.entity.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> extractAllEmployees() throws IOException;
}
