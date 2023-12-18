package fileSystem;

import domain.entity.Employee;
import domain.repository.EmployeeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystemEmployeeRepository implements EmployeeRepository {
    private final Path filePath;

    public FileSystemEmployeeRepository(Path filePath) {
        this.filePath = filePath;
    }

    public List<Employee> extractAllEmployees() throws IOException {
        List<String> fileRows = Files.readAllLines(filePath);
        return fileRows.stream()
                .skip(1)
                .map(this::parseEmployee)
                .collect(Collectors.toList());
    }

    private Employee parseEmployee(String fileRow) {
        List<String> employeeData = Arrays.stream(fileRow.split(",")).map(String::trim).toList();
        return new Employee(employeeData.get(0), employeeData.get(1), LocalDate.parse(employeeData.get(2), DateTimeFormatter.ofPattern("y/M/d")), employeeData.get(3));
    }
}
