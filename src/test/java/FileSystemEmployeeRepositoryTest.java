import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileSystemEmployeeRepositoryTest {
    @Test
    void shouldReturnEmptyEmployeeListWhenInputFileContainsOnlyHeader() {
        FileSystemEmployeeRepository repository = new FileSystemEmployeeRepository(Path.of("onlyHeaderFile.txt"));
        assertThat(repository.extractAllEmployees()).asList().isEmpty();
    }


}
