import domain.entity.Employee;
import domain.repository.EmployeeRepository;
import email.EmailSender;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BirthdayServiceTest {
    @Test
    void noBirthdayToday() throws IOException {
        EmployeeRepository repository = new InMemoryEmployeeRepository();
        EmailSender notifier = new SpyEmailSender();
        BirthdayService birthdayService = new BirthdayService(repository, notifier);
        LocalDate today = LocalDate.of(1982, 10, 9);
        birthdayService.sendBirthdayEmail(today);

        assertThat(((SpyEmailSender) notifier).getSentGreetingsCount()).isEqualTo(0);
    }

    @Test
    void oneBirthdayToday() throws IOException {
        EmployeeRepository repository = new InMemoryEmployeeRepository(
                new Employee("Doe", "Joe", LocalDate.of(1982, 10, 8), "john.doe@foobar.com"),
                new Employee("Ann", "Mary", LocalDate.of(1975, 3, 11), "mary.ann@foobar.com")
        );
        EmailSender notifier = new SpyEmailSender();
        BirthdayService birthdayService = new BirthdayService(repository, notifier);
        LocalDate today = LocalDate.of(1982, 10, 8);
        birthdayService.sendBirthdayEmail(today);

        assertThat(((SpyEmailSender) notifier).getSentGreetingsCount()).isEqualTo(1);
    }

    @Test
    void multipleBirthdaysToday() throws IOException {
        EmployeeRepository repository = new InMemoryEmployeeRepository(
                new Employee("Doe", "Joe", LocalDate.of(1982, 10, 8), "john.doe@foobar.com"),
                new Employee("Ann", "Mary", LocalDate.of(1975, 10, 8), "mary.ann@foobar.com")
        );
        EmailSender notifier = new SpyEmailSender();
        BirthdayService birthdayService = new BirthdayService(repository, notifier);
        LocalDate today = LocalDate.of(1982, 10, 8);
        birthdayService.sendBirthdayEmail(today);

        List<String> greetedEmployeesNames = ((SpyEmailSender) notifier).getNotifiedEmployees().stream().map(Employee::firstName).toList();
        assertThat(greetedEmployeesNames).asList().containsAll(List.of("Joe", "Mary"));
    }
}

class SpyEmailSender implements EmailSender {
    private final List<Employee> greetedEmployees = new ArrayList<>();

    public int getSentGreetingsCount() {
        return greetedEmployees.size();
    }

    @Override
    public void sendBirthdayEmail(Employee employee) {
        greetedEmployees.add(employee);
    }

    public List<Employee> getNotifiedEmployees() {
        return greetedEmployees;
    }
}

class InMemoryEmployeeRepository implements EmployeeRepository {
    private final List<Employee> employees;

    public InMemoryEmployeeRepository(Employee... employees) {
        this.employees = Arrays.asList(employees);
    }

    @Override
    public List<Employee> extractAllEmployees() {
        return employees;
    }
}

