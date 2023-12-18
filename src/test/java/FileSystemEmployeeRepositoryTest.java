import fileSystem.FileSystemEmployeeRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

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
}
