import domain.entity.Employee;
import fileSystem.FileSystemEmployeeRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileSystemEmployeeRepositoryTest {
    @Test
    void shouldReturnEmptyEmployeeListWhenInputFileContainsOnlyHeader() throws IOException {
        FileSystemEmployeeRepository repository = new FileSystemEmployeeRepository(Path.of("src/test/resources/onlyHeaderFile.txt"));

        assertThat(repository.extractAllEmployees()).asList().isEmpty();
    }

    @Test
    void shouldFindAnEmployeeForEachRowOfInputFile() throws IOException {
        FileSystemEmployeeRepository repository = new FileSystemEmployeeRepository(Path.of("src/test/resources/employees.txt"));

        assertThat(repository.extractAllEmployees()).asList().size().isEqualTo(2);
    }

    @Test
    void shouldParseEmployeeCorrectlyIdentifyingItsData() throws IOException {
        Path employeeFilePath = Files.createTempFile("employee", ".txt");
        String lastName = "Ann";
        String firstName = "Mary";
        String dateOfBirth = "1975/03/11";
        String email = "mary.ann@foobar.com";
        Files.write(employeeFilePath, Arrays.asList(
                "last_name, first_name, date_of_birth, email",
                lastName + ", " + firstName + ", " + dateOfBirth + ", " + email
        ));

        FileSystemEmployeeRepository repository = new FileSystemEmployeeRepository(employeeFilePath);
        Employee employee = repository.extractAllEmployees().get(0);

        assertThat(employee.lastName()).isEqualTo(lastName);
        assertThat(employee.firstName()).isEqualTo(firstName);
        assertThat(employee.dateOfBirth()).isEqualTo(LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("y/M/d")));
        assertThat(employee.email()).isEqualTo(email);
    }
}
