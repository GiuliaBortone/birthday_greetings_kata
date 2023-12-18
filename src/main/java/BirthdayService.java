import domain.entity.Employee;
import domain.repository.EmployeeRepository;
import email.EmailSender;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BirthdayService {
    private final EmployeeRepository repository;
    private final EmailSender emailSender;

    public BirthdayService(EmployeeRepository repository, EmailSender emailSender) {
        this.repository = repository;
        this.emailSender = emailSender;
    }

    public void sendBirthdayEmail(LocalDate today) throws IOException {
        List<Employee> employees = repository.extractAllEmployees();

        for (Employee employee : employees) {
            if (isBirthday(today, employee)) {
                emailSender.sendBirthdayEmail(employee);
            }
        }
    }

    private boolean isBirthday(LocalDate today, Employee employee) {
        return employee.dateOfBirth().getDayOfMonth() == today.getDayOfMonth() && employee.dateOfBirth().getMonth() == today.getMonth();
    }
}
